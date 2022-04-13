package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDao implements ExerciseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Exercise> getAll() {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "select * from exercises";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Exercise exercise = mapRowToExercise(results);
            exercises.add(exercise);
        }

        return exercises;
    }

    @Override
    public Exercise getExerciseById(Long exerciseId) {
        String sql = "SELECT * FROM exercises WHERE exercise_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, exerciseId);
        if (results.next()) {
            return mapRowToExercise(results);
        } else {
            throw new ExerciseNotFoundException("Exercise not found");
        }
    }

    @Override
    public Exercise findByExerciseName(String name) throws ExerciseNotFoundException {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException();
        }

        for (Exercise exercise : this.getAll()) {
            if (exercise.getName().toLowerCase().equals(name.toLowerCase())) {
                return exercise;

            }
        }
        throw new ExerciseNotFoundException("Exercise " + name + " was not found.");
    }

    @Override
    public List<Exercise> findByMuscleGroup(String group) {
        List<Exercise> exercises  = new ArrayList<>();
        String sql = "SELECT * FROM exercises WHERE muscle_group = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,group);

        while(results.next()){
            Exercise exercise = mapRowToExercise(results);
            exercises.add(exercise);
        }
        return  exercises;
    }

    @Override
    public Exercise findByType(String type) {
        return null;
    }

    @Override
    public boolean create(String name, String description, String muscleGroup) {
        return false;
    }

    private Exercise mapRowToExercise(SqlRowSet rs) {
        Exercise exercise = new Exercise();
        exercise.setId(rs.getLong("exercise_id"));
        exercise.setName(rs.getString("exercise_name"));
        exercise.setDescription(rs.getString("exercise_description"));
        exercise.setMuscleGroup(rs.getString("muscle_group"));
        exercise.setRepRange(rs.getInt("rep_range"));
        exercise.setType(rs.getString("exercise_type"));
        return exercise;
    }
}
