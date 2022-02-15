package catalogo.reportes.core.catalogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import javax.ws.rs.core.Link;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Empresa registrada en el sistema")
@Document(collection = "Empresa")
@CompoundIndexes({
        @CompoundIndex(name = "empresa_rut", def = "{ 'rut': 1 }", unique = true)
})
public class Empresa extends Entidad {

    @Schema(description = "Identificador de afiliación con GS1.")
    private String gln;

    @Schema(description = "Código interno de la empresa.")
    private String codigoInterno;

    @Schema(description = "Razón social de la empresa.")
    private String razonSocial;

    @Schema(description = "Nombre de la empresa.")
    private String nombre;

    @Schema(description = "Identificador de la empresa. Es único para cada empresa.", required = true)
    private String rut;

    @Schema(description = "Indica si la empresa está validada.")
    private Boolean validado;

    @Schema(description = "Indica si la empresa está activa.")
    private Boolean activo;

    @Schema(description = "Indica si la empresa acepto los terminos y condiciones.")
    private Boolean aceptoTerminosYCondiciones;

    @Schema(description = "Indica si la empresa utiliza el api de acciones.")
    private Boolean utilizaApiDeAcciones;

    @Schema(description = "Foto de la empresa.")
    private String foto;

    @ArraySchema(arraySchema = @Schema(description = "Cuáles son las acciones a realizar de un producto en una empresa?"))
    @DBRef(lazy = true)
    // todo: Cuáles son las acciones a realizar de un producto en una empresa?
    private Set<ProductoAccion> productosAccionesRealizar = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductosAccionesRealizar = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es un grupo de una empresa?"))
    @DBRef(lazy = true)
    // todo: Qué es un grupo de una empresa?
    private Set<Grupo> grupos = new HashSet<>(); // todo: representa lo mismo que la propiedad empresaGrupos?

    @Schema(hidden = true)
    private Set<String> sgrupos = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es un grupo de una empresa?"))
    @DBRef(lazy = true)
    private Set<Grupo> empresaGrupos = new HashSet<>(); // todo: representa lo mismo que la propiedad grupos?

    @Schema(hidden = true)
    private Set<String> sempresaGrupos = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es un grupo de una empresa?"))
    @DBRef(lazy = true)
    private Set<Grupo> empresaGruposParaSincronizacion = new HashSet<>(); // todo: representa lo mismo que la propiedad grupos?

    @Schema(hidden = true)
    private Set<String> sempresaGruposParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Productos de la empresa"))
    @DBRef(lazy = true)
    private Set<Producto> productosEmpresa = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductosEmpresa = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Listas de venta de la empresa"))
    @DBRef(lazy = true)
    private Set<ListaDeVenta> listasDeVenta = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> slistasDeVenta = new HashSet<>();

    // todo: revisar descripción
    @ArraySchema(arraySchema = @Schema(description = "Productos visibles en una empresa"))
    @DBRef(lazy = true)
    private Set<Producto> productosVisibles = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductosVisibles = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Categorias en las que clasifica la empresa"))
    @DBRef(lazy = true)
    private Set<Categoria> categorias = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> scategorias = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es una baja en una empresa?"))
    @DBRef(lazy = true)
    // todo: Qué es una baja en una empresa. A qué se le aplica una baja en una empresa?
    private Set<Baja> bajas = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sbajas = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es una ubicación de una empresa?"))
    @DBRef(lazy = true)
    // todo: Qué es una ubicación en una empresa?
    private Set<Ubicacion> ubicaciones = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> subicaciones = new HashSet<>();

    @Schema(description = "Qué es?") // todo: revisar descripción
    @Transient
    @JsonProperty
    private Baja nuevaBaja;

    @Schema(description = "Email de la empresa")
    @Transient
    @JsonProperty
    private String email;

    @Schema(description = "Qué es?") // todo: revisar descripción
    @Transient
    @JsonProperty
    private int wishlistSize;

    public Empresa() {
        super();
    }

    public Empresa(String gln, String razonSocial, String nombre, String rut, Boolean activo, Boolean eliminado,
                   String fechaCreacion, String fechaEdicion) {
        super();
        this.gln = gln;
        this.razonSocial = razonSocial;
        this.nombre = nombre;
        this.rut = rut;
        this.activo = activo;
        this.eliminado = eliminado;
        this.fechaCreacion = new DateTime(fechaCreacion);
        this.fechaEdicion = new DateTime(fechaEdicion);
    }

    public Empresa(Empresa empresa) {
        super();
        this.oldId = empresa.getOldId();
        this.gln = empresa.getGln();
        this.razonSocial = empresa.getRazonSocial();
        this.nombre = empresa.getNombre();
        this.rut = empresa.getRut();
        this.email = empresa.getEmail();
        this.wishlistSize = empresa.getWishlistSize();
        this.activo = empresa.getActivo();
        this.validado = empresa.getValidado();
        this.eliminado = empresa.getEliminado();
        this.fechaCreacion = empresa.getFechaCreacion();
        this.fechaEdicion = empresa.getFechaEdicion();
        this.eliminado = empresa.getEliminado();
    }

    public Boolean getAceptoTerminosYCondiciones() {
        return aceptoTerminosYCondiciones;
    }

    public void setAceptoTerminosYCondiciones(Boolean aceptoTerminosYCondiciones) {
        this.aceptoTerminosYCondiciones = aceptoTerminosYCondiciones;
    }

    public Baja getNuevaBaja() {
        return nuevaBaja;
    }

    public void setNuevaBaja(Baja nuevaBaja) {
        this.nuevaBaja = nuevaBaja;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore()
    public int getWishlistSize() {
        return wishlistSize;
    }

    public void setWishlistSize(int size) {
        this.wishlistSize = size;
    }

    @JsonIgnore()
    public Set<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(Set<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @JsonIgnore()
    public Set<String> getSubicaciones() {
        return subicaciones;
    }

    public void setSubicaciones(Set<String> subicaciones) {
        this.subicaciones = subicaciones;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getGln() {
        return gln;
    }

    public void setGln(String gln) {
        this.gln = gln;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean getValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    @JsonIgnore()
    public Set<ProductoAccion> getProductosAccionesRealizar() {
        return productosAccionesRealizar;
    }

    public void setProductosAccionesRealizar(Set<ProductoAccion> productosAccionesRealizar) {
        this.productosAccionesRealizar = productosAccionesRealizar;
    }

    @JsonIgnore()
    public Set<String> getSproductosAccionesRealizar() {
        return sproductosAccionesRealizar;
    }

    public void setSproductosAccionesRealizar(Set<String> sproductosAccionesRealizar) {
        this.sproductosAccionesRealizar = sproductosAccionesRealizar;
    }

    @JsonIgnore()
    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    @JsonIgnore()
    public Set<String> getSgrupos() {
        return sgrupos;
    }

    public void setSgrupos(Set<String> sgrupos) {
        this.sgrupos = sgrupos;
    }

    @JsonIgnore()
    public Set<Producto> getProductosEmpresa() {
        return productosEmpresa;
    }

    public void setProductosEmpresa(Set<Producto> productosEmpresa) {
        this.productosEmpresa = productosEmpresa;
    }

    @JsonIgnore()
    public Set<String> getSproductosEmpresa() {
        return sproductosEmpresa;
    }

    public void setSproductosEmpresa(Set<String> sproductosEmpresa) {
        this.sproductosEmpresa = sproductosEmpresa;
    }

    @JsonIgnore()
    public Set<ListaDeVenta> getListasDeVenta() {
        return listasDeVenta;
    }

    public void setListasDeVenta(Set<ListaDeVenta> listasDeVenta) {
        this.listasDeVenta = listasDeVenta;
    }

    @JsonIgnore()
    public Set<String> getSlistasDeVenta() {
        return slistasDeVenta;
    }

    public void setSlistasDeVenta(Set<String> slistasDeVenta) {
        this.slistasDeVenta = slistasDeVenta;
    }

    @JsonIgnore()
    public Set<Producto> getProductosVisibles() {
        return productosVisibles;
    }

    public void setProductosVisibles(Set<Producto> productosVisibles) {
        this.productosVisibles = productosVisibles;
    }

    @JsonIgnore()
    public Set<String> getSproductosVisibles() {
        return sproductosVisibles;
    }

    public void setSproductosVisibles(Set<String> sproductosVisibles) {
        this.sproductosVisibles = sproductosVisibles;
    }

    @JsonIgnore()
    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    @JsonIgnore()
    public Set<String> getScategorias() {
        return scategorias;
    }

    public void setScategorias(Set<String> scategorias) {
        this.scategorias = scategorias;
    }

    public Set<Baja> getBajas() {
        return bajas;
    }

    public void setBajas(Set<Baja> bajas) {
        this.bajas = bajas;
    }

    @JsonIgnore()
    public Set<String> getSbajas() {
        return sbajas;
    }

    public void setSbajas(Set<String> sbajas) {
        this.sbajas = sbajas;
    }

    @JsonIgnore()
    public Set<Grupo> getEmpresaGrupos() {
        return empresaGrupos;
    }

    public void setEmpresaGrupos(Set<Grupo> empresaGrupos) {
        this.empresaGrupos = empresaGrupos;
    }

    @JsonIgnore()
    public Set<String> getSempresaGrupos() {
        return sempresaGrupos;
    }

    public void setSempresaGrupos(Set<String> sempresaGrupos) {
        this.sempresaGrupos = sempresaGrupos;
    }

    public Set<Grupo> getEmpresaGruposParaSincronizacion() {
        return empresaGruposParaSincronizacion;
    }

    public void setEmpresaGruposParaSincronizacion(Set<Grupo> empresaGruposParaSincronizacion) {
        this.empresaGruposParaSincronizacion = empresaGruposParaSincronizacion;
    }

    public Set<String> getSempresaGruposParaSincronizacion() {
        return sempresaGruposParaSincronizacion;
    }

    public void setSempresaGruposParaSincronizacion(Set<String> sempresaGruposParaSincronizacion) {
        this.sempresaGruposParaSincronizacion = sempresaGruposParaSincronizacion;
    }

    @JsonIgnore
    public Boolean getUtilizaApiDeAcciones() {
        return utilizaApiDeAcciones;
    }

    public void setUtilizaApiDeAcciones(Boolean utilizaApiDeAcciones) {
        this.utilizaApiDeAcciones = utilizaApiDeAcciones;
    }

    @JsonIgnore()
    public Baja getBaja() {
        if (this.bajas != null) {
            for (Baja baja : this.bajas) {
                if (!baja.getEliminado() && baja.getActivo()) {
                    return baja;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }

    @Override
    public void inicializarLinkData() {
        Link self = Link.fromUri("/empresas/" + this.id).rel("self").title("Obtener Empresa").type("GET").build();
        //this.links.add(self);
    }
}

