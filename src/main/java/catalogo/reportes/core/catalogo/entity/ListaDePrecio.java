package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeDeserializer;
import catalogo.reportes.core.catalogo.utils.serializer.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ListaDePrecio")
public class ListaDePrecio extends Entidad{

	private String descripcion;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String productoCpp;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String glnListaDeVenta;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String grupoClientes;

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private DateTime fechaVigencia;

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private DateTime fechaDeCarga;

	private String fechaDeCargaMilisegundos;

	private String fechaVigenciaMilisegundos;

	private String tipoIva;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String moneda;

	private String precio;

	private String precioBase;

	private String precioConDescuentos;

	private String precioConDescuentosEImpuestos;

	private String precioTotal;

	private String precioSugerido;

	private String unidades;

	public ListaDePrecio() {

	}

	public ListaDePrecio(String productoCpp, String glnListaDeVenta, String moneda, String precio) {
		this.productoCpp = productoCpp;
		this.glnListaDeVenta = glnListaDeVenta;
		this.moneda = moneda;
		this.precio = precio;
	}


	public ListaDePrecio(String productoCpp, String unidades, String tipoIva, String moneda, String precio, String glnListaDeVenta, String grupoClientes, DateTime fechaVigencia, DateTime fechaDeCarga, String descripcion) {
		this.productoCpp = productoCpp;
		this.glnListaDeVenta = glnListaDeVenta;
		this.grupoClientes = grupoClientes;
		this.moneda = moneda;
		this.precio = precio;
		this.precioBase = precio;
		this.fechaVigencia = fechaVigencia;
		this.fechaVigenciaMilisegundos = String.valueOf(fechaVigencia.getMillis());
		this.fechaDeCargaMilisegundos = String.valueOf(fechaDeCarga.getMillis());
		this.fechaDeCarga = fechaDeCarga;
		this.unidades = unidades;
		this.tipoIva = tipoIva;
		this.descripcion = descripcion;
	}

	public String getProductoCpp() {
		return productoCpp;
	}

	public void setProductoCpp(String productoCpp) {
		this.productoCpp = productoCpp;
	}

	public String getGlnListaDeVenta() {
		return glnListaDeVenta;
	}

	public void setGlnListaDeVenta(String glnListaDeVenta) {
		this.glnListaDeVenta = glnListaDeVenta;
	}

	public String getGrupoClientes() {
		return grupoClientes;
	}

	public void setGrupoClientes(String grupoClientes) {
		this.grupoClientes = grupoClientes;
	}

	public DateTime getFechaVigencia() {
		return fechaVigencia;
	}

	public String getFechaVigenciaMilisegundos() {
		return fechaVigenciaMilisegundos;
	}

	public void setFechaVigenciaMilisegundos(String fechaVigenciaMilisegundos) {
		this.fechaVigenciaMilisegundos = fechaVigenciaMilisegundos;
	}

	public void setFechaVigencia(DateTime fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getTipoIva() {
		return tipoIva;
	}

	public void setTipoIva(String tipoIva) {
		this.tipoIva = tipoIva;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(String precioBase) {
		this.precioBase = precioBase;
	}

	public String getPrecioConDescuentos() {
		return precioConDescuentos;
	}

	public void setPrecioConDescuentos(String precioConDescuentos) {
		this.precioConDescuentos = precioConDescuentos;
	}

	public String getPrecioSugerido() {
		return precioSugerido;
	}

	public void setPrecioSugerido(String precioSugerido) {
		this.precioSugerido = precioSugerido;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public DateTime getFechaDeCarga() {
		return fechaDeCarga;
	}

	public void setFechaDeCarga(DateTime fechaDeCarga) {
		this.fechaDeCarga = fechaDeCarga;
	}

	public String getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getPrecioConDescuentosEImpuestos() {
		return precioConDescuentosEImpuestos;
	}

	public void setPrecioConDescuentosEImpuestos(String precioConDescuentosEImpuestos) {
		this.precioConDescuentosEImpuestos = precioConDescuentosEImpuestos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaDeCargaMilisegundos() {
		return fechaDeCargaMilisegundos;
	}

	public void setFechaDeCargaMilisegundos(String fechaDeCargaMilisegundos) {
		this.fechaDeCargaMilisegundos = fechaDeCargaMilisegundos;
	}
}