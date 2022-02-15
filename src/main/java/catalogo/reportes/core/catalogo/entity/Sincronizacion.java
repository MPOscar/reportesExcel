package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Schema(description = "Fechas de la sincronizacion")
@Document(collection = "Sincronizacion")
@CompoundIndexes({
        @CompoundIndex(name = "sincronizacion_colleccion", def = "{ 'collecion': 1 }", unique = true)
})
public class Sincronizacion extends Entidad {

    @Schema(description = "Nombre de la collecion")
    private String colleccion;

    @Schema(description = "Ultima fecha de sincronizacion")
    private Date ultimaFechaSincronizacion;

    public Sincronizacion(String colleccion, Date ultimaFechaSincronizacion) {
        this.colleccion = colleccion;
        this.ultimaFechaSincronizacion = ultimaFechaSincronizacion;
    }

    public String getColleccion() {
        return colleccion;
    }

    public void setColleccion(String colleccion) {
        this.colleccion = colleccion;
    }

    public Date getUltimaFechaSincronizacion() {
        return ultimaFechaSincronizacion;
    }

    public void setUltimaFechaSincronizacion(Date ultimaFechaSincronizacion) {
        this.ultimaFechaSincronizacion = ultimaFechaSincronizacion;
    }

    @Override
    public String toString() {
        return "Sincronizacion{" +
                "colleccion='" + colleccion + '\'' +
                ", ultimaFechaSincronizacion=" + ultimaFechaSincronizacion +
                '}';
    }
}
