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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/processos")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProcessoResponseDTO> createProcesso(
            @RequestParam("processoJson") String processoJson, @RequestParam("file") MultipartFile file) throws IOException {
        Processo processo = processoService.convertJsonToProcesso(processoJson);
        if (file != null && !file.isEmpty()) {
            processo.setDocumento(file.getBytes());
        }
        Processo newProcesso = processoService.save(ProcessoRequestDTO.to(processo));
        return new ResponseEntity<>(ProcessoResponseDTO.from(newProcesso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoResponseDTO> updateProcesso(
            @PathVariable Long id, @RequestParam("processoJson") String processoJson, @RequestParam("file") MultipartFile file) throws IOException {
        Optional<Processo> processo = processoService.findById(id);
        if (processo.isPresent()) {
            Processo processoEdit = processoService.convertJsonToProcesso(processoJson);
            if (file != null && !file.isEmpty()) {
                processoEdit.setDocumento(file.getBytes());
            }
            Processo updatedProcesso = processoService.save(processoEdit);
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

    @PutMapping("/{id}/visualizacao")
    public ResponseEntity<Processo> updateDataVisualizacao(@PathVariable Long id) {
        Optional<Processo> processo = processoService.updateDataVisualizacao(id);
        return processo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
