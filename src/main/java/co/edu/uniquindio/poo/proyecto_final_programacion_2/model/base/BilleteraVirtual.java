package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class BilleteraVirtual {
    private String nombre;
    private LinkedList<Persona> listaPersonas = new LinkedList<>();
    private LinkedList<Cuenta> listaCuentas = new LinkedList<>();
    private static BilleteraVirtual instance;

    /// Constructor
    private BilleteraVirtual(String nombre) {
        this.nombre = nombre;
    }

    //Implementacion del singleton//
    public static BilleteraVirtual getInstance() {
        if (instance == null) {
            instance = new BilleteraVirtual("Billetera Virtual ");
        }
        return instance;
    }







    /// Setters & getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(LinkedList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public static void setInstance(BilleteraVirtual instance) {
        BilleteraVirtual.instance = instance;
    }
}