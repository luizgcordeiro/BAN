
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
public class ExerciciosModel {

    public static int create(ExerciciosBean a, Connection con) throws SQLException {
        int codigo;
        try (PreparedStatement st = con.prepareStatement("INSERT INTO exercicio (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, a.getNome());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            generatedKeys.next();
            codigo = generatedKeys.getInt(1);
        }
        
        return codigo;
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT codexercicio , nome FROM exercicio";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ExerciciosBean(result.getInt(1), result.getString(2)));
            }
        return list;
    }
    
    
    
    
    public static ExerciciosBean buscarExercicioPorCodigo(Connection con, int codExercicio) throws SQLException {
        ExerciciosBean exercicio = null;
        String sql = "SELECT codexercicio , nome FROM exercicio WHERE codexercicio = ?";
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, codExercicio);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                exercicio = new ExerciciosBean(rs.getInt(1), rs.getString(2));
            }
        }

        return exercicio;
    }
}
