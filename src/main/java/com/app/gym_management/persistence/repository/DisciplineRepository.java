package com.app.gym_management.persistence.repository;

import com.app.gym_management.persistence.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
