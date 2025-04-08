package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public interface ICrud {
    public <T> String agregarObjeto(T objeto);
    public <T> String actualizarObjeto(T objeto);
    public <T> String eliminarObjeto(T objeto);
    public <T> String buscarObjeto(T objeto);
}
