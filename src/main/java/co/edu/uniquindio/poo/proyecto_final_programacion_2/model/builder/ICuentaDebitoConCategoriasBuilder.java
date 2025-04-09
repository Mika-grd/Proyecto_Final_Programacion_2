package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Categoria;

public interface ICuentaDebitoConCategoriasBuilder extends  ICuentaDebitoBuilder{

    void añadirCategoria(Categoria categoria);

}
