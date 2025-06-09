package com.sky.config;

import com.aliyun.oss.common.utils.StringUtils;
import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建AliOssUtil对象
 */
@Configuration
@Slf4j
public class OssConfiguration {

    private class OSSProperties extends AliOssProperties {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        String accessKeyId = StringUtils.trim(System.getenv("OSS_ACCESS_KEY_ID"));
        String secretAccessKey = StringUtils.trim(System.getenv("OSS_ACCESS_KEY_SECRET"));
        String endpoint = StringUtils.trim(System.getenv("OSS_ENDPOINT"));
        String bucketName = StringUtils.trim(System.getenv("OSS_BUCKET_NAME"));

        OSSProperties(AliOssProperties aliOssProperties) {
            // 未配置环境变量或环境变量不全时使用配置文件中的配置
            this.setAccessKeyId(configuration(accessKeyId, aliOssProperties.getAccessKeyId()));
            this.setAccessKeySecret(configuration(secretAccessKey, aliOssProperties.getAccessKeySecret()));
            this.setEndpoint(configuration(endpoint, aliOssProperties.getEndpoint()));
            this.setBucketName(configuration(bucketName, aliOssProperties.getBucketName()));
        }

        String configuration(String a, String b) {
            return a != null && !a.isEmpty() ? a : b;
        }
    }

    //依赖IOC容器自动装配AliOssUtil对象
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        AliOssProperties ossProperties = new OSSProperties(aliOssProperties);
        log.info("开始创建阿里云文件上传工具类对象：{}", ossProperties);
        return new AliOssUtil(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                ossProperties.getBucketName());
    }

}