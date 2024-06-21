package com.app.gym_management.controllers;

import com.app.gym_management.dto.DisciplineDto;
import com.app.gym_management.dto.SaveDisciplineDto;
import com.app.gym_management.persistance.models.Discipline;
import com.app.gym_management.service.IDisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discipline")
@RequiredArgsConstructor
public class DisciplineController {

    private final IDisciplineService disciplineService;

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Get all disciplines",
            description = "Get all discipliness")
    private ResponseEntity<List<DisciplineDto>> findAll() {
        List<DisciplineDto> disciplines = disciplineService.findAll();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Get discipline by ID",
            description = "Get discipline by ID")
    ResponseEntity<?> findDisciplineById(@PathVariable Long id) {
        try {
            DisciplineDto disciplineDto = disciplineService.findByIdDto(id);
            return ResponseEntity.ok(disciplineDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna disciplina!");
        }
    }

    @PutMapping("/update/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Update discipline", description = "Update discipline")
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SaveDisciplineDto saveDisciplineDto, BindingResult result) {
        try {
            if (result.hasFieldErrors()) {
                return validation(result);
            }
            Discipline discipline = disciplineService.findById(id);
            discipline.setName(saveDisciplineDto.getName());
            discipline.setPrice(saveDisciplineDto.getPrice());

            disciplineService.save(discipline);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(discipline);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la disciplina!");
        }
    }

    @PostMapping("/save")
    @Tag(name = "POST")
    @Operation(summary = "Create a discipline",
            description = "Create a discipline")
    private ResponseEntity<?> save(@RequestBody @Valid SaveDisciplineDto saveDisciplineDto, BindingResult result) {
        try {
            if (result.hasFieldErrors()) {
                return validation(result);
            }
            DisciplineDto discipline = disciplineService.save(saveDisciplineDto);
            return ResponseEntity.ok(discipline);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible crear la disciplina, verifique los datos!");
        }
    }

    @DeleteMapping("delete/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Delete a discipline",
            description = "Delete a discipline")
    public ResponseEntity<?> deleteDisciplineById(@PathVariable Long id) {
        try {
            DisciplineDto disciplineDto = disciplineService.findByIdDto(id);
            disciplineService.deleteById(id);
            return ResponseEntity.ok(disciplineDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar la disciplina, asegurese de que ningun usuario este inscripto antes de eliminarla!");
        }
    }
}
