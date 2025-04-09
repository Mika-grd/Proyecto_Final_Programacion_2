package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GestionAdministrador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> administradoresTabla;

    @FXML
    private Button agregarAdministradorBoton;

    @FXML
    private Button buscarBoton;

    @FXML
    private TextField busquedaCampo;

    @FXML
    private TextField cedulaCampo;

    @FXML
    private TableColumn<?, ?> cedulaColumna;

    @FXML
    private TextField correoCampo;

    @FXML
    private TextField correoCampo1;

    @FXML
    private TableColumn<?, ?> correoColumna;

    @FXML
    private Button editarAdministradorBoton;

    @FXML
    private Button eliminarAdministradorBoton;

    @FXML
    private TextField nombreCampo;

    @FXML
    private TableColumn<?, ?> nombreColumna;

    @FXML
    private TableColumn<?, ?> nombreColumna1;

    @FXML
    private Button recargarBoton;

    @FXML
    private AnchorPane telefonoCampo;

    @FXML
    private Button volverBoton;

    @FXML
    void agregarAdministrador(ActionEvent event) {

    }

    @FXML
    void buscarAccion(ActionEvent event) {

    }


    @FXML
    void editarAdministrador(ActionEvent event) {

    }

    @FXML
    void eliminarAdministrador(ActionEvent event) {

    }

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {
        // Obtener la ventana actual y cerrarla
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
        }


    @FXML
    void initialize() {
        assert administradoresTabla != null : "fx:id=\"administradoresTabla\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert agregarAdministradorBoton != null : "fx:id=\"agregarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert buscarBoton != null : "fx:id=\"buscarBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert busquedaCampo != null : "fx:id=\"busquedaCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoCampo != null : "fx:id=\"correoCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoCampo1 != null : "fx:id=\"correoCampo1\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoColumna != null : "fx:id=\"correoColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert editarAdministradorBoton != null : "fx:id=\"editarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert eliminarAdministradorBoton != null : "fx:id=\"eliminarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreColumna1 != null : "fx:id=\"nombreColumna1\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert telefonoCampo != null : "fx:id=\"telefonoCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";

    }

}
