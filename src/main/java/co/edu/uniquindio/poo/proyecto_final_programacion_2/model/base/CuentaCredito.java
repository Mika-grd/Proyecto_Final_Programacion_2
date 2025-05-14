package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class CuentaCredito extends Cuenta{

    private  double tasaInteres;
    private double cupoDisponible;
    private double cupoEnUso;

    ///Constructor de la clase CuentaCredito
    public CuentaCredito(String id, String nombreBanco, int numCuenta, Usuario usuario, double tasaInteres, double cupoDisponible, double cupoEnUso) {
        super(id, nombreBanco, numCuenta, usuario);
        this.tasaInteres = tasaInteres;
        this.cupoDisponible = cupoDisponible;
        this.cupoEnUso = cupoEnUso;
    }


    /// Getters & Setters
    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public double getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(double cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public double getCupoEnUso() {
        return cupoEnUso;
    }

    public void setCupoEnUso(double cupoEnUso) {
        this.cupoEnUso = cupoEnUso;
    }

    public String getTipoCuenta() {
        return "Crédito";
    }

}