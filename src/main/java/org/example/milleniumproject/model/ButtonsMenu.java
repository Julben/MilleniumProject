package org.example.milleniumproject.model;

import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Classe utilitaire pour créer des boutons de menu avec un style prédéfini.
 */
public class ButtonsMenu {

    /**
     * Crée une liste de boutons de menu avec des noms prédéfinis et un style spécifié.
     * @return Une liste de boutons de menu.
     */
    public List<Button> creerBoutons() {

        //Création des Boutons du Menu
        List<Button> boutons = new ArrayList<>();
        String[] nomsBoutons = {"Nouvelle Partie", "Charger une Partie", "Profil", "Paramètres", "Quitter", "Campagne", "Jouer contre l'ordinateur","Joueur contre joueur", "Audio", "Video"};

        for (String nom : nomsBoutons) {
            Button bouton = new Button(nom);
            ButtonsStyle.appliquerStyle(bouton, 0.28125*screenWidth, 0.0694*screenHeight, 0.0347*screenHeight); // Appliquer le style défini dans ButtonsStyle
            boutons.add(bouton);
        }
        return boutons;
    }
}
