package io.michaelarnold.healthtracker.model;

public enum ExerciseType {
    BENCH_PRESS("BENCHPRESS"),
    BACK_SQUAT("BACKSQUAT"),
    FRONT_SQUAT("FRONTSQUAT"),
    OVERHEAD_PRESS("OVERHEADPRESS"),
    BARBELL_ROW("BARBELLROW");

    private final String name;

    public String getName() {
        return name;
    }

    ExerciseType(String name) {
        this.name = name;
    }

    public static ExerciseType fromString(String name) throws IllegalAccessException {
        for (ExerciseType e: ExerciseType.values()) {
            if (e.name.equals(name)) return e;
        }
        throw new IllegalAccessException("No ExerciseType can be parsed from: " + name);
    }

}
