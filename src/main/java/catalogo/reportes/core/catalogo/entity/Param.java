package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Parámetros de configuración de Catálogo")
@Document(collection = "Param")
@CompoundIndexes({
        @CompoundIndex(name = "param_nombre", def = "{ 'nombre': 1 }", unique = true)
})
public class Param extends Entidad {

    @Schema(description = "Nombre del parámetro")
    private String nombre;

    @Schema(description = "Valor del parámetro")
    private String valor;

    public Param() {
        super();
    }

    public Param(Param param) {
        super();
        this.nombre = param.getNombre();
        this.valor = param.getValor();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
