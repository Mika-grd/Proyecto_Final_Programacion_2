module co.edu.uniquindio.poo.proyecto_final_programacion_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;
    requires javafx.swing;

    opens co.edu.uniquindio.poo.proyecto_final_programacion_2 to javafx.fxml;
    opens co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers to javafx.fxml;
    opens co.edu.uniquindio.poo.proyecto_final_programacion_2.model.base to javafx.base;


    exports co.edu.uniquindio.poo.proyecto_final_programacion_2;
    exports co.edu.uniquindio.poo.proyecto_final_programacion_2.Controllers to javafx.fxml;
    opens co.edu.uniquindio.poo.proyecto_final_programacion_2.model.reportes to javafx.base;
}
