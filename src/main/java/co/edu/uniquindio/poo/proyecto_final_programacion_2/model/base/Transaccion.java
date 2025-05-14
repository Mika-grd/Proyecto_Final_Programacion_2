package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.time.LocalDate;
import java.util.LinkedList;

public class Transaccion {
    private String id;
    private LocalDate fechaTransaccion;
    private double montoATransferir;
    private double montoDisponible;
    private String descripcion;
    private CuentaDebito cuentaPropia;
    private CuentaDebito cuentaObjetivo;


    ////Constructor de la clase Transaccion

    public Transaccion(String id, LocalDate fechaTransaccion, double montoATransferir, double montoDisponible, String descripcion, CuentaDebito cuentaPropia, CuentaDebito cuentaObjetivo) {
        this.id = id;
        this.fechaTransaccion = fechaTransaccion;
        this.montoATransferir = montoATransferir;
        this.montoDisponible = montoDisponible;
        this.descripcion = descripcion;
        this.cuentaPropia = cuentaPropia;
        this.cuentaObjetivo = cuentaObjetivo;
    }


    /**
     * Realiza una transacción de transferencia entre dos cuentas.
     *
     * Verifica que todos los datos necesarios estén definidos correctamente:
     * - La cuenta objetivo no debe ser nula.
     * - La fecha de transacción debe estar definida.
     * - El monto a transferir debe ser mayor a 0.
     * - El monto disponible debe ser mayor a 0.
     *
     * Si todos los valores son válidos y el monto a transferir es menor o igual al monto disponible,
     * se realiza la transferencia: se descuenta el monto de la cuenta propia y se suma a la cuenta objetivo.
     *
     * @return true si la transacción se realizó exitosamente, false en caso contrario.
     */
    public boolean realizarTransaccion() {
        if (cuentaObjetivo != null && fechaTransaccion != null && montoATransferir > 0 && montoDisponible > 0) {
            if (montoATransferir <= montoDisponible) {
                cuentaPropia.setSaldo(cuentaPropia.getSaldo() - montoATransferir);
                cuentaObjetivo.setSaldo(cuentaObjetivo.getSaldo() + montoATransferir);
                return true;
            }
        }
        return false;
    }

    /// SETTERS & GETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getMontoATransferir() {
        return montoATransferir;
    }

    public void setMontoATransferir(double montoATransferir) {
        this.montoATransferir = montoATransferir;
    }

    public double getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CuentaDebito getCuentaPropia() {
        return cuentaPropia;
    }

    public void setCuentaPropia(CuentaDebito cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
    }

    public CuentaDebito getCuentaObjetivo() {
        return cuentaObjetivo;
    }

    public void setCuentaObjetivo(CuentaDebito cuentaObjetivo) {
        this.cuentaObjetivo = cuentaObjetivo;
    }
}
