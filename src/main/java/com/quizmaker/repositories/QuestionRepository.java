package com.quizmaker.repositories;

import com.quizmaker.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByCategoryId(Long id);

    List<Question> findAllByIdIn(List<Long> ids);
}
