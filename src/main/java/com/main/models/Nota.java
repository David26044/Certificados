package com.main.models;

public class Nota {
    private int id;
    private int estudianteId;
    private int cursoId;
    private double nota;

    public Nota(int id, int estudianteId, int cursoId, double nota) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.nota = nota;
    }

    // Constructor vacío
    public Nota() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", estudianteId=" + estudianteId +
                ", cursoId=" + cursoId +
                ", nota=" + nota +
                '}';
    }
}
