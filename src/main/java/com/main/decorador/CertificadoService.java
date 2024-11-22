package com.main.decorador;

import com.main.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CertificadoService implements CertificadoServiceInterface {
    @Override
    public void generarCertificado(int estudianteId) throws SQLException {
        String estudianteQuery = "SELECT nombre, apellido FROM estudiantes WHERE id = ?";
        String notasQuery = """
                SELECT c.nombre AS curso, n.nota
                FROM notas n
                JOIN cursos c ON n.curso_id = c.id
                WHERE n.estudiante_id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Obtener información del estudiante
            String nombreCompleto = "";
            try (PreparedStatement estudianteStmt = conn.prepareStatement(estudianteQuery)) {
                estudianteStmt.setInt(1, estudianteId);
                try (ResultSet rs = estudianteStmt.executeQuery()) {
                    if (rs.next()) {
                        nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
                    } else {
                        System.out.println("Estudiante no encontrado.");
                        return;
                    }
                }
            }

            // Obtener las notas del estudiante
            Map<String, Double> notas = new HashMap<>();
            try (PreparedStatement notasStmt = conn.prepareStatement(notasQuery)) {
                notasStmt.setInt(1, estudianteId);
                try (ResultSet rs = notasStmt.executeQuery()) {
                    while (rs.next()) {
                        notas.put(rs.getString("curso"), rs.getDouble("nota"));
                    }
                }
            }

            // Generar el certificado
            System.out.println("\n--- Certificado de Notas ---");
            System.out.println("Estudiante: " + nombreCompleto);
            System.out.println("Cursos y Notas:");
            notas.forEach((curso, nota) -> System.out.println("  - " + curso + ": " + nota));
            System.out.println("---------------------------");
        }
    }
}