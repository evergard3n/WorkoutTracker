package com.example.workouttracker;

public class Workout {
    private String reps;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //private int level;
    public Workout (String name, String reps) {
        this.name = name;
        this.reps = reps;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return name+" "+reps;
    }
}
