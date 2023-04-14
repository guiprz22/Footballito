module com.example.footballito {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.footballito to javafx.fxml;
    exports com.example.footballito;
}