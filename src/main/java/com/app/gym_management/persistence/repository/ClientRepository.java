package com.app.gym_management.persistence.repository;

import com.app.gym_management.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
