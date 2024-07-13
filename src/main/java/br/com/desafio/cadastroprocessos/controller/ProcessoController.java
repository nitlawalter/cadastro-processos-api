package br.com.desafio.cadastroprocessos.controller;

import br.com.desafio.cadastroprocessos.dtos.ProcessoRequestDTO;
import br.com.desafio.cadastroprocessos.dtos.ProcessoResponseDTO;
import br.com.desafio.cadastroprocessos.model.Processo;
import br.com.desafio.cadastroprocessos.service.ProcessoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<ProcessoResponseDTO>> getAllProcessos() {
        List<Processo> processos = processoService.findAll();
        List<ProcessoResponseDTO> processosResponse = ProcessoResponseDTO.fromList(processos);
        return new ResponseEntity<>(processosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoResponseDTO> getProcessoById(@PathVariable Long id) {
        Optional<Processo> entity = processoService.findById(id);
        return entity.map(processo -> new ResponseEntity<>(ProcessoResponseDTO.from(processo), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProcessoResponseDTO> createProcesso(@Valid @RequestBody ProcessoRequestDTO processo) {
        Processo newProcesso = processoService.save(ProcessoRequestDTO.to(processo));
        return new ResponseEntity<>(ProcessoResponseDTO.from(newProcesso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoResponseDTO> updateProcesso(@PathVariable Long id, @Valid @RequestBody ProcessoRequestDTO processoDetails) {
        Optional<Processo> processo = processoService.findById(id);
        if (processo.isPresent()) {
            Processo existingProcesso = processo.get();
            existingProcesso.setNpu(processoDetails.getNpu());
            existingProcesso.setMunicipio(processoDetails.getMunicipio());
            existingProcesso.setUf(processoDetails.getUf());
            existingProcesso.setDocumento(processoDetails.getDocumento());
            Processo updatedProcesso = processoService.save(existingProcesso);
            return new ResponseEntity<>(ProcessoResponseDTO.from(updatedProcesso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProcesso(@PathVariable Long id) {
        processoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ProcessoResponseDTO>> getPaginatedProcessos(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Page<Processo> processos = processoService.findPaginated(page, size);
        return new ResponseEntity<>(ProcessoResponseDTO.fromListPaginated(processos), HttpStatus.OK);
    }

    @PatchMapping("/{id}/visualizacao")
    public ResponseEntity<Processo> updateDataVisualizacao(@PathVariable Long id) {
        Optional<Processo> processo = processoService.updateDataVisualizacao(id);
        return processo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
