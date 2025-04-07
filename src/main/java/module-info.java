module co.edu.uniquindio.poo.proyecto_final_programacion_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.proyecto_final_programacion_2 to javafx.fxml;
    exports co.edu.uniquindio.poo.proyecto_final_programacion_2;
}