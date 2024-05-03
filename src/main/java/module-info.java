module org.example.milleniumproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.prefs;


    opens org.example.milleniumproject to javafx.fxml;
    exports org.example.milleniumproject;
    exports org.example.milleniumproject.model;
    opens org.example.milleniumproject.model to javafx.fxml;
    exports org.example.milleniumproject.view;
    opens org.example.milleniumproject.view to javafx.fxml;
}