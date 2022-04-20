package com.techelevator.dao;

import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcWorkoutDaoTests extends BaseDaoTests {
    protected static final Workout WORKOUT_1 = new Workout(1L,"Workout1",1L,1L,false,LocalDate.parse("2020-04-20"));

    protected static final Workout WORKOUT_2 = new Workout(2L,"Workout1",2L,2L,true, LocalDate.parse("2020-04-20"));


    private JdbcWorkoutDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        sut = new JdbcWorkoutDao(jdbcTemplate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWorkOutById_given_null_throws_Exception(){
        sut.getWorkoutById(null);
    }

    @Test(expected = WorkoutNotFoundException.class)
    public void getWorkOutById_given_invalid_workout_throws_exception(){
        sut.getWorkoutById(3L);
    }

    @Test
    public void getWorkOutById_given_valid_Workout_returns_workout(){

        Workout workout = sut.getWorkoutById(1L);

        Assert.assertEquals(WORKOUT_1,workout);
    }


}
