package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import java.util.List;

public class InvisibleButtons {

    public static void renderInvisibleButtons(List<Button> boutonsComplets, Button bouton) {

        if (bouton.getText().equals("Nouvelle Partie")) {
            for (Button b : boutonsComplets) {
                if (b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                    b.setVisible(true);
                    b.setDisable(false);
                } else if (b.getText().equals("Video") || b.getText().equals("Audio")) {
                    b.setVisible(false);
                    b.setDisable(true);
                }
            }
        } else if (bouton.getText().equals("Paramètres")) {
            for (Button b : boutonsComplets) {
                if (b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur")) {
                    b.setVisible(false);
                    b.setDisable(true);
                } else if (b.getText().equals("Video") || b.getText().equals("Audio")) {
                    b.setVisible(true);
                    b.setDisable(false);
                }
            }
        } else {
            for (Button b : boutonsComplets) {
                if (b.getText().equals("Campagne") || b.getText().equals("Jouer contre l'ordinateur") || b.getText().equals("Joueur contre joueur") || b.getText().equals("Audio") || b.getText().equals("Video")) {
                    b.setVisible(false);
                    b.setDisable(true);
                }
            }
        }
    }
}