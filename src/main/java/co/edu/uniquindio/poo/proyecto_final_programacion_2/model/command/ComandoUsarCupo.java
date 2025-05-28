package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion; // Necesario para crear la transacción

public class ComandoUsarCupo implements ComandoOperacionCuenta {
    private CuentaCredito cuenta;
    private double monto;
    private String descripcion;

    //Constructo
    public ComandoUsarCupo(CuentaCredito cuenta, double monto, String descripcion) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    @Override
    public boolean ejecutar() {
        // La lógica de negocio ahora está en el método usarCupo de CuentaCredito,
        // que a su vez delega a Transaccion.
        // Aquí no volvemos a pasar la lógica, solo invocamos el método de la cuenta.
        return cuenta.usarCupo(monto, descripcion);
    }
}