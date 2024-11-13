package com.app.gym_management.service.interfaces;

import com.app.gym_management.presentation.dto.client.ClientDto;
import com.app.gym_management.presentation.dto.client.SaveClientDto;
import com.app.gym_management.persistence.entity.Client;

import java.util.List;

public interface IClientService {

    List<ClientDto> findAll();

    ClientDto saveDto(SaveClientDto saveClientDto);

    Client save(Client client);

    ClientDto findByIdDto(Long id);

    Client findById(Long id);

    void deleteById(Long id);

}
