package catalogo.reportes.core.catalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.Rol;

public interface IRolRepository extends MongoRepository<Rol, String> {
    public Rol findFirstByOldId(long oldId);
}
