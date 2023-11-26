
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
public class AlunosController {
   
    
    public void createAluno(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo aluno: ");
       
        long cpf = 0;

        while (true) {
            System.out.print("CPF: ");
            String cpfInput = input.nextLine().replaceAll("[^0-9]", "");

            try {
                cpf = Long.parseLong(cpfInput);

                // Verifica se o CPF está no intervalo válido
                if (cpf >= 0 && cpf <= 99999999999L) {
                    break; // Sai do loop se o CPF for válido
                } else {
                    System.out.println("CPF invalido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato invalido. Tente novamente.");
            }
        }
        
        System.out.print("nome: ");
        String nome = input.nextLine();
        System.out.print("email: ");
        String email = input.nextLine();
        AlunosBean ab = new AlunosBean(cpf, nome, email);
        AlunosModel.create(ab, con);
        System.out.println("Aluno cadastrado com sucesso!!");
    }

    void listarAluno(Connection con) throws SQLException {
        HashSet all = AlunosModel.listAll(con);
        Iterator<AlunosBean> it = all.iterator();
        while (it.hasNext()) {
            AlunosBean aluno = it.next();
            System.out.println(aluno);

            mostrarSecoesDoAluno(con, aluno.getCpf());

            System.out.println("==============================");
        }
    }
    
    public static void mostrarSecoesDoAluno(Connection con, long cpf) throws SQLException{
        
        HashSet secoesDoAluno = AlunosModel.listSecoesDoAluno(con,cpf);

        if (secoesDoAluno.size()==0) {
            System.out.println("\tSem secoes cadastradas para este aluno.");
            return;
        }
        
        for (Object s : secoesDoAluno) {
            System.out.println("\t" + s);
        }
    }
    
    public void cadastrarSecao(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Digite o cpf do aluno ao qual voce quer cadastrar uma secao (ou 0 para cancelar): ");

        long cpf = Long.parseLong(input.nextLine());

        if (cpf == 0) {
            System.out.println("Cadastro de secao para aluno cancelado.");
            return;
        }

        AlunosBean aluno = AlunosModel.buscarAlunoPorCpf(con, cpf);
        if (aluno == null) {
            System.out.println("Aluno nao encontrado. Tente novamente.");
            return;
        }
        
        System.out.println("Aluno encontrado: " + aluno);
        cadastrarSecao(con,cpf);
    }
    
    public void cadastrarSecao(Connection con, long cpf) throws SQLException {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Digite o codigo da secao que voce quer cadastrar a este aluno (ou 0 para cancelar): ");

        int codSecao = input.nextInt();

        if (codSecao == 0) {
            System.out.println("Cadastro de secao para aluno cancelado.");
            return;
        }

        // Verifica se existe tal secao
        SecoesBean secao = SecoesModel.buscarSecaoPorCodigo(con, codSecao);
        if (secao == null) {
            System.out.println("Secao nao encontrada.");
            return;
        }

        // Verifica se a secao ja esta cadastrada para o aluno
        if (AlunosModel.secaoJaCadastrada(con, cpf, codSecao)) {
            System.out.println("Esta secao ja esta cadastrada pra o aluno. Tente novamente");
            return;
        }

        // Encontrou uma secao
        System.out.println("Secao encontrada: " + secao);       
        
        AlunosModel.cadastrarSecaoAluno(con, cpf, codSecao);

        System.out.println("Secao cadastrada para o aluno com sucesso!");
    }
    
    public void mostrarAlunoComMaisExercicios(Connection con) throws SQLException {
        HashSet list = AlunosModel.getAlunosComMaisExercicios(con);
        if (list.size()==0) {
            System.out.println("Nenhum aluno faz exercícios.");
            return;
        }
        
        if (list.size()==1) {
            System.out.println("Aluno com mais exercicios:");
        } else {
            System.out.println("Alunos com mais exercicios:");
        }
        
        Iterator<AlunosBean> it = list.iterator();
        while (it.hasNext()) {
            AlunosBean aluno = it.next();
            System.out.println(aluno);

            System.out.println("==============================");
        }
        
    }
}
