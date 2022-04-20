package com.techelevator.controller;


import com.techelevator.dao.WorkoutDao;
import com.techelevator.dao.WorkoutExerciseDao;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutExercise;
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
    private WorkoutExerciseDao workoutExerciseDao;


    public WorkoutController(WorkoutDao workoutDao, WorkoutExerciseDao workoutExerciseDao){
        this.workoutDao = workoutDao;
        this.workoutExerciseDao = workoutExerciseDao;
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

    @GetMapping("/workout")
    public List<Workout> getAllWorkouts() {
        List<Workout> allWorkouts = workoutDao.getAll();

        return allWorkouts;
    }

    @PostMapping("/workout")
    public Workout createWorkout(@RequestBody Workout workout){
          return this.workoutDao.create(workout);

    }

    @PutMapping("/workout/{id}")
    public boolean updateWorkout(@Valid @PathVariable Long id, @RequestBody Workout updatedWorkout){
        return this.workoutDao.updateWorkout(id, updatedWorkout);
    }

    @DeleteMapping("workout/{id}")
    public void deleteExercise(@Valid @PathVariable Long id){
        this.workoutDao.deleteWorkout(id);
    }

    @DeleteMapping("workout/{id}/exercise")
    public void deleteWorkoutExercise(@Valid @PathVariable Long id){
        this.workoutExerciseDao.deleteWorkoutExercise(id);
    }


    @PostMapping("/workout/{workoutID}/exercise")
    public void add( @PathVariable Long workoutID, @RequestBody WorkoutExercise workoutExercise){
        this.workoutExerciseDao.add(workoutID, workoutExercise);
    }
}
