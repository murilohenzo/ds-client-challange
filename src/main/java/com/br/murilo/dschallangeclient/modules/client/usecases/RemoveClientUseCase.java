package com.br.murilo.dschallangeclient.modules.client.usecases;

import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import com.br.murilo.dschallangeclient.modules.client.exceptions.EntityNotFoundException;
import com.br.murilo.dschallangeclient.modules.client.infra.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RemoveClientUseCase {
    private final ClientRepository repository;

    public void execute(Long id) {
        log.info("Client id: {}", id);
        Client client = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id equal" + id));
        repository.delete(client);
    }
}
