package org.example.milleniumproject.model;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
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
                "-fx-background-color: transparent; " + // Couleur de fond transparente
                "-fx-border-color: white; " + // Couleur de contour blanc
                "-fx-border-width: 3px;"); // Épaisseur du contour

        // Définir un CellFactory pour styliser les éléments de la liste (texte noir)
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

        // Définir le style pour l'élément sélectionné dans le ComboBox (texte blanc)
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
        lancerPartieButton.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
        lancerPartieButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Cardo", FontWeight.NORMAL, 20));
        messageLabel.setStyle("-fx-text-fill: white;");
        messageLabel.setPrefWidth(250);
        messageLabel.setPrefHeight(30);

        lancerPartieButton.setOnAction(e -> {
            String fileChoisi = comboBox.getValue();
            if (fileChoisi != null) {
                LoadPartyCall loadPartyCall = new LoadPartyCall(primaryStage, fileChoisi);
                primaryStage.getScene().equals(loadPartyCall);
            } else {
                // Aucun fichier sélectionné dans la ComboBox
                messageLabel.setText("Aucun fichier sélectionné.");
            }
        });

        // Créer un VBox pour organiser les éléments verticalement
        VBox vbox = new VBox(20); // Espacement de 10 entre les éléments
        vbox.getChildren().addAll(comboBox, lancerPartieButton); // Ajouter le ComboBox et le bouton au VBox
        vbox.setAlignment(Pos.CENTER);

        // Ajouter la VBox et le bouton retour au StackPane
        getChildren().addAll(vbox, retourButton);
    }
}
