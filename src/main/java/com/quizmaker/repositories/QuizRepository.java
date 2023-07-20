package com.quizmaker.repositories;

import com.quizmaker.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findOneBySession(String session);

}
