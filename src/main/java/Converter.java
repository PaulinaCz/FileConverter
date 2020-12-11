import java.io.File;
import java.io.OutputStream;
import java.text.Format;

public interface Converter {


    /*Convert File file from fromFormat to toFormat, streaming result to OutputStream outStream.
    *
    * Supports:
    * fromFormat : DOC/DOCX
    *
    * toFormat: PDF
     */

    void convert(File file, Format fromFormat, Format toFormat, OutputStream outStream);
}
