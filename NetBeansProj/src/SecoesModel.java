
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
public class SecoesModel {

    public static int create(SecoesBean a, Connection con) throws SQLException {
        int codigo;
        try (PreparedStatement st = con.prepareStatement("INSERT INTO secao (descricao) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, a.getDescricao());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            generatedKeys.next();
            codigo = generatedKeys.getInt(1);
        }
  
        return codigo;
    }

    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT codsecao , descricao FROM secao";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new SecoesBean(result.getInt(1), result.getString(2)));
        }
        return list;
    }
    
    public static void cadastrarExercicioSecao (Connection con, SecoesBean s , ExerciciosBean e, int numSeries, int numRepeticoes) throws SQLException {
        String sql = "INSERT INTO treinar(codsecao,codexercicio,numseries,numrepeticoes) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, s.getCodSecao());
        st.setInt(2, e.getCodExercicio());
        st.setInt(3, numSeries);
        st.setInt(4, numRepeticoes);

        st.executeUpdate();
    }
    
    public static void cadastrarExercicioSecao (Connection con, int codSecao , int codExercicio, int numSeries, int numRepeticoes) throws SQLException {
        String sql = "INSERT INTO treinar(codsecao,codexercicio,numseries,numrepeticoes) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, codSecao);
        st.setInt(2, codExercicio);
        st.setInt(3, numSeries);
        st.setInt(4, numRepeticoes);

        st.executeUpdate();
    }
    
    public static boolean exercicioJaCadastradoNaSecao(Connection con, int codSecao, int codExercicio) throws SQLException {
        String sql = "SELECT COUNT(*) FROM treinar WHERE codsecao = ? AND codexercicio = ?";
        
        PreparedStatement st = con.prepareStatement(sql); 
        st.setInt(1, codSecao);
        st.setInt(2, codExercicio);

        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Retorna true se existir pelo menos uma entrada
            }
        }
        

        return false; // Retorna false em caso de erro ou nenhuma entrada
    }
    
    public static SecoesBean buscarSecaoPorCodigo(Connection con, int codSecao) throws SQLException {
        SecoesBean secao = null;
        String sql = "SELECT codsecao , descricao FROM secao WHERE codsecao = ?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, codSecao);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                secao = new SecoesBean(rs.getInt(1), rs.getString(2));
            }
        }

        return secao;
    }
    
    public static ResultSet obterExerciciosDaSecao(Connection con, int codSecao) throws SQLException {
        String sql = "SELECT e.nome, t.numrepeticoes, t.numseries FROM treinar t " +
                     "JOIN exercicio e ON t.codexercicio = e.codexercicio " +
                     "WHERE t.codsecao = ?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, codSecao);

        return pstmt.executeQuery();
    }
}
