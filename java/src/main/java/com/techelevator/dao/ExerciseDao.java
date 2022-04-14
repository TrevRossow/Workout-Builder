package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getAll();

    Exercise getExerciseById(Long exerciseId);

    Exercise findByExerciseName(String name);

    List<Exercise> findByMuscleGroup(String group);

    List<Exercise> findByType(String type);

    Long create(String name, String muscleGroup, Integer repRange, String type, String description);

    Long getIdByExerciseName(String name);
}
