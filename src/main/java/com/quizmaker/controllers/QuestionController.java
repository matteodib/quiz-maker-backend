package com.quizmaker.controllers;

import com.quizmaker.models.Category;
import com.quizmaker.models.Question;
import com.quizmaker.models.dtos.QuestionDTO;
import com.quizmaker.services.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "protected/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{categoryId}")
    public List<Question> getQuestionsOfCategory(@PathVariable("categoryId") Long categoryId){
        return questionService.getQuestionsOfCategory(categoryId);
    }

    @PostMapping("/")
    public Question storeQuestion(@RequestBody QuestionDTO request) throws Exception {
        return questionService.storeQuestion(request);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.deleteQuestion(questionId);
    }
}
