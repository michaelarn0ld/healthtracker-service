package io.michaelarnold.healthtracker.data;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import io.michaelarnold.healthtracker.config.ApplicationConfiguration;
import io.michaelarnold.healthtracker.exceptions.HealthTrackerInfrastructureException;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import io.michaelarnold.healthtracker.model.ExerciseType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class ExerciseDataPointDynamoDbRepository implements ExerciseDataPointRepository {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Override
    public List<ExerciseDataPoint> getExerciseDataPoints(ExerciseType exerciseType) {
        log.info("About to fetch exercise data points for: " + exerciseType.getName());
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ApplicationConfiguration.HEALTH_TRACKER_PRIMARY_KEY_NAME,
                new AttributeValue(exerciseType.getName()));
        GetItemRequest request = new GetItemRequest()
                .withKey(key)
                .withTableName(ApplicationConfiguration.HEALTH_TRACKER_TABLE_NAME);
        AttributeValue attributeValue = null;
        try {
            Map<String, AttributeValue> result = amazonDynamoDB.getItem(request).getItem();
            for (var e: result.entrySet()) {
                log.info("KEY: " + e.getKey());
                log.info("VAL: " + e.getValue());
            }
        } catch (Exception e) {
            log.error(generateExceptionMessage(e));
            throw new HealthTrackerInfrastructureException(generateExceptionMessage(e));
        }
        return null;
    }

    private String generateExceptionMessage(Exception e) {
        return "Failed to query DDb with: " + e.getMessage();
    }

}
