
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
public class ExerciciosController {
    
    public void createExercicio(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo exercicio: ");
       
        String nome = "";

        while (nome.length()<5) {
            System.out.print("nome: ");
            nome = input.nextLine();

            if (nome.length()<5) {
                System.out.println("O nome deve ter pelo menos 5 caracteres. Tente novamente.");
            }
        }
        
        ExerciciosBean ab = new ExerciciosBean(nome);
        int codExercicio = ExerciciosModel.create(ab, con);
        System.out.println("Exercicio cadastrado com sucesso!!");
        System.out.println("O codigo do exercicio criado " + codExercicio);
    }

    void listarExercicio(Connection con) throws SQLException {
        HashSet all = ExerciciosModel.listAll(con);
        Iterator<ExerciciosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}
