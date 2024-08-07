package br.com.desafio.cadastroprocessos.service;

import br.com.desafio.cadastroprocessos.model.Processo;
import br.com.desafio.cadastroprocessos.repository.ProcessoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessoService {

    private final ProcessoRepository processoRepository;
    private final ObjectMapper objectMapper;

    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    public Optional<Processo> findById(Long id) {
        return processoRepository.findById(id);
    }

    public Processo save(Processo processo) {
        return processoRepository.save(processo);
    }

    public void deleteById(Long id) {
        processoRepository.deleteById(id);
    }

    public Page<Processo> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return processoRepository.findAll(pageable);
    }

    public Optional<Processo> updateDataVisualizacao(Long id) {
        Optional<Processo> processo = processoRepository.findById(id);
        if (processo.isPresent()) {
            Processo existingProcesso = processo.get();
            existingProcesso.setDataVisualizacao(LocalDate.now());
            processoRepository.save(existingProcesso);
        }
        return processo;
    }

    public Processo convertJsonToProcesso(String processoJson) throws IOException {
        return objectMapper.readValue(processoJson, Processo.class);
    }

}
