CREATE TABLE IF NOT EXISTS transacao(
    id SERIAL PRIMARY KEY,
    tipo int,
    data date,
    valor decimal(10,2),
    cpf VARCHAR(11),
    cartao VARCHAR(255),
    hora time,
    dono_da_loja VARCHAR(255),
    nome_da_loja VARCHAR(255)
);