package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.reportes;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy.FiltroPorCuentaCredito; // Importar la estrategia concreta
import java.util.List;

public class ReporteTransaccionesCredito extends ReporteGeneral {

    private CuentaCredito cuentaCredito; // Necesario para mostrar información específica de la cuenta

    public ReporteTransaccionesCredito(CuentaCredito cuentaCredito) {
        // Al constructor del padre (ReporteGeneral) le pasamos la estrategia de filtrado específica
        super(new FiltroPorCuentaCredito(cuentaCredito));
        this.cuentaCredito = cuentaCredito;
    }

    @Override
    protected String formatearReporte(List<Transaccion> transacciones) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Reporte de Transacciones de Crédito para Cuenta ").append(cuentaCredito.getNumCuenta()).append(" ---\n");
        sb.append("Cupo Total: ").append(String.format("%.2f", cuentaCredito.getCupoTotal()))
                .append(", Cupo Disponible: ").append(String.format("%.2f", cuentaCredito.getCupoDisponible()))
                .append(", Cupo en Uso: ").append(String.format("%.2f", cuentaCredito.getCupoEnUso()))
                .append("\n");

        if (transacciones.isEmpty()) {
            sb.append("No hay transacciones de crédito registradas para esta cuenta.\n");
        } else {
            for (Transaccion t : transacciones) {
                sb.append("ID: ").append(t.getId())
                        .append(", Fecha: ").append(t.getFechaTransaccion())
                        .append(", Tipo: ").append(t.getTipoTransaccionCredito() != null ? t.getTipoTransaccionCredito().name() : "N/A") // Muestra el tipo de transacción de crédito
                        .append(", Monto: ").append(String.format("%.2f", t.getMontoATransferir()))
                        .append(", Descripción: ").append(t.getDescripcion())
                        .append("\n");
            }
        }
        sb.append("-----------------------------------------------------\n");
        return sb.toString();
    }
}