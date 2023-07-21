package com.quizmaker.repositories;

import com.quizmaker.models.Question;
import com.quizmaker.models.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    public List<QuizQuestion> findAllByQuizId(Long quizId);
    @Transactional
    public void deleteByQuizIdAndQuestionId(Long quizId, Long questionId);

    public QuizQuestion findOneByQuizIdAndQuestionId(Long quizId, Long questionId);
}
