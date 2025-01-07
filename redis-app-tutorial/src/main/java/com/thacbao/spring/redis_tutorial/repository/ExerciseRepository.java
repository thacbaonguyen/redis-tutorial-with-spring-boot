package com.thacbao.spring.redis_tutorial.repository;

import com.thacbao.spring.redis_tutorial.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
