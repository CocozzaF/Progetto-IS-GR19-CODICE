CREATE TABLE studente
(
    matricola VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NULL,
    cognome   VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    CONSTRAINT pk_studente PRIMARY KEY (matricola)
);