package catalogo.reportes.core.catalogo.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "SegmentGPC")
@CompoundIndexes({
		@CompoundIndex(name = "segmentCode", def = "{ 'segmentCode': 1 }", unique = true), })
public class SegmentGPC extends Entidad {

	private String segmentCode;

	private String segmentDescripcion;

	@DBRef(lazy = true)
	private List<FamiliaGPC> Familias = new ArrayList<FamiliaGPC>();

	public SegmentGPC() {
		super();
	}

	public SegmentGPC(String segmentCode, String segmentDescripcion) {
		this.segmentCode = segmentCode;
		this.segmentDescripcion = segmentDescripcion;
	}

	public String getSegmentCode() {
		return segmentCode;
	}

	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}

	public String getSegmentDescripcion() {
		return segmentDescripcion;
	}

	public void setSegmentDescripcion(String segmentDescripcion) {
		this.segmentDescripcion = segmentDescripcion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SegmentGPC other = (SegmentGPC) obj;
		if (other.getSegmentCode().equals(this.getSegmentCode()))
			return true;
		return false;
	}

}
