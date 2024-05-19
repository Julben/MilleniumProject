package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
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
import org.example.milleniumproject.view.Audio;
import org.example.milleniumproject.view.AudioData;
import org.example.milleniumproject.view.Menu;
import org.example.milleniumproject.view.VideoData;

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
    private Audio.SliderWithControls sliderWithControls2;
    private Audio.SliderWithControls sliderWithControls3;

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


    public static void parametres(StackPane root) {
        // Création de la StackPane pour contenir l'image et le bouton
        StackPane parametrePane = new StackPane();
        parametrePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");

        // Création de l'image
        Image image = new Image("ParametresParty.png");
        ImageView imageView = new ImageView(image);

        // Redimensionner l'image
        imageView.setFitWidth(0.9765 * screenWidth); // Largeur souhaitée
        imageView.setFitHeight(1.04167 * screenHeight); // Hauteur souhaitée

        VBox vboxVideo = new VBox(0.0416667*screenHeight);
        vboxVideo.setAlignment(Pos.CENTER);

        CheckBox animationCheckBox = new CheckBox("Activer animations");
        addDropShadowEffect(animationCheckBox);
        animationCheckBox.setStyle("-fx-text-fill: white; -fx-font-family: Cardo; -fx-font-weight: bold; -fx-font-size: 20px;");
        vboxVideo.getChildren().add(animationCheckBox);

        // Initialiser les CheckBox avec les valeurs sauvegardées
        animationCheckBox.setSelected(VideoData.isAnimation());
        animationCheckBox.setOnAction(event -> VideoData.setAnimation(animationCheckBox.isSelected()));

        VBox vboxAudio = new VBox(0.416667*screenHeight);
        vboxAudio.setAlignment(Pos.CENTER);

        // Création des labels
        Label volumeMusiqueLabel = createStyledLabel("Musique");
        Label volumeEffetsLabel = createStyledLabel("Sons");

        VBox leftbox = new VBox(0.094444*screenHeight);
        leftbox.setAlignment(Pos.CENTER_LEFT);
        leftbox.getChildren().addAll(volumeMusiqueLabel, volumeEffetsLabel);

        // Création des boutons, du slider et du label de valeur
        Audio.SliderWithControls sliderWithControls2 = new Audio.SliderWithControls();
        Audio.SliderWithControls sliderWithControls3 = new Audio.SliderWithControls();

        // Restaure le volume de la musique à partir de AudioData
        sliderWithControls2.getSlider().setValue(AudioData.getMusicVolume() * 100);
        sliderWithControls2.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setMusicVolume(volume);
            MusicPlayer.setVolume(volume);
        });

        // Restaure le volume des sons à partir de AudioData
        sliderWithControls3.getSlider().setValue(AudioData.getSoundVolume() * 100);
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setSoundVolume(volume);
        });

        // Restaure la position du slider à partir de AudioData
        sliderWithControls3.getSlider().setValue(AudioData.getSliderPosition() * 100);
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioData.setSliderPosition(newValue.doubleValue() / 100.0);
        });

        // Création des HBox
        HBox hbox2 = new HBox(0, sliderWithControls2.getBtnLeft(), sliderWithControls2.getSlider(), sliderWithControls2.getBtnRight(), sliderWithControls2.getValueLabel());
        HBox hbox3 = new HBox(0, sliderWithControls3.getBtnLeft(), sliderWithControls3.getSlider(), sliderWithControls3.getBtnRight(), sliderWithControls3.getValueLabel());

        // Alignement horizontal des éléments dans les HBox
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        // Stylisation des boutons "<" et ">"
        stylizeButton(sliderWithControls2.getBtnLeft());
        stylizeButton(sliderWithControls2.getBtnRight());
        stylizeButton(sliderWithControls3.getBtnLeft());
        stylizeButton(sliderWithControls3.getBtnRight());

        // Ajout d'un effet de drop shadow aux boutons
        addDropShadowEffect(sliderWithControls2.getBtnLeft());
        addDropShadowEffect(sliderWithControls2.getBtnRight());
        addDropShadowEffect(sliderWithControls3.getBtnLeft());
        addDropShadowEffect(sliderWithControls3.getBtnRight());

        // Ajout d'un effet de drop shadow aux labels
        addDropShadowEffect(volumeMusiqueLabel);
        addDropShadowEffect(volumeEffetsLabel);

        // Ajout d'un effet de drop shadow aux nombres des sliders
        addDropShadowEffect(sliderWithControls2.getSlider());
        addDropShadowEffect(sliderWithControls3.getSlider());

        // Création de la VBox et ajout des HBox
        VBox rightbox = new VBox(0.059722*screenHeight, hbox2, hbox3);
        rightbox.setAlignment(Pos.CENTER_LEFT);

        HBox hbox = new HBox(0.0078125*screenWidth);
        hbox.getChildren().addAll(leftbox, rightbox);
        setMargin(hbox, new Insets(0, 0, 0, 0.078125*screenHeight));

        vboxAudio.getChildren().add(hbox);

        // Création du bouton pour masquer l'image et le bouton
        Button fermerButton = new Button();
        fermerButton.setMinWidth(0.0546875*screenWidth);
        fermerButton.setMinHeight(0.097222*screenHeight);
        StackPane.setMargin(fermerButton, new Insets(0, 0, 0.8541667*screenHeight, 0.8203125*screenWidth));
        fermerButton.setStyle("-fx-background-color: transparrent");
        fermerButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            root.getChildren().remove(parametrePane);
        });

        HBox hboxfinal = new HBox(0.140625*screenWidth, vboxVideo, vboxAudio);
        hboxfinal.setAlignment(Pos.CENTER);
        setMargin(hboxfinal, new Insets(0, 0, 0.01388*screenHeight, 0.0625*screenWidth));

        // Ajout de l'image et du bouton à la StackPane
        parametrePane.getChildren().addAll(imageView, hboxfinal, fermerButton);

        // Centrer la StackPane dans la fenêtre
        StackPane.setAlignment(parametrePane, Pos.CENTER);

        // Ajouter la StackPane à la racine de la scène
        root.getChildren().add(parametrePane);
    }

    // Méthode pour créer un label stylisé
    private static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-family: Cardo; -fx-text-fill: #FFFFFFFF; -fx-font-size: 20px;");
        addDropShadowEffect(label);
        return label;
    }

    // Méthode pour styliser un bouton
    private static void stylizeButton(Button button) {
        button.setStyle("-fx-font-family: Cardo; -fx-background-color: transparent; -fx-text-fill: #FFFFFFFF; -fx-font-size: 25px;");
    }

    // Méthode pour ajouter un effet de drop shadow à un bouton
    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        node.setEffect(dropShadow);
    }


    /**
     * Crée un menu pour confirmer la sortie du jeu.
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @return Une VBox contenant le menu de confirmation.
     */
    static VBox boutonquitter(Stage primaryStage) {
        VBox vbox = new VBox(0.04167 * screenHeight);

        Label confirmationLabel = new Label("Pour sauvegarder la partie le placement des pions doit être terminé. Voulez-vous vraiment quitter ?");
        confirmationLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305 * screenHeight));
        confirmationLabel.setTextFill(Color.WHITE);

        HBox hbox = new HBox(0.05139 * screenHeight);
        Button quitterButton = new Button("Oui");
        Button annulerButton = new Button("Non");

        quitterButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));
        annulerButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));

        quitterButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        annulerButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        annulerButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            vbox.setVisible(false);
        });

        quitterButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
        });

        hbox.getChildren().addAll(quitterButton, annulerButton);
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

        Label confirmationLabelsave = new Label("Souhaitez-vous sauvegarder la partie ?");
        confirmationLabelsave.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305 * screenHeight));
        confirmationLabelsave.setTextFill(Color.WHITE);

        HBox hbox = new HBox(0.05139 * screenHeight);
        Button ouiButton = new Button("Oui");
        Button nonButton = new Button("Non");

        ouiButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));
        nonButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));

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
            VBox dialogBox = new VBox(0.0138889*screenHeight);
            dialogBox.setAlignment(Pos.CENTER);

            Label fileNameLabel = new Label("Nom du fichier de sauvegarde:");
            fileNameLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0305 * screenHeight));
            fileNameLabel.setTextFill(Color.WHITE);
            TextField fileNameField = new TextField();
            fileNameField.setPrefSize(100,50);

            fileNameField.setStyle("-fx-background-color: rgba(0,0,0,0.5); " +
                    "-fx-border-color: #FF9800; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-family: 'Cardo'; " +
                    "-fx-font-size: 25; " +
                    "-fx-alignment: center;");


            Button saveButton = new Button("Sauvegarder");
            Button cancelButton = new Button("Annuler");

            saveButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));
            cancelButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));

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
                        fileExistsLabel.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
                        fileExistsLabel.setTextFill(Color.WHITE);

                        Button overwriteButton = new Button("Écraser");
                        Button cancelButton2 = new Button("Annuler");

                        overwriteButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));
                        cancelButton2.setFont(Font.font("Cardo", FontWeight.BOLD, 0.023611*screenHeight));

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
            vbox.getChildren().addAll(dialogBox);
        });

        hbox.getChildren().addAll(ouiButton, nonButton);
        vbox.getChildren().addAll(confirmationLabelsave, hbox);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px");

        // Positionnement du menu pause au centre de l'écran
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    public static boolean isSave() {
        return save;
    }
}
