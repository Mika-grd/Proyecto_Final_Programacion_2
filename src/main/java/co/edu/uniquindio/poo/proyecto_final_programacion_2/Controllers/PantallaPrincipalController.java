package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PantallaPrincipalController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button usuariosBoton, cuentasBoton, administradoresBoton;

    @FXML
    private void abrirAdministradores() {
        // Obtener ventana (Stage)
        Stage stage = (Stage) rootPane.getScene().getWindow();

        // Capturar el nodo raíz de la escena (toda la ventana visible)
        Node rootNode = rootPane.getScene().getRoot();

        // Abrir diálogo para guardar el PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar pantalla como PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                // Tomar snapshot como imagen
                BufferedImage image = SwingFXUtils.fromFXImage(rootNode.snapshot(null, null), null);

                // Crear PDF con la imagen
                try (PDDocument document = new PDDocument()) {
                    PDPage page = new PDPage();
                    document.addPage(page);

                    PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);

                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    // Escalar imagen para que quepa
                    float scale = Math.min(pageWidth / image.getWidth(), pageHeight / image.getHeight());
                    float imgWidth = image.getWidth() * scale;
                    float imgHeight = image.getHeight() * scale;
                    float x = (pageWidth - imgWidth) / 2;
                    float y = (pageHeight - imgHeight) / 2;

                    contentStream.drawImage(pdImage, x, y, imgWidth, imgHeight);
                    contentStream.close();

                    document.save(file);
                    System.out.println("PDF guardado en: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void abrirUsuarios() {
        // Aquí puedes agregar la lógica de abrir usuarios si la tienes
    }

    @FXML
    private void abrirCuentas() {
        // Aquí puedes agregar la lógica de abrir cuentas si la tienes
    }



@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert administradoresBoton != null : "fx:id=\"administradoresBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
        assert cuentasBoton != null : "fx:id=\"cuentasBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
        assert usuariosBoton != null : "fx:id=\"usuariosBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";

    }
}
