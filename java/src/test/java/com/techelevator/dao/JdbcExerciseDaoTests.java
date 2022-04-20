package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseNotFoundException;
import com.techelevator.model.Workout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDaoTests extends BaseDaoTests {

    protected static final Exercise Exercise1 = new Exercise(1L, 0L, "Stair Climbing", "Find some stairs and climb up and down. Great for inside or outside!","Cardio", "10 min","Cardio", 2, 5);
    ;

    protected static final Exercise Exercise2 = new Exercise(2L, 2L, "exercise2", "this is the second best workout", "Biceps", "6-10", "strength", 1, 10);


    private JdbcExerciseDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        sut = new JdbcExerciseDao(jdbcTemplate);
    }

    @Test
    public void getAll_should_return_number_of_workouts() {
        List<Exercise> exerciseList = sut.getAll();

        Assert.assertNotNull(exerciseList);
        Assert.assertEquals(43, exerciseList.size());

    }

    @Test(expected = ExerciseNotFoundException.class)
    public void getExerciseById_given_null_should_throw_exception() {
        sut.getExerciseById(null);
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void getExerciseById_when_invalid_exercise_throws_Exception() {
        sut.getExerciseById(-1L);

    }

    @Test
    public void getExerciseById_given_valid_Exercise_id_returns_exercise(){
        Exercise actualExercise = sut.getExerciseById(Exercise1.getId());

        Assert.assertEquals(Exercise1,actualExercise);
    }

    
}

