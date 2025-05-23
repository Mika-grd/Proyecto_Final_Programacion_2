package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Categoria;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Presupuesto;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
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

import java.io.IOException;

public class GestionarCategoriaController {

    @FXML
    private Button atrasBoton;

    @FXML
    private TextField txtIdCategoria;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private TextField txtDescripcionCategoria;

    @FXML
    private Button btnAgregarCategoria;


    @FXML
    private Button btnActualizarTabla;

    @FXML
    private TableView<Categoria> tablaCategorias;

    @FXML
    private TableColumn<Categoria, String> colIdCategoria;

    @FXML
    private TableColumn<Categoria, String> colNombre;

    @FXML
    private TableColumn<Categoria, String> colDescripcion;

    @FXML
    private Button liberarBoton;


    Sesion sesion = Sesion.getInstancia();
    private ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList(sesion.getCuentaSeleccionada().getListaCategorias());



    private CuentaDebito cuentaDebito;




    @FXML
    private void agregarCategoria(ActionEvent event) {
        String id = txtIdCategoria.getText().trim();
        String nombre = txtNombreCategoria.getText().trim();
        String descripcion = txtDescripcionCategoria.getText().trim();
        Presupuesto presupuesto = new Presupuesto(id, nombre,0);

        if (!id.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty()) {
            Categoria categoria = new Categoria(id, nombre, descripcion,new Presupuesto(id,nombre, presupuesto.getMontoActual()));
            sesion.getCuentaSeleccionada().getListaCategorias().add(categoria);
            actualizarTabla();
            limpiarCampos();
        } else {
            System.out.println("Por favor completa todos los campos.");
        }
    }

    @FXML
    private void atrasAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) atrasBoton.getScene().getWindow();

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
    private void actualizarAccion(ActionEvent event) {
        actualizarTabla();
    }

    @FXML
    private void actualizarTabla() {
        listaCategorias.setAll(sesion.getCuentaSeleccionada().getListaCategorias());
    }

    // Metodo para limpiar los campos de texto
    private void limpiarCampos() {
        txtIdCategoria.clear();
        txtNombreCategoria.clear();
        txtDescripcionCategoria.clear();
    }

    @FXML
    void liberarAccion(ActionEvent event) {
        Categoria categoriaSeleccionada =  tablaCategorias.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada != null) {
            CuentaDebito cuentaDebito = sesion.getCuentaDebito();
            cuentaDebito.getListaCategorias().remove(categoriaSeleccionada);
            cuentaDebito.setSaldo(cuentaDebito.getSaldo() + categoriaSeleccionada.getPresupuesto().getMontoActual());

            actualizarTabla();
            mostrarAlerta("Exitoso", "Categoria eliminada correctamente", Alert.AlertType.CONFIRMATION);
        }
        else {
            mostrarAlerta("Categoria sin seleccionar", "Debe seleccionar una categoria para liberar", Alert.AlertType.ERROR);
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


    //Inicializa el controlador
    @FXML
    public void initialize() {
        // Configurar columnas
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tablaCategorias.setItems(listaCategorias);


        actualizarTabla();

        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'GestionarCategorias.fxml'.";

        assert txtIdCategoria != null : "fx:id=\"txtIdCategoria\" was not injected: check your FXML file.";
        assert txtNombreCategoria != null : "fx:id=\"txtNombreCategoria\" was not injected: check your FXML file.";
        assert txtDescripcionCategoria != null : "fx:id=\"txtDescripcionCategoria\" was not injected: check your FXML file.";
        assert btnAgregarCategoria != null : "fx:id=\"btnAgregarCategoria\" was not injected: check your FXML file.";
        assert btnActualizarTabla != null : "fx:id=\"btnActualizarTabla\" was not injected: check your FXML file.";
        assert tablaCategorias != null : "fx:id=\"tablaCategorias\" was not injected: check your FXML file.";
        assert colIdCategoria != null : "fx:id=\"colIdCategoria\" was not injected: check your FXML file.";
        assert colNombre != null : "fx:id=\"colNombre\" was not injected: check your FXML file.";
        assert colDescripcion != null : "fx:id=\"colDescripcion\" was not injected: check your FXML file.";
        assert liberarBoton != null : "fx:id=\"liberarBoton\" was not injected: check your FXML file 'GestionarCategorias.fxml'.";

    }




}


