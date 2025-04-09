package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Cuenta {
    private String id;
    private String nombreBanco;
    private int numCuenta;
    private Usuario usuario;
    private LinkedList<Transaccion> listaTransaccion;
    private LinkedList<Categoria> listaCategorias;

    /////Constructor
    public Cuenta(String id, String nombreBanco, int numCuenta, Usuario usuario) {
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numCuenta = numCuenta;
        this.usuario = usuario;
        this.listaTransaccion = new LinkedList<Transaccion>();
        this.listaCategorias = new LinkedList<Categoria>();
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
