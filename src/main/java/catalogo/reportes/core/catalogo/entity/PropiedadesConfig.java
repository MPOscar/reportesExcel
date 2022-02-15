package catalogo.reportes.core.catalogo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PropiedadesConfig")
public class PropiedadesConfig extends Entidad{

    private String propiedad;
    private String valor;

    public PropiedadesConfig() {
        super();
    }

    public PropiedadesConfig(String id, String propiedad, String valor) {
        super();
        this.id = id;
        this.propiedad = propiedad;
        this.valor = valor;
    }

    public PropiedadesConfig(PropiedadesConfig propiedadesConfig) {
        this.oldId = propiedadesConfig.getOldId();
        this.propiedad = propiedadesConfig.getPropiedad();
        this.valor = propiedadesConfig.getValor();
        this.fechaCreacion = propiedadesConfig.getFechaCreacion();
        this.fechaEdicion = propiedadesConfig.getFechaEdicion();
        this.eliminado = propiedadesConfig.getEliminado();
    }

    public String getPropiedad() {
        return propiedad;
    }
    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PropiedadesConfig other = (PropiedadesConfig) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }
}
