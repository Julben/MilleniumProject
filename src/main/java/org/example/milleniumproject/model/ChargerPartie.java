package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChargerPartie {
    String nomFichier = "test.txt";
    public static Party chargerPartieDepuisFichier(String nomFichier,GridPane gridPane, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2) {
        GridPane gridPane = new GridPane();
        String avatar1 = "";
        String avatar2 = "";
        String rank1 = "";
        String rank2 = "";
        String ship1 = "";
        String ship2 = "";
        String name1 = "";
        String name2 = "";
        int currentPlayer = 1;
        int turn = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.startsWith("setgraphique=")) {
                    String[] parts = ligne.split("=");
                    boolean aImage = Boolean.parseBoolean(parts[1].trim());
                    if (aImage) {
                        String imageUrl = parts[2].trim();
                        Image image = new Image(imageUrl);
                        ImageView imageView = new ImageView(image);
                        Button button = new Button();
                        button.setGraphic(imageView);
                        gridPane.add(button, 0, 0); // Ajoutez le bouton à la grille, assurez-vous de spécifier la position correcte
                    } else {
                        // Ajoutez un bouton vide à la grille si nécessaire
                    }
                } else if (ligne.startsWith("currentPlayer=")) {
                    currentPlayer = Integer.parseInt(ligne.split("=")[1].trim());
                } else if (ligne.startsWith("Tour=")) {
                    turn = Integer.parseInt(ligne.split("=")[1].trim());
                } else if (ligne.startsWith("Avatar1=")) {
                    avatar1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Avatar2=")) {
                    avatar2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Rank1=")) {
                    rank1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Rank2=")) {
                    rank2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Ship1=")) {
                    ship1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Ship2=")) {
                    ship2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Name1=")) {
                    name1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Name2=")) {
                    name2 = ligne.split("=")[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Créez une instance de Party avec les données chargées
// Créez une instance de Party avec les données chargées
        return new Party(gridPane, toggleGroup3, hbox3, toggleGroup2, hbox2);
    }
}

