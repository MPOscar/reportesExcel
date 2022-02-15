package catalogo.reportes.core.catalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import catalogo.reportes.core.catalogo.entity.ListaDePrecio;

import java.util.List;

public interface IPrecioRepository extends MongoRepository<ListaDePrecio, String> {
    public void deleteAllByGlnListaDeVenta(String glnListaVenta);
    public List<ListaDePrecio> findAllByProductoCppAndGlnListaDeVenta(String productoCpp, String glnListaVenta);
}
