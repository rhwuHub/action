package com.blog.controller.open;

import com.blog.utils.FileConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * office文件在线预览接口
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
public class OfficeFilePreviewController {
  
    /**
     * 系统文件在线预览接口
     */
    @GetMapping("onlinePreview")
    public void onlinePreview(String url, HttpServletResponse response) throws Exception {
        // 获取文件类型
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        log.info("suffix {}", suffix);

        if (suffix.length() == 0) {
            throw new Exception("文件格式不正确");
        }
        if (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls")
                && !suffix.equals("xlsx") && !suffix.equals("ppt") && !suffix.equals("pptx")) {
            throw new Exception("文件格式不支持预览");
        }
        InputStream in = FileConvertUtil.convertNetFile(url, suffix);
        OutputStream outputStream = response.getOutputStream();
        // 创建存放文件内容的byte[]数组
        byte[] buff = new byte[1024];
        int n;
        while ((n = in.read(buff)) != -1) {
            outputStream.write(buff, 0, n);
        }
        outputStream.flush();
        outputStream.close();
        in.close();
    }
}

