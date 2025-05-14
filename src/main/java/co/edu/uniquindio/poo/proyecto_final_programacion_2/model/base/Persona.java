package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public abstract class Persona {
    private String nombre;
    private String id;
    private String correo;
    private String telefono;
    private String contraseña;


    /// Constructor de la clase Persona
    public Persona(String nombre, String id, String correo, String telefono, String contraseña) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }


    /// SETTERS & GETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
