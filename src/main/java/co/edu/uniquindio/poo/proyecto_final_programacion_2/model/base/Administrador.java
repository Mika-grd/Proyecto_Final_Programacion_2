package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class Administrador extends Persona implements IAdmin {

    /**
     * Constructor de la clase Administrador
     * @param nombre
     * @param id
     * @param correo
     * @param telefono
     * @param contraseña
     */
    public Administrador(String nombre, String id, String correo, String telefono, String contraseña) {
        super(nombre, id, correo, telefono, contraseña);
    }


    /**
     * Agrega un objeto a una lista especifica usando un metodo desde la billetera virtual
     * @param objeto
     * @param listaObjetos
     * @return Mensaje informando que se agrego el objeto
     * @param <T>
     */
    @Override
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().agregarObjeto(objeto, listaObjetos);
    }


    /**
     *  Edita un objeto que ya existe y lo agrega a la lista usando tambien un metodo desde la billetera virtual
     * @param objeto
     * @param objetoNuevo
     * @param listaObjetos
     * @return mensaje informado el resultado de la operacion
     * @param <T>
     */
    @Override
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().editarObjeto(objeto, objetoNuevo, listaObjetos);
    }


    /**
     * Elimina un objeto de la lista usando un metodo desde la billetera virtual
     * @param objeto
     * @param listaObjetos
     * @return mensaje indicando el resultado de la operacion
     * @param <T>
     */
    @Override
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().eliminarObjeto(objeto, listaObjetos);
    }


    /**
     * Busca un objeto en una lista por id utilizando un metodo desde la billetera virtual
     * @param id
     * @param listaObjetos
     * @return el objeto que se encontro
     * @param <T>
     */
    @Override
    public <T> Object buscarObjeto(String id, LinkedList<T> listaObjetos) {

        return BilleteraVirtual.getInstance().buscarObjeto(id, listaObjetos);
    }
}