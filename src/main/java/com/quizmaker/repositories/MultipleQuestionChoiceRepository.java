package com.quizmaker.repositories;

import com.quizmaker.models.MultipleQuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultipleQuestionChoiceRepository extends JpaRepository<MultipleQuestionChoice, Long> {
}
