-- SQL Manager Lite for PostgreSQL 5.7.1.47382
-- ---------------------------------------
-- Host      : localhost
-- Database  : softjur
-- Version   : PostgreSQL 9.6.4, compiled by Visual C++ build 1800, 64-bit



SET check_function_bodies = false;
--
-- Structure for table audiencia (OID = 45376) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.audiencia (
    codigo bigint NOT NULL,
    ativo boolean,
    autor varchar(100),
    datacadastro timestamp without time zone NOT NULL,
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
    tipoaudiencia_codigo bigint,
    orientacoes text
)
WITH (oids = false);
--
-- Structure for table audienciahistorico (OID = 45381) : 
--
CREATE TABLE public.audienciahistorico (
    codigo bigint NOT NULL,
    arquivoupload varchar(200),
    ativo boolean,
    autor varchar(100),
    datacadastro timestamp without time zone NOT NULL,
    datarealizar date NOT NULL,
    horarealizar time without time zone NOT NULL,
    login varchar(100) NOT NULL,
    numeroconsulta varchar(100),
    numerointerno varchar(100),
    numeroprocesso varchar(100),
    observacao varchar(1000),
    audiencia_codigo bigint NOT NULL,
    correspondente_codigo bigint,
    empresa_codigo bigint,
    escritorio_codigo bigint,
    orgao_codigo bigint,
    status_codigo bigint,
    tipoaudiencia_codigo bigint
)
WITH (oids = false);
--
-- Structure for table banco (OID = 45389) : 
--
CREATE TABLE public.banco (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    nome varchar(100) NOT NULL,
    sigla varchar(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table cidade (OID = 45394) : 
--
CREATE TABLE public.cidade (
    codigo bigint NOT NULL,
    nome varchar(50) NOT NULL,
    estado_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table correspondente (OID = 45399) : 
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
-- Structure for table empresa (OID = 45404) : 
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
-- Structure for table escritorio (OID = 45412) : 
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
-- Structure for table estado (OID = 45420) : 
--
CREATE TABLE public.estado (
    codigo bigint NOT NULL,
    nome varchar(50) NOT NULL,
    sigla varchar(2) NOT NULL
)
WITH (oids = false);
--
-- Structure for table orgao (OID = 45425) : 
--
CREATE TABLE public.orgao (
    codigo bigint NOT NULL,
    nome varchar(200) NOT NULL,
    numero varchar(100) NOT NULL,
    cidade_codigo bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table paginaspermissao (OID = 45430) : 
--
CREATE TABLE public.paginaspermissao (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    nome varchar(50) NOT NULL,
    perfil varchar(20) NOT NULL
)
WITH (oids = false);
--
-- Structure for table parametrosgerais (OID = 45435) : 
--
CREATE TABLE public.parametrosgerais (
    codigo bigint NOT NULL,
    patharquivo varchar(500) NOT NULL,
    pathupload varchar(500) NOT NULL
)
WITH (oids = false);
--
-- Structure for table pessoa (OID = 45443) : 
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
-- Structure for table status (OID = 45451) : 
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
-- Structure for table tipoaudiencia (OID = 45456) : 
--
CREATE TABLE public.tipoaudiencia (
    codigo bigint NOT NULL,
    ativo boolean NOT NULL,
    descricao varchar(200),
    nome varchar(50) NOT NULL
)
WITH (oids = false);
--
-- Structure for table upload (OID = 45461) : 
--
CREATE TABLE public.upload (
    codigo bigint NOT NULL,
    datacadastro timestamp without time zone,
    nome varchar(200) NOT NULL
)
WITH (oids = false);
--
-- Structure for table usuario (OID = 45466) : 
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
-- Definition for sequence idaudiencia (OID = 45586) : 
--
CREATE SEQUENCE public.idaudiencia
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idaudienciahistorico (OID = 45588) : 
--
CREATE SEQUENCE public.idaudienciahistorico
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idbanco (OID = 45590) : 
--
CREATE SEQUENCE public.idbanco
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idcidade (OID = 45592) : 
--
CREATE SEQUENCE public.idcidade
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idcorrespondente (OID = 45594) : 
--
CREATE SEQUENCE public.idcorrespondente
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idempresa (OID = 45596) : 
--
CREATE SEQUENCE public.idempresa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idescritorio (OID = 45598) : 
--
CREATE SEQUENCE public.idescritorio
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idestado (OID = 45600) : 
--
CREATE SEQUENCE public.idestado
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idorgao (OID = 45602) : 
--
CREATE SEQUENCE public.idorgao
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idpaginaspermissao (OID = 45604) : 
--
CREATE SEQUENCE public.idpaginaspermissao
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idparametros (OID = 45606) : 
--
CREATE SEQUENCE public.idparametros
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idpessoa (OID = 45608) : 
--
CREATE SEQUENCE public.idpessoa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idstatus (OID = 45610) : 
--
CREATE SEQUENCE public.idstatus
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idtipoaudiencia (OID = 45612) : 
--
CREATE SEQUENCE public.idtipoaudiencia
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idupload (OID = 45614) : 
--
CREATE SEQUENCE public.idupload
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence idusuario (OID = 45616) : 
--
CREATE SEQUENCE public.idusuario
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Structure for table servico (OID = 45622) : 
--
CREATE TABLE public.servico (
    codigo bigint NOT NULL,
    ativo varchar(255),
    exigeoab boolean,
    nome varchar(200) NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence idservico (OID = 45627) : 
--
CREATE SEQUENCE public.idservico
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Data for table public.audiencia (OID = 45376) (LIMIT 0,1)
--
INSERT INTO audiencia (codigo, ativo, autor, datacadastro, datarealizar, horarealizar, numeroconsulta, numerointerno, numeroprocesso, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo, orientacoes)
VALUES (10, NULL, '', '2017-11-29 23:04:52.545', '2017-11-29', '23:04:00', NULL, '', '', 1, 3, 4, 2, 1, 1, 'Aqui você vai fazer isso isso isso.');

--
-- Data for table public.audienciahistorico (OID = 45381) (LIMIT 0,12)
--
INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (35, NULL, NULL, '', '2017-11-29 23:05:04.569', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (36, NULL, NULL, '', '2017-11-29 23:06:09.731', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (37, '201711292308WhatsApp Image 2017-11-26 at 19.54.42.jpeg', NULL, '', '2017-11-29 23:08:41.211', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', 'asdf', 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (38, NULL, NULL, '', '2017-11-29 23:09:01.545', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (39, '201711292309WhatsApp Image 2017-11-26 at 19.54.42.jpeg', NULL, '', '2017-11-29 23:09:14.363', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', 'adf', 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (40, NULL, NULL, '', '2017-11-29 23:11:36.545', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (41, '201711292328bkpprodlogjur.sql', NULL, '', '2017-11-29 23:28:39.07', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', 'df', 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (42, NULL, NULL, '', '2017-12-03 21:06:59.449', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, NULL, 3, 4, 2, 2, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (43, NULL, NULL, '', '2017-12-03 21:17:14.623', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (44, '201712032118nova pauta.xls', NULL, '', '2017-12-03 21:18:56.168', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', 'ok', 10, 1, 3, 4, 2, 1, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (45, NULL, NULL, '', '2017-12-03 21:36:29.337', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 2, 1);

INSERT INTO audienciahistorico (codigo, arquivoupload, ativo, autor, datacadastro, datarealizar, horarealizar, login, numeroconsulta, numerointerno, numeroprocesso, observacao, audiencia_codigo, correspondente_codigo, empresa_codigo, escritorio_codigo, orgao_codigo, status_codigo, tipoaudiencia_codigo)
VALUES (46, NULL, NULL, '', '2017-12-03 21:36:37.533', '2017-11-29', '23:04:00', 'thiago.novato', NULL, '', '', NULL, 10, 1, 3, 4, 2, 1, 1);

--
-- Data for table public.cidade (OID = 45394) (LIMIT 0,3)
--
INSERT INTO cidade (codigo, nome, estado_codigo)
VALUES (1, 'Goiânia', 1);

INSERT INTO cidade (codigo, nome, estado_codigo)
VALUES (2, 'Aparecida de Goiânia', 1);

INSERT INTO cidade (codigo, nome, estado_codigo)
VALUES (3, 'Anápolis', 1);

--
-- Data for table public.correspondente (OID = 45399) (LIMIT 0,2)
--
INSERT INTO correspondente (codigo, agencia, ativo, contacorrente, datacadastro, banco_codigo, pessoa_codigo)
VALUES (1, '', true, '', '2017-11-01', NULL, 1);

INSERT INTO correspondente (codigo, agencia, ativo, contacorrente, datacadastro, banco_codigo, pessoa_codigo)
VALUES (2, '', true, '', '2017-11-22', NULL, 3);

--
-- Data for table public.empresa (OID = 45404) (LIMIT 0,3)
--
INSERT INTO empresa (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (1, '1', '1_.___-___', '1', '1', 'TIM', '1', 'TIM', '1', '1', '(1_)_____-____', 1);

INSERT INTO empresa (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (2, '1', '1_.___-___', '1', '1', 'GVT', '1', 'GVT', '1', '1', '(1_)_____-____', 1);

INSERT INTO empresa (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (3, '1', '1_.___-___', '1', '1', 'VIVO', '1', 'VIVO', '1', '1', '(1_)_____-____', 1);

--
-- Data for table public.escritorio (OID = 45412) (LIMIT 0,5)
--
INSERT INTO escritorio (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (1, '1', '1_.___-___', '1', '1', 'Mortoza', '1', 'Mortoza', '1', '11', '(1_)_____-____', 1);

INSERT INTO escritorio (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (2, '1', '1_.___-___', '1', '1', 'BAUHER', '1', 'BAUHER', '1', '1', '(1_)_____-____', 1);

INSERT INTO escritorio (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (3, '1', '1_.___-___', '1', '1', 'RMS', '1', 'RMS', '1', '1', '(1_)_____-____', 1);

INSERT INTO escritorio (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (4, '1', '1_.___-___', '1', '1', 'MCA', '1', 'MCA', '1', '1', '(1_)_____-____', 1);

INSERT INTO escritorio (codigo, bairro, cep, complemento, email, nomecompleto, nomecontato, nomeresumido, numero, rua, telefone, cidade_codigo)
VALUES (5, '1', '1_.___-___', '1', '1', 'BECKER', '1', 'BECKER', '1', '1', '(1_)_____-____', 1);

--
-- Data for table public.estado (OID = 45420) (LIMIT 0,1)
--
INSERT INTO estado (codigo, nome, sigla)
VALUES (1, 'Goiás', 'GO');

--
-- Data for table public.orgao (OID = 45425) (LIMIT 0,4)
--
INSERT INTO orgao (codigo, nome, numero, cidade_codigo)
VALUES (2, 'JEC', '2', 3);

INSERT INTO orgao (codigo, nome, numero, cidade_codigo)
VALUES (1, 'JEC', '1', 1);

INSERT INTO orgao (codigo, nome, numero, cidade_codigo)
VALUES (3, 'JEC', '1', 2);

INSERT INTO orgao (codigo, nome, numero, cidade_codigo)
VALUES (4, 'JEC', '9', 2);

--
-- Data for table public.paginaspermissao (OID = 45430) (LIMIT 0,25)
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

INSERT INTO paginaspermissao (codigo, ativo, nome, perfil)
VALUES (25, true, '/pages/parametrosgerais.xhtml', 'A');

--
-- Data for table public.parametrosgerais (OID = 45435) (LIMIT 0,1)
--
INSERT INTO parametrosgerais (codigo, patharquivo, pathupload)
VALUES (1, '../resources/upload/', 'D:\\\\Desenvolvimento\\\\Workspace\\\\softjur\\\\src\\\\main\\\\webapp\\\\resources\\\\upload\\\\');

--
-- Data for table public.pessoa (OID = 45443) (LIMIT 0,3)
--
INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (1, 'Alto da Glória', '(62)98157-6880', '74.815-610', '1', '011.744.341-71', 'thiagonovato@gmai.com', 'Thiago Novato', '1', '3852377', '1', '', 1);

INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (2, '4', '(7_)_____-____', '5_.___-___', '2', '000.996.591-54', 'claudio@claudionovato.com.br', 'Claudio Novato', '3', '1', '1', '(6_)____-____', 1);

INSERT INTO pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo)
VALUES (3, '0', '(0_)_____-____', '0_.___-___', '0', '111.111.111-11', 'valerialemos02@gmail.com', 'Valeria Novato', '1', '1', '0', '(0_)____-____', 1);

--
-- Data for table public.status (OID = 45451) (LIMIT 0,5)
--
INSERT INTO status (codigo, ativo, descricao, label, nome, visaocorrespondente, visaogeral)
VALUES (1, true, 'Aguardando Realização', 'warning', 'Aguardando Realização', true, true);

INSERT INTO status (codigo, ativo, descricao, label, nome, visaocorrespondente, visaogeral)
VALUES (3, true, 'Cancelada', 'danger', 'Cancelada', false, true);

INSERT INTO status (codigo, ativo, descricao, label, nome, visaocorrespondente, visaogeral)
VALUES (5, true, 'Não realizada', 'primary', 'Não realizada', false, true);

INSERT INTO status (codigo, ativo, descricao, label, nome, visaocorrespondente, visaogeral)
VALUES (4, true, 'Adiada', 'info', 'Adiada', false, true);

INSERT INTO status (codigo, ativo, descricao, label, nome, visaocorrespondente, visaogeral)
VALUES (2, true, 'Realizada', 'success', 'Realizada', false, true);

--
-- Data for table public.tipoaudiencia (OID = 45456) (LIMIT 0,1)
--
INSERT INTO tipoaudiencia (codigo, ativo, descricao, nome)
VALUES (1, true, 'Conciliação', 'Conciliação');

--
-- Data for table public.usuario (OID = 45466) (LIMIT 0,3)
--
INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (1, true, 'thiago.novato', 'A', 'e83ed6f04fb0dc73e0227d5d003c465f', 1);

INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (2, true, 'claudio', 'A', 'e10adc3949ba59abbe56e057f20f883e', 2);

INSERT INTO usuario (codigo, ativo, login, perfil, senha, pessoa_codigo)
VALUES (3, true, 'valeria', 'C', 'e10adc3949ba59abbe56e057f20f883e', 3);

--
-- Definition for index audiencia_pkey (OID = 45379) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT audiencia_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index audienciahistorico_pkey (OID = 45387) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT audienciahistorico_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index banco_pkey (OID = 45392) : 
--
ALTER TABLE ONLY banco
    ADD CONSTRAINT banco_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index cidade_pkey (OID = 45397) : 
--
ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index correspondente_pkey (OID = 45402) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT correspondente_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index empresa_pkey (OID = 45410) : 
--
ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index escritorio_pkey (OID = 45418) : 
--
ALTER TABLE ONLY escritorio
    ADD CONSTRAINT escritorio_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index estado_pkey (OID = 45423) : 
--
ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index orgao_pkey (OID = 45428) : 
--
ALTER TABLE ONLY orgao
    ADD CONSTRAINT orgao_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index paginaspermissao_pkey (OID = 45433) : 
--
ALTER TABLE ONLY paginaspermissao
    ADD CONSTRAINT paginaspermissao_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index parametrosgerais_pkey (OID = 45441) : 
--
ALTER TABLE ONLY parametrosgerais
    ADD CONSTRAINT parametrosgerais_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index pessoa_pkey (OID = 45449) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index status_pkey (OID = 45454) : 
--
ALTER TABLE ONLY status
    ADD CONSTRAINT status_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index tipoaudiencia_pkey (OID = 45459) : 
--
ALTER TABLE ONLY tipoaudiencia
    ADD CONSTRAINT tipoaudiencia_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index upload_pkey (OID = 45464) : 
--
ALTER TABLE ONLY upload
    ADD CONSTRAINT upload_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index usuario_pkey (OID = 45469) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey
    PRIMARY KEY (codigo);
--
-- Definition for index uk_tmfya9jg9r7uaivmfex7f5wvy (OID = 45471) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT uk_tmfya9jg9r7uaivmfex7f5wvy
    UNIQUE (pessoa_codigo);
--
-- Definition for index uk_rgvtd13kui1bw3v2bo4p0m1ln (OID = 45473) : 
--
ALTER TABLE ONLY paginaspermissao
    ADD CONSTRAINT uk_rgvtd13kui1bw3v2bo4p0m1ln
    UNIQUE (nome);
--
-- Definition for index uk_gej40f8jfd5efnwlggtpwjloo (OID = 45475) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT uk_gej40f8jfd5efnwlggtpwjloo
    UNIQUE (cpf);
--
-- Definition for index uk_g1orfqvgih1w8s3vyg15fq2b8 (OID = 45477) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_g1orfqvgih1w8s3vyg15fq2b8
    UNIQUE (login);
--
-- Definition for index uk_s8lrxfgvascltkib6t418n5ef (OID = 45479) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_s8lrxfgvascltkib6t418n5ef
    UNIQUE (pessoa_codigo);
--
-- Definition for index fk_ntnhg9t9cd38s8c7u84fif7i1 (OID = 45481) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_ntnhg9t9cd38s8c7u84fif7i1
    FOREIGN KEY (correspondente_codigo) REFERENCES correspondente(codigo);
--
-- Definition for index fk_iqfr3fdhnbo30tf0d7aea2iea (OID = 45486) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_iqfr3fdhnbo30tf0d7aea2iea
    FOREIGN KEY (empresa_codigo) REFERENCES empresa(codigo);
--
-- Definition for index fk_2mx88lie4c4uw4ngks2wn4sc2 (OID = 45491) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_2mx88lie4c4uw4ngks2wn4sc2
    FOREIGN KEY (escritorio_codigo) REFERENCES escritorio(codigo);
--
-- Definition for index fk_49ltgyjrmhrrtst9vyl85cqup (OID = 45496) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_49ltgyjrmhrrtst9vyl85cqup
    FOREIGN KEY (orgao_codigo) REFERENCES orgao(codigo);
--
-- Definition for index fk_gkke7a3vmhvufpbdc5gyij1mo (OID = 45501) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_gkke7a3vmhvufpbdc5gyij1mo
    FOREIGN KEY (status_codigo) REFERENCES status(codigo);
--
-- Definition for index fk_737gn8bg21hqcisxrt6ol66hp (OID = 45506) : 
--
ALTER TABLE ONLY audiencia
    ADD CONSTRAINT fk_737gn8bg21hqcisxrt6ol66hp
    FOREIGN KEY (tipoaudiencia_codigo) REFERENCES tipoaudiencia(codigo);
--
-- Definition for index fk_q4w3w9hny94xm3qqg7f3ic7xy (OID = 45511) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_q4w3w9hny94xm3qqg7f3ic7xy
    FOREIGN KEY (audiencia_codigo) REFERENCES audiencia(codigo);
--
-- Definition for index fk_gon0k206idbbb93oe02hoju50 (OID = 45516) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_gon0k206idbbb93oe02hoju50
    FOREIGN KEY (correspondente_codigo) REFERENCES correspondente(codigo);
--
-- Definition for index fk_wqr66unb3gew43tni2tvcywa (OID = 45521) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_wqr66unb3gew43tni2tvcywa
    FOREIGN KEY (empresa_codigo) REFERENCES empresa(codigo);
--
-- Definition for index fk_q0jhym6t9dty08dpjjabmihst (OID = 45526) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_q0jhym6t9dty08dpjjabmihst
    FOREIGN KEY (escritorio_codigo) REFERENCES escritorio(codigo);
--
-- Definition for index fk_ajtfmowig0pt1cpv5hs9p4sgm (OID = 45531) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_ajtfmowig0pt1cpv5hs9p4sgm
    FOREIGN KEY (orgao_codigo) REFERENCES orgao(codigo);
--
-- Definition for index fk_v7e5stuhbyhtg62wy9dml8aw (OID = 45536) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_v7e5stuhbyhtg62wy9dml8aw
    FOREIGN KEY (status_codigo) REFERENCES status(codigo);
--
-- Definition for index fk_t36xggi2i8jvo2wbxs1vb9u7 (OID = 45541) : 
--
ALTER TABLE ONLY audienciahistorico
    ADD CONSTRAINT fk_t36xggi2i8jvo2wbxs1vb9u7
    FOREIGN KEY (tipoaudiencia_codigo) REFERENCES tipoaudiencia(codigo);
--
-- Definition for index fk_3uxfxu3ar4lpub19hldftpikb (OID = 45546) : 
--
ALTER TABLE ONLY cidade
    ADD CONSTRAINT fk_3uxfxu3ar4lpub19hldftpikb
    FOREIGN KEY (estado_codigo) REFERENCES estado(codigo);
--
-- Definition for index fk_lp9l2w0bdgmkjqvkerjsv2oyi (OID = 45551) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT fk_lp9l2w0bdgmkjqvkerjsv2oyi
    FOREIGN KEY (banco_codigo) REFERENCES banco(codigo);
--
-- Definition for index fk_tmfya9jg9r7uaivmfex7f5wvy (OID = 45556) : 
--
ALTER TABLE ONLY correspondente
    ADD CONSTRAINT fk_tmfya9jg9r7uaivmfex7f5wvy
    FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo);
--
-- Definition for index fk_ar9mgfptwbetesl4o7jnja9bg (OID = 45561) : 
--
ALTER TABLE ONLY empresa
    ADD CONSTRAINT fk_ar9mgfptwbetesl4o7jnja9bg
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_ewfna2jp97go1jw07v3rry8ty (OID = 45566) : 
--
ALTER TABLE ONLY escritorio
    ADD CONSTRAINT fk_ewfna2jp97go1jw07v3rry8ty
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_hk0jwssg5g3rus476fwa2i10n (OID = 45571) : 
--
ALTER TABLE ONLY orgao
    ADD CONSTRAINT fk_hk0jwssg5g3rus476fwa2i10n
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_ru7rwevg7kj864u8vkyo8vbyi (OID = 45576) : 
--
ALTER TABLE ONLY pessoa
    ADD CONSTRAINT fk_ru7rwevg7kj864u8vkyo8vbyi
    FOREIGN KEY (cidade_codigo) REFERENCES cidade(codigo);
--
-- Definition for index fk_s8lrxfgvascltkib6t418n5ef (OID = 45581) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_s8lrxfgvascltkib6t418n5ef
    FOREIGN KEY (pessoa_codigo) REFERENCES pessoa(codigo);
--
-- Definition for index servico_pkey (OID = 45625) : 
--
ALTER TABLE ONLY servico
    ADD CONSTRAINT servico_pkey
    PRIMARY KEY (codigo);
--
-- Data for sequence public.idaudiencia (OID = 45586)
--
SELECT pg_catalog.setval('idaudiencia', 10, true);
--
-- Data for sequence public.idaudienciahistorico (OID = 45588)
--
SELECT pg_catalog.setval('idaudienciahistorico', 46, true);
--
-- Data for sequence public.idbanco (OID = 45590)
--
SELECT pg_catalog.setval('idbanco', 1, false);
--
-- Data for sequence public.idcidade (OID = 45592)
--
SELECT pg_catalog.setval('idcidade', 1, false);
--
-- Data for sequence public.idcorrespondente (OID = 45594)
--
SELECT pg_catalog.setval('idcorrespondente', 2, true);
--
-- Data for sequence public.idempresa (OID = 45596)
--
SELECT pg_catalog.setval('idempresa', 1, false);
--
-- Data for sequence public.idescritorio (OID = 45598)
--
SELECT pg_catalog.setval('idescritorio', 1, false);
--
-- Data for sequence public.idestado (OID = 45600)
--
SELECT pg_catalog.setval('idestado', 1, false);
--
-- Data for sequence public.idorgao (OID = 45602)
--
SELECT pg_catalog.setval('idorgao', 4, true);
--
-- Data for sequence public.idpaginaspermissao (OID = 45604)
--
SELECT pg_catalog.setval('idpaginaspermissao', 25, true);
--
-- Data for sequence public.idparametros (OID = 45606)
--
SELECT pg_catalog.setval('idparametros', 1, false);
--
-- Data for sequence public.idpessoa (OID = 45608)
--
SELECT pg_catalog.setval('idpessoa', 1, false);
--
-- Data for sequence public.idstatus (OID = 45610)
--
SELECT pg_catalog.setval('idstatus', 5, true);
--
-- Data for sequence public.idtipoaudiencia (OID = 45612)
--
SELECT pg_catalog.setval('idtipoaudiencia', 1, false);
--
-- Data for sequence public.idupload (OID = 45614)
--
SELECT pg_catalog.setval('idupload', 11, true);
--
-- Data for sequence public.idusuario (OID = 45616)
--
SELECT pg_catalog.setval('idusuario', 1, false);
--
-- Data for sequence public.idservico (OID = 45627)
--
SELECT pg_catalog.setval('idservico', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
