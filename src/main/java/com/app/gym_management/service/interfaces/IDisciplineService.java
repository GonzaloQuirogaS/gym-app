package com.app.gym_management.service.interfaces;

import com.app.gym_management.presentation.dto.discipline.DisciplineDto;
import com.app.gym_management.presentation.dto.discipline.SaveDisciplineDto;
import com.app.gym_management.persistence.entity.Discipline;

import java.util.List;

public interface IDisciplineService {

    List<DisciplineDto> findAll();

    DisciplineDto save(SaveDisciplineDto saveDisciplineDto);

    Discipline save(Discipline discipline);

    DisciplineDto findByIdDto(Long id);

    Discipline findById(Long id);

    void deleteById(Long id);


}
