package com.quizmaker.services.quiz;

import com.quizmaker.models.Quiz;
import com.quizmaker.models.dtos.AddQuestionsDTO;
import com.quizmaker.models.dtos.FindQuizSessionDTO;
import com.quizmaker.models.dtos.QuizDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public interface QuizService {

    List<Quiz> getAllQuizzes();

    List<Quiz> getQuizzesOfCategory(Long categoryId);
    Quiz saveQuiz(QuizDTO request) throws Exception;

    Quiz addQuestions(AddQuestionsDTO request, Long quizId) throws Exception;

    Quiz getQuiz(Long quizId) throws Exception;

    Quiz getQuizBySession(FindQuizSessionDTO request) throws ResponseStatusException;
    Quiz setQuizAsCompleted(Long quizId);

    void deleteQuiz(Long quizId);
}
