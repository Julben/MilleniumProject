package org.example.milleniumproject.view;

import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import org.example.milleniumproject.model.BG;
import org.example.milleniumproject.model.BoutonsContainerPosition;
import org.example.milleniumproject.model.ButtonIAFactory;

public class Jcj extends Pane {

    public Jcj(Stage primaryStage) {
        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        ButtonIAFactory buttonFactory = new ButtonIAFactory();
        String[] imagePaths = {"BGAUDIO.png", "BGVIDEO.png", "BGProfil.png"};
        var a = buttonFactory.createImageButtons(imagePaths);
        BoutonsContainerPosition boutonsContainer1 = new BoutonsContainerPosition(30,950,800);
        boutonsContainer1.getChildren().addAll(a);
        getChildren().add(boutonsContainer1);// Ajout du conteneur de boutons à Jcia

        ButtonIAFactory buttonFactory1 = new ButtonIAFactory();
        String[] imagePaths1 = {"Naboo.png", "Coruscant.png", "Mustafar.png"};
        var b = buttonFactory1.createImageButtons(imagePaths1);
        BoutonsContainerPosition boutonsContainer2=new BoutonsContainerPosition(30,950,550);
        boutonsContainer2.getChildren().addAll(b); // Ajout des boutons à la vue de Jcia
        getChildren().add(boutonsContainer2);// Ajout du conteneur de boutons à Jcia
    }
}
