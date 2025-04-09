package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class Presupuesto {
    private String id;
    private String nombre;
    private int montoActual;
    private int montoGastado;

    ///////Constructor
    public Presupuesto(String id, String nombre, int montoActual, int montoGastado) {
        this.id = id;
        this.nombre = nombre;
        this.montoActual = montoActual;
        this.montoGastado = montoGastado;
    }







    ////////Setters & getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(int montoActual) {
        this.montoActual = montoActual;
    }

    public int getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(int montoGastado) {
        this.montoGastado = montoGastado;
    }
}
