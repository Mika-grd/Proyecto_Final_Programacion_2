package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaCategoriasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.CuentaDebitoBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.DirectorCuentasBuilder;
import co.edu.uniquindio.poo.proyecto_final_programacion_2.model.builder.ICuentaDebitoConCategoriasBuilder;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para CuentaDebito.
 */
class CuentaDebitoTest {

    /**
     * Prueba que verifica el cálculo del saldo total
     *
     */
    @Test
    public void testCalcularSaldoTotal() {
        // Crear el usuario asociado a la cuenta
        Usuario usuario = new Usuario("Carlos", "U002", "carlos@correo.com", "1122334455", "clave456");

        // Crear presupuesto y categoría "para ahorrar"
        Presupuesto presupuesto = new Presupuesto("P003", "Ahorro Mensual", 300.0);
        Categoria categoriaAhorro = new Categoria("C005", "para ahorrar", "Dinero reservado para ahorro", presupuesto);

        // Construir cuenta débito con una categoría usando el director
        DirectorCuentasBuilder director = new DirectorCuentasBuilder();
        CuentaCategoriasBuilder builder = new CuentaCategoriasBuilder();
        Cuenta cuenta = director.cuentaDebitoConUnaCategoria(builder, "CD002", 123456, usuario, categoriaAhorro);

        // Validar que es CuentaDebito y castearla
        assertTrue(cuenta instanceof CuentaDebito);
        CuentaDebito cuentaDebito = (CuentaDebito) cuenta;

        // Asignar un saldo a la cuenta
        cuentaDebito.setSaldo(200.0);

        // Ejecutar el metodo que se desea probar
        cuentaDebito.calcularSaldoTotal();

        // Verificar que el saldo total es la suma del saldo más el monto del presupuesto de la categoría
        // saldoTotal = 200 (saldo) + 300 (presupuesto) = 500
        assertEquals(500.0, cuentaDebito.getSaldoTotal(), 0.001);
    }

    /**
     * Prueba unitaria para el metodo agregarSaldo de la clase CuentaDebito.
     *
     */
    @Test
    void agregarSaldo() {
        // Crear el usuario asociado a la cuenta
        Usuario usuario = new Usuario("Carlos", "U002", "carlos@correo.com", "1122334455", "clave456");

        // Crear presupuesto y categoría "para ahorrar"
        Presupuesto presupuesto = new Presupuesto("P003", "Ahorro Mensual", 0);
        Categoria categoriaAhorro = new Categoria("C005", "para ahorrar", "Dinero reservado para ahorro", presupuesto);

        // Construir cuenta débito con una categoría usando el director
        DirectorCuentasBuilder director = new DirectorCuentasBuilder();
        CuentaCategoriasBuilder builder = new CuentaCategoriasBuilder();
        Cuenta cuenta = director.cuentaDebitoConUnaCategoria(builder, "CD002", 123456, usuario, categoriaAhorro);

        // Validar que es CuentaDebito y castearla
        assertTrue(cuenta instanceof CuentaDebito);
        CuentaDebito cuentaDebito = (CuentaDebito) cuenta;
        // Verificar saldo inicial
        assertEquals(0.0, cuentaDebito.getSaldo(), 0.01, "El saldo inicial debe ser 0");

        // Agregar saldo
        cuentaDebito.agregarSaldo(500.0);

        // Verificar nuevo saldo
        assertEquals(500.0, cuentaDebito.getSaldo(), 0.01, "El saldo debe aumentar a 500.0");
    }

    /**
     * Prueba unitaria para el metodo retirarSaldo de la clase CuentaDebito.
     *
     */
    @Test
    void retirarSaldo() {
        // Crear el usuario asociado a la cuenta
        Usuario usuario = new Usuario("Carlos", "U002", "carlos@correo.com", "1122334455", "clave456");

        // Crear presupuesto y categoría "para ahorrar"
        Presupuesto presupuesto = new Presupuesto("P003", "Ahorro Mensual", 0);
        Categoria categoriaAhorro = new Categoria("C005", "para ahorrar", "Dinero reservado para ahorro", presupuesto);

        // Construir cuenta débito con una categoría usando el director
        DirectorCuentasBuilder director = new DirectorCuentasBuilder();
        CuentaCategoriasBuilder builder = new CuentaCategoriasBuilder();
        Cuenta cuenta = director.cuentaDebitoConUnaCategoria(builder, "CD002", 123456, usuario, categoriaAhorro);

        // Validar que es CuentaDebito y castearla
        assertTrue(cuenta instanceof CuentaDebito);
        CuentaDebito cuentaDebito = (CuentaDebito) cuenta;

        // Agregar saldo antes de retirar
        cuentaDebito.agregarSaldo(500.0);
        assertEquals(500.0, cuentaDebito.getSaldo(), 0.01, "El saldo debe ser 500 antes de retirar");

        // Retirar monto válido
        String mensajeExitoso = cuentaDebito.retirarSaldo(200.0);
        assertEquals("Exitoso", mensajeExitoso, "El retiro debería ser exitoso");
        assertEquals(300.0, cuentaDebito.getSaldo(), 0.01, "El saldo debe ser 300 después del retiro");

        // Retirar monto mayor al saldo
        String mensajeFallido = cuentaDebito.retirarSaldo(400.0);
        assertEquals("No hay suficiente saldo disponible.", mensajeFallido, "El retiro debe fallar por saldo insuficiente");
        assertEquals(300.0, cuentaDebito.getSaldo(), 0.01, "El saldo no debe cambiar tras intento fallido");
    }

    /**
     * Prueba unitaria para el metodo depositarACategoria de la clase CuentaDebito.
     *
     */
    @Test
    void depositoACategoria() {
        // Crear el usuario asociado a la cuenta
        Usuario usuario = new Usuario("Carlos", "U002", "carlos@correo.com", "1122334455", "clave456");

        // Crear presupuesto y categoría "para ahorrar"
        Presupuesto presupuesto = new Presupuesto("P003", "Ahorro Mensual", 0);
        Categoria categoriaAhorro = new Categoria("C005", "para ahorrar", "Dinero reservado para ahorro", presupuesto);

        // Construir cuenta débito con una categoría usando el patrón Builder
        DirectorCuentasBuilder director = new DirectorCuentasBuilder();
        CuentaCategoriasBuilder builder = new CuentaCategoriasBuilder();
        Cuenta cuenta = director.cuentaDebitoConUnaCategoria(builder, "CD002", 123456, usuario, categoriaAhorro);

        // Confirmar que se creó correctamente una CuentaDebito
        assertTrue(cuenta instanceof CuentaDebito);
        CuentaDebito cuentaDebito = (CuentaDebito) cuenta;

        // Cargar la cuenta con saldo inicial
        cuentaDebito.agregarSaldo(500.0);
        assertEquals(500.0, cuentaDebito.getSaldo(), 0.01);

        // Caso 1: Depósito exitoso en la categoría existente
        String mensaje = cuentaDebito.depositoACategoria(categoriaAhorro, 200.0);
        assertEquals("Exitoso", mensaje);
        assertEquals(300.0, cuentaDebito.getSaldo(), 0.01, "El saldo debe reducirse a 300 después del depósito");
        assertEquals(200.0, categoriaAhorro.getPresupuesto().getMontoActual(), 0.01, "El presupuesto debe actualizarse correctamente");

        // Caso 2: Intento de depósito con saldo insuficiente
        String mensajeInsuficiente = cuentaDebito.depositoACategoria(categoriaAhorro, 400.0);
        assertEquals("No hay suficiente saldo disponible.", mensajeInsuficiente);
        assertEquals(300.0, cuentaDebito.getSaldo(), 0.01, "El saldo debe permanecer igual tras el intento fallido");
        assertEquals(200.0, categoriaAhorro.getPresupuesto().getMontoActual(), 0.01, "El presupuesto tampoco debe cambiar");

        // Caso 3: Depósito a una categoría que no está en la lista de la cuenta
        Presupuesto otroPresupuesto = new Presupuesto("P004", "Viaje", 0);
        Categoria categoriaInexistente = new Categoria("C006", "viaje", "Ahorro para vacaciones", otroPresupuesto);
        String mensajeInvalido = cuentaDebito.depositoACategoria(categoriaInexistente, 100.0);
        assertEquals("La categoria no existe.", mensajeInvalido);
    }
}