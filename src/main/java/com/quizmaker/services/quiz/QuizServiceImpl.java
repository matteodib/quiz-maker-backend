package com.quizmaker.services.quiz;

import com.quizmaker.models.Category;
import com.quizmaker.models.Question;
import com.quizmaker.models.Quiz;
import com.quizmaker.models.QuizQuestion;
import com.quizmaker.models.dtos.AddQuestionsDTO;
import com.quizmaker.models.dtos.FindQuizSessionDTO;
import com.quizmaker.models.dtos.QuizDTO;
import com.quizmaker.repositories.CategoryRepository;
import com.quizmaker.repositories.QuestionRepository;
import com.quizmaker.repositories.QuizQuestionRepository;
import com.quizmaker.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Override
    public Quiz saveQuiz(QuizDTO request) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.title);
        quiz.setDescription(request.description);
        quiz.setSession(request.session);
        quiz.setCategory(categoryRepository.findById(request.categoryId).orElseThrow(() -> new Exception()));
        quizRepository.save(quiz);
        return quiz;
    }

    @Override
    public Quiz addQuestions(AddQuestionsDTO request, Long quizId) throws Exception {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception());
        List<Question> questions = questionRepository.findAllByIdIn(request.questionIds);
        for (Question question: questions) {
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuiz(quiz);
            quizQuestion.setQuestion(question);
            quizQuestionRepository.save(quizQuestion);
        }
        quizRepository.save(quiz);
        return quiz;
    }

    @Override
    public Quiz getQuiz(Long quizId) throws Exception {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception());
        return quiz;
    }

    @Override
    public Quiz getQuizBySession(FindQuizSessionDTO request) throws ResponseStatusException {
        Quiz quiz = quizRepository.findOneBySession(request.session);
        if(quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non è stato trovato il quiz in questione");
        return quiz;
    }
}