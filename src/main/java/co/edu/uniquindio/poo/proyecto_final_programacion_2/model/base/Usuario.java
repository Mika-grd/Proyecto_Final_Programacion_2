package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class Usuario extends Persona {
    private double saldo;

    public Usuario(String nombre, String id, String correo, String telefono, double saldo) {
        super(nombre, id, correo, telefono);
        this.saldo = saldo;
    }
}
