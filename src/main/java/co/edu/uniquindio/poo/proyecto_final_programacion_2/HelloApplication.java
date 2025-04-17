package co.edu.uniquindio.poo.proyecto_final_programacion_2;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Categoria;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Cuenta;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Presupuesto;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.Usuario;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaDebitoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.DirectorCuentasBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        Usuario user = new Usuario("juan", "1234", "juan@.com", "3015694075", "123");

        DirectorCuentasBuilder director = new DirectorCuentasBuilder();

        CuentaDebitoBuilder debito = new CuentaDebitoBuilder();

        CuentaCategoriasBuilder builderCategorias = new CuentaCategoriasBuilder();


        Cuenta cuenta1 = director.cuentaDebitoSimple(debito, "123", 107890654, user);

        Presupuesto presupuesto = new Presupuesto("", "", 0);

        Categoria Ahorro = new Categoria("12", "Ahorros", "Para ahorrar", presupuesto);
        Cuenta cuenta2 = director.cuentaDebitoConUnaCategoria(builderCategorias, "123", 1234, user, Ahorro);

        System.out.println(cuenta1.toString());

        System.out.println(cuenta2.toString());

        launch();
    }
}