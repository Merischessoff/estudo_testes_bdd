-- Inserir Usuários (A senha é 'pass')
INSERT INTO users (user_id, email, enabled, username, role, password) VALUES
                                                                          (1, 'fulano@gmail.com', 1, 'fulano', 'USER', '$2a$10$8MeF8YTUTv22DVthkhOs3.WGT4W1Wp1xRXcRxTM12MgDzRviDpw7i'),
                                                                          (2, 'cigano@gmail.com', 1, 'cigano', 'USER', '$2a$10$8MeF8YTUTv22DVthkhOs3.WGT4W1Wp1xRXcRxTM12MgDzRviDpw7i'),
                                                                          (3, 'beltrano@gmail.com', 1, 'beltrano', 'USER', '$2a$10$8MeF8YTUTv22DVthkhOs3.WGT4W1Wp1xRXcRxTM12MgDzRviDpw7i');

-- Inserir Leilões
INSERT INTO leilao (id, data_abertura, nome, valor_inicial, usuario_user_id) VALUES
                                                                                 (1, '2026-03-18', 'Tablet Xpto 3', 5.00, 1),
                                                                                 (2, '2026-03-18', 'Computador Z3', 500.00, 3);

-- Inserir Lances
INSERT INTO lance (id, data, valor, leilao_id, usuario_user_id) VALUES
                                                                    (1, '2026-03-19', 10.00, 1, 3),
                                                                    (2, '2026-03-19', 15.00, 1, 2);