package com.main.decorador;

import java.sql.SQLException;

public class LogDecorador implements CertificadoServiceInterface {
    private final CertificadoServiceInterface certificadoService;

    public LogDecorador(CertificadoServiceInterface certificadoService) {
        this.certificadoService = certificadoService;
    }

    @Override
    public void generarCertificado(int estudianteId) throws SQLException {
        certificadoService.generarCertificado(estudianteId);
        registrarEnLog(estudianteId);
    }

    private void registrarEnLog(int estudianteId) {
        System.out.println("[LOG] Certificado generado para el estudiante con ID: " + estudianteId);
    }
}