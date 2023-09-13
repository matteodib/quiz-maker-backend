package com.quizmaker.repositories;

import com.quizmaker.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT * FROM quizzes q JOIN quizzes_categories qc ON q.id = qc.quiz_id WHERE qc.category_id = ?1  ORDER BY q.id", nativeQuery = true)
    List<Quiz> findAllByCategoryIds(Long categoryId);
    Quiz findOneBySession(String session);
    List<Quiz> findAllByActive(boolean active);

}
