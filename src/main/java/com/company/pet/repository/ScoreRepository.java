package com.company.pet.repository;

import com.company.pet.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreRepository extends JpaRepository<Score, UUID> {
}
