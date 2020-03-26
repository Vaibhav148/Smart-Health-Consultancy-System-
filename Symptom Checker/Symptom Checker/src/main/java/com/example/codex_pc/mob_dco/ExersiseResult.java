package com.example.codex_pc.mob_dco;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ExersiseResult {
    @SerializedName("exercises")
    List<ExercisePOJO> exercises;

    public List<ExercisePOJO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExercisePOJO> exercises) {
        this.exercises = exercises;
    }
}
