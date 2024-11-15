package com.app.gym_management.presentation.dto.discipline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineDto implements Serializable {

    private Long id;
    private String name;
    private Integer price;
}
