package com.br.murilo.dschallangeclient.modules.client.usecases;

import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import com.br.murilo.dschallangeclient.modules.client.exceptions.EntityAlreadyExistsException;
import com.br.murilo.dschallangeclient.modules.client.exceptions.EntityNotFoundException;
import com.br.murilo.dschallangeclient.modules.client.infra.repositories.ClientRepository;
import com.br.murilo.dschallangeclient.modules.client.mappers.impl.ClientMapperImpl;
import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateClientUseCase {
    private final ClientRepository repository;
    private final ClientMapperImpl mapper;

    @Transactional
    public ClientDTO execute(Long id, ClientDTO clientDTO) {
        Client client = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: "+id));
        log.info("Found client: {}", client.getName());

        existsClient(clientDTO.getCpf());
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setIncome(clientDTO.getIncome());
        client.setChildren(clientDTO.getChildren());

        client = repository.save(client);
        log.info("Update client: {}", client);
        return mapper.toDTO(client);
    }

    private void existsClient(String clientCpf) {
        repository.findByCpf(clientCpf)
                .ifPresent((category) -> {
                    String message = "Client with cpf "+clientCpf+" already exists";
                    log.info(message);
                    throw new EntityAlreadyExistsException(message);
                });
    }
}
