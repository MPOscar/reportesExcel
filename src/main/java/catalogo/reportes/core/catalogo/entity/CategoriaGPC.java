package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Qué es?") // todo: revisar descripción
public class CategoriaGPC {

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String segmentCode;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String familiaCode;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String claseCode;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String brickCode;

    public CategoriaGPC(String segmentCode, String familiaCode, String claseCode, String brickCode) {
        this.segmentCode = segmentCode;
        this.familiaCode = familiaCode;
        this.claseCode = claseCode;
        this.brickCode = brickCode;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getFamiliaCode() {
        return familiaCode;
    }

    public void setFamiliaCode(String familiaCode) {
        this.familiaCode = familiaCode;
    }

    public String getClaseCode() {
        return claseCode;
    }

    public void setClaseCode(String claseCode) {
        this.claseCode = claseCode;
    }

    public String getBrickCode() {
        return brickCode;
    }

    public void setBrickCode(String brickCode) {
        this.brickCode = brickCode;
    }
}
