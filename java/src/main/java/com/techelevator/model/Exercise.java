package com.techelevator.model;

public class Exercise {

    private Long id;
    private String name;
    private String description;
    private String muscleGroup;
    private Integer repRange;
    // Get clarification on type
    private String type;

    public Exercise() {

    }

    public Exercise(Long id, String name, String description,
                    String muscleGroup, Integer repRange, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.repRange = repRange;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public Integer getRepRange() {
        return repRange;
    }

    public void setRepRange(Integer repRange) {
        this.repRange = repRange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", muscleGroup=" + muscleGroup +
                ", repRange=" + repRange +
                ", type=" + type +
                "}";
    }
}
