package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.utils.IEmailHelper;
import common.rondanet.clasico.core.catalogo.catalogoModels.ListaPrecio;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import catalogo.reportes.core.catalogo.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SincronizadorListaDePreciosDAO {

    Logger logger = LogManager.getLogger(SincronizadorListaDePreciosDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    ProductosDAO productosDAO;

    @Autowired
    ListaDePrecioDAO listaDePrecioDAO;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    @Autowired
    ListasDeVentaDAO listasDeVentaDAO;

    @Autowired
    ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO;

    ReportesConfiguration configuration;

    public SincronizadorListaDePreciosDAO(ReportesConfiguration configuration, UbicacionesDAO ubicacionesDAO, ProductosDAO productosDAO, ListaDePrecioDAO listaDePrecioDAO) {
        this.configuration = configuration;
        this.ubicacionesDAO = ubicacionesDAO;
        this.productosDAO = productosDAO;
        this.listaDePrecioDAO = listaDePrecioDAO;
    }

    public void sincronizarListaDePrecio(List<ListaPrecio> listaPrecios) {
        try {
            if(listaPrecios.size() > 0) {
                String gln = listaPrecios.get(0).getId().getGln();
                String target = listaPrecios.get(0).getId().getGrupoClientes();
                String moneda = listaPrecios.get(0).getMoneda();
                Optional<Ubicacion> ubicacion = ubicacionesDAO.findByKey("codigo", gln);
                if (ubicacion.isPresent()) {
                    Empresa empresa = ubicacion.get().getEmpresa();
                    if (this.configuration.empresasASincronizar(empresa.getRut())) {
                        List<ListaDePrecio> listaDePrecios = listaDePrecioDAO.findAllByGlnAndTargetAndMoneda(gln, target, moneda);
                        listaDePrecioDAO.deleteAll(listaDePrecios);

                        listaDePrecios = new ArrayList<>();
                        for (ListaPrecio listaPrecio: listaPrecios) {
                        ListaDePrecio listaDePrecio = new ListaDePrecio();
                        listaDePrecio.setProductoCpp(listaPrecio.getId().getCpp());
                        listaDePrecio.setGlnListaDeVenta(listaPrecio.getId().getGln());
                        listaDePrecio.setFechaVigencia(new DateTime(listaPrecio.getId().getFechaVigencia()));
                        listaDePrecio.setFechaVigenciaMilisegundos(String.valueOf(new DateTime(listaPrecio.getId().getFechaVigencia()).getMillis()));
                        listaDePrecio.setPrecio(listaPrecio.getPrecioBase());
                        listaDePrecio.setPrecio(listaPrecio.getPrecioBase());
                        listaDePrecio.setPrecioBase(listaPrecio.getPrecioBase());
                        listaDePrecio.setGrupoClientes(listaPrecio.getId().getGrupoClientes());
                        listaDePrecio.setTipoIva(listaPrecio.getTipoIva());
                        listaDePrecio.setMoneda(listaPrecio.getMoneda());
                        listaDePrecio.setPrecioConDescuentos(listaPrecio.getPrecioConDescuentos());
                        listaDePrecio.setPrecioConDescuentosEImpuestos(listaPrecio.getPrecioConDescuentosEImpuestos());
                        listaDePrecio.setPrecioSugerido(listaPrecio.getPrecioSugerido());
                        listaDePrecio.setSId(listaDePrecio.getId());
                        listaDePrecios.add(listaDePrecio);
                        }
                        listaDePrecios = listaDePrecioDAO.saveAll(listaDePrecios);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Ocurrió un error al sincronizar listas de precios. Error: " + e.getMessage());
            this.emailHelper.sendErrorEmail("SincronizadorListaDePreciosDAO", e);
        }
    }
}
