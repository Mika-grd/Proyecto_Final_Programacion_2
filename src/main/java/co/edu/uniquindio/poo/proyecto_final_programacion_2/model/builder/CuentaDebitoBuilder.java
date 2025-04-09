package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;

public class CuentaDebitoBuilder implements ICuentaDebitoBuilder{

    private String id;
    private String nombreBanco;
    private int numeroCuenta;
    private double saldo;
    private Usuario usuario;
    private Cuenta cuentaDebito;

    public CuentaDebitoBuilder() {
    }


    @Override
    public void serSaldo(double saldo) {
        this.saldo = saldo;
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

         cuentaDebito = new CuentaDebito(
                this.id,
                this.nombreBanco,
                this.numeroCuenta,
                this.usuario,
                this.saldo
        );
        return cuentaDebito;
    }

    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }
}
