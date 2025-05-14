package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class Presupuesto {
    private String id;
    private String nombre;
    private double montoActual;


    ///////Constructor de la clase Presupuesto
    public Presupuesto(String id, String nombre, double montoActual) {
        this.id = id;
        this.nombre = nombre;
        this.montoActual = montoActual;

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

    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }

}
