package com.techelevator.dao;

import com.techelevator.model.ExerciseNotFoundException;
import com.techelevator.model.ExerciseStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcExerciseStatusDao implements ExerciseStatusDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcExerciseStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getStatusById(Long status_id) {

        try {
            return jdbcTemplate.queryForObject("SELECT exercise_status_description FROM exercise_status WHERE exercise_status_id = ?", String.class, status_id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ExerciseNotFoundException("Status not found for ID" + status_id);
        }
    }

    private ExerciseStatus mapRowToExerciseStatus(SqlRowSet rs) {
        ExerciseStatus exerciseStatus = new ExerciseStatus();
        exerciseStatus.setStatus_id(rs.getLong("exercise_status_id"));
        exerciseStatus.setStatus_description(rs.getString("exercise_status_description"));
        return exerciseStatus;
    }
}
