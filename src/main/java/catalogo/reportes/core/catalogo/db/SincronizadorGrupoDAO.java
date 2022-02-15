package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.entity.Empresa;
import catalogo.reportes.core.utils.IEmailHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SincronizadorGrupoDAO {

    Logger logger = LogManager.getLogger(SincronizadorProductoDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    GruposDAO gruposDAO;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    ReportesConfiguration configuration;

    public SincronizadorGrupoDAO(ReportesConfiguration configuration, GruposDAO gruposDAO, UbicacionesDAO ubicacionesDAO, EmpresasDAO empresasDAO) {
        this.configuration = configuration;
        this.gruposDAO = gruposDAO;
        this.ubicacionesDAO = ubicacionesDAO;
        this.empresasDAO = empresasDAO;
    }

    public void sincronizarGrupo(HashMap<String, List<String>> visibilidadProductoASincronizar, Empresa empresa) {
        try {
            Set<catalogo.reportes.core.catalogo.entity.Grupo> grupos = new HashSet<catalogo.reportes.core.catalogo.entity.Grupo>();
            Set<String> sgrupos = new HashSet<String>();
            for (Map.Entry<String, List<String>> visibilidad : visibilidadProductoASincronizar.entrySet()) {
                String nombreGrupo = visibilidad.getKey();
                List<String> gruposOGlnEmpresas = visibilidad.getValue();
                catalogo.reportes.core.catalogo.entity.Grupo grupo = gruposDAO.obtenerGrupo(nombreGrupo, empresa.getId());
                if (grupo == null) {
                    grupo = new catalogo.reportes.core.catalogo.entity.Grupo();
                    grupo.setNombre(nombreGrupo);
                    grupo.setEmpresa(empresa);
                    grupo.setSempresa(empresa.getId());
                    grupo = gruposDAO.save(grupo);
                }

                grupo.setDescripcion("Rondanet");
                grupo.setFueActualizado(true);

                Set<Empresa> empresas = new HashSet<Empresa>();
                Set<String> sempresas = new HashSet<String>();
                for (String gln : gruposOGlnEmpresas) {
                    Optional<catalogo.reportes.core.catalogo.entity.Ubicacion> ubicacion = ubicacionesDAO.findByKey("codigo", gln);
                    if (ubicacion.isPresent()) {
                        Empresa empresaCliente = empresasDAO.findById(ubicacion.get().getSempresa());
                        if(empresaCliente != null) {
                            empresas.add(empresaCliente);
                            sempresas.add(empresaCliente.getId());
                            empresaCliente.getEmpresaGruposParaSincronizacion().add(grupo);
                            empresaCliente.getSempresaGruposParaSincronizacion().add(grupo.getId());
                            empresasDAO.save(empresaCliente);
                        }
                    }
                }
                grupo.getEmpresasParaSincronizacion().addAll(empresas);
                grupo.getSempresasParaSincronizacion().addAll(sempresas);
                gruposDAO.save(grupo);
                grupos.add(grupo);
                sgrupos.add(grupo.getId());
            }
            empresa = empresasDAO.findById(empresa.getId());
            empresa.getGrupos().addAll(grupos);
            empresa.getSgrupos().addAll(sgrupos);
            empresasDAO.save(empresa);
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar los grupos. Error: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorGrupoDAO", e);
        }
    }

}
