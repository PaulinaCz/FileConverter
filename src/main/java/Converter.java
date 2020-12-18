public interface Converter {


    /*Convert File fileName
     */
    void convert(String filePath);

    /*Read file as byte array and convert
    */
    void convertFromByteArray(String filePath);
}
