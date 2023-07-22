package com.quizmaker.repositories;

import com.quizmaker.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByCategoryId(Long categoryId);
    Quiz findOneBySession(String session);

}
