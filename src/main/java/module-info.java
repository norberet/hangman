module com.example.hangman_ver2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.hangman_ver2 to javafx.fxml;
    exports com.example.hangman_ver2;
}