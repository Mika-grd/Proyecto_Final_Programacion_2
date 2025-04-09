package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Usuario extends Persona implements IUsuario{

    private String contraseña;
    private LinkedList<Cuenta> listaCuentas;

    ///////Constructor
    public Usuario(String nombre, String id, String correo, String telefono, String contraseña) {
        super(nombre, id, correo, telefono);
        this.contraseña = contraseña;
        this.listaCuentas = new LinkedList<>();
    }



    /// Crud generico
    @Override
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return agregarObjeto(objeto, listaObjetos);
    }

    @Override
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos) {
        return editarObjeto(objeto, objetoNuevo, listaObjetos);
    }

    @Override
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return eliminarObjeto(objeto, listaObjetos);
    }

    @Override
    public <T> Object buscarObjeto(T id, LinkedList<T> listaObjetos) {
        return buscarObjeto(id, listaObjetos);
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
}
