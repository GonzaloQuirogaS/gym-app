package com.app.gym_management.service.impl;

import com.app.gym_management.dto.InvoiceDto;
import com.app.gym_management.mapper.Mapper;
import com.app.gym_management.persistance.models.Invoice;
import com.app.gym_management.persistance.repository.InvoiceRepository;
import com.app.gym_management.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IInvoiceServiceImpl implements IInvoiceService {

    private final Mapper mapper;

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(mapper::mapToInvoiceDto).collect(Collectors.toList());
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public InvoiceDto findByIdDto(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return mapper.mapToInvoiceDto(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
