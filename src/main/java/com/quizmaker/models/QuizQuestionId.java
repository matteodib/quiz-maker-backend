package com.quizmaker.models;

import java.io.Serializable;

public class QuizQuestionId implements Serializable {
    public Quiz quiz;
    public Question question;

    public String answer;
}
