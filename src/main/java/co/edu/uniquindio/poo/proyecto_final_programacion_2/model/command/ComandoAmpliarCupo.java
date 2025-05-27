package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;

public class ComandoAmpliarCupo implements ComandoOperacionCuenta {
    private CuentaCredito cuenta;
    private double montoAmpliacion;

    public ComandoAmpliarCupo(CuentaCredito cuenta, double montoAmpliacion) {
        this.cuenta = cuenta;
        this.montoAmpliacion = montoAmpliacion;
    }

    @Override
    public boolean ejecutar() {
        return cuenta.ampliarCupo(montoAmpliacion);
    }
}