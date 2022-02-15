package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeDeserializer;
import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ubicacion")
@CompoundIndexes({
        @CompoundIndex(name = "ubicacion_codigo", def = "{ 'codigo': 1 }", unique = true)
})
public class Ubicacion extends Entidad {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Schema(description = "Código estándar de GS1.")
    private String codigo;

    @Schema(description = "Descripción de la ubicación.")
    private String descripcion;

    @Schema(description = "Tipo de ubicación. Puede ser una sucursal, un centro de distribución, un depósito, etc.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String tipo;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Schema(description = "Fecha de creación de la ubicación.")
    protected DateTime fechaAlta;

    @Schema(description = "Fecha de baja de la ubicación.")
    protected DateTime fechaBaja;

    @Schema(description = "Empresa asociada ala ubicación.")
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(description = "Id de la empresa a la cual pertenece la ubicación.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String sempresa;

    public Ubicacion(String codigo, String descripcion, String tipo, DateTime fechaAlta, DateTime fechaBaja,
                     Empresa empresa) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.empresa = empresa;
    }

    public Ubicacion(Ubicacion ubicacion) {
        super();
        this.oldId = ubicacion.getOldId();
        this.codigo = ubicacion.getCodigo();
        this.descripcion = ubicacion.getDescripcion();
        this.tipo = ubicacion.getTipo();
        this.fechaAlta = ubicacion.getFechaAlta();
        this.fechaBaja = ubicacion.getFechaBaja();
    }

    public Ubicacion() {
        super();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DateTime getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(DateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public DateTime getFechaBaja() {
        return this.fechaBaja;
    }

    public void setFechaBaja(DateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
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

    public void update(Ubicacion ubicacion) {
        this.setCodigo(ubicacion.getCodigo());
        this.setDescripcion(ubicacion.getDescripcion());
        this.setTipo(ubicacion.getTipo());
        this.setFechaAlta(ubicacion.getFechaAlta());
        this.setFechaBaja(ubicacion.getFechaBaja());
        this.eliminado = false;
        this.setFechaEdicion();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ubicacion other = (Ubicacion) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }

}
