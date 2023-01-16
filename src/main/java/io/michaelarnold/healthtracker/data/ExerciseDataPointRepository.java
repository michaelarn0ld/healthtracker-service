package io.michaelarnold.healthtracker.data;

import io.michaelarnold.healthtracker.model.ExerciseDataPoint;
import io.michaelarnold.healthtracker.model.ExerciseType;

import java.util.List;

public interface ExerciseDataPointRepository {

    ExerciseDataPoint add(ExerciseDataPoint exerciseDataPoint);


}
