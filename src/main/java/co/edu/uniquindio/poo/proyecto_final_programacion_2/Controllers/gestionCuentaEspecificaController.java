package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
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

    @FXML
    private Button recargarBoton;

    CuentaDebito cuentaActual = Sesion.getInstancia().getCuentaDebito();

    private CuentaDebito cuentaSeleccionada;

    public void setCuentaSeleccionada(CuentaDebito cuenta) {
        this.cuentaSeleccionada = cuenta;
        cargarTransacciones();
    }

    //muestra las transacciones en la tabla
    private void cargarTransacciones() {
        if (cuentaSeleccionada != null) {
            ObservableList<Transaccion> transacciones = FXCollections.observableArrayList(cuentaSeleccionada.getListaTransaccion());
            historialTabla.setItems(transacciones);
        }
    }

    //recarrga la tabla
    @FXML
    void recargarAccion(ActionEvent event) {
        ObservableList<Transaccion> historial = FXCollections.observableArrayList(cuentaActual.getListaTransaccion());
        historialTabla.setItems(historial);
    }

    @FXML
    void generarReporteAccion(ActionEvent event) {

    }

    //va a gestion cuenta administrador
    @FXML
    void gestionarSusCuentasAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionCuentaAdministrador.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) gestionarSusCuentasBoton.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }

    //Vuelve gestionar usuario admin
    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionarUsuarioAdmin.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) volverBoton.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
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
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionCuentaEspecifica.fxml'.";
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
            var cuentaPropia = cellData.getValue().getCuentaPropiaDebito();
            return new javafx.beans.property.SimpleStringProperty(
                    cuentaPropia != null ? cuentaPropia.getId() : "N/A"
            );
        });

        cuentaDestinoColumna.setCellValueFactory(cellData -> {
            var cuentaObjetivo = cellData.getValue().getCuentaObjetivoDebito();
            return new javafx.beans.property.SimpleStringProperty(
                    cuentaObjetivo != null ? cuentaObjetivo.getId() : "N/A"
            );
        });
    }

}





