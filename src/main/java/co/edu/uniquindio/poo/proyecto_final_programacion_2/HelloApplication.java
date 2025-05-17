package co.edu.uniquindio.poo.proyecto_final_programacion_2;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaDebitoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.DirectorCuentasBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InicioSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 645, 600);
        stage.setTitle("Billetera Virtual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();


        Usuario user = new Usuario("juan", "123", "juan@.com", "3015694075", "123");

        Administrador administrador = new Administrador("raul","444","raul@.com","3015694075","444");

        DirectorCuentasBuilder director = new DirectorCuentasBuilder();

        CuentaDebitoBuilder debito = new CuentaDebitoBuilder();

        CuentaCategoriasBuilder builderCategorias = new CuentaCategoriasBuilder();


        Cuenta cuenta1 = director.cuentaDebitoSimple(debito, "123", 107890654, user);

        CuentaDebito cuentaDebito2 = (CuentaDebito) cuenta1;

        cuentaDebito2.agregarSaldo(100000);

        Presupuesto presupuesto = new Presupuesto("", "", 0);



        Categoria Ahorro = new Categoria("12", "Ahorros", "Para ahorrar", presupuesto);


        Cuenta cuenta2 = director.cuentaDebitoConUnaCategoria(builderCategorias, "124", 1234, user, Ahorro);

        billeteraVirtual.agregarObjeto(user, billeteraVirtual.getListaPersonas());
        billeteraVirtual.agregarObjeto(administrador, billeteraVirtual.getListaPersonas());

        CuentaDebito cuentaDebito = (CuentaDebito) cuenta2;

        cuentaDebito.agregarSaldo(10000);
        cuentaDebito.depositoACategoria(Ahorro, 5000);

        System.out.println("***********"+ cuentaDebito.getSaldo() + " " + cuentaDebito.getSaldoTotal());



        billeteraVirtual.agregarObjeto(cuenta1, billeteraVirtual.getListaCuentas());
        billeteraVirtual.agregarObjeto(cuenta2, billeteraVirtual.getListaCuentas());

        user.agregarObjeto(cuenta1, user.getListaCuentas());
        user.agregarObjeto(cuenta2, user.getListaCuentas());


        LinkedList<CuentaDTO> listaCuentaDTO = new LinkedList<>();

        for (Cuenta cuenta : billeteraVirtual.getListaCuentas()) {
            listaCuentaDTO.add(new CuentaDTO(cuenta));
        }

        for (CuentaDTO cuentaDTO : listaCuentaDTO) {
            System.out.println(cuentaDTO.toString());
        }

        LinkedList<UsuarioDTO> listaUsuarioDTO = new LinkedList<>();

        for (Persona Persona : billeteraVirtual.getListaPersonas()){
            if (Persona instanceof Usuario){
                listaUsuarioDTO.add(new UsuarioDTO((Usuario) Persona));
            }
        }

        for (UsuarioDTO usuarioDTO : listaUsuarioDTO) {
            System.out.println(usuarioDTO.toString());
        }


        //Prototype

        Transaccion transaccion = new Transaccion("1", LocalDate.now(), 50000, cuentaDebito2.getSaldo(), "Para el mercado", cuentaDebito2, cuentaDebito);
        transaccion.realizarTransaccion();

        Transaccion transaccion2 = transaccion.clone();
        transaccion2.setMontoATransferir(25000);
        transaccion2.realizarTransaccion();

        System.out.println(cuentaDebito2.getSaldoTotal() + " " + cuentaDebito2.getSaldo());
        System.out.println(cuentaDebito.getSaldoTotal() + " " + cuentaDebito.getSaldo());

        System.out.println(cuentaDebito.getListaCategorias());

        launch();
    }
}