package com.app.gym_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto implements Serializable {

    private Long id;
    private String number;
    private String discipline;
    private LocalDateTime createdTime;
    private Integer total;
    private String client;

}
