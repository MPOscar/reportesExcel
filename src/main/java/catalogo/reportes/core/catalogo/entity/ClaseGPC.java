package catalogo.reportes.core.catalogo.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ClaseGPC")
@CompoundIndexes({
		@CompoundIndex(name = "claseCode", def = "{ 'claseCode': 1 }", unique = true), })
public class ClaseGPC extends Entidad {

	private String claseCode;

	private String claseDescripcion;

	private String familiaCode;

	@DBRef(lazy = true)
	private FamiliaGPC familia;

	@DBRef(lazy = true)
	private List<BrickGPC> briks = new ArrayList<BrickGPC>();

	public ClaseGPC() {
		super();
	}

	public ClaseGPC(String familiaCode, String claseCode, String claseDescripcion) {
		this.familiaCode = familiaCode;
		this.claseCode = claseCode;
		this.claseDescripcion = claseDescripcion;
	}

	public String getClaseCode() {
		return claseCode;
	}

	public void setClaseCode(String familyCode) {
		this.claseCode = claseCode;
	}

	public String getClaseDescripcion() {
		return claseDescripcion;
	}

	public void setClaseDescripcion(String claseDescripcion) {
		this.claseDescripcion = claseDescripcion;
	}

	public FamiliaGPC getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaGPC familia) {
		this.familia = familia;
	}

	public String getFamiliaCode() {
		return familiaCode;
	}

	public void setFamiliaCode(String familiaCode) {
		this.familiaCode = familiaCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaseGPC other = (ClaseGPC) obj;
		if (other.getClaseCode().equals(this.getClaseCode()))
			return true;
		return false;
	}
}
