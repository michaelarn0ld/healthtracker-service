package io.michaelarnold.healthtracker.controller;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import io.michaelarnold.healthtracker.domain.ExerciseDataPointService;
import io.michaelarnold.healthtracker.domain.Result;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.michaelarnold.healthtracker.config.ApplicationConfiguration.HEALTH_TRACKER_POST_KEY;


@RestController
@Log4j2
public class ExerciseDataPointController {

    @Autowired
    ExerciseDataPointService service;

    @Autowired
    AWSSecretsManager awsSecretsManager;

    @PostMapping("/exerciseDataPoint")
    public ResponseEntity<?> addExerciseDataPoint(@RequestBody @Valid ExerciseDataPoint exerciseDataPoint,
                                                  @RequestParam(name = HEALTH_TRACKER_POST_KEY) String accessPin) {

        GetSecretValueRequest request = new GetSecretValueRequest();
        request.setSecretId(HEALTH_TRACKER_POST_KEY);
        GetSecretValueResult secretResult = awsSecretsManager.getSecretValue(request);
        String secret = secretResult.getSecretString();
        JSONObject object = new JSONObject(secret);
        String key = object.getString(HEALTH_TRACKER_POST_KEY);

        if (!key.equals(accessPin)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<ExerciseDataPoint> result = service.add(exerciseDataPoint);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
