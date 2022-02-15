package catalogo.reportes.core.catalogo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Información extendida del  laboratorio")
public class AtributosExtendidosLaboratorio extends Entidad {

    @Schema(description = "Cantidad del contenido neto")
    private String contenidoNetoCantidad;

    @Schema(description = "Unidad del contenido neto")
    private String contenidoNetoUnidad;

    @Schema(description = "Cantidad del contenido neto por uso")
    private String contenidoNetoPorUsoCantidad;

    @Schema(description = "Unidad del contenido neto por uso")
    private String contenidoNetoPorUsoUnidad;

    @Schema(description = "Unidad definida por la empresas farmacéuticas, ejemplo: Comprimidos, Capsulas")
    private String formaFarmaceutica;

    @Schema(description = "Forma de administración del fármaco")
    private String formaDeAdministracion;

    @Schema(description = "Indica si contiene azúcar")
    private Boolean contieneAzucar;

    @Schema(description = "Indica si contiene lactosa")
    private Boolean contieneLactosa;

    @Schema(description = "Prospecto del fármaco")
    private String prospecto;

    @Schema(description = "Sitio web del laboratorio")
    private String website;

    @ArraySchema(arraySchema = @Schema(description = "Componentes activos del fármaco"))
    private Set<ComponenteActivo> componentesActivos = new HashSet<>();

    public AtributosExtendidosLaboratorio(String contenidoNetoCantidad,
                                          String contenidoNetoUnidad,
                                          String contenidoNetoPorUsoCantidad,
                                          String contenidoNetoPorUsoUnidad,
                                          String formaFarmaceutica,
                                          String formaDeAdministracion,
                                          Boolean contieneAzucar,
                                          Boolean contieneLactosa,
                                          Set<ComponenteActivo> componentesActivos) {
        this.contenidoNetoCantidad = contenidoNetoCantidad;
        this.contenidoNetoUnidad = contenidoNetoUnidad;
        this.contenidoNetoPorUsoCantidad = contenidoNetoPorUsoCantidad;
        this.contenidoNetoPorUsoUnidad = contenidoNetoPorUsoUnidad;
        this.formaFarmaceutica = formaFarmaceutica;
        this.formaDeAdministracion = formaDeAdministracion;
        this.contieneAzucar = contieneAzucar;
        this.contieneLactosa = contieneLactosa;
        this.componentesActivos = componentesActivos;
    }

    @JsonIgnore
    public String getContenidoNetoCantidad() {
        return contenidoNetoCantidad;
    }

    public void setContenidoNetoCantidad(String contenidoNetoCantidad) {
        this.contenidoNetoCantidad = contenidoNetoCantidad;
    }

    @JsonIgnore
    public String getContenidoNetoUnidad() {
        return contenidoNetoUnidad;
    }

    public void setContenidoNetoUnidad(String contenidoNetoUnidad) {
        this.contenidoNetoUnidad = contenidoNetoUnidad;
    }

    public String getContenidoNetoPorUsoCantidad() {
        return contenidoNetoPorUsoCantidad;
    }

    public void setContenidoNetoPorUsoCantidad(String contenidoNetoPorUsoCantidad) {
        this.contenidoNetoPorUsoCantidad = contenidoNetoPorUsoCantidad;
    }

    public String getContenidoNetoPorUsoUnidad() {
        return contenidoNetoPorUsoUnidad;
    }

    public void setContenidoNetoPorUsoUnidad(String contenidoNetoPorUsoUnidad) {
        this.contenidoNetoPorUsoUnidad = contenidoNetoPorUsoUnidad;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getFormaDeAdministracion() {
        return formaDeAdministracion;
    }

    public void setFormaDeAdministracion(String formaDeAdministracion) {
        this.formaDeAdministracion = formaDeAdministracion;
    }

    public Boolean getContieneAzucar() {
        return contieneAzucar;
    }

    public void setContieneAzucar(Boolean contieneAzucar) {
        this.contieneAzucar = contieneAzucar;
    }

    public Boolean getContieneLactosa() {
        return contieneLactosa;
    }

    public void setContieneLactosa(Boolean contieneLactosa) {
        this.contieneLactosa = contieneLactosa;
    }

    public Set<ComponenteActivo> getComponentesActivos() {
        return componentesActivos;
    }

    public void setComponentesActivos(Set<ComponenteActivo> componentesActivos) {
        this.componentesActivos = componentesActivos;
    }

    public Boolean isContieneAzucar() {
        return contieneAzucar;
    }

    public Boolean isContieneLactosa() {
        return contieneLactosa;
    }

    public String getProspecto() {
        return prospecto;
    }

    public void setProspecto(String prospecto) {
        this.prospecto = prospecto;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AtributosExtendidosLaboratorio other = (AtributosExtendidosLaboratorio) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}
