package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeDeserializer;
import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Acción que tiene un cliente para realizar sobre un producto de un proveedor X.")
@Document(collection = "ProductoAccion")
@CompoundIndexes({
        @CompoundIndex(name = "proveedorId_empresaId_productoId", def = "{ 'proveedorId': 1, 'empresaId': 1, 'productoId': 1 }", unique = true),
})
public class ProductoAccion extends Entidad {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Indica si la empresa cliente recibió la acción realizada sobre un producto.")
    @Indexed(direction = IndexDirection.ASCENDING)
    protected Boolean recibido;

    @Schema(description = "Acción que tiene pendiente sobre un producto", example = "POST,UPDATE,DELETE")
    private String accion;

    @Schema(description = "Fecha en que se recibió la acción.")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime fechaRecibido;

    @Schema(description = "Id de la empresa proveedora.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String proveedorId;

    @Schema(description = "Id del producto.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String productoId;

    @Schema(description = "Rut de la empresa proveedora.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String proveedorRUT;


    @Schema(description = "Rut de la empresa cliente.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String empresaRUT;

    @Schema(description = "Id de la empresa cliente.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String empresaId;

    @Schema(description = "Indica si la empresa se actualizo la accion.")
    protected Boolean actualizada;


    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String productoCpp;


    @Schema(hidden = true)
    @Indexed(direction = IndexDirection.ASCENDING)
    private String productoGtin;

    public ProductoAccion() {
        super();
        this.recibido = false;
    }

    public ProductoAccion(Empresa proveedor, Empresa empresa, Producto producto) {
        this.proveedorId = proveedor.getId();
        this.proveedorRUT = proveedor.getRut();
        this.empresaId = empresa.getId();
        this.empresaRUT = empresa.getRut();
        this.productoGtin = producto.getGtin();
        this.productoId = producto.getId();
        this.productoCpp = producto.getCpp();
        this.fechaCreacion = new DateTime();
    }

    public ProductoAccion(ProductoAccion productoAccion) {
        super();
        this.oldId = productoAccion.getOldId();
        this.proveedorRUT = productoAccion.getProveedorRUT();
        this.empresaRUT = productoAccion.getEmpresaRUT();
        this.accion = productoAccion.getAccion();
        this.fechaRecibido = productoAccion.getFechaRecibido();
        this.recibido = productoAccion.getRecibido();
        this.fechaCreacion = productoAccion.getFechaCreacion();
        this.fechaEdicion = productoAccion.getFechaEdicion();
        this.eliminado = productoAccion.getEliminado();
    }

    public String getProveedorId() {
        return this.proveedorId;
    }

    public void setProveedorId(String idProveedor) {
        this.proveedorId = idProveedor;
    }

    public String getEmpresaId() {
        return this.empresaId;
    }

    public void setEmpresaId(String idEmpresa) {
        this.empresaId = idEmpresa;
    }

    public String getProductoId() {
        return this.productoId;
    }

    public void setProductoId(String idProducto) {
        this.productoId = idProducto;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setRecibido() {
        this.recibido = true;
    }

    public Boolean getRecibido() {
        return this.recibido;
    }

    public void setRecibido(Boolean recibido) {
        this.recibido = recibido;
    }

    public DateTime getFechaRecibido() {
        return this.fechaRecibido;
    }

    public void setFechaRecibido(DateTime date) {
        this.fechaRecibido = date;
    }

    public void setFechaRecibido() {
        DateTimeZone uruguay = DateTimeZone.forID("America/Argentina/Buenos_Aires");
        this.fechaRecibido = DateTime.now().withZone(uruguay);
        this.setRecibido();
    }

    public String getProveedorRUT() {
        return this.proveedorRUT;
    }

    public void setProveedorRUT(String proveedorRUT) {
        this.proveedorRUT = proveedorRUT;
    }

    public String getEmpresaRUT() {
        return this.empresaRUT;
    }

    public void setEmpresaRUT(String empresaRUT) {
        this.empresaRUT = empresaRUT;
    }

    public String getProductoCpp() {
        return productoCpp;
    }

    public void setProductoCpp(String productoCpp) {
        this.productoCpp = productoCpp;
    }

    public Boolean getActualizada() {
        return actualizada;
    }

    public void setActualizada(Boolean actualizada) {
        this.actualizada = actualizada;
    }

    public String getProductoGtin() {
        return productoGtin;
    }

    public void setProductoGtin(String productoGtin) {
        this.productoGtin = productoGtin;
    }
}