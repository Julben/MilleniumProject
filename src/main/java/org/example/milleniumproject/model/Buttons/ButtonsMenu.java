package org.example.milleniumproject.model.Buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

public class ButtonsMenu {

    public List<Button> creerBoutons() {
        List<Button> boutons = new ArrayList<>();
        String[] nomsBoutons = {"Nouvelle Partie", "Charger une Partie", "Profil", "Paramètres", "Quitter", "Campagne", "Jouer contre l'ordinateur","Joueur contre joueur", "Audio", "Video"};

        for (String nom : nomsBoutons) {
            Button bouton = new Button(nom);
            bouton.setPrefSize(360, 50); // Taille préférée des boutons (largeur x hauteur)
            bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), Insets.EMPTY)));
            bouton.setTextFill(Color.WHITE); // Couleur du texte
            bouton.setFont(Font.font("Cardo", FontWeight.BOLD, 25)); // Police et taille du texte

            // Changement de style lorsque survolé
            bouton.setOnMouseEntered(event -> {
                bouton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), Insets.EMPTY)));
                bouton.setTextFill(Color.BLACK);
            });

            // Rétablissement du style initial lorsque le curseur quitte le bouton
            bouton.setOnMouseExited(event -> {
                bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), Insets.EMPTY)));
                bouton.setTextFill(Color.WHITE);
            });

            boutons.add(bouton);
        }

        return boutons;
    }
}