package edu.educate.helper;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import edu.educate.model.baseModel.BaseEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator<T extends BaseEntity> {

    private List<T> baseEntities;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<T> baseEntities) {
        this.baseEntities = baseEntities;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader(List<String> headerNames) {

        String sheetName = baseEntities.get(0).getClass().getSimpleName();
        sheet = workbook.createSheet(sheetName);
        sheet.setRightToLeft(true);
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        for (int i = 0; i < headerNames.size(); i++) {
            createCell(row, i, headerNames.get(i), style);
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Float) {
            cell.setCellValue((Float) valueOfCell);
        } else if (valueOfCell instanceof Boolean){
            cell.setCellValue((Boolean) valueOfCell);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (T record : baseEntities) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            List<Object> cellValues = record.getCellValues();
            for (Object cellValue : cellValues) {
                createCell(row, columnCount++, cellValue, style);
            }
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        List<String> headerNames = baseEntities.get(0).getHeaderNames();
        writeHeader(headerNames);
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
