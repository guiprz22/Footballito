module com.example.footballito {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.footballito to javafx.fxml;
    exports com.example.footballito;
    exports com.example.footballito.Factory;
    opens com.example.footballito.Factory to javafx.fxml;
    exports com.example.footballito.Singleton;
    opens com.example.footballito.Singleton to javafx.fxml;
    exports com.example.footballito.Strategy;
    opens com.example.footballito.Strategy to javafx.fxml;
}