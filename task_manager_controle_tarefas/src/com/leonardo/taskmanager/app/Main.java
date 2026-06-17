package com.leonardo.taskmanager.app;

import com.leonardo.taskmanager.controller.GerenciadorDeTarefas;
import com.leonardo.taskmanager.model.Tarefa;
import com.leonardo.taskmanager.model.Prioridade;
import com.leonardo.taskmanager.model.Status;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeTarefas manager = new GerenciadorDeTarefas();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("-- GERENCIADOR DE TAREFAS --");
            System.out.println("1. Criar nova tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Concluir tarefa");
            System.out.println("4. Remover tarefa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Responsável: ");
                    String responsavel = scanner.nextLine();
                    Tarefa t = new Tarefa(titulo, descricao, responsavel,
                            LocalDate.now(), Prioridade.MEDIA, Status.EM_ANDAMENTO);
                    manager.adicionarTarefas(t);
                    break;
                case 2:
                    manager.listarTarefas();
                    break;
                case 3:
                    System.out.print("ID da tarefa a concluir: ");
                    int idConcluir = scanner.nextInt();
                    manager.concluirTarefa(idConcluir);
                    break;
                case 4:
                    System.out.print("ID da tarefa a remover: ");
                    int idRemover = scanner.nextInt();
                    manager.removerTarefa(idRemover);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}