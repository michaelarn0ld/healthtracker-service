package io.michaelarnold.healthtracker.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Builder
@Data
public class ExerciseDataPoint {

    @Null
    private LocalDateTime logTime;

    @NotNull
    private Float weightLbs;

    @NotNull
    private Float bodyFatPercentage;

    @NotNull
    private ExerciseType exerciseType;

    @NotNull
    private RepRange repRange;

    @NotNull
    private Integer maximumWeightAchieved;
}
