package com.example.loginregister;

public class Questions {
    private int id;
    private String correctText,question,choiceA,choiceB,choiceC,choiceD,difficulty;

    public Questions(int id, String correctText, String question, String choiceA,
                     String choiceB, String choiceC, String choiceD, String difficulty) {
        this.id = id;
        this.correctText = correctText;
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getCorrectText() {
        return correctText;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
