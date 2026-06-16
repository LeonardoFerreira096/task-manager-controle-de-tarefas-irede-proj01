package com.leonardo.taskmanager;

import java.util.ArrayList;


public class GerenciadorDeTarefas {
    private ArrayList<Tarefa> tarefa = new ArrayList<Tarefa>();

    public void adicionarTarefas(Tarefa t) {
        tarefa.add(t);
    }

    public void listarTarefa() {
        for (Tarefa tarefaAtual : tarefa) {
            System.out.println(tarefaAtual.getTitulo());
            System.out.println(tarefaAtual.getDescricao());
            System.out.println(tarefaAtual.getPrazo());
            System.out.println(tarefaAtual.getPrioridade());
            System.out.println(tarefaAtual.getResponsavel());
            System.out.println(tarefaAtual.getStatus());
        }
    }

    public void concluirTarefa(){

    }

    public void removerTarefa(){

    }

}
