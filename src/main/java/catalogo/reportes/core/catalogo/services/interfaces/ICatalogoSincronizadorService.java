package catalogo.reportes.core.catalogo.services.interfaces;

import catalogo.reportes.core.catalogo.entity.ListaDeVenta;
import catalogo.reportes.core.catalogo.resources.GruposYEmpresas;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Ubicacion;
import common.rondanet.clasico.core.catalogo.catalogoModels.*;

import java.util.List;

public interface ICatalogoSincronizadorService {

    public void sincronizarEmpresas(List<Empresa> empresasASincronizar);

    public void sincronizarUbicaciones(List<Ubicacion> ubicacionesASincronizar);

    void enviarPorcientoDeActualizacionGrupos(String mensaje);

    void actualizarGruposAntesDeSincronizar(catalogo.reportes.core.catalogo.entity.Empresa empresa, String gln);

    void actualizarGruposDespuesDeSincronizar(catalogo.reportes.core.catalogo.entity.Empresa empresa, String gln);

    void eliminarGruposNoActualizados(catalogo.reportes.core.catalogo.entity.Empresa empresa);

    public void sincronizarProductos(List<ProductoGtin> productosASincronizar);

    public void sincronizarGrupos(List<Grupo> gruposASincronizar, catalogo.reportes.core.catalogo.entity.Empresa empresa);

    ListaDeVenta obtenerListaDeVenta(catalogo.reportes.core.catalogo.entity.Ubicacion ubicacion, String gln, catalogo.reportes.core.catalogo.entity.Empresa empresa);

    void sincronizarProductosEliminadosDelCatalogoViejo();

    public void sincronizarListaPrecio(List<ListaPrecio> listaPrecios);

    void sincronizarVisibilidad(List<VisibilidadProducto> visibilidadProductosASincronizar, ListaDeVenta listaDeVenta, String gln, long totalRegistros);

    void sincronizarVisibilidadProductos(List<VisibilidadProducto> visibilidadProductosASincronizar, catalogo.reportes.core.catalogo.entity.Empresa empresa, GruposYEmpresas gruposYEmpresas, long totalRegistros);

    void verificarVisibilidad();
}
