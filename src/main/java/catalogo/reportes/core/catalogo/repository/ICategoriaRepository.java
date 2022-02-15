package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.Categoria;

import java.util.List;

public interface ICategoriaRepository extends MongoRepository<Categoria, String> {
    public Categoria findFirstByOldId(long oldId);
    public List<Categoria> findAllByEmpresa(Empresa empresa);
    public List<Categoria> findAllBySempresa(String sidEmpresa);
}
