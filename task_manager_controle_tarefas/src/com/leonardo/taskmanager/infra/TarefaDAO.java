package com.leonardo.taskmanager.infra;

import com.leonardo.taskmanager.model.Prioridade;
import com.leonardo.taskmanager.model.Status;
import com.leonardo.taskmanager.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, responsavel, prazo, prioridade, status, concluida) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getResponsavel());
            stmt.setDate(4, java.sql.Date.valueOf(tarefa.getPrazo()));
            stmt.setString(5, tarefa.getPrioridade().name());
            stmt.setString(6, tarefa.getStatus().name());
            stmt.setBoolean(7, false);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir: " + e.getMessage());
        }
    }

    public void atualizar(Tarefa t) {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, responsavel = ?, prazo = ?, prioridade = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getTitulo());
            stmt.setString(2, t.getDescricao());
            stmt.setString(3, t.getResponsavel());
            stmt.setDate(4, java.sql.Date.valueOf(t.getPrazo()));
            stmt.setString(5, t.getPrioridade().name());
            stmt.setInt(6, t.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTodas() {
        String sql = "SELECT * FROM tarefas ORDER BY id";
        List<Tarefa> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa t = new Tarefa(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("responsavel"),
                        rs.getDate("prazo").toLocalDate(),
                        Prioridade.valueOf(rs.getString("prioridade")),
                        Status.valueOf(rs.getString("status")));
                t.setId(rs.getInt("id"));
                lista.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void concluir(int id) {
        String sql = "UPDATE tarefas SET status = 'CONCLUIDO', concluida = true WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao concluir: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover: " + e.getMessage());
        }

    }
}