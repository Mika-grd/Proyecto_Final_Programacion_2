package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReporteUsuarioDebitoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerarReporteDebito;

    @FXML
    private ComboBox<String> comboRangoFechasDebito;

    @FXML
    private Label labelPromedioEntrada;

    @FXML
    private Label labelPromedioSalida;

    @FXML
    private PieChart pieChartDebito;

    @FXML
    private Button btnGenerarPdf;



    @FXML
    private void GenerarPdf() {
        // Lógica para generar PDF
        System.out.println("Generando PDF...");
    }



    @FXML
    void initialize() {
        assert btnGenerarReporteDebito != null : "fx:id=\"btnGenerarReporteDebito\" not injected.";
        assert comboRangoFechasDebito != null : "fx:id=\"comboRangoFechasDebito\" not injected.";
        assert labelPromedioEntrada != null : "fx:id=\"labelPromedioEntrada\" not injected.";
        assert labelPromedioSalida != null : "fx:id=\"labelPromedioSalida\" not injected.";
        assert pieChartDebito != null : "fx:id=\"pieChartDebito\" not injected.";

        comboRangoFechasDebito.getItems().addAll("Última semana", "Último mes", "Últimos 3 meses");

        btnGenerarReporteDebito.setOnAction(e -> generarReporteDebito());
    }

    private void generarReporteDebito() {
        // Simulación de datos
        pieChartDebito.getData().clear();

        PieChart.Data entrada = new PieChart.Data("Entradas", 60);
        PieChart.Data salida = new PieChart.Data("Salidas", 40);

        pieChartDebito.getData().addAll(entrada, salida);

        labelPromedioEntrada.setText("Promedio entrada: $250");
        labelPromedioSalida.setText("Promedio salida: $180");
    }
}
