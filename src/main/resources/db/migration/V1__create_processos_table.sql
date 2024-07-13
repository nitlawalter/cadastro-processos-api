CREATE SEQUENCE IF NOT EXISTS processos_id_seq;

CREATE TABLE IF NOT EXISTS processos (
    id BIGINT PRIMARY KEY DEFAULT nextval('processos_id_seq'),
    npu VARCHAR(25) NOT NULL,
    data_cadastro DATE NOT NULL,
    data_visualizacao DATE,
    municipio VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    documento BYTEA NOT NULL
);
