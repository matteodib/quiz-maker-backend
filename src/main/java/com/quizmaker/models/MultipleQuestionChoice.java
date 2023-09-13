package com.quizmaker.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "multiple_question_choices")
public class MultipleQuestionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_correct", columnDefinition = "boolean default false")
    private Boolean isCorrect;

    @Column(name = "text", nullable = false)
    private String text;

}
