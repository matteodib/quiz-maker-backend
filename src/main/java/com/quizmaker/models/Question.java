package com.quizmaker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "ranking_id", nullable = true)
    private Ranking ranking;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private QuestionType questionType;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Set<MultipleQuestionChoice> multipleQuestionChoices = new HashSet<MultipleQuestionChoice>();
}
