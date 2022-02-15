package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Rol de un usuario registrado en el sistema")
@Document(collection = "Rol")
public class Rol extends Entidad {

	@Schema(description = "Símbolo que representa al rol")
	private String rol;

	@Schema(description = "Cadena de texto que describe el rol")
	private String descripcion;

	@Schema(description = "Indica si el rol es visible")
	private Boolean visible;

	public Rol() {
		super();
	}

	public Rol(String id, String rol, String descripcion, Boolean visible) {
		super();
		this.id = id;
		this.rol = rol;
		this.descripcion = descripcion;
		this.visible = visible;
	}

	public Rol(Rol rol) {
		super();
		this.oldId = rol.getOldId();
		this.rol = rol.getRol();
		this.descripcion = rol.getDescripcion();
		this.visible = rol.getVisible();
		this.fechaCreacion = rol.getFechaCreacion();
		this.fechaEdicion = rol.getFechaEdicion();
		this.eliminado = rol.getEliminado();
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (other.getId().equals(this.getId()))
			return true;
		return false;
	}
}
