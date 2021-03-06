import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfToDocxConverter implements Converter {

    public final String TO_FORMAT = ".docx";
    public final String FROM_FORMAT = ".pdf";

    @Override
    public void convert(String filePath) {

        try {
            long start = System.currentTimeMillis();
            PdfReader reader = new PdfReader(filePath + FROM_FORMAT);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            XWPFDocument document = new XWPFDocument();

            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                String text = strategy.getResultantText();
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(text);
                run.addBreak(BreakType.PAGE);
            }

            FileOutputStream outputStream = new FileOutputStream(new File(filePath + TO_FORMAT));
            document.write(outputStream);


            reader.close();
            outputStream.close();

            System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                    + (System.currentTimeMillis() - start) + " milli seconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void convertFromByteArray(String filePath) {

        try {
            long start = System.currentTimeMillis();
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath + FROM_FORMAT));
            FileOutputStream outputStream = new FileOutputStream(new File(filePath + TO_FORMAT));
            outputStream.write(fileContent);

            outputStream.close();

            System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                    + (System.currentTimeMillis() - start) + " milli seconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
