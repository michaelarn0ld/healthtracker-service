package io.michaelarnold.healthtracker.data;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import io.michaelarnold.healthtracker.config.ApplicationConfiguration;
import io.michaelarnold.healthtracker.exceptions.HealthTrackerInfrastructureException;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import io.michaelarnold.healthtracker.model.ExerciseType;
import io.michaelarnold.healthtracker.model.RepRange;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Repository
public class ExerciseDataPointDynamoDbRepository implements ExerciseDataPointRepository {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Override
    public ExerciseDataPoint add(ExerciseDataPoint exerciseDataPoint) {

        exerciseDataPoint.setLogTime(LocalDateTime.now());

        Map<String, AttributeValue> attributeMap = new HashMap<>();
        for (Field f: exerciseDataPoint.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            String attributeName = f.getName();
            log.info("Name: " + attributeName);
            FieldType fieldType;
            Class<?> clazz = f.getType();
            try {
                fieldType = FieldType.fromClass(clazz);
                String attributeString  = f.get(exerciseDataPoint).toString();
                AttributeValue attributeValue = new AttributeValue();
                switch (fieldType) {
                    case FLOAT, INTEGER ->  {
                        attributeValue.setN(attributeString);
                    }
                    case LOCAL_DATE_TIME, EXERCISE_TYPE, REP_RANGE -> {
                        attributeValue.setS(attributeString);
                    }
                }
                attributeMap.put(attributeName, attributeValue);
            } catch (IllegalAccessException e) {
                throw new HealthTrackerInfrastructureException("Cannot parse class: " + clazz.getName() + " into field type");
            } finally {
                f.setAccessible(false);
            }
        }
        amazonDynamoDB.putItem(ApplicationConfiguration.HEALTH_TRACKER_TABLE_NAME, attributeMap);
        return null;
    }

    private enum FieldType {
        LOCAL_DATE_TIME,
        FLOAT,
        INTEGER,
        EXERCISE_TYPE,
        REP_RANGE;

        static FieldType fromClass(Class<?> clazz) throws IllegalAccessException {
            if (clazz == LocalDateTime.class) return LOCAL_DATE_TIME;
            else if (clazz == Float.class) return FLOAT;
            else if (clazz == Integer.class) return INTEGER;
            else if (clazz == ExerciseType.class) return EXERCISE_TYPE;
            else if (clazz == RepRange.class) return REP_RANGE;
            throw new IllegalAccessException(String.format("No %s can be parsed from: %s", FieldType.class.getName() , clazz.getName()));
        }
    }

}
