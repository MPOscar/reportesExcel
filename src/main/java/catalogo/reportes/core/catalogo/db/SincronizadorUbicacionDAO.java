package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesApplication;
import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.entity.Empresa;
import catalogo.reportes.core.utils.IEmailHelper;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Ubicacion;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import catalogo.reportes.core.catalogo.repository.IUbicacionRepository;

import java.util.Optional;

@Component
public class SincronizadorUbicacionDAO {

    Logger logger = LogManager.getLogger(SincronizadorUbicacionDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    IUbicacionRepository ubicacionRepository;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    ReportesConfiguration configuration;

    public SincronizadorUbicacionDAO(ReportesConfiguration configuration, EmpresasDAO empresasDAO, UbicacionesDAO ubicacionesDAO, IUbicacionRepository ubicacionRepository) {
        this.configuration = configuration;
        this.empresasDAO = empresasDAO;
        this.ubicacionesDAO = ubicacionesDAO;
        this.ubicacionRepository = ubicacionRepository;
    }

    public void sincronizarUbicacion(Ubicacion ubicacionASincronizar) {
        try {
            Optional<Empresa> optionalEmpresa = empresasDAO.findByKey("codigoInterno", ubicacionASincronizar.getCodigoInternoEmpresa());
            if (optionalEmpresa.isPresent() && this.configuration.empresasASincronizar(optionalEmpresa.get().getRut())) {
                Empresa empresa = optionalEmpresa.get();
                if (ubicacionASincronizar.getTipoUbicacion().contains("E")) {
                    empresa.setGln(ubicacionASincronizar.getId().getCodigo());
                }

                Optional<catalogo.reportes.core.catalogo.entity.Ubicacion> optionalUbicacion = ubicacionesDAO.findByKey("codigo", ubicacionASincronizar.getId().getCodigo());
                catalogo.reportes.core.catalogo.entity.Ubicacion ubicacion = new catalogo.reportes.core.catalogo.entity.Ubicacion();
                if (optionalUbicacion.isPresent()) {
                    ubicacion = optionalUbicacion.get();
                } else {
                    ubicacion.setEmpresa(empresa);
                    ubicacion.setSempresa(empresa.getId());
                }
                ubicacion.setCodigo(ubicacionASincronizar.getId().getCodigo());
                ubicacion.setDescripcion(ubicacionASincronizar.getDescripcion());
                ubicacion.setTipo(ubicacionASincronizar.getTipoUbicacion());
                ubicacion.setFechaAlta(new DateTime(ubicacionASincronizar.getFechaAlta()));
                ubicacion.setFechaBaja(null);
                ubicacion = ubicacionesDAO.save(ubicacion);
                empresa.getUbicaciones().add(ubicacion);
                empresa.getSubicaciones().add(ubicacion.getId());
                empresasDAO.save(empresa);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar el producto con cpp: "
                    + ". Error: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorUbicacionDAO", e);

        }
    }
}
