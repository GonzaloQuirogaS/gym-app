package com.app.gym_management.presentation.dto.discipline;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveDisciplineDto implements Serializable {
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    private Integer price;
}
