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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class inicioSesionController {

    private Sesion sesion = Sesion.getInstancia();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ContraseñaCampo;

    @FXML
    private TextField UsuarioCampo;

    @FXML
    private Button iniciarSesionBoton;

    @FXML
    private Hyperlink olvideContraseniaLink;


    /**
     * Maneja el evento de inicio de sesión cuando el usuario presiona el botón correspondiente.
     *
     * Este metodo válida que los campos de usuario e contraseña no estén vacíos,
     * busca al usuario en la lista de personas de la billetera virtual, verifica
     * que la contraseña sea correcta y, dependiendo del tipo de persona encontrada
     * (Usuario o Administrador), establece el objeto correspondiente en la sesión.
     *
     * En caso de error (campos vacíos, usuario no encontrado o contraseña incorrecta),
     * se muestra un mensaje de alerta informando al usuario.
     *
     * @param event el evento que desencadena la acción (por ejemplo, clic en el botón de login)
     */
    @FXML
    void iniciarSesionAccion(ActionEvent event) {
        BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();

        String usuarioID = UsuarioCampo.getText().trim();
        String contraseña = ContraseñaCampo.getText();

        // Validar campos vacíos
        if (usuarioID.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error de inicio de sesión", "Por favor debe llenar todos los campos", Alert.AlertType.WARNING);
            return;
        }

        Persona personaSesion = billeteraVirtual.buscarObjeto(usuarioID, billeteraVirtual.getListaPersonas());

        //Persona nula
        if (personaSesion == null) {
            mostrarAlerta("Usuario no encontrado", "No se encontró la persona con ID: " + usuarioID, Alert.AlertType.ERROR);
            return;
        }

        //Contraseña incorrecta
        if (!personaSesion.getContraseña().equals(contraseña)) {
            mostrarAlerta("Contraseña incorrecta", "La contraseña ingresada es incorrecta.", Alert.AlertType.ERROR);
            return;
        }

        // Login exitoso
        if (personaSesion instanceof Usuario usuarioSesion) {
            sesion.setUsuario(usuarioSesion);
            mostrarAlerta("Bienvenido", "Inicio sesión usuario: " + usuarioSesion.getNombre(), Alert.AlertType.INFORMATION);
            // Cargar vista de usuario
            cargarVistaUsuario();
        } else if (personaSesion instanceof Administrador administrador) {
            sesion.setAdministrador(administrador);
            mostrarAlerta("Bienvenido", "Inicio sesión administrador: " + administrador.getNombre(), Alert.AlertType.INFORMATION);
            // Cargar vista de administrador aquí
            cargarVistaAdministrador();
        } else {
            mostrarAlerta("Tipo de usuario desconocido", "No se puede iniciar sesión con este tipo de cuenta.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Carga y muestra la vista principal para el administrador después de un inicio de sesión exitoso.
     * Cambia la escena actual a la interfaz de gestión de administrador.
     */
    private void cargarVistaAdministrador() {
        try {
            // Cargar el archivo FXML de la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdministrador.fxml")); // Asegúrate de usar la ruta absoluta

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtener el stage desde cualquier nodo (como un botón)
            Stage stage = (Stage) iniciarSesionBoton.getScene().getWindow();

            // Crear una nueva escena con el contenido cargado
            Scene scene = new Scene(root);

            // Establecer la nueva escena en la ventana actual
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Mostrar el error si ocurre al cargar el archivo FXML
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista: GestionAdministrador", Alert.AlertType.ERROR);
        }
    }

    /**
     * Carga y muestra la vista principal para el usuario después de un inicio de sesión exitoso.
     * Cambia la escena actual a la interfaz de gestión de usuario.
     */
    private void cargarVistaUsuario() {
        try {
            // Cargar el archivo FXML de la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionCuenta.fxml")); // Asegúrate de usar la ruta absoluta

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtener el stage desde cualquier nodo (como un botón)
            Stage stage = (Stage) iniciarSesionBoton.getScene().getWindow();

            // Crear una nueva escena con el contenido cargado
            Scene scene = new Scene(root);

            // Establecer la nueva escena en la ventana actual
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Mostrar el error si ocurre al cargar el archivo FXML
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista: GestionCuenta", Alert.AlertType.ERROR);
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


    @FXML
    void olvidoContraseniaAccion(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ContraseñaCampo != null : "fx:id=\"ContraseñaCampo\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert UsuarioCampo != null : "fx:id=\"UsuarioCampo\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert iniciarSesionBoton != null : "fx:id=\"iniciarSesionBoton\" was not injected: check your FXML file 'InicioSesion.fxml'.";
        assert olvideContraseniaLink != null : "fx:id=\"olvideContraseniaLink\" was not injected: check your FXML file 'InicioSesion.fxml'.";

    }

}