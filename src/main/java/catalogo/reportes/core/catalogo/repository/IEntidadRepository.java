package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.Entidad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEntidadRepository extends MongoRepository<Entidad, String> {
}
