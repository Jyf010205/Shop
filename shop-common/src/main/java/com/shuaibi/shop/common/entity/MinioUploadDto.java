package com.shuaibi.shop.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jianyufeng
 * @date: 2021/1/25 18:00
 * @description: minio上传文件封装类
 */
@Data
public class MinioUploadDto {
    @ApiModelProperty("文件名称")
    public String name;

    @ApiModelProperty("文件下载地址")
    private String url;
}
