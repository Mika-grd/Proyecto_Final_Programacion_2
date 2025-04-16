package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class inicioSesionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button iniciarSesionBoton;

    @FXML
    private Hyperlink olvideContraseniaLink;

    @FXML
    void iniciarSesionAccion(ActionEvent event) {

    }

    @FXML
    void olvidoContraseniaAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert iniciarSesionBoton != null : "fx:id=\"iniciarSesionBoton\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert olvideContraseniaLink != null : "fx:id=\"olvideContraseniaLink\" was not injected: check your FXML file 'InicioSesion.fxml'.";

    }

}