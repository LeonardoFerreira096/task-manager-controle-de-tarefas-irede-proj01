package com.leonardo.taskmanager;

import java.time.LocalDate;

public class Tarefa {
   private static int contadorId = 0;
   private int id;
   private String titulo;
   private String descricao;
   private String responsavel;
   private LocalDate prazo;
   private Prioridade prioridade;
   private Status status;

   public void concluir () {
      this.status = Status.CONCLUIDO;
   }

   public Tarefa(String titulo, String descricao, String responsavel, LocalDate prazo, Prioridade prioridade, Status status) {
      this.titulo = titulo;
      this.descricao = descricao;
      this.responsavel = responsavel;
      this.prazo = prazo;
      this.prioridade = prioridade;
      this.status = status;
      contadorId++;
      this.id = contadorId;
   }

   public static int getContadorId() {
      return contadorId;
   }

   public int getId() {
      return id;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public String getResponsavel() {
      return responsavel;
   }

   public void setResponsavel(String responsavel) {
      this.responsavel = responsavel;
   }

   public LocalDate getPrazo() {
      return prazo;
   }

   public void setPrazo(LocalDate prazo) {
      this.prazo = prazo;
   }

   public Prioridade getPrioridade() {
      return prioridade;
   }

   public void setPrioridade(Prioridade prioridade) {
      this.prioridade = prioridade;
   }

   public Status getStatus() {
      return status;
   }

   public void setStatus(Status status) {
      this.status = status;
   }
}


