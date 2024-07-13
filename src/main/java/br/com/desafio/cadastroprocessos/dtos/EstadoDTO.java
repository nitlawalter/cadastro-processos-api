package br.com.desafio.cadastroprocessos.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EstadoDTO {
    private int id;
    private String sigla;
    private String nome;
}
