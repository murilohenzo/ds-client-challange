package com.br.murilo.dschallangeclient.modules.client.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;
    @NotNull
    private Double income;
    @NotNull
    private Instant birthDate;
    @NotNull
    private Integer children;
}
