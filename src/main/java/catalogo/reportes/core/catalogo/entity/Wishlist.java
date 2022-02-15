package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Wishlist")
public class Wishlist extends Entidad {

	@DBRef(lazy = true)
	private UsuarioEmpresa usuarioEmpresa;

	@Schema(description = "Id del usuario al cual pertenece la lista de deseos.")
	private String susuarioEmpresa;

	@DBRef(lazy = true)
	private Empresa empresa;

	@Schema(description = "Id del empresa asociada al usuario al cual pertenece la lista de deseos.")
	private String sempresa;

	@DBRef(lazy = true)
	private Set<Producto> productos = new HashSet<Producto>();

	private Set<String> sproductos = new HashSet<String>();

	private Boolean enWishlist;

	public UsuarioEmpresa getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	public Boolean getEnWishlist() {
		return enWishlist;
	}

	public void setEnWishlist(Boolean enWishlist) {
		this.enWishlist = enWishlist;
	}

	public String getSusuarioEmpresa() {
		return susuarioEmpresa;
	}

	public void setSusuarioEmpresa(String susuarioEmpresa) {
		this.susuarioEmpresa = susuarioEmpresa;
	}

	public Set<String> getSproductos() {
		return sproductos;
	}

	public void setSproductos(Set<String> sproductos) {
		this.sproductos = sproductos;
	}

	public Wishlist() {
		super();
	}

}