package com.br.murilo.dschallangeclient.modules.client.mappers;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toModel(ClientDTO clientDTO);
    ClientDTO toDTO(Client client);
}
