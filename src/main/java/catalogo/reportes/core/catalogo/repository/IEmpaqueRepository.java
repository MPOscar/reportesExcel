package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.Empaque;

import java.util.Optional;

public interface IEmpaqueRepository extends MongoRepository<Empaque, String> {
    public Empaque findFirstByOldId(long oldId);
    public Optional<Empaque> findByIdAndEmpresa(String id, Empresa empresa);
    public Optional<Empaque> findBySidAndSempresa(String id, String sidEmpresa);
}
