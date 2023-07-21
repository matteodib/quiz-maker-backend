package com.quizmaker.services.quizquestion;

import com.quizmaker.models.Question;
import com.quizmaker.models.Quiz;
import com.quizmaker.models.QuizQuestion;
import com.quizmaker.models.dtos.GetQuestionsNotInQuizDTO;
import com.quizmaker.models.dtos.RemoveQuestionFromQuizDTO;
import com.quizmaker.models.dtos.UpdateAnswerDTO;
import com.quizmaker.repositories.QuestionRepository;
import com.quizmaker.repositories.QuizQuestionRepository;
import com.quizmaker.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizQuestionServiceImpl implements QuizQuestionService{
    @Autowired
    QuizQuestionRepository quizQuestionRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<QuizQuestion> getQuestionsOfQuiz(Long quizId) {
        List<QuizQuestion> quizQuestion = quizQuestionRepository.findAllByQuizId(quizId);
        return quizQuestion;
    }

    @Override
    public List<Question> getQuestionsNotInQuiz(Long quizId, GetQuestionsNotInQuizDTO request) {
        ArrayList<Long> questionIdsInQuiz = new ArrayList<>();
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        List<Question> questions = quiz.getQuestions();
        for (Question question: questions) {
            questionIdsInQuiz.add(question.getId());
        }
        List<Question> availableQuestions = new ArrayList<>();
        if(request.getCategoryId() != null) {
            availableQuestions = questionRepository.findByIdNotInAndCategoryId(questionIdsInQuiz, request.getCategoryId());
        } else {
            availableQuestions = questionRepository.findByIdNotIn(questionIdsInQuiz);
        }
        return availableQuestions;
    }

    @Override
    public Question removeQuestionFromQuiz(RemoveQuestionFromQuizDTO request) {
        Question question = questionRepository.findById(request.getQuestionId()).orElse(null);
        Quiz quiz = quizRepository.findById(request.getQuizId()).orElse(null);
        if(question != null && quiz != null) {
            quizQuestionRepository.deleteByQuizIdAndQuestionId(request.getQuizId(), request.getQuestionId());
        }
        return question;
    }

    @Override
    public Quiz updateQuestionAnswer(UpdateAnswerDTO request) {
        Question question = questionRepository.findById(request.getQuestionId()).orElse(null);
        Quiz quiz = quizRepository.findById(request.getQuizId()).orElse(null);
        if(question != null && quiz != null) {
            QuizQuestion quizQuestion = quizQuestionRepository.findOneByQuizIdAndQuestionId(request.getQuizId(), request.getQuestionId());
            quizQuestion.setAnswer(request.getAnswer());
            quizQuestionRepository.save(quizQuestion);
        }
        return quiz;
    }
}
