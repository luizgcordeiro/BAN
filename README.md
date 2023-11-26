# BAN
Entrega do trabalho 2 de BAN

Os arquivos "academia_....sql" são dumps do pgadmin4 na versão do PostgreSQL conforme o nome (12 ou 16), com ou sem INSERTs no lugar de COPYs.

Parece que somente as versões com inserts funcionam (até a 16 funciona no Postgre 12).

A pasta ExemploUsoAPI tem o projeto do NetBeans. O arquivo ExemploUsoAPI.zip é a mesma coisa zipada.
O arquivo modelo_ER_proj_2.pdf tem o grafo ER modelado no projeto (5 tabelas do projeto original).

Os requerimentos do trabalho foram cumpridos da seguinte forma:

As informações das tabelas "aluno", "secao" e "exercicio" são exibidas conforme as opções no menu
As informações da tabela "treino" são exibidas junto com as da "aluno";
As informações da tabela "treinar" são exibidas junto com as da "secao".

Ambas as exibições das informações dos alunos e das seções já envolvem buscas com junções de tabelas.
No caso, a função AlunosModel.listSecoesDoAluno(con,cpf) faz um pedido SQL do tipo

"SELECT s.codsecao, s.descricao FROM treino t " +
                     "JOIN secao s ON t.codsecao = s.codsecao " +
                     "WHERE t.cpf = ?";

e similarmente apra a função secalModel.obterExerciciosDaSecao(con,codSecao)

A opção 9 mostra a lista de todos os alunos que têm quantidades máximas de exercícios dentre todas as
seções nas quais estão cadastrados (ignorando repetições de exercícios). Isto envolve uma subconsulta e
e funções de agregação (COUNT e MAX): Em AlunosModel:

public static HashSet getAlunosComMaisExercicios(Connection con) throws SQLException {
        HashSet list = new HashSet();
        
        /* Toda a seleção a nível do banco de dados*/
        String sql = "WITH ContagemExercicios AS ( " +
            "SELECT a.cpf, a.nome, a.email, COUNT(DISTINCT e.nome) AS total_exercicios " +
            "FROM aluno a " +
            "JOIN treino t1 ON a.cpf = t1.cpf " +
            "JOIN secao s ON t1.codsecao = s.codsecao " +
            "JOIN treinar t2 ON t2.codsecao = t1.codsecao " +
            "JOIN exercicio e ON t2.codexercicio = e.codexercicio " +
            "GROUP BY a.cpf " +
            ") " +
            "SELECT cpf, nome, email " +
            "FROM ContagemExercicios " +
            "WHERE total_exercicios = (SELECT MAX(total_exercicios) FROM ContagemExercicios) ";
