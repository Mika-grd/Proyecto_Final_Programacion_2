package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Administrador;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.BilleteraVirtual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GestionAdministrador {

    private BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Administrador> administradoresTabla;

    @FXML
    private Button agregarAdministradorBoton;

    @FXML
    private Button buscarBoton;

    @FXML
    private TextField busquedaCampo;

    @FXML
    private TextField cedulaCampo;

    @FXML
    private TableColumn<Administrador, String> cedulaColumna;

    @FXML
    private TextField correoCampo;

    @FXML
    private TableColumn<Administrador, String> correoColumna;

    @FXML
    private Button editarAdministradorBoton;

    @FXML
    private Button eliminarAdministradorBoton;

    @FXML
    private TextField nombreCampo;

    @FXML
    private TableColumn<Administrador, String> nombreColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private TextField telefonoCampo;

    @FXML
    private TableColumn<Administrador, String> telefonoColumna;

    @FXML
    private Button volverBoton;

    @FXML
    void agregarAdministrador(ActionEvent event) {
            if (nombreCampo.getText().isEmpty() ||
                    cedulaCampo.getText().isEmpty()) {

                // Mostrar alerta si algún campo está vacío
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error al añadir Administrador");
                alerta.setHeaderText(null);
                alerta.setContentText("Todos los campos son obligatorios. ¡Por favor, complétalos!");
                alerta.showAndWait();

            } else {
                // Crear y añadir medico
                Administrador administrador = new Administrador(
                        nombreCampo.getText(),
                        cedulaCampo.getText(),
                        correoCampo.getText(),
                        telefonoCampo.getText());
                billetera.agregarObjeto(administrador,billetera.getListaPersonas());

                // Mostrar mensaje de éxito (opcional)
                Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
                confirmacion.setTitle("Administrador añadido");
                confirmacion.setHeaderText(null);
                confirmacion.setContentText("El Administrador ha sido añadido con éxito.");
                confirmacion.showAndWait();
            }
    }

    @FXML
    void buscarAccion(ActionEvent event) {

    }

    @FXML
    void editarAdministrador(ActionEvent event) {

    }

    @FXML
    void eliminarAdministrador(ActionEvent event) {
        Administrador administrador = administradoresTabla.getSelectionModel().getSelectedItem();

        if (administrador != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("Eliminar Administrador");
            alerta.setContentText("¿Estás seguro de que deseas eliminar al usuario?");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                billetera.eliminarObjeto(administrador, billetera.getListaPersonas());

            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecciona un admin para eliminar.");
            alerta.showAndWait();
        }
    }

    @FXML
    void recargarAccion(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {
        // Obtener la ventana actual y cerrarla
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
    }

    @FXML
    void initialize() {
        assert administradoresTabla != null : "fx:id=\"administradoresTabla\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert agregarAdministradorBoton != null : "fx:id=\"agregarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert buscarBoton != null : "fx:id=\"buscarBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert busquedaCampo != null : "fx:id=\"busquedaCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoCampo != null : "fx:id=\"correoCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoColumna != null : "fx:id=\"correoColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert editarAdministradorBoton != null : "fx:id=\"editarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert eliminarAdministradorBoton != null : "fx:id=\"eliminarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert telefonoCampo != null : "fx:id=\"telefonoCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";

    }
}