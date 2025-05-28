package co.edu.uniquindio.poo.proyecto_final_programacion_2;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base.*;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCreditoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaDebitoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.DirectorCuentasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.ComandoRealizarPago;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.ComandoUsarCupo;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command.OperacionCuentaInvoker;
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

 // Assuming this is your main class

        public static void main(String[] args) {

            BilleteraVirtual billeteraVirtual = BilleteraVirtual.getInstance();
            DirectorCuentasBuilder director = new DirectorCuentasBuilder();
            CuentaDebitoBuilder debitoBuilder = new CuentaDebitoBuilder();
            CuentaCategoriasBuilder categoriasBuilder = new CuentaCategoriasBuilder();
            CuentaCreditoBuilder creditoBuilder = new CuentaCreditoBuilder();

            OperacionCuentaInvoker invoker = new OperacionCuentaInvoker();

            // --- Administrador (from second block) ---
            Administrador administrador = new Administrador("raul","444","raul@.com","3015694075","444");
            billeteraVirtual.agregarObjeto(administrador, billeteraVirtual.getListaPersonas());

            // --- Usuario Juan (Consolidated) ---
            Usuario userJuan = new Usuario("juan", "123", "juan@correo.com", "3015694075", "123");
            billeteraVirtual.agregarObjeto(userJuan, billeteraVirtual.getListaPersonas()); // Add Juan to the virtual wallet

            // Juan's Cuenta Crédito (from first block)
            CuentaCredito creditoJuan = (CuentaCredito) director.cuentaCreditoSimple(creditoBuilder, "CRED1", 1001, userJuan);
            creditoJuan.setCupoTotalInicial(500000);
            creditoJuan.setCupoDisponible(500000);
            creditoJuan.setTasaInteres(0.2);
            creditoJuan.setCupoEnUso(0);
            userJuan.agregarObjeto(creditoJuan, userJuan.getListaCuentas());
            billeteraVirtual.agregarObjeto(creditoJuan, billeteraVirtual.getListaCuentas());

            // Juan's Cuenta Débito con categoría (from first block)
            Categoria AhorroJuan = new Categoria("cat1", "Ahorro", "Ahorro mensual", new Presupuesto("", "PREP0", 0));
            CuentaDebito cuentaDebitoJuan = (CuentaDebito) director.cuentaDebitoConUnaCategoria(categoriasBuilder, "DEB1", 2001, userJuan, AhorroJuan);
            cuentaDebitoJuan.agregarSaldo(1000000);
            userJuan.agregarObjeto(cuentaDebitoJuan, userJuan.getListaCuentas());
            billeteraVirtual.agregarObjeto(cuentaDebitoJuan, billeteraVirtual.getListaCuentas());

            // Juan's second Cuenta Debito (from second block, if different from DEB1)
            // If "123" and 107890654 are intended to be a *different* account than DEB1, keep it.
            // If it's meant to be the same, then this part is redundant. Assuming different for now.
            Cuenta cuentaDebitoJuan2 = director.cuentaDebitoSimple(debitoBuilder, "123", 107890654, userJuan);
            CuentaDebito castCuentaDebitoJuan2 = (CuentaDebito) cuentaDebitoJuan2;
            castCuentaDebitoJuan2.agregarSaldo(10000000);
            userJuan.agregarObjeto(castCuentaDebitoJuan2, userJuan.getListaCuentas());
            billeteraVirtual.agregarObjeto(castCuentaDebitoJuan2, billeteraVirtual.getListaCuentas());

            // Juan's credit account setup from second block, if different
            // If this is meant to be a *different* credit account than CRED1, keep it.
            // If it's meant to be the same, then this part is redundant. Assuming different for now.
            CuentaCredito creditoJuan2 = (CuentaCredito) director.cuentaCreditoSimple(creditoBuilder, "1", 1344, userJuan);
            creditoJuan2.setCupoTotalInicial(600000);
            creditoJuan2.setCupoDisponible(600000);
            creditoJuan2.setTasaInteres(0.5);
            creditoJuan2.setCupoEnUso(0);
            System.out.println(creditoJuan2.getTasaInteres());
            userJuan.agregarObjeto(creditoJuan2, userJuan.getListaCuentas());
            billeteraVirtual.agregarObjeto(creditoJuan2, billeteraVirtual.getListaCuentas());

            // Juan's second categorized debit account (from second block, if different from DEB1)
            Presupuesto presupuestoJuan2 = new Presupuesto("", "", 0);
            Categoria AhorroJuan2 = new Categoria("12", "Ahorros", "Para ahorrar", presupuestoJuan2);
            Cuenta cuentaDebitoJuan3 = director.cuentaDebitoConUnaCategoria(categoriasBuilder, "124", 1234, userJuan, AhorroJuan2);
            CuentaDebito castCuentaDebitoJuan3 = (CuentaDebito) cuentaDebitoJuan3;
            castCuentaDebitoJuan3.agregarSaldo(10000);
            castCuentaDebitoJuan3.depositoACategoria(AhorroJuan2, 5000);
            System.out.println("*****"+ castCuentaDebitoJuan3.getSaldo() + " " + castCuentaDebitoJuan3.getSaldoTotal());
            userJuan.agregarObjeto(castCuentaDebitoJuan3, userJuan.getListaCuentas());
            billeteraVirtual.agregarObjeto(castCuentaDebitoJuan3, billeteraVirtual.getListaCuentas());


            // --- Usuario Maria (Consolidated) ---
            Usuario userMaria = new Usuario("maria", "456", "maria@correo.com", "3012345678", "456");
            billeteraVirtual.agregarObjeto(userMaria, billeteraVirtual.getListaPersonas());

            // Maria's Cuenta Crédito
            CuentaCredito creditoMaria = (CuentaCredito) director.cuentaCreditoSimple(creditoBuilder, "CRED2", 1002, userMaria);
            creditoMaria.setCupoTotalInicial(1000000);
            creditoMaria.setCupoDisponible(1000000);
            creditoMaria.setTasaInteres(0.15);
            creditoMaria.setCupoEnUso(0);
            userMaria.agregarObjeto(creditoMaria, userMaria.getListaCuentas());
            billeteraVirtual.agregarObjeto(creditoMaria, billeteraVirtual.getListaCuentas());

            // Maria's Cuenta Débito
            CuentaDebito cuentaDebitoMaria = (CuentaDebito) director.cuentaDebitoSimple(debitoBuilder, "DEB2", 2002, userMaria);
            cuentaDebitoMaria.agregarSaldo(200000);
            userMaria.agregarObjeto(cuentaDebitoMaria, userMaria.getListaCuentas());
            billeteraVirtual.agregarObjeto(cuentaDebitoMaria, billeteraVirtual.getListaCuentas());


            // --- Usuario Carlos (Consolidated) ---
            Usuario userCarlos = new Usuario("carlos", "789", "carlos@correo.com", "3018765432", "789");
            billeteraVirtual.agregarObjeto(userCarlos, billeteraVirtual.getListaPersonas());

            // Carlos's Cuenta Débito con categoría
            Categoria ocioCarlos = new Categoria("cat2", "Ocio", "Gastos de entretenimiento", new Presupuesto("", "PREP0", 0));
            CuentaDebito cuentaDebitoCarlos = (CuentaDebito) director.cuentaDebitoConUnaCategoria(categoriasBuilder, "DEB3", 2003, userCarlos, ocioCarlos);
            cuentaDebitoCarlos.agregarSaldo(500000);
            userCarlos.agregarObjeto(cuentaDebitoCarlos, userCarlos.getListaCuentas());
            billeteraVirtual.agregarObjeto(cuentaDebitoCarlos, billeteraVirtual.getListaCuentas());

            // Carlos's Cuenta Crédito
            CuentaCredito creditoCarlos = (CuentaCredito) director.cuentaCreditoSimple(creditoBuilder, "CRED3", 1003, userCarlos);
            creditoCarlos.setCupoTotalInicial(750000);
            creditoCarlos.setCupoDisponible(750000);
            creditoCarlos.setTasaInteres(0.25);
            creditoCarlos.setCupoEnUso(0);
            userCarlos.agregarObjeto(creditoCarlos, userCarlos.getListaCuentas());
            billeteraVirtual.agregarObjeto(creditoCarlos, billeteraVirtual.getListaCuentas());


            // --- TRANSACCIONES DÉBITO A DÉBITO (from first block) ---

            // Juan envía 100,000 a Maria
            Transaccion t1 = new Transaccion(
                    "T1",
                    LocalDate.now(),
                    100000,
                    "Pago renta",
                    cuentaDebitoJuan,
                    cuentaDebitoMaria
            );
            if (t1.realizarTransaccion()) {
                System.out.println("Transferencia débito Juan -> María exitosa");
            }

            // Maria envía 50,000 a Carlos
            Transaccion t2 = new Transaccion(
                    "T2",
                    LocalDate.now(),
                    50000,
                    "Compra regalos",
                    cuentaDebitoMaria,
                    cuentaDebitoCarlos
            );
            if (t2.realizarTransaccion()) {
                System.out.println("Transferencia débito María -> Carlos exitosa");
            }

            // Carlos envía 30,000 a Juan
            Transaccion t3 = new Transaccion(
                    "T3",
                    LocalDate.now(),
                    30000,
                    "Devolución préstamo",
                    cuentaDebitoCarlos,
                    cuentaDebitoJuan
            );
            if (t3.realizarTransaccion()) {
                System.out.println("Transferencia débito Carlos -> Juan exitosa");
            }


            // --- TRANSACCIONES CRÉDITO (from first block) ---

            // Juan usa 200,000 de su cupo crédito (ComandoUsarCupo)
            ComandoUsarCupo usarCupoJuan = new ComandoUsarCupo(creditoJuan, 200000, "Uso cupo para emergencia");
            invoker.setComando(usarCupoJuan);
            if (invoker.ejecutarComando()) {
                System.out.println("Juan uso cupo crédito 200,000");
            }

            // María paga 300,000 a su crédito (ComandoRealizarPago)
            ComandoRealizarPago pagoMaria = new ComandoRealizarPago(creditoMaria, 300000);
            invoker.setComando(pagoMaria);
            if (invoker.ejecutarComando()) {
                System.out.println("María pago 300,000 a crédito");
            }

            // Carlos usa 400,000 de su cupo crédito (ComandoUsarCupo)
            ComandoUsarCupo usarCupoCarlos = new ComandoUsarCupo(creditoCarlos, 400000, "Uso cupo ocio");
            invoker.setComando(usarCupoCarlos);
            if (invoker.ejecutarComando()) {
                System.out.println("Carlos uso cupo crédito 400,000");
            }

            // Carlos paga 100,000 a su crédito
            ComandoRealizarPago pagoCarlos = new ComandoRealizarPago(creditoCarlos, 100000);
            invoker.setComando(pagoCarlos);
            if (invoker.ejecutarComando()) {
                System.out.println("Carlos pago 100,000 a crédito");
            }


            // --- Mostrar saldos finales para ver cambios (from first block) ---

            System.out.println("\n--- Saldos finales ---");
            System.out.println(userJuan.getNombre() + " Débito: " + cuentaDebitoJuan.getSaldo() + ", Crédito cupo disponible: " + creditoJuan.getCupoDisponible() + ", cupo en uso: " + creditoJuan.getCupoEnUso());
            System.out.println(userMaria.getNombre() + " Débito: " + cuentaDebitoMaria.getSaldo() + ", Crédito cupo disponible: " + creditoMaria.getCupoDisponible() + ", cupo en uso: " + creditoMaria.getCupoEnUso());
            System.out.println(userCarlos.getNombre() + " Débito: " + cuentaDebitoCarlos.getSaldo() + ", Crédito cupo disponible: " + creditoCarlos.getCupoDisponible() + ", cupo en uso: " + creditoCarlos.getCupoEnUso());


            // --- Prototype Transactions (from first block, using consolidated variables) ---

            Transaccion protoTransaccion1 = new Transaccion("1", LocalDate.now(), 50000, "Para el mercado", cuentaDebitoMaria, cuentaDebitoJuan);
            protoTransaccion1.realizarTransaccion();

            Transaccion protoTransaccion2 = new Transaccion("2", LocalDate.now(), 25000, "Pago de servicios", cuentaDebitoMaria, cuentaDebitoJuan);
            protoTransaccion2.realizarTransaccion();

            Transaccion protoTransaccion3 = new Transaccion("3", LocalDate.now(), 5000, "Transferencia rápida", cuentaDebitoMaria, cuentaDebitoJuan);
            protoTransaccion3.realizarTransaccion();

            Transaccion protoTransaccion4 = new Transaccion("4", LocalDate.of(2025, 3, 1), 3000000, "Inversión", cuentaDebitoMaria, cuentaDebitoJuan);
            System.out.println("Antes de ejecutar: " + protoTransaccion4.getFechaTransaccion());
            protoTransaccion4.realizarTransaccion();
            System.out.println("Después de ejecutar: " + protoTransaccion4.getFechaTransaccion());

            Transaccion protoTransaccion5 = new Transaccion("5", LocalDate.now(), 80000, "Compra mensual", cuentaDebitoMaria, cuentaDebitoJuan);
            protoTransaccion5.realizarTransaccion();

            CuentaDebitoDecorator decoratorJuan = new CuentaDebitoDecorator(cuentaDebitoJuan);
            decoratorJuan.aplicarCategoriasPredeterminadas();


            // --- Additional Prototype/DTO Logic (from second block, using consolidated variables) ---
            // Note: The transaction variables from the second block also clash in ID and purpose with the first set.
            // I've renamed them to differentiate them if they are indeed separate transactions.
            // If these are meant to be *the same* transactions, you would remove this block.

            Transaccion protoTransaccion1_second = new Transaccion("1_B", LocalDate.now(), 50000, "Para el mercado", castCuentaDebitoJuan2, castCuentaDebitoJuan3);
            protoTransaccion1_second.realizarTransaccion();

            Transaccion protoTransaccion2_second = new Transaccion("2_B", LocalDate.now(), 25000, "Pago de servicios", castCuentaDebitoJuan2, castCuentaDebitoJuan3);
            protoTransaccion2_second.realizarTransaccion();

            Transaccion protoTransaccion3_second = new Transaccion("3_B", LocalDate.now(), 5000, "Transferencia rápida", castCuentaDebitoJuan2, castCuentaDebitoJuan3);
            protoTransaccion3_second.realizarTransaccion();

            Transaccion protoTransaccion4_second = new Transaccion("4_B", LocalDate.of(2025, 3, 1), 3000000, "Inversión", castCuentaDebitoJuan2, castCuentaDebitoJuan3);
            System.out.println("Antes de ejecutar: " + protoTransaccion4_second.getFechaTransaccion());
            protoTransaccion4_second.realizarTransaccion();
            System.out.println("Después de ejecutar: " + protoTransaccion4_second.getFechaTransaccion());

            Transaccion protoTransaccion5_second = new Transaccion("5_B", LocalDate.now(), 80000, "Compra mensual", castCuentaDebitoJuan2, castCuentaDebitoJuan3);
            protoTransaccion5_second.realizarTransaccion();

            System.out.println(castCuentaDebitoJuan2.getListaTransaccion());
            System.out.println(castCuentaDebitoJuan3.getListaTransaccion());

            // DTOs listing (from second block, using the single billeteraVirtual instance)
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

            // Final launch of the JavaFX application
            launch();
        }

    }
