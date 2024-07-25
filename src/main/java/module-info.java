module com.example.classversionnew {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.classversionnew to javafx.fxml;
    exports com.example.classversionnew;
}