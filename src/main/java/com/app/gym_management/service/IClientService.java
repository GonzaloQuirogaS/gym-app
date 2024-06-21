package com.app.gym_management.service;

import com.app.gym_management.dto.ClientDto;
import com.app.gym_management.dto.SaveClientDto;
import com.app.gym_management.persistance.models.Client;

import java.util.List;

public interface IClientService {

    List<ClientDto> findAll();

    ClientDto saveDto(SaveClientDto saveClientDto);

    Client save(Client client);

    ClientDto findByIdDto(Long id);

    Client findById(Long id);

    void deleteById(Long id);

}
