package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import java.util.List;

public interface FiltroTransaccionesStrategy {
    List<Transaccion> filtrar(List<Transaccion> transacciones);
}