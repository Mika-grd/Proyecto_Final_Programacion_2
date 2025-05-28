package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

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
    private AnchorPane rootReportePane;

    @FXML
    private void GenerarPdf() {
        // Obtener el Stage actual a partir de cualquier nodo de la escena
        Stage stage = (Stage) btnGenerarPdf.getScene().getWindow();

        // Capturar el nodo raíz (todo lo visible en la escena)
        Node rootNode = btnGenerarPdf.getScene().getRoot();

        // Crear un diálogo para que el usuario elija dónde guardar el PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar reporte como PDF");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf")
        );

        // Mostrar el diálogo de guardar archivo
        File file = fileChooser.showSaveDialog(stage);

        // Verificar si el usuario seleccionó un archivo
        if (file != null) {
            try {
                // Tomar un snapshot de la interfaz gráfica como imagen Java AWT
                BufferedImage image = SwingFXUtils.fromFXImage(rootNode.snapshot(null, null), null);

                // Crear un nuevo documento PDF
                try (PDDocument document = new PDDocument()) {
                    // Añadir una página al documento
                    PDPage page = new PDPage();
                    document.addPage(page);

                    // Convertir la imagen a un objeto PDF compatible (sin compresión con pérdida)
                    PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);

                    // Obtener las dimensiones de la página
                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    // Calcular la escala adecuada para que la imagen encaje dentro de la página
                    float scale = Math.min(pageWidth / image.getWidth(), pageHeight / image.getHeight());
                    float imgWidth = image.getWidth() * scale;
                    float imgHeight = image.getHeight() * scale;

                    // Centrar la imagen en la página
                    float x = (pageWidth - imgWidth) / 2;
                    float y = (pageHeight - imgHeight) / 2;

                    // Dibujar la imagen en el PDF
                    contentStream.drawImage(pdImage, x, y, imgWidth, imgHeight);
                    contentStream.close();

                    // Guardar el documento en el archivo seleccionado por el usuario
                    document.save(file);

                    // Confirmación en consola
                    System.out.println("PDF guardado en: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                // Imprimir errores en consola si algo falla
                e.printStackTrace();
            }
        }
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
