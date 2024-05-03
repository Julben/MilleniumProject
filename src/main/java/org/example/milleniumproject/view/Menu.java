package org.example.milleniumproject.view;

import org.example.milleniumproject.model.BG;
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

public class Menu {

    public void afficherMenu(Stage primaryStage) {
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().clear(); // Supprime tous les nœuds existants dans la pile

        // Ajouter l'ImageView du menu à la pile
        BG ground = new BG("src/main/resources/TestBckgrnd.png");
        root.getChildren().add(ground);

        // Création des boutons du menu
        ButtonsMenu buttonsMenuCreator = new ButtonsMenu();
        List<Button> boutonsComplets = buttonsMenuCreator.creerBoutons();
        List<Button> boutons1 = new ArrayList<>(boutonsComplets.subList(0, 5)); // Les 5 premiers boutons
        List<Button> boutons2 = new ArrayList<>(boutonsComplets.subList(5, boutonsComplets.size())); // Les 5 suivants

        // Rendre les boutons2 invisibles et non-cliquables
        for (Button bouton : boutons2) {
            bouton.setVisible(false);
            bouton.setDisable(true);
        }

        VBox boutonsLayout = createButtonVBox(boutons1);
        VBox boutonsLayout1 = createButtonVBox(boutons2);

        // Créer une HBox pour contenir les VBox des boutons
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        HBox.setMargin(boutonsLayout, new Insets(0, 0, 0, 350)); // Marge de 50 pixels à gauche

        // Ajouter les VBox à la HBox
        hbox.getChildren().addAll(boutonsLayout, boutonsLayout1);

        // Ajouter la HBox à la pile
        root.getChildren().add(hbox);

        // Maximiser la fenêtre
        primaryStage.setMaximized(true);

        // Gestion des événements pour les boutons
        Management.gererEvenements(primaryStage, boutonsComplets);
    }

    private VBox createButtonVBox(List<Button> buttons) {
        VBox vbox = new VBox(30); // Espacement vertical de 30 pixels entre les boutons
        vbox.setAlignment(Pos.CENTER); // Centrer les boutons
        vbox.setPadding(new Insets(100, 0, 0, 0)); // Ajouter un padding-top de 100 pixels
        vbox.setMaxSize(400, 300); // Taille maximale de la boîte de boutons
        vbox.getChildren().addAll(buttons);
        return vbox;
    }

}