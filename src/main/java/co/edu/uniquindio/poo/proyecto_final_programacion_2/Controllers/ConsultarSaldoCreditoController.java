package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.ComandoRealizarPago;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.ComandoUsarCupo;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.OperacionCuentaInvoker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import javafx.stage.Stage;

public class ConsultarSaldoCreditoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttnPagarCuota;

    @FXML
    private Button bttnPagarCuota1;

    @FXML
    private Label cupoDisponible;

    @FXML
    private Label cupoEnUso;

    @FXML
    private Label cupoEnUso1;

    @FXML
    private TableView<Transaccion> historialTable;

    @FXML
    private TableColumn<Transaccion, String> descripcionColumna;

    @FXML
    private TableColumn<Transaccion, LocalDate> fechaColumna;

    @FXML
    private TableColumn<Transaccion, String> idColumna;

    @FXML
    private TableColumn<Transaccion, Double> montoColumna;

    @FXML
    private AnchorPane idCupo;

    @FXML
    private AnchorPane idCupo1;

    @FXML
    private Label labelPagarCuota;


    @FXML
    private TextField montoPagar;

    @FXML
    private Button recargarBoton;

    @FXML
    private Button volverBoton1;

    Sesion sesion = Sesion.getInstancia();

    CuentaCredito cuentaActual = (CuentaCredito) sesion.getCuentaSeleccionada();

    ObservableList<Transaccion> listaTransacciones = FXCollections.observableArrayList(cuentaActual.getMovimientosCredito());



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
    void pagarCuotaAccion(ActionEvent event) {
        if (cuentaActual != null && !montoPagar.getText().isBlank()) {
            try {
                double monto = Double.parseDouble(montoPagar.getText());
                double deudaPendiente = cuentaActual.getDeudaTotal();

                if (monto < deudaPendiente) {
                    double faltante = deudaPendiente - monto;
                    mostrarAlerta("El monto no cubre la deuda. Faltan $" + faltante, Alert.AlertType.WARNING);
                    cargarSaldos();
                    return; // No continuar si no se cubre la deuda
                }

                // Pagar la deuda
                cuentaActual.setDeudaTotal(0);

                // Calcular excedente
                double excedente = monto - deudaPendiente;

                if (excedente > 0) {

                    ComandoRealizarPago comando = new ComandoRealizarPago(cuentaActual, excedente);
                    OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();
                    invoker.setComando(comando);

                    double ampliarCupo = excedente - cuentaActual.getCupoEnUso();

                    // Ejecutar el comando
                    boolean exito = invoker.ejecutarComando();
                    if (exito) {
                        actualizarVistaCuentaSeleccionada(cuentaActual);
                        historialTable.refresh();
                        montoPagar.clear();
                        mostrarAlerta("Pago exitoso. Deuda saldada" +
                                        (excedente > 0 ? " y cupo parcialmente liberado." : "."),
                                Alert.AlertType.INFORMATION);
                        cargarSaldos();

                        if (ampliarCupo > 0) {
                            cuentaActual.setCupoDisponible(cuentaActual.getCupoDisponible() + ampliarCupo);
                            cuentaActual.setCupoTotalInicial(cuentaActual.getCupoTotal() + ampliarCupo);
                            cargarSaldos();
                            mostrarAlerta("Dado el excedente, se ha ampliado su cupo", Alert.AlertType.INFORMATION);
                        }
                    } else {
                        mostrarAlerta("Error en el pago", Alert.AlertType.WARNING);
                    }
                }



            } catch (NumberFormatException e) {
                mostrarAlerta("Monto inválido", Alert.AlertType.WARNING);
            }
        } else {
            mostrarAlerta("Selección requerida", Alert.AlertType.WARNING);
        }
    }


    @FXML
    void retirarDineroAccion(ActionEvent event) {
        if (cuentaActual != null && !montoPagar.getText().isBlank()) {
            try {
                double monto = Double.parseDouble(montoPagar.getText());

                // Crear el comando
                ComandoUsarCupo comando = new ComandoUsarCupo(cuentaActual, monto, "Prestamo");
                OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();
                invoker.setComando(comando);

                // Ejecutar el comando
                boolean exito = invoker.ejecutarComando();
                if (exito) {
                    actualizarVistaCuentaSeleccionada(cuentaActual);
                    historialTable.refresh();
                    montoPagar.clear();
                    mostrarAlerta("Exitoso", Alert.AlertType.INFORMATION);
                    cargarSaldos();
                } else {
                    mostrarAlerta("Error en el retiro", Alert.AlertType.WARNING);
                }

            } catch (NumberFormatException e) {
                mostrarAlerta("Monto inválido", Alert.AlertType.WARNING);
            }
        } else {
            mostrarAlerta("Selección requerida", Alert.AlertType.WARNING);
        }
    }


    private void actualizarVistaCuentaSeleccionada(CuentaCredito cuenta) {
        cupoDisponible.setText(String.format("$ %.2f", cuenta.getCupoDisponible()));
        cupoEnUso.setText(String.format("$ %.2f", cuenta.getCupoEnUso()));
    }

    @FXML
    void recargarAccion(ActionEvent event) {
        historialTable.setItems(FXCollections.observableArrayList(cuentaActual.getMovimientosCredito()));
    }

    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionCuenta.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) volverBoton1.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);

            sesion.setCuentaSeleccionada(null);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }

    /**
     * Carga los label de cupo disponible y cupo en uso
     */
    private void cargarSaldos() {


        if (cuentaActual != null) {
            cupoDisponible.setText(String.format("$%,.2f", cuentaActual.getCupoDisponible()));
            cupoEnUso.setText(String.format("$%,.2f", cuentaActual.getCupoEnUso()));
            cupoEnUso1.setText(String.format("$%,.2f", cuentaActual.getDeudaTotal()));
        } else {
            cupoDisponible.setText("N/A");
            cupoEnUso.setText("N/A");
        }
    }

    @FXML
    void initialize() {


        cargarSaldos();

        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        descripcionColumna.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoTransaccionCredito().name())
        );
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaTransaccion"));
        montoColumna.setCellValueFactory(new PropertyValueFactory<>("montoATransferir"));

        historialTable.setItems(FXCollections.observableArrayList(cuentaActual.getListaTransaccion()));


        historialTable.setItems(listaTransacciones);

        assert bttnPagarCuota != null : "fx:id=\"bttnPagarCuota\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert bttnPagarCuota1 != null : "fx:id=\"bttnPagarCuota1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoDisponible != null : "fx:id=\"cupoDisponible\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoEnUso != null : "fx:id=\"cupoEnUso\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert cupoEnUso1 != null : "fx:id=\"cupoEnUso1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert descripcionColumna != null : "fx:id=\"descripcionColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert historialTable != null : "fx:id=\"historialTable\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idColumna != null : "fx:id=\"idColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idCupo != null : "fx:id=\"idCupo\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert idCupo1 != null : "fx:id=\"idCupo1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert labelPagarCuota != null : "fx:id=\"labelPagarCuota\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert montoPagar != null : "fx:id=\"montoPagar\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";
        assert volverBoton1 != null : "fx:id=\"volverBoton1\" was not injected: check your FXML file 'ConsultarSaldoCredito.fxml'.";

    }

}

