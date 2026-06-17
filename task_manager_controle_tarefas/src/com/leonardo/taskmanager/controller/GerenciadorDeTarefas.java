package com.leonardo.taskmanager.controller;

import com.leonardo.taskmanager.model.Tarefa;

import java.util.ArrayList;

public class GerenciadorDeTarefas {
    private ArrayList<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefas(Tarefa t) {
        tarefas.add(t);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada");
            return;
        }
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    public void concluirTarefa(int id) {
        boolean encontrada = false;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.concluir();
                encontrada = true;
                System.out.println("Tarefa concluída!");
            }
        }
        if (!encontrada) {
            System.out.println("Tarefa não encontrada");
        }
    }

    public void removerTarefa(int id) {
        boolean encontrada = false;
        Tarefa paraRemover = null;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                paraRemover = tarefa;
                encontrada = true;
            }
        }
        if (encontrada) {
            tarefas.remove(paraRemover);
            System.out.println("Tarefa removida!");
        } else {
            System.out.println("Tarefa não encontrada");
        }
    }
}