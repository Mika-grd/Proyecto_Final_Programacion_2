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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class consultarSaldoTransaccionesController {

    private BilleteraVirtual billetera = BilleteraVirtual.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Transaccion> historialTable;

    @FXML
    private TableColumn<Transaccion, String> descripcionColumna;

    @FXML
    private TableColumn<Transaccion, LocalDate > fechaColumna;

    @FXML
    private TableColumn<Transaccion, String> idColumna;

    @FXML
    private AnchorPane idSaldo;

    @FXML
    private TableColumn<Transaccion, Double> montoColumna;

    @FXML
    private Button recargarBoton;

    @FXML
    private Label saldoDisponible;

    @FXML
    private Label saldoTotal;

    @FXML
    private Button volverBoton1;


    @FXML
    private TextField RetirarDepositar;

    @FXML
    private Button bttnAgregarSaldo1;

    @FXML
    private Button bttnDepositarCategoria1;

    @FXML
    private Button bttnRetirarSaldo1;

    @FXML
    private ComboBox<Categoria> comboboxCategoria;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelRetirarDepositar;


    @FXML
    private Button botonRetirarCategoria;

    @FXML
    private Button bttnConvertirADolares;



    @FXML
    private Label labelMontoDolares;


    CuentaDebito cuentaActual = Sesion.getInstancia().getCuentaDebito();


    /**
     * Muestra una alerta de tipo WARNING con el mensaje especificado.
     * Se usa para notificar al usuario sobre errores o validaciones fallidas.
     *
     * @param mensaje Texto que se mostrará en la alerta.
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Maneja la acción de agregar saldo a una cuenta de débito.
     * Este metodo valida el monto ingresado, actualiza el saldo de la cuenta,
     * registra la transacción y actualiza la interfaz de usuario.
     */
    @FXML
    void ConvertirADolares(ActionEvent event) {
        try {
            String texto = RetirarDepositar.getText();
            if (texto == null || texto.isBlank()) {
                labelMontoDolares.setText("USD: Ingrese un monto");
                return;
            }

            double montoPesos = Double.parseDouble(texto);

            ConversorMoneda conversor = new ConversorMoneda();
            double montoDolares = conversor.convertirPesos(montoPesos);

            labelMontoDolares.setText(String.format("USD: $%.2f", montoDolares));
        } catch (NumberFormatException e) {
            labelMontoDolares.setText("USD: Monto inválido");
        }
    }

    //Metodo para agregar saldo a la cuenta
    @FXML
    void agregarSaldoAccion(ActionEvent event) {
        if (RetirarDepositar.getText() != null) {

            try {
                double monto = Double.parseDouble(RetirarDepositar.getText());
                cuentaActual.agregarSaldo(monto);
                mostrarAlerta("Se ha agregado un saldo de " + String.valueOf(monto), Alert.AlertType.INFORMATION);
                Transaccion deposito = new Transaccion("Deposito", LocalDate.now(), monto, "Deposito de dinero");
                cuentaActual.getListaTransaccion().add(deposito);
                historialTable.refresh();

            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo", Alert.AlertType.ERROR);
        }

        cargarSaldos();

    }


    /**
     * Maneja la acción de depositar un monto desde la cuenta principal hacia una categoría específica.
     * Verifica que exista un monto válido y una categoría seleccionada antes de realizar la operación.
     *
     */
    @FXML
    void depositarCategoria(ActionEvent event) {

        if (RetirarDepositar.getText() != null  && comboboxCategoria.getSelectionModel().getSelectedItem() != null) {

            try {
                Categoria categoria = comboboxCategoria.getSelectionModel().getSelectedItem();
                double monto = Double.parseDouble(RetirarDepositar.getText());
                if (cuentaActual.getSaldo() >= monto) {
                    cuentaActual.setSaldo(cuentaActual.getSaldo() - monto);
                    categoria.getPresupuesto().setMontoActual(categoria.getPresupuesto().getMontoActual() + monto);
                    cuentaActual.calcularSaldoTotal();
                    mostrarAlerta("Se ha agregado un saldo de " + String.valueOf(monto) + " A la categoria" + categoria.getNombre(), Alert.AlertType.INFORMATION);
                    Transaccion deposito = new Transaccion("Deposito a " + categoria.getNombre(), LocalDate.now(), monto, "Deposito de dinero");
                    cuentaActual.getListaTransaccion().add(deposito);
                    historialTable.refresh();
                }
                else {
                    mostrarAlerta("No hay suficiente saldo en Disponible", Alert.AlertType.ERROR);
                }

            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo  y seleccionar una categoria", Alert.AlertType.ERROR);
        }

        cargarSaldos();

    }


    /**
     * Retira un monto de una categoría seleccionada y lo transfiere al saldo general de la cuenta.
     * El metodo valida que se haya ingresado un monto válido y que se haya seleccionado una categoría.
     * Si el presupuesto actual de la categoría es suficiente para cubrir el retiro, se descuenta
     * el monto del presupuesto y se suma al saldo disponible de la cuenta. Se registra la transacción
     * en el historial y se actualiza la interfaz. Si el monto no es válido o no hay suficiente saldo
     * en la categoría, se muestra una alerta al usuario.
     *
     */
    @FXML
    void retirarCategoria(ActionEvent event) {

        if (RetirarDepositar.getText() != null && comboboxCategoria.getSelectionModel().getSelectedItem() != null) {

            try {
                Categoria categoria = comboboxCategoria.getSelectionModel().getSelectedItem();
                double monto = Double.parseDouble(RetirarDepositar.getText());
                if (categoria.getPresupuesto().getMontoActual() >= monto) {
                    categoria.getPresupuesto().setMontoActual(categoria.getPresupuesto().getMontoActual() - monto);
                    cuentaActual.setSaldo(cuentaActual.getSaldo() + monto);
                    cuentaActual.calcularSaldoTotal();
                    mostrarAlerta("Se ha retirado un saldo de " + String.valueOf(monto) + " de la categoria" + categoria.getNombre(), Alert.AlertType.INFORMATION);
                    Transaccion deposito = new Transaccion("Retiro de " + categoria.getNombre(), LocalDate.now(), monto, "retiro de dinero");
                    cuentaActual.getListaTransaccion().add(deposito);
                    historialTable.refresh();
                }
                else {
                    mostrarAlerta("No hay suficiente saldo en la categoria", Alert.AlertType.ERROR);
                }

            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo y seleccionar una categoria", Alert.AlertType.ERROR);
        }

        cargarSaldos();

    }


    /**
     * Retira un monto del saldo disponible de la cuenta actual.
     * El metodo permite al usuario retirar una cantidad de dinero desde el saldo general.
     * Primero valida que se haya ingresado un valor numérico válido. Luego verifica si el saldo
     * es suficiente para realizar el retiro. Si lo es, descuenta el monto, registra la transacción
     * en el historial y actualiza la vista. En caso contrario, muestra una alerta indicando la
     * causa del error (saldo insuficiente o entrada inválida).
     */
    @FXML
    void retirarSaldoAccion(ActionEvent event) {

        if (RetirarDepositar.getText() != null) {

            try {
                double monto = Double.parseDouble(RetirarDepositar.getText());
                if (monto > cuentaActual.getSaldo()) {
                    mostrarAlerta("No hay suficiente saldo en Disponible", Alert.AlertType.INFORMATION);
                }
                else {
                    cuentaActual.retirarSaldo(monto);
                    mostrarAlerta("Se ha retirado exitosamente saldo de " + String.valueOf(monto), Alert.AlertType.INFORMATION);
                    Transaccion retiro = new Transaccion("Retiro", LocalDate.now(), monto, "Retiro de dinero");
                    cuentaActual.getListaTransaccion().add(retiro);
                    historialTable.refresh();
                }
            }
            catch (NumberFormatException e) {
                mostrarAlerta("Debse ser un numero", Alert.AlertType.ERROR);
            }
        }
        else{

            mostrarAlerta("Debe ingresar una saldo", Alert.AlertType.ERROR);
        }

        cargarSaldos();

    }

    //Metodo para recargar el historial de transacciones
    //Recarga la lista
    @FXML
    void recargarAccion(ActionEvent event) {

        ObservableList<Transaccion> historial = FXCollections.observableArrayList(cuentaActual.getListaTransaccion());
        historialTable.setItems(historial);

    }


    //Metodo de volver
    @FXML
    void volverAccion(ActionEvent event) {
        try {
            // Carga el archivo FXML de la pantalla anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyecto_final_programacion_2/GestionUsuario.fxml"));

            // Crea el árbol de nodos desde el archivo FXML
            Parent root = loader.load();

            // Obtiene la ventana actual desde el botón
            Stage stage = (Stage) volverBoton1.getScene().getWindow();

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
     * Carga los label de saldo disponible y saldo total
     */
    private void cargarSaldos() {


        if (cuentaActual != null) {
            saldoDisponible.setText(String.format("$%,.2f", cuentaActual.getSaldo()));
            saldoTotal.setText(String.format("$%,.2f", cuentaActual.getSaldoTotal()));
        } else {
            saldoDisponible.setText("N/A");
            saldoTotal.setText("N/A");
        }
    }

    // Método que se ejecuta al inicializar el controlador
    @FXML
    void initialize() {

        cargarSaldos();


        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaTransaccion"));
        montoColumna.setCellValueFactory(new PropertyValueFactory<>("montoATransferir"));

        historialTable.setItems(FXCollections.observableArrayList(cuentaActual.getListaTransaccion()));

        ObservableList<Transaccion> historial = FXCollections.observableArrayList(cuentaActual.getListaTransaccion());
        ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList(cuentaActual.getListaCategorias());


        if (listaCategorias.size() == 0) {
            comboboxCategoria.setDisable(true);
        }
        comboboxCategoria.setItems(listaCategorias);
        historialTable.setItems(historial);

        assert bttnConvertirADolares != null : "fx:id=\"bttnConvertirADolares\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert botonRetirarCategoria != null : "fx:id=\"botonRetirarCategoria\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert RetirarDepositar != null : "fx:id=\"RetirarDepositar\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnAgregarSaldo1 != null : "fx:id=\"bttnAgregarSaldo1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnDepositarCategoria1 != null : "fx:id=\"bttnDepositarCategoria1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert bttnRetirarSaldo1 != null : "fx:id=\"bttnRetirarSaldo1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert comboboxCategoria != null : "fx:id=\"comboboxCategoria\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert descripcionColumna != null : "fx:id=\"descripcionColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert fechaColumna != null : "fx:id=\"fechaColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert historialTable != null : "fx:id=\"historialTable\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idColumna != null : "fx:id=\"idColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert idSaldo != null : "fx:id=\"idSaldo\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert labelCategoria != null : "fx:id=\"labelCategoria\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert labelRetirarDepositar != null : "fx:id=\"labelRetirarDepositar\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert montoColumna != null : "fx:id=\"montoColumna\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert recargarBoton != null : "fx:id=\"recargarBoton\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert saldoDisponible != null : "fx:id=\"saldoDisponible\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert saldoTotal != null : "fx:id=\"saldoTotal\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";
        assert volverBoton1 != null : "fx:id=\"volverBoton1\" was not injected: check your FXML file 'ConsultarSaldoTransacciones.fxml'.";

    }

}
