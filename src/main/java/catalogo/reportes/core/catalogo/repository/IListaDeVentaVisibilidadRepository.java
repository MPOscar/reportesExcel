package catalogo.reportes.core.catalogo.repository;

import catalogo.reportes.core.catalogo.entity.ListaDeVentaVisibilidad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IListaDeVentaVisibilidadRepository extends MongoRepository<ListaDeVentaVisibilidad, String> {
    public ListaDeVentaVisibilidad findFirstByOldId(long oldId);
}
