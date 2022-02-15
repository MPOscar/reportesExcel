package catalogo.reportes.core.catalogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Kit o comobo promocional de productos") // todo: revisar descripción
public class KitPromocional extends Entidad {

    // mientras el getter esté anotado con @JsonIgnore, ésta lista no se expondrá en la doc de la api en el modelo
    // KitPromocional
    @Schema(description = "Productos pertenecientes al kit promocional")
    @DBRef(lazy = true)
    private Set<Producto> productos = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sproductos = new HashSet<>();

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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KitPromocional other = (KitPromocional) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}
