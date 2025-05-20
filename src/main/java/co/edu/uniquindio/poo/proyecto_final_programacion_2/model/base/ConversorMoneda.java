package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;


///  Adapter
public class ConversorMoneda {

    // Tasa de cambio
    private static final double TASA_DOLAR_A_PESOS = 4100.0;


    /**
     * Convierte de dolares a Pesos
     * @param montoDolares monto en dolares
     * @return monto en pesos
     */
    public double convertirDolarAPesos(double montoDolares) {
        return montoDolares * TASA_DOLAR_A_PESOS;
    }


    /**
     * Convierte de pesos a dolares
     * @param montoPesos monto en pesos
     * @return monto en dolares
     */
    public double convertirPesos(double montoPesos) {
        return montoPesos / TASA_DOLAR_A_PESOS;
    }

}