package com.shuaibi.shop.system.service;

import com.shuaibi.shop.common.entity.MinioUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/25 17:55
 * @description:
 */
public interface IFileService {
    Optional<MinioUploadDto> upload(MultipartFile file) throws Exception;

    void delete(String objectName) throws Exception;
}
