package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorCuentaCredito implements FiltroTransaccionesStrategy {
    private CuentaCredito cuentaCredito;

    public FiltroPorCuentaCredito(CuentaCredito cuentaCredito) {
        this.cuentaCredito = cuentaCredito;
    }


    /**
     * Filtra la lista de transacciones para devolver solo aquellas que afectan
     * a la cuenta de crédito específica asociada a este filtro.
     *
     * @param transacciones Lista original de transacciones a filtrar.
     * @return Lista de transacciones que afectan a la cuenta de crédito.
     */
    @Override
    public List<Transaccion> filtrar(List<Transaccion> transacciones) {
        return transacciones.stream()
                .filter(t -> t.getCuentaCreditoAfectada() != null && t.getCuentaCreditoAfectada().equals(cuentaCredito))
                .collect(Collectors.toList());
    }
}