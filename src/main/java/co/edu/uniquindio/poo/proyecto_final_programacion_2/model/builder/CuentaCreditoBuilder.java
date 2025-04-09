package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;


public class CuentaCreditoBuilder implements ICuentaCreditoBuilder {

    private String id;
    private String nombreBanco;
    private int numeroCuenta;
    private double tasaInteres;
    private double cupoDisponible;
    private double cupoEnUso;
    private Usuario usuario;

    public CuentaCreditoBuilder() {
    }

    @Override
    public void setIntereses(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void setCupoDisponible(double cupoDisponible) {
        this.cupoDisponible = cupoDisponible;

    }

    @Override
    public void setCupoEnUso(double cupoEnUso) {
        this.cupoEnUso = cupoEnUso;

    }

    @Override
    public void setId(String id) {
        this.id = id;

    }

    @Override
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;

    }

    @Override
    public void setNumCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Cuenta build() {
        // Validación básica
        if (this.id == null || this.nombreBanco == null) {
            throw new IllegalStateException("El ID y el Nombre del Banco son obligatorios para construir la cuenta.");
        }

        return new CuentaCredito(
                this.id,
                this.nombreBanco,
                this.numeroCuenta,
                this.usuario,
                this.tasaInteres,
                this.cupoDisponible,
                this.cupoEnUso
        );
    }
}
