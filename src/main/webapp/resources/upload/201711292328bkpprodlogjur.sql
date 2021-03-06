-- SQL Manager Lite for PostgreSQL 5.7.1.47382
-- ---------------------------------------
-- Host      : 35.194.1.173
-- Database  : logjur
-- Version   : PostgreSQL 9.6.6 on x86_64-pc-linux-gnu, compiled by gcc (Debian 6.3.0-18) 6.3.0 20170516, 64-bit



SET check_function_bodies = false;
--
-- Structure for table audiencia (OID = 16404) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.audiencia (
    codigo bigint NOT NULL,
    ativo boolean,
    autor varchar(100),
    datacadastro timestamp without time zone,
    datarealizar date NOT NULL,
    horarealizar time without time zone NOT NULL,
    numeroconsulta varchar(100),
    numerointerno varchar(100),
    numeroprocesso varchar(100),
    correspondente_codigo bigint,
    empresa_codigo bigint,
    escritorio_codigo bigint,
    orgao_codigo bigint,
    status_codigo bigint,
    tipoaudiencia_codigo bigint
)
WITH (oids = false);
--
-- Structure for table banco (OID = 16409) : 
--
CREATE TABLE public.banco (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    nome varchar(100) NOT NULL,
    sigla varchar(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table cidade (OID = 16414) : 
--
CREATE TABLE public.cidade (
    codigo bigint NOT NULL,
    nome varchar(50) NOT NULL,
    estado_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table correspondente (OID = 16419) : 
--
CREATE TABLE public.correspondente (
    codigo bigint NOT NULL,
    agencia varchar(30) NOT NULL,
    ativo boolean NOT NULL,
    contacorrente varchar(50) NOT NULL,
    datacadastro date NOT NULL,
    banco_codigo bigint,
    pessoa_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table empresa (OID = 16424) : 
--
CREATE TABLE public.empresa (
    codigo bigint NOT NULL,
    bairro varchar(30) NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(10),
    email varchar(100) NOT NULL,
    nomecompleto varchar(200) NOT NULL,
    nomecontato varchar(100) NOT NULL,
    nomeresumido varchar(100) NOT NULL,
    numero varchar(255) NOT NULL,
    rua varchar(100) NOT NULL,
    telefone varchar(14) NOT NULL,
    cidade_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table escritorio (OID = 16432) : 
--
CREATE TABLE public.escritorio (
    codigo bigint NOT NULL,
    bairro varchar(30) NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(10),
    email varchar(100) NOT NULL,
    nomecompleto varchar(200) NOT NULL,
    nomecontato varchar(100) NOT NULL,
    nomeresumido varchar(100) NOT NULL,
    numero varchar(255) NOT NULL,
    rua varchar(100) NOT NULL,
    telefone varchar(14) NOT NULL,
    cidade_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table estado (OID = 16440) : 
--
CREATE TABLE public.estado (
    codigo bigint NOT NULL,
    nome varchar(50) NOT NULL,
    sigla varchar(2) NOT NULL
)
WITH (oids = false);
--
-- Structure for table orgao (OID = 16445) : 
--
CREATE TABLE public.orgao (
    codigo bigint NOT NULL,
    nome varchar(200) NOT NULL,
    numero varchar(100) NOT NULL,
    cidade_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table paginaspermissao (OID = 16450) : 
--
CREATE TABLE public.paginaspermissao (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    nome varchar(50) NOT NULL,
    perfil varchar(20) NOT NULL
)
WITH (oids = false);
--
-- Structure for table pessoa (OID = 16455) : 
--
CREATE TABLE public.pessoa (
    codigo bigint NOT NULL,
    bairro varchar(30) NOT NULL,
    celular varchar(14) NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(300),
    cpf varchar(14) NOT NULL,
    email varchar(100) NOT NULL,
    nome varchar(150) NOT NULL,
    numero varchar(255) NOT NULL,
    rg varchar(12) NOT NULL,
    rua varchar(100) NOT NULL,
    telefone varchar(13) NOT NULL,
    cidade_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table status (OID = 16463) : 
--
CREATE TABLE public.status (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao varchar(200),
    label varchar(50),
    nome varchar(50) NOT NULL,
    visaocorrespondente boolean NOT NULL,
    visaogeral boolean NOT NULL
)
WITH (oids = false);
--
-- Structure for table tipoaudiencia (OID = 16468) : 
--
CREATE TABLE public.tipoaudiencia (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao varchar(200),
    nome varchar(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table upload (OID = 16473) : 
--
CREATE TABLE public.upload (
    codigo bigint NOT NULL,
    datacadastro timestamp without time zone,
    nome varchar(200) NOT NULL
)
WITH (oids = false);
--
-- Structure for table usuario (OID = 16478) : 
--
CREATE TABLE public.usuario (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    login varchar(50) NOT NULL,
    perfil varchar(10) NOT NULL,
    senha varchar(32) NOT NULL,
    pessoa_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence idaudiencia (OID = 16563) : 
--
CREATE SEQUENCE public.idaudiencia
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idbanco (OID = 16565) : 
--
CREATE SEQUENCE public.idbanco
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idcidade (OID = 16567) : 
--
CREATE SEQUENCE public.idcidade
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idcorrespondente (OID = 16569) : 
--
CREATE SEQUENCE public.idcorrespondente
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idempresa (OID = 16571) : 
--
CREATE SEQUENCE public.idempresa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idescritorio (OID = 16573) : 
--
CREATE SEQUENCE public.idescritorio
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idestado (OID = 16575) : 
--
CREATE SEQUENCE public.idestado
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idorgao (OID = 16577) : 
--
CREATE SEQUENCE public.idorgao
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idpaginaspermissao (OID = 16579) : 
--
CREATE SEQUENCE public.idpaginaspermissao
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idpessoa (OID = 16581) : 
--
CREATE SEQUENCE public.idpessoa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idstatus (OID = 16583) : 
--
CREATE SEQUENCE public.idstatus
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idtipoaudiencia (OID = 16585) : 
--
CREATE SEQUENCE public.idtipoaudiencia
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idupload (OID = 16587) : 
--
CREATE SEQUENCE public.idupload
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idusuario (OID = 16589) : 
--
CREATE SEQUENCE public.idusuario
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Data for table public.cidade (OID = 16414) (LIMIT 0,2)
--
INSERT INTO cidade (codigo, nome, estado_codigo)
VALUES (1, 'Goiânia', 1);

INSERT INTO cidade (codigo, nome, estado_codigo)
VALUES (2, 'Aparecida de Goiânia', 1);

--
-- Data for table public.estado (OID = 16440) (LIMIT 0,1)
--
INSERT INTO estado (codigo, nome, sigla)
VALUES (1, 'Goiás', 'GO');

--
-- Data for table public.paginaspermissao (OID = 16450) (LIMIT 0,24)
--
INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (1, true, '/pages/paginaspermissao.xhtml', 'A');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (3, true, '/pages/acessoproibido.xhtml', 'A,C,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (4, true, '/pages/audienciascorrespondente.xhtml', 'A,C');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (5, true, '/pages/menucadastros.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (6, true, '/pages/menuaudiencia.xhtml', 'A,C');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (7, true, '/pages/audiencias.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (8, true, '/pages/correspondentes.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (9, true, '/pages/escritorios.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (10, true, '/pages/empresas.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (11, true, '/pages/orgaos.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (12, true, '/pages/tiposaudiencia.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (13, true, '/pages/status.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (14, true, '/pages/usuarios.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (15, true, '/pages/menusistema.xhtml', 'A');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (16, false, '/pages/menuconfig.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (2, true, '/pages/principal.xhtml', 'A,C,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (17, true, '/pages/menulocais.xhtml', 'A');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (18, true, '/pages/meuperfil.xhtml', 'A,C,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (19, true, '/pages/senha.xhtml', 'A,C,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (20, true, '/pages/pessoas.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (21, true, '/pages/estados.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (22, true, '/pages/cidades.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (23, true, '/pages/bancos.xhtml', 'A,U');

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (24, true, '/pages/upload.xhtml', 'A');

--
-- Data for table public.pessoa (OID = 16455) (LIMIT 0,3)
--
INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (1, 'Alto da Glória', '(62)98157-6880', '74.815-610', '1', '011.744.341-71', 'thiagonovato@gmai.com', 'Thiago Novato', '1', '3852377', '1', '', 1);

INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (2, '4', '(7_)_____-____', '5_.___-___', '2', '000.996.591-54', 'claudio@claudionovato.com.br', 'Claudio Novato', '3', '1', '1', '(6_)____-____', 1);

INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (3, '0', '(0_)_____-____', '0_.___-___', '0', '111.111.111-11', 'valerialemos02@gmail.com', 'Valeria Novato', '1', '1', '0', '(0_)____-____', 1);

--
-- Data for table public.usuario (OID = 16478) (LIMIT 0,3)
--
INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (1, true, 'thiago.novato', 'A', 'e83ed6f04fb0dc73e0227d5d003c465f', 1);

INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (2, true, 'claudio', 'A', 'e10adc3949ba59abbe56e057f20f883e', 2);

INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (3, true, 'valeria', 'A', 'e10adc3949ba59abbe56e057f20f883e', 3);

--
-- Definition for index audiencia_pkey (OID = 16407) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT audiencia_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index banco_pkey (OID = 16412) : 
--
ALTER TABLE ONLY banco
    ADD CONSTRAINT banco_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index cidade_pkey (OID = 16417) : 
--
ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index correspondente_pkey (OID = 16422) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT correspondente_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index empresa_pkey (OID = 16430) : 
--
ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index escritorio_pkey (OID = 16438) : 
--
ALTER TABLE ONLY escritorio
    ADD CONSTRAINT escritorio_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index estado_pkey (OID = 16443) : 
--
ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index orgao_pkey (OID = 16448) : 
--
ALTER TABLE ONLY orgao
    ADD CONSTRAINT orgao_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index paginaspermissao_pkey (OID = 16453) : 
--
ALTER TABLE ONLY paginaspermissao
    ADD CONSTRAINT paginaspermissao_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index pessoa_pkey (OID = 16461) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index status_pkey (OID = 16466) : 
--
ALTER TABLE ONLY status
    ADD CONSTRAINT status_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index tipoaudiencia_pkey (OID = 16471) : 
--
ALTER TABLE ONLY tipoaudiencia
    ADD CONSTRAINT tipoaudiencia_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index upload_pkey (OID = 16476) : 
--
ALTER TABLE ONLY upload
    ADD CONSTRAINT upload_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index usuario_pkey (OID = 16481) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index uk_tmfya9jg9r7uaivmfex7f5wvy (OID = 16483) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT uk_tmfya9jg9r7uaivmfex7f5wvy
    UNIQUE (pessoa_codigo);
--
-- Definition for index uk_rgvtd13kui1bw3v2bo4p0m1ln (OID = 16485) : 
--
ALTER TABLE ONLY paginaspermissao
    ADD CONSTRAINT uk_rgvtd13kui1bw3v2bo4p0m1ln
    UNIQUE (nome);
--
-- Definition for index uk_gej40f8jfd5efnwlggtpwjloo (OID = 16487) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT uk_gej40f8jfd5efnwlggtpwjloo
    UNIQUE (cpf);
--
-- Definition for index uk_g1orfqvgih1w8s3vyg15fq2b8 (OID = 16489) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_g1orfqvgih1w8s3vyg15fq2b8
    UNIQUE (login);
--
-- Definition for index uk_s8lrxfgvascltkib6t418n5ef (OID = 16491) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_s8lrxfgvascltkib6t418n5ef
    UNIQUE (pessoa_codigo);
--
-- Definition for index fk_ntnhg9t9cd38s8c7u84fif7i1 (OID = 16493) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_ntnhg9t9cd38s8c7u84fif7i1
    FOREIGN KEY (correspondente_codigo) REFERENCES correspondente(codigo);
--
-- Definition for index fk_iqfr3fdhnbo30tf0d7aea2iea (OID = 16498) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_iqfr3fdhnbo30tf0d7aea2iea
    FOREIGN KEY (empresa_codigo) REFERENCES empresa(codigo);
--
-- Definition for index fk_2mx88lie4c4uw4ngks2wn4sc2 (OID = 16503) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_2mx88lie4c4uw4ngks2wn4sc2
    FOREIGN KEY (escritorio_codigo) REFERENCES escritorio(codigo);
--
-- Definition for index fk_49ltgyjrmhrrtst9vyl85cqup (OID = 16508) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_49ltgyjrmhrrtst9vyl85cqup
    FOREIGN KEY (orgao_codigo) REFERENCES orgao(codigo);
--
-- Definition for index fk_gkke7a3vmhvufpbdc5gyij1mo (OID = 16513) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_gkke7a3vmhvufpbdc5gyij1mo
    FOREIGN KEY (status_codigo) REFERENCES status(codigo);
--
-- Definition for index fk_737gn8bg21hqcisxrt6ol66hp (OID = 16518) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_737gn8bg21hqcisxrt6ol66hp
    FOREIGN KEY (tipoaudiencia_codigo) REFERENCES tipoaudiencia(codigo);
--
-- Definition for index fk_3uxfxu3ar4lpub19hldftpikb (OID = 16523) : 
--
ALTER TABLE ONLY cidade
    ADD CONSTRAINT fk_3uxfxu3ar4lpub19hldftpikb
    FOREIGN KEY (estado_codigo) REFERENCES estado(codigo);
--
-- Definition for index fk_lp9l2w0bdgmkjqvkerjsv2oyi (OID = 16528) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT fk_lp9l2w0bdgmkjqvkerjsv2oyi
    FOREIGN KEY (banco_codigo) REFERENCES banco(codigo);
--
-- Definition for index fk_tmfya9jg9r7uaivmfex7f5wvy (OID = 16533) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT fk_tmfya9jg9r7uaivmfex7f5wvy
    FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo);
--
-- Definition for index fk_ar9mgfptwbetesl4o7jnja9bg (OID = 16538) : 
--
ALTER TABLE ONLY empresa
    ADD CONSTRAINT fk_ar9mgfptwbetesl4o7jnja9bg
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_ewfna2jp97go1jw07v3rry8ty (OID = 16543) : 
--
ALTER TABLE ONLY escritorio
    ADD CONSTRAINT fk_ewfna2jp97go1jw07v3rry8ty
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_hk0jwssg5g3rus476fwa2i10n (OID = 16548) : 
--
ALTER TABLE ONLY orgao
    ADD CONSTRAINT fk_hk0jwssg5g3rus476fwa2i10n
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_ru7rwevg7kj864u8vkyo8vbyi (OID = 16553) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT fk_ru7rwevg7kj864u8vkyo8vbyi
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_s8lrxfgvascltkib6t418n5ef (OID = 16558) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_s8lrxfgvascltkib6t418n5ef
    FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo);
--
-- Data for sequence public.idaudiencia (OID = 16563)
--
SELECT pg_catalog.setval('idaudiencia', 1, false);
--
-- Data for sequence public.idbanco (OID = 16565)
--
SELECT pg_catalog.setval('idbanco', 1, false);
--
-- Data for sequence public.idcidade (OID = 16567)
--
SELECT pg_catalog.setval('idcidade', 4, true);
--
-- Data for sequence public.idcorrespondente (OID = 16569)
--
SELECT pg_catalog.setval('idcorrespondente', 1, false);
--
-- Data for sequence public.idempresa (OID = 16571)
--
SELECT pg_catalog.setval('idempresa', 1, false);
--
-- Data for sequence public.idescritorio (OID = 16573)
--
SELECT pg_catalog.setval('idescritorio', 1, false);
--
-- Data for sequence public.idestado (OID = 16575)
--
SELECT pg_catalog.setval('idestado', 1, false);
--
-- Data for sequence public.idorgao (OID = 16577)
--
SELECT pg_catalog.setval('idorgao', 1, false);
--
-- Data for sequence public.idpaginaspermissao (OID = 16579)
--
SELECT pg_catalog.setval('idpaginaspermissao', 24, true);
--
-- Data for sequence public.idpessoa (OID = 16581)
--
SELECT pg_catalog.setval('idpessoa', 3, true);
--
-- Data for sequence public.idstatus (OID = 16583)
--
SELECT pg_catalog.setval('idstatus', 1, false);
--
-- Data for sequence public.idtipoaudiencia (OID = 16585)
--
SELECT pg_catalog.setval('idtipoaudiencia', 1, false);
--
-- Data for sequence public.idupload (OID = 16587)
--
SELECT pg_catalog.setval('idupload', 1, false);
--
-- Data for sequence public.idusuario (OID = 16589)
--
SELECT pg_catalog.setval('idusuario', 3, true);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
