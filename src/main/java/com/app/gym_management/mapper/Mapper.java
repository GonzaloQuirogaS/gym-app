package com.app.gym_management.mapper;

import com.app.gym_management.dto.ClientDto;
import com.app.gym_management.dto.DisciplineDto;
import com.app.gym_management.dto.InvoiceDto;
import com.app.gym_management.persistance.models.Client;
import com.app.gym_management.persistance.models.Discipline;
import com.app.gym_management.persistance.models.Invoice;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public DisciplineDto mapToDisciplineDto(Discipline discipline) {
        if (discipline == null) return null;

        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        disciplineDto.setPrice(discipline.getPrice());

        return disciplineDto;
    }

    public ClientDto mapToClientDto(Client client) {
        if (client == null) return null;

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setAge(client.getAge());
        clientDto.setPhone(client.getPhone());
        clientDto.setRegisterDate(client.getRegisterDate());
        clientDto.setEmail(client.getEmail());
        clientDto.setDiscipline(client.getDiscipline());
        clientDto.setInvoices(client.getInvoices());

        return clientDto;
    }

    public InvoiceDto mapToInvoiceDto(Invoice invoice) {
        if (invoice == null) return null;

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setNumber(invoice.getNumber());
        invoiceDto.setCreatedTime(invoice.getCreatedTime());
        invoiceDto.setClient(invoice.getClient().getName()+" "+invoice.getClient().getSurname());
        invoiceDto.setDiscipline(invoice.getDiscipline());
        invoiceDto.setTotal(invoice.getTotal());

        return invoiceDto;
    }
}
