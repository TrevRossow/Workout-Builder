package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkoutDao {

    List<Workout> getAll();

    List<Workout> getWorkoutByUserId(Long userId);

    Workout getWorkoutById(Long workoutId);

    Workout getDateCompleted(Long userId);

    Workout create(Workout workout);

    boolean deleteWorkout(Long id);

    boolean updateWorkout(Long id, Workout updatedWorkout);

}
