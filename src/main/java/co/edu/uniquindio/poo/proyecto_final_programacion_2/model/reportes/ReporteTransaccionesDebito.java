package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.reportes;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy.FiltroPorCuentaDebito; // Importar la estrategia concreta
import java.util.List;

public class ReporteTransaccionesDebito extends ReporteGeneral {

    private CuentaDebito cuentaDebito; // Necesario para mostrar información específica de la cuenta en el formato

    public ReporteTransaccionesDebito(CuentaDebito cuentaDebito) {
        // Al constructor del padre (ReporteGeneral) le pasamos la estrategia de filtrado específica
        super(new FiltroPorCuentaDebito(cuentaDebito));
        this.cuentaDebito = cuentaDebito;
    }


    /**
     * Formatea un reporte detallado de las transacciones de débito para la cuenta de débito actual.
     *
     * @param transacciones Lista de transacciones de débito a incluir en el reporte.
     * @return Una cadena formateada que contiene el saldo actual de la cuenta y
     *         el detalle de cada transacción (ID, fecha, monto, cuentas involucradas y descripción).
     */
    @Override
    protected String formatearReporte(List<Transaccion> transacciones) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Reporte de Transacciones de Débito para Cuenta ").append(cuentaDebito.getNumCuenta()).append(" ---\n");
        sb.append("Saldo Actual: ").append(String.format("%.2f", cuentaDebito.getSaldo())).append("\n"); // Muestra el saldo actual

        if (transacciones.isEmpty()) {
            sb.append("No hay transacciones registradas para esta cuenta de débito.\n");
        } else {
            for (Transaccion t : transacciones) {
                sb.append("ID: ").append(t.getId())
                        .append(", Fecha: ").append(t.getFechaTransaccion())
                        .append(", Monto: ").append(String.format("%.2f", t.getMontoATransferir()));
                // Agrega información de cuentas de débito si es una transferencia
                if (t.getCuentaPropiaDebito() != null && t.getCuentaObjetivoDebito() != null) {
                    sb.append(", De: ").append(t.getCuentaPropiaDebito().getNumCuenta())
                            .append(" -> Para: ").append(t.getCuentaObjetivoDebito().getNumCuenta());
                } else if (t.getCuentaPropiaDebito() != null) {
                    sb.append(", Cuenta: ").append(t.getCuentaPropiaDebito().getNumCuenta());
                }
                sb.append(", Descripción: ").append(t.getDescripcion())
                        .append("\n");
            }
        }
        sb.append("---------------------------------------------------\n");
        return sb.toString();
    }
}