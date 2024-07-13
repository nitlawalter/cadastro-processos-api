package br.com.desafio.cadastroprocessos.dtos;

import br.com.desafio.cadastroprocessos.model.Processo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class ProcessoResponseDTO {

        private Long id;
        private String npu;
        private String dataCadastro;
        private String dataVisualizacao;
        private String municipio;
        private String uf;
        private byte[] documento;

    public static List<ProcessoResponseDTO> fromList(List<Processo> processos) {
        return processos.stream()
                .map(processo -> ProcessoResponseDTO.builder()
                        .id(processo.getId())
                        .npu(processo.getNpu())
                        .dataCadastro(processo.getDataCadastro().toString())
                        .dataVisualizacao(processo.getDataVisualizacao() != null ? processo.getDataVisualizacao().toString() : null)
                        .municipio(processo.getMunicipio())
                        .uf(processo.getUf())
                        .documento(processo.getDocumento())
                        .build())
                .toList();
    }

    public static ProcessoResponseDTO from(Processo processo) {
        return ProcessoResponseDTO.builder()
                .id(processo.getId())
                .npu(processo.getNpu())
                .dataCadastro(processo.getDataCadastro().toString())
                .dataVisualizacao(processo.getDataVisualizacao() != null ? processo.getDataVisualizacao().toString() : null)
                .municipio(processo.getMunicipio())
                .uf(processo.getUf())
                .documento(processo.getDocumento())
                .build();
    }

    public static Page<ProcessoResponseDTO> fromListPaginated(Page<Processo> processos) {
        return processos.map(processo -> ProcessoResponseDTO.builder()
                .id(processo.getId())
                .npu(processo.getNpu())
                .dataCadastro(processo.getDataCadastro().toString())
                .dataVisualizacao(processo.getDataVisualizacao() != null ? processo.getDataVisualizacao().toString() : null)
                .municipio(processo.getMunicipio())
                .uf(processo.getUf())
                .documento(processo.getDocumento())
                .build());
    }
}
