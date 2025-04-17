package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class RealizarTransaccionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button añadirTransaccionBoton;

    @FXML
    private HBox boxCredito;

    @FXML
    private HBox boxDebito;

    @FXML
    private DatePicker fechaTransaccion;

    @FXML
    private TextField txtCuenta;

    @FXML
    private TextField txtCupoDisponible;

    @FXML
    private TextField txtCupoEnUso;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdCuenta;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtSaldo;

    @FXML
    private TextField txtTasaInteres;

    @FXML
    private TextField txtTitular;

    @FXML
    void añadirEmpleadoAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert añadirTransaccionBoton != null : "fx:id=\"añadirTransaccionBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert boxCredito != null : "fx:id=\"boxCredito\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert boxDebito != null : "fx:id=\"boxDebito\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert fechaTransaccion != null : "fx:id=\"fechaTransaccion\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtCuenta != null : "fx:id=\"txtCuenta\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtCupoDisponible != null : "fx:id=\"txtCupoDisponible\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtCupoEnUso != null : "fx:id=\"txtCupoEnUso\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtIdCuenta != null : "fx:id=\"txtIdCuenta\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtMonto != null : "fx:id=\"txtMonto\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtSaldo != null : "fx:id=\"txtSaldo\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtTasaInteres != null : "fx:id=\"txtTasaInteres\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtTitular != null : "fx:id=\"txtTitular\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";

    }
    
}
