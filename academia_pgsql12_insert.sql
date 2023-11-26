--
-- PostgreSQL database dump
--

-- Dumped from database version 12.17
-- Dumped by pg_dump version 12.17

-- Started on 2023-11-26 17:57:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16394)
-- Name: exercicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exercicio (
    codexercicio integer NOT NULL,
    nome text NOT NULL
);


ALTER TABLE public.exercicio OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16400)
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Exercicio_codExercicio_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Exercicio_codExercicio_seq" OWNER TO postgres;

--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 203
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Exercicio_codExercicio_seq" OWNED BY public.exercicio.codexercicio;


--
-- TOC entry 204 (class 1259 OID 16402)
-- Name: secao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.secao (
    codsecao integer NOT NULL,
    descricao text
);


ALTER TABLE public.secao OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16408)
-- Name: Secao_codSecao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Secao_codSecao_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Secao_codSecao_seq" OWNER TO postgres;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 205
-- Name: Secao_codSecao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Secao_codSecao_seq" OWNED BY public.secao.codsecao;


--
-- TOC entry 206 (class 1259 OID 16410)
-- Name: aluno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aluno (
    cpf bigint NOT NULL,
    nome text NOT NULL,
    email text
);


ALTER TABLE public.aluno OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16416)
-- Name: observacoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.observacoes (
    recorde numeric,
    pesoatual numeric,
    regulagem text,
    cpf bigint NOT NULL,
    codexercicio integer NOT NULL
);


ALTER TABLE public.observacoes OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16422)
-- Name: treinar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.treinar (
    numseries integer,
    numrepeticoes integer,
    codsecao integer NOT NULL,
    codexercicio integer NOT NULL
);


ALTER TABLE public.treinar OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16425)
-- Name: treino; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.treino (
    cpf bigint NOT NULL,
    codsecao bigint NOT NULL
);


ALTER TABLE public.treino OWNER TO postgres;

--
-- TOC entry 2713 (class 2604 OID 16428)
-- Name: exercicio codexercicio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercicio ALTER COLUMN codexercicio SET DEFAULT nextval('public."Exercicio_codExercicio_seq"'::regclass);


--
-- TOC entry 2714 (class 2604 OID 16429)
-- Name: secao codsecao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.secao ALTER COLUMN codsecao SET DEFAULT nextval('public."Secao_codSecao_seq"'::regclass);


--
-- TOC entry 2863 (class 0 OID 16410)
-- Dependencies: 206
-- Data for Name: aluno; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.aluno VALUES (996507973, 'Luiz Gustavo Cordeiro', 'luizgc6@gmail.com');
INSERT INTO public.aluno VALUES (123456, 'Jose da Silva', 'josedasilva@gmail.com');
INSERT INTO public.aluno VALUES (21234254131, 'joao da silva', 'joaodasilva@gmail.com');
INSERT INTO public.aluno VALUES (123, 'ze', 'ze@ze.ze.ze');


--
-- TOC entry 2859 (class 0 OID 16394)
-- Dependencies: 202
-- Data for Name: exercicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.exercicio VALUES (1, 'Supino reto com barra');
INSERT INTO public.exercicio VALUES (2, 'peck deck frontal');
INSERT INTO public.exercicio VALUES (3, 'leg press');
INSERT INTO public.exercicio VALUES (4, 'agachamento');
INSERT INTO public.exercicio VALUES (5, 'afundo');


--
-- TOC entry 2864 (class 0 OID 16416)
-- Dependencies: 207
-- Data for Name: observacoes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2861 (class 0 OID 16402)
-- Dependencies: 204
-- Data for Name: secao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.secao VALUES (1, 'Peito e triceps');
INSERT INTO public.secao VALUES (6, 'Perna e ombro');
INSERT INTO public.secao VALUES (8, 'braco');
INSERT INTO public.secao VALUES (9, 'pernas');


--
-- TOC entry 2865 (class 0 OID 16422)
-- Dependencies: 208
-- Data for Name: treinar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.treinar VALUES (3, 10, 1, 1);
INSERT INTO public.treinar VALUES (3, 10, 1, 2);
INSERT INTO public.treinar VALUES (10, 3, 8, 2);
INSERT INTO public.treinar VALUES (3, 10, 9, 3);
INSERT INTO public.treinar VALUES (3, 9, 9, 4);
INSERT INTO public.treinar VALUES (3, 10, 9, 5);


--
-- TOC entry 2866 (class 0 OID 16425)
-- Dependencies: 209
-- Data for Name: treino; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.treino VALUES (996507973, 1);
INSERT INTO public.treino VALUES (996507973, 8);
INSERT INTO public.treino VALUES (123456, 9);


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 203
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Exercicio_codExercicio_seq"', 5, true);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 205
-- Name: Secao_codSecao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Secao_codSecao_seq"', 9, true);


--
-- TOC entry 2720 (class 2606 OID 16431)
-- Name: aluno Aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT "Aluno_pkey" PRIMARY KEY (cpf);


--
-- TOC entry 2716 (class 2606 OID 16433)
-- Name: exercicio Exercicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercicio
    ADD CONSTRAINT "Exercicio_pkey" PRIMARY KEY (codexercicio);


--
-- TOC entry 2722 (class 2606 OID 16435)
-- Name: observacoes Observacoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT "Observacoes_pkey" PRIMARY KEY (cpf, codexercicio);


--
-- TOC entry 2718 (class 2606 OID 16437)
-- Name: secao Secao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.secao
    ADD CONSTRAINT "Secao_pkey" PRIMARY KEY (codsecao);


--
-- TOC entry 2724 (class 2606 OID 16439)
-- Name: treinar Treinar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "Treinar_pkey" PRIMARY KEY (codsecao, codexercicio);


--
-- TOC entry 2726 (class 2606 OID 16441)
-- Name: treino Treino_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT "Treino_pkey" PRIMARY KEY (cpf, codsecao);


--
-- TOC entry 2729 (class 2606 OID 16442)
-- Name: treinar codExercicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "codExercicio" FOREIGN KEY (codexercicio) REFERENCES public.exercicio(codexercicio);


--
-- TOC entry 2727 (class 2606 OID 16447)
-- Name: observacoes codExercicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT "codExercicio" FOREIGN KEY (codexercicio) REFERENCES public.exercicio(codexercicio);


--
-- TOC entry 2731 (class 2606 OID 16452)
-- Name: treino codSecao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT "codSecao" FOREIGN KEY (codsecao) REFERENCES public.secao(codsecao);


--
-- TOC entry 2730 (class 2606 OID 16457)
-- Name: treinar codSecao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "codSecao" FOREIGN KEY (codsecao) REFERENCES public.secao(codsecao);


--
-- TOC entry 2732 (class 2606 OID 16462)
-- Name: treino cpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT cpf FOREIGN KEY (cpf) REFERENCES public.aluno(cpf);


--
-- TOC entry 2728 (class 2606 OID 16467)
-- Name: observacoes cpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT cpf FOREIGN KEY (cpf) REFERENCES public.aluno(cpf);


-- Completed on 2023-11-26 17:57:25

--
-- PostgreSQL database dump complete
--

