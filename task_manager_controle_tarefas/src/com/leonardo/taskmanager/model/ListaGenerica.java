package com.leonardo.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class ListaGenerica<T> {
    private List<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        itens.add(item);
    }

    public List<T> listar() {
        return itens;
    }

    public void remover(T item) {
        itens.remove(item);
    }


    public void adicionarTodos(List<? extends T> origem) {
        itens.addAll(origem);
    }


    public void copiarPara(List<? super T> destino) {
        destino.addAll(itens);
    }


    public static void imprimir(List<?> lista) {
        for (Object item : lista) {
            System.out.println(item);
        }
    }
}