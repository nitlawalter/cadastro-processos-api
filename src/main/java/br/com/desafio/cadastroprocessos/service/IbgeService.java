package br.com.desafio.cadastroprocessos.service;

import br.com.desafio.cadastroprocessos.dtos.Municipio;
import br.com.desafio.cadastroprocessos.dtos.UF;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class IbgeService {

    private final RestTemplate restTemplate;

    public List<UF> getEstados() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        UF[] estados = restTemplate.getForObject(url, UF[].class);
        return Arrays.asList(estados);
    }

    public List<Municipio> getMunicipiosByUf(String ufCode) {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + ufCode + "/municipios";
        Municipio[] municipios = restTemplate.getForObject(url, Municipio[].class);
        return Arrays.asList(municipios);
    }

}
