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
import org.example.milleniumproject.view.Menu;
import java.io.File;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.PartyIA.currentPlayer;

/**
 * Cette classe gère le bouton pause et les sous paramètres de celui-ci.
 */
public class ButtonPause extends StackPane {

    private static boolean save;

    public static VBox quitterMenu;
    private Audio.SliderWithControls sliderWithControls2;
    private Audio.SliderWithControls sliderWithControls3;

    /**
     * Affiche les règles du jeu.
     *
     * @param root  Conteneur principal de la scène.
     */
    public static void displayRules(StackPane root) {

        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");


        Image image = new Image("Règles.png");
        ImageView imageView = new ImageView(image);


        imageView.setFitWidth(0.9765 * screenWidth);
        imageView.setFitHeight(1.04167 * screenHeight);

        Button fermerButton = new Button();

        fermerButton.setMinWidth(0.039 * screenWidth);
        fermerButton.setMinHeight(0.0694 * screenHeight);
        fermerButton.setStyle("-fx-background-color: transparent;");

        fermerButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            root.getChildren().remove(reglesPane);
        });

        StackPane.setMargin(fermerButton, new Insets(0, 0, 0.6667 * screenHeight, 0.796875 * screenWidth));

        reglesPane.getChildren().addAll(imageView, fermerButton);

        StackPane.setAlignment(reglesPane, Pos.CENTER);

        root.getChildren().add(reglesPane);
    }

    /**
     * Affiche les paramètres du jeu.
     *
     * @param root Conteneur principal de la scène.
     */
    public static void settings(StackPane root) {
        StackPane parametrePane = new StackPane();
        parametrePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");

        Image image = new Image("ParametresParty.png");
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(0.9765 * screenWidth);
        imageView.setFitHeight(1.04167 * screenHeight);

        VBox vboxVideo = new VBox(0.0416667*screenHeight);
        vboxVideo.setAlignment(Pos.CENTER);

        CheckBox animationCheckBox = new CheckBox("Activer animations");
        addDropShadowEffect(animationCheckBox);
        animationCheckBox.setFont(Font.font("cardo", FontWeight.BOLD, 15));
        animationCheckBox.setStyle("-fx-text-fill: white");
        vboxVideo.getChildren().add(animationCheckBox);

        animationCheckBox.setSelected(VideoData.isAnimation());
        animationCheckBox.setOnAction(event -> VideoData.setAnimation(animationCheckBox.isSelected()));

        VBox vboxAudio = new VBox(0.416667*screenHeight);
        vboxAudio.setAlignment(Pos.CENTER);

        Label volumeMusiqueLabel = createStyledLabel("Musique");
        Label volumeEffetsLabel = createStyledLabel("Sons");

        VBox leftbox = new VBox(0.094444*screenHeight);
        leftbox.setAlignment(Pos.CENTER_LEFT);
        leftbox.getChildren().addAll(volumeMusiqueLabel, volumeEffetsLabel);

        Audio.SliderWithControls sliderWithControls2 = new Audio.SliderWithControls();
        Audio.SliderWithControls sliderWithControls3 = new Audio.SliderWithControls();

        sliderWithControls2.getSlider().setValue(AudioData.getMusicVolume() * 100);
        sliderWithControls2.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setMusicVolume(volume);
            MusicPlayer.setVolume(volume);
        });

        sliderWithControls3.getSlider().setValue(AudioData.getSoundVolume() * 100);
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100.0;
            AudioData.setSoundVolume(volume);
        });

        sliderWithControls3.getSlider().setValue(AudioData.getSliderPosition() * 100);
        sliderWithControls3.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioData.setSliderPosition(newValue.doubleValue() / 100.0);
        });

        HBox hbox2 = new HBox(0, sliderWithControls2.getBtnLeft(), sliderWithControls2.getSlider(), sliderWithControls2.getBtnRight(), sliderWithControls2.getValueLabel());
        HBox hbox3 = new HBox(0, sliderWithControls3.getBtnLeft(), sliderWithControls3.getSlider(), sliderWithControls3.getBtnRight(), sliderWithControls3.getValueLabel());

        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        stylizeButton(sliderWithControls2.getBtnLeft());
        stylizeButton(sliderWithControls2.getBtnRight());
        stylizeButton(sliderWithControls3.getBtnLeft());
        stylizeButton(sliderWithControls3.getBtnRight());

        addDropShadowEffect(sliderWithControls2.getBtnLeft());
        addDropShadowEffect(sliderWithControls2.getBtnRight());
        addDropShadowEffect(sliderWithControls3.getBtnLeft());
        addDropShadowEffect(sliderWithControls3.getBtnRight());

        addDropShadowEffect(volumeMusiqueLabel);
        addDropShadowEffect(volumeEffetsLabel);

        addDropShadowEffect(sliderWithControls2.getSlider());
        addDropShadowEffect(sliderWithControls3.getSlider());

        VBox rightbox = new VBox(0.059722*screenHeight, hbox2, hbox3);
        rightbox.setAlignment(Pos.CENTER_LEFT);

        HBox hbox = new HBox(0.0078125*screenWidth);
        hbox.getChildren().addAll(leftbox, rightbox);
        setMargin(hbox, new Insets(0, 0, 0, 0.078125*screenHeight));

        vboxAudio.getChildren().add(hbox);

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

        parametrePane.getChildren().addAll(imageView, hboxfinal, fermerButton);

        StackPane.setAlignment(parametrePane, Pos.CENTER);

        root.getChildren().add(parametrePane);
    }
    /**
     * Crée un label stylisé.
     *
     * @param text Le texte du label.
     * @return Le label.
     */
    private static Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("cardo", FontWeight.BOLD, 15));
        label.setStyle("-fx-text-fill: #FFFFFFFF;");
        addDropShadowEffect(label);
        return label;
    }
    /**
     * Applique un style aux boutons.
     *
     * @param button Le bouton à styliser.
     */
    private static void stylizeButton(Button button) {
        button.setFont(Font.font("cardo", FontWeight.BOLD, 19));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFFFF;");
    }
    /**
     * Ajoute un effet d'ombre.
     *
     * @param node Le nœud auquel appliquer l'effet d'ombre.
     */
    private static void addDropShadowEffect(Node node) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        node.setEffect(dropShadow);
    }

    /**
     * Sert à revenir vers le menu du jeu.
     *
     * @param primaryStage  La scène en premier plan.
     * @return Une VBox contenant le bouton quitter.
     */
    static VBox quitButton(Stage primaryStage) {
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
            menu.showMenu(primaryStage);
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
            currentPlayer = 1;
            turns = 0;
            isNoChrono = false;
            buttonsJ1.clear();
            buttonsJ2.clear();
            isRemovePieceMode = false;
            placementisfinished = false;
        });

        hbox.getChildren().addAll(quitterButton, annulerButton);
        vbox.getChildren().addAll(confirmationLabel, hbox);

        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px;");

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(0.02778 * screenHeight, 0, 0, 0));

        return vbox;
    }
    /**
     * Possibilité de sauvegarder la partie en quittant.
     *
     * @param primaryStage La scène en premier plan.
     * @param gridPane Le GridPane représentant le plateau de jeu.
     * @param chrono La valeur  chrono.
     * @param bg L'index du fond d'écran.
     * @param ia Indique si l'adversaire est l'IA.
     * @param difficulty Le niveau de difficulté de l'IA.
     * @return Une VBox que dimande de confirmer si le joueur veut sauvegarder.
     */
    static VBox quitButtonSave(Stage primaryStage, GridPane gridPane, int chrono, int bg, boolean ia, int difficulty) {
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
            menu.showMenu(primaryStage);
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
            fileNameField.setPrefSize(0.0781125*screenWidth,0.06944*screenHeight);

            fileNameField.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-border-color: #FF9800; -fx-text-fill: white; -fx-alignment: center;");
            fileNameField.setFont(Font.font("cardo", FontWeight.BOLD, 25));


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
                            SaveParty sauvegardePartie = new SaveParty(gridPane, ProfileData.getAvatar(1), ProfileData.getAvatar(2), ProfileData.getRank(1), ProfileData.getRank(2), ProfileData.getShip(1), ProfileData.getShip(2), ProfileData.getPlayerName(1), ProfileData.getPlayerName(2), currentPlayer, turns, chrono, bg,ia,difficulty);
                            sauvegardePartie.saveInFile(filePath);
                            Menu menu = new Menu();
                            menu.showMenu(primaryStage);
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
                        SaveParty sauvegardePartie = new SaveParty(gridPane, ProfileData.getAvatar(1), ProfileData.getAvatar(2), ProfileData.getRank(1), ProfileData.getRank(2), ProfileData.getShip(1), ProfileData.getShip(2), ProfileData.getPlayerName(1), ProfileData.getPlayerName(2), currentPlayer, turns, chrono, bg,ia,difficulty);
                        sauvegardePartie.saveInFile(filePath);
                        Menu menu = new Menu();
                        menu.showMenu(primaryStage);
                        MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
                        vbox.setVisible(false);
                    }
                }
                currentPlayer = 1;
                buttonsJ1.clear();
                buttonsJ2.clear();
                turns = 0;
                isRemovePieceMode = false;
                placementisfinished = false;
                isNoChrono = false;
            });

            cancelButton.setOnAction(event -> vbox.setVisible(false));

            dialogBox.getChildren().addAll(fileNameLabel, fileNameField, saveButton, cancelButton);
            vbox.getChildren().clear();
            vbox.getChildren().addAll(dialogBox);
        });

        hbox.getChildren().addAll(ouiButton, nonButton);
        vbox.getChildren().addAll(confirmationLabelsave, hbox);

        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20px");

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);



        return vbox;
    }

    public static boolean isSave() {
        return save;
    }
}
