package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionUsuarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="atrasBoton"
    private Button atrasBoton; // Value injected by FXMLLoader

    @FXML // fx:id="añadirUsuarioBoton"
    private Button añadirUsuarioBoton; // Value injected by FXMLLoader

    @FXML // fx:id="cedulaCampo"
    private TextField cedulaCampo; // Value injected by FXMLLoader

    @FXML // fx:id="cedulaColumna"
    private TableColumn<?, ?> cedulaColumna; // Value injected by FXMLLoader

    @FXML // fx:id="editarUsuarioBoton"
    private Button editarUsuarioBoton; // Value injected by FXMLLoader

    @FXML // fx:id="eliminarUsuarioBoton"
    private Button eliminarUsuarioBoton; // Value injected by FXMLLoader

    @FXML // fx:id="nombreCampo"
    private TextField nombreCampo; // Value injected by FXMLLoader

    @FXML // fx:id="nombreColumna"
    private TableColumn<?, ?> nombreColumna; // Value injected by FXMLLoader

    @FXML // fx:id="telefonoCampo"
    private TextField telefonoCampo; // Value injected by FXMLLoader

    @FXML // fx:id="telefonoColumna"
    private TableColumn<?, ?> telefonoColumna; // Value injected by FXMLLoader

    @FXML // fx:id="usuariosTabla"
    private TableView<?> usuariosTabla; // Value injected by FXMLLoader

    @FXML
    void atrasAccion(ActionEvent event) {

    }

    @FXML
    void añadirUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void editarUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void eliminarUsuarioAccion(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert añadirUsuarioBoton != null : "fx:id=\"añadirUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert editarUsuarioBoton != null : "fx:id=\"editarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert eliminarUsuarioBoton != null : "fx:id=\"eliminarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoCampo != null : "fx:id=\"telefonoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert usuariosTabla != null : "fx:id=\"usuariosTabla\" was not injected: check your FXML file 'GestionUsuario.fxml'.";

    }

}

