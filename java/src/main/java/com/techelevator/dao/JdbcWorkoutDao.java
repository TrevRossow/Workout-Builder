package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcWorkoutDao implements WorkoutDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Workout> getAll() {
        List<Workout> workouts = new ArrayList<>();

        String sql = "SELECT * FROM workouts";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Workout workout = mapRowToWorkout(results);
            workouts.add(workout);
        }
        return workouts;
    }

    @Override
    public List<Workout> getWorkoutByUserId(Long userId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workouts WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Workout workout = mapRowToWorkout(results);
            workouts.add(workout);
        }
        return workouts;
    }

    @Override
    public Workout getWorkoutById(Long workoutId) {
        String sql = "SELECT * FROM workouts WHERE workout_id = ? ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);
        if (results.next()) {
            return mapRowToWorkout(results);
        } else {
            throw new WorkoutNotFoundException("Workout not found.");
        }
    }
    @Override
    public Workout getDateCompleted(Long userId){
        String sql = "SELECT * FROM workouts WHERE user_id = ? ORDER BY workout_date Limit 1 ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            return mapRowToWorkout(results);
        } else {
            throw new WorkoutNotFoundException("Workout not found.");
        }
    }
    @Override
    public Workout create(Workout workout){
        String sql = "INSERT INTO workouts (" +
                " workout_name, trainer_id, user_id, completed, workout_date) " +
                "VALUES (?, ?,?,?,?) RETURNING workout_id";
        Long workoutId = jdbcTemplate.queryForObject(sql, Long.class, workout.getWorkoutName(), workout.getTrainerId(), workout.getUserId(), workout.isCompleted(), workout.getDateCompleted());
        return this.getWorkoutById(workoutId);
    }

    @Override
    public boolean deleteWorkout(Long id) {
        String sql = "DELETE FROM workouts WHERE workout_id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public boolean updateWorkout(Long id, Workout updatedWorkout) {
        String sql = "UPDATE workouts SET completed = ?, workout_date = ? WHERE workout_id = ?";
        return jdbcTemplate.update(sql, updatedWorkout.isCompleted(), updatedWorkout.getDateCompleted(), id) == 1;
    }


    private Workout mapRowToWorkout(SqlRowSet rs) {
        Workout workout = new Workout();
        workout.setId(rs.getLong("workout_id"));
        workout.setWorkoutName(rs.getString("workout_name"));
        workout.setTrainerId(rs.getLong("trainer_id"));
        workout.setUserId(rs.getLong("user_id"));
        workout.setCompleted(rs.getBoolean("completed"));
        java.sql.Date workout_date = rs.getDate("workout_date");
        if(workout_date != null) {
            workout.setDateCompleted(workout_date.toLocalDate());
        }
        return workout;
    }
}
