package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Administrador extends Persona implements IAdmin {

    public Administrador(String nombre, String id, String correo, String telefono) {
        super(nombre, id, correo, telefono);

    }


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
}
