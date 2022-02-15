package catalogo.reportes.core.catalogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.ws.rs.core.Link;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Lista de Venta")
@Document(collection = "ListaDeVenta")
public class ListaDeVenta extends Entidad {

    @Schema(description = "Empresa que crea la lista de ventas.")
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(hidden = true)
    private String sempresa;

    @Schema(description = "Qué es?") // todo: revisar descripción
    @DBRef(lazy = true)
    private Ubicacion ubicacion;

    @Schema(hidden = true)
    private String subicacion;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String glnUbicacion;

    @Schema(description = "Nombre de la lista de venta.")
    private String nombre;

    @Schema(description = "Descripción de la lista de venta.")
    private String descripcion;

    @ArraySchema(arraySchema = @Schema(description = "Listado de empresas que pueden ver la lista de ventas."))
    @DBRef(lazy = true)
    private Set<Empresa> empresas = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempresas = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Listado de empresas que pueden ver la lista de ventas."))
    @DBRef(lazy = true)
    private Set<Empresa> empresasParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempresasParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es?")) // todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Grupo> grupos = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sgrupos = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es?")) // todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Grupo> gruposParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sgruposParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Productos en la lista de venta"))
    @DBRef(lazy = true)
    private Set<Producto> productos = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductos = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Productos en la lista de venta"))
    @DBRef(lazy = true)
    private Set<Producto> productosParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductosParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Listado de productos visibles en la lista de venta."))
    @DBRef(lazy = true)
    private Set<ListaDeVentaVisibilidad> listaDeVentaProductosVisibilidad = new HashSet<ListaDeVentaVisibilidad>();

    @Schema(hidden = true)
    private Set<String> slistaDeVentaProductosVisibilidad = new HashSet<String>();

    public ListaDeVenta(String nombre, String desc) {
        super();
        this.nombre = nombre;
        this.descripcion = desc;
    }

    public ListaDeVenta(ListaDeVenta listaDeVenta) {
        super();
        this.nombre = listaDeVenta.getNombre();
        this.descripcion = listaDeVenta.getDescripcion();
    }

    public ListaDeVenta() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nom) {
        this.nombre = nom;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getSempresa() {
        return sempresa;
    }

    public void setSempresa(String sempresa) {
        this.sempresa = sempresa;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSubicacion() {
        return subicacion;
    }

    public void setSubicacion(String subicacion) {
        this.subicacion = subicacion;
    }

    @JsonIgnore()
    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Set<String> getSempresas() {
        return sempresas;
    }

    public void setSempresas(Set<String> sempresas) {
        this.sempresas = sempresas;
    }

    @JsonIgnore()
    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Set<String> getSgrupos() {
        return sgrupos;
    }

    public void setSgrupos(Set<String> sgrupos) {
        this.sgrupos = sgrupos;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Set<String> getSproductos() {
        return sproductos;
    }

    public void setSproductos(Set<String> sproductos) {
        this.sproductos = sproductos;
    }

    @JsonIgnore()
    public Set<ListaDeVentaVisibilidad> getListaDeVentaProductosVisibilidad() {
        return listaDeVentaProductosVisibilidad;
    }

    public void setListaDeVentaProductosVisibilidad(Set<ListaDeVentaVisibilidad> listaDeVentaProductosVisibilidad) {
        this.listaDeVentaProductosVisibilidad = listaDeVentaProductosVisibilidad;
    }

    public Set<String> getSlistaDeVentaProductosVisibilidad() {
        return slistaDeVentaProductosVisibilidad;
    }

    public void setSlistaDeVentaProductosVisibilidad(Set<String> slistaDeVentaProductosVisibilidad) {
        this.slistaDeVentaProductosVisibilidad = slistaDeVentaProductosVisibilidad;
    }

    public String getGlnUbicacion() {
        return glnUbicacion;
    }

    public void setGlnUbicacion(String glnUbicacion) {
        this.glnUbicacion = glnUbicacion;
    }

    public Set<Empresa> getEmpresasParaSincronizacion() {
        return empresasParaSincronizacion;
    }

    public void setEmpresasParaSincronizacion(Set<Empresa> empresasParaSincronizacion) {
        this.empresasParaSincronizacion = empresasParaSincronizacion;
    }

    public Set<String> getSempresasParaSincronizacion() {
        return sempresasParaSincronizacion;
    }

    public void setSempresasParaSincronizacion(Set<String> sempresasParaSincronizacion) {
        this.sempresasParaSincronizacion = sempresasParaSincronizacion;
    }

    public Set<Grupo> getGruposParaSincronizacion() {
        return gruposParaSincronizacion;
    }

    public void setGruposParaSincronizacion(Set<Grupo> gruposParaSincronizacion) {
        this.gruposParaSincronizacion = gruposParaSincronizacion;
    }

    public Set<String> getSgruposParaSincronizacion() {
        return sgruposParaSincronizacion;
    }

    public void setSgruposParaSincronizacion(Set<String> sgruposParaSincronizacion) {
        this.sgruposParaSincronizacion = sgruposParaSincronizacion;
    }

    public Set<Producto> getProductosParaSincronizacion() {
        return productosParaSincronizacion;
    }

    public void setProductosParaSincronizacion(Set<Producto> productosParaSincronizacion) {
        this.productosParaSincronizacion = productosParaSincronizacion;
    }

    public Set<String> getSproductosParaSincronizacion() {
        return sproductosParaSincronizacion;
    }

    public void setSproductosParaSincronizacion(Set<String> sproductosParaSincronizacion) {
        this.sproductosParaSincronizacion = sproductosParaSincronizacion;
    }

    @Override
    public void inicializarLinkData() {
        Link self = Link.fromUri("/listasDeVenta/" + this.id).rel("self").title("Obtener Lista de Venta").type("GET")
                .build();
        //this.links.add(self);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListaDeVenta other = (ListaDeVenta) obj;
        if (other.getId() != null && other.getId().equals(this.getId()))
            return true;
        return false;
    }
}