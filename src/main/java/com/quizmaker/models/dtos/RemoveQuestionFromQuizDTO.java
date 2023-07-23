package com.quizmaker.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveQuestionFromQuizDTO {
    private Long questionId;
    private Long quizId;
}
