package co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;

/**
 * La clase {@code Sesion} gestiona la sesión del usuario en la aplicación.
 *
 * Proporciona acceso al usuario autenticado y a la cuenta seleccionada.
 * Esta clase usa el patrón Singleton, lo que significa que solo existe
 * una instancia de sesión durante la ejecución de la aplicación.
 */

public class Sesion {

    private static Sesion instancia;

    private Usuario usuario;
    private Administrador administrador;
    private Cuenta cuentaSeleccionada;
    private CuentaDebito cuentaDebito;

    private Sesion() {
        // Constructor privado para singleton
    }

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cuenta getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    public static void setInstancia(Sesion instancia) {
        Sesion.instancia = instancia;
    }

    public CuentaDebito getCuentaDebito() {
        return cuentaDebito;
    }

    public void setCuentaDebito(CuentaDebito cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void cerrarSesion() {
        usuario = null;
        cuentaSeleccionada = null;
        instancia = null;
        cuentaDebito = null;
    }
}

