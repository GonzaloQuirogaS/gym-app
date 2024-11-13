package com.app.gym_management.service.interfaces;

import com.app.gym_management.presentation.dto.invoice.InvoiceDto;
import com.app.gym_management.persistence.entity.Invoice;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    Invoice save(Invoice invoice);

    void deleteById(Long id);

    InvoiceDto findByIdDto(Long id);

    Invoice findById(Long id);
}
