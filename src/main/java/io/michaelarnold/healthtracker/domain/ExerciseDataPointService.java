package io.michaelarnold.healthtracker.domain;

import io.michaelarnold.healthtracker.data.ExerciseDataPointRepository;
import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import io.michaelarnold.healthtracker.model.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseDataPointService {

    @Autowired
    ExerciseDataPointRepository repository;

    public List<ExerciseDataPoint> getExerciseDataPoints(ExerciseType exerciseType) {
        return repository.getExerciseDataPoints(exerciseType);
    }
}
