package catalogo.reportes.reportesExcel.api;

import catalogo.reportes.core.security.IAuthenticationFacade;
import catalogo.reportes.reportesExcel.core.services.interfaces.IReportesExcelCatalogoService;
import catalogo.reportes.reportesExcel.core.services.interfaces.IReportesExcelCatalogoViejoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reportesExcel")
public class ReportesExcelController {
  Logger logger = LogManager.getLogger(ReportesExcelController.class);

  private final IAuthenticationFacade authenticationFacade;

  private final IReportesExcelCatalogoViejoService reportesCatalogoViejoExcelService;

  @Autowired
  IReportesExcelCatalogoService reportesExcelService;

  public ReportesExcelController(
    IAuthenticationFacade authenticationFacade, IReportesExcelCatalogoViejoService reportesExcelService) {
    this.authenticationFacade = authenticationFacade;
    this.reportesCatalogoViejoExcelService = reportesExcelService;
  }

  @PostMapping("/reportesEmpresas")
  public ResponseEntity<String> reportesEmpresas() {
    this.reportesCatalogoViejoExcelService.reporteEmpresas();
    return ok("Se ha generado el reporte");
  }

  @PostMapping("/reportesEmpresasConProductosVisibles")
  public ResponseEntity<String> reportesEmpresasConProductosVisibles(@RequestParam(defaultValue = "") String rut) {
    this.reportesCatalogoViejoExcelService.reporteEmpresasConProductosVisibles(rut);
    return ok("Se ha generado el reporte");
  }

  @PostMapping("/reporteEmpresasQueRealizanPedidosEnLaPlataformaNueva")
  public ResponseEntity<String> reporteEmpresasQueRealizanPedidosEnLaPlataformaNueva() {
    this.reportesExcelService.reporteEmpresasQueRealizanPedidosEnLaPlataformaNueva("01-01-2020", "12-02-2022");
    return ok("Se ha generado el reporte");
  }

  @PostMapping("/reporteEmpresasQueTienenListasDeVentaEnLaPlataformaNueva")
  public ResponseEntity<String> reporteEmpresasQueTienenListasDeVentaEnLaPlataformaNueva() {
    this.reportesExcelService.reporteEmpresasQueTienenListasDeVentaEnLaPlataformaNueva("01-01-2020", "12-02-2022");
    return ok("Se ha generado el reporte");
  }


}