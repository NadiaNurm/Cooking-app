module com.example.cooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mybatis;


    opens com.example.cooking to javafx.fxml;
    exports com.example.cooking;
}