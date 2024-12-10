module org.example.quuiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.quuiz to javafx.fxml;
    exports org.example.quuiz;
}