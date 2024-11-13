package com.app.gym_management.presentation.controllers.client;

import com.app.gym_management.presentation.dto.client.ClientDto;
import com.app.gym_management.presentation.dto.client.SaveClientDto;
import com.app.gym_management.persistence.entity.Client;
import com.app.gym_management.persistence.entity.Discipline;
import com.app.gym_management.persistence.entity.Invoice;
import com.app.gym_management.service.interfaces.IClientService;
import com.app.gym_management.service.interfaces.IDisciplineService;
import com.app.gym_management.service.interfaces.IInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    private final IDisciplineService disciplineService;

    private final IInvoiceService IInvoiceService;

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping
    @Tag(name = "GET", description = "Get Methods")
    @Operation(summary = "Get all clients",
            description = "Get all clients")
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/save")
    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Create a client",
            description = "Create a client")
    public ResponseEntity<?> save(@RequestBody @Valid SaveClientDto saveClientDto, BindingResult result) {
        try {
            if (result.hasFieldErrors()) {
                return validation(result);
            }
            ClientDto clientDto = clientService.saveDto(saveClientDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clientDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible crear el ciente, verifique sus datos!");
        }
    }

    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Get client by ID",
            description = "Get client by ID")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {
        try {
            ClientDto clientDto = clientService.findByIdDto(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No existe cliente con ese id!");
        }
    }

    @PutMapping("/set-discipline/{id}")
    @Tag(name = "PUT", description = "Put Methods")
    @Operation(summary = "Set discipline", description = "Set discipline to a client & generate an invoice")
    public ResponseEntity<?> setDiscipline(@PathVariable Long id, @RequestBody Long idDiscipline) {
        try {
            Client client = clientService.findById(id);
            Discipline discipline = disciplineService.findById(idDiscipline);

            Invoice invoice = new Invoice();
            UUID numero = UUID.randomUUID();
            invoice.setClient(client);
            invoice.setNumber(numero.toString());
            invoice.setDiscipline(discipline.getName());
            invoice.setTotal(discipline.getPrice());
            invoice.setCreatedTime(LocalDateTime.now());

            client.addDiscipline(discipline);
            client.addInvoice(invoice);
            IInvoiceService.save(invoice);
            clientService.save(client);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(invoice);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar disciplina al usuario! Verifique si el cliente y la disciplina existen!");
        }
    }

    @PutMapping("/update/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Update client", description = "Update client")
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SaveClientDto saveClientDto, BindingResult result) {
        try {
            if (result.hasFieldErrors()) {
                return validation(result);
            }
            Client client = clientService.findById(id);
            client.setName(saveClientDto.getName());
            client.setSurname(saveClientDto.getSurname());
            client.setEmail(saveClientDto.getEmail());
            client.setPhone(saveClientDto.getPhone());
            client.setAge(saveClientDto.getAge());

            clientService.save(client);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el ciente!");
        }
    }

    @DeleteMapping("/delete-discipline/{id}")
    @Tag(name = "DELETE", description = "Delete Methods")
    @Operation(summary = "Delete a client's discipline",
            description = "Delete a client's discipline, using Client's id as a parameter and Discipline's id as body")
    private ResponseEntity<?> deleteDiscipline(@PathVariable Long id, @RequestBody Long idDiscipline) {
        try {
            Optional<Client> clientDtoOptional = Optional.ofNullable(clientService.findById(id));
            if (clientDtoOptional.isPresent()) {
                Client client = clientDtoOptional.get();
                Optional<Discipline> disciplineOptional = Optional.ofNullable(disciplineService.findById(idDiscipline));

                if (disciplineOptional.isPresent()) {
                    Discipline discipline = disciplineOptional.get();
                    client.getDiscipline().remove(discipline);
                    clientService.save(client);
                }
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientService.findById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar la disciplina del cliente, revise si el id ingresado es correcto!");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Delete a client by ID",
            description = "Delete a client by ID")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar al cliente, asegurese de eliminar sus disciplinas y facturas y de que el cliente exista primero!");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Eliminado con exito!");
    }
}
