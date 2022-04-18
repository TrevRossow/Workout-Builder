package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getAll();

    Exercise getExerciseById(Long exerciseId);

    Exercise findByExerciseName(String name);

    List<Exercise> findByMuscleGroup(String group);

    List<Exercise> findByType(String type);

    Exercise create(Exercise exercise);

    Long getIdByExerciseName(String name);

    boolean deleteExercise(Long id);

    boolean updateExercise(Long id, Exercise changedExercise);

}
