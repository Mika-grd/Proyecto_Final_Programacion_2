package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaccion implements Cloneable{

    private String id;
    private LocalDate fechaTransaccion;
    private double montoATransferir;
    private String descripcion;

    // Para transacciones de débito (retiros/depósitos, transferencias entre cuentas)
    private CuentaDebito cuentaPropiaDebito;
    private CuentaDebito cuentaObjetivoDebito;

    // Para transacciones de crédito (usos de cupo, pagos a la tarjeta)
    private CuentaCredito cuentaCreditoAfectada; // La cuenta de crédito que se modifica
    private TipoTransaccionCredito tipoTransaccionCredito; // Nuevo: Para diferenciar usos de cupo y pagos

    // Constructor existente (asumimos para transferencias/débitos)
    public Transaccion(String id, LocalDate fechaTransaccion, double montoATransferir, String descripcion, CuentaDebito cuentaPropia, CuentaDebito cuentaObjetivo) {
        this.id = id;
        this.fechaTransaccion = fechaTransaccion;
        this.montoATransferir = montoATransferir;
        this.descripcion = descripcion;
        this.cuentaPropiaDebito = cuentaPropia;
        this.cuentaObjetivoDebito = cuentaObjetivo;
        // La lógica de saldoResultante podría estar en la CuentaDebito o en el servicio
        // Aquí no se modifica el estado de la cuenta, solo se registran los datos
    }

    // --- Constructor para Transacciones de Crédito (AJUSTADO) ---
    public Transaccion(String id, double monto, String descripcion, CuentaCredito cuentaCreditoAfectada, TipoTransaccionCredito tipoTransaccionCredito) {
        this.id = id;
        this.fechaTransaccion = LocalDate.now();
        this.montoATransferir = monto;
        this.descripcion = descripcion;
        this.cuentaCreditoAfectada = cuentaCreditoAfectada;
        this.tipoTransaccionCredito = tipoTransaccionCredito;

        // ¡Aquí es donde la magia sucede!
        // La transacción misma modifica el estado de la cuenta de crédito al ser creada.
        aplicarEfectoEnCuentaCredito();
    }


    /// Constructor para Deposito y Retiro
    public Transaccion(String id, LocalDate fecha, double montoATransferir, String descripcion) {
        this.id = id;
        this.fechaTransaccion = fecha;
        this.montoATransferir = montoATransferir;
        this.descripcion = descripcion;
    }

    @Override
    public Transaccion clone() {
        Transaccion copia = new Transaccion(
                this.id,
                this.fechaTransaccion,
                this.montoATransferir,
                this.descripcion,
                this.cuentaPropiaDebito,
                this.cuentaObjetivoDebito
        );
        return copia;
    }


    // --- Enum para Tipos de Transacción de Crédito (Nuevo) ---
    public enum TipoTransaccionCredito {
        USO_CUPO,
        PAGO_CREDITO
    }

    /**
     * Aplica el efecto de esta transacción de crédito en la cuenta de crédito asociada.
     * Este metodo se llama automáticamente desde el constructor de crédito.
     */
    private void aplicarEfectoEnCuentaCredito() {
        if (cuentaCreditoAfectada == null) {
            System.err.println("Error: No se puede aplicar efecto de transacción de crédito sin una cuenta asociada.");
            return;
        }

        switch (tipoTransaccionCredito) {
            case USO_CUPO:
                // Lógica de usarCupo trasladada aquí
                if (montoATransferir <= 0) {
                    System.err.println("El monto a usar debe ser un valor positivo.");
                    return; // No lanza excepción, simplemente no aplica y sale
                }
                if (montoATransferir <= cuentaCreditoAfectada.getCupoDisponible()) {
                    System.out.println(cuentaCreditoAfectada.getCupoDisponible() + "   "  + cuentaCreditoAfectada.getCupoEnUso());
                    cuentaCreditoAfectada.setCupoDisponible(cuentaCreditoAfectada.getCupoDisponible() - montoATransferir);
                    cuentaCreditoAfectada.setCupoEnUso(cuentaCreditoAfectada.getCupoEnUso() + montoATransferir);
                    System.out.println(cuentaCreditoAfectada.getCupoDisponible() + "   "  + cuentaCreditoAfectada.getCupoEnUso());
                } else {
                    System.err.println("Error: No hay suficiente cupo disponible para la transacción de " + montoATransferir);
                }
                break;
            case PAGO_CREDITO:
                // Lógica de realizarPago trasladada aquí
                if (montoATransferir <= 0) {
                    System.err.println("El monto del pago debe ser un valor positivo.");
                    return;
                }
                double montoRealAPagar = montoATransferir;
                if (montoATransferir > cuentaCreditoAfectada.getCupoEnUso()) {
                    montoRealAPagar = cuentaCreditoAfectada.getCupoEnUso();
                    System.out.println("El pago excede el monto en uso. Se pagará el total pendiente: " + montoRealAPagar);
                }

                cuentaCreditoAfectada.setCupoEnUso(cuentaCreditoAfectada.getCupoEnUso() - montoRealAPagar);
                cuentaCreditoAfectada.setCupoDisponible(cuentaCreditoAfectada.getCupoDisponible() + montoRealAPagar);

                // Asegurar que cupoEnUso no sea negativo debido a la precisión de double
                if (cuentaCreditoAfectada.getCupoEnUso() < 0.001) {
                    cuentaCreditoAfectada.setCupoEnUso(0.0);
                }
                // Asegurar que cupoDisponible no exceda el cupo total actual
                if (cuentaCreditoAfectada.getCupoDisponible() > cuentaCreditoAfectada.getCupoTotal()) {
                    cuentaCreditoAfectada.setCupoDisponible(cuentaCreditoAfectada.getCupoTotal());
                }
                System.out.println("Transacción de pago de crédito aplicada. Monto: " + montoATransferir);
                break;
            default:
                System.err.println("Tipo de transacción de crédito desconocido: " + tipoTransaccionCredito);
                break;
        }
    }

    /**
     * Realiza una transacción de transferencia entre dos cuentas.
     *
     * Verifica que todos los datos necesarios estén definidos correctamente:
     * - La cuenta objetivo no debe ser nula.
     * - La fecha de transacción debe estar definida.
     * - El monto a transferir debe ser mayor a 0.
     * - El monto disponible debe ser mayor a 0.
     *
     * Si todos los valores son válidos y el monto a transferir es menor o igual al monto disponible,
     * se realiza la transferencia: se descuenta el monto de la cuenta propia y se suma a la cuenta objetivo.
     *
     * @return true si la transacción se realizó exitosamente, false en caso contrario.
     */
    public boolean realizarTransaccion() {
        double montoDisponible = cuentaPropiaDebito.getSaldo();

        if (cuentaObjetivoDebito != null && fechaTransaccion != null && montoATransferir > 0 && montoDisponible > 0) {
            if (montoATransferir <= montoDisponible) {

                // Ajustar saldos
                cuentaPropiaDebito.setSaldo(cuentaPropiaDebito.getSaldo() - montoATransferir);
                cuentaPropiaDebito.calcularSaldoTotal();

                cuentaObjetivoDebito.setSaldo(cuentaObjetivoDebito.getSaldo() + montoATransferir);
                cuentaObjetivoDebito.calcularSaldoTotal();

                // Crear transacciones separadas para cada cuenta
                Transaccion transaccionParaOrigen = new Transaccion(
                        this.id,
                        this.fechaTransaccion,
                        this.montoATransferir,
                        this.descripcion + " (Enviada a: " + cuentaObjetivoDebito.getId() + ")",
                        this.cuentaPropiaDebito,
                        this.cuentaObjetivoDebito
                );

                Transaccion transaccionParaDestino = new Transaccion(
                        this.id,
                        this.fechaTransaccion,
                        this.montoATransferir,
                        this.descripcion + " (Recibida de: " + cuentaPropiaDebito.getId() + ")",
                        this.cuentaPropiaDebito,
                        this.cuentaObjetivoDebito
                );

                // Registrar transacciones en ambas cuentas
                cuentaPropiaDebito.getListaTransaccion().add(transaccionParaOrigen);
                cuentaObjetivoDebito.getListaTransaccion().add(transaccionParaDestino);

                System.out.println("Transacción realizada:");
                System.out.println(" - Origen: " + transaccionParaOrigen.getFechaTransaccion());
                System.out.println(" - Destino: " + transaccionParaDestino.getFechaTransaccion());

                return true;
            }
        }

        return false;
    }


    // --- Getters y Setters existentes (mantenerlos) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(LocalDate fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }
    public double getMontoATransferir() { return montoATransferir; }
    public void setMontoATransferir(double montoATransferir) { this.montoATransferir = montoATransferir; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public CuentaDebito getCuentaPropiaDebito() { return cuentaPropiaDebito; }
    public void setCuentaPropiaDebito(CuentaDebito cuentaPropiaDebito) { this.cuentaPropiaDebito = cuentaPropiaDebito; }
    public CuentaDebito getCuentaObjetivoDebito() { return cuentaObjetivoDebito; }
    public void setCuentaObjetivoDebito(CuentaDebito cuentaObjetivoDebito) { this.cuentaObjetivoDebito = cuentaObjetivoDebito; }

    public CuentaCredito getCuentaCreditoAfectada() { return cuentaCreditoAfectada; }
    public void setCuentaCreditoAfectada(CuentaCredito cuentaCreditoAfectada) { this.cuentaCreditoAfectada = cuentaCreditoAfectada; }
    public TipoTransaccionCredito getTipoTransaccionCredito() { return tipoTransaccionCredito; }
    public void setTipoTransaccionCredito(TipoTransaccionCredito tipoTransaccionCredito) { this.tipoTransaccionCredito = tipoTransaccionCredito; }



}

