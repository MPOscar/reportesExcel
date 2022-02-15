package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Baja que se le realiza a una empresa") // todo: revisar descripción
@Document(collection = "Baja")
public class Baja extends Entidad {

    @Schema(description = "Motivo por la cual se realiza la baja a la empresa") // todo: revisar descripción
    private String motivo;

    @Schema(description = "Indica si la baja está vigente") // todo: revisar descripción
    private Boolean activo;

    @Schema(description = "Empresa causante de la baja") // todo: revisar descripción
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(hidden = true)
    private String sempresa;

    @Schema(description = "Administrados del sistema que autoriza o ejecuta la baja") // todo: revisar descripción
    @DBRef(lazy = true)
    private Usuario admin;

    @Schema(hidden = true)
    private String sadmin;

    public Baja() {
        super();
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

    public String getSadmin() {
        return sadmin;
    }

    public void setSadmin(String sadmin) {
        this.sadmin = sadmin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Baja other = (Baja) obj;
        if (other.getId() != null && other.getId().equals(this.getId()))
            return true;
        return false;
    }
}