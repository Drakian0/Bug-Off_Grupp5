package excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPrinter {

    private XSSFWorkbook workbook;
    private String excelName;

    public ExcelPrinter(String name) throws IOException {
        workbook = new XSSFWorkbook();

        // Formatera aktuell tid och datum
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        excelName = name + "_" + timeStamp;  // Lägg till tidsstämpeln
    }

    // Skapa och applicera cellstil för rubriker
    private CellStyle createHeaderStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);  // Centrera text

        // Skapa och applicera fetstil på font
        Font font = workbook.createFont();
        font.setBold(true);  // Gör texten fet
        style.setFont(font);

        return style;
    }

    // Skapa och applicera cellstil för dataceller
    private CellStyle createDataStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);  // Centrera text i dataceller
        return style;
    }

    // addHeaders skriver rubrikerna i den första raden.
    public void addHeaders(String[] headers, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Row headerRow = sheet.createRow(0);  // Skapar första raden för rubrikerna
        CellStyle headerStyle = createHeaderStyle();  // Skapa stil för rubriker

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);  // Applicera stil
        }
    }

    // add-metoden lägger till själva datan, med start från andra raden.
    public void add(Object[][] data, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        int rowCount = sheet.getLastRowNum() + 1; // Fortsätter på raden efter rubrikerna
        CellStyle dataStyle = createDataStyle();  // Skapa stil för dataceller

        for (Object[] aBook : data) {
            Row row = sheet.createRow(rowCount);
            rowCount++;
            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount);
                columnCount++;

                if (field instanceof String) {
                    cell.setCellValue((String) field);

                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);

                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                }
                cell.setCellStyle(dataStyle);  // Applicera stil på dataceller
            }
        }

        // Autojustera kolumnbredder för att passa innehållet
        for (int i = 0; i < data[0].length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void write() throws IOException {
        FileOutputStream out = new FileOutputStream("C:/Eclipse/resultat_" + excelName + ".xlsx");
        workbook.write(out);
        workbook.close();
    }
}
