package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
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

    /**
     * Muestra una alerta de tipo WARNING con el mensaje especificado.
     * Se usa para notificar al usuario sobre errores o validaciones fallidas.
     *
     * @param mensaje Texto que se mostrará en la alerta.
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void generarReporteDebito() {
        // Limpia el gráfico
        pieChartDebito.getData().clear();

        // Obtener el rango de fechas desde el ComboBox
        String seleccion = comboRangoFechasDebito.getSelectionModel().getSelectedItem();
        LocalDate fechaMinima;

        if (seleccion == null) {
            mostrarAlerta("Seleccione un rango de fechas", Alert.AlertType.WARNING);
            return;
        }

        switch (seleccion) {
            case "Última semana":
                fechaMinima = LocalDate.now().minusWeeks(1);
                break;
            case "Último mes":
                fechaMinima = LocalDate.now().minusMonths(1);
                break;
            case "Últimos 3 meses":
                fechaMinima = LocalDate.now().minusMonths(3);
                break;
            default:
                fechaMinima = LocalDate.MIN; // En caso de error, sin filtro
                break;
        }

        Usuario usuario = Sesion.getInstancia().getUsuario();

        double totalEntradas = 0;
        double totalSalidas = 0;
        int cantidadEntradas = 0;
        int cantidadSalidas = 0;

        // Recorre las cuentas del usuario

        CuentaDebito cuenta = (CuentaDebito) Sesion.getInstancia().getCuentaSeleccionada();
                // Recorre las transacciones asociadas a la cuenta
                for (Transaccion transaccion : cuenta.getListaTransaccion()) {
                    // Verifica la fecha
                    LocalDate fechaTransaccion = transaccion.getFechaTransaccion();
                    if (fechaTransaccion.isBefore(fechaMinima)) {
                        continue; // Ignorar transacciones fuera del rango
                    }

                    System.out.println("Transacción fecha: " + transaccion.getFechaTransaccion());
                    Cuenta cuentaOrigen = transaccion.getCuentaPropiaDebito();
                    Cuenta cuentaDestino = transaccion.getCuentaObjetivoDebito();
                    double monto = transaccion.getMontoATransferir();

                    if (cuenta.equals(cuentaDestino)) {
                        totalEntradas += monto;
                        cantidadEntradas++;
                    } else if (cuenta.equals(cuentaOrigen)) {
                        totalSalidas += monto;
                        cantidadSalidas++;
                    }
                }



        // Calcular promedios
        double promedioEntrada = cantidadEntradas > 0 ? totalEntradas / cantidadEntradas : 0;
        double promedioSalida = cantidadSalidas > 0 ? totalSalidas / cantidadSalidas : 0;

        // Agregar datos al PieChart
        PieChart.Data entradaData = new PieChart.Data("Entradas", totalEntradas);
        PieChart.Data salidaData = new PieChart.Data("Salidas", totalSalidas);
        pieChartDebito.getData().addAll(entradaData, salidaData);

        // Actualizar etiquetas
        labelPromedioEntrada.setText(String.format("Promedio entrada: $%.2f", promedioEntrada));
        labelPromedioSalida.setText(String.format("Promedio salida: $%.2f", promedioSalida));
    }

}
