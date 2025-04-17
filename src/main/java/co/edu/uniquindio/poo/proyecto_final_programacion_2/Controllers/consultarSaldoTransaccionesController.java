package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

public class consultarSaldoTransaccionesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> descripcionColumna;

    @FXML
    private TableColumn<?, ?> fechaColumna;

    @FXML
    private TableColumn<?, ?> idColumna;

    @FXML
    private AnchorPane idSaldo;

    @FXML
    private TableColumn<?, ?> montoColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert descripcionColumna != null : "fx:id=\"descripcionColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idColumna != null : "fx:id=\"idColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idSaldo != null : "fx:id=\"idSaldo\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";

    }

}
