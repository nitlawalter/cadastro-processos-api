package br.com.desafio.cadastroprocessos.dtos;

import br.com.desafio.cadastroprocessos.model.Processo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProcessoRequestDTO {
        private String npu;
        private String municipio;
        private String uf;
        private byte[] documento;

    public static Processo to(ProcessoRequestDTO processo) {
        return Processo.builder()
                .npu(processo.getNpu())
                .municipio(processo.getMunicipio())
                .uf(processo.getUf())
                .documento(processo.getDocumento())
                .build();
    }
}
