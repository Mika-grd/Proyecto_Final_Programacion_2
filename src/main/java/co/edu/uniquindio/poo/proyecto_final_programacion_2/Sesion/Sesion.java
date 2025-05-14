package co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;


public class Sesion {

    //instancia unica en la clase
    private static Sesion instancia;

    private Usuario usuario;
    private Administrador administrador;
    private Cuenta cuentaSeleccionada;
    private CuentaDebito cuentaDebito;

    private Sesion() {
        // Constructor privado para singleton
    }

    /// implementacion del singleton
    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }


    /// SETTER & GETTERS
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


    /**
     * Cierra la sesion actual, limpiando todos los datos
     */
    public void cerrarSesion() {
        usuario = null;
        cuentaSeleccionada = null;
        instancia = null;
        cuentaDebito = null;
    }
}

