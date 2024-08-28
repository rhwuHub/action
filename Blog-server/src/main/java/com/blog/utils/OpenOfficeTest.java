package com.blog.utils;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class OpenOfficeTest {
    private static final Logger log = LoggerFactory.getLogger(OpenOfficeTest.class);

    public static void main(String[] args) {
        String jdocServerHost = "139.9.220.169";
        int jdocServerPort = 8100;

        OpenOfficeConnection connection = null;
        try {
            connection = new SocketOpenOfficeConnection(jdocServerHost, jdocServerPort);
            connection.connect();
            log.info("远程连接OpenOffice服务: {}:{}成功", jdocServerHost, jdocServerPort);

            // 创建文件转换器
            StreamOpenOfficeDocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);

            // 定义源文件和目标文件路径
            File sourceFile = new File("F:\\Firefox下载位置\\实验4等价类测试.docx");
            String targetFilePath = sourceFile.getParent() + File.separator + "实验4等价类测试.pdf";
            File targetFile = new File(targetFilePath);
            // 文件转化
            converter.convert(sourceFile, targetFile);
            log.info("文件转换成功: {} -> {}", sourceFile.getPath(), targetFile.getPath());
        } catch (Exception e) {
            log.error("连接或转换失败", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
                log.info("断开连接");
            }
        }
    }
}
