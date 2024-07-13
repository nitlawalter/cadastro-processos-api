package br.com.desafio.cadastroprocessos.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String npu;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    private LocalDate dataVisualizacao;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false, length = 2)
    private String uf;

    @Lob
    @Column(nullable = false)
    private byte[] documento;

}
