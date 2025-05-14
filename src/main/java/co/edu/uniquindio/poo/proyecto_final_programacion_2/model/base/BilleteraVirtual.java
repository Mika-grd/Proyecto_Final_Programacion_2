package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class BilleteraVirtual implements ICrud {
    private String nombre;
    private LinkedList<Persona> listaPersonas = new LinkedList<>();
    private LinkedList<Cuenta> listaCuentas = new LinkedList<>();
    private static BilleteraVirtual instance;

    /// Constructor privado para mas adelante hacer uso del patron de diseño "Singleton" de billetera virtual
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


    //CRUD generico


    /**
     * Agrega un objeto generico a una lista
     * @param objeto
     * @param listaObjetos
     * @return retorna un mensaje "exitoso" si se agrego correctamente, de lo contrario suelta "No exitoso"
     * @param <T>
     */
    @Override
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos) {
        if (objeto != null) {
            listaObjetos.add(objeto);
            return "Exitoso";
        }
        return "No exitoso";
    }

    /**
     * Edita un objeto existente con un nuevo objeto dentro de una lista
     * @param objeto
     * @param objetoNuevo
     * @param listaObjetos
     * @return un mensaje "exitoso" si se pudo editar correctamente o de lo contrario un mensaje "no exitoso"
     * @param <T>
     */
    @Override
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos) {

        String resultado = "Exitoso";
        if (objeto != null && objetoNuevo != null) {
            listaObjetos.remove(objeto);
            listaObjetos.add(objetoNuevo);
            return resultado;
        }
        return "No exitoso";
    }


    /**
     * Elimina un objeto de la lista
     * @param objeto
     * @param listaObjetos
     * @return en caso de que se pudo eliminar correctamente "exitoso" pero si no se pudo "no exitoso"
     * @param <T>
     */
    @Override
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos) {
        if (objeto != null) {
            listaObjetos.remove(objeto);
            return "Exitoso";
        }
        return "No exitoso";
    }

    /**
     * Busca un objeto por la id dentro de la lista
     * @param id
     * @param listaObjetos
     * @return el objeto encontrado, de lo contrario retorna null si no se encuentra el objeto con el ID dado
     * @param <T>
     */

    @Override
    public <T> T buscarObjeto(String id, LinkedList<T> listaObjetos) {
        for (T objeto : listaObjetos) {
            try {
                Method getIdMethod = objeto.getClass().getMethod("getId");
                Object objetoId = getIdMethod.invoke(objeto);

                if (objetoId != null && objetoId.equals(id)) {
                    return objeto;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
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