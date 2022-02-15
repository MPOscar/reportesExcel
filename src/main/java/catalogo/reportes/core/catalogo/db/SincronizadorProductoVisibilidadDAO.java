package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.entity.*;
import catalogo.reportes.core.catalogoViejo.catalogoServices.interfaces.ICatalogoViejoProductosService;
import catalogo.reportes.core.utils.IEmailHelper;
import common.rondanet.clasico.core.catalogo.catalogoModels.ProductoGtin;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SincronizadorProductoVisibilidadDAO {

    Logger logger = LogManager.getLogger(SincronizadorProductoVisibilidadDAO.class);

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
    ICatalogoViejoProductosService catalogoViejoProductosService;

    ReportesConfiguration configuration;

    private static boolean sincronizandoVisibilidad = false;

    public SincronizadorProductoVisibilidadDAO(ReportesConfiguration configuration, UbicacionesDAO ubicacionesDAO, ListasDeVentaDAO listasDeVentaDAO,
                                               ProductosDAO productosDAO, EmpaquesDAO empaquesDAO, PresentacionesDAO presentacionesDAO, ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO,
                                               CategoriasDAO categoriasDAO, ProductosAccionesDAO productosAccionesDAO) {
        this.configuration = configuration;
        this.ubicacionesDAO = ubicacionesDAO;
        this.listasDeVentaDAO = listasDeVentaDAO;
        this.productosDAO = productosDAO;
        this.empaquesDAO = empaquesDAO;
        this.presentacionesDAO = presentacionesDAO;
        this.listaDeVentaVisibilidadDAO = listaDeVentaVisibilidadDAO;
        this.categoriasDAO = categoriasDAO;
        this.productosAccionesDAO = productosAccionesDAO;
    }

    public void actualizarAccionesProductosDespuesDeSincronizar(String gln, Empresa empresa){
        try {
            int rows = 0;
            boolean todosLosProductos = false;
            while (!todosLosProductos) {
                int to = rows + 25;
                List<ProductoGtin> productos = catalogoViejoProductosService.findAllByGln(gln, rows + 1, to);
                if (productos.size() > 0) {
                    actualizarAccionesDespuesDeSincronizar(productos, empresa);
                }
                rows = to;
                if (productos.size() < 25) {
                    todosLosProductos = true;
                }
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Ocurrió un error al actualizarVisiblidadProductosAntesDeSincronizar: " + e.getMessage());
        }
    }

    public void actualizarAccionesDespuesDeSincronizar(List<ProductoGtin> productos, Empresa empresa){
        for (ProductoGtin productoGtin: productos) {
            try {
                Optional<Producto> producto = productosDAO.findByIdEmpresaAndCpp(empresa.getId(), productoGtin.getId().getCpp());
                if (producto.isPresent()) {
                    productosAccionesDAO.setearAccionesAntesDeActualizarLaVisivilidad(producto.get());
                    if (producto.get().getCambiosEnAtributosDelProducto() != null && producto.get().getCambiosEnAtributosDelProducto()) {
                        productosAccionesDAO.actualizarAccionesProductoPrivado(empresa, producto.get());
                    } else {
                        productosAccionesDAO.actualizarAccionesProductoVisibilidad(empresa, producto.get());
                    }
                    productosAccionesDAO.eliminarAccionesNoActualizadas(producto.get());
                    producto.get().setCambiosEnAtributosDelProducto(false);
                    productosDAO.save(producto.get());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
