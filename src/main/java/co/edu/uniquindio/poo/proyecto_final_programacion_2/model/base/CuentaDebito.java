package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class CuentaDebito extends Cuenta {

    private double saldo;
    private double saldoTotal =0;

    /**
     * Constructor de la clase CuentaDebito
     * @param id
     * @param nombreBanco
     * @param numCuenta
     * @param usuario
     */
    public CuentaDebito(String id, String nombreBanco, int numCuenta, Usuario usuario, double saldo) {
        super(id, nombreBanco, numCuenta, usuario);
        this.saldo = saldo;
        calcularSaldoTotal();
    }

    /**
     * Metodo para calcular el saldo total de la cuenta, pasa por todas las categorias
     */
    public void calcularSaldoTotal() {
        saldoTotal = 0;
        for (Categoria categoria : this.getListaCategorias()) {
            saldoTotal += categoria.getPresupuesto().getMontoActual();
        }
        saldoTotal += saldo;
    }


    /**
     * Metodo para agregar saldo a la cuenta
     * @param monto
     */
    public void agregarSaldo(double monto) {
        this.saldo += monto;
        calcularSaldoTotal();
    }

    /**
     * Metodo para retirar saldo de la cuenta
     * @param monto
     * @return "Exitoso" si la operación fue realizada, de lo contrario "No exitoso".
     */
    public String retirarSaldo(double monto) {
        String mensaje = "Exitoso";
        if (monto <= saldo) {
            this.saldo -= monto;
            calcularSaldoTotal();
        } else {
            mensaje = "No hay suficiente saldo disponible.";
        }
        return mensaje;
    }


    /**
     * Metodo para depositar saldo a una categoria
     * @param categoria
     * @param monto
     * @return "Exitoso" si la operación fue realizada, de lo contrario "No exitoso". Revisa si la categoria existe en la lista de categorias de laa cuenta
     */
    public String depositoACategoria(Categoria categoria, double monto) {
        String mensaje = "Exitoso";

        if (getListaCategorias().contains(categoria)) {
            if (monto <= saldo) {
                this.saldo -= monto;
                categoria.getPresupuesto().setMontoActual(monto);
                calcularSaldoTotal();
            } else {
                mensaje = "No hay suficiente saldo disponible.";
            }
        }
        else {
            mensaje = "La categoria no existe.";
        }

        return mensaje;

    }

    /// Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getTipoCuenta() {
        return "Débito";
    }
}
