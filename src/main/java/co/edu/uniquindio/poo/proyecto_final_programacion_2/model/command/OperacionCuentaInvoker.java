package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.command;

public class OperacionCuentaInvoker {
    private ComandoOperacionCuenta comando;

    public void setComando(ComandoOperacionCuenta comando) {
        this.comando = comando;
    }

    public boolean ejecutarComando() {
        if (comando == null) {
            System.out.println("No hay comando configurado para ejecutar.");
            return false;
        }
        return comando.ejecutar();
    }
}
