package catalogo.reportes.core.catalogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Qué es?")// todo: revisar descripción
@Document(collection = "ListaDeVentaVisibilidad")
@CompoundIndexes({
        @CompoundIndex(name = "lista_de_venta_producto", def = "{ 'slistaDeVenta': 1, 'sproducto': 1 }"),
        @CompoundIndex(name = "lista_de_venta_producto_empresas", def = "{ 'slistaDeVenta': 1, 'sproducto': 1 , 'sempresasConVisibilidad': 1, 'eliminado': 1 , 'esPublico': 1, 'esPrivado': 1}"),
        @CompoundIndex(name = "lista_de_venta_producto_grupos", def = "{ 'slistaDeVenta': 1, 'sproducto': 1 , 'sgruposConVisibilidad': 1, 'eliminado': 1 , 'esPublico': 1, 'esPrivado': 1}")
})
public class ListaDeVentaVisibilidad extends Entidad {

    @Schema(description = "Qué es?")// todo: revisar descripción
    @DBRef(lazy = true)
    private ListaDeVenta listaDeVenta;

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String slistaDeVenta;

    @Schema(description = "Qué es?")// todo: revisar descripción
    @DBRef(lazy = true)
    private Producto producto;

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String sproducto;

    @ArraySchema(arraySchema = @Schema(description = "Qué es."))// todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Empresa> empresasConVisibilidad = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempresasConVisibilidad = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es."))// todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Empresa> empresasConVisibilidadParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempresasConVisibilidadParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es."))// todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Grupo> gruposConVisibilidad = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sgruposConVisibilidad = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es."))// todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Grupo> gruposConVisibilidadParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sgruposConVisibilidadParaSincronizacion = new HashSet<>();

    @Schema(description = "Qué es?")// todo: revisar descripción
    private Boolean esPrivado = false;

    @Schema(description = "Qué es?")// todo: revisar descripción
    private Boolean esPublico = true;

    @Schema(description = "Qué es?")// todo: revisar descripción
    private Boolean fueSincronizada = false;

    public ListaDeVentaVisibilidad() {
    }

    public ListaDeVentaVisibilidad(ListaDeVenta listaDeVenta, Set<Empresa> empresasConVisibilidad, Set<Grupo> gruposConVisibilidad, Producto producto) {
        super();
        this.listaDeVenta = listaDeVenta;
        this.producto = producto;
        this.empresasConVisibilidad = empresasConVisibilidad;
        this.gruposConVisibilidad = gruposConVisibilidad;
    }

    public ListaDeVentaVisibilidad(Long id, Long id1) {
    }

    public Boolean getEsPublico() {
        return this.esPublico;
    }

    public void setEsPublico(Boolean publico) {
        this.esPublico = publico;
    }

    public Boolean getEsPrivado() {
        return this.esPrivado;
    }

    public void setEsPrivado(Boolean esPrivado) {
        this.esPrivado = esPrivado;
    }

    public Boolean getFueSincronizada() {
        return fueSincronizada;
    }

    public void setFueSincronizada(Boolean fueSincronizada) {
        this.fueSincronizada = fueSincronizada;
    }

    public ListaDeVenta getListaDeVenta() {
        return listaDeVenta;
    }

    public void setListaDeVenta(ListaDeVenta listaDeVenta) {
        this.listaDeVenta = listaDeVenta;
    }

    public String getSlistaDeVenta() {
        return slistaDeVenta;
    }

    public void setSlistaDeVenta(String slistaDeVenta) {
        this.slistaDeVenta = slistaDeVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getSproducto() {
        return sproducto;
    }

    public void setSproducto(String sproducto) {
        this.sproducto = sproducto;
    }

    @JsonIgnore
    public Set<Empresa> getEmpresasConVisibilidad() {
        return empresasConVisibilidad;
    }

    public void setEmpresasConVisibilidad(Set<Empresa> empresasConVisibilidad) {
        this.empresasConVisibilidad = empresasConVisibilidad;
    }

    public Set<String> getSempresasConVisibilidad() {
        return sempresasConVisibilidad;
    }

    public void setSempresasConVisibilidad(Set<String> sempresasConVisibilidad) {
        this.sempresasConVisibilidad = sempresasConVisibilidad;
    }

    @JsonIgnore
    public Set<Grupo> getGruposConVisibilidad() {
        return gruposConVisibilidad;
    }

    public void setGruposConVisibilidad(Set<Grupo> gruposConVisibilidad) {
        this.gruposConVisibilidad = gruposConVisibilidad;
    }

    public Set<String> getSgruposConVisibilidad() {
        return sgruposConVisibilidad;
    }

    public void setSgruposConVisibilidad(Set<String> sgruposConVisibilidad) {
        this.sgruposConVisibilidad = sgruposConVisibilidad;
    }

    public Set<Empresa> getEmpresasConVisibilidadParaSincronizacion() {
        return empresasConVisibilidadParaSincronizacion;
    }

    public void setEmpresasConVisibilidadParaSincronizacion(Set<Empresa> empresasConVisibilidadParaSincronizacion) {
        this.empresasConVisibilidadParaSincronizacion = empresasConVisibilidadParaSincronizacion;
    }

    public Set<String> getSempresasConVisibilidadParaSincronizacion() {
        return sempresasConVisibilidadParaSincronizacion;
    }

    public void setSempresasConVisibilidadParaSincronizacion(Set<String> sempresasConVisibilidadParaSincronizacion) {
        this.sempresasConVisibilidadParaSincronizacion = sempresasConVisibilidadParaSincronizacion;
    }

    public Set<Grupo> getGruposConVisibilidadParaSincronizacion() {
        return gruposConVisibilidadParaSincronizacion;
    }

    public void setGruposConVisibilidadParaSincronizacion(Set<Grupo> gruposConVisibilidadParaSincronizacion) {
        this.gruposConVisibilidadParaSincronizacion = gruposConVisibilidadParaSincronizacion;
    }

    public Set<String> getSgruposConVisibilidadParaSincronizacion() {
        return sgruposConVisibilidadParaSincronizacion;
    }

    public void setSgruposConVisibilidadParaSincronizacion(Set<String> sgruposConVisibilidadParaSincronizacion) {
        this.sgruposConVisibilidadParaSincronizacion = sgruposConVisibilidadParaSincronizacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListaDeVentaVisibilidad other = (ListaDeVentaVisibilidad) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}