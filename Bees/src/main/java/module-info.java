module com.bees.bees {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires AnimateFX;

    opens com.bees.bees;
    exports com.bees.bees;
}