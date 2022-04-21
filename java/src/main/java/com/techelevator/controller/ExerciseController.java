package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.dao.ExerciseStatusDao;
import com.techelevator.model.Exercise;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ExerciseController {

    private ExerciseDao exerciseDao;
    private ExerciseStatusDao exerciseStatusDao;

    public ExerciseController(ExerciseDao exerciseDao, ExerciseStatusDao exerciseStatusDao) {
        this.exerciseDao = exerciseDao;
        this.exerciseStatusDao = exerciseStatusDao;
    }


    @GetMapping("/exercise/all")
    public List<Exercise> getAllExercises() {
        List<Exercise> allExercises = exerciseDao.getAll();

        return allExercises;
    }

    @GetMapping("/exercise/view/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseDao.getExerciseById(id);
    }

    @GetMapping("/exercise/view/{id}/status")
    public List<Exercise> getExercisesByStatusId(@PathVariable int id) {
        return exerciseDao.getExercisesByStatusId(id);
    }

    @GetMapping("/exercise/user/{id}")
    public List<Exercise> getExercisesByUserId(@PathVariable Long id) {
        return exerciseDao.getExercisesByUserId(id);
    }



    @GetMapping("/exercise/group/{muscleGroup}")
    public List<Exercise> getExerciseByMuscleGroup(@PathVariable String muscleGroup) {
        List<Exercise> exerciseGroup = exerciseDao.findByMuscleGroup(muscleGroup);

        return exerciseGroup;
    }

    @GetMapping("/exercise/{Id}")
    public List<Exercise> getExerciseByWorkout(@PathVariable Long Id) {
        List<Exercise> exerciseList = exerciseDao.findByWorkout(Id);

        return exerciseList;
    }

    @PostMapping("/exercise")
    public void createExercise(@RequestBody Exercise exercise) {
        this.exerciseDao.create(exercise);
    }

    @PutMapping("/exercise/{id}")
    public boolean updateExercise(@Valid @PathVariable Long id, @RequestBody Exercise changedExercise) {
        return this.exerciseDao.updateExercise(id, changedExercise);
    }

    @DeleteMapping("exercise/{id}")
    public void deleteExercise(@Valid @PathVariable Long id) {
        this.exerciseDao.deleteExercise(id);
    }

    // Incorporating exercise status in this controller
    @GetMapping("exercise/status")
    public String getStatusById() {
        return this.exerciseStatusDao.getStatus();
    }

}
