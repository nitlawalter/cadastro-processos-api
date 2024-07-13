package br.com.desafio.cadastroprocessos.service;

import br.com.desafio.cadastroprocessos.model.Processo;
import br.com.desafio.cadastroprocessos.repository.ProcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessoService {

    private final ProcessoRepository processoRepository;


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

}
