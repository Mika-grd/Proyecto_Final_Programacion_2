package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {
    private final String telefonoUsuario;
    private final String correoUsuario;
    private final String nombreUsuario;
    private final String idUsuario;
    private Usuario usuarioReal;


    /// Constructor de la clase UsuarioDTO
    public UsuarioDTO(Usuario usuario) {
        this.telefonoUsuario = usuario.getTelefono();
        this.correoUsuario = usuario.getCorreo();
        this.nombreUsuario = usuario.getNombre();
        this.idUsuario = usuario.getId();
        this.usuarioReal = usuario;
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

    public Usuario getUsuarioReal() {
        return usuarioReal;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        // Obtener todas las personas desde la billetera
        LinkedList<Persona> personas = BilleteraVirtual.getInstance().getListaPersonas();

        // Filtrar solo instancias de Usuario
        return personas.stream()
                .filter(p -> p instanceof Usuario)
                .map(p -> (Usuario) p)
                .collect(Collectors.toList());
    }

    public List<Cuenta> getListaCuentas() {
        return usuarioReal.getListaCuentas();
    }

    public List<Transaccion> getListaTransacciones() {
        return usuarioReal.getListaTransacciones();
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
