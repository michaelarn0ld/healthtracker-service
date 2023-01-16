package io.michaelarnold.healthtracker.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationConfiguration {

    public static final String HEALTH_TRACKER_POST_KEY = "accessPin";
    public static final String HEALTH_TRACKER_TABLE_NAME = "HealthTracker";
    private static final Regions REGION = Regions.US_WEST_1;
    private static final ProfileCredentialsProvider CREDENTIALS = new ProfileCredentialsProvider();
    @Bean
    public AmazonDynamoDB initializeAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(CREDENTIALS)
                .build();
    }

    @Bean
    public AWSSecretsManager initializeAmazonSecretsManager() {
        return AWSSecretsManagerClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(CREDENTIALS)
                .build();
    }

    @Bean
    public Validator initializeValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
