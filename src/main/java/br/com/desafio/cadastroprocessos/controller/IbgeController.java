package br.com.desafio.cadastroprocessos.controller;

import br.com.desafio.cadastroprocessos.dtos.Municipio;
import br.com.desafio.cadastroprocessos.enums.Estado;
import br.com.desafio.cadastroprocessos.service.IbgeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/localidades")
@AllArgsConstructor
public class IbgeController {

    private final IbgeService ibgeService;

    @GetMapping("/municipios/{ufSigla}")
    public ResponseEntity<List<Municipio>> getMunicipiosBySiglaUf(@PathVariable String ufSigla) {
        Estado estado = Estado.getBySigla(ufSigla);
        List<Municipio> municipios = ibgeService.getMunicipiosByUf(String.valueOf(estado.getId()));
        return new ResponseEntity<>(municipios, HttpStatus.OK);
    }

    @GetMapping("/estado/{sigla}")
    public ResponseEntity<Estado> getEstadoBySigla(@PathVariable String sigla) {
        Estado estado = Estado.getBySigla(sigla);
        return estado != null ? new ResponseEntity<>(estado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
