package com.quizmaker.models.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizStatisticsPOJO {
    @JsonProperty("onGoingQuizzes")
    private int onGoingQuizzes;
    @JsonProperty("endedQuizzes")
    private int endedQuizzes;
}