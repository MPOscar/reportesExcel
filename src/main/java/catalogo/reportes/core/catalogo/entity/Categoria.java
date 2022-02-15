package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.exceptions.ModelException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.ws.rs.core.Link;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Agrupación con que se clasifican determinadas empresas y productos")
@Document(collection = "Categoria")
@CompoundIndexes({
        @CompoundIndex(name = "nombre_categoria_empresa", def = "{ 'nombre': 1, 'sempresa': 1 }", unique = false)
})
public class Categoria extends Entidad {

    @Schema(description = "Nombre de la categoría.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String nombre;

    @Schema(description = "qué es un nivel de una categoría?")
    private Long nivel; // todo: qué es un nivel de una categoría?

    @Schema(description = "Descripción de la categoría.")
    private String descripcion;

    @Schema(description = "qué es una posición de una categoría?")
    private Long posicion; // todo: qué es una posición de una categoría?

    @Schema(description = "Empresa clasificada con la categoría")
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String sempresa;

    @Schema(description = "Categoría padre de la categoría. Una categoría puede a su vez, agrupar otras categorías")
    @DBRef(lazy = true)
    private Categoria padre;

    @Schema(hidden = true)
    private String spadre;

    @ArraySchema(arraySchema = @Schema(description = "Productos clasificados con la categoría"))
    @DBRef(lazy = true)
    private Set<Producto> productos = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductos = new HashSet<>();

    public Categoria() {
        super();
    }

    public Categoria(String nombre, Long nivel) {
        super();
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public Categoria(Categoria categoria) {
        super();
        this.oldId = categoria.getOldId();
        this.nombre = categoria.getNombre();
        this.nivel = categoria.getNivel();
        this.descripcion = categoria.getDescripcion();
        this.posicion = categoria.getPosicion();
        this.fechaCreacion = categoria.getFechaCreacion();
        this.fechaEdicion = categoria.getFechaEdicion();
        this.eliminado = categoria.getEliminado();
    }

    public Categoria(String nombre, Long nivel, Categoria padre) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.padre = padre;
    }

    public void copyForExcel(String nombre, Long nivel, Categoria padre) throws ModelException {
        if (padre.getPadre() != null) {
            if (this.getId() == padre.getPadre().getId()) {
                throw new ModelException("La categoría " + nombre + " tiene a la cateogría " + padre.getNombre()
                        + " como padre y vice versa");
            }
        }
        this.nombre = nombre;
        this.nivel = nivel;
        this.padre = padre;
    }

    public void checkForParentLoop(Categoria padre) throws ModelException {
        if (padre.getPadre() != null) {
            if (this.getId() == padre.getPadre().getId() || this.getNombre().equals(padre.getNombre())) {
                throw new ModelException("La categoría" + this.getNombre() + "tiene a la cateogría " + padre.getNombre()
                        + " como padre y vice versa");
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPosicion() {
        return posicion;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    @JsonIgnore
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

    public Categoria getPadre() {
        return padre;
    }

    public void setPadre(Categoria padre) {
        this.padre = padre;
    }

    public String getSpadre() {
        return spadre;
    }

    public void setSpadre(String spadre) {
        this.spadre = spadre;
    }

    @JsonIgnore
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

    @Override
    public void inicializarLinkData() {
        Link self = Link.fromUri("/categorias/" + this.id).rel("self").title("Obtener Categoria").type("GET").build();
        //this.getLinks().add(self);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}