package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.util.LinkedList;

public class UsuarioDTO {
    private final String telefonoUsuario;
    private final String correoUsuario;
    private final String nombreUsuario;
    private final String idUsuario;

    /// Constructor de la clase UsuarioDTO
    public UsuarioDTO(Usuario usuario) {
        this.telefonoUsuario = usuario.getTelefono();
        this.correoUsuario = usuario.getCorreo();
        this.nombreUsuario = usuario.getNombre();
        this.idUsuario = usuario.getId();
    }

    /// SETTERS & GETTERS
    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }



    @Override
    public String toString() {
        return
                "Telefono: " + telefonoUsuario + '\'' +
                ", Correo: " + correoUsuario + '\'' +
                ", nombre: " + nombreUsuario + '\'' +
                ", id: " + idUsuario + '\'' ;
    }
}
