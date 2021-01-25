package com.shuaibi.shop.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.shuaibi.shop.common.entity.MinioUploadDto;
import com.shuaibi.shop.system.service.IFileService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/25 17:55
 * @description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements IFileService {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Autowired
    private MinioClient minioClient;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public Optional<MinioUploadDto> upload(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        // 设置存储对象名称
        String objectName = DateUtil.formatDate(new DateTime())+ "/" + filename;
        // 使用putObject上传一个文件到存储桶中
        minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
        log.info("文件上传成功!");
        MinioUploadDto minioUploadDto = new MinioUploadDto();
        minioUploadDto.setName(filename);
        minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
        return Optional.of(minioUploadDto);
    }

    /**
     * 文件删除
     * @param objectName
     */
    @Override
    public void delete(String objectName) throws Exception{
        minioClient.removeObject(BUCKET_NAME, objectName);
    }
}
