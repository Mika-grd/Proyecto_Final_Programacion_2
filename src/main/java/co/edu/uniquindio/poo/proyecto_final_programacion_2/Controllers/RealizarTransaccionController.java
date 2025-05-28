package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RealizarTransaccionController {

    private Sesion sesion = Sesion.getInstancia();
    CuentaDebito cuentaActual = Sesion.getInstancia().getCuentaDebito();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Categoria> CategoriaCombo;

    @FXML
    private Button añadirTransaccionBoton;

    @FXML
    private Button clonarBoton;

    @FXML
    private TableColumn<String, Transaccion> descripcionColumna;

    @FXML
    private TableColumn<LocalDate, Transaccion> fechaColumna;

    @FXML
    private TableView<Transaccion> historialTable;

    @FXML
    private TableColumn<String, Transaccion> idColumna;

    @FXML
    private TableColumn<Double, Transaccion> montoColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdCuenta;

    @FXML
    private TextField txtMonto;

    @FXML
    private Button volverBoton;


    private void cargarTabla() {
        List<Transaccion> transaccionesFiltradas = sesion.getCuentaDebito().getListaTransaccion().stream()
                .filter(t -> t.getCuentaObjetivoDebito() != null)
                .collect(Collectors.toList());

        historialTable.getItems().setAll(transaccionesFiltradas);
    }


    /**
     * Maneja el evento de añadir una nueva transacción desde la interfaz gráfica.
     *
     * Esta función:
     * - Valida los campos del formulario.
     * - Busca la cuenta y el usuario objetivo.
     * - Determina el monto disponible según la categoría seleccionada.
     * - Crea y realiza la transacción si los datos son válidos.
     * - Agrega la transacción a la lista del usuario actual.
     * - Muestra mensajes al usuario con el resultado de la operación.
     *
     * @param event Evento que se dispara al presionar el botón de "Añadir".
     */
    @FXML
    void añadirTransaccionAccion(ActionEvent event){
        BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();


        String id = txtId.getText();
        String idCuenta = txtIdCuenta.getText();
        LocalDate fecha = LocalDate.now();
        String descripcion = txtDescripcion.getText();
        Categoria categoria = CategoriaCombo.getValue();

        if (billeteraVirtual.buscarObjeto(idCuenta, sesion.getUsuario().getListaCuentas()) == sesion.getCuentaDebito()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("Error");
            alerta.setContentText("No se puede enviar dinero a la misma cuenta.");
            alerta.showAndWait();
            return;
        }



        double monto;
        try {
            monto = Double.parseDouble(txtMonto.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El monto debe ser un número válido.", Alert.AlertType.ERROR);
            return;
        }

        if (id.isEmpty() || fecha == null || descripcion.isEmpty() || monto <= 0) {
            mostrarAlerta("Campos incompletos", "Debe llenar todos los campos y el monto debe ser mayor a 0.", Alert.AlertType.WARNING);
            return;
        }

        CuentaDebito cuentaObjetivo = (CuentaDebito) billeteraVirtual.buscarObjeto(txtIdCuenta.getText(), billeteraVirtual.getListaCuentas());
        if (cuentaObjetivo == null) {
            mostrarAlerta("Cuenta no encontrada", "No se encontró la cuenta con ID: " + txtIdCuenta.getText(), Alert.AlertType.ERROR);
            return;
        }


        double montoDisponible = 0;
        if (categoria != null) {
            montoDisponible = categoria.getPresupuesto().getMontoActual();
        } else {
            montoDisponible = sesion.getCuentaDebito().getSaldo();
        }

        Transaccion nuevaTransaccion = new Transaccion(id, fecha, monto, descripcion, sesion.getCuentaDebito(), cuentaObjetivo);

        if (nuevaTransaccion.realizarTransaccion()) {

            mostrarAlerta("Éxito", "Transacción agregada exitosamente:\n" +
                    "Saldo actual: " + sesion.getCuentaDebito().getSaldo() + "\n" +
                    "Monto transferido: " + monto, Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error de transacción", "No fue posible realizar la transacción. Verifique los fondos disponibles.", Alert.AlertType.ERROR);
        }
        cargarTabla();
    }


    /**
     * Muestra una ventana emergente (popup) con un mensaje al usuario.
     *
     * @param titulo   El título de la ventana de alerta.
     * @param contenido  El mensaje que se desea mostrar dentro del popup.
     * @param tipo     El tipo de alerta (información, advertencia, error, etc.).
     */
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    /**
     *
     * Carga la pantalla anterior  y la muestra en la misma ventana actual.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));

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
    void clonarAccion(ActionEvent event) {
        // Obtener la transacción seleccionada de la tabla
        Transaccion transaccionSeleccionada = historialTable.getSelectionModel().getSelectedItem();

        if (transaccionSeleccionada.getCuentaObjetivoDebito() == sesion.getCuentaDebito()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("Error");
            alerta.setContentText("No se puede enviar dinero a la misma cuenta.");
            alerta.showAndWait();
            return;
        }

        // Validar que se haya seleccionado una transacción
        if (transaccionSeleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("Selección requerida");
            alerta.setContentText("Por favor, selecciona una transacción antes de clonar.");
            alerta.showAndWait();
            return;
        }

        // Confirmación antes de clonar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("Clonar Transacción");
        confirmacion.setContentText("¿Estás seguro de que deseas clonar esta transacción?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Clonar la transacción
            Transaccion clon = transaccionSeleccionada.clone();

            // Establecer fecha actual
            clon.setFechaTransaccion(LocalDate.now());

            double monto;
            try {
                monto = Double.parseDouble(txtMonto.getText());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error de formato", "El monto debe ser un número válido.", Alert.AlertType.ERROR);
                return;
            }

            clon.setMontoATransferir(monto);


            if (txtDescripcion.getText().isEmpty()) {
                clon.setDescripcion("Sin descripcion");
            } else {
                clon.setDescripcion(txtDescripcion.getText());
            }

            // Realizar la transacción clonada
            if (clon.realizarTransaccion()) {

                // Mostrar éxito
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Éxito");
                exito.setHeaderText("Transacción Clonada");
                exito.setContentText("La transacción ha sido clonada exitosamente.");
                exito.showAndWait();

                // Recargar tabla
                cargarTabla();
            } else {
                // Error al intentar realizar la transacción
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Transacción Fallida");
                error.setContentText("No fue posible realizar la transacción clonada. Verifique los fondos.");
                error.showAndWait();
            }
        }
    }

    
    @FXML
    void recargarAccion(ActionEvent event) {
        cargarTabla();
    }

    @FXML
    void initialize() {

            ObservableList<Categoria> categorias = FXCollections.observableArrayList(
                    sesion.getCuentaSeleccionada().getListaCategorias()
            );
            CategoriaCombo.setItems(categorias);

    // Configurar columnas
            idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
            descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaTransaccion"));
            montoColumna.setCellValueFactory(new PropertyValueFactory<>("montoATransferir"));

    // Filtrar transacciones: solo mostrar las que tienen cuentaObjetivoDebito != null
            List<Transaccion> transaccionesFiltradas = sesion.getCuentaDebito().getListaTransaccion().stream()
                    .filter(t -> t.getCuentaObjetivoDebito() != null)
                    .collect(Collectors.toList());

            ObservableList<Transaccion> historial = FXCollections.observableArrayList(transaccionesFiltradas);
            historialTable.setItems(historial);

        assert CategoriaCombo != null : "fx:id=\"CategoriaCombo\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert añadirTransaccionBoton != null : "fx:id=\"añadirTransaccionBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert clonarBoton != null : "fx:id=\"clonarBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert descripcionColumna != null : "fx:id=\"descripcionColumna\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert historialTable != null : "fx:id=\"historialTable\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert idColumna != null : "fx:id=\"idColumna\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtIdCuenta != null : "fx:id=\"txtIdCuenta\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtMonto != null : "fx:id=\"txtMonto\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
    }



}

