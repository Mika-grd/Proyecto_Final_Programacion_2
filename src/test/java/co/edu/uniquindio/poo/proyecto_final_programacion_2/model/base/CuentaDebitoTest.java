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
        // Crear un usuario para que se asocie a la cuenta
        Usuario usuario = new Usuario("Juan", "123", "juan@gmail.com", "3214567890", "123");

        // Crear una cuenta débito con un saldo inicial de 1000
        CuentaDebito cuentaDebito = new CuentaDebito("123456", "Nequi", 123456, usuario, 10000);

        // Agregar 5000 al saldo
        cuentaDebito.agregarSaldo(5000);

        // Verificar que el saldo ahora sea 15000
        assertEquals(15000, cuentaDebito.getSaldo(), 0.01);
    }

    @Test
    void retirarSaldo() {
    }

    @Test
    void depositoACategoria() {
    }
}