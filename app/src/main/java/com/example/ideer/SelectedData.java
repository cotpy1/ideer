package com.example.ideer;

public class SelectedData {
    private String chosenTopic;
    private String chosenLevel;
    private String chosenQuestion;

    public SelectedData(String chosenTopic, String chosenLevel, String chosenQuestion) {
        this.chosenTopic = chosenTopic;
        this.chosenLevel = chosenLevel;
        this.chosenQuestion = chosenQuestion;
    }

    public String getChosenTopic() {
        return chosenTopic;
    }

    public void setChosenTopic(String chosenTopic) {
        this.chosenTopic = chosenTopic;
    }

    public String getChosenLevel() {
        return chosenLevel;
    }

    public void setChosenLevel(String chosenLevel) {
        this.chosenLevel = chosenLevel;
    }

    public String getChosenQuestion() {
        return chosenQuestion;
    }

    public void setChosenQuestion(String chosenQuestion) {
        this.chosenQuestion = chosenQuestion;
    }
}
