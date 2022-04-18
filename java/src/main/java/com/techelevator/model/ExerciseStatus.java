package com.techelevator.model;

public class ExerciseStatus {

    private Long status_id;
    private String status_description;

    public ExerciseStatus() {

    }

    public ExerciseStatus(Long status_id, String status_description) {
        this.status_id = status_id;
        this.status_description = status_description;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

}
