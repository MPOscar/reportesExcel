package catalogo.reportes;

import catalogo.reportes.core.catalogo.db.EmpresasDAO;
import catalogo.reportes.core.catalogo.db.UbicacionesDAO;
import catalogo.reportes.core.catalogo.entity.Empresa;
import catalogo.reportes.core.catalogo.entity.Param;
import catalogo.reportes.core.catalogo.entity.Ubicacion;
import catalogo.reportes.core.catalogo.repository.IParamRepository;
import catalogo.reportes.core.catalogo.utils.Propiedades;
import catalogo.reportes.core.utils.DespliegueConfiguration;
import catalogo.reportes.core.utils.mandrill.MandrillConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ReportesConfiguration {
    @Autowired
    IParamRepository paramRepository;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    UbicacionesDAO ubicacionesDAO;

    private DespliegueConfiguration configuracionDespliegue = new DespliegueConfiguration();

    public DespliegueConfiguration getConfiguracionDespliegue() {
        return this.configuracionDespliegue;
    }

    public void setConfiguracionDespliegue(DespliegueConfiguration despliegue) {
        this.configuracionDespliegue = despliegue;
    }

    @Value("${RUT_DE_EMPRESAS_A_SINCRONIZAR}")
    public String sempresasASincronizar;

    @Value("${RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD}")
    public String sempresasASincronizarVisibilidad;

    @Value("${RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS}")
    public String sempresasASincronizarHaciaAtras;

    private List<String> empresasASincronizar;

    private List<String> empresasASincronizarVisibilidad;

    private List<String> rutEmpresasASincronizarVisibilidad;

    private List<String> empresasASincronizarHaciaAtras;

    public List<String> empresasASincronizarHaciaAtrasSeleccion;

    public List<String> getEmpresasASincronizar() {
        return empresasASincronizar;
    }

    public void setEmpresasASincronizar(List<String> empresasASincronizar) {
        this.empresasASincronizar = empresasASincronizar;
    }

    public boolean empresasASincronizar(String rut) {
        Param paramSeleccion = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE__EMPRESAS_A_SINCRONIZAR_OPCION);
        boolean tipoSeleccion = true;
        if(paramSeleccion != null && paramSeleccion.getValor() != null){
            tipoSeleccion = !paramSeleccion.getValor().equals("false");
        }
        return tipoSeleccion ? this.empresasASincronizar.indexOf(rut) > -1 : this.empresasASincronizar.indexOf(rut) == -1;
    }

    public boolean empresasASincronizarVisibilidad(String gln){
        Param paramSeleccion = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD_OPCION);
        boolean tipoSeleccion = true;
        if(paramSeleccion != null && paramSeleccion.getValor() != null){
            tipoSeleccion = !paramSeleccion.getValor().equals("false");
        }
        return tipoSeleccion ? this.empresasASincronizarVisibilidad.indexOf(gln) > -1 : this.empresasASincronizarVisibilidad.indexOf(gln) == -1;
    }

    public boolean rutEmpresasASincronizarVisibilidad(String gln){
        Param paramSeleccion = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD_OPCION);
        boolean tipoSeleccion = true;
        if(paramSeleccion != null && paramSeleccion.getValor() != null){
            tipoSeleccion = !paramSeleccion.getValor().equals("false");
        }
        return tipoSeleccion ? this.rutEmpresasASincronizarVisibilidad.indexOf(gln) > -1 : this.rutEmpresasASincronizarVisibilidad.indexOf(gln) == -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportesConfiguration)) return false;
        ReportesConfiguration that = (ReportesConfiguration) o;
        return Objects.equals(getConfiguracionDespliegue(), that.getConfiguracionDespliegue());
    }

    @PostConstruct
    public void init() {
        createBucketUrlParam();
        cargarConfiguracion();
        cargarConfiguracionEmpresas();
    }

    public void cargarConfiguracion() {

        Param despliqueFrontend = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_FRONTEND).orElse(null);
        Param despliqueTerceros = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_TERCEROS).orElse(null);
        Param despliqueCors = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_CORS).orElse(null);
        Param despliqueBucket = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_BUCKET).orElse(null);
        Param despliqueBucketUrl = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_BUCKET_URL).orElse(null);

        Param s3Id = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_S3_S3Id).orElse(null);
        Param s3Api = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_S3_S3APIKEY).orElse(null);

        Param mandrillFromEmail = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_MANDRILL_MAIL).orElse(null);
        Param mandrillApiKey = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_MANDRILL_APIKEY).orElse(null);
        Param mandrillUrlFronted = paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_MANDRILL_URL).orElse(null);


        MandrillConfiguration mandrillConfiguration = new MandrillConfiguration(mandrillFromEmail.getValor(), mandrillApiKey.getValor(),mandrillUrlFronted.getValor());

        DespliegueConfiguration despliegueConfiguration = new DespliegueConfiguration(
                Boolean.valueOf(despliqueFrontend.getValor()),
                Boolean.valueOf(despliqueTerceros.getValor()),
                despliqueCors.getValor(),
                despliqueBucket.getValor(),
                despliqueBucketUrl.getValor(),
                mandrillConfiguration);

        this.configuracionDespliegue = despliegueConfiguration;
    }

    void createBucketUrlParam(){
        Optional<Param> optionalParamBucket = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_BUCKET);
        Optional<Param> optionalParamBucketUrl = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.DW_DESPLIEGUE_BUCKET_URL);
        Param param = new Param();
        if (optionalParamBucket.isPresent()) {
            param.setNombre(Propiedades.DW_DESPLIEGUE_BUCKET_URL);
            if(optionalParamBucketUrl.isPresent()){
                param = optionalParamBucketUrl.get();
            } else {
                param.setFechaCreacion();
            }
            param.setFechaEdicion();
            if(optionalParamBucket.get().getValor().contains("desarrollo")){
                param.setValor("https://s3.us-east-2.amazonaws.com/rondanet.recursos.desarrollo");
            } else if(optionalParamBucket.get().getValor().contains("test")){
                param.setValor("https://s3.us-east-2.amazonaws.com/rondanet.recursos.test");
            }  else if(optionalParamBucket.get().getValor().contains("sqa")){
                param.setValor("https://s3.us-east-2.amazonaws.com/rondanet.recursos.sqa");
            } else {
                param.setValor("https://s3.us-east-2.amazonaws.com/rondanet.recursos");
            }
            param = paramRepository.save(param);
            param.setSId(param.getId());
            paramRepository.save(param);
        }

        Optional<Param> optionalEmpresasANoSincronizar = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR);
        if(!optionalEmpresasANoSincronizar.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR, this.sempresasASincronizar);
        }

        Optional<Param> optionalEmpresasANoSincronizarSeleccion = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE__EMPRESAS_A_SINCRONIZAR_OPCION);
        if(!optionalEmpresasANoSincronizarSeleccion.isPresent()){
            saveParam(Propiedades.RUT_DE__EMPRESAS_A_SINCRONIZAR_OPCION, "true");
        }

        Optional<Param> optionalEmpresasASincronizarVisibilidad = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD);
        if(!optionalEmpresasASincronizarVisibilidad.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD, this.sempresasASincronizarVisibilidad);
        }

        Optional<Param> optionalEmpresasASincronizarVisibilidadSeleccion = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD_OPCION);
        if(!optionalEmpresasASincronizarVisibilidadSeleccion.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD_OPCION, "true");
        }

        Optional<Param> optionalEmpresasASincronizarHaciaAtras = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS);
        if(!optionalEmpresasASincronizarHaciaAtras.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS, this.sempresasASincronizarHaciaAtras);
        }

        Optional<Param> optionalEmpresasASincronizarHaciaAtrasSeleccion = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_OPCION);
        if(!optionalEmpresasASincronizarHaciaAtrasSeleccion.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_OPCION, "true");
        }

        Optional<Param> actualizarConfiguracion = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.ACTUALIZAR_CONFIGURACION);
        if(!actualizarConfiguracion.isPresent()){
            saveParam(Propiedades.ACTUALIZAR_CONFIGURACION, "false");
        }
    }

    public void actualizarConfiguracionEmpresas(){
        Param actualizarConfiguracion = this.paramRepository.findFirstByNombre(Propiedades.ACTUALIZAR_CONFIGURACION);
        if(actualizarConfiguracion != null && actualizarConfiguracion.getValor() != null && actualizarConfiguracion.getValor().equals("true")){
            cargarConfiguracionEmpresas();
        }
    }

    public void cargarConfiguracionEmpresas(){
        Param param = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR);
        String arrayEmpresasANoSincronizar[] = param.getValor().split(",");
        this.empresasASincronizar = Arrays.asList(arrayEmpresasANoSincronizar);
        cargarEmpresasASincronizarVisibilidad();
        cargarEmpresasASincronizarHaciaAtrasSeleccion();
        cargarEmpresasASincronizarHaciaAtras();
    }

    public void cargarEmpresasASincronizarVisibilidad(){
        Param param = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD);
        String arrayEmpresasASincronizarVisibilidad[] = param.getValor().split(",");
        this.empresasASincronizarVisibilidad = new ArrayList<>();
        this.rutEmpresasASincronizarVisibilidad = Arrays.asList(arrayEmpresasASincronizarVisibilidad);
        for (String rutEmpresa: rutEmpresasASincronizarVisibilidad) {
            Optional<Empresa> empresa = empresasDAO.findByRutEmpresa(rutEmpresa);
            if(empresa.isPresent()){
                List<Ubicacion> ubicaciones = ubicacionesDAO.buscarUbicacionesDeEmpresa(empresa.get());
                for (Ubicacion ubicacion: ubicaciones) {
                    if(ubicacion != null) {
                        this.empresasASincronizarVisibilidad.add(ubicacion.getCodigo());
                    }
                }
            }
        }

        Optional<Param> optionalEmpresasASincronizarVisibilidad = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.GLN_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD);
        if(!optionalEmpresasASincronizarVisibilidad.isPresent()){
            saveParam(Propiedades.GLN_DE_EMPRESAS_A_SINCRONIZAR_VISIBILIDAD, String.join(",", this.empresasASincronizarVisibilidad));
        }else{
            optionalEmpresasASincronizarVisibilidad.get().setValor(String.join(",", this.empresasASincronizarVisibilidad));
            this.paramRepository.save(optionalEmpresasASincronizarVisibilidad.get());
        }
    }

    public void cargarEmpresasASincronizarHaciaAtras(){
        Param param = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_SELECCION);
        String arrayEmpresasASincronizarHaciaAtras[] = param.getValor().split(",");
        this.empresasASincronizarHaciaAtrasSeleccion = Arrays.asList(arrayEmpresasASincronizarHaciaAtras);
    }

    public void cargarEmpresasASincronizarHaciaAtrasSeleccion() {
        Param paramSeleccion = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_OPCION);
        Param param = this.paramRepository.findFirstByNombre(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS);
        boolean tipoSeleccion = true;
        if(paramSeleccion != null && paramSeleccion.getValor() != null) {
            tipoSeleccion = !paramSeleccion.getValor().equals("false");
        }
        if( param!= null) {
            String arrayEmpresasASincronizarVisibilidad[] = param.getValor().split(",");
            this.empresasASincronizarHaciaAtras = Arrays.asList(arrayEmpresasASincronizarVisibilidad);
        }
        if(!tipoSeleccion) {
            cargarTodasLasEmpresasASincronizarHaciaAtras();
        } else {
            this.empresasASincronizarHaciaAtrasSeleccion = this.empresasASincronizarHaciaAtras;
        }

        Optional<Param> optionalEmpresasASincronizarHaciaAtrasSeleccion = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_SELECCION);
        if(!optionalEmpresasASincronizarHaciaAtrasSeleccion.isPresent()){
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_SELECCION, "");
        } else {
            optionalEmpresasASincronizarHaciaAtrasSeleccion.get().setValor(param.getValor());
            this.paramRepository.save(optionalEmpresasASincronizarHaciaAtrasSeleccion.get());
        }
    }

    public void cargarTodasLasEmpresasASincronizarHaciaAtras() {
        List<Empresa> empresas = empresasDAO.getAllIdEmpresas();
        this.empresasASincronizarHaciaAtrasSeleccion = new ArrayList<>();
        for (Empresa empresa: empresas){
            empresa = empresasDAO.findById(empresa.getId());
            if(empresa != null){
                if(this.empresasASincronizarHaciaAtras.indexOf(empresa.getRut()) == -1){
                    this.empresasASincronizarHaciaAtrasSeleccion.add(empresa.getRut());
                }
            }
        }
        String empresasString = String.join("," , this.empresasASincronizarHaciaAtrasSeleccion);
        Optional<Param> optionalEmpresasASincronizarCargadasPorElSincronizador = this.paramRepository.findByNombreAndEliminadoIsFalse(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_SELECCION);
        if(!optionalEmpresasASincronizarCargadasPorElSincronizador.isPresent()) {
            saveParam(Propiedades.RUT_DE_EMPRESAS_A_SINCRONIZAR_HACIA_ATRAS_SELECCION, empresasString);
        } else {
            optionalEmpresasASincronizarCargadasPorElSincronizador.get().setValor(empresasString);
            this.paramRepository.save(optionalEmpresasASincronizarCargadasPorElSincronizador.get());
        }
    }

    public void saveParam(String nombre, String value) {
        Param param = new Param();
        param.setNombre(nombre);
        param.setValor(value);
        param = paramRepository.save(param);
        param.setSId(param.getId());
        this.paramRepository.save(param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConfiguracionDespliegue());
    }

    @Override
    public String toString() {
        return "CatalogoConfiguration{" +
                ", configuracionDespliegue=" + configuracionDespliegue +
                '}';
    }

}
