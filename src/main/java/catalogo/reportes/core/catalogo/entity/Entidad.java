package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeDeserializer;
import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;


import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(value = {"target"})
public abstract class Entidad implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id de la entidad")
    @Id
    protected String id;

    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    protected String sid;

    @Schema(hidden = true)
    protected Long oldId;

    @Schema(hidden = true)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    protected DateTime fechaCreacion;

    @Schema(hidden = true)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    protected DateTime fechaEdicion;

    @Schema(description = "(Soft delete) Marca una entidad como eliminada", hidden = true)
    protected Boolean eliminado;

    public Entidad() {
        this.eliminado = false;
        setFechaCreacion();
        setFechaEdicion();
    }

    /**
     * Actualiza la fecha de edición y cambia el atributo {@link Boolean} eliminado
     * a true
     */
    public void eliminar() {
        setFechaEdicion();
        this.eliminado = true;
    }

    /**
     * Le asigna la fecha y hora del momento al {@link DateTime} fechaEdicion
     */
    public void setFechaEdicion() {
        this.fechaEdicion = new DateTime();
    }

    public void setFechaCreacion() {
        DateTimeZone uruguay = DateTimeZone.forID("America/Argentina/Buenos_Aires");
        this.fechaCreacion = new DateTime();
    }

    public DateTime getFechaEdicion() {
        if (this.fechaEdicion == null)
            this.fechaEdicion = new DateTime();
        return this.fechaEdicion;
    }

    public void setFechaEdicion(DateTime date) {
        this.fechaEdicion = date;
    }

    public DateTime getFechaCreacion() {
        if (this.fechaCreacion == null)
            this.fechaCreacion = new DateTime();
        return this.fechaCreacion;
    }

    public void setFechaCreacion(DateTime date) {
        this.fechaCreacion = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String paramId) {
        this.id = paramId;
    }

    @JsonIgnore
    public long getOldId() {
        return oldId;
    }

    public void setOldId(long oldId) {
        this.oldId = oldId;
    }

    public void noEliminado() {
        this.eliminado = false;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void inicializarLinkData() {
    }

    public String getSId() {
        return sid;
    }

    public void setSId(String sId) {
        this.sid = sId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
