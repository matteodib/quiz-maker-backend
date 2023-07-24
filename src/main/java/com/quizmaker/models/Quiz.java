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
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 10240)
    private String description;

    @Column(name = "session", nullable = false, unique = true)
    private String session;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @Column(name = "firstOpened", nullable = true)
    private Date firstOpening;

    @Column(name = "sendingDate", nullable = true)
    private Date sendingDate;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "quizzes_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();


}
