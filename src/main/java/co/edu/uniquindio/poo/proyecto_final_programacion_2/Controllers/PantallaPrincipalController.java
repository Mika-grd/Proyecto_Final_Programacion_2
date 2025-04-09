package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaPrincipalController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="administradoresBoton"
    private Button administradoresBoton; // Value injected by FXMLLoader

    @FXML // fx:id="cuentasBoton"
    private Button cuentasBoton; // Value injected by FXMLLoader

    @FXML // fx:id="usuariosBoton"
    private Button usuariosBoton; // Value injected by FXMLLoader

    @FXML
    void abrirAdministradores(ActionEvent event) {

    }

    @FXML
    void abrirCuentas(ActionEvent event) {

    }

    @FXML
    void abrirUsuarios(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert administradoresBoton != null : "fx:id=\"administradoresBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
        assert cuentasBoton != null : "fx:id=\"cuentasBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
        assert usuariosBoton != null : "fx:id=\"usuariosBoton\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";

    }
}
