package com.br.murilo.dschallangeclient.modules.client.controllers.impl;

import com.br.murilo.dschallangeclient.modules.client.controllers.ClientController;
import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import com.br.murilo.dschallangeclient.modules.client.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/clients")
@AllArgsConstructor
public class ClientControllerImpl implements ClientController {
    private final FindAllPagedClientsUseCase findAllPagedClientsUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final FindClientByIdUseCase findClientByIdUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final RemoveClientUseCase removeClientUseCase;

    @GetMapping()
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return ResponseEntity.status(HttpStatus.OK).body(findAllPagedClientsUseCase.execute(pageRequest));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createClientUseCase.execute(clientDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id", required = true) String id) {
        return ResponseEntity.status(HttpStatus.OK).body(findClientByIdUseCase.execute(Long.parseLong(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable(name = "id", required = true) String id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateClientUseCase.execute(Long.parseLong(id), clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id", required = true) String id) {
        removeClientUseCase.execute(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }
}
