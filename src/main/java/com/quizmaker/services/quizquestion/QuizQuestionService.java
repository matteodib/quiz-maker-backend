package com.quizmaker.services.quizquestion;

import com.quizmaker.models.Question;
import com.quizmaker.models.Quiz;
import com.quizmaker.models.QuizQuestion;
import com.quizmaker.models.dtos.GetQuestionsNotInQuizDTO;
import com.quizmaker.models.dtos.RemoveQuestionFromQuizDTO;
import com.quizmaker.models.dtos.UpdateAnswerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuizQuestionService {
    List<QuizQuestion> getQuestionsOfQuiz(Long quizId);

    QuizQuestion getAnswerOfQuestion(Long quizId, Long questionId);
    List<Question> getQuestionsNotInQuiz(Long quizId, GetQuestionsNotInQuizDTO request);

    Question removeQuestionFromQuiz(RemoveQuestionFromQuizDTO request);

    QuizQuestion updateAnswer(Long quizQuestionid,UpdateAnswerDTO request);
}
