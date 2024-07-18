package br.com.desafio.cadastroprocessos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.nio.charset.StandardCharsets;
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

    //@Pattern(regexp = "\\\\d{7}-\\\\d{2}\\\\.\\\\d{4}\\\\.\\\\d\\\\.\\\\d{2}\\\\.\\\\d{4}", message = "NPU must be in the format 1111111-11.1111.1.11.1111")
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

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDate.now();
        if (this.documento == null) {
            this.documento = "Documento Placeholder".getBytes(StandardCharsets.UTF_8);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataVisualizacao = LocalDate.now();
        if (this.documento == null) {
            this.documento = "Documento Placeholder".getBytes(StandardCharsets.UTF_8);
        }
    }

}
