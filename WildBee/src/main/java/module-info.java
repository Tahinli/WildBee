module com.wildbee.wildbee {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;


    opens com.wildbee.wildbee to javafx.fxml;
    exports com.wildbee.wildbee;
}