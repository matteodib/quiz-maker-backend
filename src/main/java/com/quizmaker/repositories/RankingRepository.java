package com.quizmaker.repositories;

import com.quizmaker.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
