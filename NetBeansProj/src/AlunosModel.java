
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebeca
 */
public class AlunosModel {

    public static void create(AlunosBean a, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO aluno (cpf, nome, email) VALUES (?,?,?)");
        st.setLong(1, a.getCpf());
        st.setString(2, a.getNome());
        st.setString(3, a.getEmail());
        st.execute();
        st.close();
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT cpf, nome, email FROM aluno";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new AlunosBean(result.getLong(1), result.getString(2), result.getString(3)));
        }
        return list;
    }
    
    public static AlunosBean buscarAlunoPorCpf(Connection con, long cpf) throws SQLException {
        AlunosBean aluno = null;
        String sql = "SELECT cpf , nome , email FROM aluno WHERE cpf = ?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, cpf);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                aluno = new AlunosBean(rs.getLong(1), rs.getString(2),rs.getString(3));
            }
        }

        return aluno;
    }
    
    public static boolean secaoJaCadastrada(Connection con, long cpf, int codSecao) throws SQLException {
        String sql = "SELECT COUNT(*) FROM treino WHERE cpf = ? AND codSecao = ?";
        
        PreparedStatement st = con.prepareStatement(sql); 
        st.setLong(1, cpf);
        st.setInt(2, codSecao);

        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Retorna true se existir pelo menos uma entrada
            }
        }
        
        return false; // Retorna false em caso de erro ou nenhuma entrada
    }
    
    public static void cadastrarSecaoAluno (Connection con, long cpf , int codSecao) throws SQLException {
        String sql = "INSERT INTO treino(cpf,codsecao) VALUES (?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, cpf);
        st.setInt(2, codSecao);
        st.executeUpdate();
    }
    
    public static HashSet listSecoesDoAluno(Connection con, long cpf) throws SQLException {
        
        HashSet list = new HashSet();
        
        String sql = "SELECT s.codsecao, s.descricao FROM treino t " +
                     "JOIN secao s ON t.codsecao = s.codsecao " +
                     "WHERE t.cpf = ?";
        
        PreparedStatement st = con.prepareStatement(sql);
        st.setLong(1, cpf);
        ResultSet result = st.executeQuery();
        
        while(result.next()) {
            list.add(new SecoesBean(result.getInt(1), result.getString(2)));
        }
        
        return list;
    }
    
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
        
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new AlunosBean(result.getLong(1), result.getString(2), result.getString(3)));
        }
        return list;
    }
}
