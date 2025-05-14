package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;


public class Usuario extends Persona implements IUsuario{


    private LinkedList<Cuenta> listaCuentas;

    ///////Constructor de la clase Usuario
    public Usuario(String nombre, String id, String correo, String telefono, String contraseña) {
        super(nombre, id, correo, telefono, contraseña);
        this.listaCuentas = new LinkedList<>();
    }

    /// Crud generico

    /**
     * Agrega un objeto a una lista usando la logica centralizada de billetera virtual
     * @param objeto
     * @param listaObjetos
     * @return "Exitoso" si la operación fue realizada, de lo contrario "No exitoso".
     * @param <T>
     */
    @Override
    public <T> String agregarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().agregarObjeto(objeto, listaObjetos);
    }


    /**
     * Edita un objeto ya existente por uno nuevo en una lista  usando la logica centralizada de billetera virtual
     * @param objeto
     * @param objetoNuevo
     * @param listaObjetos
     * @return 
     * @param <T>
     */
    @Override
    public <T> String editarObjeto(T objeto, T objetoNuevo, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().editarObjeto(objeto, objetoNuevo, listaObjetos);
    }

    @Override
    public <T> String eliminarObjeto(T objeto, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().eliminarObjeto(objeto, listaObjetos);
    }

    @Override
    public <T> Object buscarObjeto(String id, LinkedList<T> listaObjetos) {
        return BilleteraVirtual.getInstance().buscarObjeto(id, listaObjetos);
    }



    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
}
