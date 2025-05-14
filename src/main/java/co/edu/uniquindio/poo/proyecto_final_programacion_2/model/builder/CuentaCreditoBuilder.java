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

    /// Constructor por defecto
    public CuentaCreditoBuilder() {
    }

    /**
     * establece la tasa de interes en una cuenta de credito
     * @param tasaInteres
     */
    @Override
    public void setIntereses(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    /**
     * establece el cupo disponible en la cuenta de credito
     * @param cupoDisponible
     */
    @Override
    public void setCupoDisponible(double cupoDisponible) {
        this.cupoDisponible = cupoDisponible;

    }

    /**
     * establece el cupo en uso en la cuenta de credito
     * @param cupoEnUso
     */
    @Override
    public void setCupoEnUso(double cupoEnUso) {
        this.cupoEnUso = cupoEnUso;

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
     * establece el numero de cuenta
     * @param numeroCuenta
     */
    @Override
    public void setNumCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

    }

    /**
     * asocia un usuario a la cuenta
     * @param usuario
     */
    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /// hace el builder, osea que construye y retorna la instancia con los atributos
    @Override
    public CuentaCredito build() {
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
