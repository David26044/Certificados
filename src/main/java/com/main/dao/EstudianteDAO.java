package com.main.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.main.DatabaseConnection;
import com.main.models.Estudiante;

public class EstudianteDAO {
    public List<Estudiante> obtenerEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                estudiantes.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("documento_identidad"),
                        rs.getDate("fecha_nacimiento")
                ));
            }
        }
        return estudiantes;
    }

    public void agregarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "INSERT INTO estudiantes (nombre, apellido, documento_identidad, fecha_nacimiento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getDocumentoIdentidad());
            stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));

            stmt.executeUpdate();
        }
    }

    public void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "UPDATE estudiantes SET nombre = ?, apellido = ?, documento_identidad = ?, fecha_nacimiento = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getDocumentoIdentidad());
            stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmt.setInt(5, estudiante.getId());

            stmt.executeUpdate();
        }
    }

    public void eliminarEstudiante(int id) throws SQLException {
        String query = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
