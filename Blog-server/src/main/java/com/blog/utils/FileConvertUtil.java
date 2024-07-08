package com.blog.utils;import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;import com.artofsolving.jodconverter.DocumentConverter;import com.artofsolving.jodconverter.DocumentFormat;import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;import java.io.*;import java.net.HttpURLConnection;import java.net.URL;import java.net.URLConnection;import java.nio.file.Files;/** * 文件格式转换工具类 */public class FileConvertUtil {    /**     * 默认转换后文件后缀     */    private static final String DEFAULT_SUFFIX = "pdf";    /**     * openoffice的host:你部署openoffice的服务器ip     */    private static final String OPENOFFICE_HOST = "139.9.220.169";    /**     * openoffice的port     */    private static final Integer OPENOFFICE_PORT = 8100;    /**     * 方法描述 office文档转换为PDF(处理本地文件)     * @param sourcePath 源文件路径     * @param suffix     源文件后缀     * @return InputStream 转换后文件输入流     */    public static InputStream convertLocaleFile(String sourcePath, String suffix) throws Exception {        File inputFile = new File(sourcePath);        InputStream inputStream = new FileInputStream(inputFile);        return covertCommonByStream(inputStream, suffix);    }    /**     * office文档转换为PDF文件流(处理网络文件)     *     * @param netFileUrl 网络文件路径     * @param suffix     文件后缀     * @return InputStream 转换后文件输入流     */    public static InputStream convertNetFile(String netFileUrl, String suffix) throws Exception {        // 创建URL        URL url = new URL(netFileUrl);        // 试图连接并取得返回状态码        URLConnection urlconn = url.openConnection();        urlconn.connect();        HttpURLConnection httpconn = (HttpURLConnection) urlconn;        int httpResult = httpconn.getResponseCode();        if (httpResult == HttpURLConnection.HTTP_OK) {            InputStream inputStream = urlconn.getInputStream();            return covertCommonByStream(inputStream, suffix);        }        return null;    }    /**     * office文档转换为PDF文件(处理网络文件)     *     * @param netFileUrl 网络文件路径     * @param suffix     文件后缀     * @param targetPath      目标文件全路径 eg: C:\Users\cvec2022\Desktop\abc.pdf     * @return InputStream 转换后文件输入流     */    public static File convertNetFileToFile(String netFileUrl, String suffix, String targetPath) throws Exception {        // 创建URL        URL url = new URL(netFileUrl);        // 试图连接并取得返回状态码        URLConnection urlconn = url.openConnection();        urlconn.connect();        HttpURLConnection httpconn = (HttpURLConnection) urlconn;        int httpResult = httpconn.getResponseCode();        if (httpResult == HttpURLConnection.HTTP_OK) {            InputStream inputStream = urlconn.getInputStream();            return covertCommonByStream(inputStream, suffix, targetPath);        }        return null;    }    /**     * 将文件以流的形式转换     *     * @param inputStream 源文件输入流     * @param suffix      源文件后缀     * @return InputStream 转换后文件输入流     */    public static InputStream covertCommonByStream(InputStream inputStream, String suffix) throws Exception {        ByteArrayOutputStream out = new ByteArrayOutputStream();        OpenOfficeConnection connection = new SocketOpenOfficeConnection(OPENOFFICE_HOST, OPENOFFICE_PORT);        connection.connect();        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();        DocumentFormat targetFormat = formatReg.getFormatByFileExtension(DEFAULT_SUFFIX);        DocumentFormat sourceFormat = formatReg.getFormatByFileExtension(suffix);        converter.convert(inputStream, sourceFormat, out, targetFormat);        connection.disconnect();        return outputStreamConvertInputStream(out);    }    /**     * 将文件以文件的形式转换     *     * @param inputStream 源文件输入流     * @param suffix      源文件后缀     * @param targetPath      目标文件全路径 eg: C:\Users\cvec2022\Desktop\abc.pdf     * @return File 转换后文件     */    public static File covertCommonByStream(InputStream inputStream, String suffix, String targetPath) throws Exception {        ByteArrayOutputStream out = new ByteArrayOutputStream();        OpenOfficeConnection connection = new SocketOpenOfficeConnection(OPENOFFICE_HOST, OPENOFFICE_PORT);        connection.connect();        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();        DocumentFormat targetFormat = formatReg.getFormatByFileExtension(DEFAULT_SUFFIX);        DocumentFormat sourceFormat = formatReg.getFormatByFileExtension(suffix);        converter.convert(inputStream, sourceFormat, out, targetFormat);        connection.disconnect();        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;        return byteArrayToFile(baos.toByteArray(), targetPath);    }    /**     * byte数组转File     * @param byteArray 字节数组     * @param targetPath 目标路径     */    public static File byteArrayToFile(byte[] byteArray, String targetPath) {        InputStream in = new ByteArrayInputStream(byteArray);        File file = new File(targetPath);        String path = targetPath.substring(0, targetPath.lastIndexOf(File.separator));        if (!file.exists()) {            new File(path).mkdir();        }        FileOutputStream fos = null;        try {            fos = new FileOutputStream(file);            int len = 0;            byte[] buf = new byte[1024];            while ((len = in.read(buf)) != -1) {                fos.write(buf, 0, len);            }            fos.flush();        } catch (Exception e) {            e.printStackTrace();        } finally {            if (null != fos) {                try {                    fos.close();                } catch (IOException e) {                    e.printStackTrace();                }            }        }        return file;    }    /**     *  inputStream转File     */    public static void inputStreamToFile(InputStream ins, File file) throws IOException {        OutputStream os = Files.newOutputStream(file.toPath());        int bytesRead = 0;        byte[] buffer = new byte[8192];        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {            os.write(buffer, 0, bytesRead);        }        os.close();        ins.close();    }    /**     *  outputStream转inputStream     */    public static ByteArrayInputStream outputStreamConvertInputStream(final OutputStream out) throws Exception {        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;        return new ByteArrayInputStream(baos.toByteArray());    }    public static void main(String[] args) throws Exception {    	convertNetFileToFile("http://139.9.220.169:9090/upload/4bd6e27a-5ab3-4961-8891-9079aff817c6.docx", "docx", "C:\\Users\\cvec2022\\Desktop\\abc.pdf");    }}