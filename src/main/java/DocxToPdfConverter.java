
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


import java.io.*;

public class DocxToPdfConverter implements Converter {

    public final String TO_FORMAT = "pdf";
    public final String FROM_FORMAT = "docx";

    public void convert(String  fileName) throws IOException {

        long start = System.currentTimeMillis();
        InputStream inStream = new FileInputStream(new File(fileName + "." + FROM_FORMAT));
        OutputStream outStream = new FileOutputStream(new File(fileName + "." + TO_FORMAT));

        XWPFDocument document = new XWPFDocument(inStream);

        PdfOptions options = PdfOptions.create();

        PdfConverter.getInstance().convert(document, outStream, options);

        inStream.close();
        outStream.close();
        System.out.println(fileName + "." + TO_FORMAT + " was converted to a PDF file in : "
                + (System.currentTimeMillis() - start) + " milli seconds");
    }
}
