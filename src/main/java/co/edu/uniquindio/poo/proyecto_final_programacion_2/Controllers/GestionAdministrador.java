package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionAdministrador {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="administradoresTabla"
    private TableView<?> administradoresTabla; // Value injected by FXMLLoader

    @FXML // fx:id="agregarAdministradorBoton"
    private Button agregarAdministradorBoton; // Value injected by FXMLLoader

    @FXML // fx:id="cedulaCampo"
    private TextField cedulaCampo; // Value injected by FXMLLoader

    @FXML // fx:id="cedulaColumna"
    private TableColumn<?, ?> cedulaColumna; // Value injected by FXMLLoader

    @FXML // fx:id="correoCampo"
    private TextField correoCampo; // Value injected by FXMLLoader

    @FXML // fx:id="correoColumna"
    private TableColumn<?, ?> correoColumna; // Value injected by FXMLLoader

    @FXML // fx:id="editarAdministradorBoton"
    private Button editarAdministradorBoton; // Value injected by FXMLLoader

    @FXML // fx:id="eliminarAdministradorBoton"
    private Button eliminarAdministradorBoton; // Value injected by FXMLLoader

    @FXML // fx:id="nombreCampo"
    private TextField nombreCampo; // Value injected by FXMLLoader

    @FXML // fx:id="nombreColumna"
    private TableColumn<?, ?> nombreColumna; // Value injected by FXMLLoader

    @FXML // fx:id="volverBoton"
    private Button volverBoton; // Value injected by FXMLLoader

    @FXML
    void agregarAdministrador(ActionEvent event) {

    }

    @FXML
    void editarAdministrador(ActionEvent event) {

    }

    @FXML
    void eliminarAdministrador(ActionEvent event) {

    }

    @FXML
    void volverAccion(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert administradoresTabla != null : "fx:id=\"administradoresTabla\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert agregarAdministradorBoton != null : "fx:id=\"agregarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaCampo != null : "fx:id=\"cedulaCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert cedulaColumna != null : "fx:id=\"cedulaColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoCampo != null : "fx:id=\"correoCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert correoColumna != null : "fx:id=\"correoColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert editarAdministradorBoton != null : "fx:id=\"editarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert eliminarAdministradorBoton != null : "fx:id=\"eliminarAdministradorBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreCampo != null : "fx:id=\"nombreCampo\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert nombreColumna != null : "fx:id=\"nombreColumna\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'GestionAdministrador.fxml'.";

    }
}
