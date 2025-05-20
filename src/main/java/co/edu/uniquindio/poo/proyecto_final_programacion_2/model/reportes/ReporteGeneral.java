package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.reportes;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.strategy.FiltroTransaccionesStrategy; // Importar la interfaz Strategy
import java.util.List;

public abstract class ReporteGeneral {

    // La estrategia de filtrado se inyecta en el constructor
    protected FiltroTransaccionesStrategy filtroStrategy;

    public ReporteGeneral(FiltroTransaccionesStrategy filtroStrategy) {
        this.filtroStrategy = filtroStrategy;
    }

    /**
     * Método plantilla que define el algoritmo general para generar un reporte.
     * Este método es final para asegurar que el orden de los pasos no se altere.
     * @param todasLasTransacciones Una lista global de todas las transacciones para filtrar.
     * @return Una cadena de texto que representa el reporte formateado.
     */
    public final String generarReporte(List<Transaccion> todasLasTransacciones) {
        // Paso 1: Filtrar transacciones usando la estrategia inyectada
        List<Transaccion> transaccionesFiltradas = filtroStrategy.filtrar(todasLasTransacciones);

        // Paso 2: Ordenar transacciones (es un "hook" method, puede ser sobrescrito)
        ordenarTransacciones(transaccionesFiltradas);

        // Paso 3: Formatear el reporte (método abstracto, debe ser implementado por las subclases)
        return formatearReporte(transaccionesFiltradas);
    }

    /**
     * Hook Method: Proporciona un ordenamiento por defecto, pero las subclases pueden sobrescribirlo.
     * Por defecto, ordena las transacciones por fecha.
     * @param transacciones La lista de transacciones a ordenar.
     */
    protected void ordenarTransacciones(List<Transaccion> transacciones) {
        // Ordena las transacciones por fecha de forma ascendente
        transacciones.sort((t1, t2) -> t1.getFechaTransaccion().compareTo(t2.getFechaTransaccion()));
    }

    /**
     * Método Abstracto: Debe ser implementado por las subclases para definir
     * cómo se presenta la información del reporte.
     * @param transacciones Las transacciones ya filtradas y ordenadas.
     * @return La cadena de texto del reporte formateado.
     */
    protected abstract String formatearReporte(List<Transaccion> transacciones);
}