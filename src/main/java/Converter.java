import com.itextpdf.text.DocumentException;


import java.io.IOException;

public interface Converter {



    /*Convert File fileName
     */
    void convert(String fileName) throws IOException;
}
