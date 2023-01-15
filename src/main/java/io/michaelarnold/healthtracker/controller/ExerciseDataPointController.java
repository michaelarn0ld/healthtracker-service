package io.michaelarnold.healthtracker.controller;

import io.michaelarnold.healthtracker.domain.ExerciseDataPointService;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import io.michaelarnold.healthtracker.model.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseDataPointController {

    @Autowired
    ExerciseDataPointService service;

    @GetMapping("/")
    public ResponseEntity<?> getExerciseDataPoints() {
        List<ExerciseDataPoint> exerciseDataPoints = service.getExerciseDataPoints(ExerciseType.BENCH_PRESS);
        return new ResponseEntity<>(exerciseDataPoints, HttpStatus.OK);
    }
}
