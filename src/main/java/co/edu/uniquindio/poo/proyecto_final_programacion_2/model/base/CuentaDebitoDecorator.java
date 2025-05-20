package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

public class CuentaDebitoDecorator {


    private CuentaDebito cuentaDecorada;


    public CuentaDebitoDecorator(CuentaDebito cuentaDecorada) {
        this.cuentaDecorada = cuentaDecorada;
        agregarCategoriasPredeterminadas();

    }

    private void agregarCategoriasPredeterminadas() {
        System.out.println("Añadiendo categorías predeterminadas a la cuenta " + cuentaDecorada.getNumCuenta() + "...");
        cuentaDecorada.getListaCategorias().add(new Categoria("1", "Alimentos", "Gastos de comida", new Presupuesto("1", "ALimentos", 0)));
        cuentaDecorada.getListaCategorias().add(new Categoria("2","Transporte", "Gastos de transporte", new Presupuesto("1", "Transporte", 0)));
        cuentaDecorada.getListaCategorias().add(new Categoria("3","Entretenimiento", "Ocio y diversión", new Presupuesto("1", "Entretenimiento", 0)));
        System.out.println("Categorías predeterminadas añadidas.");
    }


}
