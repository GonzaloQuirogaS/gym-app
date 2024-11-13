package com.app.gym_management.persistence.repository;

import com.app.gym_management.persistence.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


}
