package co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ReporteUsuarioController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ComboBox<String> comboTipoCuenta;

    @FXML
    private PieChart pieChart;

    @FXML
    void initialize() {
        // Cargar tipos de cuenta en el ComboBox
        comboTipoCuenta.setItems(FXCollections.observableArrayList("Ahorros", "Crédito"));

        // Acción al hacer clic en el botón
        btnGenerarReporte.setOnAction(event -> generarReporte());
    }

<<<<<<< Updated upstream
=======

    /**
     * Genera un reporte visual en un gráfico de pastel (PieChart) que muestra
     * el porcentaje de cuentas de un usuario divididas entre cuentas de crédito y débito.
     */
>>>>>>> Stashed changes
    private void generarReporte() {
        String tipoCuenta = comboTipoCuenta.getValue();
        if (tipoCuenta == null) {
            return; // No hacer nada si no se seleccionó un tipo de cuenta
        }

        // Limpiar datos anteriores
        barChart.getData().clear();
        pieChart.getData().clear();

        // Simular datos para el gráfico de barras
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Movimientos");

        if (tipoCuenta.equals("Ahorros")) {
            series.getData().add(new XYChart.Data<>("Enero", 300));
            series.getData().add(new XYChart.Data<>("Febrero", 500));
            series.getData().add(new XYChart.Data<>("Marzo", 250));

            pieChart.setData(FXCollections.observableArrayList(
                    new PieChart.Data("Ingresos", 60),
                    new PieChart.Data("Gastos", 40)
            ));
        } else if (tipoCuenta.equals("Crédito")) {
            series.getData().add(new XYChart.Data<>("Enero", 700));
            series.getData().add(new XYChart.Data<>("Febrero", 450));
            series.getData().add(new XYChart.Data<>("Marzo", 620));

            pieChart.setData(FXCollections.observableArrayList(
                    new PieChart.Data("Deuda Usada", 70),
                    new PieChart.Data("Cupo Disponible", 30)
            ));
        }

<<<<<<< Updated upstream
        barChart.getData().add(series);
=======

    /**
     * Obtiene una lista de objetos de tipo Usuario que están almacenados en la lista general de personas
     * dentro de la instancia singleton de BilleteraVirtual.
     * Este metodo filtra la lista de personas para incluir solo aquellos que son instancias de Usuario,
     * y luego los convierte a tipo Usuario.
     *
     */
    private List<Usuario> obtenerListaUsuarios() {
        return BilleteraVirtual.getInstance()
                .getListaPersonas()
                .stream()
                .filter(p -> p instanceof Usuario)
                .map(p -> (Usuario) p)
                .toList();
    }

    //vuelve a la ventana anterior
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
>>>>>>> Stashed changes
    }
}


