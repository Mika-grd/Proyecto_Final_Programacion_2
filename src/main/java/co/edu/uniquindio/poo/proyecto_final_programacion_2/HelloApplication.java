package co.edu.uniquindio.poo.proyecto_final_programacion_2;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCreditoBuilder;
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

        CuentaCreditoBuilder creditoBuilder = new CuentaCreditoBuilder();

        CuentaCredito credito1 = (CuentaCredito) director.cuentaCreditoSimple(creditoBuilder, "1", 1344, user);

        credito1.setCupoTotalInicial(600000);
        credito1.setCupoDisponible(600000);
        credito1.setTasaInteres(0.5);
        credito1.setCupoEnUso(0);

        System.out.println(credito1.getTasaInteres());


        user.agregarObjeto(credito1, user.getListaCuentas());

        Cuenta cuenta1 = director.cuentaDebitoSimple(debito, "123", 107890654, user);

        CuentaDebito cuentaDebito2 = (CuentaDebito) cuenta1;

        cuentaDebito2.agregarSaldo(10000000);

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

        Transaccion transaccion1 = new Transaccion("1", LocalDate.now(), 50000, "Para el mercado", cuentaDebito2, cuentaDebito);
        transaccion1.realizarTransaccion();

        Transaccion transaccion2 = new Transaccion("2", LocalDate.now(), 25000, "Pago de servicios", cuentaDebito2, cuentaDebito);
        transaccion2.realizarTransaccion();

        Transaccion transaccion3 = new Transaccion("3", LocalDate.now(), 5000, "Transferencia rápida", cuentaDebito2, cuentaDebito);
        transaccion3.realizarTransaccion();

        Transaccion transaccion4 = new Transaccion("4", LocalDate.of(2025, 3, 1), 3000000, "Inversión", cuentaDebito2, cuentaDebito);
        System.out.println("Antes de ejecutar: " + transaccion4.getFechaTransaccion());
        transaccion4.realizarTransaccion();
        System.out.println("Después de ejecutar: " + transaccion4.getFechaTransaccion());

        Transaccion transaccion5 = new Transaccion("5", LocalDate.now(), 80000, "Compra mensual", cuentaDebito2, cuentaDebito);
        transaccion5.realizarTransaccion();

        System.out.println(cuentaDebito2.getListaTransaccion());

        System.out.println(cuentaDebito.getListaTransaccion());

        launch();
    }
}