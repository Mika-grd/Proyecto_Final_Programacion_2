package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaCredito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.ComandoRealizarPago;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.OperacionCuentaInvoker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ConsultarSaldoCreditoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CuentaCredito, String> bancoColumna;

    @FXML
    private Button bttnPagarCuota;

    @FXML
    private Label cupoDisponible;

    @FXML
    private TableColumn<CuentaCredito, Double> cupoDisponibleColumna;

    @FXML
    private Label cupoEnUso;

    @FXML
    private TableColumn<CuentaCredito, Double> cupoEnUsoColumna;

    @FXML
    private TableColumn<CuentaCredito, String> idCuentaColumna;

    @FXML
    private AnchorPane idCupo;

    @FXML
    private AnchorPane idCupo1;

    @FXML
    private Label labelPagarCuota;

    @FXML
    private TextField montoPagar;

    @FXML
    private TableColumn<CuentaCredito, Integer> numeroCuentaColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private TableView<CuentaCredito> tablaCuentasCredito;

    @FXML
    private TableColumn<CuentaCredito, Double> tasaColumna;

    @FXML
    private Button volverBoton1;

    private Usuario usuario;

    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    private ObservableList<CuentaCredito> listaCuentasCredito = FXCollections.observableArrayList();

    @FXML
    void pagarCuotaAccion(ActionEvent event) {
        CuentaCredito cuentaSeleccionada = tablaCuentasCredito.getSelectionModel().getSelectedItem();
        if (cuentaSeleccionada != null && !montoPagar.getText().isBlank()) {
            try {
                double monto = Double.parseDouble(montoPagar.getText());

                // Crear el comando
                ComandoRealizarPago comando = new ComandoRealizarPago(cuentaSeleccionada, monto);
                OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();
                invoker.setComando(comando);

                // Ejecutar el comando
                boolean exito = invoker.ejecutarComando();
                if (exito) {
                    actualizarVistaCuentaSeleccionada(cuentaSeleccionada);
                    tablaCuentasCredito.refresh();
                    montoPagar.clear();
                } else {
                    mostrarAlerta("Error en el pago", "No se pudo realizar el pago. Verifica el monto.");
                }

            } catch (NumberFormatException e) {
                mostrarAlerta("Monto inválido", "Por favor ingresa un número válido.");
            }
        } else {
            mostrarAlerta("Selección requerida", "Selecciona una cuenta y escribe un monto a pagar.");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void actualizarVistaCuentaSeleccionada(CuentaCredito cuenta) {
        cupoDisponible.setText(String.format("$ %.2f", cuenta.getCupoDisponible()));
        cupoEnUso.setText(String.format("$ %.2f", cuenta.getCupoEnUso()));
    }

    @FXML
    void recargarAccion(ActionEvent event) {
        tablaCuentasCredito.setItems(listaCuentasCredito);
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

        configurarColumnasTabla();
        cargarCuentasCredito();
    }

    private void configurarColumnasTabla() {
        idCuentaColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        bancoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        numeroCuentaColumna.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        tasaColumna.setCellValueFactory(new PropertyValueFactory<>("tasaInteres"));
        cupoDisponibleColumna.setCellValueFactory(new PropertyValueFactory<>("cupoDisponible"));
        cupoEnUsoColumna.setCellValueFactory(new PropertyValueFactory<>("cupoEnUso"));
    }

    public void inicializarDatos(Usuario usuario) {
        this.usuario = usuario;
        cargarCuentasCredito(); // Llamas aquí directamente si depende del usuario
    }


    private void cargarCuentasCredito() {
        listaUsuarios = FXCollections.observableArrayList();
        listaCuentasCredito = FXCollections.observableArrayList();

        listaCuentasCredito.addAll(
                new CuentaCredito("CR001", "Banco A", 12345, usuario, 5000.0, 1000.0, 0.05),
                new CuentaCredito("CR002", "Banco B", 67890, usuario, 4000.0, 1500.0, 0.04)
        );

        tablaCuentasCredito.setItems(listaCuentasCredito);
    }

}
