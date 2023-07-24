package com.quizmaker.controllers;

import com.quizmaker.models.Quiz;
import com.quizmaker.models.QuizQuestion;
import com.quizmaker.models.dtos.FindQuizSessionDTO;
import com.quizmaker.models.dtos.UpdateAnswerDTO;
import com.quizmaker.services.quiz.QuizService;
import com.quizmaker.services.quizquestion.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    QuizService quizService;
    @Autowired
    QuizQuestionService quizQuestionService;
    @GetMapping("/hello")
    public String hello() {
        return "Ciaoooo";
    }

    @PostMapping("/get-quiz-by-session")
    public Quiz getQuizBySession(@RequestBody FindQuizSessionDTO request) throws ResponseStatusException {
        return quizService.getQuizBySession(request);
    }

    @GetMapping("/get-quiz-questions/{quizId}")
    public List<QuizQuestion> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        return quizQuestionService.getQuestionsOfQuiz(quizId);
    }

    @PostMapping("/update-answer-question/{quizQuestionId}")
    public QuizQuestion updateAnswer(@PathVariable("quizQuestionId") Long quizQuestionId, @RequestBody UpdateAnswerDTO request) {
        return quizQuestionService.updateAnswer(quizQuestionId, request);
    }

    @GetMapping("/set-quiz-as-completed/{quizId}")
    public Quiz setQuizAsCompleted(@PathVariable("quizId") Long quizId) {
        return quizService.setQuizAsCompleted(quizId);
    }
}
