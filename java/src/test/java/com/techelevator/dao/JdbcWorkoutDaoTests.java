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
    protected static final Workout WORKOUT_1 = new Workout(1L,"The Mike Levy",3L,1L,false,LocalDate.parse("2020-04-20"));

    protected static final Workout WORKOUT_2 = new Workout(2L,"The Dave Hoy",3L,1L,false, LocalDate.parse("2020-04-20"));

    protected static final Workout WORKOUT_3 = new Workout(1L, "The Mike Levy", 1L, 3L, false, null);


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

    @Test
    public void getAllWorkouts_returns_expected() {
        List<Workout> actual = sut.getAll();

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void getWorkoutByUserId_returns_expected() {
        List<Workout> actual = sut.getWorkoutByUserId(1L);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void getWorkoutById_returns_expected() {
        Workout actual = sut.getWorkoutById(1L);

        Assert.assertEquals(WORKOUT_3, actual);
    }


}
