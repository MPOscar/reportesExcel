package catalogo.reportes.core.catalogo.db;

import java.util.*;


import catalogo.reportes.core.catalogo.pedidosQuery.ListaDeVentaQuerys;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import catalogo.reportes.core.catalogo.entity.*;
import catalogo.reportes.core.catalogo.repository.*;
import org.joda.time.DateTime;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Component
public class ProductosDAO{

	Logger logger = LogManager.getLogger(ProductosDAO.class);

	@Autowired
	IProductoRepository productoRepository;

	@Autowired
	IEmpresaRepository empresaRepository;

	@Autowired
	EmpresasDAO empresasDAO;

	@Autowired
	IGrupoRepository grupoRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	ListasDeVentaDAO listasDeVentaDAO;

	@Autowired
	ListaDeVentaVisibilidadDAO listaDeVentaVisibilidadDAO;

	private final MongoOperations mongoOperations;

	public ProductosDAO(@Qualifier("mongoTemplateCatalogo") MongoOperations mongoOperations, IProductoRepository productoRepository, IEmpresaRepository empresaRepository,
						IUserRepository userRepository, EmpresasDAO empresasDAO, IGrupoRepository grupoRepository) {
		this.mongoOperations = mongoOperations;
		this.productoRepository = productoRepository;
		this.empresaRepository = empresaRepository;
		this.userRepository = userRepository;
		this.empresasDAO = empresasDAO;
		this.grupoRepository = grupoRepository;
	}

	/**
	 * Devuelve un {@link Producto} donde {@link String} id del {@link Producto} sea igual al {@link String} id pasado por par??metro
	 * @param id {@link String}
	 * @return {@link Producto}
	 */
	public Producto findById(String id) {
		Optional<Producto> producto = productoRepository.findById(id);
		logger.log(Level.INFO, "El m??todo findById() de la clase ProductosDAO fue ejecutado.");
		return producto.isPresent() ? producto.get() : null;
	}

	/**
	 * Devuelve un {@link Optional}<{@link Producto}> donde el {@link String} cpp y el {@link String} sempresa de {@link Producto}
	 * sea igual al {@link String} cpp y el {@link String} empresaId pasado por par??metro.
	 * @param empresaId {@link String}
	 * @param cpp {@link String}
	 * @return {@link Optional}<{@link Producto}>
	 */
	public Optional<Producto> findByIdEmpresaAndCpp(String empresaId, String cpp) {
		logger.log(Level.INFO, "El metodo findByIdEmpresaAndCpp() de la clase ProductosDAO fue ejecutado.");
		Query query = new Query();
		query.addCriteria(Criteria.where("sempresa").is(empresaId).andOperator(Criteria.where("cpp").is(cpp)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		if (productos.size() > 0)
			return Optional.of(productos.get(0));
		return Optional.ofNullable(null);
	}

	public Producto save(Producto producto) {
		producto.setFechaEdicion();
		if(producto.getFechaCreacion() == null){
			producto.setFechaCreacion();
		}
		if(producto.getSId() == null) {
			producto = productoRepository.save(producto);
			producto.setSId(producto.getId());
		}
		logger.log(Level.INFO, "El m??todo save() de la clase ProductoDAO fue ejecutado.");
		return productoRepository.save(producto);
	}

	/**
	 * Actualiza un {@link Producto} pasado por par??metro y actualiza el {@link DateTime} del atributo fecha de edici??n
	 * @param p {@link Producto}
	 * @return {@link Producto}
	 */
	public Producto update(Producto p) {
		p.setFechaEdicion();
		logger.log(Level.INFO, "Se ejecut?? el m??todo update() de la clase ProductosDAO");
		return productoRepository.save(p);
	}

	public List<Producto> getAll() {
		List<Producto> listaProductos = productoRepository.findAll();
		return listaProductos;
	}

	public List<Producto> findByKey(String key, String value) {
		Query query = new Query();
		List<Producto> productos = new ArrayList<>();
		try {
			query.addCriteria(Criteria.where("eliminado").is(false).orOperator(Criteria.where(key).is(value), Criteria.where("key").is(Long.parseLong(value))));
			productos = mongoOperations.find(query, Producto.class);
		} catch (NumberFormatException e) {
			query.addCriteria(Criteria.where("eliminado").is(false).orOperator(Criteria.where(key).is(value)));
			productos = mongoOperations.find(query, Producto.class);
		}
		return productos;
	}

	public Producto getProductByBussisnesAndCpp(String empresaId, String cpp) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sempresa").is(empresaId).andOperator(Criteria.where("cpp").is(cpp), Criteria.where("eliminado").is(false)));
		Producto producto = mongoOperations.findOne(query, Producto.class);
		return producto;
	}

	public List<Producto> findAllByEmpresa(String empresaId) {
		Aggregation productosAggregation = Aggregation.newAggregation(match(Criteria.where("sempresa").is(empresaId).andOperator(Criteria.where("eliminado").is(false)).orOperator(Criteria.where("tipo").is(null), Criteria.where("tipo").is("retail"))), group("sid"));
		List<Producto> productos = mongoOperations.aggregate(productosAggregation, "Producto", Producto.class).getMappedResults();
		return productos;
	}

	public List<Producto> findAllByGtin(String gtin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("gtin").is(gtin).andOperator(Criteria.where("eliminado").is(false)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		return productos;
	}

	/**
	 * Busca un ??nico {@link Producto} dado un c??digo <code>GTIN</code>.
	 * @param gtin C??digo GTIN por el cual se busca el {@link Producto} .
	 * @return {@link Optional}<{@link Producto}>
	 */
	public Optional<Producto> buscarProductoPrincipalPorGtinEnTodasLasEmpresas(String gtin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("eliminado").is(false).andOperator(Criteria.where("gtin").is(gtin), Criteria.where("esPrincipal").is(true)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		return Optional.ofNullable(productos.isEmpty() ? null : productos.get(0));
	}

	public boolean esPrincipal(String gtin) {
		Query query = new Query();
		query.addCriteria(Criteria.where("eliminado").is(false).andOperator(Criteria.where("gtin").is(gtin), Criteria.where("esPrincipal").is(true)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		return productos.size() == 0;
	}

	public List<Producto> obtenerTodosLosProductosActualizados(DateTime fechaEdicion, Empresa empresa) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sempresa").is(empresa.getId()).andOperator(Criteria.where("fechaEdicion").gte(fechaEdicion)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		logger.log(Level.INFO, "El m??todo findByKey() de la clase ListasDeVentaDAO fue ejecutado.");
		return productos;
	}

	public List<Producto> findAllByGrupo(String idGrupo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sgruposConVisibilidad").in(idGrupo).andOperator(Criteria.where("eliminado").is(false)));
		List<Producto> productos = mongoOperations.find(query, Producto.class);
		logger.log(Level.INFO, "El m??todo findByKey() de la clase ListasDeVentaDAO fue ejecutado.");
		return productos;
	}

	public long getTotalDeProductosVisiblesEnListaDeVenta(Empresa empresa, ListaDeVenta listaDeVenta) {
		Query productosQuery = getVisibleByBussinesOnSaleListQuery(empresa, listaDeVenta);
		long total = mongoOperations.count(productosQuery, Producto.class);
		return total;
	}

	public Query getVisibleByBussinesOnSaleListQuery(Empresa empresa, ListaDeVenta lv){
		Aggregation productosVisiblesListaDeVentaAggregation = Aggregation.newAggregation(match(Criteria.where("slistaDeVenta").is(lv.getSId()).andOperator(Criteria.where("eliminado").is(false)).orOperator(Criteria.where("esPublico").is(true), Criteria.where("sempresasConVisibilidad").in(empresa.getSId()), Criteria.where("sgruposConVisibilidad").in(empresa.getSempresaGrupos()))), group("sproducto"));
		List<ListaDeVentaVisibilidad> listaDeVentaVisibilidadProductos = mongoOperations.aggregate(productosVisiblesListaDeVentaAggregation, "ListaDeVentaVisibilidad", ListaDeVentaVisibilidad.class).getMappedResults();
		Set<String> productos = new HashSet<>();
		for (ListaDeVentaVisibilidad listaDeVentaVisibilidad: listaDeVentaVisibilidadProductos) {
			productos.add(listaDeVentaVisibilidad.getId());
		}

		Query productosQuery;
		productosQuery = new BasicQuery(ListaDeVentaQuerys.MOSTRAR_TODOS);

		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("sid").in(productos), Criteria.where("eliminado").is(false)).orOperator(Criteria.where("esPublico").is(true), Criteria.where("esPrivado").is(true));
		productosQuery.addCriteria(criteria);
		return productosQuery;
	}
}
