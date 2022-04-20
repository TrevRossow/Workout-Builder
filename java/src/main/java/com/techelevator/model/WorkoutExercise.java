package com.techelevator.model;

import java.util.List;

public class WorkoutExercise {

    private Long workoutId;
    private Long exerciseId;

    public  WorkoutExercise() {}

    public WorkoutExercise(Long workoutId, Long exerciseId){
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }






}
