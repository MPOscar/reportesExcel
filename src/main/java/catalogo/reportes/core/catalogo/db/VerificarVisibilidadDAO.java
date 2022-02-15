package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.entity.*;
import catalogo.reportes.core.catalogoViejo.catalogoRepositories.ICatalogoViejoGrupoRepository;
import catalogo.reportes.core.catalogoViejo.catalogoRepositories.ICatalogoViejoProductoRepository;
import catalogo.reportes.core.catalogoViejo.catalogoRepositories.ICatalogoViejoVisibilidadRepository;
import catalogo.reportes.core.services.implementations.SincronizadorService;
import catalogo.reportes.core.utils.IEmailHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class VerificarVisibilidadDAO {

    Logger logger = LogManager.getLogger(VerificarVisibilidadDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    GruposDAO gruposDAO;

    @Autowired
    ProductosDAO productosDAO;

    @Autowired
    ListasDeVentaDAO listasDeVentaDAO;

    @Autowired
    ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO;

    @Autowired
    SincronizadorService sincronizadorService;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    @Autowired
    ICatalogoViejoGrupoRepository catalogoViejoGrupoRepository;

    @Autowired
    ICatalogoViejoVisibilidadRepository catalogoViejoVisibilidadRepository;

    @Autowired
    ICatalogoViejoProductoRepository catalogoViejoProductoRepository;

    ReportesConfiguration configuration;

    VerificarVisibilidadDAO(ReportesConfiguration configuration){
        this.configuration = configuration;
    }

    private int porcientoAnterior = 0;

    public void verificarVisibilidad(){
        try {
            List<Empresa> empresas = empresasDAO.getAllIdEmpresas();
            List<Ubicacion> ubicacionesProveedor = buscarUbicaciones(empresas);
            if (ubicacionesProveedor.size() > 0) {
                for (Ubicacion ubicacionProveedor : ubicacionesProveedor) {
                    int cantidadDeEmpresas = 0;
                    if(ubicacionProveedor != null) {
                        Optional<ListaDeVenta> listaDeVenta = listasDeVentaDAO.findFirstBySubicacionAndNombre(ubicacionProveedor.getId(), ubicacionProveedor.getDescripcion());
                        if (listaDeVenta.isPresent()) {
                            List<BigDecimal> glnClientes = catalogoViejoGrupoRepository.findAllByGlnGroupByGln2(ubicacionProveedor.getCodigo());
                            int totalEmpresas = glnClientes.size();
                            for (BigDecimal glnCliente: glnClientes) {
                                String gln = String.valueOf(glnCliente);
                                Optional<Ubicacion> optionalUbicacion = ubicacionesDAO.findByKey("codigo", gln);
                                if (optionalUbicacion.isPresent()) {
                                    Empresa empresa = optionalUbicacion.get().getEmpresa();
                                    if (empresa != null && !empresa.getId().equals(ubicacionProveedor.getSempresa())) {
                                        List<Ubicacion> ubicaciones = ubicacionesDAO.buscarUbicacionesDeEmpresaODeposito(empresa);
                                        if (ubicaciones.size() > 0) {
                                            try {
                                                long totalDeProductosVisiblesCatalogoViejo = obtenerTotalDeProductosEnListaDeVenta(ubicacionProveedor, ubicaciones);
                                                long totalDeProductosVisiblesEnListaDeVenta = productosDAO.getTotalDeProductosVisiblesEnListaDeVenta(empresa, listaDeVenta.get());
                                                if (totalDeProductosVisiblesCatalogoViejo != totalDeProductosVisiblesEnListaDeVenta) {
                                                    logger.log(Level.ERROR, "no coincide la visibilidad: " + ubicacionProveedor.getCodigo() + "--> cliente: " + empresa.getId());
                                                }
                                                enviarPorciento(totalEmpresas, cantidadDeEmpresas);
                                            } catch (Exception exception) {
                                                logger.log(Level.ERROR, "no coincide la visibilidad: " + ubicacionProveedor.getCodigo() + "--> cliente: " + empresa.getId());
                                            }
                                        }
                                    }
                                    cantidadDeEmpresas++;
                                    enviarPorciento(totalEmpresas, cantidadDeEmpresas);
                                }
                            }
                        } else {
                            logger.log(Level.ERROR, "No existe la lista de venta: " + ubicacionProveedor.getCodigo());
                        }
                    }
                 }
            }
            porcientoAnterior = 0;
        } catch (Exception exception) {
            enviarEmail("verificarVisibilidad", "No existe la ubicacion : " + exception.getStackTrace());
        }

    }

    public List<Ubicacion> buscarUbicaciones(List<Empresa> empresas) {
        List<Ubicacion> ubicacionesProveedor = new ArrayList<>();
        for (Empresa proveedor : empresas) {
            proveedor = empresasDAO.findById(proveedor.getId());
            if (proveedor != null && configuration.rutEmpresasASincronizarVisibilidad(proveedor.getRut()) && configuration.empresasASincronizar(proveedor.getRut())) {
                ubicacionesProveedor.addAll(ubicacionesDAO.buscarUbicacionesDeEmpresa(proveedor));
            }
        }
        return ubicacionesProveedor;
    }

    public void enviarPorciento(int totalRegistros, int totalDeRegistrosProcesados){
        int porciento = (totalDeRegistrosProcesados * 100) / totalRegistros;
        if(porciento != porcientoAnterior) {
            porcientoAnterior = porciento;
        }
    }

    public long obtenerTotalDeProductosEnListaDeVenta(Ubicacion ubicacionProveedor, List<Ubicacion> ubicaciones){
        long total = 0;
        for (Ubicacion ubicacion: ubicaciones) {
            if(ubicacion != null) {
                long cantidad = catalogoViejoVisibilidadRepository.getTotalDeProductosVisiblesOrdenCompra(ubicacionProveedor.getCodigo(), ubicacion.getCodigo());
                if (cantidad > total) {
                    total = cantidad;
                }
            } else {
                enviarEmail("verificarVisibilidad", "No existe : ubicacion");
            }
        }
        return total;
    }

    public void enviarEmail(String funcion, String error){
        Exception exception = new Exception(error);
        this.emailHelper.sendErrorEmail(funcion, exception);
    }


}
