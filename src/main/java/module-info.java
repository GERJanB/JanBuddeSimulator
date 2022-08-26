module com.example.gamereferee {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamereferee to javafx.fxml;
    exports com.example.gamereferee;
}