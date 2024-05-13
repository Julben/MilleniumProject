/*package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChargerPartie {
    public List<String> chargerPartieDepuisFichier() {
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
        int chronoselect=0;
        int BGselect=0;
        //String nomFichier = "test.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader("Save/test.txt"))) {
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
                        //gridPane.add(button, 0, 0); // Ajoutez le bouton à la grille, assurez-vous de spécifier la position correcte
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
                } else if (ligne.startsWith("ChronoSelect=")) {
                    chronoselect=Integer.parseInt(ligne.split("=")[1].trim());
                }else if (ligne.startsWith("BGselect=")) {
                    BGselect=Integer.parseInt(ligne.split("=")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>(Arrays.asList(avatar1, avatar2,rank1,rank2,ship1,ship2,name1,name2,String.valueOf(currentPlayer),String.valueOf(turn),String.valueOf(chronoselect),String.valueOf(BGselect)));

    }
}*/

