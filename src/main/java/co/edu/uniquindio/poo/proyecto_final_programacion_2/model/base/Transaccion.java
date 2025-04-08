package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.time.LocalDate;
import java.util.LinkedList;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fechaTransaccion;
    private double monto;
    private String descripcion;
    private LinkedList<Cuenta> listaCuentas = new LinkedList<>();

    public Transaccion(String idTransaccion, LocalDate fechaTransaccion, double monto, String descripcion) {
        this.idTransaccion = idTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
}
