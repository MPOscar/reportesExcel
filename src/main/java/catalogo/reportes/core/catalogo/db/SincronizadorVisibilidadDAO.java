package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesApplication;
import catalogo.reportes.core.catalogo.resources.GruposYEmpresas;
import catalogo.reportes.core.catalogoViejo.catalogoRepositories.ICatalogoViejoGrupoRepository;
import catalogo.reportes.core.catalogoViejo.catalogoServices.interfaces.ICatalogoViejoProductosService;
import catalogo.reportes.core.services.implementations.SincronizadorService;
import catalogo.reportes.core.utils.IEmailHelper;
import common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import catalogo.reportes.core.catalogo.entity.*;

import java.util.*;

@Component
public class SincronizadorVisibilidadDAO {

    Logger logger = LogManager.getLogger(SincronizadorVisibilidadDAO.class);

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
    ICatalogoViejoProductosService catalogoViejoProductosService;

    private long totalDeRegistrosProcesados;

    private long porcientoAnterior;

    private long porciento;

    private long totalDeRegistrosProcesadosProductos;

    private long porcientoAnteriorProductos;

    private long porcientoProductos;

    SincronizadorVisibilidadDAO(ProductosDAO productosDAO, ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO, GruposDAO gruposDAO,
                                UbicacionesDAO ubicacionesDAO, ListasDeVentaDAO listasDeVentaDAO, EmpresasDAO empresasDAO){
        this.productosDAO = productosDAO;
        this.listaDeVentaVisibilidadDAO = listaDeVentaVisibilidadDAO;
        this.gruposDAO = gruposDAO;
        this.ubicacionesDAO = ubicacionesDAO;
        this.listasDeVentaDAO = listasDeVentaDAO;
        this.empresasDAO = empresasDAO;
    }

    public void sincronizarVisibilidadCpp(HashMap<String, List<String>> visibilidadProductoASincronizar, ListaDeVenta listaDeVenta, long totalRegistros) {
        try {
            porcientoAnterior = 0;
            Set<Producto> productos = new HashSet<>();
            Set<String> sproductos = new HashSet<>();
            String gln = listaDeVenta.getUbicacion().getCodigo();
            for (Map.Entry<String, List<String>> visibilidad : visibilidadProductoASincronizar.entrySet()) {
                String cpp = visibilidad.getKey();
                List<String> gruposOGlnEmpresas = visibilidad.getValue();
                Empresa empresa = listaDeVenta.getEmpresa();
                Optional<catalogo.reportes.core.catalogo.entity.Producto> optionalProducto = productosDAO.findByIdEmpresaAndCpp(empresa.getId(), cpp);
                if (optionalProducto.isPresent()) {

                    productos.add(optionalProducto.get());
                    sproductos.add(optionalProducto.get().getId());

                    ListaDeVentaVisibilidad listaDeVentaVisibilidad = new ListaDeVentaVisibilidad();

                    listaDeVentaVisibilidad.setListaDeVenta(listaDeVenta);
                    listaDeVentaVisibilidad.setSlistaDeVenta(listaDeVenta.getId());
                    listaDeVentaVisibilidad.setProducto(optionalProducto.get());
                    listaDeVentaVisibilidad.setSproducto(optionalProducto.get().getId());

                    if (gruposOGlnEmpresas.get(0).equals("TODOS_PUBLICOS")) {
                        listaDeVentaVisibilidad.setEsPrivado(false);
                        listaDeVentaVisibilidad.setEsPublico(true);
                        listaDeVentaVisibilidad.setFueSincronizada(true);
                    } else {
                        for (String grupoOglnEmpresa : gruposOGlnEmpresas) {
                            Optional<Ubicacion> ubicacionEmpresa = ubicacionesDAO.findByKey("codigo", grupoOglnEmpresa);
                            if (ubicacionEmpresa.isPresent()) {
                                Empresa empresaUbicacion = empresasDAO.findById(ubicacionEmpresa.get().getEmpresa().getId());
                                if(empresaUbicacion != null) {
                                    listaDeVentaVisibilidad.getEmpresasConVisibilidadParaSincronizacion().add(empresaUbicacion);
                                    listaDeVentaVisibilidad.getSempresasConVisibilidadParaSincronizacion().add(empresaUbicacion.getId());
                                }
                            } else {
                                Grupo grupo = gruposDAO.obtenerGrupoPorEmpresa(grupoOglnEmpresa, empresa);
                                if (grupo != null) {
                                    listaDeVentaVisibilidad.getGruposConVisibilidadParaSincronizacion().add(grupo);
                                    listaDeVentaVisibilidad.getSgruposConVisibilidadParaSincronizacion().add(grupo.getId());
                                } else {
                                    grupo = crearGrupo(empresa, grupoOglnEmpresa);
                                    listaDeVentaVisibilidad.getGruposConVisibilidadParaSincronizacion().add(grupo);
                                    listaDeVentaVisibilidad.getSgruposConVisibilidadParaSincronizacion().add(grupo.getId());
                                }
                            }
                        }
                        listaDeVentaVisibilidad.setEsPrivado(true);
                        listaDeVentaVisibilidad.setEsPublico(false);
                        listaDeVentaVisibilidad.setFueSincronizada(true);
                    }
                    listaDeVentaVisibilidadDAO.save(listaDeVentaVisibilidad);
                }
            }
            listaDeVenta.getProductosParaSincronizacion().addAll(productos);
            listaDeVenta.getSproductosParaSincronizacion().addAll(sproductos);
            listasDeVentaDAO.save(listaDeVenta);
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar los producto: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorVisibilidadDAO", e);
        }
    }

    public void enviarPorcientoDeActualizacion(String gln){
        sincronizadorService.enviarListaDeVentaQueSeEstaActualizando(gln);
    }

    public void resetearContador(){
        totalDeRegistrosProcesadosProductos = 0;
    }

    public void actualizarVisiblidadAntesDeSincronizar(ListaDeVenta listaDeVenta){
        try {
            listaDeVenta.getEmpresasParaSincronizacion().clear();
            listaDeVenta.getSempresasParaSincronizacion().clear();

            listaDeVenta.getGruposParaSincronizacion().clear();
            listaDeVenta.getSgruposParaSincronizacion().clear();

            listaDeVenta.getProductosParaSincronizacion().clear();
            listaDeVenta.getSproductosParaSincronizacion().clear();

            listaDeVenta.getListaDeVentaProductosVisibilidad().clear();
            listaDeVenta.getSlistaDeVentaProductosVisibilidad().clear();

            listasDeVentaDAO.save(listaDeVenta);
            List<ListaDeVentaVisibilidad> listaDeVentaVisibilidadProductos = listaDeVentaVisibilidadDAO.findAllByIdListaDeVenta(listaDeVenta.getId());
            for (ListaDeVentaVisibilidad listaDeVentaVisibilidad: listaDeVentaVisibilidadProductos) {
                listaDeVentaVisibilidad = listaDeVentaVisibilidadDAO.findById(listaDeVentaVisibilidad.getId());
                listaDeVentaVisibilidad.setFueSincronizada(false);

                listaDeVentaVisibilidad.getEmpresasConVisibilidadParaSincronizacion().clear();
                listaDeVentaVisibilidad.getSempresasConVisibilidadParaSincronizacion().clear();

                listaDeVentaVisibilidad.getGruposConVisibilidadParaSincronizacion().clear();
                listaDeVentaVisibilidad.getSgruposConVisibilidadParaSincronizacion().clear();

                listaDeVentaVisibilidadDAO.save(listaDeVentaVisibilidad);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al eliminar la visibilidad: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorVisibilidadDAO", e);
        }
    }

    public void actualizarVisiblidadDespuesDeSincronizar(ListaDeVenta listaDeVenta){
        try {
            listaDeVenta.setEmpresas(listaDeVenta.getEmpresasParaSincronizacion());
            listaDeVenta.setSempresas(listaDeVenta.getSempresasParaSincronizacion());

            listaDeVenta.setEmpresasParaSincronizacion(new HashSet<>());
            listaDeVenta.setSempresasParaSincronizacion(new HashSet<>());

            listaDeVenta.setGrupos(listaDeVenta.getGruposParaSincronizacion());
            listaDeVenta.setSgrupos(listaDeVenta.getSgruposParaSincronizacion());

            listaDeVenta.setGruposParaSincronizacion(new HashSet<>());
            listaDeVenta.setSgruposParaSincronizacion(new HashSet<>());

            listaDeVenta.setProductos(listaDeVenta.getProductosParaSincronizacion());
            listaDeVenta.setSproductos(listaDeVenta.getSproductosParaSincronizacion());

            listaDeVenta.setProductosParaSincronizacion(new HashSet<>());
            listaDeVenta.setSproductosParaSincronizacion(new HashSet<>());

            listasDeVentaDAO.save(listaDeVenta);
            List<ListaDeVentaVisibilidad> listaDeVentaVisibilidadProductos = listaDeVentaVisibilidadDAO.findAllByIdListaDeVenta(listaDeVenta.getId());
            for (ListaDeVentaVisibilidad listaDeVentaVisibilidad: listaDeVentaVisibilidadProductos) {
                listaDeVentaVisibilidad = listaDeVentaVisibilidadDAO.findById(listaDeVentaVisibilidad.getId());
                if(listaDeVentaVisibilidad.getFueSincronizada()) {
                    listaDeVentaVisibilidad.setEmpresasConVisibilidad(listaDeVentaVisibilidad.getEmpresasConVisibilidadParaSincronizacion());
                    listaDeVentaVisibilidad.setSempresasConVisibilidad(listaDeVentaVisibilidad.getSempresasConVisibilidadParaSincronizacion());

                    listaDeVentaVisibilidad.setEmpresasConVisibilidadParaSincronizacion(new HashSet<>());
                    listaDeVentaVisibilidad.setSempresasConVisibilidadParaSincronizacion(new HashSet<>());

                    listaDeVentaVisibilidad.setGruposConVisibilidad(listaDeVentaVisibilidad.getGruposConVisibilidadParaSincronizacion());
                    listaDeVentaVisibilidad.setSgruposConVisibilidad(listaDeVentaVisibilidad.getSgruposConVisibilidadParaSincronizacion());

                    listaDeVentaVisibilidad.setGruposConVisibilidadParaSincronizacion(new HashSet<>());
                    listaDeVentaVisibilidad.setSgruposConVisibilidadParaSincronizacion(new HashSet<>());
                    listaDeVentaVisibilidadDAO.save(listaDeVentaVisibilidad);
                } else {
                    listaDeVentaVisibilidadDAO.delete(listaDeVentaVisibilidad);
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al eliminar la visibilidad: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorVisibilidadDAO", e);
        }
    }

    public void actualizarGruposYEmpresasEnLaListaDeVenta(ListaDeVenta listaDeVenta, String gln) {
        List<common.rondanet.clasico.core.catalogo.catalogoModels.Grupo> grupos = catalogoViejoGrupoRepository.findAllByGln(gln);
        Set<Grupo> gruposConVisibilidad = new HashSet<>();
        Set<String> sgruposConVisibilidad = new HashSet<>();
        Set<Empresa> empresasConVisibilidad = new HashSet<>();
        Set<String> sempresasConVisibilidad = new HashSet<>();
        Empresa empresa = listaDeVenta.getEmpresa();
        for (common.rondanet.clasico.core.catalogo.catalogoModels.Grupo grupoCatalogoViejo : grupos) {
            Grupo grupo = gruposDAO.obtenerGrupoPorEmpresa(grupoCatalogoViejo.getId().getNombreGrupo(), empresa);
            if (grupo != null) {
                gruposConVisibilidad.add(grupo);
                sgruposConVisibilidad.add(grupo.getId());
            } else {
                Optional<Ubicacion> ubicacionEmpresa = ubicacionesDAO.findByKey("codigo", grupoCatalogoViejo.getId().getNombreGrupo());
                if (ubicacionEmpresa.isPresent()) {
                    empresasConVisibilidad.add(empresa);
                    sempresasConVisibilidad.add(empresa.getId());
                }
            }
        }
        listaDeVenta.setEmpresasParaSincronizacion(empresasConVisibilidad);
        listaDeVenta.setSempresasParaSincronizacion(sempresasConVisibilidad);
        listaDeVenta.setGruposParaSincronizacion(gruposConVisibilidad);
        listaDeVenta.setSgruposParaSincronizacion(sgruposConVisibilidad);
        listasDeVentaDAO.save(listaDeVenta);
    }

    public void actualizarVisiblidadProductosAntesDeSincronizar(String gln, Empresa empresa){
        try {
            int rows = 0;
            boolean todosLosProductos = false;
            while (!todosLosProductos) {
                int to = rows + 100;
                List<ProductoGtin> productos = catalogoViejoProductosService.findAllByGln(gln, rows + 1, to);
                if (productos.size() > 0) {
                    actualizarVisibilidadAntesDeSincronizar(productos, empresa);
                }
                rows = to;
                if (productos.size() < 100) {
                    todosLosProductos = true;
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al actualizarVisiblidadProductosAntesDeSincronizar: " + e.getMessage());
            this.emailHelper.sendErrorEmail("actualizarVisiblidadProductosAntesDeSincronizar", e);
        }
    }

    public void actualizarVisibilidadAntesDeSincronizar(List<ProductoGtin> productos, Empresa empresa){
        for (ProductoGtin productoGtin: productos) {
            Optional<Producto> producto = productosDAO.findByIdEmpresaAndCpp(empresa.getId(), productoGtin.getId().getCpp());
            if(producto.isPresent()){
                producto.get().getEmpresasConVisibilidad().clear();
                producto.get().getSempresasConVisibilidad().clear();
                producto.get().getGruposConVisibilidad().clear();
                producto.get().getSgruposConVisibilidad();
                productosDAO.save(producto.get());
            }
        }
    }

    public void sincronizarVisibilidadProductosCatalogo(HashMap<String, List<String>> visibilidadProductoASincronizar, Empresa empresa, GruposYEmpresas gruposYEmpresas, long totalRegistros) {
        try {
            porcientoAnteriorProductos = 0;
            for (Map.Entry<String, List<String>> visibilidad : visibilidadProductoASincronizar.entrySet()) {
                String cpp = visibilidad.getKey();
                List<String> gruposOGlnEmpresas = visibilidad.getValue();
                Optional<catalogo.reportes.core.catalogo.entity.Producto> optionalProducto = productosDAO.findByIdEmpresaAndCpp(empresa.getId(), cpp);
                if (optionalProducto.isPresent()) {
                    Producto producto = optionalProducto.get();
                   if (gruposOGlnEmpresas.get(0).equals("TODOS_PUBLICOS")) {
                       agregarGruposYEmpresasVisibilidadPublica(producto, gruposYEmpresas);
                       enviarPorcientoDeActualizacionProductos(totalRegistros);
                    } else {
                        for (String grupoOglnEmpresa : gruposOGlnEmpresas) {
                            Optional<Ubicacion> ubicacionEmpresa = ubicacionesDAO.findByKey("codigo", grupoOglnEmpresa);
                            if (ubicacionEmpresa.isPresent()) {
                                Empresa empresaUbicacion = empresasDAO.findById(ubicacionEmpresa.get().getEmpresa().getId());
                                if(empresaUbicacion != null) {
                                    producto.getEmpresasConVisibilidad().add(empresaUbicacion);
                                    producto.getSempresasConVisibilidad().add(empresaUbicacion.getId());
                                }
                            } else {
                                Grupo grupo = gruposDAO.obtenerGrupoPorEmpresa(grupoOglnEmpresa, empresa);
                                if (grupo != null) {
                                    producto.getGruposConVisibilidad().add(grupo);
                                    producto.getSgruposConVisibilidad().add(grupo.getId());
                                } else {
                                    grupo = crearGrupo(empresa, grupoOglnEmpresa);
                                    producto.getGruposConVisibilidad().add(grupo);
                                    producto.getSgruposConVisibilidad().add(grupo.getId());
                                }
                            }
                            productosDAO.save(producto);
                            enviarPorcientoDeActualizacionProductos(totalRegistros);
                        }
                    }
                } else {
                    logger.log(Level.ERROR, "Revisar que no existe un producto en base de datos, empresa: " + empresa.getRut() + " cpp: " +  cpp);
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar los producto: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorVisibilidadDAO", e);

        }
    }

    public void enviarPorcientoDeActualizacionProductos(long totalRegistros) {
        totalDeRegistrosProcesadosProductos ++;
        porcientoProductos = (totalDeRegistrosProcesadosProductos * 100) / totalRegistros;
        if(porcientoProductos != porcientoAnteriorProductos && porcientoProductos <= 100) {
            porcientoAnteriorProductos = porcientoProductos;
        }
    }

    public Producto agregarGruposYEmpresasVisibilidadPublica(Producto producto, GruposYEmpresas gruposYEmpresas){
        producto.setEmpresasConVisibilidad(gruposYEmpresas.getEmpresasConVisibilidad());
        producto.setSempresasConVisibilidad(gruposYEmpresas.getSempresasConVisibilidad());
        producto.setGruposConVisibilidad(gruposYEmpresas.getGruposConVisibilidad());
        producto.setSgruposConVisibilidad(gruposYEmpresas.getSgruposConVisibilidad());
        return productosDAO.save(producto);
    }

    public GruposYEmpresas obtenerGruposYEmpresas(Empresa empresa, List<String> gruposOGlnEmpresas){
        GruposYEmpresas gruposYEmpresas = new GruposYEmpresas();
        for (String grupoOglnEmpresa : gruposOGlnEmpresas) {
            Optional<Ubicacion> ubicacionEmpresa = ubicacionesDAO.findByKey("codigo", grupoOglnEmpresa);
            if (ubicacionEmpresa.isPresent()) {
                Empresa empresaUbicacion = empresasDAO.findById(ubicacionEmpresa.get().getEmpresa().getId());
                if(empresaUbicacion != null) {
                    gruposYEmpresas.getEmpresasConVisibilidad().add(empresaUbicacion);
                    gruposYEmpresas.getSempresasConVisibilidad().add(empresaUbicacion.getId());
                }
            } else {
                Grupo grupo = gruposDAO.obtenerGrupoPorEmpresa(grupoOglnEmpresa, empresa);
                if (grupo != null) {
                    gruposYEmpresas.getGruposConVisibilidad().add(grupo);
                    gruposYEmpresas.getSgruposConVisibilidad().add(grupo.getId());
                } else {
                    grupo = crearGrupo(empresa, grupoOglnEmpresa);
                    gruposYEmpresas.getGruposConVisibilidad().add(grupo);
                    gruposYEmpresas.getSgruposConVisibilidad().add(grupo.getId());
                }
            }
        }
        return gruposYEmpresas;
    }

    public Grupo crearGrupo(Empresa empresa, String nombreGrupo){
        Grupo grupo = new catalogo.reportes.core.catalogo.entity.Grupo();
        grupo.setNombre(nombreGrupo);
        grupo.setEmpresa(empresa);
        grupo.setSempresa(empresa.getId());
        grupo.setDescripcion("Rondanet");
        grupo.setFueActualizado(true);
        grupo = gruposDAO.save(grupo);
        return grupo;
    }
}
