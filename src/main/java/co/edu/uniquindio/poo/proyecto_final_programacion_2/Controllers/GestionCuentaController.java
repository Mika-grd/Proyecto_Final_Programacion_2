package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionCuentaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="agregarCuentaBoton"
    private Button agregarCuentaBoton; // Value injected by FXMLLoader

    @FXML // fx:id="cuentasTabla"
    private TableView<?> cuentasTabla; // Value injected by FXMLLoader

    @FXML // fx:id="editarCuentaBoton"
    private Button editarCuentaBoton; // Value injected by FXMLLoader

    @FXML // fx:id="eliminarCuentaBoton"
    private Button eliminarCuentaBoton; // Value injected by FXMLLoader

    @FXML // fx:id="numeroCuentaCampo"
    private TextField numeroCuentaCampo; // Value injected by FXMLLoader

    @FXML // fx:id="numeroCuentaColumna"
    private TableColumn<?, ?> numeroCuentaColumna; // Value injected by FXMLLoader

    @FXML // fx:id="saldoCampo"
    private TextField saldoCampo; // Value injected by FXMLLoader

    @FXML // fx:id="saldoColumna"
    private TableColumn<?, ?> saldoColumna; // Value injected by FXMLLoader

    @FXML // fx:id="tipoCuentaColumna"
    private TableColumn<?, ?> tipoCuentaColumna; // Value injected by FXMLLoader

    @FXML // fx:id="tipoCuentaCombo"
    private ComboBox<?> tipoCuentaCombo; // Value injected by FXMLLoader

    @FXML // fx:id="volverBoton"
    private Button volverBoton; // Value injected by FXMLLoader

    @FXML
    void agregarCuenta(ActionEvent event) {

    }

    @FXML
    void editarCuenta(ActionEvent event) {

    }

    @FXML
    void eliminarCuenta(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert agregarCuentaBoton != null : "fx:id=\"agregarCuentaBoton\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert cuentasTabla != null : "fx:id=\"cuentasTabla\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert editarCuentaBoton != null : "fx:id=\"editarCuentaBoton\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert eliminarCuentaBoton != null : "fx:id=\"eliminarCuentaBoton\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert numeroCuentaCampo != null : "fx:id=\"numeroCuentaCampo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert numeroCuentaColumna != null : "fx:id=\"numeroCuentaColumna\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert saldoCampo != null : "fx:id=\"saldoCampo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert saldoColumna != null : "fx:id=\"saldoColumna\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert tipoCuentaColumna != null : "fx:id=\"tipoCuentaColumna\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert tipoCuentaCombo != null : "fx:id=\"tipoCuentaCombo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionCuenta.fxml'.";

    }
}
