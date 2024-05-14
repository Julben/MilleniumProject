package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Menu;

import java.io.File;
import java.util.Optional;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Cette classe gère le bouton pause et l'affichage des règles du jeu.
 */
public class ButtonPause extends StackPane {

    private static boolean save;

    public static VBox quitterMenu;

    /**
     * Affiche les règles du jeu sur une StackPane semi-transparente avec un bouton pour les fermer.
     *
     * @param root La StackPane racine où afficher les règles.
     */
    public static void afficherRegles(StackPane root) {
        // Création de la StackPane pour contenir l'image et le bouton
        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // Fond semi-transparent

        // Création de l'image
        Image image = new Image("Règles.png"); // Remplacez "regles_image.png" par le chemin de votre image
        ImageView imageView = new ImageView(image);

        // Redimensionner l'image
        imageView.setFitWidth(0.9765 * screenWidth); // Largeur souhaitée
        imageView.setFitHeight(1.04167 * screenHeight); // Hauteur souhaitée

        // Création du bouton pour masquer l'image et le bouton
        Button fermerButton = new Button();

        fermerButton.setMinWidth(0.039 * screenWidth);
        fermerButton.setMinHeight(0.0694 * screenHeight);
        fermerButton.setStyle("-fx-background-color: transparent;"); // Fond transparent

        // Action du bouton pour masquer l'image et le bouton
        fermerButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            root.getChildren().remove(reglesPane); // Retirer la StackPane de la racine
        });

        StackPane.setMargin(fermerButton, new Insets(0, 0, 0.6667 * screenHeight, 0.796875 * screenWidth)); // Marge de 10 pixels

        // Ajout de l'image et du bouton à la StackPane
        reglesPane.getChildren().addAll(imageView, fermerButton);

        // Centrer la StackPane dans la fenêtre
        StackPane.setAlignment(reglesPane, Pos.CENTER);

        // Ajouter la StackPane à la racine de la scène
        root.getChildren().add(reglesPane);
    }


    /**
     * Crée un menu pour confirmer la sortie du jeu.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @return Une VBox contenant le menu de confirmation.
     */
    static VBox boutonquitter(Stage primaryStage) {
        VBox vbox = new VBox(0.04167 * screenHeight);

        Label confirmationLabel = new Label("Si vous quittez maintenant, vous ne pouvez pas sauvegarder la partie. Veuillez terminer le placement des pions avant de sauvegarder.");
        confirmationLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305 * screenHeight));
        confirmationLabel.setTextFill(Color.WHITE);

        HBox hbox = new HBox(0.05139 * screenHeight);
        Button ouiButton = new Button("Oui");
        Button nonButton = new Button("Non");

        ouiButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));
        nonButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));

        ouiButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        nonButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        nonButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            vbox.setVisible(false);
        });

        ouiButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
        });

        hbox.getChildren().addAll(ouiButton, nonButton);
        vbox.getChildren().addAll(confirmationLabel, hbox);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px;");

        // Positionnement du menu pause au centre de l'écran
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(0.02778 * screenHeight, 0, 0, 0));

        return vbox;
    }

    static VBox boutonquittersave(Stage primaryStage, GridPane gridPane, int currentPlayer, int turns, int chrono, int bg) {
        VBox vbox = new VBox(0.04167 * screenHeight);

        Label confirmationLabelsave = new Label("Voulez-vous sauvegarder la partie ?");
        confirmationLabelsave.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305 * screenHeight));
        confirmationLabelsave.setTextFill(Color.WHITE);

        HBox hbox = new HBox(0.05139 * screenHeight);
        Button ouiButton = new Button("Oui");
        Button nonButton = new Button("Non");

        ouiButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));
        nonButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));

        ouiButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        nonButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        nonButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
            save=false;
        });

        ouiButton.setOnAction(e -> {
            save = true;
            SoundPlayer.soundPlay();
            VBox dialogBox = new VBox(10);
            dialogBox.setAlignment(Pos.CENTER);

            Label fileNameLabel = new Label("Nom du fichier de sauvegarde:");
            fileNameLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
            fileNameLabel.setTextFill(Color.WHITE);
            TextField fileNameField = new TextField();
            fileNameField.setPrefSize(100,50);

            fileNameField.setStyle("-fx-background-color: transparent; " +
                    "-fx-border-color: #FF9800; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-family: 'Cardo'; " +
                    "-fx-font-size: 20; " +
                    "-fx-alignment: center;");


            Button saveButton = new Button("Sauvegarder");
            Button cancelButton = new Button("Annuler");

            saveButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));
            cancelButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));

            saveButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
            cancelButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
            cancelButton.setOnAction(event -> {
                vbox.setVisible(false);
                SoundPlayer.soundPlay();
            });

            saveButton.setOnAction(event -> {
                String fileName = fileNameField.getText().trim();
                if (!fileName.isEmpty()) {
                    String filePath = "Save/" + fileName + ".txt";
                    File file = new File(filePath);
                    if (file.exists()) {
                        Label fileExistsLabel = new Label("Le fichier existe déjà. Voulez-vous écraser le fichier existant ?");
                        fileExistsLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 20));
                        fileExistsLabel.setTextFill(Color.WHITE);

                        Button overwriteButton = new Button("Écraser");
                        Button cancelButton2 = new Button("Annuler");

                        overwriteButton.setFont(Font.font("Cardo", FontWeight.BOLD, 17));
                        cancelButton2.setFont(Font.font("Cardo", FontWeight.BOLD, 17));

                        overwriteButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
                        cancelButton2.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

                        overwriteButton.setOnAction(event1 -> {
                            SauvegardePartie sauvegardePartie = new SauvegardePartie(gridPane, ProfileData.getAvatar(1), ProfileData.getAvatar(2), ProfileData.getRank(1), ProfileData.getRank(2), ProfileData.getShip(1), ProfileData.getShip(2), ProfileData.getPlayerName(1), ProfileData.getPlayerName(2), currentPlayer, turns, chrono, bg);
                            sauvegardePartie.sauvegarderDansFichier(filePath);
                            Menu menu = new Menu();
                            menu.afficherMenu(primaryStage);
                            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
                            vbox.setVisible(false);
                        });

                        cancelButton2.setOnAction(event1 -> {
                            vbox.setVisible(false);
                            SoundPlayer.soundPlay();
                        });


                        dialogBox.getChildren().clear();
                        dialogBox.getChildren().addAll(fileExistsLabel, overwriteButton, cancelButton2);
                    } else {
                        SauvegardePartie sauvegardePartie = new SauvegardePartie(gridPane, ProfileData.getAvatar(1), ProfileData.getAvatar(2), ProfileData.getRank(1), ProfileData.getRank(2), ProfileData.getShip(1), ProfileData.getShip(2), ProfileData.getPlayerName(1), ProfileData.getPlayerName(2), currentPlayer, turns, chrono, bg);
                        sauvegardePartie.sauvegarderDansFichier(filePath);
                        Menu menu = new Menu();
                        menu.afficherMenu(primaryStage);
                        MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
                        vbox.setVisible(false);
                    }
                }
            });

            cancelButton.setOnAction(event -> vbox.setVisible(false));

            dialogBox.getChildren().addAll(fileNameLabel, fileNameField, saveButton, cancelButton);
            vbox.getChildren().clear();
            vbox.getChildren().addAll(confirmationLabelsave, dialogBox);
        });

        hbox.getChildren().addAll(ouiButton, nonButton);
        vbox.getChildren().addAll(confirmationLabelsave, hbox);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px;");

        // Positionnement du menu pause au centre de l'écran
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(0.02778 * screenHeight, 0, 0, 0));

        return vbox;
    }

    public static boolean isSave() {
        return save;
    }
}
