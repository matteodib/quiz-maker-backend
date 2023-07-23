package com.quizmaker.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnswerDTO {
    Long questionId;
    Long quizId;
    String answer;
}
