package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class gestionCuentaEspecificaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Transaccion, String> cuentaDestinoColumna;

    @FXML
    private TableColumn<Transaccion, String> cuentaOrigenColumna;

    @FXML
    private TableColumn<Transaccion, String> fechaColumna;

    @FXML
    private Button generarReporteBoton;

    @FXML
    private Button gestionarSusCuentasBoton;

    @FXML
    private TableView<Transaccion> historialTabla;

    @FXML
    private TableColumn<Transaccion, String> montoColumna;

    @FXML
    private Button volverBoton;

    private CuentaDebito cuentaSeleccionada;

    public void setCuentaSeleccionada(CuentaDebito cuenta) {
        this.cuentaSeleccionada = cuenta;
        cargarTransacciones();
    }

    private void cargarTransacciones() {
        if (cuentaSeleccionada != null) {
            ObservableList<Transaccion> transacciones = FXCollections.observableArrayList(cuentaSeleccionada.getListaTransaccion());
            historialTabla.setItems(transacciones);
        }
    }

    @FXML
    void generarReporteAccion(ActionEvent event) {
        // Puedes exportar los datos de historialTabla a un archivo si lo deseas
    }

    @FXML
    void gestionarSusCuentasAccion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/proyecto_final_programacion_2/view/GestionCuentaAdministrador.fxml"
            ));

            Parent root = loader.load();

            Stage stage = (Stage) gestionarSusCuentasBoton.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void volverAccion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/proyecto_final_programacion_2/view/GestionarUsuarioAdmin.fxml"
            ));

            Parent root = loader.load();

            Stage stage = (Stage) volverBoton.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        assert cuentaDestinoColumna != null : "fx:id=\"cuentaDestinoColumna\" was not injected: check your FXML file.";
        assert cuentaOrigenColumna != null : "fx:id=\"cuentaOrigenColumna\" was not injected: check your FXML file.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file.";
        assert generarReporteBoton != null : "fx:id=\"generarReporteBoton\" was not injected: check your FXML file.";
        assert gestionarSusCuentasBoton != null : "fx:id=\"gestionarSusCuentasBoton\" was not injected: check your FXML file.";
        assert historialTabla != null : "fx:id=\"historialTabla\" was not injected: check your FXML file.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file.";

        // Asignación de columnas
        fechaColumna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFechaTransaccion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
        );

        montoColumna.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getMontoATransferir())
                )
        );

        cuentaOrigenColumna.setCellValueFactory(cellData -> {
            var cuentaPropia = cellData.getValue().getCuentaPropia();
            return new javafx.beans.property.SimpleStringProperty(
                    cuentaPropia != null ? cuentaPropia.getId() : "N/A"
            );
        });

        cuentaDestinoColumna.setCellValueFactory(cellData -> {
            var cuentaObjetivo = cellData.getValue().getCuentaObjetivo();
            return new javafx.beans.property.SimpleStringProperty(
                    cuentaObjetivo != null ? cuentaObjetivo.getId() : "N/A"
            );
        });
    }
}
