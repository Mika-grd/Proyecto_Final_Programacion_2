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
     * Muestra una alerta al usuario con un mensaje personalizado.
     *
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Acción ejecutada al presionar el botón de pagar cuota.
     * Si el monto es menor que la deuda, actualiza la deuda restante.
     * Si el monto es mayor, paga la deuda y usa el excedente para ampliar el cupo disponible.
     */
    @FXML
    void pagarCuotaAccion(ActionEvent event) {
        if (cuentaActual != null && !montoPagar.getText().isBlank()) {
            try {
                double monto = Double.parseDouble(montoPagar.getText());
                double deudaPendiente = cuentaActual.getDeudaTotal();

                if (monto < deudaPendiente) {
                    double faltante = deudaPendiente - monto;
                    mostrarAlerta("El monto no cubre la deuda. Faltan $" + faltante, Alert.AlertType.WARNING);
                    cuentaActual.setDeudaTotal(deudaPendiente - monto);
                    cargarSaldos();
                    return;
                }

                // Pago completo de la deuda
                cuentaActual.setDeudaTotal(0);
                double excedente = monto - deudaPendiente;

                if (excedente > 0) {
                    // Usa el excedente como nuevo cupo con patrón Command
                    ComandoRealizarPago comando = new ComandoRealizarPago(cuentaActual, excedente);
                    OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();
                    invoker.setComando(comando);

                    double ampliarCupo = excedente - cuentaActual.getCupoEnUso();

                    boolean exito = invoker.ejecutarComando();
                    if (exito) {
                        actualizarVistaCuentaSeleccionada(cuentaActual);
                        historialTable.refresh();
                        montoPagar.clear();
                        mostrarAlerta("Pago exitoso. Deuda saldada" +
                                (excedente > 0 ? " y cupo parcialmente liberado." : "."), Alert.AlertType.INFORMATION);
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

    /**
     * Acción que permite al usuario retirar dinero usando el cupo de su cuenta de crédito.
     * Utiliza el patrón Command para registrar la operación.
     */
    @FXML
    void retirarDineroAccion(ActionEvent event) {
        // Verifica que haya una cuenta seleccionada y que el campo de monto no esté vacío
        if (cuentaActual != null && !montoPagar.getText().isBlank()) {
            try {
                // Intenta convertir el texto del campo a un valor double
                double monto = Double.parseDouble(montoPagar.getText());

                // Crea el comando (retiro de dinero) y lo ejecuta
                ComandoUsarCupo comando = new ComandoUsarCupo(cuentaActual, monto, "Prestamo");
                OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();
                invoker.setComando(comando);
                boolean exito = invoker.ejecutarComando();

                // Si el comando se ejecutó correctamente
                if (exito) {
                    // Actualiza los campos visuales con la información de la cuenta
                    actualizarVistaCuentaSeleccionada(cuentaActual);

                    // Refresca la tabla del historial de transacciones
                    historialTable.refresh();

                    // Limpia el campo de texto donde se ingresó el monto
                    montoPagar.clear();

                    // Muestra una alerta de éxito
                    mostrarAlerta("Exitoso", Alert.AlertType.INFORMATION);

                    // Recarga los valores de saldo, cupo disponible y en uso
                    cargarSaldos();
                } else {
                    // Si la ejecución del comando falla, notifica al usuario
                    mostrarAlerta("Error en el retiro", Alert.AlertType.WARNING);
                }

            } catch (NumberFormatException e) {
                // Si el texto ingresado no es un número válido, muestra una alerta
                mostrarAlerta("Monto inválido", Alert.AlertType.WARNING);
            }
        } else {
            // Si no hay cuenta seleccionada o el campo está vacío, muestra advertencia
            mostrarAlerta("Selección requerida", Alert.AlertType.WARNING);
        }
    }

    /**
     * Actualiza los campos de texto que muestran la información de cupo de la cuenta seleccionada.
     *
     */
    private void actualizarVistaCuentaSeleccionada(CuentaCredito cuenta) {
        cupoDisponible.setText(String.format("$ %.2f", cuenta.getCupoDisponible()));
        cupoEnUso.setText(String.format("$ %.2f", cuenta.getCupoEnUso()));
    }

    /**
     * Recarga el historial de movimientos de la cuenta de crédito seleccionada en la tabla.
     */
    @FXML
    void recargarAccion(ActionEvent event) {
        historialTable.setItems(FXCollections.observableArrayList(cuentaActual.getMovimientosCredito()));
    }

    /**
     * Navega de regreso a la vista de gestión de cuentas.
     *
     */
    @FXML
    void volverAccion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionCuenta.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) volverBoton1.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            sesion.setCuentaSeleccionada(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los valores actuales del cupo disponible, cupo en uso y deuda total
     *
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

