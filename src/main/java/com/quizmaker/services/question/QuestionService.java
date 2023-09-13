package com.quizmaker.services.question;

import com.quizmaker.models.Question;
import com.quizmaker.models.QuestionType;
import com.quizmaker.models.Ranking;
import com.quizmaker.models.dtos.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> getAllQuestions();
    List<Question> getQuestionsOfCategory(Long id);
    Question storeQuestion(QuestionDTO request) throws Exception;
    Question deleteQuestion(Long questionId);
    List<Ranking> getRankings();

    List<QuestionType> getQuestionTypes();
}
