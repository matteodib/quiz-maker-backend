package com.quizmaker.services.question;

import com.quizmaker.models.Category;
import com.quizmaker.models.Question;
import com.quizmaker.models.Quiz;
import com.quizmaker.models.dtos.QuestionDTO;
import com.quizmaker.repositories.CategoryRepository;
import com.quizmaker.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsOfCategory(Long id) {
        return questionRepository.findAllByCategoryId(id);
    }

    @Override
    public Question storeQuestion(QuestionDTO request) throws Exception {
        Question newQuestion = new Question();
        newQuestion.setDescription(request.description);
        Category categoryExists = categoryRepository.findById(request.categoryId).orElseThrow(() -> new Exception());
        newQuestion.setCategory(categoryExists);
        questionRepository.save(newQuestion);
        return newQuestion;
    }

    @Override
    public Question deleteQuestion(Long quizId) {
        Question question = questionRepository.findById(quizId).orElse(null);
        if(question != null) {
            questionRepository.deleteById(quizId);
        }
        return question;
    }
}
