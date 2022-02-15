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

@Schema(description = "Se agrupan empresas")
@Document(collection = "Grupo")
@CompoundIndexes({@CompoundIndex(name = "grupo_nombre", def = "{ 'nombre': 1, 'empresa': 1 }", unique = true)})
public class Grupo extends Entidad {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ArraySchema(arraySchema = @Schema(description = "Productos visibles en el grupo"))
    @DBRef(lazy = true)
    private Set<Producto> productosVisibles = new HashSet<>();

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private Set<String> sproductosVisibles = new HashSet<>();

    @Schema(description = "Empresa que creo este grupo")
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String sempresa;

    @Schema(description = "Nombre del grupo")
    private String nombre;

    @Schema(description = "Descripción del grupo")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String descripcion;

    @ArraySchema(arraySchema = @Schema(description = "Empresas que estan dendro de este grupo"))
    @DBRef(lazy = true)
    private Set<Empresa> empresas = new HashSet<>();

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private Set<String> sempresas = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Empresas que estan dendro de este grupo"))
    @DBRef(lazy = true)
    private Set<Empresa> empresasParaSincronizacion = new HashSet<>();

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private Set<String> sempresasParaSincronizacion = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Listas de ventas a las que el grupo pertenece"))
    @DBRef(lazy = true)
    private Set<ListaDeVenta> listasDeVenta = new HashSet<>();

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private Set<String> slistasDeVenta = new HashSet<>();

    private boolean fueActualizado;

    public Grupo(String nombre, String desc) {
        super();
        this.nombre = nombre;
        this.descripcion = desc;
    }

    public Grupo(Grupo grupo) {
        super();
        this.oldId = grupo.getOldId();
        this.nombre = grupo.getNombre();
        this.descripcion = grupo.getDescripcion();
        this.fechaCreacion = grupo.getFechaCreacion();
        this.fechaEdicion = grupo.getFechaEdicion();
        this.eliminado = grupo.getEliminado();
    }

    public Grupo() {
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

    @JsonIgnore()
    public Set<Producto> getProductosVisibles() {
        return productosVisibles;
    }

    public void setProductosVisibles(Set<Producto> productosVisibles) {
        this.productosVisibles = productosVisibles;
    }

    public Set<String> getSproductosVisibles() {
        return sproductosVisibles;
    }

    public void setSproductosVisibles(Set<String> sproductosVisibles) {
        this.sproductosVisibles = sproductosVisibles;
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

    public boolean getFueActualizado() {
        return fueActualizado;
    }

    public void setFueActualizado(boolean fueActualizado) {
        this.fueActualizado = fueActualizado;
    }

    @JsonIgnore()
    public Set<ListaDeVenta> getListasDeVenta() {
        return listasDeVenta;
    }

    public void setListasDeVenta(Set<ListaDeVenta> listasDeVenta) {
        this.listasDeVenta = listasDeVenta;
    }

    public Set<String> getSlistasDeVenta() {
        return slistasDeVenta;
    }

    public void setSlistasDeVenta(Set<String> slistasDeVenta) {
        this.slistasDeVenta = slistasDeVenta;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grupo other = (Grupo) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}