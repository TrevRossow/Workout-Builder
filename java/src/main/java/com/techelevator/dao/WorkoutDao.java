package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkoutDao {

    List<Workout> getAll();

    Workout getWorkoutByUserId(long userId);

    Workout getWorkoutById(long userId);

    Workout getDateCompleted(Date dateCompleted);

    Workout create(Workout workout);

    boolean deleteWorkout(Long id);

    boolean updateWorkout(Long id, Workout updatedWorkout);

}
