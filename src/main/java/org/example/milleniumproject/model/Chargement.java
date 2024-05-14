package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;


public class Chargement extends StackPane {

    public Chargement(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG();
        setBackground(ground.getCustomBackground());

        ComboBox<String> comboBox = new ComboBox<>();

        // Chemin du dossier contenant les fichiers .txt
        String dossierSave = "Save";

        File dossier = new File(dossierSave);

        if (dossier.exists() && dossier.isDirectory()) {
            // Liste des fichiers dans le dossier
            File[] fichiers = dossier.listFiles();

            if (fichiers != null) {
                // Parcourir les fichiers pour ne garder que les fichiers .txt
                for (File fichier : fichiers) {
                    if (fichier.isFile() && fichier.getName().endsWith(".txt")) {
                        // Ajouter le nom de fichier sans l'extension .txt dans le ComboBox
                        String nomFichier = fichier.getName().substring(0, fichier.getName().lastIndexOf('.'));
                        comboBox.getItems().add(nomFichier);
                    }
                }
            }
        } else {
            System.err.println("Le dossier 'Save' n'existe pas ou n'est pas un dossier valide.");
        }

        // Appliquer un style CSS au ComboBox
        comboBox.setStyle("-fx-pref-width: 200px; " + // Largeur préférée
                "-fx-pref-height: 50px; " + // Hauteur préférée
                "-fx-background-color: transparent; " + // Couleur de fond blanche
                "-fx-border-color: white; " + // Couleur de contour noir
                "-fx-border-width: 3px;"); // Épaisseur du contour


        Button lancerPartieButton = new Button("Lancer la partie");
        lancerPartieButton.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
        lancerPartieButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Ajouter un événement pour gérer le clic sur le bouton "Lancer la partie"
        lancerPartieButton.setOnAction(e -> {
             String fileChoisi = comboBox.getValue();
            if (fileChoisi != null) {

                LoadPartyCall loadPartyCall = new LoadPartyCall(primaryStage, fileChoisi);
                primaryStage.getScene().equals(loadPartyCall);
            } else {
                // Aucun fichier sélectionné dans la ComboBox
                System.err.println("Aucun fichier sélectionné.");
            }

        });



        // Créer un VBox pour organiser les éléments verticalement
        VBox vbox1 = new VBox(20); // Espacement de 10 entre les éléments
        vbox1.getChildren().addAll(comboBox, lancerPartieButton); // Ajouter le ComboBox et le bouton au VBox

        vbox1.setAlignment(Pos.CENTER_RIGHT);


        VBox vbox2 = new VBox(retourButton);
        vbox2.setAlignment(Pos.TOP_LEFT);

        // Créer une HBox pour placer les deux VBox horizontalement
        HBox hbox = new HBox(500);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(vbox2, vbox1);

        // Ajouter la HBox à la StackPane
        getChildren().add(hbox);
        HBox.setMargin(vbox1, new Insets(0,1000 , 0, 0));
        HBox.setMargin(vbox2, new Insets(0, 0, 0, 0));

    }


}

