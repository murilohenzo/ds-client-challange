package com.br.murilo.dschallangeclient.modules.client.mappers.impl;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import com.br.murilo.dschallangeclient.modules.client.mappers.ClientMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public Client toModel(ClientDTO clientDTO) {
        if (Objects.nonNull(clientDTO)) {
            Client.ClientBuilder clientBuilder = Client.builder();
            clientBuilder.name(clientDTO.getName());
            clientBuilder.cpf(clientDTO.getCpf());
            clientBuilder.income(clientDTO.getIncome());
            clientBuilder.birthDate(clientDTO.getBirthDate());
            clientBuilder.children(clientDTO.getChildren());

            return clientBuilder.build();
        }
        return null;
    }

    @Override
    public ClientDTO toDTO(Client client) {
        if (Objects.nonNull(client)) {
            ClientDTO.ClientDTOBuilder clientDTOBuilder = ClientDTO.builder();
            clientDTOBuilder.id(client.getId());
            clientDTOBuilder.name(client.getName());
            clientDTOBuilder.cpf(client.getCpf());
            clientDTOBuilder.income(client.getIncome());
            clientDTOBuilder.birthDate(client.getBirthDate());
            clientDTOBuilder.children(client.getChildren());

            return clientDTOBuilder.build();
        }
        return null;
    }
}
