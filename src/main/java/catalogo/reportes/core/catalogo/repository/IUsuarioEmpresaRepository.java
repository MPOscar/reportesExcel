package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.Usuario;
import catalogo.reportes.core.catalogo.entity.UsuarioEmpresa;

import java.util.List;
import java.util.Optional;

public interface IUsuarioEmpresaRepository extends MongoRepository<UsuarioEmpresa, String> {


    public UsuarioEmpresa findFirstByOldId(long oldId);

    public Optional<UsuarioEmpresa> findById(String id);

    public List<UsuarioEmpresa> findByUsuarioAndEmpresa(Usuario usuario, Empresa empresa);

    public List<UsuarioEmpresa> findAllBySusuarioAndSempresa(String idUsuario, String idEmpresa);

    public List<UsuarioEmpresa> findAllByEmpresaAndEliminado(Empresa empresa, boolean eliminado);

    public List<UsuarioEmpresa> findByUsuario(Usuario usuario);

    public List<UsuarioEmpresa> findByUsuarioAndEliminado(Usuario usuario, boolean eliminado);

    public List<UsuarioEmpresa> findAllByEmpresa(Empresa empresa);

    public List<UsuarioEmpresa> findByUsuarioAndActivoAndValidado(Usuario usuario, boolean activo, boolean validado);

    public List<UsuarioEmpresa> findByUsuarioAndEmpresaAndActivoAndValidado(Usuario usuario, Empresa empresa, boolean activo, boolean validado);
}
