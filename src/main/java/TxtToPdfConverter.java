
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TxtToPdfConverter implements Converter{

    public final String TO_FORMAT = ".pdf";
    public final String FROM_FORMAT = ".txt";

    @Override
    public void convert(String filePath) throws IOException {

        long start = System.currentTimeMillis();
        PdfWriter writer = new PdfWriter(filePath + TO_FORMAT);
        PdfDocument pdfDocument = new PdfDocument(writer);

        Document document = new Document(pdfDocument);
        document.setTextAlignment(TextAlignment.LEFT);


        InputStreamReader streamReader = new InputStreamReader(new FileInputStream(filePath + FROM_FORMAT), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        Paragraph paragraph = new Paragraph();

        String line;
        while ((line = br.readLine()) != null){
            line = line.replace("\u0020", "\u00A0");
            paragraph.add(line + "\n");
        }
        document.add(paragraph);
        document.close();
        br.close();

        System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                + (System.currentTimeMillis() - start) + " milli seconds");
    }
}
