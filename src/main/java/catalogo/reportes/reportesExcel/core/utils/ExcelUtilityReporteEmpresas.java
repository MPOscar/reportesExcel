package catalogo.reportes.reportesExcel.core.utils;

import catalogo.reportes.reportesExcel.core.utils.resources.ExcelDocumentoColumna;
import common.rondanet.clasico.core.afiliados.afiliadosModels.Empresa;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ExcelUtilityReporteEmpresas {


    public ExcelUtilityReporteEmpresas() {

    }

    public XSSFWorkbook obtenerExcelParaReporteEmpresas() {
        XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
        try {

            File file = new File("excelParaReportes/reportes.xlsx");
            InputStream inputStream = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet worksheet = wb.getSheetAt(0);

            /*HashMap<String, List<ExcelDocumentoColumna>> excelDocumentoColumnas = obtenerColumnasParaExcel(ordenDeCompraExcel);
            Map.Entry<String, List<ExcelDocumentoColumna>> entry = excelDocumentoColumnas.entrySet().iterator().next();
            crearCabeceras(wb, worksheet, 0, entry.getValue(), entry.getKey());
            setHojaExcel(worksheet, ordenDeCompraExcel, 2);*/

            inputStream.close();
            xSSFWorkbook = wb;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return xSSFWorkbook;
    }

    public XSSFWorkbook agregarEmpresa(XSSFWorkbook xSSFWorkbook, Empresa empresa, String glnEmpresa, Date ultimaFechaDeActualizacion, int totalDeProductosConGTIN13, int totalDeProductosConGTIN14) {
        try {

            XSSFSheet worksheet = xSSFWorkbook.getSheetAt(0);
            List<ExcelDocumentoColumna> excelDocumentoColumnas =  ExcelDocumentoColumna.cabecerasExcelParaReporteEmpresa();
            // int row = crearCabeceras(xSSFWorkbook, worksheet, 0, excelDocumentoColumnas);
            int rowNumber = worksheet.getLastRowNum();
            rowNumber++;

            Row row = worksheet.createRow(rowNumber);

            Format formatter = new SimpleDateFormat("dd/MM/YYYY");
            String fechaDeActualizacion = formatter.format(ultimaFechaDeActualizacion);

            Cell cell = row.createCell(0);
            cell.setCellValue(empresa != null ? empresa.getRuc(): "No tiene ubicación");

            cell = row.createCell(1);
            cell.setCellValue(glnEmpresa);

            cell = row.createCell(2);
            cell.setCellValue(empresa != null ? empresa.getRazonSocial(): "No tiene ubicación");

            cell = row.createCell(3);
            cell.setCellValue(fechaDeActualizacion);

            cell = row.createCell(4);
            cell.setCellValue(String.valueOf(totalDeProductosConGTIN13));

            cell = row.createCell(5);
            cell.setCellValue(String.valueOf(totalDeProductosConGTIN14));

            return xSSFWorkbook;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return xSSFWorkbook;
    }

    public XSSFWorkbook agregarEmpresasEmitiendoOrdenesDeCompra(XSSFWorkbook xSSFWorkbook, catalogo.reportes.core.catalogo.entity.Empresa empresa) {
        try {

            XSSFSheet worksheet = xSSFWorkbook.getSheetAt(0);
            int rowNumber = worksheet.getLastRowNum();
            rowNumber++;

            Row row = worksheet.createRow(rowNumber);

            Cell cell = row.createCell(0);
            cell.setCellValue(empresa != null ? empresa.getRut(): "No tiene RUT");

            cell = row.createCell(1);
            cell.setCellValue(empresa.getGln());

            cell = row.createCell(2);
            cell.setCellValue(empresa != null ? empresa.getRazonSocial(): "No tiene ubicación");

            cell = row.createCell(3);
            cell.setCellValue(empresa != null ? empresa.getId(): "No tiene Id");

            return xSSFWorkbook;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return xSSFWorkbook;
    }

    public int crearCabeceras(XSSFWorkbook xSSFWorkbook, XSSFSheet worksheet, int row, List<ExcelDocumentoColumna> excelDocumentoColumnas){
        XSSFCellStyle CellStyleBorder = xSSFWorkbook.createCellStyle();
        Font font = xSSFWorkbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        CellStyleBorder.setFont(font);
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setBold(false);
        font.setItalic(false);

        font.setBold(true);

        row = worksheet.getLastRowNum();
        row++;
        Row cabecera = worksheet.createRow(row);
        for (ExcelDocumentoColumna excelDocumentoColumna: excelDocumentoColumnas ) {
            Cell cell = cabecera.createCell(excelDocumentoColumna.getPosicion());
            cell.setCellStyle(CellStyleBorder);
            cell.setCellValue(excelDocumentoColumna.getLabel());
            worksheet.setColumnWidth(excelDocumentoColumna.getPosicion(), 255 * 20);
        }
        return row + 1;
    }

}
