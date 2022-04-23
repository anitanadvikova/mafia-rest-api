package org.example.mafia.service;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {
    private final MinioClient amazonS3;
    @Value("${minio.access.bucket-name}")
    private String bucketName;

    @PostConstruct
    void checkBucket() throws Exception {
        if (!amazonS3.bucketExists(bucketName)) {
            amazonS3.makeBucket(bucketName);
        }
    }

    @SneakyThrows
    public void putInputStream(String fileName, InputStream inputStream) {
        amazonS3.putObject(bucketName, fileName, inputStream, "pdf");
        Thread.sleep(1000 * 5);
    }

    @SneakyThrows
    public String getLink(String fileName) {
        return amazonS3.presignedGetObject(bucketName, fileName);
    }
}
