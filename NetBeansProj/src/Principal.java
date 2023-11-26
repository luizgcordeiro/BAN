/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author rebeca
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;
        do{
            op = menu();
            try {
                switch (op) {
                    case 1: new AlunosController().createAluno(con);
                            break;
                    case 2: new SecoesController().createSecao(con);
                            break;
                    case 3: new ExerciciosController().createExercicio(con);
                            break;
                    case 4: new AlunosController().listarAluno(con);
                            break;
                    case 5: new SecoesController().listarSecao(con);
                            break;
                    case 6: new ExerciciosController().listarExercicio(con);
                            break;
                    case 7: new SecoesController().cadastrarExercicio(con);
                            break;
                    case 8: new AlunosController().cadastrarSecao(con);
                            break;
                    case 9: new AlunosController().mostrarAlunoComMaisExercicios(con);
                            break;
                }
            }catch(SQLException ex) {
                //ex.printStackTrace();
                System.out.println(ex.getMessage());
                continue;
            }
        } while(op>0 && op<10);  
        con.close();
    }    
    
    private static int menu() {
        System.out.println("");
        System.out.println("Informe o numero da opcao a executar: ");
        System.out.println("1 - Cadastrar um novo aluno");
        System.out.println("2 - Cadastrar uma nova secao");
        System.out.println("3 - Cadastrar um novo exercicio");
        System.out.println("4 - Exibir todos os alunos");
        System.out.println("5 - Exibir todas as secoes");
        System.out.println("6 - Exibir todos os exercicios");
        System.out.println("7 - Cadastrar um exercicio numa secao");
        System.out.println("8 - Cadastrar uma secao a um aluno");
        System.out.println("9 - Mostrar aluno(s) com o maximo de exercicios");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opcao: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
