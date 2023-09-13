package com.quizmaker.services.question;

import com.quizmaker.models.*;
import com.quizmaker.models.dtos.QuestionDTO;
import com.quizmaker.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;
    @Autowired
    private MultipleQuestionChoiceRepository multipleQuestionChoiceRepository;
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
        Ranking ranking = rankingRepository.findById(request.rankingId).orElse(null);
        QuestionType questionType = questionTypeRepository.findById(request.questionType).orElse(null);
        if(ranking == null || questionType == null) {
            throw new Exception("No ranking or type found");
        }
        Question newQuestion = new Question();
        newQuestion.setDescription(request.description);
        Category categoryExists = categoryRepository.findById(request.categoryId).orElseThrow(() -> new Exception());
        newQuestion.setCategory(categoryExists);
        newQuestion.setRanking(ranking);
        newQuestion.setQuestionType(questionType);
        List<MultipleQuestionChoice> multipleQuestionChoices = new ArrayList<MultipleQuestionChoice>();
        if(request.multipleQuestionChoices.length > 0) {
            for (MultipleQuestionChoice multipleQuestionChoice: request.multipleQuestionChoices) {
                MultipleQuestionChoice newMultipleQuestionChoice = new MultipleQuestionChoice();
                newMultipleQuestionChoice.setText(multipleQuestionChoice.getText());
                newMultipleQuestionChoice.setIsCorrect(multipleQuestionChoice.getIsCorrect());
                multipleQuestionChoices.add(newMultipleQuestionChoice);
            }
        }
        newQuestion.setMultipleQuestionChoices(multipleQuestionChoices.stream().collect(Collectors.toSet()));
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

    @Override
    public List<Ranking> getRankings() {
        return rankingRepository.findAll();
    }

    @Override
    public List<QuestionType> getQuestionTypes() {
        return questionTypeRepository.findAll();
    }
}
