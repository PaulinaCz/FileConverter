import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.stream.Stream;

public class CsvToXlsxConverter implements Converter {

    public final String TO_FORMAT = ".xlsx";
    public final String FROM_FORMAT = ".csv";

    @Override
    public void convert(String filePath) {

        try {
            long start = System.currentTimeMillis();
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            SXSSFSheet sheet = workbook.createSheet();

            BufferedReader br = new BufferedReader(new FileReader(filePath + FROM_FORMAT));
            String currentLine;
            int rowNumber = 0;

            while ((currentLine = br.readLine()) != null) {
                String[] nextLine = currentLine.split(",");
                rowNumber++;
                Row currentRow = sheet.createRow(rowNumber);
                Stream.iterate(0, i -> i + 1).limit(nextLine.length).forEach(
                        i -> currentRow.createCell(i).setCellValue(nextLine[i])
                );
            }

            FileOutputStream outputStream = new FileOutputStream(new File(filePath + TO_FORMAT));
            workbook.write(outputStream);
            outputStream.close();

            System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                    + (System.currentTimeMillis() - start) + " milli seconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void convertFromByteArray(String filePath) {

    }
}
