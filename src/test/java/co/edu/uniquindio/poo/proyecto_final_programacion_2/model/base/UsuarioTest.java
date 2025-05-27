package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    /**
     * Prueba que verifica que un objeto válido se agrega correctamente a la lista.
     */
    @Test
    public void testAgregarObjetoExitoso() {
        Usuario usuario = new Usuario("Juan", "123", "juan@gmail.com", "98765", "123");

        // Crear una lista vacía de tipo String (por ejemplo, lista de usuarios)
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();

        // Agregar un objeto válido
        String resultado = usuario.agregarObjeto(usuario, listaUsuarios);

        // Verificar que la operación fue exitosa
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que no se puede agregar un objeto null a la lista.
     */
    @Test
    public void testAgregarObjetoNoExitoso() {
        Usuario usuario = new Usuario(null, "A001", "ana@gmail.com", "0988888888", "123");
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();

        // Ejecutar metodo agregarObjeto con objeto null
        String resultado = usuario.agregarObjeto(null, listaUsuarios);

        // Verificar que el metodo retorno "No exitoso"
        assertEquals("No exitoso", resultado);
    }



    /**
     * Prueba que verifica que un objeto válido es reemplazado correctamente
     * por uno nuevo en la lista usando el metodo editarObjeto.
     */
    @Test
    public void testEditarObjetoExitoso() {
        //usuario original
        Usuario usuario = new Usuario("Ana", "123", "ana@gmail.com", "123456789", "123");

        //lista a la que se va a añadir
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();
        Usuario usuarioNuevo = new Usuario("Ana María", "123", "ana@gmail.com", "22222222", "123");

        //Añade el administrador original
        listaUsuarios.add(usuario);

        //ejecuta el metodo de editar
        String resultado = usuario.editarObjeto(usuario, usuarioNuevo, listaUsuarios);

        //son las verificaciones que se espera que el resultado sea exitoso
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que si se intenta editar con un objeto null,
     * la operación no es exitosa.
     */
    @Test
    public void testEditarObjetoNoExitosoPorNull() {
        Usuario usuario = new Usuario("Ana", "A01", "anita@gmail.com", "123456789", "111");

        LinkedList<Usuario> listaUsuarios = new LinkedList<>();

        Usuario usuarioNuevo = new Usuario("Ana", "A02", "ana@gmail.com", "11111111", "111");

        listaUsuarios.add(usuarioNuevo);

        // Intentar editar pasando objeto null
        String resultado1 = usuario.editarObjeto(null, usuarioNuevo, listaUsuarios);
        assertEquals("No exitoso", resultado1);

        // Intentar editar pasando nuevo objeto null
        String resultado2 = usuario.editarObjeto(usuario, null, listaUsuarios);
        assertEquals("No exitoso", resultado2);
    }


    /**
     * Prueba que verifica que un objeto válido se elimina correctamente de la lista.
     */
    @Test
    public void testEliminarObjetoExitoso() {
        // Usuario :)
        Usuario usuario = new Usuario("Raul", "123", "raul@gmail.com", "123456789", "123");

        // Lista que contiene al usuario a eliminar
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();
        listaUsuarios.add(usuario);

        // Ejecuta el metodo de eliminación
        String resultado = usuario.eliminarObjeto(usuario, listaUsuarios);

        // Verificaciones: se espera que la eliminación sea exitosa
        assertEquals("Exitoso", resultado);
    }

    /**
     * Prueba que verifica que si se intenta eliminar un objeto null,
     *
     */
    @Test
    public void testEliminarObjetoNoExitoso() {
        // usuario
        Usuario usuario = new Usuario("Ana", "123", "ana@correo.com", "123456789", "123");

        // Lista usuarios
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();
        listaUsuarios.add(usuario);

        // Intenta eliminar un objeto null
        String resultado = usuario.eliminarObjeto(null, listaUsuarios);

        // Verificaciones: la operación no debe ser exitosa
        assertEquals("No exitoso", resultado);
    }


    /**
     * Prueba que verifica que se puede encontrar correctamente un objeto
     * en la lista utilizando su ID.
     */
    @Test
    public void testBuscarObjetoExitoso() {
        // se crea una lista usuarios y será buscado un usuario
        Usuario usuario = new Usuario("Ana", "123", "ana@gmail.com", "123456789", "123");
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();
        listaUsuarios.add(usuario);

        // Ejecuta el metodo de búsqueda
        Object resultado = usuario.buscarObjeto("123", listaUsuarios);

        // Verificaciones: debe encontrar el objeto correcto
        assertNotNull(resultado, "Debe encontrar un objeto");

        Usuario usuarioEncontrado = (Usuario) resultado;
        assertEquals("123", usuarioEncontrado.getId(), "El ID del usuario debe coincidir");
    }

    /**
     * Prueba que verifica que si no se encuentra un objeto con el ID proporcionado,
     * el metodo retorna null.
     */
    @Test
    public void testBuscarObjetoNoEncontrado() {
        // Lista con un usuario distinto
        Usuario usuario = new Usuario("Carlos", "456", "carlos@gmail.com", "987654321", "456");
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();
        listaUsuarios.add(usuario);

        // Ejecuta el metodo de búsqueda con un ID que no existe
        Object resultado = usuario.buscarObjeto("999", listaUsuarios);

        // Verificación: no debe encontrar nada
        assertNull(resultado, "No debe encontrar ningún objeto con ese ID");
    }
}