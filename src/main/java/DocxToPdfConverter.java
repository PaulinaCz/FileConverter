
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocxToPdfConverter implements Converter {

    public final String TO_FORMAT = ".pdf";
    public final String FROM_FORMAT = ".docx";

    public void convert(String filePath) {

        try {
            long start = System.currentTimeMillis();
            InputStream inStream = new FileInputStream(new File(filePath + FROM_FORMAT));
            XWPFDocument document = new XWPFDocument(inStream);

            OutputStream outStream = new FileOutputStream(new File(filePath + TO_FORMAT));
            PdfOptions options = PdfOptions.create();

            PdfConverter.getInstance().convert(document, outStream, options);

            inStream.close();
            outStream.close();

            System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                    + (System.currentTimeMillis() - start) + " milli seconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void convertFromByteArray(String filePath) {
        try {
            long start = System.currentTimeMillis();
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath + FROM_FORMAT));

            OutputStream outputStream = new FileOutputStream(new File(filePath + TO_FORMAT));
            outputStream.write(fileContent);
            outputStream.close();


            System.out.println(filePath + FROM_FORMAT + " was converted to a " + TO_FORMAT.toUpperCase() + " file in : "
                    + (System.currentTimeMillis() - start) + " milli seconds");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
