package com.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.main.dao.CursoDAO;
import com.main.dao.EstudianteDAO;
import com.main.dao.NotaDAO;
import com.main.decorador.CertificadoService;
import com.main.decorador.CertificadoServiceInterface;
import com.main.decorador.LogDecorador;
import com.main.decorador.QRDecorador;
import com.main.models.Curso;
import com.main.models.Estudiante;
import com.main.models.Nota;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // DAOs y servicios necesarios
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        CursoDAO cursoDAO = new CursoDAO();
        NotaDAO notaDAO = new NotaDAO();

        try {
            while (true) {
                System.out.println("\n--- Menú Principal ---");
                System.out.println("1. Gestionar Estudiantes");
                System.out.println("2. Gestionar Cursos");
                System.out.println("3. Gestionar Notas");
                System.out.println("4. Generar Certificado de Notas");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> gestionarEstudiantes(estudianteDAO, scanner);
                    case 2 -> gestionarCursos(cursoDAO, scanner);
                    case 3 -> gestionarNotas(notaDAO, scanner);
                    case 4 -> generarCertificado(scanner);
                    case 5 -> {
                        System.out.println("¡Hasta luego!");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción inválida. Intenta de nuevo.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void generarCertificado(Scanner scanner) {
        CertificadoServiceInterface certificadoService = new CertificadoService();

        System.out.println("Seleccione la funcionalidad adicional:");
        System.out.println("1. Registrar en log");
        System.out.println("2. Generar código QR");
        System.out.println("3. Ambas");
        System.out.println("4. Ninguna (Funcionalidad básica)");
        System.out.print("Elige una opción: ");
        int opcionDecorador = scanner.nextInt();

        switch (opcionDecorador) {
            case 1 -> certificadoService = new LogDecorador(certificadoService);
            case 2 -> certificadoService = new QRDecorador(certificadoService);
            case 3 -> certificadoService = new QRDecorador(new LogDecorador(certificadoService));
            case 4 -> System.out.println("Usando funcionalidad básica.");
            default -> System.out.println("Opción no válida. Usando funcionalidad básica.");
        }

        System.out.print("ID del estudiante: ");
        int estudianteId = scanner.nextInt();
        try {
            certificadoService.generarCertificado(estudianteId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void gestionarEstudiantes(EstudianteDAO estudianteDAO, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Gestión de Estudiantes ---");
            System.out.println("1. Listar Estudiantes");
            System.out.println("2. Agregar Estudiante");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    List<Estudiante> estudiantes = estudianteDAO.obtenerEstudiantes();
                    System.out.println("Estudiantes:");
                    for (Estudiante estudiante : estudiantes) {
                        System.out.println(estudiante);
                    }
                }
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.next();
                    System.out.print("Apellido: ");
                    String apellido = scanner.next();
                    System.out.print("Documento: ");
                    String documento = scanner.next();
                    System.out.print("Fecha de Nacimiento (yyyy-mm-dd): ");
                    String fecha = scanner.next();
                    estudianteDAO.agregarEstudiante(
                            new Estudiante(0, nombre, apellido, documento, java.sql.Date.valueOf(fecha)));
                    break;
                }
                case 3 -> {
                    System.out.print("ID del estudiante a actualizar: ");
                    int id = scanner.nextInt();
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.next();
                    System.out.print("Nuevo apellido: ");
                    String apellido = scanner.next();
                    System.out.print("Documento: ");
                    String documento = scanner.next();
                    System.out.print("Fecha de Nacimiento (yyyy-mm-dd): ");
                    String fecha = scanner.next();
                    estudianteDAO.actualizarEstudiante(new Estudiante(id, nombre, apellido, documento, java.sql.Date.valueOf(fecha)));
                    System.out.println("Estudiante actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("ID del estudiante a eliminar: ");
                    int id = scanner.nextInt();
                    estudianteDAO.eliminarEstudiante(id);
                    System.out.println("Estudiante eliminado correctamente.");
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void gestionarCursos(CursoDAO cursoDAO, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Gestión de Cursos ---");
            System.out.println("1. Listar Cursos");
            System.out.println("2. Agregar Curso");
            System.out.println("3. Actualizar Curso");
            System.out.println("4. Eliminar Curso");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    List<Curso> cursos = cursoDAO.obtenerCursos();
                    System.out.println("Cursos:");
                    for (Curso curso : cursos) {
                        System.out.println(curso);
                    }
                }
                case 2 -> {
                    System.out.print("Nombre del curso: ");
                    String nombre = scanner.next();
                    System.out.print("Descripción del curso: ");
                    String descripcion = scanner.next();
                    cursoDAO.agregarCurso(new Curso(0, nombre, descripcion));
                    System.out.println("Curso agregado correctamente.");
                }
                case 3 -> {
                    System.out.print("ID del curso a actualizar: ");
                    int id = scanner.nextInt();
                    System.out.print("Nuevo nombre del curso: ");
                    String nombre = scanner.next();
                    System.out.print("Nueva descripción del curso: ");
                    String descripcion = scanner.next();
                    cursoDAO.actualizarCurso(new Curso(id, nombre, descripcion));
                    System.out.println("Curso actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("ID del curso a eliminar: ");
                    int id = scanner.nextInt();
                    cursoDAO.eliminarCurso(id);
                    System.out.println("Curso eliminado correctamente.");
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void gestionarNotas(NotaDAO notaDAO, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Gestión de Notas ---");
            System.out.println("1. Listar Notas");
            System.out.println("2. Agregar Nota");
            System.out.println("3. Actualizar Nota");
            System.out.println("4. Eliminar Nota");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    List<Nota> notas = notaDAO.obtenerNotas();
                    System.out.println("Notas:");
                    for (Nota nota : notas) {
                        System.out.println(nota);
                    }
                }
                case 2 -> {
                    System.out.print("ID del estudiante: ");
                    int estudianteId = scanner.nextInt();
                    System.out.print("ID del curso: ");
                    int cursoId = scanner.nextInt();
                    System.out.print("Nota: ");
                    double notaValor = scanner.nextDouble();
                    notaDAO.agregarNota(new Nota(0, estudianteId, cursoId, notaValor));
                    System.out.println("Nota agregada correctamente.");
                }
                case 3 -> {
                    System.out.print("ID de la nota a actualizar: ");
                    int id = scanner.nextInt();
                    System.out.print("Nuevo ID del estudiante: ");
                    int estudianteId = scanner.nextInt();
                    System.out.print("Nuevo ID del curso: ");
                    int cursoId = scanner.nextInt();
                    System.out.print("Nueva nota: ");
                    double notaValor = scanner.nextDouble();
                    notaDAO.actualizarNota(new Nota(id, estudianteId, cursoId, notaValor));
                    System.out.println("Nota actualizada correctamente.");
                }
                case 4 -> {
                    System.out.print("ID de la nota a eliminar: ");
                    int id = scanner.nextInt();
                    notaDAO.eliminarNota(id);
                    System.out.println("Nota eliminada correctamente.");
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
