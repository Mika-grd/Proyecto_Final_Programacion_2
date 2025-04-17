package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public interface ICrud {
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos);
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos);
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos);
    public <T> Object buscarObjeto(String id, LinkedList<T> listaObjetos);

}
