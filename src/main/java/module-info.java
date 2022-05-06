module com.example.scenebuilderatm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scenebuilderatm to javafx.fxml;
    exports com.example.scenebuilderatm;
}