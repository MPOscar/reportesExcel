package catalogo.reportes.core.catalogo.services.implementations;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.resources.GruposYEmpresas;
import catalogo.reportes.core.catalogoViejo.catalogoServices.interfaces.ICatalogoViejoGruposService;
import catalogo.reportes.core.services.interfaces.ISincronizadorService;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Ubicacion;
import common.rondanet.clasico.core.catalogo.catalogoModels.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import catalogo.reportes.core.catalogo.db.*;
import catalogo.reportes.core.catalogo.entity.ListaDeVenta;
import catalogo.reportes.core.catalogo.services.interfaces.ICatalogoSincronizadorService;

import java.util.*;


@Service
public class CatalogoSincronizadorService implements ICatalogoSincronizadorService {

	Logger logger = LogManager.getLogger(CatalogoSincronizadorService.class);

	@Autowired
	SincronizadorEmpresaDAO sincronizadorEmpresaDAO;

	@Autowired
	SincronizadorUbicacionDAO sincronizadorUbicacionDAO;

	@Autowired
	SincronizadorProductoDAO sincronizadorProductoDAO;

	@Autowired
	SincronizadorGrupoDAO sincronizadorGrupoDAO;

	@Autowired
	SincronizadorVisibilidadDAO sincronizadorVisibilidadDAO;

	@Autowired
	SincronizadorListaDePreciosDAO sincronizadorListaDePreciosDAO;

	@Autowired
	EmpresasDAO empresasDAO;

	@Autowired
	GruposDAO gruposDAO;

	@Autowired
	ProductosDAO productosDAO;

	@Autowired
	UbicacionesDAO ubicacionesDAO;

	@Autowired
	ListasDeVentaDAO listasDeVentaDAO;

	@Autowired
	ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO;

	@Autowired
	VerificarVisibilidadDAO verificarVisibilidadDAO;

	@Autowired
	ICatalogoViejoGruposService catalogoViejoGruposService;

	@Autowired
	ISincronizadorService sincronizadorService;

	ReportesConfiguration configuration;

	public CatalogoSincronizadorService(ReportesConfiguration configuration) {
		this.configuration = configuration;
	}

	public void sincronizarEmpresas(List<Empresa> empresasASincronizar) {
		for (Empresa empresaASincronizar : empresasASincronizar) {
			sincronizadorEmpresaDAO.sincronizarEmpresa(empresaASincronizar);
		}
	}

	public void sincronizarUbicaciones(List<Ubicacion> ubicacionesASincronizar){
		for (Ubicacion ubicacionASincronizar : ubicacionesASincronizar) {
			sincronizadorUbicacionDAO.sincronizarUbicacion(ubicacionASincronizar);
		}
	}

	public void sincronizarGrupos(List<Grupo> gruposASincronizar, catalogo.reportes.core.catalogo.entity.Empresa empresa) {
		try {
			HashMap<String, List<String>> gruposASincronizarAgrupados = new HashMap<>();
			for (Grupo grupo : gruposASincronizar) {
				if (!grupo.getId().getGln2().equals(grupo.getId().getNombreGrupo())) {
					if (gruposASincronizarAgrupados.containsKey(grupo.getId().getNombreGrupo())) {
						gruposASincronizarAgrupados.get(grupo.getId().getNombreGrupo()).add(grupo.getId().getGln2());
					} else {
						List<String> gruposOGlnEmpresas = new ArrayList<>();
						gruposOGlnEmpresas.add(grupo.getId().getGln2());
						gruposASincronizarAgrupados.put(grupo.getId().getNombreGrupo(), gruposOGlnEmpresas);
					}
				}
			}
			sincronizadorGrupoDAO.sincronizarGrupo(gruposASincronizarAgrupados, empresa);
		} catch (Exception exception) {
			logger.log(Level.INFO, "Ocurrió un error al sincronizar los grupos. Error: " + exception.getMessage());
		}
	}

	@Override
	public void enviarPorcientoDeActualizacionGrupos(String mensaje){
		sincronizadorService.enviarNotificacionAlActualizarGrupos(mensaje);
	}

	@Override
	public void actualizarGruposAntesDeSincronizar(catalogo.reportes.core.catalogo.entity.Empresa empresa, String gln){
		List<String> grupos = catalogoViejoGruposService.findAllByGlnGroupByNombre(gln);
		for (String nombreGrupo: grupos) {
			catalogo.reportes.core.catalogo.entity.Grupo grupoEmpresa = gruposDAO.obtenerGrupoPorEmpresa(nombreGrupo, empresa);
			if(grupoEmpresa != null) {
				grupoEmpresa.getSempresasParaSincronizacion().clear();
				grupoEmpresa.getEmpresasParaSincronizacion().clear();
				grupoEmpresa.setFueActualizado(false);
				gruposDAO.save(grupoEmpresa);
			}
		}
	}

	@Override
	public void actualizarGruposDespuesDeSincronizar(catalogo.reportes.core.catalogo.entity.Empresa empresa, String gln){
		List<String> grupos = catalogoViejoGruposService.findAllByGlnGroupByNombre(gln);
		for (String nombreGrupo: grupos) {
			catalogo.reportes.core.catalogo.entity.Grupo grupoEmpresa = gruposDAO.obtenerGrupoPorEmpresa(nombreGrupo, empresa);
			if(grupoEmpresa != null) {
				grupoEmpresa.setSempresas(grupoEmpresa.getSempresasParaSincronizacion());
				grupoEmpresa.setEmpresas(grupoEmpresa.getEmpresasParaSincronizacion());
				grupoEmpresa.setSempresasParaSincronizacion(new HashSet<>());
				grupoEmpresa.setEmpresasParaSincronizacion(new HashSet<>());
				gruposDAO.save(grupoEmpresa);
			}
		}
	}

	@Override
	public void eliminarGruposNoActualizados(catalogo.reportes.core.catalogo.entity.Empresa empresa){
		List<catalogo.reportes.core.catalogo.entity.Grupo> gruposNoActualizados = gruposDAO.obtenerTodosLosGruposNoActualizados(empresa);
		for (catalogo.reportes.core.catalogo.entity.Grupo grupo: gruposNoActualizados) {
			try {
				eliminarGrupo(grupo, empresa);
			} catch (Exception exception) {
				logger.log(Level.INFO, "Ocurrió un error al sincronizar los grupos. Error: " + exception.getMessage());
			}
		}
	}

	public void eliminarGrupo(catalogo.reportes.core.catalogo.entity.Grupo grupo, catalogo.reportes.core.catalogo.entity.Empresa empresa) {
		catalogo.reportes.core.catalogo.entity.Grupo group = this.gruposDAO.findById(grupo.getId());
		if (group.getEmpresa().getId().equals(empresa.getId()) && !group.getNombre().equals("OCULTO")) {

			empresa.getGrupos().remove(group);
			empresa.getSgrupos().remove(group.getId());
			this.empresasDAO.save(empresa);

			Set<catalogo.reportes.core.catalogo.entity.Empresa> empresas = group.getEmpresas();
			for (catalogo.reportes.core.catalogo.entity.Empresa empresaAEliminarGrupos: empresas) {
				empresaAEliminarGrupos.getEmpresaGrupos().remove(group);
				empresaAEliminarGrupos.getSempresaGrupos().remove(group.getId());
				empresasDAO.save(empresa);
			}

			empresas = null;

			List<ListaDeVenta> listaDeVentas = listasDeVentaDAO.findAllByGrupo(grupo.getId());
			for (ListaDeVenta listaDeVenta: listaDeVentas) {
				listaDeVenta.getGrupos().remove(grupo);
				listaDeVenta.getSgrupos().remove(grupo.getId());
				listasDeVentaDAO.save(listaDeVenta);
			}

			listaDeVentas = null;

			catalogo.reportes.core.catalogo.entity.Grupo grupoOculto = null;
			List<catalogo.reportes.core.catalogo.entity.ListaDeVentaVisibilidad> listaDeVentaVisibilidades = listaDeVentaVisibilidadDAO.findAllByGrupo(grupo.getId());
			for (catalogo.reportes.core.catalogo.entity.ListaDeVentaVisibilidad listaDeVentaVisibilidad: listaDeVentaVisibilidades) {
				listaDeVentaVisibilidad.getGruposConVisibilidad().remove(grupo);
				listaDeVentaVisibilidad.getSgruposConVisibilidad().remove(grupo.getId());
				ListaDeVenta listaDeVenta = listasDeVentaDAO.findById(listaDeVentaVisibilidad.getSlistaDeVenta());
				if(listaDeVenta != null) {
					if (listaDeVentaVisibilidad.getEmpresasConVisibilidad().size() == 0 && listaDeVentaVisibilidad.getGruposConVisibilidad().size() == 0) {
						if (grupoOculto == null) {
							grupoOculto = crearGrupoParaProductosOcultos(empresa);
						}
						listaDeVentaVisibilidad.getGruposConVisibilidad().add(grupoOculto);
						listaDeVentaVisibilidad.getSgruposConVisibilidad().add(grupoOculto.getId());
						if (listaDeVenta.getGrupos() == null) {
							Set<catalogo.reportes.core.catalogo.entity.Grupo> grupos = new HashSet<>();
							listaDeVenta.setGrupos(grupos);
						}
						if (listaDeVenta.getSgrupos() == null) {
							Set<String> sgrupos = new HashSet<>();
							listaDeVenta.setSgrupos(sgrupos);
						}
						listaDeVenta.getGrupos().add(grupoOculto);
						listaDeVenta.getSgrupos().add(grupoOculto.getId());
						listasDeVentaDAO.save(listaDeVenta);
					}
					listaDeVentaVisibilidadDAO.save(listaDeVentaVisibilidad);
				} else {
					listaDeVentaVisibilidadDAO.delete(listaDeVentaVisibilidad);
				}
			}

			listaDeVentaVisibilidades = null;

			List<catalogo.reportes.core.catalogo.entity.Producto> productos = productosDAO.findAllByGrupo(grupo.getId());
			for (catalogo.reportes.core.catalogo.entity.Producto producto: productos) {
				producto.getGruposConVisibilidad().remove(grupo);
				producto.getSgruposConVisibilidad().remove(grupo.getId());
				if (producto.getEmpresasConVisibilidad().size() == 0 && producto.getGruposConVisibilidad().size() == 0) {
					if (grupoOculto == null) {
						grupoOculto = crearGrupoParaProductosOcultos(empresa);
					}
					producto.getGruposConVisibilidad().add(grupoOculto);
					producto.getSgruposConVisibilidad().add(grupoOculto.getId());
				}
				productosDAO.save(producto);
			}

			productos = null;

			group.getEmpresas().clear();
			group.getSempresas().clear();
			UUID uuid = UUID.randomUUID();
			group.setNombre(group.getNombre() + "_eliminado_" + uuid);
			group.eliminar();
			group.setDescripcion("Eliminado de Rondanet");
			empresa.getGrupos().remove(group);
			empresa.getSgrupos().remove(group.getId());
			this.empresasDAO.save(empresa);
			this.gruposDAO.update(group);
		}
	}

	public catalogo.reportes.core.catalogo.entity.Grupo crearGrupoParaProductosOcultos(catalogo.reportes.core.catalogo.entity.Empresa empresa){
		catalogo.reportes.core.catalogo.entity.Grupo grupo = this.gruposDAO.obtenerGrupo("OCULTO", empresa.getId());
		if (grupo == null) {
			grupo = new catalogo.reportes.core.catalogo.entity.Grupo("OCULTO", "OCULTO");
			grupo.setDescripcion("Grupo para ocultar productos.");
			grupo.setEmpresa(empresa);
			grupo.setSempresa(empresa.getId());
			grupo = this.gruposDAO.save(grupo);
		}
		return grupo;
	}

	public void sincronizarProductos(List<ProductoGtin> productosASincronizar) {
		sincronizadorProductoDAO.sincronizarProducto(productosASincronizar);
	}

	public void sincronizarProductosEliminadosDelCatalogoViejo() {
		sincronizadorProductoDAO.sincronizarProductosEliminadosDelCatalogoViejo();
	}

	public void sincronizarListaPrecio(List<ListaPrecio> listaPrecios){
		sincronizadorListaDePreciosDAO.sincronizarListaDePrecio(listaPrecios);
	}

	@Override
	public ListaDeVenta	obtenerListaDeVenta(catalogo.reportes.core.catalogo.entity.Ubicacion ubicacion, String gln, catalogo.reportes.core.catalogo.entity.Empresa empresa){
		Optional<ListaDeVenta> optionalListaDeVenta = listasDeVentaDAO.findFirstBySubicacionAndNombre(ubicacion.getId(), ubicacion.getDescripcion());
		ListaDeVenta listaDeVenta = new ListaDeVenta();
		if (!optionalListaDeVenta.isPresent()) {
			listaDeVenta.setNombre(ubicacion.getDescripcion());
			listaDeVenta.setDescripcion(ubicacion.getDescripcion());
			listaDeVenta.setEmpresa(empresa);
			listaDeVenta.setSempresa(empresa.getId());
			listaDeVenta.setUbicacion(ubicacion);
			listaDeVenta.setSubicacion(ubicacion.getId());
			listaDeVenta.setGlnUbicacion(gln);
			listaDeVenta = listasDeVentaDAO.save(listaDeVenta);
		} else {
			listaDeVenta = optionalListaDeVenta.get();
		}
		return listaDeVenta;
	}

	@Override
	public void sincronizarVisibilidad(List<VisibilidadProducto> visibilidadProductosASincronizar, ListaDeVenta listaDeVenta, String gln, long totalRegistros) {
		try {
			HashMap<String, List<String>> visibilidadAgrupadaPorCpp = obtenerVisibilidadAgrupada(visibilidadProductosASincronizar);
			sincronizadorVisibilidadDAO.sincronizarVisibilidadCpp(visibilidadAgrupadaPorCpp, listaDeVenta, totalRegistros);
		} catch (Exception exception) {
			logger.log(Level.INFO, "Ocurrió un error al sincronizar visibilidad. Error: " + exception.getMessage());
		}
	}

	@Override
	public void sincronizarVisibilidadProductos(List<VisibilidadProducto> visibilidadProductosASincronizar, catalogo.reportes.core.catalogo.entity.Empresa empresa, GruposYEmpresas gruposYEmpresas, long totalRegistros) {
		try {
			HashMap<String, List<String>> visibilidadAgrupadaPorCpp = obtenerVisibilidadAgrupada(visibilidadProductosASincronizar);
			sincronizadorVisibilidadDAO.sincronizarVisibilidadProductosCatalogo(visibilidadAgrupadaPorCpp, empresa, gruposYEmpresas, totalRegistros);
		} catch (Exception exception) {
			logger.log(Level.INFO, "Ocurrió un error al sincronizar visibilidad. Error: " + exception.getMessage());
		}
	}

	public HashMap<String, List<String>> obtenerVisibilidadAgrupada(List<VisibilidadProducto> visibilidadProductosASincronizar){
		HashMap<String, List<String>> visibilidadAgrupadaPorCpp = new HashMap<>();
		for (VisibilidadProducto visibilidadProducto : visibilidadProductosASincronizar) {
			if (visibilidadAgrupadaPorCpp.containsKey(visibilidadProducto.getId().getCpp())) {
				visibilidadAgrupadaPorCpp.get(visibilidadProducto.getId().getCpp()).add(visibilidadProducto.getId().getGrupoOGlnEmpresa());
			} else {
				List<String> gruposOGlnEmpresas = new ArrayList<>();
				gruposOGlnEmpresas.add(visibilidadProducto.getId().getGrupoOGlnEmpresa());
				visibilidadAgrupadaPorCpp.put(visibilidadProducto.getId().getCpp(), gruposOGlnEmpresas);
			}
		}
		return visibilidadAgrupadaPorCpp;
	}

	@Override
	public void verificarVisibilidad() {
		verificarVisibilidadDAO.verificarVisibilidad();
	}
}
