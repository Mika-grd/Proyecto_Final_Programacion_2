package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.TipoCuenta;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

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
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<?, ?> colBanco;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colUsuario;

    @FXML
    private ComboBox<?> comboTipoCuenta;

    @FXML
    private TableView<?> tablaCuentas;

    @FXML
    private TextField txtCupoDisponible;

    @FXML
    private TextField txtCupoEnUso;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombreBanco;

    @FXML
    private TextField txtNumCuenta;

    @FXML
    private TextField txtSaldo;

    @FXML
    private TextField txtTasaInteres;

    @FXML
    private TextField txtUsuario;

    private final ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();


    private void agregarCuenta() {
        String id = txtId.getText();
        String banco = txtNombreBanco.getText();
        String numero = txtNumCuenta.getText();
        String usuario = txtUsuario.getText();
        String tipo = comboTipoCuenta.getValue();

        if (tipo == null) {
            mostrarAlerta("Debe seleccionar un tipo de cuenta.");
            return;
        }

        if (tipo.equals("Crédito")) {
            double tasa = Double.parseDouble(txtTasaInteres.getText());
            double disponible = Double.parseDouble(txtCupoDisponible.getText());
            double enUso = Double.parseDouble(txtCupoEnUso.getText());

            CuentaCredito cuenta = new CuentaCredito.Builder()
                    .id(id)
                    .nombreBanco(banco)
                    .numeroCuenta(numero)
                    .usuario(usuario)
                    .tasaInteres(tasa)
                    .cupoDisponible(disponible)
                    .cupoEnUso(enUso)
                    .build();

            listaCuentas.add(cuenta);
        } else {
            double saldo = Double.parseDouble(txtSaldo.getText());

            CuentaAhorro cuenta = new CuentaAhorro(id, banco, numero, usuario, saldo);
            listaCuentas.add(cuenta);
        }

        limpiarCampos();
    }

    private void eliminarCuenta() {
        Cuenta seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            listaCuentas.remove(seleccionada);
        } else {
            mostrarAlerta("Debe seleccionar una cuenta para eliminar.");
        }
    }

    private void actualizarTabla() {
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
        actualizarCamposPorTipo();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void initialize() {

        comboTipoCuenta.setItems(FXCollections.observableArrayList("Crédito", "Ahorro"));

        comboTipoCuenta.setOnAction(e -> actualizarCamposPorTipo());

        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colBanco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreBanco()));
        colNumero.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroCuenta()));
        colUsuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClass()));
        colTipo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoCuenta()));

        tablaCuentas.setItems(listaCuentas);

        btnAgregar.setOnAction(e -> agregarCuenta());
        btnEliminar.setOnAction(e -> eliminarCuenta());
        btnActualizar.setOnAction(e -> actualizarTabla());
    }

    private void actualizarCamposPorTipo() {
        String tipo = comboTipoCuenta.getValue();
        boolean esCredito = "Crédito".equals(tipo);

        boxCredito.setVisible(esCredito);
        boxDebito.setVisible(!esCredito);

        assert boxCredito != null : "fx:id=\"boxCredito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert boxDebito != null : "fx:id=\"boxDebito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colBanco != null : "fx:id=\"colBanco\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colNumero != null : "fx:id=\"colNumero\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colUsuario != null : "fx:id=\"colUsuario\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
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
