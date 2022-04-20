package com.techelevator.dao;

import com.techelevator.model.ExerciseNotFoundException;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutExercise;
import com.techelevator.model.WorkoutNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWorkoutExerciseDao implements WorkoutExerciseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcWorkoutExerciseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public WorkoutExercise getExerciseByWorkoutId(long workoutId) {
        String sql = "SELECT exercise_id WHERE workout_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);
        if(results.next()) {
            return mapRowToWorkoutExercise(results);
        } else {
            throw new ExerciseNotFoundException("Exercise not found.");
        }
    }

    @Override
    public void add(Long newWorkoutId, Long exerciseId ){
        String sql = "INSERT INTO workout_exercise(" +
                "workout_id, exercise_id) " +
                "VALUES (?,?)";
        Long workoutId = jdbcTemplate.queryForObject(sql, Long.class, newWorkoutId, exerciseId);

    }

    private WorkoutExercise mapRowToWorkoutExercise(SqlRowSet rs) {
        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkoutId(rs.getLong("workout_id"));
        workoutExercise.setExerciseId(rs.getLong("exercise_id"));

        return workoutExercise;
    }
}
