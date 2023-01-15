package io.michaelarnold.healthtracker.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExerciseDataPoint {

    private ExerciseType exerciseType;
    private long timestampRecorded;
    private int fiveRepMaximumLbs;
}
