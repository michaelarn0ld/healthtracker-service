package io.michaelarnold.healthtracker.model;

public enum RepRange {
    BENCH_PRESS("BENCHPRESS"),
    BACK_SQUAT("BACKSQUAT"),
    FRONT_SQUAT("FRONTSQUAT"),
    OVERHEAD_PRESS("OVERHEADPRESS"),
    BARBELL_ROW("BARBELLROW"),
    DEAD_LIFT("DEADLIFT");

    private final String name;

    public String getName() {
        return name;
    }

    RepRange(String name) {
        this.name = name;
    }

    public static RepRange fromString(String name) throws IllegalAccessException {
        for (RepRange e: RepRange.values()) {
            if (e.name.equals(name)) return e;
        }
        throw new IllegalAccessException("No ExerciseType can be parsed from: " + name);
    }

}
