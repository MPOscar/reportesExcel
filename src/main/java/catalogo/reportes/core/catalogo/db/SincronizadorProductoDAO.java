package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.exceptions.ServiceException;
import catalogo.reportes.core.catalogo.services.interfaces.IFileService;
import catalogo.reportes.core.catalogoViejo.catalogoRepositories.ICatalogoViejoProductoRepository;
import catalogo.reportes.core.services.interfaces.ISincronizadorService;
import catalogo.reportes.core.utils.IEmailHelper;
import common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import catalogo.reportes.core.catalogo.entity.*;

import java.math.BigDecimal;
import java.util.*;

@Component
public class SincronizadorProductoDAO {

    Logger logger = LogManager.getLogger(SincronizadorProductoDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    EmpaquesDAO empaquesDAO;

    @Autowired
    UsuariosDAO usuariosDAO;

    @Autowired
    UsuarioEmpresaDAO usuarioEmpresaDAO;

    @Autowired
    ProductosDAO productosDAO;

    @Autowired
    PresentacionesDAO presentacionesDAO;

    @Autowired
    private ProductosAccionesDAO productosAccionesDAO;

    @Autowired
    CategoriasDAO categoriasDAO;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    @Autowired
    ListasDeVentaDAO listasDeVentaDAO;

    @Autowired
    ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO;

    @Autowired
    ISincronizadorService sincronizadorService;

    @Autowired
    ICatalogoViejoProductoRepository catalogoViejoProductoRepository;

    @Autowired
    EmpaquesDAO packsRepository;

    @Autowired
    IFileService fileService;

    ReportesConfiguration configuration;

    public SincronizadorProductoDAO(ReportesConfiguration configuration, UbicacionesDAO ubicacionesDAO, ListasDeVentaDAO listasDeVentaDAO,
                                    ProductosDAO productosDAO, EmpaquesDAO empaquesDAO, PresentacionesDAO presentacionesDAO, ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO,
                                    CategoriasDAO categoriasDAO) {
        this.configuration = configuration;
        this.ubicacionesDAO = ubicacionesDAO;
        this.listasDeVentaDAO = listasDeVentaDAO;
        this.productosDAO = productosDAO;
        this.empaquesDAO = empaquesDAO;
        this.presentacionesDAO = presentacionesDAO;
        this.listaDeVentaVisibilidadDAO = listaDeVentaVisibilidadDAO;
        this.categoriasDAO = categoriasDAO;
    }

    public void sincronizarProducto(List<ProductoGtin> productosASincronizar) {
        try {
            String gln = productosASincronizar.get(0).getId().getGln();
            Optional<catalogo.reportes.core.catalogo.entity.Ubicacion> ubicacion = ubicacionesDAO.findByKey("codigo", gln);
            if (ubicacion.isPresent()) {
                Empresa empresa = ubicacion.get().getEmpresa();
                if (this.configuration.empresasASincronizar(empresa.getRut())) {
                    Optional<ListaDeVenta> optionalListaDeVenta = listasDeVentaDAO.findFirstBySubicacionAndNombre(ubicacion.get().getId(), ubicacion.get().getDescripcion());
                    ListaDeVenta listaDeVenta = new ListaDeVenta();
                    boolean esNuevo = false;
                    boolean occurioAlgunCambio = false;
                    if (!optionalListaDeVenta.isPresent()) {
                        listaDeVenta.setNombre(ubicacion.get().getDescripcion());
                        listaDeVenta.setDescripcion(ubicacion.get().getDescripcion());
                        listaDeVenta.setEmpresa(ubicacion.get().getEmpresa());
                        listaDeVenta.setSempresa(ubicacion.get().getEmpresa().getId());
                        listaDeVenta.setUbicacion(ubicacion.get());
                        listaDeVenta.setSubicacion(ubicacion.get().getId());
                        listaDeVenta.setGlnUbicacion(ubicacion.get().getCodigo());
                        listasDeVentaDAO.save(listaDeVenta);
                    }

                    for (ProductoGtin productoASincronizar: productosASincronizar) {
                        try {
                            Producto producto = new catalogo.reportes.core.catalogo.entity.Producto();
                            Optional<catalogo.reportes.core.catalogo.entity.Producto> optionalProducto = productosDAO.findByIdEmpresaAndCpp(empresa.getId(), productoASincronizar.getId().getCpp());

                            if (optionalProducto.isPresent()) {
                                producto = optionalProducto.get();
                            } else {
                                producto.setCpp(productoASincronizar.getId().getCpp());
                                producto.setEmpresa(empresa);
                                producto.setSempresa(empresa.getId());
                                producto.setEsPrincipal(productosDAO.esPrincipal(productoASincronizar.getGtin13()));
                                esNuevo = true;
                            }

                            if (productoASincronizar.getPresentacionPideUnidad() != null && productoASincronizar.getPresentacionPideUnidad().equals("UN")) {
                                productoASincronizar.setPresentacionPideUnidad("EA");
                            }

                            if (!esNuevo) {
                                occurioAlgunCambio = ocurrioAlgunCambioEnLosAtributosDelProducto(producto, productoASincronizar);
                                if (!occurioAlgunCambio) {
                                    occurioAlgunCambio = ocurrioAlgunCambioEnLaVisibilidadDelProducto(gln, producto);
                                }
                                if (!occurioAlgunCambio && (producto.getUnidadMedida() != null && producto.getUnidadMedida().equals("UN"))) {
                                    occurioAlgunCambio = true;
                                }
                                if (!occurioAlgunCambio && ocurrioAlgunCambioEnLasFechasDeSuspension(producto, productoASincronizar)) {
                                    occurioAlgunCambio = true;
                                }
                            }

                            if (esNuevo || occurioAlgunCambio) {
                                producto.setCambiosEnAtributosDelProducto(esNuevo || occurioAlgunCambio);
                                producto.setDescripcion(productoASincronizar.getDescripcion());
                                producto.setMarca(productoASincronizar.getMarca());
                                producto.setGtin(productoASincronizar.getGtin13());
                                producto.setPaisOrigen(productoASincronizar.getPais());
                                producto.setGtinPresentacion(String.valueOf(productoASincronizar.getGtin14()));
                                producto.setContenidoNeto(new BigDecimal(productoASincronizar.getPresentacionCantidad()));
                                producto.setUnidadMedida(productoASincronizar.getPresentacionPideUnidad());

                                producto.setDivision(productoASincronizar.getDivision());
                                producto.setLinea(productoASincronizar.getLinea());
                                producto.setEsPromo(productoASincronizar.getEsPromo().contains("S") ? true : false);
                                producto.setNivelMinimoVenta(Integer.valueOf(productoASincronizar.getUnidadesPorEmpaque()));

                                Integer cajasXPallet = Integer.parseInt(productoASincronizar.getEmpaquesPorCamada())
                                        * Integer.parseInt(productoASincronizar.getCamadasPorPallet());
                                Integer unidadesVenta = cajasXPallet * Integer.parseInt(productoASincronizar.getUnidadesPorEmpaque());

                                if (producto.getPallet() != null) {
                                    producto.getPallet().setUnidadesVenta(String.valueOf(unidadesVenta));
                                    producto.getPallet().setCajas(String.valueOf(cajasXPallet));
                                    producto.getPallet().setCamadas(productoASincronizar.getCamadasPorPallet());
                                } else {
                                    Pallet pallet = new Pallet();
                                    pallet.setAlto("");
                                    pallet.setAncho("");
                                    pallet.setUnidadesVenta(String.valueOf(unidadesVenta));
                                    pallet.setCajas(String.valueOf(cajasXPallet));
                                    pallet.setCamadas(productoASincronizar.getCamadasPorPallet());
                                    producto.setPallet(pallet);
                                }

                                if (producto.getEmpaques() != null && producto.getEmpaques().size() > 0) {
                                    Set<Empaque> empaqueList = producto.getEmpaques();
                                    for (Empaque empaque : empaqueList) {
                                        if (empaque != null) {
                                            this.empaquesDAO.delete(empaque);
                                        }
                                    }
                                    producto.getEmpaques().clear();
                                    producto.getSempaques().clear();
                                }

                                Empaque empaque = new Empaque();
                                empaque.setEmpresa(empresa);
                                empaque.setSempresa(empresa.getId());
                                empaque.setCpp(producto.getCpp());
                                empaque.setGtin(productoASincronizar.getGtin14());
                                empaque.setCantidad(new BigDecimal(productoASincronizar.getUnidadesPorEmpaque()));
                                empaque.setNivel(1);
                                empaque.setClasificacion("Caja");

                                empaque = empaquesDAO.save(empaque);
                                producto.getEmpaques().add(empaque);
                                producto.getSempaques().add(empaque.getId());

                                if (!productoASincronizar.getTipoPresentacion().equals("")) {
                                    Optional<Presentacion> optionalPresentacion = presentacionesDAO.findByName(productoASincronizar.getTipoPresentacion());
                                    if (optionalPresentacion.isPresent()) {
                                        producto.setPresentacion(optionalPresentacion.get());
                                    }
                                } else {
                                    Optional<Presentacion> optionalPresentacion = presentacionesDAO.findByName("Sin Empaque");
                                    if (optionalPresentacion.isPresent()) {
                                        producto.setPresentacion(optionalPresentacion.get());
                                    }
                                }
                                if (producto.getMercadoObjetivo() == null) {
                                    producto.setMercadoObjetivo("UY");
                                }
                                producto.setFechaCreacion(new DateTime(productoASincronizar.getFecha_alta()));
                                producto.setFechaEdicion(new DateTime(productoASincronizar.getFecha_actualizacion()));
                                if (producto.getCategoria() != null) {
                                    categoriasDAO.delete(producto.getCategoria());
                                }

                                Categoria categoria = categoriasDAO.addCategory(empresa, producto);
                                producto.setCategoria(categoria);
                                producto.setScategoria(categoria.getId());

                                producto.noEliminado();

                                producto.setVisibilidadEnCatalogoViejo(productoASincronizar.getVisibilidad());
                                producto.setEsPublico(false);
                                producto.setEsPrivado(true);
                                producto.getEmpresasConVisibilidad().clear();
                                producto.getSempresasConVisibilidad().clear();
                                producto.getGruposConVisibilidad().clear();
                                producto.getSgruposConVisibilidad().clear();
                                producto = actualizarFechasDeSuspension(producto, productoASincronizar);
                                producto = productosDAO.save(producto);
                                sincronizadorService.enviarProductoQueFueActualizado(producto.getId());
                            }
                        } catch (Exception e) {
                            logger.log(Level.INFO, "Ocurrió un error al sincronizar los producto: " + e.getMessage());
                            this.emailHelper.sendErrorEmail("SincronizadorProductoDAO", e);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar los producto: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorProductoDAO", e);
            e.printStackTrace();
        }
    }

    public boolean ocurrioAlgunCambioEnLosAtributosDelProducto(Producto producto, common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin productoASincronizar){
        boolean occurioAlgunCambio = false;
        if(
            comprobarSiHuboAlgunCambio(producto.getDescripcion(), productoASincronizar.getDescripcion()) ||
            comprobarSiHuboAlgunCambio(producto.getMarca(), productoASincronizar.getMarca()) ||
            comprobarSiHuboAlgunCambio(producto.getPaisOrigen(), productoASincronizar.getPais()) ||
            comprobarSiHuboAlgunCambio(producto.getGtinPresentacion(), String.valueOf(productoASincronizar.getGtin14())) ||
            comprobarSiHuboAlgunCambio(producto.getContenidoNeto(), new BigDecimal(productoASincronizar.getPresentacionCantidad())) ||
            comprobarSiHuboAlgunCambio(producto.getUnidadMedida(), productoASincronizar.getPresentacionPideUnidad()) ||
            comprobarSiHuboAlgunCambio(producto.getDivision(), productoASincronizar.getDivision()) ||
            comprobarSiHuboAlgunCambio(producto.getLinea(), productoASincronizar.getLinea()) ||
            comprobarSiHuboAlgunCambio(producto.getVisibilidadEnCatalogoViejo(), productoASincronizar.getVisibilidad()) ||
            chequearCambiosEnLaPresentacion(producto, productoASincronizar) ||
            chequearCambiosEnLosEmpaques(producto, productoASincronizar)
            ){
                occurioAlgunCambio = true;
            }

        return occurioAlgunCambio;
    }

    public boolean ocurrioAlgunCambioEnLaVisibilidadDelProducto(String gln, Producto producto){
        boolean occurioAlgunCambio = false;
        if(producto.getEsPublico() || (!producto.getEsPublico() && !producto.getEsPrivado())){
            occurioAlgunCambio = true;
        }

        return occurioAlgunCambio;
    }

    public boolean chequearCambiosEnLaPresentacion(Producto producto, common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin productoASincronizar){
        if(producto.getPresentacion() == null ||
          (producto.getPresentacion() != null && producto.getPresentacion().getNombre().equals("Sin Empaque") && !productoASincronizar.getTipoPresentacion().equals("")) ||
          (producto.getPresentacion() != null && !producto.getPresentacion().getNombre().equals("Sin Empaque") && !producto.getPresentacion().getNombre().equals(productoASincronizar.getTipoPresentacion()))){
            return true;
        }
        return false;
    }

    public boolean chequearCambiosEnLosEmpaques(Producto producto, common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin productoASincronizar){
        if(producto.getEmpaques().size() != 1){
            return false;
        }
        Empaque empaque = producto.getEmpaques().iterator().next();
        if(empaque != null) {
            if (comprobarSiHuboAlgunCambio(empaque.getCpp(), productoASincronizar.getId().getCpp()) ||
                    comprobarSiHuboAlgunCambio(empaque.getGtin(), productoASincronizar.getGtin14()) ||
                    comprobarSiHuboAlgunCambio(empaque.getCantidad(), new BigDecimal(productoASincronizar.getUnidadesPorEmpaque()))
            ) {
                return true;
            }
        }else{
            producto.getEmpaques().clear();
            producto.getSempaques().clear();
            productosDAO.save(producto);
            return true;
        }
        return false;
    }

    public boolean ocurrioAlgunCambioEnLasFechasDeSuspension(Producto producto, common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin productoASincronizar){
        boolean ocurrioAlgunCambioEnLasFechas = false;
        if(ocurrioAlgunCambioEnLasFechasDeSuspencion(producto.getSuspendidoDesde(), productoASincronizar.getSuspendidoDesde() != null ? new DateTime(productoASincronizar.getSuspendidoDesde()) : null ) || ocurrioAlgunCambioEnLasFechasDeSuspencion(producto.getSuspendidoHasta(), productoASincronizar.getSuspendidoHasta() != null ? new DateTime(productoASincronizar.getSuspendidoHasta()) : null )) {
            ocurrioAlgunCambioEnLasFechas = true;
        }
        return ocurrioAlgunCambioEnLasFechas;
    }

    public boolean ocurrioAlgunCambioEnLasFechasDeSuspencion(DateTime productoFecha, DateTime productoCatalogoViejoFecha){
        boolean ocurrionAlgunCambioEnLasFechasDeSuspencion = false;
        if(productoFecha == null && productoCatalogoViejoFecha != null || productoFecha != null && productoCatalogoViejoFecha == null){
            ocurrionAlgunCambioEnLasFechasDeSuspencion = true;
        } else if(productoFecha != null && productoCatalogoViejoFecha != null) {
            if((productoFecha.getDayOfMonth() != productoCatalogoViejoFecha.getDayOfMonth()) || (productoFecha.getMonthOfYear() != productoCatalogoViejoFecha.getMonthOfYear()) || (productoFecha.getYear() != productoCatalogoViejoFecha.getYear())){
                ocurrionAlgunCambioEnLasFechasDeSuspencion = true;
            }
        }
        return ocurrionAlgunCambioEnLasFechasDeSuspencion;
    }

    public Producto actualizarFechasDeSuspension(Producto producto, common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin productoASincronizar){
        producto.setSuspendidoDesde(productoASincronizar.getSuspendidoDesde() != null ? new DateTime(productoASincronizar.getSuspendidoDesde()) : null);
        producto.setSuspendidoHasta(productoASincronizar.getSuspendidoHasta() != null ? new DateTime(productoASincronizar.getSuspendidoHasta()) : null);
        return producto;
    }

    boolean comprobarSiHuboAlgunCambio(Object original, Object actualizado){
        boolean ocurrioAlgunCambio = false;
        if(original != null && actualizado == null || original == null && actualizado != null){
            ocurrioAlgunCambio = true;
        }else if(original != null && actualizado != null && !original.equals(actualizado)){
            ocurrioAlgunCambio = true;
        }
        return ocurrioAlgunCambio;
    }

    public void sincronizarProductosEliminadosDelCatalogoViejo() {
        try {
            List<catalogo.reportes.core.catalogo.entity.Empresa> empresas = empresasDAO.getAllIdEmpresas();
            for (catalogo.reportes.core.catalogo.entity.Empresa empresa : empresas) {
                empresa = empresasDAO.findById(empresa.getId());
                if (configuration.empresasASincronizar(empresa.getRut())) {
                    List<Ubicacion> ubicaciones = ubicacionesDAO.buscarUbicacionesDeEmpresa(empresa);
                    if (ubicaciones.size() > 0) {
                        List<Producto> productos = productosDAO.findAllByEmpresa(empresa.getId());
                        for (Producto producto : productos) {
                            producto = productosDAO.findById(producto.getId());
                            boolean existe = false;
                            for (Ubicacion ubicacion : ubicaciones) {
                                common.rondanet.clasico.core.catalogo.catalogoModels.Producto productoCatalogoViejo = catalogoViejoProductoRepository.findByGlnAndCpp(ubicacion.getCodigo(), producto.getCpp());
                                if (productoCatalogoViejo != null) {
                                    existe = true;
                                    break;
                                }
                            }
                            if (!existe) {
                                eliminarProducto(producto);
                            }
                        }
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Ocurrió un error al sincronizarProductosEliminadosDelCatalogoViejo: " + e.getMessage());
        }
    }

    public void eliminarProducto(Producto producto) throws ServiceException {
        if (producto != null) {
            Empresa empresa = producto.getEmpresa();
            if (empresa != null && empresa.getValidado() && !empresa.getEliminado()) {
                eliminarEmpaquesProducto(producto);
                if (producto.getEmpresa().getId().equals(empresa.getId())) {
                    actualizarProductoEliminadoEnEmpresaYListasDeVenta(empresa, producto);
                    producto = actualizarProductoEliminado(producto);
                    productosAccionesDAO.actualizarAccionesAlEliminarUnProducto(producto);
                } else {
                    throw new ServiceException("Solo se pueden eliminar productos de la propia empresa");
                }
            } else {
                throw new ServiceException("Antes de eliminar un producto un administrador debe validar esta empresa");
            }
        }
    }

    public Producto actualizarProductoEliminado(Producto producto){
        UUID uuid = UUID.randomUUID();
        producto.eliminar();
        producto.setCpp(producto.getCpp() + "_eliminado_sincronizador" + uuid);
        producto.setGtin(producto.getGtin() + "_eliminado_sincronizador" + uuid);
        producto.getEmpresasConVisibilidad().clear();
        producto.getSempresasConVisibilidad().clear();
        producto.getGruposConVisibilidad().clear();
        producto.getSgruposConVisibilidad().clear();
        if(producto.getFoto() != null){
            fileService.deleteFile(producto.getFoto(), configuration.getConfiguracionDespliegue().getBucket());
        }
        return this.productosDAO.update(producto);
    }

    public Producto eliminarEmpaquesProducto(Producto producto){
        Set<Empaque> empaques = producto.getEmpaques();
        for (Empaque empaque: empaques) {
            if(empaque != null) {
                if (empaque.getPadre() != null) {
                    if (empaque.getPadre().getPadre() != null) {
                        packsRepository.delete(empaque.getPadre().getPadre());
                    }
                    packsRepository.delete(empaque.getPadre());
                }
                packsRepository.delete(empaque);
            }
        }
        producto.getEmpaques().clear();
        producto.getSempaques().clear();
        producto = productosDAO.save(producto);
        return producto;
    }

    public void actualizarProductoEliminadoEnEmpresaYListasDeVenta(Empresa empresa, Producto producto){
        empresa.getProductosEmpresa().remove(producto);
        empresa.getSproductosEmpresa().remove(producto.getId());
        empresasDAO.save(empresa);
        List<ListaDeVentaVisibilidad> listaDeVentaVisibilidades = listaDeVentaVisibilidadDAO.findAllByProducto(producto.getId());
        for (ListaDeVentaVisibilidad listaDeVentaVisibilidad: listaDeVentaVisibilidades) {
            listaDeVentaVisibilidad.eliminar();
            ListaDeVenta listaDeVenta = listasDeVentaDAO.findById(listaDeVentaVisibilidad.getSlistaDeVenta());
            if(listaDeVenta != null) {
                listaDeVenta.getProductos().remove(producto);
                listaDeVenta.getSproductos().remove(producto.getId());
                listasDeVentaDAO.save(listaDeVenta);
            }
            listaDeVentaVisibilidadDAO.save(listaDeVentaVisibilidad);
        }
    }
}
