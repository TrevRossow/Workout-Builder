package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getAll();

    Exercise getExerciseById(Long exerciseId);

    Exercise findByExerciseName(String name);

    Exercise findByMuscleGroup(String group);

    Exercise findByType(String type);

    boolean create(String name, String description, String muscleGroup);
}
