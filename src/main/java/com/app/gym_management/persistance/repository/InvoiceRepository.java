package com.app.gym_management.persistance.repository;

import com.app.gym_management.persistance.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


}
