package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.WorkoutExercise;

public interface WorkoutExerciseDao {

    WorkoutExercise getExerciseByWorkoutId(long workoutId);

    boolean deleteWorkoutExercise(Long id);

    void add(Long id, WorkoutExercise workoutExercise );

}