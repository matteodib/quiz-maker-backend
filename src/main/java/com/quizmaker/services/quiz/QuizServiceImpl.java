package com.quizmaker.services.quiz;

import com.quizmaker.models.*;
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
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Long categoryId) {
        return quizRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Quiz saveQuiz(QuizDTO request) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.title);
        quiz.setDescription(request.description);
        quiz.setSession(request.session);
        quiz.setActive(true);
        quiz.setCategory(categoryRepository.findById(request.categoryId).orElseThrow(() -> new Exception()));
        quizRepository.save(quiz);
        if (request.addQuestions) {
            List<Question> questions = questionRepository.findRandomQuestions(quiz.getCategory().getId());
            for (Question question: questions) {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                quizQuestion.setAnswer("");
                quizQuestionRepository.save(quizQuestion);
            }
        }
        return quiz;
    }

    @Override
    public Quiz addQuestions(AddQuestionsDTO request, Long quizId) throws Exception {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new Exception());
        List<Question> questions = questionRepository.findAllByIdIn(request.questionIds);
        for (Question question: questions) {
            if(quiz != null && question != null) {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuiz(quiz);
                quizQuestion.setQuestion(question);
                quizQuestion.setAnswer("");
                quizQuestionRepository.save(quizQuestion);
            }
        }
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
        if(quiz.getFirstOpening() == null) {
            quiz.setFirstOpening(new Date());
            quizRepository.save(quiz);
        }
        if(quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non è stato trovato il quiz in questione");
        if(quiz.getActive() == false)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Hai già terminato il quiz");
        return quiz;
    }

    @Override
    public Quiz setQuizAsCompleted(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if(quiz != null ) {
            quiz.setSendingDate(new Date());
            quizRepository.save(quiz);
        }
        if(quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non è stato possibile trovare il quiz");
        quiz.setActive(false);
        quizRepository.save(quiz);
        return quiz;
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if(quiz != null) {
            quizRepository.deleteById(quizId);
            return;
        }
        return;
    }
}
