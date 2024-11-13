package com.app.gym_management.presentation.controllers.invoice;

import com.app.gym_management.presentation.dto.invoice.InvoiceDto;
import com.app.gym_management.service.interfaces.IInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final IInvoiceService IInvoiceService;

    @GetMapping()
    @Tag(name = "GET")
    @Operation(summary = "Get all invoices",
            description = "Get all invoices")
    private ResponseEntity<List<InvoiceDto>> findAll() {
        List<InvoiceDto> invoices = IInvoiceService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(invoices);
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Delete invoice by ID",
            description = "Delete invoide by ID")
    private ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            InvoiceDto invoice = IInvoiceService.findByIdDto(id);
            IInvoiceService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(invoice);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No existe factura con ese id!");
        }
    }
}
