package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.exceptions.PackException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.ws.rs.core.Link;
import java.math.BigDecimal;

@Schema(description = "Empaque de un producto")
@Document(collection = "Empaque")
public class Empaque extends Entidad {

    @Schema(description = "Identificador único. Código de barra asociado al empaque")
    private String gtin;

    @Schema(description = "Agrupación a la cual pertenece el empaque")
    private String cpp;

    @Schema(description = "Nivel del empaque.")
    private Integer nivel;

    @Schema(description = "Descripción del empaque.")
    private String descripcion;

    @Schema(description = "Unidad de medida del empaque.")
    private String unidadMedida;

    @Schema(description = "Alto del empaque.")
    private BigDecimal alto;

    @Schema(description = "Ancho del empaque.")
    private BigDecimal ancho;

    @Schema(description = "Profundidad del empaque.")
    private BigDecimal profundidad;

    @Schema(description = "Peso bruto del empaque.")
    private BigDecimal pesoBruto;

    @Schema(description = "Cantidad del empaque.")
    private BigDecimal cantidad;

    @Schema(description = "Clasificación del empaque.")
    private String clasificacion;

    @Schema(description = "Empresa due;a del empaque") // todo: revisar descripción
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(description = "Id de la empresa a la cual pertenece el empaque.")
    private String sempresa;

    @Schema(description = "Empaque padre del empaque. Un empaque puede contener a la vez, otros empaques")
    @DBRef(lazy = true)
    private Empaque padre;

    @Schema(hidden = true)
    private String spadre;

    @Schema(description = "Presentación del empaque.")
    @DBRef(lazy = true)
    private Presentacion presentacion;

    @Schema(hidden = true)
    private String spresentacion;

    public Empaque() {
        super();
    }

    public Empaque(String gtin, Integer nivel, String descripcion, String unidadMedida, BigDecimal alto,
                   BigDecimal ancho, BigDecimal profundidad, BigDecimal pesoBruto, BigDecimal cantidad,
                   Presentacion presentacion, String cpp, String clasificacion) {
        super();
        this.gtin = gtin;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.pesoBruto = pesoBruto;
        this.cantidad = cantidad;
        this.presentacion = presentacion;
        this.cpp = cpp;
        this.clasificacion = clasificacion;
    }

    public Empaque(String gtin, Integer nivel, String descripcion, String unidadMedida, BigDecimal alto,
                   BigDecimal ancho, BigDecimal profundidad, BigDecimal pesoBruto, BigDecimal cantidad, Empaque padre,
                   Presentacion presentacion, String cpp, String clasificacion) {
        super();
        this.gtin = gtin;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.pesoBruto = pesoBruto;
        this.cantidad = cantidad;
        this.presentacion = presentacion;
        this.padre = padre;
        this.cpp = cpp;
        this.clasificacion = clasificacion;
    }

    ;

    public Empaque(Empaque empaque) {
        super();
        this.oldId = empaque.getOldId();
        this.cpp = empaque.getCpp();
        this.cantidad = empaque.getCantidad();
        this.clasificacion = empaque.getClasificacion();
        this.alto = empaque.getAlto();
        this.ancho = empaque.getAncho();
        this.descripcion = empaque.getDescripcion();
        this.pesoBruto = empaque.getPesoBruto();
        this.gtin = empaque.getGtin();
        this.nivel = empaque.getNivel();
        this.profundidad = empaque.getProfundidad();
        this.unidadMedida = empaque.getUnidadMedida();
        this.fechaCreacion = empaque.getFechaCreacion();
        this.fechaEdicion = empaque.getFechaEdicion();
        this.eliminado = empaque.getEliminado();
        this.gtin = empaque.getGtin();
    }

    private static boolean isLong(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCpp() {
        return this.cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public void isValid(String gtin, String nivel, String unidadMedida, String alto, String ancho, String profundidad,
                        String pesoBruto, String cantidad, String cpp, String clasificacion) throws PackException {
        Boolean hasErrors = false;
        String errors = "Error en empaque: ";

        if (cpp.equals("") && gtin.equals("")) {
            hasErrors = true;
            errors += "Gtin y Cpp no pueden estar vacíos - ";
        }

		/*if (unidadMedida.equals("")) {
			hasErrors = true;
			errors += "La unidad de medida no puede estar vacía - ";
		}*/

        if (!isLong(gtin)) {
            hasErrors = true;
            errors += "El Gtin debe ser numérico - ";
        }

        if (!isLong(cantidad)) {
            hasErrors = true;
            errors += "La cantidad debe ser numérica - ";
        }
		/*if (!isLong(pesoBruto)) {
			hasErrors = true;
			errors += "El peso bruto debe ser numérico - ";
		}*/

		/*if (!isLong(alto)) {
			hasErrors = true;
			errors += "La altura debe ser numérica - ";
		}*/

		/*if (!isLong(ancho)) {
			hasErrors = true;
			errors += "El ancho debe ser numérico - ";
		}*/

		/*if (!isLong(profundidad)) {
			hasErrors = true;
			errors += "La profundidad debe ser numérica - ";
		}*/

        if (!isLong(nivel)) {
            hasErrors = true;
            errors += "El nivel debe ser numérico - ";
        }
        if (!clasificacion.toLowerCase().equals("inner pack") && !clasificacion.toLowerCase().equals("display")
                && !clasificacion.toLowerCase().equals("caja")) {
            hasErrors = true;
            errors += "La clasificación puede ser 'Inner Pack', 'Display' o 'Caja'";
        }

        if (hasErrors)
            throw new PackException(errors);
    }


    public void copy(Empaque empaque) {
        this.gtin = empaque.getGtin();
        this.cpp = empaque.getCpp();
        this.nivel = empaque.getNivel();
        this.descripcion = empaque.descripcion;
        this.unidadMedida = empaque.getUnidadMedida();
        this.alto = empaque.getAlto();
        this.ancho = empaque.getAncho();
        this.profundidad = empaque.getProfundidad();
        this.pesoBruto = empaque.getPesoBruto();
        this.cantidad = empaque.getCantidad();
        this.clasificacion = empaque.getClasificacion();
    }

    public void copyForExcel(String gtin, String nivel, String unidadMedida, String alto, String ancho,
                             String profundidad, String pesoBruto, String cantidad, String cpp, String clasificacion) {
        this.gtin = gtin.trim();
        this.cpp = cpp.trim();
        this.unidadMedida = unidadMedida;
        if (isLong(nivel)) {
            this.nivel = Integer.parseInt(nivel);
        }

        if (isLong(cantidad)) {
            this.cantidad = BigDecimal.valueOf(Double.parseDouble(cantidad));
        }

        if (isLong(alto)) {
            this.alto = BigDecimal.valueOf(Double.parseDouble(alto));
        }
        if (isLong(ancho)) {
            this.ancho = BigDecimal.valueOf(Double.parseDouble(ancho));
        }
        if (isLong(profundidad)) {
            this.profundidad = BigDecimal.valueOf(Double.parseDouble(profundidad));
        }
        if (isLong(pesoBruto)) {
            this.pesoBruto = BigDecimal.valueOf(Double.parseDouble(pesoBruto));
        }
        if (isLong(cantidad)) {
            this.cantidad = BigDecimal.valueOf(Double.parseDouble(cantidad));
        }
        this.clasificacion = clasificacion;
    }

    public void copyForExcelWithParent(String gtin, String nivel, String descripcion, String unidadMedida, String alto,
                                       String ancho, String profundidad, String pesoBruto, String cantidad, Empaque padre, String cpp,
                                       String clasificacion) {
        this.gtin = gtin;
        this.cpp = cpp;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        if (isLong(nivel)) {
            this.nivel = Integer.parseInt(nivel);
        }

        if (isLong(cantidad)) {
            this.cantidad = BigDecimal.valueOf(Double.parseDouble(cantidad));
        }

        if (isLong(alto)) {
            this.alto = BigDecimal.valueOf(Double.parseDouble(alto));
        }
        if (isLong(ancho)) {
            this.ancho = BigDecimal.valueOf(Double.parseDouble(ancho));
        }
        if (isLong(profundidad)) {
            this.profundidad = BigDecimal.valueOf(Double.parseDouble(profundidad));
        }
        if (isLong(pesoBruto)) {
            this.pesoBruto = BigDecimal.valueOf(Double.parseDouble(pesoBruto));
        }
        if (isLong(cantidad)) {
            this.cantidad = BigDecimal.valueOf(Double.parseDouble(cantidad));
        }
        this.padre = padre;
        this.clasificacion = clasificacion;
    }

    ;

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(BigDecimal profundidad) {
        this.profundidad = profundidad;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
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

    public Empaque getPadre() {
        return padre;
    }

    public void setPadre(Empaque padre) {
        this.padre = padre;
    }

    public String getSpadre() {
        return spadre;
    }

    public void setSpadre(String spadre) {
        this.spadre = spadre;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    public String getSpresentacion() {
        return spresentacion;
    }

    public void setSpresentacion(String spresentacion) {
        this.spresentacion = spresentacion;
    }

    @Override
    public void inicializarLinkData() {
        Link self = Link.fromUri("/empaques/" + this.id).rel("self").title("Obtener Empaque").type("GET").build();
        //this.links.add(self);
    }

    //@ValidationMethod(message = "Descripcion may not be Coda")
    @JsonIgnore
    public boolean isNotCoda() {
        return !"Coda".equals(descripcion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empaque other = (Empaque) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}