package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

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
    private AnchorPane rootReportePane;



    @FXML
    private void GenerarPdf() {
        // Obtener el Stage actual a partir de cualquier nodo de la escena
        Stage stage = (Stage) btnGenerarPdf.getScene().getWindow();

        // Capturar el nodo raíz (en este caso todo lo visible en la escena)
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
