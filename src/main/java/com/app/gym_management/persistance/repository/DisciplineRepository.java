package com.app.gym_management.persistance.repository;

import com.app.gym_management.persistance.models.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
