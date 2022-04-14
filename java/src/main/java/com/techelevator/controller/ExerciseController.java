package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.model.Exercise;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@PreAuthorize("isAuthenticated()")
public class ExerciseController {

private ExerciseDao exerciseDao;

    public ExerciseController(ExerciseDao exerciseDao){
        this.exerciseDao = exerciseDao;

    }
    @GetMapping("/exercise/{name}")
    public Exercise getExerciseByName(@PathVariable String name){

        return exerciseDao.findByExerciseName(name);
    }

    @GetMapping("/exercise/all")
    public List<Exercise> getAllExercises(){
        List<Exercise> allExercises = exerciseDao.getAll();

        return allExercises;
    }

    @GetMapping("/exercise/{id}")
    public Exercise getExerciseById(@PathVariable Long id){
        return exerciseDao.getExerciseById(id);
    }

    @GetMapping("/exercise/{muscleGroup}")
    public List<Exercise> getExerciseByMuscleGroup(@PathVariable String Group){
        List<Exercise> exerciseGroup = exerciseDao.findByMuscleGroup(Group);

        return  exerciseGroup;
    }

    @GetMapping("/exercise/{type}")
    public List<Exercise> getExerciseByType(@PathVariable String type){
        List<Exercise> exerciseType = exerciseDao.findByType(type);

        return exerciseType;
    }

    @PostMapping("/exercise")
    public void createExercise(@RequestBody Exercise exercise){
        this.exerciseDao.create(exercise);
    }

}
