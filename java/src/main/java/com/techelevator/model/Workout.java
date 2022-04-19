package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Workout {

    private Long id;
    private Long userId;
    private Long trainerId;
    private boolean isCompleted;
    private Date dateCompleted;


    public Workout() {}

    public Workout(Long id, Long userId, Long trainerId, boolean isCompleted, Date dateCompleted){
        this.id = id;
        this.userId = userId;
        this.trainerId = trainerId;
        this.isCompleted = isCompleted;
        this.dateCompleted = dateCompleted;

    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id = " + id +
                ", userId = " + userId +
                ", trainer Id = " + trainerId +
                ", Completed = " + isCompleted +
                ", date completed = " + dateCompleted +
                "}";
    }

}


