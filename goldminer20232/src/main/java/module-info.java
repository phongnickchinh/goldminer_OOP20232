module com.oop {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oop to javafx.fxml;
    exports com.oop;
}
