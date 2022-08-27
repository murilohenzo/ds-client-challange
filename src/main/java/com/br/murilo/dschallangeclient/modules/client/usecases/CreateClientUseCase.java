package com.br.murilo.dschallangeclient.modules.client.usecases;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import com.br.murilo.dschallangeclient.modules.client.exceptions.EntityAlreadyExistsException;
import com.br.murilo.dschallangeclient.modules.client.infra.repositories.ClientRepository;
import com.br.murilo.dschallangeclient.modules.client.mappers.impl.ClientMapperImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CreateClientUseCase {
    private final ClientRepository repository;
    private final ClientMapperImpl mapper;

    public ClientDTO execute(ClientDTO clientDTO) {
        log.info("Client DTO: {}", clientDTO);
        existsClient(clientDTO.getCpf());
        Client client = repository.save(mapper.toModel(clientDTO));
        log.info("Create new client: {}", client);
        return mapper.toDTO(client);
    }

    private void existsClient(String cpf) {
        repository.findByCpf(cpf)
                .ifPresent((client) -> {
                    String message = "Client with cpf "+client.getCpf()+" already exists";
                    log.info(message);
                    throw new EntityAlreadyExistsException(message);
                });
    }
}
