package com.techelevator.controller;


import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.Workout;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@PreAuthorize("isAuthenticated()")

public class WorkoutController {

    private WorkoutDao workoutDao;


    public WorkoutController(WorkoutDao workoutDao){
        this.workoutDao = workoutDao;
    }

    @GetMapping("/workout/{id}")
    public Workout getWorkoutById(@PathVariable Long id ){
        return workoutDao.getWorkoutById(id);
    }

    @GetMapping("/workout/user/{id}")
    public Workout getWorkoutByUserId(@PathVariable Long id ){
        return workoutDao.getWorkoutByUserId(id);
    }

    @GetMapping("/workout/{date}")
    public Workout getDateCompleted(@PathVariable Date date){
        return workoutDao.getDateCompleted(date);
    }

    @GetMapping("/workout/all")
    public List<Workout> getAllWorkouts() {
        List<Workout> allWorkouts = workoutDao.getAll();

        return allWorkouts;
    }

    @PostMapping("/workout")
    public void createWorkout(@RequestBody Workout workout){
        this.workoutDao.create(workout);
    }

    @PutMapping("/workout/{id}")
    public boolean updateWorkout(@Valid @PathVariable Long id, @RequestBody Workout updatedWorkout){
        return this.workoutDao.updateWorkout(id, updatedWorkout);
    }

    @DeleteMapping("workout/{id}")
    public void deleteExercise(@Valid @PathVariable Long id){
        this.workoutDao.deleteWorkout(id);
    }



}
