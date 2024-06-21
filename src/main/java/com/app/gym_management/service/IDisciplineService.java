package com.app.gym_management.service;

import com.app.gym_management.dto.DisciplineDto;
import com.app.gym_management.dto.SaveDisciplineDto;
import com.app.gym_management.persistance.models.Discipline;

import java.util.List;

public interface IDisciplineService {

    List<DisciplineDto> findAll();

    DisciplineDto save(SaveDisciplineDto saveDisciplineDto);

    Discipline save(Discipline discipline);

    DisciplineDto findByIdDto(Long id);

    Discipline findById(Long id);

    void deleteById(Long id);


}
