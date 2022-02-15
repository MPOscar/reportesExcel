package catalogo.reportes.core.afiliados.afiliadosRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import common.rondanet.clasico.core.afiliados.afiliadosModels.EmpresaId;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IAfiliadosEmpresaRepository extends JpaRepository<Empresa, EmpresaId> {

    @Transactional
    @Query(value = "select * from Emp e where e.ruc != '' and e.fbaja is null and not exists (select * from emp emp2 where e.EMPRESA != emp2.Empresa and e.ruc = emp2.ruc and e.ruc != '' and emp2.fbaja is null  and e.falta > emp2.falta) and e.falta > ?1 rows ?2 to ?3 ", nativeQuery = true)
    List<Empresa> findAll(Date fecha, int limit, int offet);

    @Transactional
    @Query(value = "SELECT count(*) FROM EMP e AND e.fbaja is null and e.falta > ?1", nativeQuery = true)
    int getTotal(Date fecha);

    @Transactional
    @Query(value = "SELECT * FROM EMP e WHERE e.EMPRESA = ?1", nativeQuery = true)
    Empresa obtenerEmpresaPorCodigoInterno(String codigoInterno);


    @Transactional
    @Query(value = "select * from Emp e where e.ruc = ?1 and not exists (select * from emp emp2 where e.EMPRESA != emp2.Empresa and e.ruc = emp2.ruc and emp2.fbaja is null  and e.falta > emp2.falta)", nativeQuery = true)
    Empresa findByRut(String rutEmpresa);

}