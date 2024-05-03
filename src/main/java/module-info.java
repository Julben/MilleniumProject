module org.example.milleniumproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;



    opens org.example.milleniumproject to javafx.fxml;
    exports org.example.milleniumproject;
    exports org.example.milleniumproject.model.Buttons;
    opens org.example.milleniumproject.model.Buttons to javafx.fxml;
    exports org.example.milleniumproject.model;
    opens org.example.milleniumproject.model to javafx.fxml;
    exports org.example.milleniumproject.view;
    opens org.example.milleniumproject.view to javafx.fxml;
}