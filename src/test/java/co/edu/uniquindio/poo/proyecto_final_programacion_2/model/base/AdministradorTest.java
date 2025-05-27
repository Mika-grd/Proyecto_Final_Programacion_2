package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    /**
     * Prueba que verifica que un objeto válido se agrega correctamente a la lista.
     */
    @Test
    public void testAgregarObjetoExitoso() {
        Administrador administrador = new Administrador("Juan", "123", "juan@gmail.com", "98765", "123");

        // Crear una lista vacía de tipo String (por ejemplo, lista de admins)
        LinkedList<Administrador> listaAdministradores = new LinkedList<>();

        // Agregar un objeto válido
        String resultado = administrador.agregarObjeto(administrador, listaAdministradores);

        // Verificar que la operación fue exitosa
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que no se puede agregar un objeto null a la lista.
     */
    @Test
    public void testAgregarObjetoNoExitoso() {
        Administrador administrador = new Administrador(null, "A001", "ana@gmail.com", "0988888888", "admin123");
        LinkedList<Administrador> listaAdministrador = new LinkedList<>();

        // Ejecutar metodo agregarObjeto con objeto null
        String resultado = administrador.agregarObjeto(null, listaAdministrador);

        // Verificar que el metodo retorno "No exitoso"
        assertEquals("No exitoso", resultado);
    }



    /**
     * Prueba que verifica que un objeto válido es reemplazado correctamente
     * por uno nuevo en la lista usando el metodo editarObjeto.
     */
    @Test
    public void testEditarObjetoExitoso() {
        //administrador original
        Administrador administrador = new Administrador("Ana", "123", "ana@gmail.com", "123456789", "123");

        //lista a la que se va a añadir
        LinkedList<Administrador> lista = new LinkedList<>();
        Administrador adminNuevo = new Administrador("Ana María", "123", "ana@gmail.com", "22222222", "123");

        //Añade el administrador original
        lista.add(administrador);

        //ejecuta el metodo de editar
        String resultado = administrador.editarObjeto(administrador, adminNuevo, lista);

        //son las verificaciones que se espera que el resultado sea exitoso
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que si se intenta editar con un objeto null,
     * la operación no es exitosa.
     */
    @Test
    public void testEditarObjetoNoExitosoPorNull() {
        Administrador administrador = new Administrador("Ana", "A01", "anita@gmail.com", "123456789", "111");

        LinkedList<Administrador> lista = new LinkedList<>();

        Administrador adminNuevo = new Administrador("Ana", "A02", "ana@gmail.com", "11111111", "111");

        lista.add(adminNuevo);

        // Intentar editar pasando objeto null
        String resultado1 = administrador.editarObjeto(null, adminNuevo, lista);
        assertEquals("No exitoso", resultado1);

        // Intentar editar pasando nuevo objeto null
        String resultado2 = administrador.editarObjeto(administrador, null, lista);
        assertEquals("No exitoso", resultado2);
    }


    /**
     * Prueba que verifica que un objeto válido se elimina correctamente de la lista.
     */
    @Test
    public void testEliminarObjetoExitoso() {
        // Administrador
        Administrador administrador = new Administrador("Raul", "123", "raul@gmail.com", "123456789", "123");

        // Lista que contiene al administrador a eliminar
        LinkedList<Administrador> listaAdministradores = new LinkedList<>();
        listaAdministradores.add(administrador);

        // Ejecuta el metodo de eliminación
        String resultado = administrador.eliminarObjeto(administrador, listaAdministradores);

        // Verificaciones: se espera que la eliminación sea exitosa
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que si se intenta eliminar un objeto null,
     *
     */
    @Test
    public void testEliminarObjetoNoExitoso() {
        // Administrador
        Administrador administrador = new Administrador("Ana", "123", "ana@correo.com", "123456789", "123");

        // Lista Administradores
        LinkedList<Administrador> listaAdministradores = new LinkedList<>();
        listaAdministradores.add(administrador);

        // Intenta eliminar un objeto null
        String resultado = administrador.eliminarObjeto(null, listaAdministradores);

        // Verificaciones: la operación no debe ser exitosa
        assertEquals("No exitoso", resultado);
    }


    /**
     * Prueba que verifica que se puede encontrar correctamente un objeto
     * en la lista utilizando su ID.
     */
    @Test
    public void testBuscarObjetoExitoso() {
        // se crea una lista administradores y será buscado un admin
        Administrador administrador = new Administrador("Ana", "123", "ana@gmail.com", "123456789", "123");
        LinkedList<Administrador> listaAdministradores = new LinkedList<>();
        listaAdministradores.add(administrador);

        // Ejecuta el metodo de búsqueda
        Object resultado = administrador.buscarObjeto("123", listaAdministradores);

        // Verificaciones: debe encontrar el objeto correcto
        assertNotNull(resultado, "Debe encontrar un objeto");

        Administrador administradorEncontrado = (Administrador) resultado;
        assertEquals("123", administradorEncontrado.getId(), "El ID del administrador debe coincidir");
    }

    /**
     * Prueba que verifica que si no se encuentra un objeto con el ID proporcionado,
     * el metodo retorna null.
     */
    @Test
    public void testBuscarObjetoNoEncontrado() {
        // Lista con un administrador distinto
        Administrador administrador = new Administrador("Carlos", "456", "carlos@gmail.com", "987654321", "456");
        LinkedList<Administrador> listaAdministradores = new LinkedList<>();
        listaAdministradores.add(administrador);

        // Ejecuta el metodo de búsqueda con un ID que no existe
        Object resultado = administrador.buscarObjeto("999", listaAdministradores);

        // Verificación: no debe encontrar nada
        assertNull(resultado, "No debe encontrar ningún objeto con ese ID");
    }
}