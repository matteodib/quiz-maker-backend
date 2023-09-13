package com.quizmaker.models.dtos;

import com.quizmaker.models.Category;
import com.quizmaker.models.MultipleQuestionChoice;

public class QuestionDTO {
    public String description;
    public Long categoryId;
    public Long rankingId;
    public Long questionType;
    public MultipleQuestionChoice[] multipleQuestionChoices;
}
