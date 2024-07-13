package br.com.desafio.cadastroprocessos.controller;

import br.com.desafio.cadastroprocessos.dtos.Municipio;
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

    @GetMapping("/municipios/{ufCode}")
    public ResponseEntity<List<Municipio>> getMunicipiosByUf(@PathVariable String ufCode) {
        List<Municipio> municipios = ibgeService.getMunicipiosByUf(ufCode);
        return new ResponseEntity<>(municipios, HttpStatus.OK);
    }

}
