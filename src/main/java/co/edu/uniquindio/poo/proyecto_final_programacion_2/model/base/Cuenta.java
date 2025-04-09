package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Cuenta {
    private String id;
    private String nombreBanco;
    private int numCuenta;
    private LinkedList<Transaccion> listaTransaccion = new LinkedList<>();
    private LinkedList<Categoria> listaCategorias = new LinkedList<>();

    /////Constructor
    public Cuenta(String id, String nombreBanco, int numCuenta) {
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numCuenta = numCuenta;
    }





    ////SETTERS & GETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public LinkedList<Transaccion> getListaTransaccion() {
        return listaTransaccion;
    }

    public void setListaTransaccion(LinkedList<Transaccion> listaTransaccion) {
        this.listaTransaccion = listaTransaccion;
    }

    public LinkedList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
}
