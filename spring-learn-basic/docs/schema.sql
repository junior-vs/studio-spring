

-- Criar a tabela
CREATE TABLE IF NOT EXISTS people (
    person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(200),
    last_name VARCHAR(200)
);

-- Inserir dados na tabela (opcional)
-- INSERT INTO people (first_name, last_name) VALUES ('João', 'Silva'), ('Maria', 'Santos');
