package com.quizmaker.repositories;

import com.quizmaker.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByCategoryId(Long id);

    List<Question> findAllByIdIn(List<Long> ids);

    List<Question> findAllByIdNotIn(List<Long> ids);

    List<Question> findByIdNotInAndCategoryId(@Param("ids") List<Long> ids, @Param("categoryId") Long categoryId);
    @Query(value = "SELECT * FROM questions q WHERE q.category_id IN ?1 AND q.ranking_id = ?3 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestions(Long[] categoryIds, int numberOfQuestions, int rankingId);

}
