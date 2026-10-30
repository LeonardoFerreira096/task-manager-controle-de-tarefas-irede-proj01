package com.leonardo.taskmanager.infra;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/taskmanager";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}