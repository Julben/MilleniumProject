package org.example.milleniumproject.presentation;

import javafx.scene.control.Button;
import java.util.List;

/**
 * Classe permettant de rendre certains boutons du menu invisibles et non cliquables.
 */
public class InvisibleButtons {

    /**
     * Rend certains boutons du menu invisibles et non cliquables en fonction du dernier bouton cliqué.
     * @param boutonsComplets La liste des boutons principaux du menu
     * @param bouton          Le bouton cliqué
     */
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