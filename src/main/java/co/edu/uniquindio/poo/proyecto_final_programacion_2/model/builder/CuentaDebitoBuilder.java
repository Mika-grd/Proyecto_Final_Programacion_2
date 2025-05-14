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

    /// Constructor por defecto
    public CuentaDebitoBuilder() {
    }


    /**
     * establece el saldo en la cuenta de debito
     * @param saldo
     */
    @Override
    public void serSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * establece el id
     * @param id
     */
    @Override
    public void setId(String id) {
        this.id = id;

    }

    /**
     * establece el nombre del banco
     * @param nombreBanco
     */
    @Override
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;

    }

    /**
     * establece el numero de la cuenta
     * @param numeroCuenta
     */
    @Override
    public void setNumCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

    }

    /**
     * asocia un usuario como propietario de la cuenta
     * @param usuario
     */
    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    /// hace el builder que construye y retorna la cuenta debito con los parametros
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

    /// SETTERS & GETTERS
    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }
}
