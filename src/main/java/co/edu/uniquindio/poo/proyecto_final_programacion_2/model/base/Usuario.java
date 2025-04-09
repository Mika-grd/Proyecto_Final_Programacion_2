package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Usuario extends Persona implements IUsuario{

    private double saldo;

    ///////Constructor
    public Usuario(String nombre, String id, String correo, String telefono, double saldo) {
        super(nombre, id, correo, telefono);
        this.saldo = saldo;
    }



    /// Crud generico
    @Override
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().agregarObjeto(objeto, listaObjetos);
    }

    @Override
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().editarObjeto(objeto, objetoNuevo, listaObjetos);
    }

    @Override
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().eliminarObjeto(objeto, listaObjetos);
    }

    @Override
    public <T> Object buscarObjeto(T id, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().buscarObjeto(id, listaObjetos);
    }








    ////Setters & getters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
