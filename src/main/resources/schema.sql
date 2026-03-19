DROP TABLE IF EXISTS lance;
DROP TABLE IF EXISTS leilao;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       role VARCHAR(255) DEFAULT NULL,
                       password VARCHAR(255) NOT NULL,
                       PRIMARY KEY (user_id)
);

CREATE TABLE leilao (
                        id BIGINT AUTO_INCREMENT NOT NULL,
                        data_abertura DATE NOT NULL,
                        nome VARCHAR(255) NOT NULL,
                        valor_inicial DECIMAL(19,2) NOT NULL,
                        usuario_user_id BIGINT NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT FK_usuario_leilao FOREIGN KEY (usuario_user_id) REFERENCES users (user_id)
);

CREATE TABLE lance (
                       id BIGINT AUTO_INCREMENT NOT NULL,
                       data DATE NOT NULL,
                       valor DECIMAL(19,2) NOT NULL,
                       leilao_id BIGINT NOT NULL,
                       usuario_user_id BIGINT DEFAULT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT FK_leilao_lance FOREIGN KEY (leilao_id) REFERENCES leilao (id),
                       CONSTRAINT FK_usuario_lance FOREIGN KEY (usuario_user_id) REFERENCES users (user_id)
);