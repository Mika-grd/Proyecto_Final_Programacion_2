package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Administrador;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.BilleteraVirtual;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Persona;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GestionarUsuarioAdminController {

    private BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agregarAdministradorBoton;

    @FXML
    private Button agregarUsuarioBoton;

    @FXML
    private Button buscarBoton;

    @FXML
    private TextField busquedaCampo;

    @FXML
    private TextField cedulaCampo;

    @FXML
    private TableColumn<Persona, String > cedulaColumna;

    @FXML
    private TextField contraseñaCampo;

    @FXML
    private TextField correoCampo;

    @FXML
    private TableColumn<Persona, String> correoColumna;

    @FXML
    private Button editarBoton;

    @FXML
    private Button eliminarBoton;

    @FXML
    private TextField nombreCampo;

    @FXML
    private TableColumn<Persona, String> nombreColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private TableColumn<Persona, String> rolColumna;

    @FXML
    private Button SeleccionarBoton;

    @FXML
    private TextField telefonoCampo;

    @FXML
    private TableColumn<Persona, String> telefonoColumna;

    @FXML
    private TableView<Persona> usuariosAdminTabla;

    @FXML
    private Button volverBoton;


    private void cargarTabla() {
        usuariosAdminTabla.getItems().setAll(billetera.getListaPersonas());
    }



    @FXML
    void agregarAdministradorAccion(ActionEvent event) {
        if (nombreCampo.getText().isEmpty() ||
                cedulaCampo.getText().isEmpty()) {

            // Mostrar alerta si algún campo está vacío
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error al añadir Administrador");
            alerta.setHeaderText(null);
            alerta.setContentText("Todos los campos son obligatorios. ¡Por favor, complétalos!");
            alerta.showAndWait();

        } else {
            // Crear y añadir un administrador
            Administrador administrador = new Administrador(
                    nombreCampo.getText(),
                    cedulaCampo.getText(),
                    correoCampo.getText(),
                    telefonoCampo.getText(),
                    contraseñaCampo.getText());
            billetera.agregarObjeto(administrador,billetera.getListaPersonas());

            // Mostrar mensaje de éxito (opcional)
            Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
            confirmacion.setTitle("Administrador añadido");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("El Administrador ha sido añadido con éxito.");
            confirmacion.showAndWait();
        }
        cargarTabla();
    }

    @FXML
    void agregarUsuarioAccion(ActionEvent event) {
        if (nombreCampo.getText().isEmpty() ||
                cedulaCampo.getText().isEmpty()) {

            // Mostrar alerta si algún campo está vacío
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error al añadir el Usuario");
            alerta.setHeaderText(null);
            alerta.setContentText("Todos los campos son obligatorios. ¡Por favor, complétalos!");
            alerta.showAndWait();

        } else {
            // Crear y añadir un usuario
            Usuario usuario = new Usuario(
                    nombreCampo.getText(),
                    cedulaCampo.getText(),
                    correoCampo.getText(),
                    telefonoCampo.getText(),
                    contraseñaCampo.getText());
            billetera.agregarObjeto(usuario,billetera.getListaPersonas());

            // Mostrar mensaje de éxito (opcional)
            Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
            confirmacion.setTitle("Usuario añadido");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("El Usuario ha sido añadido con éxito.");
            confirmacion.showAndWait();
        }
        cargarTabla();
    }

    @FXML
    void buscarAccion(ActionEvent event) {
        String id = busquedaCampo.getText();

        if (id == null || id.trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campo vacío");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor ingrese un ID para buscar.");
            alerta.showAndWait();
            return;
        }

        Persona personaEncontrada = billetera.buscarObjeto(id, billetera.getListaPersonas());

        if (personaEncontrada != null) {
            usuariosAdminTabla.getSelectionModel().clearSelection();
            usuariosAdminTabla.getSelectionModel().select(personaEncontrada);
            usuariosAdminTabla.scrollTo(personaEncontrada);
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("No encontrado");
            alerta.setHeaderText(null);
            alerta.setContentText("No se encontró ninguna persona con el ID proporcionado.");
            alerta.showAndWait();
        }
    }

    @FXML
    void editarAccion(ActionEvent event) {
        Persona personaSeleccionada = usuariosAdminTabla.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Debes seleccionar una persona en la tabla para editar.");
            alerta.showAndWait();
            return;
        }

        // Validar que los campos no estén vacíos
        if (nombreCampo.getText().isEmpty() || cedulaCampo.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Nombre y cédula son obligatorios.");
            alerta.showAndWait();
            return;
        }

        // Crear un nuevo objeto del mismo tipo que el seleccionado
        Persona personaEditada;

        if (personaSeleccionada instanceof Administrador) {
            personaEditada = new Administrador(
                    nombreCampo.getText(),
                    cedulaCampo.getText(),
                    correoCampo.getText(),
                    telefonoCampo.getText(),
                    contraseñaCampo.getText()
            );
        } else {
            personaEditada = new Usuario(
                    nombreCampo.getText(),
                    cedulaCampo.getText(),
                    correoCampo.getText(),
                    telefonoCampo.getText(),
                    contraseñaCampo.getText()
            );
        }

        // Editar usando el metodo generico
        String resultado = billetera.editarObjeto(personaSeleccionada, personaEditada, billetera.getListaPersonas());

        if (resultado.equals("Exitoso")) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Éxito");
            alerta.setHeaderText(null);
            alerta.setContentText("La persona fue editada correctamente.");
            alerta.showAndWait();
            cargarTabla(); // refresca la tabla
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Ocurrió un error al editar la persona.");
            alerta.showAndWait();
        }
    }

    @FXML
    void eliminarAccion(ActionEvent event) {
        Persona persona = usuariosAdminTabla.getSelectionModel().getSelectedItem();

        if (persona == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("No hay una persona seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Debes seleccionar una persona en la tabla.");
            alerta.showAndWait();
            return;
        }

        String resultado = billetera.eliminarObjeto(persona, billetera.getListaPersonas());

        if (resultado.equals("Exitoso")) {
            Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
            confirmacion.setTitle("Eliminación exitosa");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("La persona fue eliminada correctamente.");
            confirmacion.showAndWait();
            cargarTabla(); // Refresca la tabla
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error al eliminar");
            error.setHeaderText(null);
            error.setContentText("No se pudo eliminar la persona.");
            error.showAndWait();
        }
    }

    @FXML
    void recargarAccion(ActionEvent event) {
        cargarTabla();
    }

    @FXML
    void SeleccionarAccion(ActionEvent event) {
        Persona usuarioSeleccionado = usuariosAdminTabla.getSelectionModel().getSelectedItem();
        Sesion sesion = Sesion.getInstancia();

        if (usuarioSeleccionado != null) {
            sesion.getUsuario();


            // Mensaje opcional
            String mensaje = "Persona seleccionada: " + usuarioSeleccionado.getNombre() + " (" + usuarioSeleccionado.getId() + ")";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Selección realizada");
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionCuentaEspecifica.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) SeleccionarBoton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error de navegación");
                error.setHeaderText(null);
                error.setContentText("No se pudo cargar la vista de 'GestionarCuentaEspecifica'.");
                error.showAndWait();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Ninguna persona seleccionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Debes seleccionar una persona en la tabla.");
            alerta.showAndWait();
        }
    }

    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/vistaAdministrador.fxml"));

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

        nombreColumna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        cedulaColumna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));
        correoColumna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCorreo()));
        telefonoColumna.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelefono()));
        rolColumna.setCellValueFactory(cellData -> {
            Persona persona = cellData.getValue();
            String rol = (persona instanceof Administrador) ? "Admin" : "Usuario";
            return new javafx.beans.property.SimpleStringProperty(rol);
        });

        assert agregarAdministradorBoton != null : "fx:id=\"agregarAdministradorBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert agregarUsuarioBoton != null : "fx:id=\"agregarUsuarioBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert buscarBoton != null : "fx:id=\"buscarBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert busquedaCampo != null : "fx:id=\"busquedaCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert contraseñaCampo != null : "fx:id=\"contraseñaCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert correoCampo != null : "fx:id=\"correoCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert correoColumna != null : "fx:id=\"correoColumna\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert editarBoton != null : "fx:id=\"editarBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert eliminarBoton != null : "fx:id=\"eliminarBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert rolColumna != null : "fx:id=\"rolColumna\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert SeleccionarBoton != null : "fx:id=\"seleccionarBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert telefonoCampo != null : "fx:id=\"telefonoCampo\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert usuariosAdminTabla != null : "fx:id=\"usuariosAdminTabla\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionarUsuarioAdmin.fxml'.";

    }


}
