package catalogo.reportes.core.catalogo.db;

import catalogo.reportes.core.ediservices.ediservicesRepositories.IEdiServiceUsuarioRepository;
import catalogo.reportes.core.utils.IEmailHelper;
import com.google.common.hash.Hashing;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.catalogo.entity.Rol;
import catalogo.reportes.core.catalogo.entity.Usuario;
import catalogo.reportes.core.catalogo.entity.UsuarioEmpresa;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import catalogo.reportes.core.pedidos.services.interfaces.IDatosAfiliadosService;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SincronizadorEmpresaDAO {

    Logger logger = LogManager.getLogger(SincronizadorEmpresaDAO.class);

    @Autowired
    IEmailHelper emailHelper;

    @Autowired
    EmpresasDAO empresasDAO;

    @Autowired
    UsuariosDAO usuariosDAO;

    @Autowired
    UsuarioEmpresaDAO usuarioEmpresaDAO;

    @Autowired
    RolesDAO rolesDAO;

    IDatosAfiliadosService datosAfiliadosService;

    @Autowired
    IEdiServiceUsuarioRepository ediServiceUsuarioRepository;

    ReportesConfiguration configuration;

    List<Rol> rolesEmpresa = new ArrayList<Rol>();

    public SincronizadorEmpresaDAO(ReportesConfiguration configuration, EmpresasDAO empresasDAO, UsuariosDAO usuariosDAO,
                                   UsuarioEmpresaDAO usuarioEmpresaDAO, RolesDAO rolesDAO, IDatosAfiliadosService datosAfiliadosService) {
        this.configuration = configuration;
        this.empresasDAO = empresasDAO;
        this.usuariosDAO = usuariosDAO;
        this.datosAfiliadosService = datosAfiliadosService;
        this.usuarioEmpresaDAO = usuarioEmpresaDAO;
        this.rolesDAO = rolesDAO;
        this.rolesEmpresa = this.rolesDAO.findAll();
    }

    public void sincronizarEmpresa(Empresa empresaASincronizar) {

        if(empresaASincronizar.getRuc() != null && !empresaASincronizar.getRuc().equals("") && empresaASincronizar.getFechaDeBaja() == null && this.configuration.empresasASincronizar(empresaASincronizar.getRuc())){
            try {
                Optional<catalogo.reportes.core.catalogo.entity.Empresa> optionalEmpresa = empresasDAO.findByKey("rut", empresaASincronizar.getRuc());
                catalogo.reportes.core.catalogo.entity.Empresa empresa = new catalogo.reportes.core.catalogo.entity.Empresa();
                if(optionalEmpresa.isPresent()){
                    empresa = optionalEmpresa.get();
                }
                empresa = actualizarEmpresa(empresa, empresaASincronizar);
                actualizarUsuario(empresa, empresaASincronizar);
            } catch (Exception e) {
                logger.log(Level.INFO, "Ocurrió un error al sincronizar las empresas. Error: " + e.getMessage());
                this.emailHelper.sendErrorEmail("SincronizadorEmpresaDAO", e);
            }
        }
    }


    public catalogo.reportes.core.catalogo.entity.Empresa actualizarEmpresa(catalogo.reportes.core.catalogo.entity.Empresa empresa, Empresa empresaASincronizar){
        if(empresaASincronizar.getRuc() != null && !empresaASincronizar.getRuc().equals("")){
            empresa.setRut(empresaASincronizar.getRuc());
        } else {
            empresa.setRut(empresaASincronizar.getId().getCodigoInternoEmpresa());
        }

        empresa.setCodigoInterno(empresaASincronizar.getId().getCodigoInternoEmpresa());
        empresa.setRazonSocial(empresaASincronizar.getRazonSocial());
        empresa.setNombre(empresaASincronizar.getNombreEmpresa());
        empresa.setEmail(empresaASincronizar.getEmail());
        if(empresaASincronizar.getFechaDeBaja() == null){
            empresa.setActivo(true);
            empresa.setEliminado(false);
        }else{
            empresa.setActivo(false);
            empresa.setEliminado(true);
        }
        empresa.setValidado(true);
        empresa.setEliminado(false);
        empresa.setFechaCreacion(new DateTime(empresaASincronizar.getFechaIngreso()));
        empresa.setFechaEdicion(new DateTime());
        return empresasDAO.save(empresa);
    }

    public void actualizarUsuario(catalogo.reportes.core.catalogo.entity.Empresa empresa, Empresa empresaASincronizar){
        try {
            Optional<Usuario> optionalUsuario = Optional.empty();
            String email = "";
            if (empresaASincronizar.getEmail() != null && !empresaASincronizar.getEmail().equals("") && !empresaASincronizar.getEmail().contains("No tienen")) {
                if (empresaASincronizar.getEmail().indexOf(";") > -1) {
                    email = empresaASincronizar.getEmail().substring(0, empresaASincronizar.getEmail().indexOf(";"));
                } else {
                    email = empresaASincronizar.getEmail();
                }
            } else {
                email = empresaASincronizar.getId().getCodigoInternoEmpresa();
            }

            optionalUsuario = usuariosDAO.findByUsuario(empresaASincronizar.getId().getCodigoInternoEmpresa());

            Usuario usuario = new Usuario();
            if (optionalUsuario.isPresent()) {
                usuario = optionalUsuario.get();
            } else {
                usuario.setUsuario(empresaASincronizar.getId().getCodigoInternoEmpresa());
                usuario.setEmail(email);
                usuario.setFechaCreacion(new DateTime(empresaASincronizar.getFechaIngreso()));
            }

            if (empresaASincronizar.getContrasenaRondanet() != null) {
                usuario.setValidado(true);
                usuario.setActivo(true);
                String password = datosAfiliadosService.desencriptarPassword(empresaASincronizar.getContrasenaRondanet());
                String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
                usuario.setContrasena(sha256hex);
            } else {
                usuario.setValidado(false);
                usuario.setActivo(false);
            }

            usuario = usuariosDAO.save(usuario);

            AgregarUsuarioEmpresa(empresa, usuario);

            actualizarUsuariosDeEmpresa(empresa, empresaASincronizar);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

    public void AgregarUsuarioEmpresa(catalogo.reportes.core.catalogo.entity.Empresa empresa, Usuario usuario){
        List<UsuarioEmpresa> usuariosEmpresa = usuarioEmpresaDAO.findByIdUsuarioAndIdEmpresa(usuario.getId(), empresa.getId());
        UsuarioEmpresa usuarioEmpresa = usuariosEmpresa.size() > 0 ? usuariosEmpresa.get(0) : new UsuarioEmpresa();
        if(usuariosEmpresa.size() > 1){
            for (UsuarioEmpresa usuarioEmp: usuariosEmpresa.subList(1, usuariosEmpresa.size())) {
                usuarioEmpresaDAO.delete(usuarioEmp);
            }

        } else if(usuariosEmpresa.size() == 0) {
            usuarioEmpresa.setEmpresa(empresa);
            usuarioEmpresa.setSempresa(empresa.getId());
            usuarioEmpresa.setUsuario(usuario);
            usuarioEmpresa.setSusuario(usuario.getId());
        }
        usuarioEmpresa.getRoles().clear();
        usuarioEmpresa.getSroles().clear();
        for (Rol rol: rolesEmpresa) {
            usuarioEmpresa.getRoles().add(rol);
            usuarioEmpresa.getSroles().add(rol.getId());
        }

        usuarioEmpresa.setActivo(usuario.getActivo());
        usuarioEmpresa.setValidado(usuario.getValidado());
        usuarioEmpresaDAO.save(usuarioEmpresa);
    }

    public void actualizarUsuariosDeEmpresa(catalogo.reportes.core.catalogo.entity.Empresa empresa, Empresa empresaASincronizar){
        List<common.rondanet.clasico.core.ediservices.ediservicesModels.Usuario> usuariosEdiService = ediServiceUsuarioRepository.obtenerTodosLosUsuarioEmpresa(empresaASincronizar.getId().getCodigoInternoEmpresa());
        for (common.rondanet.clasico.core.ediservices.ediservicesModels.Usuario usuarioEdi: usuariosEdiService) {
            eliminarUsuariosDuplicados(usuarioEdi.getId().getUsuId(), empresa);
            Optional<Usuario> optionalUsuario = usuariosDAO.findByUsuario(usuarioEdi.getId().getUsuId());
            Usuario usuario = new Usuario();
            if (optionalUsuario.isPresent()) {
                usuario = optionalUsuario.get();
            } else {
                usuario.setNombre(usuarioEdi.getUsuario());
                usuario.setUsuario(usuarioEdi.getId().getUsuId());
                usuario.setEmail(usuarioEdi.getId().getUsuId());
                usuario.setFechaCreacion(new DateTime(empresaASincronizar.getFechaIngreso()));
            }

            if (usuarioEdi.getContrasena() != null) {
                usuario.setValidado(true);
                usuario.setActivo(true);
                String password = datosAfiliadosService.desencriptarPassword(usuarioEdi.getContrasena());
                String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
                usuario.setContrasena(sha256hex);
            }

            usuario = usuariosDAO.save(usuario);

            AgregarUsuarioEmpresa(empresa, usuario);
        }
    }

    public void eliminarUsuariosDuplicados(String nombreUsuario, catalogo.reportes.core.catalogo.entity.Empresa empresa){
        List<Usuario> usuarios = usuariosDAO.findAllByUsuario(nombreUsuario);
        if(usuarios.size() > 1) {
            for (Usuario usuario : usuarios.subList(1, usuarios.size())) {
                List<UsuarioEmpresa> usuarioEmpresas = usuarioEmpresaDAO.findByIdUsuarioAndIdEmpresa(usuario.getId(), empresa.getId());
                for (UsuarioEmpresa usuarioEmpresa: usuarioEmpresas) {
                    if(usuarioEmpresa != null){
                        usuarioEmpresaDAO.delete(usuarioEmpresa);
                    }
                }
                usuariosDAO.delete(usuario);
            }
        }
    }
}
