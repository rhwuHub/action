package com.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.FileUpload;
import com.blog.service.FileUploadService;
import com.blog.mapper.FileUploadMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【file_upload】的数据库操作Service实现
* @createDate 2024-07-11 09:57:53
*/
@Service
public class FileUploadServiceImpl extends ServiceImpl<FileUploadMapper, FileUpload>
    implements FileUploadService{

    @Override
    public Long queryByFileName(String originalName) {
        return baseMapper.selectCount(Wrappers.<FileUpload>lambdaQuery().likeRight(FileUpload::getFileName,originalName));
    }
}




