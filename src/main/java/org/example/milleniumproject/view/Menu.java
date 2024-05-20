package org.example.milleniumproject.view;

import org.example.milleniumproject.presentation.BG;
import org.example.milleniumproject.model.ButtonsMenu;
import org.example.milleniumproject.model.Management;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Classe gérant l'affichage du menu principal de l'application.
 * Elle permet d'afficher les boutons du menu sur la scène principale.
 */
public class Menu {

    /**
     * Affiche le menu principal sur la scène principale.
     *
     * @param primaryStage La fenêtre principale de l'application.
     */
    public void afficherMenu(Stage primaryStage) {

        // Affichage de la scène principale + nettoyage de la scène
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().clear();

        // Ajout du fond d'écran du menu
        BG ground = new BG("src/main/resources/BGMENU.png");
        root.getChildren().add(ground);

        // Création des boutons du menu
        ButtonsMenu buttonsMenuCreator = new ButtonsMenu();
        List<Button> boutonsComplets = buttonsMenuCreator.creerBoutons();
        List<Button> boutons1 = new ArrayList<>(boutonsComplets.subList(0, 5)); // Les 5 premiers boutons
        List<Button> boutons2 = new ArrayList<>(boutonsComplets.subList(5, boutonsComplets.size())); // Les 5 suivants

        // Rendre les sous-boutons invisibles et non-cliquables
        for (Button bouton : boutons2) {
            bouton.setVisible(false);
            bouton.setDisable(true);
        }

        // Créer une VBox pour les 2 listes de boutons
        VBox boutonsLayout = createButtonVBox(boutons1);
        VBox boutonsLayout1 = createButtonVBox(boutons2);

        // Créer une HBox pour contenir les VBox des boutons
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        HBox.setMargin(boutonsLayout, new Insets(0, 0, 0, 0.2734*screenWidth)); // Marge de 350 pixels à gauche

        // Ajouter les VBox à la HBox
        hbox.getChildren().addAll(boutonsLayout, boutonsLayout1);

        // Ajouter la HBox à la pile
        root.getChildren().add(hbox);

        // Maximiser la fenêtre
        primaryStage.setMaximized(true);

        // Gestion des événements pour les boutons
        Management.gererEvenements(primaryStage, boutonsComplets);
    }

    /**
     * Crée une VBox pour contenir une liste de boutons.
     *
     * @param buttons La liste de boutons à inclure dans la VBox.
     * @return La VBox contenant les boutons.
     */
    private VBox createButtonVBox(List<Button> buttons) {
        VBox vbox = new VBox(0.0417*screenHeight); // Espacement vertical de 30 pixels entre les boutons
        vbox.setAlignment(Pos.CENTER); // Centrer les boutons
        vbox.setPadding(new Insets(0.1389*screenHeight, 0, 0, 0)); // Ajouter un padding-top de 100 pixels
        vbox.setMaxSize(0.3125*screenWidth, 0.4167*screenHeight); // Taille maximale de la boîte de boutons
        vbox.getChildren().addAll(buttons);
        return vbox;
    }
}