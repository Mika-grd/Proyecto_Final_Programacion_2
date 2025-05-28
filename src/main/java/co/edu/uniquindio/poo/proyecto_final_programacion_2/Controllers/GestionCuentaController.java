package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCreditoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaDebitoBuilder;
import javafx.beans.property.SimpleStringProperty;
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
    private Button btnActualizar;

    @FXML
    private Button botonReporte;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Cuenta, String> colBanco;

    @FXML
    private TableColumn<Cuenta, String> colId;

    @FXML
    private TableColumn<Cuenta, String> colNumero;

    @FXML
    private TableColumn<Cuenta, String> colTipo;

    @FXML
    private TableView<Cuenta> tablaCuentas;

    @FXML
    private ComboBox<String> comboTipoCuenta;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombreBanco;

    @FXML
    private TextField txtNumCuenta;

    @FXML
    private Button SeleccionarBoton;

    //Metodo para cambiar a la vista de reporte dependiendo del tipo de cuenta que sea
    @FXML
    private void ReporteAccion(ActionEvent event) {
        Cuenta cuentaSeleccionada = tablaCuentas.getSelectionModel().getSelectedItem();

        Sesion.getInstancia().setCuentaSeleccionada(cuentaSeleccionada);

        if (cuentaSeleccionada == null) {
            mostrarAlerta("Por favor seleccione una cuenta.");
            return;
        }

        CuentaDTO cuentaDto = cuentaSeleccionada.getCuentaDto();

        if (cuentaDto == null) {
            mostrarAlerta("La cuenta seleccionada no tiene datos asociados (CuentaDto es null).");
            return;
        }

        String tipoCuenta = cuentaDto.getTipoCuenta();

        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            Stage stage = new Stage();
            Scene scene;

            if (tipoCuenta.equalsIgnoreCase("credito")) {
                loader.setLocation(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/ReporteUsuarioCredito.fxml"));
                root = loader.load();
                stage.setTitle("Reporte Cuenta Crédito");
            } else if (tipoCuenta.equalsIgnoreCase("debito")) {
                loader.setLocation(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/ReporteUsuarioDebito.fxml"));
                root = loader.load();
                stage.setTitle("Reporte Cuenta Débito");
            } else {
                mostrarAlerta("Tipo de cuenta no reconocido.");
                return;
            }

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista del reporte.");
        }
    }

    //Metodo para seleccionar una cuenta de la tabla y cargar la vista de gestión de usuario
    @FXML
    private void seleccionarAccion(ActionEvent event) {
        Sesion sesion = Sesion.getInstancia();
        Cuenta cuentaSeleccionada = tablaCuentas.getSelectionModel().getSelectedItem();

        if (cuentaSeleccionada != null) {
            if (cuentaSeleccionada instanceof CuentaDebito) {
                sesion.setCuentaDebito((CuentaDebito) cuentaSeleccionada);
            }
            if (cuentaSeleccionada instanceof CuentaCredito) {
                sesion.setCuentaSeleccionada((CuentaCredito) cuentaSeleccionada);
            }
        }

        sesion.setCuentaSeleccionada(cuentaSeleccionada);

        CuentaDTO dto = cuentaSeleccionada.getCuentaDto();
        String mensaje = "Cuenta seleccionada: " + dto.getNombreUsuario() + " " + dto.getTipoCuenta() + " " + dto.getNumeroCuenta() + " " + dto.getBanco();

        mostrarAlerta(mensaje);

        if (cuentaSeleccionada instanceof CuentaDebito) {
            try {
                // Cargar el archivo FXML de la nueva pantalla
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));
                Parent root = loader.load();

                // Obtener el stage desde cualquier nodo (como un botón)
                Stage stage = (Stage) SeleccionarBoton.getScene().getWindow();

                // Crear una nueva escena con el contenido cargado
                Scene scene = new Scene(root);

                // Establecer la nueva escena en la ventana actual
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                mensaje = "No se pudo cargar la vista";
                mostrarAlerta(mensaje);
            }
        } else if (cuentaSeleccionada instanceof CuentaCredito) {
            try {
                // Cargar el archivo FXML de la nueva pantalla
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/ConsultarSaldoCredito.fxml"));
                Parent root = loader.load();

                // Obtener el stage desde cualquier nodo (como un botón)
                Stage stage = (Stage) SeleccionarBoton.getScene().getWindow();

                // Crear una nueva escena con el contenido cargado
                Scene scene = new Scene(root);

                // Establecer la nueva escena en la ventana actual
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                mensaje = "No se pudo cargar la vista";
                mostrarAlerta(mensaje);
            }
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
            // Se puede agregar un campo para saldo si es necesario
        } else if ("Crédito".equals(tipo)) {
            // Se pueden agregar campos para tasa, cupo disponible y en uso si es necesario
        } else {
            // Para cualquier otro tipo
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


        if (billeteraVirtual.buscarObjeto(id, billeteraVirtual.getListaCuentas()) != null) {
            mostrarAlerta("La cuenta con este id ya existe");
            return;
        }
        int numCuenta;

        try {
            numCuenta = Integer.parseInt(txtNumCuenta.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Número de cuenta inválido.");
            return;
        }

        if (id.isEmpty() || banco.isEmpty()) {
            mostrarAlerta("Debe completar todos los campos.");
            return;
        }

        String tipoCuentaSeleccionada = comboTipoCuenta.getValue();
        Cuenta cuenta = null;
        Usuario usuario = Sesion.getInstancia().getUsuario();

        if ("Débito".equals(tipoCuentaSeleccionada)) {
            cuenta = new CuentaDebito(id, banco, numCuenta,usuario ,0); // Puedes agregar el saldo de la cuenta si es necesario
        } else if ("Crédito".equals(tipoCuentaSeleccionada)) {
            cuenta = new CuentaCredito(id, banco, numCuenta, usuario, 0,0,0); // Añade tasas y otros atributos si es necesario
        }

        if (cuenta != null) {
            billeteraVirtual.getListaCuentas().add(cuenta);
            usuario.getListaCuentas().add(cuenta);
            actualizarLista();
            limpiarCampos();
            System.out.println(usuario.getListaCuentas());
        } else {
            mostrarAlerta("Debe seleccionar un tipo de cuenta.");
        }
    }


    /**
     * Elimina la cuenta actualmente seleccionada en la tabla.
     * Si no hay ninguna cuenta seleccionada, se muestra una alerta al usuario.
     */
    @FXML
    private void eliminarCuentaAccion(ActionEvent event) {
        Cuenta seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            billeteraVirtual.getListaCuentas().remove(seleccionada);
            Sesion.getInstancia().getUsuario().getListaCuentas().remove(seleccionada);// Eliminar de la lista de negocio
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
        actualizarLista();
    }

    //Actualiza la lista de cuentas en la tabla
    private void actualizarLista() {
        listaCuentas.setAll(Sesion.getInstancia().getUsuario().getListaCuentas());
        tablaCuentas.setItems(listaCuentas);
        tablaCuentas.refresh();
    }

    /**
     * Limpia todos los campos del formulario y oculta los cuadros de campos específicos
     * según el tipo de cuenta seleccionada (Débito/Crédito).
     */
    private void limpiarCampos() {
        txtId.clear();
        txtNombreBanco.clear();
        txtNumCuenta.clear();
        comboTipoCuenta.setValue(null);
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

        assert SeleccionarBoton != null : "fx:id=\"SeleccionarBoton\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colBanco != null : "fx:id=\"colBanco\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colNumero != null : "fx:id=\"colNumero\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert comboTipoCuenta != null : "fx:id=\"comboTipoCuenta\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert tablaCuentas != null : "fx:id=\"tablaCuentas\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtNombreBanco != null : "fx:id=\"txtNombreBanco\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
        assert txtNumCuenta != null : "fx:id=\"txtNumCuenta\" was not injected: check your FXML file 'GestionCuenta.fxml'.";
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
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
}
