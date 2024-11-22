package com.main.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.main.DatabaseConnection;
import com.main.models.Curso;

public class CursoDAO {
    public List<Curso> obtenerCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM cursos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        }
        return cursos;
    }

    public void agregarCurso(Curso curso) throws SQLException {
        String query = "INSERT INTO cursos (nombre, descripcion) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());

            stmt.executeUpdate();
        }
    }

    public void actualizarCurso(Curso curso) throws SQLException {
        String query = "UPDATE cursos SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setInt(3, curso.getId());

            stmt.executeUpdate();
        }
    }

    public void eliminarCurso(int id) throws SQLException {
        String query = "DELETE FROM cursos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
