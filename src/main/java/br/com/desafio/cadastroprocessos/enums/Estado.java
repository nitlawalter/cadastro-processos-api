package br.com.desafio.cadastroprocessos.enums;

import java.util.HashMap;
import java.util.Map;

public enum Estado {

    RO(11, "Rondônia", "RO", "Norte"),
    AC(12, "Acre", "AC", "Norte"),
    AM(13, "Amazonas", "AM", "Norte"),
    RR(14, "Roraima", "RR", "Norte"),
    PA(15, "Pará", "PA", "Norte"),
    AP(16, "Amapá", "AP", "Norte"),
    TO(17, "Tocantins", "TO", "Norte"),
    MA(21, "Maranhão", "MA", "Nordeste"),
    PI(22, "Piauí", "PI", "Nordeste"),
    CE(23, "Ceará", "CE", "Nordeste"),
    RN(24, "Rio Grande do Norte", "RN", "Nordeste"),
    PB(25, "Paraíba", "PB", "Nordeste"),
    PE(26, "Pernambuco", "PE", "Nordeste"),
    AL(27, "Alagoas", "AL", "Nordeste"),
    SE(28, "Sergipe", "SE", "Nordeste"),
    BA(29, "Bahia", "BA", "Nordeste"),
    MG(31, "Minas Gerais", "MG", "Sudeste"),
    ES(32, "Espírito Santo", "ES", "Sudeste"),
    RJ(33, "Rio de Janeiro", "RJ", "Sudeste"),
    SP(35, "São Paulo", "SP", "Sudeste"),
    PR(41, "Paraná", "PR", "Sul"),
    SC(42, "Santa Catarina", "SC", "Sul"),
    RS(43, "Rio Grande do Sul", "RS", "Sul"),
    MS(50, "Mato Grosso do Sul", "MS", "Centro-Oeste"),
    MT(51, "Mato Grosso", "MT", "Centro-Oeste"),
    GO(52, "Goiás", "GO", "Centro-Oeste"),
    DF(53, "Distrito Federal", "DF", "Centro-Oeste");

    private static final Map<String, Estado> BY_SIGLA = new HashMap<>();

    static {
        for (Estado e : values()) {
            BY_SIGLA.put(e.sigla, e);
        }
    }

    private final int id;
    private final String nome;
    private final String sigla;
    private final String regiaoNome;

    Estado(int id, String nome, String sigla, String regiaoNome) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.regiaoNome = regiaoNome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getsigla() {
        return sigla;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public static Estado getBySigla(String sigla) {
        return BY_SIGLA.get(sigla.toUpperCase());
    }

    public String getSigla() {
        return sigla;
    }
}
