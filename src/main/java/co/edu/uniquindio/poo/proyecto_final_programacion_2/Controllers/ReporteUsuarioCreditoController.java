package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReporteUsuarioCreditoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String, Number> barChartCredito;

    @FXML
    private Button btnGenerarReporteCredito;

    @FXML
    private ComboBox<String> comboRangoFechasCredito;

    @FXML
    private Label labelPromedioUso;

    @FXML
    private Label labelTotalUso;

    @FXML
    private CategoryAxis xAxisCredito;

    @FXML
    private NumberAxis yAxisCredito;

    @FXML
    private Button btnGenerarPdf;



    @FXML
    private void GenerarPdf() {
        // Lógica para generar PDF
        System.out.println("Generando PDF...");
    }



    @FXML
    void initialize() {
        assert barChartCredito != null : "fx:id=\"barChartCredito\" not injected.";
        assert btnGenerarReporteCredito != null : "fx:id=\"btnGenerarReporteCredito\" not injected.";
        assert comboRangoFechasCredito != null : "fx:id=\"comboRangoFechasCredito\" not injected.";
        assert labelPromedioUso != null : "fx:id=\"labelPromedioUso\" not injected.";
        assert labelTotalUso != null : "fx:id=\"labelTotalUso\" not injected.";
        assert xAxisCredito != null : "fx:id=\"xAxisCredito\" not injected.";
        assert yAxisCredito != null : "fx:id=\"yAxisCredito\" not injected.";

        comboRangoFechasCredito.getItems().addAll("Última semana", "Último mes", "Últimos 3 meses");

        btnGenerarReporteCredito.setOnAction(e -> generarReporteCredito());
    }

    private void generarReporteCredito() {
        // Simulación de datos
        barChartCredito.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Uso de Cupo");

        series.getData().add(new XYChart.Data<>("Uso 1", 200));
        series.getData().add(new XYChart.Data<>("Uso 2", 150));
        series.getData().add(new XYChart.Data<>("Pago", 100));

        barChartCredito.getData().add(series);

        labelPromedioUso.setText("Promedio: $150");
        labelTotalUso.setText("Total usado: $450");
    }
}
