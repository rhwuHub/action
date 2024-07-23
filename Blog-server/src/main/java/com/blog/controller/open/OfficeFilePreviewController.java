package com.blog.controller.open;

import com.blog.domain.entity.FileUpload;
import com.blog.domain.request.FileUploadReq;
import com.blog.service.FileUploadService;
import com.blog.utils.FileConvertUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * office文件在线预览接口
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
public class OfficeFilePreviewController {

    @Resource
    private FileUploadService fileUploadService;
  
    /**
     * 系统文件在线预览接口
     */
    @GetMapping("onlinePreview")
    public void onlinePreview(String url, HttpServletResponse response) throws Exception {
        // 获取文件类型
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        log.info("suffix {}", suffix);
        if (suffix.isEmpty()) {
            throw new RuntimeException("文件格式不正确");
        }
        if (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls")
                && !suffix.equals("xlsx") && !suffix.equals("ppt") && !suffix.equals("pptx") && !suffix.equals("pdf")) {
            throw new RuntimeException("文件格式不支持预览");
        }
        InputStream in = FileConvertUtil.convertNetFile(url, suffix);
        if (in == null) {
            // 处理无法获取输入流的情况
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try (OutputStream outputStream = response.getOutputStream()) {
            byte[] buff = new byte[8192]; // 增加缓冲区大小到8KB
            int n;
            while ((n = in.read(buff)) != -1) {
                outputStream.write(buff, 0, n);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("saveFile")
    public void saveFile(@RequestBody @Validated FileUploadReq requestBody) {
        FileUpload build = FileUpload.builder().fileName(requestBody.getFileName()).fileQrcode(requestBody.getFileQrcode()).fileUrl(requestBody.getFileUrl()).build();
        fileUploadService.save(build);
    }

    @GetMapping("allFiles")
    public List<FileUpload> allFiles(){
        return fileUploadService.list();
    }

    @DeleteMapping("deleteFile")
    public void deleteFile(@RequestParam Long id){
        fileUploadService.removeById(id);
    }
}

