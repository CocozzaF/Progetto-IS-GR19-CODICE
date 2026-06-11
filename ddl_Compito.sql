CREATE TABLE compito
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    titolo            VARCHAR(255)          NULL,
    descrizione       VARCHAR(255)          NULL,
    data_assegnazione date                  NULL,
    data_scadenza     date                  NULL,
    classe_codice     VARCHAR(255)          NULL,
    CONSTRAINT pk_compito PRIMARY KEY (id)
);

ALTER TABLE compito
    ADD CONSTRAINT FK_COMPITO_ON_CLASSE_CODICE FOREIGN KEY (classe_codice) REFERENCES classe_virtuale (codice_univoco);