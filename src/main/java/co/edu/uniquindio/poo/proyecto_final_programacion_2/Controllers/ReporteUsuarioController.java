package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.BilleteraVirtual;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;

import java.util.*;

public class ReporteUsuarioController {


    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ComboBox<Usuario> comboTipoCuenta;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button btnVolver;

    private List<Usuario> listaUsuarios;


    @FXML
    void initialize() {
        listaUsuarios = obtenerListaUsuarios(); // Asegúrate que no sea null ni vacía

        System.out.println("Usuarios disponibles:");
        listaUsuarios.forEach(u -> System.out.println(u.getNombre())); // Para depurar

        comboTipoCuenta.setItems(FXCollections.observableArrayList(listaUsuarios));

        // Convertidor para mostrar solo el nombre en el campo seleccionado
        comboTipoCuenta.setConverter(new StringConverter<>() {
            @Override
            public String toString(Usuario usuario) {
                return usuario != null ? usuario.getNombre() : "";
            }

            @Override
            public Usuario fromString(String nombre) {
                return listaUsuarios.stream()
                        .filter(u -> u.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);
            }
        });

        // Asegura que la lista desplegable muestre el nombre también
        comboTipoCuenta.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Usuario usuario, boolean empty) {
                super.updateItem(usuario, empty);
                setText(empty || usuario == null ? null : usuario.getNombre());
            }
        });

        comboTipoCuenta.setPromptText("Escoja el usuario");

        btnGenerarReporte.setOnAction(e -> generarReporte());
    }


    private void generarReporte() {
        Usuario usuario = comboTipoCuenta.getValue();
        if (usuario == null) return;

        pieChart.getData().clear();

        // === PieChart: porcentaje de cuentas por tipo ===
        long totalCredito = usuario.getListaCuentasCredito().size();
        long totalDebito = usuario.getListaCuentasDebito().size();
        long total = totalCredito + totalDebito;

        if (total > 0) {
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("Crédito", totalCredito),
                    new PieChart.Data("Débito", totalDebito)
            );
            pieChart.setData(pieData);
        }
    }

    private List<Usuario> obtenerListaUsuarios() {
        return BilleteraVirtual.getInstance()
                .getListaPersonas()
                .stream()
                .filter(p -> p instanceof Usuario)
                .map(p -> (Usuario) p)
                .toList();
    }

    @FXML
    void VolverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionCuentaAdministrador.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) btnVolver.getScene().getWindow();

            // Crea una nueva escena con el contenido de Pantalla1
            Scene scene = new Scene(root);

            // Establece la nueva escena en la ventana actual
            stage.setScene(scene);
        } catch (IOException e) {
            // Muestra el error si hay un problema al cargar el FXML
            e.printStackTrace();
        }
    }
}
