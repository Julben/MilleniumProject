package org.example.milleniumproject.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.milleniumproject.presentation.BG;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.example.milleniumproject.model.Constant.screenHeight;

public class Chargement extends StackPane {
    private ChargerPartie chargerPartie = new ChargerPartie();

    public Chargement(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        try {
            Image image = new Image(new FileInputStream("src/main/resources/BGChargement.png"));
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
            Background background = new Background(backgroundImage);
            setBackground(background);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ComboBox<String> comboBox = new ComboBox<>();

        String dossierSave = "Save";

        File dossier = new File(dossierSave);

        if (dossier.exists() && dossier.isDirectory()) {
            File[] fichiers = dossier.listFiles();

            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (fichier.isFile() && fichier.getName().endsWith(".txt")) {
                        String nomFichier = fichier.getName().substring(0, fichier.getName().lastIndexOf('.'));
                        comboBox.getItems().add(nomFichier);
                    }
                }
            }
        } else {
            System.err.println("Le dossier 'Save' n'existe pas ou n'est pas un dossier valide.");
        }

        comboBox.setStyle("-fx-pref-width: 200px; " +
                "-fx-pref-height: 50px; " +
                "-fx-background-color: rgba(0,0,0,0.3); " +
                "-fx-border-color: white; " +
                "-fx-border-width: 3px;");

        comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item);
                            setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                        }
                    }
                };
            }
        });

        comboBox.setButtonCell(new ListCell<String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
                }
            }
        });
        Button lancerPartieButton = new Button("Lancer la partie");
        lancerPartieButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.02777*screenHeight));
        lancerPartieButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        addDropShadowEffect(lancerPartieButton);

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0347222*screenHeight));
        messageLabel.setStyle("-fx-text-fill: white;");
        addDropShadowEffect(messageLabel);



        lancerPartieButton.setOnAction(e -> {
            String fileChoisi = comboBox.getValue();
            List<Object> allInfo = chargerPartie.chargerPartieDepuisFichier(fileChoisi);
            boolean Ia = (boolean) allInfo.get(13);
            if (fileChoisi != null) {

                int valeur = 0;
                if(Ia == true){
                    valeur= 1;
                }else if(Ia == false){
                    valeur= 0;
                }
                LoadPartyCall loadPartyCall = new LoadPartyCall(primaryStage,valeur,fileChoisi);
                primaryStage.getScene().equals(loadPartyCall);
            } else {
                // Aucun fichier sélectionné dans la ComboBox
                messageLabel.setText("Aucun fichier sélectionné");
            }
        });

        VBox vbox = new VBox(0.027777*screenHeight);
        vbox.getChildren().addAll(messageLabel, comboBox, lancerPartieButton);
        vbox.setAlignment(Pos.CENTER);
        getChildren().addAll(vbox, retourButton);
    }

    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        node.setEffect(dropShadow);
    }
}