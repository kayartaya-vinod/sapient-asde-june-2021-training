package com.sapient.asde.batch5.emailservice;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import io.awspring.cloud.ses.SimpleEmailServiceJavaMailSender;

@Configuration
public class AwsConfig {
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        AWSCredentialsProvider awsCreds = new ClasspathPropertiesFileCredentialsProvider();

        return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(awsCreds).withRegion(Regions.US_EAST_1)
                .build();

        // return AmazonSimpleEmailServiceClientBuilder.standard()
        // .withCredentials(new
        // ProfileCredentialsProvider("sapient")).withRegion(Regions.US_EAST_1).build();
    }

    @Bean
    public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
    }
}
