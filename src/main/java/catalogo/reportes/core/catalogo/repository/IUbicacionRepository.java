package catalogo.reportes.core.catalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.Ubicacion;

public interface IUbicacionRepository extends MongoRepository<Ubicacion, String> {
    public Ubicacion findFirstByOldId(long oldId);
}
