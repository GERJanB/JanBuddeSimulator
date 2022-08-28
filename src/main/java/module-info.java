module com.example.gamereferee {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gamereferee to javafx.fxml;
    exports com.gamereferee;
}