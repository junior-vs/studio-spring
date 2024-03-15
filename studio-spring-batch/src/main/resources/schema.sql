DROP TABLE people IF EXISTS;
DROP SEQUENCE PERSON_SEQUENCE IF EXISTS;

-- Criar a sequência para gerar IDs
CREATE SEQUENCE person_sequence START WITH 1 INCREMENT BY 1;

-- Criar a tabela
CREATE TABLE people (
    person_id BIGINT DEFAULT NEXTVAL('person_sequence') NOT NULL PRIMARY KEY,
    first_name VARCHAR(200),
    last_name VARCHAR(200)
);