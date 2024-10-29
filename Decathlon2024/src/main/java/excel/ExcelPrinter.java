package excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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

    //addHeaders skriver rubrikerna i den första raden.
    public void addHeaders(String[] headers, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Row headerRow = sheet.createRow(0);  // Skapar första raden för rubrikerna
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    //add-metoden lägger till själva datan, med start från andra raden.
    public void add(Object[][] data, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        int rowCount = sheet.getLastRowNum() + 1; // Fortsätter på raden efter rubrikerna

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
