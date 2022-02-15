package catalogo.reportes.core.catalogo.entity;

import catalogo.reportes.core.catalogo.utils.serializer.FechaPedidoCustomDateTimeDeserializer;
import catalogo.reportes.core.catalogo.utils.serializer.FechaPedidoCustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import catalogo.reportes.core.catalogo.exceptions.ModelException;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Producto de una empresa")
@Document(collection = "Producto")
@CompoundIndexes({
        @CompoundIndex(name = "producto_empresa_cpp", def = "{ 'cpp': 1, 'empresa': 1 }", unique = true),})
public class Producto extends Entidad {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(Producto.class);

    @Schema(description = "Fecha en la cual se suspendió el producto.")
    @JsonSerialize(using = FechaPedidoCustomDateTimeSerializer.class)
    @JsonDeserialize(using = FechaPedidoCustomDateTimeDeserializer.class)
    protected DateTime suspendidoDesde;

    @Schema(description = "Fecha en la cual terminará la suspensión del producto")
    @JsonSerialize(using = FechaPedidoCustomDateTimeSerializer.class)
    @JsonDeserialize(using = FechaPedidoCustomDateTimeDeserializer.class)
    protected DateTime suspendidoHasta;

    @Schema(description = "Es un identificador del producto. El productor base lo genera.") // todo: revisar descripción
    private String cpp;

    @Schema(description = "Es una categoría que le da la empresa al producto.", example = "")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String division;

    @Schema(description = "Línea del producto.") // todo: revisar descripción
    @Indexed(direction = IndexDirection.ASCENDING)
    private String linea;

    @Schema(description = "Pallet en el que se va a cargar el producto")
    private Pallet pallet;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private boolean eskitPromocional;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private KitPromocional kitPromocional;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private String tipo;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private AtributosExtendidosLaboratorio atributosLaboratorio;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private AtributosExtendidosAlimentos atributosExtendidosAlimentos;

    @Schema(description = "Código de barra") // todo: revisar descripción
    private String gtin;

    @Schema(description = "Código de barra de la presentación del producto") // todo: revisar descripción
    private String gtinPresentacion;

    @Schema(description = "El nombre del producto.")
    private String descripcion;

    @Schema(description = "Mercado al cual va dirigido el producto.")
    private String mercadoObjetivo;

    @Schema(description = "Unidad de medida en que se mide el peso bruto del producto")// todo: revisar descripción
    private String unidadMedidaPesoBruto;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private Integer nivelMinimoVenta;

    @Schema(description = "Marca del producto.")
    @Indexed(direction = IndexDirection.ASCENDING)
    private String marca;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private BigDecimal contenidoNeto;

    // todo: revisar descripción
    @Schema(description = "Unidad de medida en la que se medirán las dimensiones de logitud del producto")
    private String unidadMedida;

    @Schema(description = "País de origen del producto.")
    private String paisOrigen;

    @Schema(description = "Foto del Producto")
    private String foto;

    @Schema(description = "Altura del Producto")
    private BigDecimal alto;

    @Schema(description = "Anchura del Producto")
    private BigDecimal ancho;

    @Schema(description = "Profundidad del Producto")
    private BigDecimal profundidad;

    @Schema(description = "Peso bruto del Producto", example = "1.3kg, 1.3g")
    private BigDecimal pesoBruto;

    @Schema(description = "Indica si el producto está en promoción.")
    private Boolean esPromo;

    @Schema(description = "Guarda la visibilidad al ser sincronizado.")
    private String visibilidadEnCatalogoViejo;

    @Schema(description = "Qué es?") // todo: revisar descripción
    private CategoriaGPC categoriaGPC;

    @Schema(description = "Indica si el producto es privado.")
    private Boolean esPrivado;

    @Schema(description = "Qué es?") // todo: revisar descripción
    @Transient
    @JsonProperty
    private Boolean visibilidadPorGrupo;

    @Schema(description = "Indica si el producto es privado.")
    private Boolean esPublico;

    @Schema(description = "Indica si el producto es el original entre todos los que tengan el mismo gtin.")
    private Boolean esPrincipal;

    @Schema(description = "Empresa a la que pertenece el producto")
    @DBRef(lazy = true)
    private Empresa empresa;

    @Schema(hidden = true)
    private String sempresa;

    @Schema(description = "Categoría a la que pertenece el producto")
    @DBRef(lazy = true)
    private Categoria categoria;

    @Schema(hidden = true)
    private String scategoria;

    @Schema(description = "Presentación asociada al producto") // todo: revisar descripción
    @DBRef(lazy = true)
    private Presentacion presentacion;

    @Schema(hidden = true)
    private String spresentacion;

    @ArraySchema(arraySchema = @Schema(description = "Distintos empaques en los que puede venir el producto"))
    @DBRef(lazy = true)
    private Set<Empaque> empaques = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempaques = new HashSet<String>();

    // todo: revisar descripción
    @ArraySchema(arraySchema = @Schema(description = "Lista de ventas en las que aparece el producto"))
    @DBRef(lazy = true)
    private Set<ListaDeVenta> listasDeVentas = new HashSet<ListaDeVenta>();

    @Schema(hidden = true)
    private Set<String> slistasDeVentas = new HashSet<String>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es?")) // todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Empresa> empresasConVisibilidad = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sempresasConVisibilidad = new HashSet<>();

    @ArraySchema(arraySchema = @Schema(description = "Qué es?")) // todo: revisar descripción
    @DBRef(lazy = true)
    private Set<Grupo> gruposConVisibilidad = new HashSet<>();

    @Schema(hidden = true)
    private Set<String> sgruposConVisibilidad = new HashSet<>();

    @Schema(description = "Indica si durante la sincronizacion hubo algun cambio en los atributos del producto.")
    private Boolean cambiosEnAtributosDelProducto;

    public Boolean getCambiosEnAtributosDelProducto() {
        return cambiosEnAtributosDelProducto;
    }

    public void setCambiosEnAtributosDelProducto(Boolean cambiosEnAtributosDelProducto) {
        this.cambiosEnAtributosDelProducto = cambiosEnAtributosDelProducto;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public Producto() {
        super();
        this.esPublico = false;
        this.esPrivado = false;
    }

    public Producto(String cpp, String gtin, String paisOrigen, String descripcion, String marca,
                    Presentacion presentacion, String unidadMedida, BigDecimal contenidoNeto, BigDecimal pesoBruto,
                    BigDecimal alto, BigDecimal ancho, BigDecimal profundidad, String mercadoObjetivo,
                    String unidadMedidaPesoBruto, Integer nivelMinimoVenta) {
        super();
        this.cpp = cpp;
        this.gtin = gtin;
        if (gtin.length() == 6)
            gtin = "0" + gtin;
        else if (gtin.length() == 11)
            gtin = "0" + gtin;
        this.gtinPresentacion = gtin;
        this.paisOrigen = paisOrigen;
        this.descripcion = descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.contenidoNeto = contenidoNeto;
        this.pesoBruto = pesoBruto;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.esPublico = false;
        this.esPrivado = true;
        this.mercadoObjetivo = mercadoObjetivo;
        this.unidadMedidaPesoBruto = unidadMedidaPesoBruto;
        this.nivelMinimoVenta = nivelMinimoVenta;
    }

    public Producto(Producto producto) {
        super();
        this.oldId = producto.getOldId();
        this.alto = producto.getAlto();
        this.ancho = producto.getAncho();
        // this.categoria = producto.getCategoria();
        this.contenidoNeto = producto.getContenidoNeto();
        this.cpp = producto.getCpp();
        this.descripcion = producto.getDescripcion();
        if (this.empaques.size() > 0 && producto.getEmpaques().size() > 0) {
            Empaque empaque = (Empaque) this.empaques.toArray()[0];
            Empaque newEmpaque = (Empaque) producto.getEmpaques().toArray()[0];
            empaque.setGtin(newEmpaque.getGtin());
            empaque.setPresentacion(newEmpaque.getPresentacion());
            empaque.setCantidad(newEmpaque.getCantidad());
            this.empaques.toArray()[0] = empaque;
        } else if (producto.getEmpaques().size() > 0) {
            // this.empaques = producto.getEmpaques();
        }
        this.foto = producto.getFoto();
        this.gtin = producto.getGtin();
        this.marca = producto.getMarca();
        this.paisOrigen = producto.getPaisOrigen();
        this.pesoBruto = producto.getPesoBruto();
        // this.presentacion = producto.getPresentacion();
        this.profundidad = producto.getProfundidad();
        this.unidadMedida = producto.getUnidadMedida();
        this.esPromo = producto.getEsPromo();
        this.esPrivado = producto.getEsPrivado();
        this.esPublico = producto.getEsPublico();
        this.fechaCreacion = producto.getFechaCreacion();
        this.fechaEdicion = producto.getFechaEdicion();
        this.eliminado = producto.getEliminado();
        /*
         * if(this.esPrivado) { if(producto.getEsPublico()){ this.esPrivado = false;
         * this.esPublico = true; if(this.getGruposConVisibilidad() != null){
         * this.getGruposConVisibilidad().clear(); } if(this.getEmpresasConVisibilidad()
         * != null){ this.getEmpresasConVisibilidad().clear(); } }else
         * if(producto.getEsPrivado()){ if (producto.getGruposConVisibilidad() != null
         * && producto.getGruposConVisibilidad().size() > 0) {
         * //this.gruposConVisibilidad.addAll(producto.getGruposConVisibilidad()); }else
         * if(producto.getEmpresasConVisibilidad() != null &&
         * producto.getEmpresasConVisibilidad().size() > 0){
         * //this.empresasConVisibilidad.addAll(producto.getEmpresasConVisibilidad()); }
         * } }
         */
        // this.empresasConVisibilidad = producto.getEmpresasConVisibilidad();
        this.gtinPresentacion = producto.getGtinPresentacion();
        this.mercadoObjetivo = producto.getMercadoObjetivo();
        this.nivelMinimoVenta = producto.getNivelMinimoVenta();
        /*
         * if(this.pallet != null && producto.getPallet() != null){
         * this.pallet.setAlto(producto.getPallet().getAlto());
         * this.pallet.setAncho(producto.getPallet().getAncho());
         * this.pallet.setProfundidad(producto.getPallet().getProfundidad());
         * this.pallet.setUnidadesVenta(producto.getPallet().getUnidadesVenta());
         * this.pallet.setCajas(producto.getPallet().getCajas());
         * this.pallet.setCamadas(producto.getPallet().getCamadas()); }else
         * if(producto.getPallet() != null){ Pallet pallet = producto.getPallet();
         * MPallet mPallet = new MPallet(pallet); }
         */
        this.unidadMedidaPesoBruto = producto.getUnidadMedidaPesoBruto();
        this.suspendidoDesde = producto.getSuspendidoDesde();
        this.suspendidoHasta = producto.getSuspendidoHasta();
    }

    private static boolean isLong(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pallet getPallet() {
        return this.pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public String getMercadoObjetivo() {
        return this.mercadoObjetivo;
    }

    public void setMercadoObjetivo(String mercado) {
        this.mercadoObjetivo = mercado;
    }

    public String getUnidadMedidaPesoBruto() {
        return this.unidadMedidaPesoBruto;
    }

    public void setUnidadMedidaPesoBruto(String unidadMedidaPesoBruto) {
        this.unidadMedidaPesoBruto = unidadMedidaPesoBruto;
    }

    public Integer getNivelMinimoVenta() {
        return this.nivelMinimoVenta;
    }

    public void setNivelMinimoVenta(Integer nivelMinimoVenta) {
        this.nivelMinimoVenta = nivelMinimoVenta;
    }

    public Boolean getEsPromo() {
        return this.esPromo;
    }

    public void setEsPromo(Boolean promo) {
        this.esPromo = promo;
    }

    public Boolean getEsPrivado() {
        return this.esPrivado;
    }

    public void setEsPrivado(Boolean esPrivado) {
        this.esPrivado = esPrivado;
    }

    public Boolean isEsPromo() {
        return this.esPromo;
    }

    public Boolean isEsPrivado() {
        return this.esPrivado;
    }

    public Boolean isVisibilidadPorGrupo() {
        return this.visibilidadPorGrupo;
    }

    public Boolean isEsPublico() {
        return this.esPublico;
    }

    public Boolean getVisibilidadPorGrupo() {
        return this.visibilidadPorGrupo != null ? this.visibilidadPorGrupo : false;
    }

    public void setVisibilidadPorGrupo(Boolean visibilidad) {
        this.visibilidadPorGrupo = visibilidad;
    }

    public Boolean getEsPublico() {
        return this.esPublico;
    }

    public void setEsPublico(Boolean publico) {
        this.esPublico = publico;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public boolean getEskitPromocional() {
        return eskitPromocional;
    }

    public KitPromocional getKitPromocional() {
        return kitPromocional;
    }

    public void setKitPromocional(KitPromocional kitPromocional) {
        this.kitPromocional = kitPromocional;
    }

    public AtributosExtendidosLaboratorio getAtributosLaboratorio() {
        return atributosLaboratorio;
    }

    public void setAtributosLaboratorio(AtributosExtendidosLaboratorio atributosLaboratorio) {
        this.atributosLaboratorio = atributosLaboratorio;
    }

    public AtributosExtendidosAlimentos getAtributosExtendidosAlimentos() {
        return atributosExtendidosAlimentos;
    }

    public void setAtributosExtendidosAlimentos(AtributosExtendidosAlimentos atributosExtendidosAlimentos) {
        this.atributosExtendidosAlimentos = atributosExtendidosAlimentos;
    }

    public CategoriaGPC getCategoriaGPC() {
        return categoriaGPC;
    }

    public void setCategoriaGPC(CategoriaGPC categoriaGPC) {
        this.categoriaGPC = categoriaGPC;
    }

    ;

    public String getVisibilidadEnCatalogoViejo() {
        return visibilidadEnCatalogoViejo;
    }

    public void setVisibilidadEnCatalogoViejo(String visibilidadEnCatalogoViejo) {
        this.visibilidadEnCatalogoViejo = visibilidadEnCatalogoViejo;
    }

    public void laboratorio(Producto productoLaboratorio) {
        this.setCpp(productoLaboratorio.getCpp());
        this.setDescripcion(productoLaboratorio.getDescripcion());
        this.setMarca(productoLaboratorio.getMarca());
        this.setTipo(productoLaboratorio.getTipo());
        this.setEskitPromocional(productoLaboratorio.getEskitPromocional());
        this.setAtributosLaboratorio(productoLaboratorio.getAtributosLaboratorio());
        this.setContenidoNeto(productoLaboratorio.getContenidoNeto());
        this.setUnidadMedida(productoLaboratorio.getUnidadMedida());
    }

    ;

    public void isValid(String cpp, String gtin, String paisOrigen, String descripcion, String marca,
                        String unidadMedida, String pesoBruto, String contenidoNeto, String alto, String ancho, String profundidad,
                        String mercadoObjetivo, String unidadMedidaPesoBruto, String nivelMinimoVenta) throws ModelException {
        Boolean hasErrors = false;
        String errors = "";
        if (cpp.equals("")) {
            hasErrors = true;
            errors += " Cpp no puede estar vacío - ";
        }
        if (gtin.equals("")) {
            hasErrors = true;
            errors += "Gtin no pueden estar vacío - ";
        }
        if (!isLong(gtin)) {
            hasErrors = true;
            errors += "El Gtin debe ser numérico - ";
        }

        if (descripcion.equals("")) {
            hasErrors = true;
            errors += "El nombre del producto no puede estar vacío - ";
        }
        if (!isLong(contenidoNeto)) {
            hasErrors = true;
            errors += "El contenido neto debe ser numérico - ";
        }
        if (!isLong(pesoBruto)) {
            pesoBruto = "0";
        }
        if (!isLong(alto)) {
            alto = "0";
        }
        if (!isLong(ancho)) {
            ancho = "0";
        }
        if (!isLong(profundidad)) {
            profundidad = "0";
        }

        if (hasErrors)
            throw new ModelException(errors);
    }

    public void setExcelData(String cpp, String gtin, String paisOrigen, String descripcion, String marca,
                             String unidadMedida, String pesoBruto, String contenidoNeto, String alto, String ancho, String profundidad,
                             String mercadoObjetivo, String unidadMedidaPesoBruto, String nivelMinimoVenta, Boolean promo)
            throws ModelException {

        this.cpp = cpp;
        this.gtin = gtin;
        if (gtin.length() == 6)
            gtin = "0" + gtin;
        else if (gtin.length() == 11)
            gtin = "0" + gtin;
        this.gtinPresentacion = gtin;
        this.esPromo = promo;
        this.paisOrigen = paisOrigen;
        this.descripcion = descripcion;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
        this.esPublico = false;
        this.esPrivado = false;
        this.mercadoObjetivo = mercadoObjetivo;
        this.unidadMedidaPesoBruto = unidadMedidaPesoBruto;
        try {
            this.nivelMinimoVenta = Integer.parseInt(nivelMinimoVenta);
        } catch (NumberFormatException ex) {
            this.nivelMinimoVenta = 1;
        }
        if (contenidoNeto != null && isLong(contenidoNeto)) {
            this.contenidoNeto = BigDecimal.valueOf(Double.parseDouble(contenidoNeto));
        }
        if (pesoBruto != null && isLong(pesoBruto)) {
            this.pesoBruto = BigDecimal.valueOf(Double.parseDouble(pesoBruto));
        }
        if (alto != null && isLong(alto)) {
            this.alto = BigDecimal.valueOf(Double.parseDouble(alto));
        }
        if (ancho != null && isLong(ancho)) {
            this.ancho = BigDecimal.valueOf(Double.parseDouble(ancho));
        }
        if (profundidad != null && isLong(profundidad)) {
            this.profundidad = BigDecimal.valueOf(Double.parseDouble(profundidad));
        }
    }

    ;

    public long getOldId() {
        return oldId;
    }

    ;

    public void setOldId(Long id) {
        this.oldId = id;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
        this.setGtinPresentacion(gtin);
    }

    public String getGtinPresentacion() {
        return gtinPresentacion;
    }

    public void setGtinPresentacion(String gtin) {
        if (gtin.length() == 6)
            gtin = "0" + gtin;
        else if (gtin.length() == 11)
            gtin = "0" + gtin;
        this.gtinPresentacion = gtin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getContenidoNeto() {
        return contenidoNeto;
    }

    public void setContenidoNeto(BigDecimal contenidoNeto) {
        this.contenidoNeto = contenidoNeto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(BigDecimal profundidad) {
        this.profundidad = profundidad;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public DateTime getSuspendidoDesde() {
        return this.suspendidoDesde;
    }

    public void setSuspendidoDesde(DateTime suspendidoDesde) {
        this.suspendidoDesde = suspendidoDesde;
    }

    public DateTime getSuspendidoHasta() {
        return this.suspendidoHasta;
    }

    public void setSuspendidoHasta(DateTime suspendidoHasta) {
        this.suspendidoHasta = suspendidoHasta;
    }

    @JsonIgnore
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getSempresa() {
        return sempresa;
    }

    public void setSempresa(String sempresa) {
        this.sempresa = sempresa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getScategoria() {
        return scategoria;
    }

    public void setScategoria(String scategoria) {
        this.scategoria = scategoria;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    public String getSpresentacion() {
        return spresentacion;
    }

    public void setSpresentacion(String spresentacion) {
        this.spresentacion = spresentacion;
    }

    public Set<Empaque> getEmpaques() {
        return empaques;
    }

    public void setEmpaques(Set<Empaque> empaques) {
        this.empaques = empaques;
    }

    public Set<String> getSempaques() {
        return sempaques;
    }

    public void setSempaques(Set<String> sempaques) {
        this.sempaques = sempaques;
    }

    public Set<ListaDeVenta> getListasDeVentas() {
        return listasDeVentas;
    }

    public void setListasDeVentas(Set<ListaDeVenta> listasDeVentas) {
        this.listasDeVentas = listasDeVentas;
    }

    public Set<String> getSlistasDeVentas() {
        return slistasDeVentas;
    }

    public void setSlistasDeVentas(Set<String> slistasDeVentas) {
        this.slistasDeVentas = slistasDeVentas;
    }

    @JsonIgnore
    public Set<Empresa> getEmpresasConVisibilidad() {
        return empresasConVisibilidad;
    }

    public void setEmpresasConVisibilidad(Set<Empresa> empresasConVisibilidad) {
        this.empresasConVisibilidad = empresasConVisibilidad;
    }

    public Set<String> getSempresasConVisibilidad() {
        return sempresasConVisibilidad;
    }

    public void setSempresasConVisibilidad(Set<String> sempresasConVisibilidad) {
        this.sempresasConVisibilidad = sempresasConVisibilidad;
    }

    @JsonIgnore
    public Set<Grupo> getGruposConVisibilidad() {
        return gruposConVisibilidad;
    }

    public void setGruposConVisibilidad(Set<Grupo> gruposConVisibilidad) {
        this.gruposConVisibilidad = gruposConVisibilidad;
    }

    public Set<String> getSgruposConVisibilidad() {
        return sgruposConVisibilidad;
    }

    public void setSgruposConVisibilidad(Set<String> sgruposConVisibilidad) {
        this.sgruposConVisibilidad = sgruposConVisibilidad;
    }

    public boolean isEskitPromocional() {
        return eskitPromocional;
    }

    public void setEskitPromocional(boolean eskitPromocional) {
        this.eskitPromocional = eskitPromocional;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @JsonIgnore
    public boolean isNotCoda() {
        return !"Coda".equals(marca);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (other.getId().equals(this.getId()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "{" + " cpp='" + getCpp() + "'" + ", gtin='" + getGtin() + "'" + ", gtinPresentacion='"
                + getGtinPresentacion() + "'" + ", descripcion='" + getDescripcion() + "'" + ", mercadoObjetivo='"
                + getMercadoObjetivo() + "'" + ", unidadMedidaPesoBruto='" + getUnidadMedidaPesoBruto() + "'"
                + ", nivelMinimoVenta='" + getNivelMinimoVenta() + "'" + ", marca='" + getMarca() + "'"
                + ", contenidoNeto='" + getContenidoNeto() + "'" + ", unidadMedida='" + getUnidadMedida() + "'"
                + ", paisOrigen='" + getPaisOrigen() + "'" + ", foto='" + getFoto() + "'" + ", alto='" + getAlto() + "'"
                + ", ancho='" + getAncho() + "'" + ", profundidad='" + getProfundidad() + "'" + ", pesoBruto='"
                + getPesoBruto() + "'" + ", esPromo='" + isEsPromo() + "'" + ", esPrivado='" + isEsPrivado() + "'"
                + ", visibilidadPorGrupo='" + isVisibilidadPorGrupo() + "'" + ", esPublico='" + isEsPublico() + "'"
                + ", suspendidoDesde='" + getSuspendidoDesde() + "'" + ", suspendidoHasta='" + getSuspendidoHasta()
                + "'" + ", empresa='" + getEmpresa() + "'" + ", categoria='" + getCategoria() + "'" + ", presentacion='"
                + getPresentacion() + "'" + ", empaques='" + getEmpaques() + "'" + ",empresasConVisibilidad='"
                + getEmpresasConVisibilidad() + "'" + ", gruposConVisibilidad='" + getGruposConVisibilidad() + "'"
                + "}";
    }

    public void copy(Producto producto) {
        this.alto = producto.getAlto();
        this.ancho = producto.getAncho();
        this.categoria = producto.getCategoria();
        this.contenidoNeto = producto.getContenidoNeto();
        this.cpp = producto.getCpp();
        this.descripcion = producto.getDescripcion();
        if (this.empaques.size() > 0 && producto.getEmpaques().size() > 0) {
            Empaque empaque = (Empaque) this.empaques.toArray()[0];
            Empaque newEmpaque = (Empaque) producto.getEmpaques().toArray()[0];
            empaque.setGtin(newEmpaque.getGtin());
            empaque.setPresentacion(newEmpaque.getPresentacion());
            empaque.setCantidad(newEmpaque.getCantidad());
            this.empaques.toArray()[0] = empaque;
        } else if (producto.getEmpaques().size() > 0) {
            this.empaques = producto.getEmpaques();
        }
        if (producto.getFoto() != null && !producto.getFoto().equals(""))
            this.foto = producto.getFoto();
        this.gtin = producto.getGtin();
        this.marca = producto.getMarca();
        this.paisOrigen = producto.getPaisOrigen();
        this.pesoBruto = producto.getPesoBruto();
        this.presentacion = producto.getPresentacion();
        this.profundidad = producto.getProfundidad();
        this.unidadMedida = producto.getUnidadMedida();
        this.esPromo = producto.getEsPromo();
        if (this.esPrivado && !this.eliminado) {
            if (producto.getEsPublico()) {
                this.esPrivado = false;
                this.esPublico = true;
                if (this.getGruposConVisibilidad() != null) {
                    this.getGruposConVisibilidad().clear();
                }
                if (this.getEmpresasConVisibilidad() != null) {
                    this.getEmpresasConVisibilidad().clear();
                }
            } else if (producto.getEsPrivado()) {
                if (producto.gruposConVisibilidad != null && producto.gruposConVisibilidad.size() > 0) {
                    this.gruposConVisibilidad.addAll(producto.gruposConVisibilidad);
                    this.sgruposConVisibilidad.addAll(producto.sgruposConVisibilidad);
                } else if (producto.empresasConVisibilidad != null && producto.empresasConVisibilidad.size() > 0) {
                    this.empresasConVisibilidad.addAll(producto.empresasConVisibilidad);
                    this.sempresasConVisibilidad.addAll(producto.sempresasConVisibilidad);
                }
            }
        } else if (this.eliminado) {
            this.esPrivado = false;
            this.esPublico = false;
        }

        if (this.gtin.length() == 6)
            gtin = "0" + gtin;
        else if (gtin.length() == 11)
            gtin = "0" + gtin;
        this.gtinPresentacion = producto.getGtinPresentacion();
        this.mercadoObjetivo = producto.getMercadoObjetivo();
        this.nivelMinimoVenta = producto.getNivelMinimoVenta();
        if (this.pallet != null && producto.getPallet() != null) {
            this.pallet.setAlto(producto.getPallet().getAlto());
            this.pallet.setAncho(producto.getPallet().getAncho());
            this.pallet.setProfundidad(producto.getPallet().getProfundidad());
            this.pallet.setUnidadesVenta(producto.getPallet().getUnidadesVenta());
            this.pallet.setCajas(producto.getPallet().getCajas());
            this.pallet.setCamadas(producto.getPallet().getCamadas());
        } else if (producto.getPallet() != null) {
            this.pallet = producto.getPallet();
        }
        this.unidadMedidaPesoBruto = producto.getUnidadMedidaPesoBruto();
        this.suspendidoDesde = producto.getSuspendidoDesde();
        this.suspendidoHasta = producto.getSuspendidoHasta();
    }

    @Override
    public void inicializarLinkData() {
        /*
         * Link self = Link.fromUri("/productos/empresas/" + this.empresa.getId() + "/"
         * + this.id).rel("self")
         * .title("Obtener Producto").type("GET").param("filtros",
         * "{cpp: 'p.cpp', marca: 'p.marca', linea: 'p.categoria.padre.nombre', division: 'p.categoria.nombre'}"
         * ).param("sort",
         * "{asc: 'campoParaOrdenar', desc: -campoParaOrdenar}").build();
         */
        // this.links.add(self);
    }
}
