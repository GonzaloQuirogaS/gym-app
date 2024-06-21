package com.app.gym_management.service.impl;

import com.app.gym_management.dto.ClientDto;
import com.app.gym_management.dto.SaveClientDto;
import com.app.gym_management.mapper.Mapper;
import com.app.gym_management.persistance.models.Client;
import com.app.gym_management.persistance.repository.ClientRepository;
import com.app.gym_management.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {


    private final ClientRepository clientRepository;
    private final Mapper mapper;

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map((mapper::mapToClientDto)).collect(Collectors.toList());
    }

    @Override
    public ClientDto saveDto(SaveClientDto saveClientDto) {

        Client client = new Client();
        client.setName(saveClientDto.getName());
        client.setSurname(saveClientDto.getSurname());
        client.setAge(saveClientDto.getAge());
        client.setEmail(saveClientDto.getEmail());
        client.setPhone(saveClientDto.getPhone());

        clientRepository.save(client);

        return mapper.mapToClientDto(client);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientDto findByIdDto(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return mapper.mapToClientDto(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
