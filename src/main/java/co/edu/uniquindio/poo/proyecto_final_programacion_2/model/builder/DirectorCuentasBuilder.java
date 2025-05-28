package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;

public class DirectorCuentasBuilder {

    /// Construye una cuenta de crédito Simple
    public Cuenta cuentaCreditoSimple(ICuentaCreditoBuilder builder, String id, int numCuenta, Usuario usuario) {
        builder.setId(id);
        builder.setNombreBanco("Banco Principal");
        builder.setNumCuenta(numCuenta);
        builder.setUsuario(usuario);
        builder.setCupoEnUso(0);
        builder.setCupoDisponible(1000000);
        builder.setIntereses(0.05); // 5%

        return builder.build();
    }

    /// Construye una cuenta de debito Simple
    public Cuenta cuentaDebitoSimple(ICuentaDebitoBuilder builder, String id, int numCuenta, Usuario usuario) {
        builder.setNombreBanco("Banco Principal");
        builder.serSaldo(0);
        return construirCuentaDebito(builder, id, numCuenta, usuario);
    }

    /// Ejecuta el comando de construir y coloca los atributos que tiene que pasar el cliente
    private Cuenta construirCuentaDebito(ICuentaDebitoBuilder builder, String id, int numCuenta, Usuario usuario) {
        builder.setNumCuenta(numCuenta);
        builder.setId(id);
        builder.setUsuario(usuario);
        return builder.build();
    }


    /// Construye una cuenta de debito con una categoria dada por el cliente
    public Cuenta cuentaDebitoConUnaCategoria(ICuentaDebitoConCategoriasBuilder builder, String id, int numCuenta, Usuario usuario, Categoria categoria) {
        builder.setNombreBanco("Banco Principal");
        builder.serSaldo(0);
        Cuenta cuenta = construirCuentaDebito(builder, id, numCuenta, usuario);
        builder.añadirCategoria(categoria);
        return cuenta;

    }

}
