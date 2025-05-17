package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.*;
import javafx.stage.Stage;

public class GestionCuentaAdministradorController {

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
    private Button btnBuscarCredito;

    @FXML
    private Button btnBuscarTransaccion;

    @FXML
    private Button btnCambiarCupo;

    @FXML
    private Button btnCambiarInteres;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCerrarSesion1;

    @FXML
    private Button btnCerrarSesion2;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnRecargarCredito;

    @FXML
    private Button btnRecargarTransaccion;



    @FXML
    private TableColumn<Cuenta,String> colBanco;

    @FXML
    private TableColumn<CuentaCredito,String> colCreditoCupo;

    @FXML
    private TableColumn<CuentaCredito, String> colCreditoId;

    @FXML
    private TableColumn<CuentaCredito, String> colCreditoInteres;

    @FXML
    private TableColumn<Cuenta,String > colId;

    @FXML
    private TableColumn<Cuenta, String> colNumero;

    @FXML
    private TableColumn<Cuenta, String> colTipo;

    @FXML
    private TableColumn<Cuenta, String> colTransDescripcion;

    @FXML
    private TableColumn<Cuenta, String> colTransDestino;

    @FXML
    private TableColumn<Cuenta, String> colTransFecha;

    @FXML
    private TableColumn<Cuenta, String> colTransId;

    @FXML
    private TableColumn<Cuenta, String> colTransMonto;

    @FXML
    private TableColumn<Cuenta, String> colTransOrigen;

    @FXML
    private TableColumn<Usuario, String> colUsuario;

    @FXML
    private ComboBox<String> comboTipoCuenta;

    @FXML
    private TableView<CuentaCredito> tablaCuentaCredito;

    @FXML
    private TableView<Cuenta> tablaCuentas;

    @FXML
    private TableView<Transaccion> tablaTransacciones;

    @FXML
    private TextField txtBuscarId;

    @FXML
    private TextField txtBuscarTransaccion;

    @FXML
    private TextField txtCupoDisponible;

    @FXML
    private TextField txtCupoEnUso;

    @FXML
    private TextField txtCupoModificar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtInteresModificar;

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

    /*
        * Atributos para almacenar las listas de cuentas y transacciones
     */
    private ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();
    private ObservableList<CuentaCredito> listaCuentasCredito = FXCollections.observableArrayList();
    private ObservableList<Transaccion> listaTransacciones = FXCollections.observableArrayList();

    /*
        * Método para actualizar la tabla de cuentas
        * Se llama cada vez que se agrega, elimina o modifica una cuenta
        * Se actualiza la lista de cuentas y se asigna a la tabla
        * @param event Evento que activa el método
     */
    @FXML
    void actualizarTablaAccion(ActionEvent event) {
        tablaCuentas.setItems(FXCollections.observableArrayList(Sesion.getInstancia().getUsuario().getListaCuentas()));

    }

    /*
        * Método para agregar una cuenta
        * Se llama al presionar el botón "Agregar"
        * Se valida que los campos no estén vacíos y se crea la cuenta según el tipo seleccionado
        * Se agrega la cuenta a la lista del usuario y se actualiza la tabla
        * @param event Evento que activa el método
     */
    @FXML
    void agregarCuentaAccion(ActionEvent event) {
        String tipoCuenta = comboTipoCuenta.getValue();
        String id = txtId.getText();
        String banco = txtNombreBanco.getText();
        String numeroStr = txtNumCuenta.getText();
        Usuario usuario = Sesion.getInstancia().getUsuario();

        if (tipoCuenta == null || id.isEmpty() || banco.isEmpty() || numeroStr.isEmpty()) {
            mostrarAlerta("Por favor complete todos los campos obligatorios.");
            return;
        }

        try {
            int numeroCuenta = Integer.parseInt(numeroStr);

            if ("Débito".equals(tipoCuenta)) {
                double saldo = Double.parseDouble(txtSaldo.getText());
                CuentaDebito cuentaDebito = new CuentaDebito(id, banco, numeroCuenta, usuario, saldo);
                usuario.getListaCuentas().add(cuentaDebito);
            } else {
                double tasa = Double.parseDouble(txtTasaInteres.getText());
                double cupoDisponible = Double.parseDouble(txtCupoDisponible.getText());
                double cupoEnUso = Double.parseDouble(txtCupoEnUso.getText());
                CuentaCredito cuentaCredito = new CuentaCredito(id, banco, numeroCuenta, usuario, tasa, cupoDisponible, cupoEnUso);
                usuario.getListaCuentas().add(cuentaCredito);
            }

            actualizarTablaAccion(event);
            mostrarAlerta("Cuenta agregada correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor ingrese valores numéricos válidos.");
        }
    }

    /*
        * Método para buscar una cuenta crédito por ID
        * Se llama al presionar el botón "Buscar"
        * Se valida que el campo de búsqueda no esté vacío y se busca la cuenta en la lista del usuario
        * Si se encuentra, se muestra en la tabla de cuentas crédito
        * @param event Evento que activa el método
     */
    @FXML
    void buscarCuentaCredito(ActionEvent event) {

        String id = txtBuscarId.getText();
        if (id.isEmpty()) {
            mostrarAlerta("Debe ingresar un ID.");
            return;
        }

        Optional<CuentaCredito> cuenta = Sesion.getInstancia().getUsuario()
                .getListaCuentas().stream()
                .filter(c -> c instanceof CuentaCredito && c.getId().equals(id))
                .map(c -> (CuentaCredito) c)
                .findFirst();

        if (cuenta.isPresent()) {
            tablaCuentaCredito.setItems(FXCollections.observableArrayList(cuenta.get()));
        } else {
            mostrarAlerta("No se encontró cuenta crédito con ese ID.");
        }

    }

    /*
    Método para buscar una transacción por ID
     */
    @FXML
    void buscarTransaccionAccion(ActionEvent event) {
        String id = txtBuscarTransaccion.getText();
        if (id.isEmpty()) {
            mostrarAlerta("Ingrese el ID de la transacción.");
            return;
        }

        List<Transaccion> transacciones = new ArrayList<>();
        for (Cuenta cuenta : Sesion.getInstancia().getUsuario().getListaCuentas()) {
            if (cuenta instanceof CuentaDebito debito) {
                transacciones.addAll(debito.getListaTransaccion());
            }
        }

        Optional<Transaccion> transaccion = transacciones.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (transaccion.isPresent()) {
            tablaTransacciones.setItems(FXCollections.observableArrayList(transaccion.get()));
        } else {
            mostrarAlerta("Transacción no encontrada.");
        }
    }

    /*
        Método para cambiar el cupo de una cuenta crédito
        Se llama al presionar el botón "Cambiar Cupo"
        Se valida que el campo de cupo no esté vacío y se actualiza la cuenta seleccionada
        Se actualiza la tabla de cuentas crédito
        @param event Evento que activa el método
     */
    @FXML
    void cambiarCupoAccion(ActionEvent event) {
        String nuevoCupo = txtCupoModificar.getText();
        Cuenta seleccionada = tablaCuentaCredito.getSelectionModel().getSelectedItem();

        if (seleccionada instanceof CuentaCredito cuenta) {
            cuenta.setCupoDisponible(Double.parseDouble(nuevoCupo));
            actualizarTablaAccion(event);
            mostrarAlerta("Cupo actualizado.");
        } else {
            mostrarAlerta("Seleccione una cuenta crédito válida.");
        }
    }

    /*
        Método para cambiar la tasa de interés de una cuenta crédito
        Se llama al presionar el botón "Cambiar Interés"
        Se valida que el campo de interés no esté vacío y se actualiza la cuenta seleccionada
        Se actualiza la tabla de cuentas crédito
        @param event Evento que activa el método
     */
    @FXML
    void cambiarInteresAccion(ActionEvent event) {
        String nuevoInteres = txtInteresModificar.getText();
        Cuenta seleccionada = tablaCuentaCredito.getSelectionModel().getSelectedItem();

        if (seleccionada instanceof CuentaCredito cuenta) {
            cuenta.setTasaInteres(Double.parseDouble(nuevoInteres));
            actualizarTablaAccion(event);
            mostrarAlerta("Interés actualizado.");
        } else {
            mostrarAlerta("Seleccione una cuenta crédito válida.");
        }
    }

    /*
        Método para cerrar sesión
        Se llama al presionar el botón "Cerrar Sesión"
        Se carga la ventana de inicio de sesión y se muestra
        @param event Evento que activa el método
     */
    @FXML
    void cerrarSesion(ActionEvent event) {
        try {
            // Cargar la ventana de inicio de sesión
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/InicioSesion.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual y cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Inicio de Sesión");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
        Método para eliminar una cuenta seleccionada
        Se llama al presionar el botón "Eliminar"
        Se valida que haya una cuenta seleccionada y se elimina de la lista del usuario
        Se actualiza la tabla de cuentas
        @param event Evento que activa el método
     */
    @FXML
    void eliminarCuentaAccion(ActionEvent event) {
        Cuenta cuenta = tablaCuentas.getSelectionModel().getSelectedItem();

        if (cuenta != null) {
            Sesion.getInstancia().getUsuario().getListaCuentas().remove(cuenta);
            actualizarTablaAccion(event);
            mostrarAlerta("Cuenta eliminada.");
        } else {
            mostrarAlerta("Seleccione una cuenta para eliminar.");
        }
    }

    /*
        Método para generar un reporte de cuentas
        Se llama al presionar el botón "Generar Reporte"
        Se muestra un mensaje con la información de las cuentas del usuario
        @param event Evento que activa el método
     */
    @FXML
    void generarReporteAccion(ActionEvent event) {
        Usuario usuario = Sesion.getInstancia().getUsuario();
        StringBuilder reporte = new StringBuilder("REPORTE DE CUENTAS:\n");

        for (Cuenta c : usuario.getListaCuentas()) {
            reporte.append("- ").append(c.getTipoCuenta()).append(": ")
                    .append(c.getNumeroCuenta()).append(" (")
                    .append(c.getBanco()).append(")\n");
        }

        mostrarAlerta(reporte.toString());
    }

    /*
        Método para recargar la tabla de cuentas crédito
        Se llama al presionar el botón "Recargar"
        Se actualiza la lista de cuentas crédito y se asigna a la tabla
        @param event Evento que activa el método
     */
    @FXML
    void recargarTablaCredito(ActionEvent event) {
        List<CuentaCredito> cuentasCredito = Sesion.getInstancia().getUsuario()
                .getListaCuentas().stream()
                .filter(c -> c instanceof CuentaCredito)
                .map(c -> (CuentaCredito) c)
                .toList();

        tablaCuentaCredito.setItems(FXCollections.observableArrayList(cuentasCredito));
    }

    /*
        Método para recargar la tabla de transacciones
        Se llama al presionar el botón "Recargar"
        Se actualiza la lista de transacciones y se asigna a la tabla
        @param event Evento que activa el método
     */
    @FXML
    void recargarTransaccionAccion(ActionEvent event) {
        List<Transaccion> transacciones = new ArrayList<>();
        for (Cuenta cuenta : Sesion.getInstancia().getUsuario().getListaCuentas()) {
            if (cuenta instanceof CuentaDebito debito) {
                transacciones.addAll(debito.getListaTransaccion());
            }
        }
        tablaTransacciones.setItems(FXCollections.observableArrayList(transacciones));
    }


    /*
        Método para mostrar un mensaje de alerta
        Se utiliza para mostrar mensajes informativos al usuario
        @param mensaje Mensaje a mostrar en la alerta
     */
    @FXML
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensaje");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /*
        Método para inicializar el controlador
     */
    @FXML
    void initialize() {
        // Inicializar ComboBox con tipos de cuenta
        comboTipoCuenta.setItems(FXCollections.observableArrayList("Débito", "Crédito"));

        // Configurar columnas de la tabla principal de cuentas (tablaCuentas)
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBanco.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numCuenta"));

        // Columna para mostrar tipo de cuenta (Débito o Crédito) según instancia
        colTipo.setCellFactory(column -> new TableCell<Cuenta, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Cuenta cuenta = (Cuenta) getTableRow().getItem();
                    if (cuenta instanceof CuentaDebito) {
                        setText("Débito");
                    } else if (cuenta instanceof CuentaCredito) {
                        setText("Crédito");
                    } else {
                        setText("Desconocido");
                    }
                }
            }
        });

        // Configurar columnas de la tabla de cuentas crédito (tablaCuentaCredito)
        colCreditoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCreditoInteres.setCellValueFactory(new PropertyValueFactory<>("tasaInteres"));
        colCreditoCupo.setCellValueFactory(new PropertyValueFactory<>("cupoDisponible"));

        // Configurar columnas de la tabla de transacciones (tablaTransacciones)
        colTransId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTransDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colTransFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTransMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colTransOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        colTransDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        // Asignar las listas observables ya inicializadas a cada tabla
        tablaCuentas.setItems(listaCuentas);
        tablaCuentaCredito.setItems(listaCuentasCredito);
        tablaTransacciones.setItems(listaTransacciones);

        // Evento para mostrar/ocultar campos según el tipo de cuenta seleccionado
        comboTipoCuenta.setOnAction(event -> mostrarCamposPorTipo(comboTipoCuenta.getValue()));

        // Seleccionar el primer ítem del ComboBox e inicializar los campos visibles
        if (!comboTipoCuenta.getItems().isEmpty()) {
            comboTipoCuenta.getSelectionModel().selectFirst();
            mostrarCamposPorTipo(comboTipoCuenta.getValue());
        }

        // Validaciones para asegurar que las referencias FXML fueron inyectadas correctamente
        assert boxCredito != null : "fx:id=\"boxCredito\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert boxDebito != null : "fx:id=\"boxDebito\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnBuscarCredito != null : "fx:id=\"btnBuscarCredito\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnBuscarTransaccion != null : "fx:id=\"btnBuscarTransaccion\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnCambiarCupo != null : "fx:id=\"btnCambiarCupo\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnCambiarInteres != null : "fx:id=\"btnCambiarInteres\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnCerrarSesion != null : "fx:id=\"btnCerrarSesion\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnCerrarSesion1 != null : "fx:id=\"btnCerrarSesion1\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnCerrarSesion2 != null : "fx:id=\"btnCerrarSesion2\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnGenerarReporte != null : "fx:id=\"btnGenerarReporte\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnRecargarCredito != null : "fx:id=\"btnRecargarCredito\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert btnRecargarTransaccion != null : "fx:id=\"btnRecargarTransaccion\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colBanco != null : "fx:id=\"colBanco\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colCreditoCupo != null : "fx:id=\"colCreditoCupo\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colCreditoId != null : "fx:id=\"colCreditoId\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colCreditoInteres != null : "fx:id=\"colCreditoInteres\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colNumero != null : "fx:id=\"colNumero\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransDescripcion != null : "fx:id=\"colTransDescripcion\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransDestino != null : "fx:id=\"colTransDestino\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransFecha != null : "fx:id=\"colTransFecha\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransId != null : "fx:id=\"colTransId\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransMonto != null : "fx:id=\"colTransMonto\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colTransOrigen != null : "fx:id=\"colTransOrigen\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert colUsuario != null : "fx:id=\"colUsuario\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert comboTipoCuenta != null : "fx:id=\"comboTipoCuenta\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert tablaCuentaCredito != null : "fx:id=\"tablaCuentaCredito\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert tablaCuentas != null : "fx:id=\"tablaCuentas\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert tablaTransacciones != null : "fx:id=\"tablaTransacciones\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtBuscarId != null : "fx:id=\"txtBuscarId\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtBuscarTransaccion != null : "fx:id=\"txtBuscarTransaccion\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtCupoDisponible != null : "fx:id=\"txtCupoDisponible\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtCupoEnUso != null : "fx:id=\"txtCupoEnUso\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtCupoModificar != null : "fx:id=\"txtCupoModificar\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtInteresModificar != null : "fx:id=\"txtInteresModificar\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtNombreBanco != null : "fx:id=\"txtNombreBanco\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtNumCuenta != null : "fx:id=\"txtNumCuenta\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtSaldo != null : "fx:id=\"txtSaldo\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtTasaInteres != null : "fx:id=\"txtTasaInteres\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'GestionCuentaAdministrador.fxml'.";
    }

    /*
        Método para mostrar u ocultar campos según el tipo de cuenta seleccionado
        Se llama al seleccionar un tipo de cuenta en el ComboBox
        @param tipoCuenta Tipo de cuenta seleccionado
     */
    private void mostrarCamposPorTipo(String tipoCuenta) {
        if ("Débito".equals(tipoCuenta)) {
            boxDebito.setVisible(true);
            boxCredito.setVisible(false);
        } else if ("Crédito".equals(tipoCuenta)) {
            boxDebito.setVisible(false);
            boxCredito.setVisible(true);
        } else {
            boxDebito.setVisible(false);
            boxCredito.setVisible(false);
        }
    }

}
