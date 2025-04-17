package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.Sesion.Sesion;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.BilleteraVirtual;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Persona;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
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

public class GestionUsuarioController {

    private Sesion sesion = Sesion.getInstancia();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button atrasBoton;

    @FXML
    private TableColumn<Usuario, String> cedulaColumna;

    @FXML
    private TextField clienteContactoCampo;

    @FXML
    private TextField clienteCorreoCampo;

    @FXML
    private TextField clienteNombreCampo;

    @FXML
    private Button consultarSaldoBoton;

    @FXML
    private Button editarUsuarioBoton;

    @FXML
    private Button gestionarCategoriaBoton;

    @FXML
    private TableColumn<Usuario, String> nombreColumna;

    //Correo
    @FXML
    private TableColumn<Usuario, String> nombreColumna1;

    @FXML
    private Button realizarTransaccionesBoton;

    @FXML
    private Button recargarBoton;

    @FXML
    private TableColumn<Usuario, String> telefonoColumna;

    @FXML
    private TableView<Usuario> usuariosTabla;

    /**
     *
     * Carga la pantalla anterior  y la muestra en la misma ventana actual.
     *
     * @param event El evento de acción generado al hacer clic en el botón.
     */
    @FXML
    void atrasAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCuenta.fxml"));

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

    /**
     * abre la pantalla ConsultarSaldoTransacciones en la misma ventana
     * */
    @FXML
    void consultarSaldoAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsultarSaldoTransacciones.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) consultarSaldoBoton.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }

    /**
     * Edita el usuario seleccionado en la tabla y cambia los valores por los que se pongan abajo a la izquierda
     * */
    @FXML
    void editarUsuarioAccion(ActionEvent event) {
        String mensaje = "Debe Seleccionar un usuario";
        BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();
        Usuario usuario = usuariosTabla.getSelectionModel().getSelectedItem();
        Usuario usuarioNuevo = new Usuario(clienteNombreCampo.getText(), usuario.getId(), clienteCorreoCampo.getText(), clienteContactoCampo.getText(), usuario.getContraseña());
        usuarioNuevo.setListaCuentas(usuario.getListaCuentas());
        if (usuario != null) {
            billeteraVirtual.editarObjeto(usuario, usuarioNuevo, billeteraVirtual.getListaPersonas());
            mensaje = "Usuario" + usuario.toString() +"actualizado con exito a " + usuarioNuevo.toString();
        }
        Alert alerta = new Alert(Alert.AlertType.INFORMATION); // Tipo de alerta: Información
        alerta.setTitle("Mensaje de Información"); // Título del popup
        alerta.setHeaderText("Información Operación"); // Texto principal en la parte superior
        alerta.setContentText(mensaje); // Texto del mensaje

        // Mostrar el popup y esperar la respuesta del usuario (por ejemplo, clic en "OK")
        alerta.showAndWait();
    }

    /**
     * abre la pantalla GestionarCategorias en la misma ventana
     * */
    @FXML
    void gestionarCategoriaAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionarCategorias.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) gestionarCategoriaBoton.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }

    /**
     * abre la pantalla RealizarTransferencia en la misma ventana
     * */
    @FXML
    void realizarTransaccionesAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RealizarTransferencia.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) realizarTransaccionesBoton.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }

    /**
     * Recarga la tabla donde se puede ver el usuario
     * */
    @FXML
    void recargarAccion(ActionEvent event) {
        usuariosTabla.refresh();
    }


    @FXML
    void initialize() {
        // Configurar valores de la tabla
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cedulaColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        nombreColumna1.setCellValueFactory(new PropertyValueFactory<>("correo"));

        Usuario usuario = sesion.getUsuario();

        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(usuario);

        usuariosTabla.setItems(usuarios);

        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteContactoCampo != null : "fx:id=\"clienteContactoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteCorreoCampo != null : "fx:id=\"clienteCorreoCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert clienteNombreCampo != null : "fx:id=\"clienteNombreCampo\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert consultarSaldoBoton != null : "fx:id=\"consultarSaldoBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert editarUsuarioBoton != null : "fx:id=\"editarUsuarioBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert gestionarCategoriaBoton != null : "fx:id=\"gestionarCategoriaBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert nombreColumna1 != null : "fx:id=\"nombreColumna1\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert realizarTransaccionesBoton != null : "fx:id=\"realizarTransaccionesBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert telefonoColumna != null : "fx:id=\"telefonoColumna\" was not injected: check your FXML file 'GestionUsuario.fxml'.";
        assert usuariosTabla != null : "fx:id=\"usuariosTabla\" was not injected: check your FXML file 'GestionUsuario.fxml'.";

    }

}