package com.blog.service;

import com.blog.domain.entity.FileUpload;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【file_upload】的数据库操作Service
* @createDate 2024-07-11 09:57:53
*/
public interface FileUploadService extends IService<FileUpload> {

    Long queryByFileName(String originalName);
}
