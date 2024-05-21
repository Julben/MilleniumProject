package org.example.milleniumproject.view;

import org.example.milleniumproject.presentation.BackGround;
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
 * Classe qui gère l'affichage du menu principal.
 * Elle sert à afficher les boutons du menu.
 */
public class Menu {

    /**
     * Affiche le menu principal en premier plan.
     *
     * @param primaryStage La scène en premier plan.
     */
    public void showMenu(Stage primaryStage) {

        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().clear();

        BackGround ground = new BackGround("src/main/resources/BGMENU.png");
        root.getChildren().add(ground);

        ButtonsMenu buttonsMenuCreator = new ButtonsMenu();
        List<Button> boutonsComplets = buttonsMenuCreator.createButtons();
        List<Button> boutons1 = new ArrayList<>(boutonsComplets.subList(0, 5));
        List<Button> boutons2 = new ArrayList<>(boutonsComplets.subList(5, boutonsComplets.size()));

        for (Button bouton : boutons2) {
            bouton.setVisible(false);
            bouton.setDisable(true);
        }

        VBox boutonsLayout = createButtonVBox(boutons1);
        VBox boutonsLayout1 = createButtonVBox(boutons2);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        HBox.setMargin(boutonsLayout, new Insets(0, 0, 0, 0.2734*screenWidth));

        hbox.getChildren().addAll(boutonsLayout, boutonsLayout1);

        root.getChildren().add(hbox);

        primaryStage.setMaximized(true);

        Management.handleEvents(primaryStage, boutonsComplets);
    }

    /**
     * Crée une VBox pour les boutons du menu.
     *
     * @param buttons La liste des boutons à placer dans la VBox.
     * @return La VBox avec les boutons du menu.
     */
    private VBox createButtonVBox(List<Button> buttons) {
        VBox vbox = new VBox(0.0417*screenHeight);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0.1389*screenHeight, 0, 0, 0));
        vbox.setMaxSize(0.3125*screenWidth, 0.4167*screenHeight);
        vbox.getChildren().addAll(buttons);
        return vbox;
    }
}