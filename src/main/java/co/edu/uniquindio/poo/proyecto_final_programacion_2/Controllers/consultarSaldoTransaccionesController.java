package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class consultarSaldoTransaccionesController {

    private BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Transaccion> historialTable;

    @FXML
    private TableColumn<Transaccion, String> descripcionColumna;

    @FXML
    private TableColumn<Transaccion, LocalDate > fechaColumna;

    @FXML
    private TableColumn<Transaccion, String> idColumna;

    @FXML
    private AnchorPane idSaldo;

    @FXML
    private TableColumn<Transaccion, Double> montoColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private Label saldoDisponible;

    @FXML
    private Label saldoTotal;

    @FXML
    private Button volverBoton1;


    @FXML
    private TextField RetirarDepositar;

    @FXML
    private Button bttnAgregarSaldo1;

    @FXML
    private Button bttnDepositarCategoria1;

    @FXML
    private Button bttnRetirarSaldo1;

    @FXML
    private ComboBox<Categoria> comboboxCategoria;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelRetirarDepositar;

    CuentaDebito cuentaActual = Sesion.getInstancia().getCuentaDebito();


    /**
     * Muestra una alerta de tipo WARNING con el mensaje especificado.
     * Se usa para notificar al usuario sobre errores o validaciones fallidas.
     *
     * @param mensaje Texto que se mostrará en la alerta.
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void agregarSaldoAccion(ActionEvent event) {
        if (RetirarDepositar.getText() != null) {

            try {
                double monto = Double.parseDouble(RetirarDepositar.getText());
                cuentaActual.agregarSaldo(monto);
                mostrarAlerta("Se ha agregado un saldo de " + String.valueOf(monto), Alert.AlertType.INFORMATION);
            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void depositarCategoria(ActionEvent event) {

    }



    @FXML
    void retirarSaldoAccion(ActionEvent event) {

        if (RetirarDepositar.getText() != null) {

            try {
                double monto = Double.parseDouble(RetirarDepositar.getText());
                if (monto > cuentaActual.getSaldo()) {
                    mostrarAlerta("No hay suficiente saldo en Disponible", Alert.AlertType.INFORMATION);
                }
                else {
                    cuentaActual.retirarSaldo(monto);
                    mostrarAlerta("Se ha retirado exitosamente saldo de " + String.valueOf(monto), Alert.AlertType.INFORMATION);
                }
            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void recargarAccion(ActionEvent event) {



    }

    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) volverBoton1.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }


    /**
     * Carga los label de saldo disponible y saldo total
     */
    private void cargarSaldos() {


        if (cuentaActual != null) {
            saldoDisponible.setText(String.format("$%,.2f", cuentaActual.getSaldo()));
            saldoTotal.setText(String.format("$%,.2f", cuentaActual.getSaldoTotal()));
        } else {
            saldoDisponible.setText("N/A");
            saldoTotal.setText("N/A");
        }
    }

    @FXML
    void initialize() {

        cargarSaldos();

        Cuenta cuentaActual = Sesion.getInstancia().getCuentaDebito();

        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        montoColumna.setCellValueFactory(new PropertyValueFactory<>("monto"));

        historialTable.setItems(FXCollections.observableArrayList(cuentaActual.getListaTransaccion()));

        assert RetirarDepositar != null : "fx:id=\"RetirarDepositar\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnAgregarSaldo1 != null : "fx:id=\"bttnAgregarSaldo1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnDepositarCategoria1 != null : "fx:id=\"bttnDepositarCategoria1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnRetirarSaldo1 != null : "fx:id=\"bttnRetirarSaldo1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert comboboxCategoria != null : "fx:id=\"comboboxCategoria\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert descripcionColumna != null : "fx:id=\"descripcionColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert historialTable != null : "fx:id=\"historialTable\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idColumna != null : "fx:id=\"idColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idSaldo != null : "fx:id=\"idSaldo\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert labelCategoria != null : "fx:id=\"labelCategoria\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert labelRetirarDepositar != null : "fx:id=\"labelRetirarDepositar\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert saldoDisponible != null : "fx:id=\"saldoDisponible\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert saldoTotal != null : "fx:id=\"saldoTotal\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert volverBoton1 != null : "fx:id=\"volverBoton1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";

    }

}
