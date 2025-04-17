package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCreditoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionCuentaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxCredito;

    @FXML
    private HBox boxDebito;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Cuenta,String > colBanco;

    @FXML
    private TableColumn<Cuenta, String> colCategoria;

    @FXML
    private TableColumn<Cuenta, String> colId;

    @FXML
    private TableColumn<Cuenta, String> colNumero;

    @FXML
    private TableColumn<Cuenta, String> colTipo;

    @FXML
    private TableColumn<Cuenta, String> colUsuario;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboTipoCuenta;

    @FXML
    private TableView<Cuenta> tablaCuentas;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombreBanco;

    @FXML
    private TextField txtNumCuenta;


    @FXML
    private ComboBox<Double> txtTasaInteres;

    @FXML
    private TextField txtUsuario;

    private final ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();


    private void mostrarCamposPorTipo(String tipo) {
        if ("Débito".equals(tipo)) {
            boxDebito.setVisible(true);
            boxCredito.setVisible(false);
        } else if ("Crédito".equals(tipo)) {
            boxDebito.setVisible(false);
            boxCredito.setVisible(true);
        } else {
            boxDebito.setVisible(false);
            boxCredito.setVisible(false);
        }
    }

    @FXML
    private void agregarCuenta() {
        String id = txtId.getText();
        String banco = txtNombreBanco.getText();
        String numero = txtNumCuenta.getText();
        String usuario = txtUsuario.getText();
        String tipo = comboTipoCuenta.getValue();
        String categoria = comboCategoria.getValue();

        if (id.isEmpty() || banco.isEmpty() || numero.isEmpty() || usuario.isEmpty() || tipo == null || categoria == null) {
            mostrarAlerta("Debe completar todos los campos.");
            return;
        }

        Cuenta cuenta;

        if ("Débito".equals(tipo)) {
            try {
                double saldo = Double.parseDouble(txtSaldo.getText());
                cuenta = new CuentaDebito(id, banco, numero, usuario, saldo, categoria);
            } catch (NumberFormatException e) {
                mostrarAlerta("Saldo inválido.");
                return;
            }
        } else if ("Crédito".equals(tipo)) {
            try {
                double tasaInteres = Double.parseDouble(txtTasaInteres.getText());
                double cupoDisponible = Double.parseDouble(txtCupoDisponible.getText());
                double cupoEnUso = Double.parseDouble(txtCupoEnUso.getText());

                cuenta = new CuentaCreditoBuilder()
                        .setId(id)
                        .setNombreBanco(banco)
                        .setNumeroCuenta(numero)
                        .setUsuario(usuario)
                        .setTasaInteres(tasaInteres)
                        .setCupoDisponible(cupoDisponible)
                        .setCupoEnUso(cupoEnUso)
                        .setCategoria(categoria)
                        .build();

            } catch (NumberFormatException e) {
                mostrarAlerta("Datos numéricos inválidos en crédito.");
                return;
            }
        } else {
            mostrarAlerta("Seleccione un tipo de cuenta válido.");
            return;
        }

        listaCuentas.add(cuenta);
        limpiarCampos();
    }

    @FXML
    private void eliminarCuenta() {
        Cuenta seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            listaCuentas.remove(seleccionada);
        } else {
            mostrarAlerta("Debe seleccionar una cuenta para eliminar.");
        }
    }

    @FXML
    private void actualizarCuenta() {
        tablaCuentas.refresh();
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombreBanco.clear();
        txtNumCuenta.clear();
        txtUsuario.clear();
        txtSaldo.clear();
        txtTasaInteres.clear();
        txtCupoDisponible.clear();
        txtCupoEnUso.clear();
        comboTipoCuenta.setValue(null);
        comboCategoria.setValue(null);
        boxDebito.setVisible(false);
        boxCredito.setVisible(false);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void initialize() {

        // Configurar ComboBoxes
        comboTipoCuenta.setItems(FXCollections.observableArrayList("Débito", "Crédito"));
        comboCategoria.setItems(FXCollections.observableArrayList("Ahorro", "Corriente", "Inversiones", "Fondo"));

        // Configurar columnas de la tabla
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreBanco()));
        colNumero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumCuenta()));
        colUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoCuenta()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaCategorias()));

        tablaCuentas.setItems(listaCuentas);

        // Manejador para mostrar campos dinámicamente
        comboTipoCuenta.setOnAction(event -> mostrarCamposPorTipo(comboTipoCuenta.getValue()));

        assert boxCredito != null : "fx:id=\"boxCredito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert boxDebito != null : "fx:id=\"boxDebito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colBanco != null : "fx:id=\"colBanco\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colCategoria != null : "fx:id=\"colCategoria\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colNumero != null : "fx:id=\"colNumero\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colUsuario != null : "fx:id=\"colUsuario\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert comboCategoria != null : "fx:id=\"comboCategoria\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert comboTipoCuenta != null : "fx:id=\"comboTipoCuenta\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert tablaCuentas != null : "fx:id=\"tablaCuentas\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtCupoDisponible != null : "fx:id=\"txtCupoDisponible\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtCupoEnUso != null : "fx:id=\"txtCupoEnUso\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtNombreBanco != null : "fx:id=\"txtNombreBanco\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtNumCuenta != null : "fx:id=\"txtNumCuenta\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtSaldo != null : "fx:id=\"txtSaldo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtTasaInteres != null : "fx:id=\"txtTasaInteres\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'GestionCuenta.fxml'.";

    }


}
