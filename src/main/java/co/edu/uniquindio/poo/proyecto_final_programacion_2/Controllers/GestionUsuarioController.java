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
    private TableColumn<?, ?> cedulaColumna;

    @FXML
    private TextField clienteContactoCampo;

    @FXML
    private TextField clienteCorreoCampo;

    @FXML
    private TextField clienteNombreCampo;

    @FXML
    private Button consultarSaldoBoton;

    @FXML
    private Button editarUsuarioBoton;

    @FXML
    private Button gestionarCategoriaBoton;

    @FXML
    private TableColumn<?, ?> nombreColumna;

    @FXML
    private TableColumn<?, ?> nombreColumna1;

    @FXML
    private Button realizarTransaccionesBoton;

    @FXML
    private Button recargarBoton;

    @FXML
    private TableColumn<?, ?> telefonoColumna;

    @FXML
    private TableView<?> usuariosTabla;

    @FXML
    void atrasAccion(ActionEvent event) {

    }

    @FXML
    void consultarSaldoAccion(ActionEvent event) {

    }

    @FXML
    void editarUsuarioAccion(ActionEvent event) {

    }

    @FXML
    void gestionarCategoriaAccion(ActionEvent event) {

    }

    @FXML
    void realizarTransaccionesAccion(ActionEvent event) {

    }

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteContactoCampo != null : "fx:id=\"clienteContactoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteCorreoCampo != null : "fx:id=\"clienteCorreoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteNombreCampo != null : "fx:id=\"clienteNombreCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert consultarSaldoBoton != null : "fx:id=\"consultarSaldoBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert editarUsuarioBoton != null : "fx:id=\"editarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert gestionarCategoriaBoton != null : "fx:id=\"gestionarCategoriaBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna1 != null : "fx:id=\"nombreColumna1\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert realizarTransaccionesBoton != null : "fx:id=\"realizarTransaccionesBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert usuariosTabla != null : "fx:id=\"usuariosTabla\" was not injected: check your FXML file 'GestionUsuario.fxml'.";

    }

}