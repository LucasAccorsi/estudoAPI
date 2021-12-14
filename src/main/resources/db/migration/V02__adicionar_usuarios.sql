INSERT INTO tb_perfil (nome) VALUES ('ADMIN');
INSERT INTO tb_perfil (nome) VALUES ('USER');

INSERT INTO tb_usuario (email, nome, senha, perfil_id) VALUES ('lucasaccorsi@hotmail.com', 'Lucas', '$2a$12$QwaSLpmGtZYtEz54pTVG7uwM.WnnVaLuWkmSVnja6UAu7Ina7S6Ve', 1);
INSERT INTO tb_usuario (email, nome, senha, perfil_id) VALUES ('startergft@gmail.com', 'startergft', '$2a$12$28zX.OGh3Boz83pH2dZfQ.hC.k6hs1kLyRcez1kpfENuQNh.zcbgS', 2);