package com.example.mmustappraisal;

public class Training_Modal {
    String training_Agreed;
    String training_duration;
    String training_comments;

    public Training_Modal(){}

    public Training_Modal(String training_Agreed, String training_duration, String training_comments) {
        this.training_Agreed = training_Agreed;
        this.training_duration = training_duration;
        this.training_comments = training_comments;
    }

    public String getTraining_Agreed() {
        return training_Agreed;
    }

    public void setTraining_Agreed(String training_Agreed) {
        this.training_Agreed = training_Agreed;
    }

    public String getTraining_duration() {
        return training_duration;
    }

    public void setTraining_duration(String training_duration) {
        this.training_duration = training_duration;
    }

    public String getTraining_comments() {
        return training_comments;
    }

    public void setTraining_comments(String training_comments) {
        this.training_comments = training_comments;
    }
}
