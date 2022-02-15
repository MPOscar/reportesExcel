package catalogo.reportes.reportesExcel.core.services.implementations;

import catalogo.reportes.ReportesConfiguration;
import catalogo.reportes.core.afiliados.afiliadosServices.interfaces.IAfiliadosEmpresaService;
import catalogo.reportes.core.catalogo.db.EmpresasDAO;
import catalogo.reportes.core.catalogo.db.ListasDeVentaDAO;
import catalogo.reportes.core.catalogo.db.UbicacionesDAO;
import catalogo.reportes.core.catalogo.entity.ListaDeVenta;
import catalogo.reportes.core.catalogo.entity.Ubicacion;
import catalogo.reportes.core.catalogoViejo.catalogoServices.interfaces.ICatalogoViejoProductosService;
import catalogo.reportes.core.pedidos.pedidosDAO.DatosAfiliadosDAO;
import catalogo.reportes.core.pedidos.pedidosDAO.OrdenDeCompraFinalizadaDAO;
import catalogo.reportes.core.pedidos.pedidosEntity.OrdenDeCompraFinalizada;
import catalogo.reportes.reportesExcel.core.services.interfaces.IReportesExcelCatalogoService;
import catalogo.reportes.reportesExcel.core.services.interfaces.IReportesExcelCatalogoViejoService;
import catalogo.reportes.reportesExcel.core.utils.ExcelUtilityReporteEmpresas;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportesExcelCatalogoService implements IReportesExcelCatalogoService {

	@Autowired
	ICatalogoViejoProductosService catalogoViejoProductosService;

	@Autowired
	IAfiliadosEmpresaService afiliadosEmpresaService;

	@Autowired
	ExcelUtilityReporteEmpresas excelUtilityReporteEmpresas;

	@Autowired
	EmpresasDAO empresasDAO;

	@Autowired
	UbicacionesDAO ubicacionesDAO;

	@Autowired
	ListasDeVentaDAO listasDeVentaDAO;

	@Autowired
	OrdenDeCompraFinalizadaDAO ordenDeCompraFinalizadaDAO;

	public ReportesExcelCatalogoService(ReportesConfiguration configuration) {

	}


	@Override
	public String reporteEmpresasQueRealizanPedidosEnLaPlataformaNueva(String desde, String hasta) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechaInicial = formatter.parseDateTime(desde);
		DateTime fechaFinal = formatter.parseDateTime(hasta);

		List<OrdenDeCompraFinalizada> empresasEmitiendoOrdenesDeCompra = ordenDeCompraFinalizadaDAO.obtenerEmpresasEmitiendoOrdenesDeCompra(fechaInicial, fechaFinal);

			XSSFWorkbook xSSFWorkbook = excelUtilityReporteEmpresas.obtenerExcelParaReporteEmpresas();
			for (OrdenDeCompraFinalizada ordenDeCompraFinalizada : empresasEmitiendoOrdenesDeCompra) {
				catalogo.reportes.core.catalogo.entity.Empresa empresaEmitiendo = empresasDAO.findById(ordenDeCompraFinalizada.getId());
				if(empresaEmitiendo != null) {
					xSSFWorkbook = excelUtilityReporteEmpresas.agregarEmpresasEmitiendoOrdenesDeCompra(xSSFWorkbook, empresaEmitiendo);
				}
			}
			crearReporte(xSSFWorkbook);
		return "Se ha generado el reporte";
	}

	@Override
	public String reporteEmpresasQueTienenListasDeVentaEnLaPlataformaNueva(String desde, String hasta) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime fechaInicial = formatter.parseDateTime(desde);
		DateTime fechaFinal = formatter.parseDateTime(hasta);

		List<String> empresasId = listasDeVentaDAO.obtenerTodasEmpresaQueTinenListasDeVentasActualizadas(fechaInicial);

		XSSFWorkbook xSSFWorkbook = excelUtilityReporteEmpresas.obtenerExcelParaReporteEmpresas();
		for (String empresaId : empresasId) {
			catalogo.reportes.core.catalogo.entity.Empresa empresaEmitiendo = empresasDAO.findById(empresaId);
			if(empresaEmitiendo != null) {
				xSSFWorkbook = excelUtilityReporteEmpresas.agregarEmpresasEmitiendoOrdenesDeCompra(xSSFWorkbook, empresaEmitiendo);
			}
		}
		crearReporte(xSSFWorkbook);
		return "Se ha generado el reporte";
	}

	public void crearReporte(XSSFWorkbook xSSFWorkbook){
		try {
			FileOutputStream out = new FileOutputStream(new File("reportes/reporteConListaDeVenta.xlsx"));
			xSSFWorkbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
