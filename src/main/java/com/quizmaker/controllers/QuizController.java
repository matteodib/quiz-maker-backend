package com.quizmaker.controllers;

import com.quizmaker.models.Quiz;
import com.quizmaker.models.dtos.AddQuestionsDTO;
import com.quizmaker.models.dtos.FindQuizSessionDTO;
import com.quizmaker.models.dtos.QuizDTO;
import com.quizmaker.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping(path = "protected/quizzes")
public class QuizController {

    @Autowired
    public QuizService quizService;
    @RequestMapping("/")
    public List<Quiz> getQuizzes(){
        return quizService.getAllQuizzes();
    }

    @RequestMapping("/category/{categoryId}")
    public List<Quiz> getQuizzes(@PathVariable("categoryId") Long categoryId){
        return quizService.getQuizzesOfCategory(categoryId);
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

    @GetMapping("/set-quiz-as-completed/{quizId}")
    public Quiz setQuizAsCompleted(@PathVariable("quizId") Long quizId) {
        return quizService.setQuizAsCompleted(quizId);
    }
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
    }
}
