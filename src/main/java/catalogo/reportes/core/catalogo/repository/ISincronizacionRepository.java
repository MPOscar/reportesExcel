package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.Sincronizacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ISincronizacionRepository extends MongoRepository<Sincronizacion, String> {

    public Optional<Sincronizacion> findFirstByColleccion(String coleccion);

}
