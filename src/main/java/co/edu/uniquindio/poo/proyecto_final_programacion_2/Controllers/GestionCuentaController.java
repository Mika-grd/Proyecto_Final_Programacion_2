package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;


import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TableColumn<Cuenta, String> colId;

    @FXML
    private TableColumn<Cuenta, String> colNumero;

    @FXML
    private TableColumn<Cuenta, String> colTipo;

    @FXML
    private TableColumn<Usuario, String> colUsuario;

    @FXML
    private TableView<Cuenta> tablaCuentas;


    @FXML
    private ComboBox<String> comboTipoCuenta;

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

    @FXML
    private Button SeleccionarBoton;



    @FXML
    void seleccionarAccion(ActionEvent event) {
        Sesion sesion = Sesion.getInstancia();
        Cuenta cuentaSeleccionada = tablaCuentas.getSelectionModel().getSelectedItem();

        if (cuentaSeleccionada != null) {
            if (cuentaSeleccionada instanceof CuentaDebito) {
                sesion.setCuentaDebito((CuentaDebito) cuentaSeleccionada);
            }
            if (cuentaSeleccionada instanceof CuentaCredito) {
                sesion.setCuentaSeleccionada( (CuentaCredito) cuentaSeleccionada );
            }
        }

        sesion.setCuentaSeleccionada(cuentaSeleccionada);

        CuentaDTO dto = new CuentaDTO(cuentaSeleccionada);
        String mensaje = "Cuenta seleccionada :" + dto.getNombreUsuario() + " " + dto.getTipoCuenta() + " " + dto.getNumeroCuenta() + " " + dto.getBanco();


        mostrarAlerta(mensaje);

        try {
            // Cargar el archivo FXML de la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtener el stage desde cualquier nodo (como un botón)
            Stage stage = (Stage) SeleccionarBoton.getScene().getWindow();

            // Crear una nueva escena con el contenido cargado
            Scene scene = new Scene(root);

            // Establecer la nueva escena en la ventana actual
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Mostrar el error si ocurre al cargar el archivo FXML
            e.printStackTrace();
            mensaje = "No se pudo cargar la vista";
        mostrarAlerta(mensaje);
        }
    }


    public BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();
    public ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList(Sesion.getInstancia().getUsuario().getListaCuentas());


    /**
     * Muestra campos específicos según el tipo de cuenta seleccionado en el ComboBox.
     * Si se selecciona "Débito", se muestra el campo de saldo.
     * Si se selecciona "Crédito", se muestran los campos de tasa, cupo disponible y en uso.
     */
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

    /**
     * Metodo invocado al presionar el botón "Agregar".
     * Permite crear una nueva cuenta de tipo Débito o Crédito según los datos ingresados por el usuario.
     * Se validan los campos necesarios y se muestra una alerta si algún dato es incorrecto.
     * Si los datos son válidos, la cuenta es añadida a la lista observable.
     */
    @FXML
    private void agregarCuentaAccion(ActionEvent event) {
        String id = txtId.getText();
        String banco = txtNombreBanco.getText();
        int numCuenta;
        try {
            numCuenta = Integer.parseInt(txtNumCuenta.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Número de cuenta inválido.");
            return;
        }

        Usuario usuario = (Usuario) billeteraVirtual.buscarObjeto(txtUsuario.getText(), billeteraVirtual.getListaPersonas());
        String tipo = comboTipoCuenta.getValue();

        if (id.isEmpty() || banco.isEmpty() || tipo == null) {
            mostrarAlerta("Debe completar todos los campos.");
            return;
        }
        if (usuario == null ){
            mostrarAlerta("Usuario no encontrado.");
        }

        Cuenta cuenta;

        if ("Débito".equals(tipo)) {
            try {
                double saldo = Double.parseDouble(txtSaldo.getText());
                cuenta = new CuentaDebito(id, banco, numCuenta, usuario, saldo);
                // Las categorías se asignarán después en otra parte
            } catch (NumberFormatException e) {
                mostrarAlerta("Saldo inválido.");
                return;
            }
        } else if ("Crédito".equals(tipo)) {
            try {
                double tasaInteres = Double.parseDouble(txtTasaInteres.getText());
                double cupoDisponible = Double.parseDouble(txtCupoDisponible.getText());
                double cupoEnUso = Double.parseDouble(txtCupoEnUso.getText());
                cuenta = new CuentaCredito(id, banco, numCuenta, usuario, tasaInteres, cupoDisponible, cupoEnUso);
            } catch (NumberFormatException e) {
                mostrarAlerta("Datos numéricos inválidos en crédito.");
                return;
            }
        } else {
            mostrarAlerta("Seleccione un tipo de cuenta válido.");
            return;
        }

        billeteraVirtual.getListaCuentas().add(cuenta);  // Añadir a la lista de negocio
        actualizarLista();  // Refrescar la lista observable que la tabla usa
        limpiarCampos();    // Limpiar los campos de entrada
    }


    /**
     * Elimina la cuenta actualmente seleccionada en la tabla.
     * Si no hay ninguna cuenta seleccionada, se muestra una alerta al usuario.
     */
    @FXML
    private void eliminarCuentaAccion(ActionEvent event) {
        Cuenta seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            billeteraVirtual.getListaCuentas().remove(seleccionada);  // Eliminar de la lista de negocio
            actualizarLista();  // Refrescar la lista observable
        } else {
            mostrarAlerta("Debe seleccionar una cuenta para eliminar.");
        }
    }

    /**
     * Actualiza la tabla de cuentas para reflejar cambios recientes.
     * Esta función se limita a refrescar visualmente los datos.
     */
    @FXML
    private void actualizarTablaAccion(ActionEvent event) {
        tablaCuentas.refresh();
    }

    private void actualizarLista() {
        listaCuentas.setAll(billeteraVirtual.getListaCuentas());  // Sincroniza con la lista real
        tablaCuentas.refresh();  // Refresca la tabla visualmente
    }

    /**
     * Limpia todos los campos del formulario y oculta los cuadros de campos específicos
     * según el tipo de cuenta seleccionada (Débito/Crédito).
     */
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
        boxDebito.setVisible(false);
        boxCredito.setVisible(false);

    }

    /**
     * Muestra una alerta de tipo WARNING con el mensaje especificado.
     * Se usa para notificar al usuario sobre errores o validaciones fallidas.
     *
     * @param mensaje Texto que se mostrará en la alerta.
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Metodo llamado automáticamente al inicializar el controlador.
     * Configura los componentes gráficos como el ComboBox, la tabla de cuentas y sus columnas,
     * y define eventos necesarios como la selección del tipo de cuenta.
     */
    @FXML
    void initialize() {


        // Configurar ComboBoxes
        comboTipoCuenta.setItems(FXCollections.observableArrayList("Débito", "Crédito"));

        // Configurar columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBanco.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numCuenta"));
        colUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(Sesion.getInstancia().getUsuario().getNombre()));
        colTipo.setCellFactory(column -> new TableCell<Cuenta, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    // Verificar si el objeto es de tipo CuentaDebito
                    if (getTableRow().getItem() instanceof CuentaDebito) {
                        setText("Débito");
                    } else {
                        setText("Crédito");
                    }
                }
            }
        });


        tablaCuentas.setItems(listaCuentas);

        // Manejador para mostrar campos dinámicamente
        comboTipoCuenta.setOnAction(event -> mostrarCamposPorTipo(comboTipoCuenta.getValue()));

        assert SeleccionarBoton != null : "fx:id=\"SeleccionarBoton\" was not injected: check your FXML file 'GestionarCategorias.fxml'.";
        assert boxCredito != null : "fx:id=\"boxCredito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert boxDebito != null : "fx:id=\"boxDebito\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
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
