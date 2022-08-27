package com.br.murilo.dschallangeclient.modules.client.usecases;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import com.br.murilo.dschallangeclient.modules.client.exceptions.EntityNotFoundException;
import com.br.murilo.dschallangeclient.modules.client.infra.repositories.ClientRepository;
import com.br.murilo.dschallangeclient.modules.client.mappers.impl.ClientMapperImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FindClientByIdUseCase {
    private final ClientRepository repository;
    private final ClientMapperImpl mapper;

    public ClientDTO execute(Long id) {
        log.info("Client id: {}", id);
        Client client = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id equal" + id));
        return mapper.toDTO(client);
    }
}
