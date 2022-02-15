package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Componente activo") // todo: revisar descripción
public class ComponenteActivo {
	@Schema(description = "Nombre de componente activo")
	private String nombre;
	@Schema(description = "Cantidad del componente activo")
	private String cantidad;
	@Schema(description = "Qué es?")// todo: revisar descripción
	private String unidad;
	@Schema(description = "Qué es?")// todo: revisar descripción
	private String enCantidad;
	@Schema(description = "Qué es?")// todo: revisar descripción
	private String enUnidad;

	public String getNombre() {
		return nombre;
	}

	public ComponenteActivo(String nombre, String cantidad, String unidad, String enCantidad, String enUnidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.enCantidad = enCantidad;
		this.enUnidad = enUnidad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getEnCantidad() {
		return enCantidad;
	}

	public void setEnCantidad(String enCantidad) {
		this.enCantidad = enCantidad;
	}

	public String getEnUnidad() {
		return enUnidad;
	}

	public void setEnUnidad(String enUnidad) {
		this.enUnidad = enUnidad;
	}

}
