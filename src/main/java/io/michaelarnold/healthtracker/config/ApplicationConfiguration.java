package io.michaelarnold.healthtracker.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    public static final String HEALTH_TRACKER_PRIMARY_KEY_NAME = "exerciseType";
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

}
