package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Categoria;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.CuentaDebito;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Presupuesto;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionarCategoriaController {

    @FXML
    private TextField txtIdCategoria;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private TextField txtDescripcionCategoria;

    @FXML
    private Button btnAgregarCategoria;

    @FXML
    private Button btnEliminarCategoria;

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

    private ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    private CuentaDebito cuentaDebito;

    private final CuentaCategoriasBuilder builder = new CuentaCategoriasBuilder();

    //Inicializa el controlador
    @FXML
    public void initialize() {
        // Configurar columnas
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tablaCategorias.setItems(listaCategorias);

        // Cuenta de ejemplo (reemplaza con tu lógica real)
        cuentaDebito = new CuentaDebito("ID1", "Banco Ejemplo", 123456, null, 1000);
        builder.setCuentaDebito(cuentaDebito);

        actualizarTabla();

        assert txtIdCategoria != null : "fx:id=\"txtIdCategoria\" was not injected: check your FXML file.";
        assert txtNombreCategoria != null : "fx:id=\"txtNombreCategoria\" was not injected: check your FXML file.";
        assert txtDescripcionCategoria != null : "fx:id=\"txtDescripcionCategoria\" was not injected: check your FXML file.";
        assert btnAgregarCategoria != null : "fx:id=\"btnAgregarCategoria\" was not injected: check your FXML file.";
        assert btnEliminarCategoria != null : "fx:id=\"btnEliminarCategoria\" was not injected: check your FXML file.";
        assert btnActualizarTabla != null : "fx:id=\"btnActualizarTabla\" was not injected: check your FXML file.";
        assert tablaCategorias != null : "fx:id=\"tablaCategorias\" was not injected: check your FXML file.";
        assert colIdCategoria != null : "fx:id=\"colIdCategoria\" was not injected: check your FXML file.";
        assert colNombre != null : "fx:id=\"colNombre\" was not injected: check your FXML file.";
        assert colDescripcion != null : "fx:id=\"colDescripcion\" was not injected: check your FXML file.";
    }

    @FXML
    private void agregarCategoria() {
        String id = txtIdCategoria.getText().trim();
        String nombre = txtNombreCategoria.getText().trim();
        String descripcion = txtDescripcionCategoria.getText().trim();
        Presupuesto presupuesto = new Presupuesto(id, nombre,0);

        if (!id.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty()) {
            Categoria categoria = new Categoria(id, nombre, descripcion,new Presupuesto(id,nombre, presupuesto.getMontoActual()));
            builder.añadirCategoria(categoria);
            actualizarTabla();
            limpiarCampos();
        } else {
            System.out.println("Por favor completa todos los campos.");
        }
    }

    @FXML
    private void eliminarCategoria() {
        Categoria seleccionada = tablaCategorias.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            cuentaDebito.getListaCategorias().remove(seleccionada);
            actualizarTabla();
        } else {
            System.out.println("Selecciona una categoría para eliminar.");
        }
    }

    @FXML
    private void actualizarTabla() {
        listaCategorias.setAll(cuentaDebito.getListaCategorias());
    }

    // Metodo para limpiar los campos de texto
    private void limpiarCampos() {
        txtIdCategoria.clear();
        txtNombreCategoria.clear();
        txtDescripcionCategoria.clear();
    }


}


