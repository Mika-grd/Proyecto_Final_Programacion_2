package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder;

public interface ICuentaCreditoBuilder extends  CuentasBuilder {

    void setIntereses(double intereses);
    void setCupoDisponible(double cupoDisponible);
    void setCupoEnUso(double cupoEnUso);
}
