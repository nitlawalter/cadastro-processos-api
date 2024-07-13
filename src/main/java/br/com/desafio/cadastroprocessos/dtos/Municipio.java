package br.com.desafio.cadastroprocessos.dtos;

public record Municipio(
        Long id,
        String nome,
        Microregiao microrregiao
    ) {
}
