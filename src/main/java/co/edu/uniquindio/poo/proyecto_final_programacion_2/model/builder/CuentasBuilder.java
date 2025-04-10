package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;

public interface CuentasBuilder {


    void setId(String id);
    void setNombreBanco(String nombreBanco);
    void setNumCuenta(int numeroCuenta);
    void setUsuario(Usuario usuario);
    Cuenta build();


}
