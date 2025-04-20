package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class CuentaDebito extends Cuenta {

    private double saldo;

    /// Constructor
    public CuentaDebito(String id, String nombreBanco, int numCuenta, Usuario usuario, double saldo) {
        super(id, nombreBanco, numCuenta, usuario);
        this.saldo = saldo;
    }


    /// Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return "Débito";
    }
}
