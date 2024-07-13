package br.com.desafio.cadastroprocessos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^[0-9]{7}-[0-9]{2}\\.[0-9]{4}\\.1\\.[0-9]{2}\\.[0-9]{4}$", message = "NPU must be in the format 1111111-11.1111.1.11.1111")
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
