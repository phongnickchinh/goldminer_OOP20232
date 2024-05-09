module com.oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.oop to javafx.fxml;
    exports com.oop;
    exports com.oop.view;
}
