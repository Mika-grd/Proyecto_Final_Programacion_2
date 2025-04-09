package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Categoria;

public class CuentaCategoriasBuilder extends CuentaDebitoBuilder implements ICuentaDebitoConCategoriasBuilder {

    @Override
    public void añadirCategoria(Categoria categoria) {
        getCuentaDebito().getListaCategorias().add(categoria);
    }
}
