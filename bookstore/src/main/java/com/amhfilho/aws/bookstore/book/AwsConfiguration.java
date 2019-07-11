package com.amhfilho.aws.bookstore.book;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {

    @Bean
    public BasicAWSCredentials basicAWSCredentials(@Value("${s3.access.key}") String key,
                                                   @Value("${s3.access.secret}") String secret){
        return new BasicAWSCredentials(key,secret);
    }

    @Bean
    public AmazonS3 amazonS3(@Value("${s3.region}") String region, BasicAWSCredentials basicAWSCredentials){
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
