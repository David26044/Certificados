package com.main.decorador;

import java.sql.SQLException;

public class QRDecorador implements CertificadoServiceInterface {
    private final CertificadoServiceInterface certificadoService;

    public QRDecorador(CertificadoServiceInterface certificadoService) {
        this.certificadoService = certificadoService;
    }

    @Override
    public void generarCertificado(int estudianteId) throws SQLException {
        certificadoService.generarCertificado(estudianteId);
        generarCodigoQR(estudianteId);
    }

    private void generarCodigoQR(int estudianteId) {
        System.out.println("[QR] Generando código QR para el certificado del estudiante con ID: " + estudianteId);
    }
}