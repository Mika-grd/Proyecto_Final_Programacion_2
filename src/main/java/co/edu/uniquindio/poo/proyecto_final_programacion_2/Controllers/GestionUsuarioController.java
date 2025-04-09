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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button atrasBoton;

    @FXML
    private Button añadirUsuarioBoton;

    @FXML
    private Button buscarBoton;

    @FXML
    private TextField busquedaCampo;

    @FXML
    private TextField cedulaCampo;

    @FXML
    private TableColumn<?, ?> cedulaColumna;

    @FXML
    private TextField contraseñaCampo;

    @FXML
    private TextField correoCampo;

    @FXML
    private Button editarUsuarioBoton;

    @FXML
    private Button eliminarUsuarioBoton;

    @FXML
    private TextField nombreCampo;

    @FXML
    private TableColumn<?, ?> nombreColumna;

    @FXML
    private TableColumn<?, ?> nombreColumna1;

    @FXML
    private Button recargarBoton;

    @FXML
    private TextField telefonoCampo;

    @FXML
    private TableColumn<?, ?> telefonoColumna;

    @FXML
    private TableView<?> usuariosTabla;

    @FXML
    void atrasAccion(ActionEvent event) {

    }

    @FXML
    void añadirUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void buscarAccion(ActionEvent event) {

    }

    @FXML
    void editarUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void eliminarUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert añadirUsuarioBoton != null : "fx:id=\"añadirUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert buscarBoton != null : "fx:id=\"buscarBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert busquedaCampo != null : "fx:id=\"busquedaCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert contraseñaCampo != null : "fx:id=\"contraseñaCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert correoCampo != null : "fx:id=\"correoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert editarUsuarioBoton != null : "fx:id=\"editarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert eliminarUsuarioBoton != null : "fx:id=\"eliminarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna1 != null : "fx:id=\"nombreColumna1\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoCampo != null : "fx:id=\"telefonoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert usuariosTabla != null : "fx:id=\"usuariosTabla\" was not injected: check your FXML file 'GestionUsuario.fxml'.";

    }

}
