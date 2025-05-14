package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class CuentaDTO {
    private final String id;
    private final String nombreUsuario;
    private final int numeroCuenta;
    private final String banco;
    private String tipoCuenta = "";

    ///Contructor de la clase CuentaDTO
    public CuentaDTO(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.nombreUsuario = cuenta.getUsuario().getNombre();
        this.numeroCuenta = cuenta.getNumCuenta();
        this.banco = cuenta.getNombreBanco();
        if (cuenta instanceof CuentaDebito) {
            tipoCuenta = "debito";
        }
        else if (cuenta instanceof CuentaCredito) {
            tipoCuenta = "credito";
        }
    }


    /// SETTERS & GETTERS
    public String getId() {
        return id;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    @Override
    public String toString() {
        return
                "nombreUsuario: " + nombreUsuario + '\'' +
                ", numeroCuenta: " + numeroCuenta + '\'' +
                ", tipo de cuenta: " + tipoCuenta + '\'' +
                ", banco: " + banco + '\'' +
                ", id: " + id + '\'' ;
    }
}
