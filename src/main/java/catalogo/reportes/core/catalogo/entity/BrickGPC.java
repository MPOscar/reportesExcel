package catalogo.reportes.core.catalogo.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BrickGPC")
@CompoundIndexes({
		@CompoundIndex(name = "brickCode", def = "{ 'brickCode': 1 }", unique = true), })
public class BrickGPC extends Entidad {

	private String brickCode;

	private String brickDescripcion;

	private String claseCode;

	@DBRef(lazy = true)
	private ClaseGPC clase;

	public BrickGPC() {
		super();
	}

	public BrickGPC(String claseCode, String brickCode, String brickDescripcion) {
		this.claseCode = claseCode;
		this.brickCode = brickCode;
		this.brickDescripcion = brickDescripcion;
	}

	public String getBrickCode() {
		return brickCode;
	}

	public void setBrickCode(String familyCode) {
		this.brickCode = brickCode;
	}

	public String getBrickDescripcion() {
		return brickDescripcion;
	}

	public void setBrickDescripcion(String brickDescripcion) {
		this.brickDescripcion = brickDescripcion;
	}

	public ClaseGPC getClase() {
		return clase;
	}

	public void setClase(ClaseGPC clase) {
		this.clase = clase;
	}

	public String getClaseCode() {
		return claseCode;
	}

	public void setClaseCode(String claseCode) {
		this.claseCode = claseCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BrickGPC other = (BrickGPC) obj;
		if (other.getBrickCode().equals(this.getBrickCode()))
			return true;
		return false;
	}

}
