package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuentaCredito extends Cuenta {

    private double tasaInteres;
    private double cupoDisponible;
    private double cupoEnUso;
    private double cupoTotalInicial; // Representa el límite de crédito general de la tarjeta
    private double deudaTotal;

    private final List<Transaccion> movimientosCredito;
    private boolean cupoAmpliadoUnaVez;

    /// Constructor de la clase CuentaCredito (SE MANTIENE IGUAL)
    public CuentaCredito(String id, String nombreBanco, int numCuenta, Usuario usuario, double tasaInteres, double cupoDisponible, double cupoEnUso) {
        super(id, nombreBanco, numCuenta, usuario);
        this.tasaInteres = (tasaInteres/ 100);
        this.cupoDisponible = cupoDisponible;
        this.cupoEnUso = cupoEnUso;
        this.cupoTotalInicial = cupoDisponible + cupoEnUso; // Calcula el cupo total inicial
        this.movimientosCredito = super.getListaTransaccion();
        this.cupoAmpliadoUnaVez = false;
    }

    /**
     * Permite utilizar una parte del cupo de crédito.
     * La lógica de modificación del saldo ahora está en el constructor de Transaccion.
     * @param monto El monto a utilizar.
     * @param descripcion La descripción de la transacción.
     * @return true si la transacción se pudo crear y registrar, false si no.
     */
    public boolean usarCupo(double monto, String descripcion) {
        // La validación de cupo disponible ocurre dentro del constructor de Transaccion
        // y su método aplicarEfectoEnCuentaCredito().
        // Si quieres que el método retorne false sin crear la transacción si no hay cupo,
        // puedes añadir una validación aquí también, pero sería redundante con la de Transaccion.
        if (monto <= 0) {
            System.out.println("El monto a usar debe ser un valor positivo.");
            return false;
        }
        if (monto > cupoDisponible) { // Validación preventiva antes de crear la transacción
            System.out.println("No hay suficiente cupo disponible. Cupo disponible: " + cupoDisponible);
            return false;
        }

        // Crea la transacción, y la transacción misma modifica el estado de 'this' cuenta.
        Transaccion nuevaTransaccion = new Transaccion(generarNuevoIdTransaccion(), monto, descripcion, this, Transaccion.TipoTransaccionCredito.USO_CUPO);
        movimientosCredito.add(nuevaTransaccion);
        setDeudaTotal(deudaTotalCalculo(monto));

        System.out.println("Solicitud de uso de cupo procesada. Revise los logs de la Transacción para el resultado.");
        System.out.println("Nuevo cupo disponible (después de intento): " + cupoDisponible + ", Cupo en uso: " + cupoEnUso);
        return true; // Asume que la transacción se creó, el efecto se registra en los logs de Transaccion.
    }

    /**
     * Permite realizar un pago a la cuenta de crédito.
     * La lógica de modificación del saldo ahora está en el constructor de Transaccion.
     * @param monto El monto a pagar.
     * @return true si la transacción se pudo crear y registrar, false si no.
     */
    public boolean realizarPago(double monto) {
        if (monto <= 0) {
            System.out.println("El monto del pago debe ser un valor positivo.");
            return false;
        }
        if (cupoEnUso <= 0.001) { // Si ya no hay deuda, no tiene sentido pagar
            System.out.println("No hay monto pendiente para pagar.");
            return false;
        }

        // Crea la transacción, y la transacción misma modifica el estado de 'this' cuenta.
        Transaccion nuevaTransaccion = new Transaccion(generarNuevoIdTransaccion(), monto, "Pago de Crédito", this, Transaccion.TipoTransaccionCredito.PAGO_CREDITO);
        movimientosCredito.add(nuevaTransaccion);

        System.out.println("Solicitud de pago de crédito procesada. Revise los logs de la Transacción para el resultado.");
        System.out.println("Nuevo cupo disponible (después de intento): " + cupoDisponible + ", Cupo en uso: " + cupoEnUso);
        return true; // Asume que la transacción se creó, el efecto se registra en los logs de Transaccion.
    }

    /**
     * Retorna el monto actual que se ha utilizado del cupo de crédito y que está pendiente de pago.
     * @return El monto pendiente de pago.
     */
    private double deudaTotalCalculo(double monto) {
        return (monto * tasaInteres);
    }

    /**
     * Verifica si la totalidad del cupo en uso ha sido pagada.
     * @return true si el cupo en uso es cero (o muy cercano a cero), false de lo contrario.
     */
    public boolean estaTodoPagado() {
        return cupoEnUso <= 0.001;
    }

    /**
     * Permite ampliar el cupo total de la tarjeta de crédito.
     * @param montoAmpliacion El monto en que se desea ampliar el cupo.
     * @return true si la ampliación fue exitosa, false si no se cumplen las condiciones.
     */
    public boolean ampliarCupo(double montoAmpliacion) {
        if (montoAmpliacion <= 0) {
            System.out.println("El monto de ampliación debe ser positivo.");
            return false;
        }

        if (estaTodoPagado() && !cupoAmpliadoUnaVez) {
            this.cupoTotalInicial += montoAmpliacion;
            this.cupoDisponible += montoAmpliacion;
            cupoAmpliadoUnaVez = true;

            System.out.println("Cupo ampliado exitosamente. Nuevo cupo total: " + this.cupoTotalInicial);
            return true;
        } else if (cupoAmpliadoUnaVez) {
            System.out.println("El cupo ya ha sido ampliado anteriormente bajo el criterio de 'todo pago'.");
            return false;
        } else {
            System.out.println("No se puede ampliar el cupo: aún hay monto pendiente de pago.");
            return false;
        }
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
        // Asegúrate de que cupoDisponible no exceda el cupo total actual
        if (cupoDisponible < 0) this.cupoDisponible = 0;
        else this.cupoDisponible = cupoDisponible;

        // Ajusta cupoEnUso para mantener la consistencia
        this.cupoEnUso = getCupoTotal() - this.cupoDisponible;
        if (cupoEnUso <0) {
            cupoEnUso = 0;
        }
    }

    public double getCupoEnUso() {
        return cupoEnUso;
    }

    public void setCupoEnUso(double cupoEnUso) {
        // Asegúrate de que cupoEnUso no sea negativo o exceda el cupo total actual
        if (cupoEnUso < 0) this.cupoEnUso = 0;
        else if (cupoEnUso > getCupoTotal()) this.cupoEnUso = getCupoTotal();
        else this.cupoEnUso = cupoEnUso;
    }

    public double getCupoTotal() {
        return cupoTotalInicial;
    }

    public String getTipoCuenta() {
        return "Crédito";
    }

    public List<Transaccion> getMovimientosCredito() {
        return Collections.unmodifiableList(movimientosCredito);
    }

    public boolean isCupoAmpliadoUnaVez() {
        return cupoAmpliadoUnaVez;
    }

    public void setCupoAmpliadoUnaVez(boolean cupoAmpliadoUnaVez) {
        this.cupoAmpliadoUnaVez = cupoAmpliadoUnaVez;
    }

    private String generarNuevoIdTransaccion() {
        return "CRED-" + System.nanoTime(); // Usar nanoTime para mayor unicidad
    }


    public void setCupoTotalInicial(double cupoTotalInicial) {
        this.cupoTotalInicial = cupoTotalInicial;
    }

    public double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }
}