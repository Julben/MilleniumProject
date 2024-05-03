package org.example.milleniumproject.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.milleniumproject.model.Buttons.*;
import org.example.milleniumproject.model.Constant;


public class Jcia extends Pane {
    private Stage primaryStage; // référence de la scène principaleeee

    public Jcia(Stage primaryStage) {
        this.primaryStage = primaryStage;// Utilisation de Pane au lieu de StackPane
    }
    public Jcia() {
        Image backgroundImage = null; //Pour le fond d'écran
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/BGAUDIO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
        setBackground(new Background(background));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ButtonsIA buttonsIA = new ButtonsIA();// Création d'une instance de ButtonsIA
        var boutonsIA = buttonsIA.creerBoutonsia();// Obtention de la liste de boutons// Création d'un conteneur pour les boutons
        BoutonsContainerPosition boutonsContainer = new BoutonsContainerPosition(10,920,400);
        boutonsContainer.getChildren().addAll(boutonsIA);
        getChildren().add(boutonsContainer);// Ajout du conteneur de boutons à Jcia
////////////////////////////////////////////////////////////////////////////////////////////////////
        ButtonIAFactory buttonFactory = new ButtonIAFactory();
        String[] imagePaths = {"BGAUDIO.png", "BGVIDEO.png", "BGProfil.png"};
        var a = buttonFactory.createImageButtons(imagePaths);
        BoutonsContainerPosition boutonsContainer1 = new BoutonsContainerPosition(30,950,800);
        boutonsContainer1.getChildren().addAll(a);
        getChildren().add(boutonsContainer1);// Ajout du conteneur de boutons à Jcia
///////////////////////////////////////////////////////////////////////////////////////////////////////
        ButtonIAFactory buttonFactory1 = new ButtonIAFactory();
        String[] imagePaths1 = {"Naboo.png", "Coruscant.png", "Mustafar.png"};
        var b = buttonFactory1.createImageButtons(imagePaths1);
        BoutonsContainerPosition boutonsContainer2=new BoutonsContainerPosition(30,950,550);
        boutonsContainer2.getChildren().addAll(b); // Ajout des boutons à la vue de Jcia
        getChildren().add(boutonsContainer2);// Ajout du conteneur de boutons à Jcia
    }
}
