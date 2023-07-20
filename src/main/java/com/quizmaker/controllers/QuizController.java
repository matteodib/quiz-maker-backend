package com.quizmaker.controllers;

import com.quizmaker.models.Quiz;
import com.quizmaker.models.dtos.AddQuestionsDTO;
import com.quizmaker.models.dtos.FindQuizSessionDTO;
import com.quizmaker.models.dtos.QuizDTO;
import com.quizmaker.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "quizzes")
public class QuizController {

    @Autowired
    public QuizService quizService;
    @RequestMapping("/")
    public String getQuizzes(){
        return "Hello World from Spring Boot";
    }

    @PostMapping("/")
    public Quiz storeQuiz(@RequestBody QuizDTO request) throws Exception {
        return quizService.saveQuiz(request);
    }

    @PostMapping("/add-questions/{quizId}")
    public Quiz addQuestionsToQuiz(@RequestBody AddQuestionsDTO request, @PathVariable("quizId") Long quizId) throws Exception {
        return quizService.addQuestions(request, quizId);
    }

    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") Long quizId) throws Exception {
        return quizService.getQuiz(quizId);
    }

    @PostMapping("/get-quiz-by-session")
    public Quiz getQuizBySession(@RequestBody FindQuizSessionDTO request) throws ResponseStatusException {
        return quizService.getQuizBySession(request);
    }
}
