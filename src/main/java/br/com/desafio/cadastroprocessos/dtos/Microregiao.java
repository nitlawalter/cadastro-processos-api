package br.com.desafio.cadastroprocessos.dtos;

public record Microregiao(
        Long id,
        String nome,
        Mesorregiao mesorregiao
    ) {
}
