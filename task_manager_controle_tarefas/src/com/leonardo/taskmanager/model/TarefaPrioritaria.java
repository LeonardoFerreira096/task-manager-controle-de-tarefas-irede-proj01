package com.leonardo.taskmanager.model;

import java.time.LocalDate;

public class TarefaPrioritaria extends Tarefa {
    private int prazoMaximoHoras;
    private String motivoUrgencia;

    public TarefaPrioritaria(String titulo, String descricao, String responsavel, LocalDate prazo, Prioridade prioridade, Status status, int prazoMaximoHoras, String motivoUrgencia) {
        super(titulo, descricao, responsavel, prazo, prioridade, status);
        this.prazoMaximoHoras = prazoMaximoHoras;
        this.motivoUrgencia = motivoUrgencia;
    }

    public int getPrazoMaximoHoras() {
        return prazoMaximoHoras;
    }

    public void setPrazoMaximoHoras(int prazoMaximoHoras) {
        this.prazoMaximoHoras = prazoMaximoHoras;
    }

    public String getMotivoUrgencia() {
        return motivoUrgencia;
    }

    public void setMotivoUrgencia(String motivoUrgencia) {
        this.motivoUrgencia = motivoUrgencia;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Prazo Máximo: " + prazoMaximoHoras + "h" +
                ", Motivo da Urgência: '" + motivoUrgencia + "'";
    }
}
