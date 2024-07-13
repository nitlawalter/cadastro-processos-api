package br.com.desafio.cadastroprocessos.controller;

import br.com.desafio.cadastroprocessos.model.Processo;
import br.com.desafio.cadastroprocessos.service.ProcessoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/processos")
@AllArgsConstructor
@Validated
public class ProcessoController {

    private final ProcessoService processoService;

    @GetMapping
    public ResponseEntity<List<Processo>> getAllProcessos() {
        List<Processo> processos = processoService.findAll();
        return new ResponseEntity<>(processos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processo> getProcessoById(@PathVariable Long id) {
        Optional<Processo> processo = processoService.findById(id);
        return processo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Processo> createProcesso(@Valid @RequestBody Processo processo) {
        Processo newProcesso = processoService.save(processo);
        return new ResponseEntity<>(newProcesso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> updateProcesso(@PathVariable Long id, @Valid @RequestBody Processo processoDetails) {
        Optional<Processo> processo = processoService.findById(id);
        if (processo.isPresent()) {
            Processo existingProcesso = processo.get();
            existingProcesso.setNpu(processoDetails.getNpu());
            existingProcesso.setDataCadastro(processoDetails.getDataCadastro());
            existingProcesso.setDataVisualizacao(processoDetails.getDataVisualizacao());
            existingProcesso.setMunicipio(processoDetails.getMunicipio());
            existingProcesso.setUf(processoDetails.getUf());
            existingProcesso.setDocumento(processoDetails.getDocumento());
            Processo updatedProcesso = processoService.save(existingProcesso);
            return new ResponseEntity<>(updatedProcesso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProcesso(@PathVariable Long id) {
        processoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
