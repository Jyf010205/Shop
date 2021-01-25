package com.shuaibi.shop.system.config;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jianyufeng
 * @date: 2021/1/25 20:54
 * @description:
 */
@Slf4j
@Configuration
public class MinioClientConfig {
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Bean
    public MinioClient minioClient() throws Exception {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        boolean isExist = minioClient.bucketExists(BUCKET_NAME);
        if (!isExist) {
            log.info("存储桶不存在,准备创建");
            //创建存储桶并设置只读权限
            minioClient.makeBucket(BUCKET_NAME);
            minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_WRITE);
        }
        return minioClient;
    }
}
