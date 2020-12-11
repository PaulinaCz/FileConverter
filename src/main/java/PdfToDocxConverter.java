import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfToDocxConverter implements Converter{

    public final String TO_FORMAT = "docx";
    public final String FROM_FORMAT = "pdf";

    @Override
    public void convert(String fileName) throws IOException {

        long start = System.currentTimeMillis();

        XWPFDocument document = new XWPFDocument();
        PdfReader reader = new PdfReader(fileName + "." + FROM_FORMAT);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        for(int i =1; i <= reader.getNumberOfPages(); i++){
            TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            String text = strategy.getResultantText();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
            run.addBreak(BreakType.PAGE);
        }

        FileOutputStream outStream = new FileOutputStream(new File(fileName + "." + TO_FORMAT));
        document.write(outStream);


        reader.close();
        outStream.close();

        System.out.println(fileName + "." + TO_FORMAT + " was converted to a DOCX file in : "
                + (System.currentTimeMillis() - start) + " milli seconds");
    }
}
