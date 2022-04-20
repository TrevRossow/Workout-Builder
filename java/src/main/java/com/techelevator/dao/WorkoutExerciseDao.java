package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.WorkoutExercise;

public interface WorkoutExerciseDao {

    WorkoutExercise getExerciseByWorkoutId(long workoutId);


    void add(Long id, WorkoutExercise workoutExercise );

}