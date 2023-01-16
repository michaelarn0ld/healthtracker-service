package io.michaelarnold.healthtracker.controller;

import io.michaelarnold.healthtracker.domain.ExerciseDataPointService;
import io.michaelarnold.healthtracker.domain.Result;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class ExerciseDataPointController {

    @Autowired
    ExerciseDataPointService service;

    @PostMapping("/exercisedatapoint")
    public ResponseEntity<?> addExerciseDataPoint(@RequestBody @Valid ExerciseDataPoint exerciseDataPoint) {
        Result<ExerciseDataPoint> result = service.add(exerciseDataPoint);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
