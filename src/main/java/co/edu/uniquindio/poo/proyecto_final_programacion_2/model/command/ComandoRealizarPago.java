package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion; // Necesario para crear la transacción

public class ComandoRealizarPago implements ComandoOperacionCuenta {
    private CuentaCredito cuenta;
    private double monto;

    public ComandoRealizarPago(CuentaCredito cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public boolean ejecutar() {
        return cuenta.realizarPago(monto);
    }
}