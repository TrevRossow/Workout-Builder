package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkoutDao {

    List<Workout> getAll();

    Workout getWorkoutById(long workoutId);

    Workout getDateCompleted(LocalDate dateCompleted);

    Workout create(Workout workout);

    boolean deleteWorkout(Long id);

    boolean updateWorkout(Long id, Workout updatedWorkout);

}
