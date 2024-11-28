module QuizSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens org.example.quizsystem to javafx.fxml;
    exports org.example.quizsystem;
}
