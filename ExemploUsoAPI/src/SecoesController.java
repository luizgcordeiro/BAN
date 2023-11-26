
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
public class SecoesController {
    
    public void createSecao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar uma nova secao: ");
       
        String descricao = "";

        while (descricao.length()<5) {
            System.out.print("Descricao: ");
            descricao = input.nextLine();

            if (descricao.length()<5) {
                System.out.println("A descricao deve ter pelo menos 5 caracteres. Tente novamente.");
            }
        }
        
        SecoesBean ab = new SecoesBean(descricao);
        int codSecao = SecoesModel.create(ab, con);
        System.out.println("Secao cadastrada com sucesso!!");
        System.out.println("O codigo da secao criada e " + codSecao);
    }

    public void cadastrarExercicio(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Digite o codigo da secao na qual voce quer cadastrar um exercicio (ou 0 para cancelar): ");

        int codSecao = input.nextInt();

        if (codSecao == 0) {
            System.out.println("Cadastro de exercicio em secao cancelado.");
            return;
        }

        SecoesBean secao = SecoesModel.buscarSecaoPorCodigo(con, codSecao);
        if (secao == null) {
            System.out.println("Secao nao encontrada. Tente novamente.");
            return;
        }

        System.out.println("Secao encontrada: " + secao); 
        cadastrarExercicio(con,codSecao);
    }
    
    public void cadastrarExercicio(Connection con, int codSecao) throws SQLException {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Digite o codigo do exercicio que voce quer cadastrar a esta secao (ou 0 para cancelar): ");

        int codExercicio = input.nextInt();

        if (codExercicio == 0) {
            System.out.println("Cadastro de exercício na seção cancelado.");
            return;
        }

        // Verifica se existe tal exercício
        ExerciciosBean exercicio = ExerciciosModel.buscarExercicioPorCodigo(con, codExercicio);
        if (exercicio == null) {
            System.out.println("Exercício não encontrado. Tente novamente.");
            return;
        }

        // Verifica se o exercício já está cadastrado na seção
        if (SecoesModel.exercicioJaCadastradoNaSecao(con, codSecao, codExercicio)) {
            System.out.println("Exercício a cadastrado nesta secao. Tente novamente");
            return;
        }

        // Encontrou um exercício
        System.out.println("Exercicio encontrado: " + exercicio);       
        
        // Pede o número de repetições e séries
        System.out.print("Quantas repeticoes? ");
        int numRepeticoes = input.nextInt();

        System.out.print("Quantas series? ");
        int numSeries = input.nextInt();

        SecoesModel.cadastrarExercicioSecao(con, codSecao, codExercicio, numSeries, numRepeticoes);

        System.out.println("Exercicio cadastrado na secao com sucesso!");
    }
    
    public void listarSecao(Connection con) throws SQLException {
        HashSet<SecoesBean> allSecoes = SecoesModel.listAll(con);
        Iterator<SecoesBean> it = allSecoes.iterator();

        while (it.hasNext()) {
            SecoesBean secao = it.next();
            System.out.println(secao);

            mostrarExerciciosDaSecao(con, secao.getCodSecao());

            System.out.println("==============================");
        }
    }
    
    public static void mostrarExerciciosDaSecao(Connection con, int codSecao) throws SQLException{
        /*ResultSet é parecido com dicionário em Python*/
        ResultSet rs = SecoesModel.obterExerciciosDaSecao(con, codSecao);

        if (rs.next()) {
            do {
                String nomeExercicio = rs.getString("nome");
                int numRepeticoes = rs.getInt("numrepeticoes");
                int numSeries = rs.getInt("numseries");

                System.out.println("\t" + nomeExercicio + ": " + numSeries + " series de " + numRepeticoes + " repeticoes");
            } while (rs.next());
        } else {
            System.out.println("\tSem exercicios nesta secao.");
        }
    }
}
