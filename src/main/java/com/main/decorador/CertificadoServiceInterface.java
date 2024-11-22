package com.main.decorador;

import java.sql.SQLException;

public interface CertificadoServiceInterface {
    void generarCertificado(int estudianteId) throws SQLException;
}
