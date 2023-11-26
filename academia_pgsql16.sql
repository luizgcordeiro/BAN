--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2023-11-26 17:49:10

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
-- TOC entry 219 (class 1259 OID 16464)
-- Name: exercicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exercicio (
    codexercicio integer NOT NULL,
    nome text NOT NULL
);


ALTER TABLE public.exercicio OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16463)
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Exercicio_codExercicio_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Exercicio_codExercicio_seq" OWNER TO postgres;

--
-- TOC entry 4831 (class 0 OID 0)
-- Dependencies: 218
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Exercicio_codExercicio_seq" OWNED BY public.exercicio.codexercicio;


--
-- TOC entry 217 (class 1259 OID 16455)
-- Name: secao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.secao (
    codsecao integer NOT NULL,
    descricao text
);


ALTER TABLE public.secao OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16454)
-- Name: Secao_codSecao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Secao_codSecao_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Secao_codSecao_seq" OWNER TO postgres;

--
-- TOC entry 4832 (class 0 OID 0)
-- Dependencies: 216
-- Name: Secao_codSecao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Secao_codSecao_seq" OWNED BY public.secao.codsecao;


--
-- TOC entry 215 (class 1259 OID 16447)
-- Name: aluno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aluno (
    cpf bigint NOT NULL,
    nome text NOT NULL,
    email text
);


ALTER TABLE public.aluno OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16509)
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
-- TOC entry 221 (class 1259 OID 16494)
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
-- TOC entry 220 (class 1259 OID 16481)
-- Name: treino; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.treino (
    cpf bigint NOT NULL,
    codsecao bigint NOT NULL
);


ALTER TABLE public.treino OWNER TO postgres;

--
-- TOC entry 4656 (class 2604 OID 16467)
-- Name: exercicio codexercicio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercicio ALTER COLUMN codexercicio SET DEFAULT nextval('public."Exercicio_codExercicio_seq"'::regclass);


--
-- TOC entry 4655 (class 2604 OID 16458)
-- Name: secao codsecao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.secao ALTER COLUMN codsecao SET DEFAULT nextval('public."Secao_codSecao_seq"'::regclass);


--
-- TOC entry 4818 (class 0 OID 16447)
-- Dependencies: 215
-- Data for Name: aluno; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aluno (cpf, nome, email) FROM stdin;
996507973	Luiz Gustavo Cordeiro	luizgc6@gmail.com
123456	Jose da Silva	josedasilva@gmail.com
21234254131	joao da silva	joaodasilva@gmail.com
123	ze	ze@ze.ze.ze
\.


--
-- TOC entry 4822 (class 0 OID 16464)
-- Dependencies: 219
-- Data for Name: exercicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exercicio (codexercicio, nome) FROM stdin;
1	Supino reto com barra
2	peck deck frontal
3	leg press
4	agachamento
5	afundo
\.


--
-- TOC entry 4825 (class 0 OID 16509)
-- Dependencies: 222
-- Data for Name: observacoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.observacoes (recorde, pesoatual, regulagem, cpf, codexercicio) FROM stdin;
\.


--
-- TOC entry 4820 (class 0 OID 16455)
-- Dependencies: 217
-- Data for Name: secao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.secao (codsecao, descricao) FROM stdin;
1	Peito e triceps
6	Perna e ombro
8	braco
9	pernas
\.


--
-- TOC entry 4824 (class 0 OID 16494)
-- Dependencies: 221
-- Data for Name: treinar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.treinar (numseries, numrepeticoes, codsecao, codexercicio) FROM stdin;
3	10	1	1
3	10	1	2
10	3	8	2
3	10	9	3
3	9	9	4
3	10	9	5
\.


--
-- TOC entry 4823 (class 0 OID 16481)
-- Dependencies: 220
-- Data for Name: treino; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.treino (cpf, codsecao) FROM stdin;
996507973	1
996507973	8
123456	9
\.


--
-- TOC entry 4833 (class 0 OID 0)
-- Dependencies: 218
-- Name: Exercicio_codExercicio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Exercicio_codExercicio_seq"', 5, true);


--
-- TOC entry 4834 (class 0 OID 0)
-- Dependencies: 216
-- Name: Secao_codSecao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Secao_codSecao_seq"', 9, true);


--
-- TOC entry 4658 (class 2606 OID 16453)
-- Name: aluno Aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT "Aluno_pkey" PRIMARY KEY (cpf);


--
-- TOC entry 4662 (class 2606 OID 16471)
-- Name: exercicio Exercicio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exercicio
    ADD CONSTRAINT "Exercicio_pkey" PRIMARY KEY (codexercicio);


--
-- TOC entry 4668 (class 2606 OID 16515)
-- Name: observacoes Observacoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT "Observacoes_pkey" PRIMARY KEY (cpf, codexercicio);


--
-- TOC entry 4660 (class 2606 OID 16462)
-- Name: secao Secao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.secao
    ADD CONSTRAINT "Secao_pkey" PRIMARY KEY (codsecao);


--
-- TOC entry 4666 (class 2606 OID 16498)
-- Name: treinar Treinar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "Treinar_pkey" PRIMARY KEY (codsecao, codexercicio);


--
-- TOC entry 4664 (class 2606 OID 16527)
-- Name: treino Treino_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT "Treino_pkey" PRIMARY KEY (cpf, codsecao);


--
-- TOC entry 4671 (class 2606 OID 16504)
-- Name: treinar codExercicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "codExercicio" FOREIGN KEY (codexercicio) REFERENCES public.exercicio(codexercicio);


--
-- TOC entry 4673 (class 2606 OID 16521)
-- Name: observacoes codExercicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT "codExercicio" FOREIGN KEY (codexercicio) REFERENCES public.exercicio(codexercicio);


--
-- TOC entry 4669 (class 2606 OID 16489)
-- Name: treino codSecao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT "codSecao" FOREIGN KEY (codsecao) REFERENCES public.secao(codsecao);


--
-- TOC entry 4672 (class 2606 OID 16499)
-- Name: treinar codSecao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinar
    ADD CONSTRAINT "codSecao" FOREIGN KEY (codsecao) REFERENCES public.secao(codsecao);


--
-- TOC entry 4670 (class 2606 OID 16484)
-- Name: treino cpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treino
    ADD CONSTRAINT cpf FOREIGN KEY (cpf) REFERENCES public.aluno(cpf);


--
-- TOC entry 4674 (class 2606 OID 16516)
-- Name: observacoes cpf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacoes
    ADD CONSTRAINT cpf FOREIGN KEY (cpf) REFERENCES public.aluno(cpf);


-- Completed on 2023-11-26 17:49:11

--
-- PostgreSQL database dump complete
--

