package com.br.murilo.dschallangeclient.modules.client.controllers;

import com.br.murilo.dschallangeclient.modules.client.domain.dto.ClientDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Component
@Api(value = "/v1/clients", produces = "application/json", tags = "Realiza operações relacionadas a clientes")
public interface ClientController {
    @ApiOperation(
            value = "Responsável por salvar cliente",
            httpMethod = "POST",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, message = ""),
    })
    public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientDTO clientDTO);

    @ApiOperation(
            value = "Responsável por listar todos os clientes de forma paginavel",
            httpMethod = "GET",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = ""),
    })
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    );

    @ApiOperation(
            value = "Responsável por listar cliente pelo id",
            httpMethod = "GET",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = ""),
    })
    public ResponseEntity<ClientDTO> findById(@PathVariable String id);

    @ApiOperation(
            value = "Responsável por atualizar cliente",
            httpMethod = "PUT",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = ""),
    })
    public ResponseEntity<ClientDTO> update(@PathVariable(name = "id", required = true) String id, @RequestBody ClientDTO clientDTO);

    @ApiOperation(
            value = "Responsável por remover o cliente pelo id",
            httpMethod = "DELETE",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204, message = ""),
    })
    public ResponseEntity delete(@PathVariable String id);
}