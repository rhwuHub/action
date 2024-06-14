package com.blog.controller.admin;

import cn.hutool.core.lang.UUID;
import com.blog.utils.AliOssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YT
 * @since 2024-01-28
 */
@RestController
@Slf4j
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @Value("${upload-Path:/app/asset/}")
    private String uploadPath;

    @Value("${server-Url:http://localhost:9091}")
    private String serverUrl;

    @PostMapping("/addFile")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        // return AliOssUtil.uploadFile(File.getOriginalFilename(),File.getInputStream());
        return url(file);
    }

    public String url(MultipartFile multipartFile) {
        // 获取文件原始名称
        String originalName = multipartFile.getOriginalFilename();
        Assert.notNull(originalName, "文件名不能为空");
        // 获取文件后缀
        String suffix = originalName.substring(originalName.lastIndexOf('.'));
        // 生成唯一文件名
        String fileName = UUID.randomUUID() + suffix;
        // 创建目录
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存文件
        File uploadFile = new File(dir, fileName);
        try {
            multipartFile.transferTo(uploadFile.getAbsoluteFile());
        } catch (IOException e) {
            // 记录错误日志并抛出自定义异常
            log.error("[FileUploadService] upload() occurred exception!", e);
            throw new RuntimeException("文件上传异常");
        }

        // 返回可以访问该文件的 URL
        String returnUrl = serverUrl + "/upload/" + fileName;
        return returnUrl.replace("\\", "/");
    }



}


