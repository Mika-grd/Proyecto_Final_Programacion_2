package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class gestionCuentaEspecificaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> cuentaDestinoColumna;

    @FXML
    private TableColumn<?, ?> cuentaOrigenColumna;

    @FXML
    private TableColumn<?, ?> fechaColumna;

    @FXML
    private Button generarReporteBoton;

    @FXML
    private Button gestionarSusCuentasBoton;

    @FXML
    private TableView<?> historialTabla;

    @FXML
    private TableColumn<?, ?> montoColumna;

    @FXML
    private Button volverBoton;

    @FXML
    void generarReporteAccion(ActionEvent event) {

    }

    @FXML
    void gestionarSusCuentasAccion(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cuentaDestinoColumna != null : "fx:id=\"cuentaDestinoColumna\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert cuentaOrigenColumna != null : "fx:id=\"cuentaOrigenColumna\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert generarReporteBoton != null : "fx:id=\"generarReporteBoton\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert gestionarSusCuentasBoton != null : "fx:id=\"gestionarSusCuentasBoton\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert historialTabla != null : "fx:id=\"historialTabla\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";

    }

}
