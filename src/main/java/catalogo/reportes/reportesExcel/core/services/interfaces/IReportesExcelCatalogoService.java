package catalogo.reportes.reportesExcel.core.services.interfaces;

public interface IReportesExcelCatalogoService {

    String reporteEmpresasQueRealizanPedidosEnLaPlataformaNueva(String desde, String hasta);

    String reporteEmpresasQueTienenListasDeVentaEnLaPlataformaNueva(String desde, String hasta);
}
