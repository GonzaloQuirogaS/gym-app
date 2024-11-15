package com.app.gym_management.service.impl;

import com.app.gym_management.presentation.dto.discipline.DisciplineDto;
import com.app.gym_management.presentation.dto.discipline.SaveDisciplineDto;
import com.app.gym_management.util.mapper.Mapper;
import com.app.gym_management.persistence.entity.Discipline;
import com.app.gym_management.persistence.repository.DisciplineRepository;
import com.app.gym_management.service.interfaces.IDisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements IDisciplineService {

    private final DisciplineRepository disciplineRepository;

    private final Mapper mapper;

    @Override
    public List<DisciplineDto> findAll() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        return disciplines.stream().map(mapper::mapToDisciplineDto).collect(Collectors.toList());
    }

    @Override
    public DisciplineDto save(SaveDisciplineDto saveDisciplineDto) {

        Discipline discipline = new Discipline();
        discipline.setName(saveDisciplineDto.getName());
        discipline.setPrice(saveDisciplineDto.getPrice());

        disciplineRepository.save(discipline);

        return mapper.mapToDisciplineDto(discipline);
    }

    @Override
    public Discipline save(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    @Override
    public DisciplineDto findByIdDto(Long id) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        return mapper.mapToDisciplineDto(discipline);
    }

    @Override
    public Discipline findById(Long id) {
        return disciplineRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        disciplineRepository.deleteById(id);
    }
}
