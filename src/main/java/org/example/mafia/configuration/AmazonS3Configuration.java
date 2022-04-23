package org.example.mafia.configuration;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Configuration {
    @Value("${minio.access.access-key}")
    private String awsAccessKey;
    @Value("${minio.access.secret-key}")
    private String awsSecretKey;
    @Value("${minio.access.url:http://127.0.0.1:9000}")
    private String url;

    @Bean
    @SneakyThrows
    public MinioClient amazonS3() {
        return new MinioClient(url, awsAccessKey, awsSecretKey);
    }
}
