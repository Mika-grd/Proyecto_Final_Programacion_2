package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class RealizarTransaccionController {

    private Sesion sesion = Sesion.getInstancia();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Categoria> CategoriaCombo;

    @FXML
    private Button añadirTransaccionBoton;

    @FXML
    private DatePicker fechaTransaccion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdCuenta;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtTitular;

    @FXML
    private Button volverBoton;

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
    void añadirEmpleadoAccion(ActionEvent event){
        BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();

        String id = txtId.getText();
        LocalDate fecha = fechaTransaccion.getValue();
        String descripcion = txtDescripcion.getText();
        Categoria categoria = CategoriaCombo.getValue();

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

        Usuario usuarioObjetivo = (Usuario) billeteraVirtual.buscarObjeto(txtTitular.getText(), billeteraVirtual.getListaPersonas());
        if (usuarioObjetivo == null) {
            mostrarAlerta("Usuario no encontrado", "No se encontró el usuario con ID: " + txtTitular.getText(), Alert.AlertType.ERROR);
            return;
        }

        double montoDisponible = 0;
        if (categoria != null) {
            montoDisponible = categoria.getPresupuesto().getMontoActual();
        } else {
            montoDisponible = sesion.getCuentaDebito().getSaldo();
        }

        Transaccion nuevaTransaccion = new Transaccion(id, fecha, monto, montoDisponible, descripcion, sesion.getCuentaDebito(), cuentaObjetivo);

        if (nuevaTransaccion.realizarTransaccion()) {
            sesion.getUsuario().agregarObjeto(nuevaTransaccion, sesion.getCuentaDebito().getListaTransaccion());

            mostrarAlerta("Éxito", "Transacción agregada exitosamente:\n" +
                    "Objetivo: " + usuarioObjetivo.getNombre() + "\n" +
                    "Saldo actual: " + sesion.getCuentaDebito().getSaldo() + "\n" +
                    "Monto transferido: " + monto, Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error de transacción", "No fue posible realizar la transacción. Verifique los fondos disponibles.", Alert.AlertType.ERROR);
        }
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
    void initialize() {

        ObservableList<Categoria> categorias = FXCollections.observableArrayList(sesion.getCuentaSeleccionada().getListaCategorias());

        CategoriaCombo.setItems(categorias);


        assert CategoriaCombo != null : "fx:id=\"CategoriaCombo\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert añadirTransaccionBoton != null : "fx:id=\"añadirTransaccionBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert fechaTransaccion != null : "fx:id=\"fechaTransaccion\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtIdCuenta != null : "fx:id=\"txtIdCuenta\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtMonto != null : "fx:id=\"txtMonto\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert txtTitular != null : "fx:id=\"txtTitular\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'RealizarTransferencia.fxml'.";
    }



}

