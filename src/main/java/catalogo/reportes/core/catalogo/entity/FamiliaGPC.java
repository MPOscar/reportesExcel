package catalogo.reportes.core.catalogo.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "FamiliaGPC")
@CompoundIndexes({
		@CompoundIndex(name = "familiaCode", def = "{ 'familiaCode': 1 }", unique = true), })
public class FamiliaGPC extends Entidad {

	private String familiaCode;

	private String familiaDescripcion;

	private String segmentCode;

	@DBRef(lazy = true)
	private SegmentGPC segment;

	@DBRef(lazy = true)
	private List<ClaseGPC> classes = new ArrayList<ClaseGPC>();

	public FamiliaGPC() {
		super();
	}

	public FamiliaGPC(String segmentCode, String familiaCode, String familiaDescripcion) {
		this.segmentCode = segmentCode;
		this.familiaCode = familiaCode;
		this.familiaDescripcion = familiaDescripcion;
	}

	public String getSegmentCode() {
		return segmentCode;
	}

	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}

	public void setSegment(SegmentGPC segment) {
		this.segment = segment;
	}

	public List<ClaseGPC> getClasses() {
		return classes;
	}

	public void setClasses(List<ClaseGPC> classes) {
		this.classes = classes;
	}

	public String getFamiliaCode() {
		return familiaCode;
	}

	public void setFamiliaCode(String familiaCode) {
		this.familiaCode = familiaCode;
	}

	public String getFamiliaDescripcion() {
		return familiaDescripcion;
	}

	public void setFamiliaDescripcion(String familiaDescripcion) {
		this.familiaDescripcion = familiaDescripcion;
	}

	public SegmentGPC getSegment() {
		return segment;
	}

	public void setSegmment(SegmentGPC segment) {
		this.segment = segment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FamiliaGPC other = (FamiliaGPC) obj;
		if (other.getFamiliaCode().equals(this.getFamiliaCode()))
			return true;
		return false;
	}
}
