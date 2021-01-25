package com.shuaibi.shop.system.controller;

import com.shuaibi.shop.common.entity.MinioUploadDto;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.utils.Asserts;
import com.shuaibi.shop.system.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author: jianyufeng
 * @date: 2021/1/25 17:51
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(tags = "MinIO对象存储管理")
public class FileController {
    @Autowired
    IFileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        Optional<MinioUploadDto> minioUploadDto = fileService.upload(file);
        if (!minioUploadDto.isPresent()) {
            Asserts.fail("文件上传失败");
        }
        return CommonResult.success(minioUploadDto.get(),"文件上传成功");
    }

    @ApiOperation("文件删除")
    @GetMapping("/delete")
    public CommonResult delete(@RequestParam("objectName") String objectName) throws Exception{
        fileService.delete(objectName);
        return CommonResult.success(null,"文件删除成功");
    }
}
