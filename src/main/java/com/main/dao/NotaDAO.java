package com.main.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.main.DatabaseConnection;
import com.main.models.Nota;

public class NotaDAO {
    public List<Nota> obtenerNotas() throws SQLException {
        List<Nota> notas = new ArrayList<>();
        String query = "SELECT * FROM notas";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                notas.add(new Nota(
                        rs.getInt("id"),
                        rs.getInt("estudiante_id"),
                        rs.getInt("curso_id"),
                        rs.getDouble("nota")
                ));
            }
        }
        return notas;
    }

    public void agregarNota(Nota nota) throws SQLException {
        String query = "INSERT INTO notas (estudiante_id, curso_id, nota) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota.getEstudianteId());
            stmt.setInt(2, nota.getCursoId());
            stmt.setDouble(3, nota.getNota());

            stmt.executeUpdate();
        }
    }

    public void actualizarNota(Nota nota) throws SQLException {
        String query = "UPDATE notas SET estudiante_id = ?, curso_id = ?, nota = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota.getEstudianteId());
            stmt.setInt(2, nota.getCursoId());
            stmt.setDouble(3, nota.getNota());
            stmt.setInt(4, nota.getId());

            stmt.executeUpdate();
        }
    }

    public void eliminarNota(int id) throws SQLException {
        String query = "DELETE FROM notas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
