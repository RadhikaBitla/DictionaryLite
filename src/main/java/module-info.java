module com.example.dictionary_miniproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dictionary_miniproject to javafx.fxml;
    exports com.example.dictionary_miniproject;
}