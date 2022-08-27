package com.br.murilo.dschallangeclient.modules.client.usecases;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.infra.repositories.ClientRepository;
import com.br.murilo.dschallangeclient.modules.client.mappers.impl.ClientMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FindAllPagedClientsUseCase {
    private final ClientRepository repository;
    private final ClientMapperImpl mapper;

    @Transactional(readOnly = true)
    public Page<ClientDTO> execute(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(mapper::toDTO);
    }
}
