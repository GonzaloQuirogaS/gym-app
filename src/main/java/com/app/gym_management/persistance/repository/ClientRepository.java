package com.app.gym_management.persistance.repository;

import com.app.gym_management.persistance.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
