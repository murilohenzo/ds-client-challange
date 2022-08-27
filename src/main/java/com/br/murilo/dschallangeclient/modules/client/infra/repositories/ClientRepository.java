package com.br.murilo.dschallangeclient.modules.client.infra.repositories;

import com.br.murilo.dschallangeclient.modules.client.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
