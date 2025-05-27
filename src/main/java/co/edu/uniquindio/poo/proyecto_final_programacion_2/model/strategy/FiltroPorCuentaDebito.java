package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroPorCuentaDebito implements FiltroTransaccionesStrategy {
    private CuentaDebito cuentaDebito;

    public FiltroPorCuentaDebito(CuentaDebito cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    @Override
    public List<Transaccion> filtrar(List<Transaccion> transacciones) {
        return transacciones.stream()
                .filter(t -> (t.getCuentaPropiaDebito() != null && t.getCuentaPropiaDebito().equals(cuentaDebito)) ||
                        (t.getCuentaObjetivoDebito() != null && t.getCuentaObjetivoDebito().equals(cuentaDebito)))
                .collect(Collectors.toList());
    }
}