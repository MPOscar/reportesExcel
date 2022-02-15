package catalogo.reportes.core.catalogo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Armazón de madera, plástico, u otro material, empleado en el movimiento de carga.")
public class Pallet {

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String unidadesVenta;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String cajas;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String camadas;

    @Schema(description = "Altura del pallet")
    private String alto;

    @Schema(description = "Anchura del pallet")
    private String ancho;

    @Schema(description = "Profundidad del pallet")
    private String profundidad;

    public Pallet() {
        super();
    }

    public Pallet(String palletAlto, String palletAncho, String palletProfundidad, String palletCajas,
                  String palletCamadas, String palletUnidadesVenta) {
        super();
        this.alto = palletAlto;
        this.ancho = palletAncho;
        this.profundidad = palletProfundidad;
        this.cajas = palletCajas;
        this.camadas = palletCamadas;
        this.unidadesVenta = palletUnidadesVenta;
    }

    public Pallet(Pallet pallet) {
        super();
        this.alto = pallet.getAlto();
        this.ancho = pallet.getAncho();
        this.profundidad = pallet.getProfundidad();
        this.cajas = pallet.getCajas();
        this.camadas = pallet.getCamadas();
        this.unidadesVenta = pallet.getUnidadesVenta();
    }

    public void setCajas(String cajas) {
        this.cajas = cajas;
    }

    public void setCamadas(String camadas) {
        this.camadas = camadas;
    }

    public void setUnidadesVenta(String unidadesVenta) {
        this.unidadesVenta = unidadesVenta;
    }

    public String getCajas() {
        return this.cajas;
    }

    public String getCamadas() {
        return this.camadas;
    }

    public String getUnidadesVenta() {
        return this.unidadesVenta;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getAlto() {
        return this.alto;
    }

    public String getAncho() {
        return this.ancho;
    }

    public String getProfundidad() {
        return this.profundidad;
    }

}
