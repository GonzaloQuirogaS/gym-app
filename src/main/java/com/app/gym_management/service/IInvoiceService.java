package com.app.gym_management.service;

import com.app.gym_management.dto.InvoiceDto;
import com.app.gym_management.persistance.models.Invoice;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    Invoice save(Invoice invoice);

    void deleteById(Long id);

    InvoiceDto findByIdDto(Long id);

    Invoice findById(Long id);
}
