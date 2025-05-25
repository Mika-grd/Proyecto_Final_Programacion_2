package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConsultarSaldoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> bancoColumna;

    @FXML
    private Button bttnPagarCuota;

    @FXML
    private Label cupoDisponible;

    @FXML
    private TableColumn<?, ?> cupoDisponibleColumna;

    @FXML
    private Label cupoEnUso;

    @FXML
    private TableColumn<?, ?> cupoEnUsoColumna;

    @FXML
    private TableColumn<?, ?> idCuentaColumna;

    @FXML
    private AnchorPane idCupo;

    @FXML
    private AnchorPane idCupo1;

    @FXML
    private Label labelPagarCuota;

    @FXML
    private TextField montoPagar;

    @FXML
    private TableColumn<?, ?> numeroCuentaColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private TableView<?> tablaCuentasCredito;

    @FXML
    private TableColumn<?, ?> tasaColumna;

    @FXML
    private Button volverBoton1;

    @FXML
    void pagarCuotaAccion(ActionEvent event) {

    }

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert bancoColumna != null : "fx:id=\"bancoColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert bttnPagarCuota != null : "fx:id=\"bttnPagarCuota\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoDisponible != null : "fx:id=\"cupoDisponible\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoDisponibleColumna != null : "fx:id=\"cupoDisponibleColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoEnUso != null : "fx:id=\"cupoEnUso\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoEnUsoColumna != null : "fx:id=\"cupoEnUsoColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idCuentaColumna != null : "fx:id=\"idCuentaColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idCupo != null : "fx:id=\"idCupo\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idCupo1 != null : "fx:id=\"idCupo1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert labelPagarCuota != null : "fx:id=\"labelPagarCuota\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert montoPagar != null : "fx:id=\"montoPagar\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert numeroCuentaColumna != null : "fx:id=\"numeroCuentaColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert tablaCuentasCredito != null : "fx:id=\"tablaCuentasCredito\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert tasaColumna != null : "fx:id=\"tasaColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert volverBoton1 != null : "fx:id=\"volverBoton1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";

    }

}

