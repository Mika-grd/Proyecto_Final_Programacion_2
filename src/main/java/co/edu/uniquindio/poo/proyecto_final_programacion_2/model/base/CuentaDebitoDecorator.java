package co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base;

    public class CuentaDebitoDecorator {

        private final CuentaDebito cuentaDecorada;
        private boolean categoriasAgregadas = false;

        public CuentaDebitoDecorator(CuentaDebito cuentaDecorada) {
            this.cuentaDecorada = cuentaDecorada;
        }

        // Método público que se puede invocar en cualquier momento
        public void aplicarCategoriasPredeterminadas() {
            if (!categoriasAgregadas) {
                System.out.println("Añadiendo categorías predeterminadas a la cuenta " + cuentaDecorada.getNumCuenta() + "...");
                agregarCategoriaAlimentos();
                agregarCategoriaTransporte();
                agregarCategoriaEntretenimiento();
                System.out.println("Categorías predeterminadas añadidas.");
                categoriasAgregadas = true;
            } else {
                System.out.println("Las categorías ya fueron añadidas a la cuenta " + cuentaDecorada.getNumCuenta());
            }
        }

        private void agregarCategoriaAlimentos() {
            Categoria alimentos = new Categoria("1", "Alimentos", "Gastos de comida", new Presupuesto("1", "Alimentos", 0));
            cuentaDecorada.getListaCategorias().add(alimentos);
        }

        private void agregarCategoriaTransporte() {
            Categoria transporte = new Categoria("2", "Transporte", "Gastos de transporte", new Presupuesto("2", "Transporte", 0));
            cuentaDecorada.getListaCategorias().add(transporte);
        }

        private void agregarCategoriaEntretenimiento() {
            Categoria entretenimiento = new Categoria("3", "Entretenimiento", "Ocio y diversión", new Presupuesto("3", "Entretenimiento", 0));
            cuentaDecorada.getListaCategorias().add(entretenimiento);
        }
    }


