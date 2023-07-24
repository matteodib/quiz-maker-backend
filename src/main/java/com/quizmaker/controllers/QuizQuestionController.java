package com.quizmaker.controllers;

import com.quizmaker.models.Question;
import com.quizmaker.models.Quiz;
import com.quizmaker.models.QuizQuestion;
import com.quizmaker.models.dtos.GetQuestionsNotInQuizDTO;
import com.quizmaker.models.dtos.RemoveQuestionFromQuizDTO;
import com.quizmaker.models.dtos.UpdateAnswerDTO;
import com.quizmaker.services.quizquestion.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "protected/quiz-question")
public class QuizQuestionController {
    @Autowired
    QuizQuestionService quizQuestionService;

    @GetMapping("/{quizId}")
    public List<QuizQuestion> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        return quizQuestionService.getQuestionsOfQuiz(quizId);
    }

    @GetMapping("/quiz/{quizId}/question/{questionId}")
    public QuizQuestion getAnswerOfQuestion(@PathVariable("quizId") Long quizId, @PathVariable("questionId") Long questionId) {
        return quizQuestionService.getAnswerOfQuestion(quizId, questionId);
    }
    @PostMapping("get-questions-not-in-quiz/{quizId}")
    public List<Question> getQuestionsNotInQuiz(@PathVariable("quizId") Long quizId, @RequestBody GetQuestionsNotInQuizDTO request) {
    return quizQuestionService.getQuestionsNotInQuiz(quizId, request);
    }

    @PatchMapping("remove-question-from-quiz")
    public Question getQuestionsNotInQuiz(@RequestBody RemoveQuestionFromQuizDTO request) {
        return quizQuestionService.removeQuestionFromQuiz(request);
    }

}
