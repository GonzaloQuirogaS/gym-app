package com.app.gym_management.service.impl;

import com.app.gym_management.dto.DisciplineDto;
import com.app.gym_management.dto.SaveDisciplineDto;
import com.app.gym_management.mapper.Mapper;
import com.app.gym_management.persistance.models.Discipline;
import com.app.gym_management.persistance.repository.DisciplineRepository;
import com.app.gym_management.service.IDisciplineService;
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
