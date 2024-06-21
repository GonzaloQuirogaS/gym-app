package com.app.gym_management.dto;

import com.app.gym_management.persistance.models.Discipline;
import com.app.gym_management.persistance.models.Invoice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClientDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Long phone;
    private LocalDateTime registerDate = LocalDateTime.now();
    private Set<Discipline> discipline;
    private Set<Invoice> invoices;

}
