package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import org.example.milleniumproject.presentation.ButtonsStyle;
import java.util.ArrayList;
import java.util.List;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Classe pour créer des boutons de menu avec un style.
 */
public class ButtonsMenu {

    /**
     * Crée la liste de boutons du menu.
     * @return La liste de boutons de menu.
     */
    public List<Button> creerBoutons() {

        //Création des Boutons du Menu
        List<Button> boutons = new ArrayList<>();
        String[] nomsBoutons = {"Nouvelle Partie", "Charger une Partie", "Profil", "Paramètres", "Quitter", "Campagne", "Jouer contre l'ordinateur","Joueur contre joueur", "Audio", "Video"};

        for (String nom : nomsBoutons) {
            Button bouton = new Button(nom);
            ButtonsStyle.appliquerStyle(bouton, 0.28125*screenWidth, 0.0694*screenHeight, 0.0347*screenHeight);
            boutons.add(bouton);
        }
        return boutons;
    }
}