package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Video;
import org.example.milleniumproject.view.VideoData;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class PrePartyIA extends StackPane {
    private Button launchButton;
    //String[] vaisseau = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};
    String video;
    String shipIndex1 = ProfileData.getShip(1);
    String shipIndex2 = ProfileData.getShip(2);

    public PrePartyIA(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG();
        setBackground(ground.getCustomBackground());

        VBox vbox = new VBox(0.0625*screenHeight); // Création de la VBox
        vbox.setAlignment(Pos.CENTER); // Centrer les HBox verticalement
        vbox.setPadding(new Insets(0.13889*screenHeight, 0, 0, 0)); // Ajouter une marge en haut

        String[] niveau = {"Facile", "Difficile"};

        ToggleGroup toggleGroup1 = new ToggleGroup();
        HBox hbox1 = createToggleHBox(niveau, toggleGroup1); // Appeler la méthode pour créer la HBox appropriée
        ToggleGroup toggleGroup2 = new ToggleGroup();
        String[] imageUrls2 = {"Chrono30sec.png", "Chrono1min.png", "ChronoNoTime.png"}; // Remplacez les liens par vos propres URLs
        HBox hbox2 = createImageToggleHBox(toggleGroup2, imageUrls2); // Appeler la méthode pour créer la HBox appropriée
        ToggleGroup toggleGroup3 = new ToggleGroup();
        String[] imageUrls3 = {"NabooNoBG.png", "CoruscantNoBG.png", "MustafarNoBG.png"}; // Remplacez les liens par vos propres URLs
        HBox hbox3 = createImageToggleHBox(toggleGroup3, imageUrls3); // Appeler la méthode pour créer la HBox appropriée
        HBox hbox4 = new HBox(0.0078*screenWidth); // Création de la VBox pour le bouton "Lancer Partie"

        hbox4.setAlignment(Pos.CENTER);

        launchButton = new Button("Lancer Partie");
        launchButton.setPrefSize(0.15625*screenWidth, 0.0694*screenHeight);
        launchButton.setBackground(new Background(new BackgroundFill(Color.rgb(61, 141, 230), new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
        launchButton.setTextFill(Color.WHITE); // Couleur du texte
        launchButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0333*screenHeight));
        launchButton.setDisable(true); // Désactiver le bouton au départ

        hbox4.getChildren().add(launchButton); // Ajouter le bouton à la HBox

        vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4); // Ajouter les HBox à la VBox

        getChildren().addAll(vbox); // Ajouter l'arrière-plan et la VBox à la StackPane

        // Ajouter des écouteurs pour surveiller les sélections dans les ToggleGroups
        toggleGroup1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkLaunchButtonState(toggleGroup1, toggleGroup2, toggleGroup3));
        toggleGroup2.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkLaunchButtonState(toggleGroup1, toggleGroup2, toggleGroup3));
        toggleGroup3.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkLaunchButtonState(toggleGroup1, toggleGroup2, toggleGroup3));

        launchButton.setOnAction(event -> {
            MusicPlayer.stopPlaying();
            VideoLoad(primaryStage, toggleGroup3, hbox3, toggleGroup2,  hbox2);
        });

        getChildren().addAll(retourButton);
    }

    public String ChooseVideo(String shipIndex1, String shipIndex2, ToggleGroup toggleGroup3, HBox hbox3) {

        int selectedIndex = getSelectedIndex(toggleGroup3, hbox3);

        String[] planete = {"Naboo.png", "Coruscant.png", "Mustafar.png"};

        if ((shipIndex1.equals("src/main/resources/PionDestroyer.png") && shipIndex2.equals("src/main/resources/PionFaucon.png")) || (shipIndex1.equals("src/main/resources/PionFaucon.png") && shipIndex2.equals("src/main/resources/PionDestroyer.png"))) {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/FauconDestroyerNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Faucon - Destroyer Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Faucon - Destroyer Mustaphar Final.mp4";
            }
        } else if ((shipIndex1.equals("src/main/resources/PionDestroyer.png") && shipIndex2.equals("src/main/resources/PionTfighter.png")) || (shipIndex1.equals("src/main/resources/PionTfighter.png") && shipIndex2.equals("src/main/resources/PionDestroyer.png"))) {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/TfighterDestroyerNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Tfighter - Destroyer Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Tfighter - Destroyer Mustaphar Final.mp4";
            }
        } else if ((shipIndex1.equals("src/main/resources/PionDestroyer.png") && shipIndex2.equals("src/main/resources/PionXwing.png")) || (shipIndex1.equals("src/main/resources/PionXwing.png") && shipIndex2.equals("src/main/resources/PionDestroyer.png"))) {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/XwingDestroyerNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Xwing - Destroyer Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Xwing - Destroyer Mustaphar Final.mp4";
            }
        } else if ((shipIndex1.equals("src/main/resources/PionFaucon.png") && shipIndex2.equals("src/main/resources/PionTfighter.png")) || (shipIndex1.equals("src/main/resources/PionTfighter.png") && shipIndex2.equals("src/main/resources/PionFaucon.png"))) {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/FauconTfighterNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Faucon - Tfighter Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Faucon - Tfighter Mustaphar Final.mp4";
            }
        } else if ((shipIndex1.equals("src/main/resources/PionFaucon.png") && shipIndex2.equals("src/main/resources/PionXwing.png")) || (shipIndex1.equals("src/main/resources/PionXwing.png") && shipIndex2.equals("src/main/resources/PionFaucon.png"))) {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/FauconXwingNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Faucon - Xwing Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Faucon - Xwing Mustaphar Final.mp4";
            }
        } else {
            if (planete[selectedIndex].equals("Naboo.png")) {
                video = "src/main/resources/VideoLoad/XwingTfighterNabooFinal.mp4";
            } else if (planete[selectedIndex].equals("Coruscant.png")) {
                video = "src/main/resources/VideoLoad/Xwing - Tfighter Coruscant Final.mp4";
            } else {
                video = "src/main/resources/VideoLoad/Xwing - Tfighter Mustaphar Final.mp4";
            }
        }
        return video;
    }

    public boolean getVideoPreference() {
        // Initialiser les préférences
        Preferences preferences = Preferences.userNodeForPackage(Video.class);

        // Récupérer la valeur de la clé "video" (false par défaut si la clé n'existe pas)
        return preferences.getBoolean("video", false);
    }




    public void VideoLoad(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3,ToggleGroup toggleGroup2, HBox hbox2) {
        if(VideoData.isVideoChoose()) {
            List<String> stringList = Arrays.asList("src/main/resources/MusicParty1.mp3", "src/main/resources/MusicParty2.mp3", "src/main/resources/MusicParty3.mp3");
            Random rand = new Random();
            int index = rand.nextInt(stringList.size());
            String randomString = stringList.get(index);

            File file = new File(ChooseVideo(shipIndex1, shipIndex2, toggleGroup3, hbox3));
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            // Ajustez la taille du MediaView
            mediaView.setFitWidth(screenWidth);
            mediaView.setFitHeight(screenHeight);

            // Ajoutez le MediaView à la scène
            StackPane root = new StackPane();
            root.getChildren().add(mediaView);

            // Créez une nouvelle scène avec le StackPane contenant le MediaView
            Scene scene = new Scene(root, screenWidth, screenHeight);

            // Mettez à jour la scène du primaryStage
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();

            primaryStage.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                    mediaPlayer.stop();
                    MusicPlayer.musicPlay(randomString);
                    PartyIA partyia = new PartyIA(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2); // Supposons que primaryStage soit accessible ici
                    primaryStage.getScene().setRoot(partyia);
                }
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                MusicPlayer.musicPlay(randomString);
                PartyIA partyia = new PartyIA(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2); // Supposons que primaryStage soit accessible ici
                primaryStage.getScene().setRoot(partyia);
            });
            mediaPlayer.play();
        }else{
            PartyIA partyia = new PartyIA(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2); // Supposons que primaryStage soit accessible ici
            primaryStage.getScene().setRoot(partyia);
        }

    }

    private HBox createToggleHBox(String[] niveau, ToggleGroup toggleGroup) {
        HBox hbox = new HBox(0); // Création de la HBox
        hbox.setAlignment(Pos.CENTER); // Centrer les boutons horizontalement

        for (String niv : niveau) {
            ToggleButton button = new ToggleButton(niv);
            button.setToggleGroup(toggleGroup); // Associez le bouton au ToggleGroup

            button.setPrefSize(0.1796875*screenWidth, 0.0277778*screenHeight);
            button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
            button.setTextFill(Color.WHITE); // Couleur du texte
            button.setFont(Font.font("Cardo", FontWeight.BOLD, 45));
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(3);
            dropShadow.setOffsetX(1);
            dropShadow.setOffsetY(1);
            button.setEffect(dropShadow);

            // Style lorsque le bouton est sélectionné
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                SoundPlayer.soundPlay();
                if (newValue) {
                    button.setTextFill(Color.rgb(0, 140, 255));
                } else {
                    button.setTextFill(Color.WHITE);
                }
            });

            hbox.getChildren().add(button); // Ajouter le bouton à la HBox
        }

        return hbox;
    }


    private HBox createImageToggleHBox(ToggleGroup toggleGroup, String[] imageUrls) {
        HBox hbox = new HBox(0.0234*screenWidth); // Création de la HBox
        hbox.setAlignment(Pos.CENTER); // Centrer les boutons horizontalement

        for (int j = 0; j < 3; j++) {
            ToggleButton button = new ToggleButton();
            button.setToggleGroup(toggleGroup);
            button.setUserData(j);

            // Charger une image différente pour chaque bouton
            Image image = new Image(imageUrls[j]);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(0.1156*screenWidth);
            imageView.setFitHeight(0.1153*screenHeight);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(3);
            dropShadow.setOffsetX(2);
            dropShadow.setOffsetY(2);
            imageView.setEffect(dropShadow);

            // Ajouter un écouteur pour changer la couleur de la bordure de l'image
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                SoundPlayer.soundPlay();
                if (newValue) {
                    Color intenseBlue = Color.rgb(0, 103, 255); // Intensité de la couleur verte
                    imageView.setEffect(new javafx.scene.effect.DropShadow(40, intenseBlue));
                } else {
                    imageView.setEffect(dropShadow);
                }
            });

            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent;");

            hbox.getChildren().add(button); // Ajouter le bouton à la HBox
        }
        return hbox;
    }



    private void checkLaunchButtonState(ToggleGroup toggleGroup1, ToggleGroup toggleGroup2, ToggleGroup toggleGroup3) {
        if (toggleGroup1.getSelectedToggle() != null && toggleGroup2.getSelectedToggle() != null && toggleGroup3.getSelectedToggle() != null) {
            launchButton.setDisable(false); // Activer le bouton si une sélection est faite dans chaque ToggleGroup
        } else {
            launchButton.setDisable(true); // Désactiver le bouton sinon
        }
    }

    public static int getSelectedIndex(ToggleGroup toggleGroup3, HBox hbox3) {
        int selectedIndex = -1;
        ToggleButton selectedButton = (ToggleButton) toggleGroup3.getSelectedToggle();
        if(selectedButton != null) {
            selectedIndex = hbox3.getChildren().indexOf(selectedButton);
        }
        return selectedIndex;
    }

    public static int getSelectedIndexchrono(ToggleGroup toggleGroup2, HBox hbox2) {
        int selectedIndex = -1;
        ToggleButton selectedButton = (ToggleButton) toggleGroup2.getSelectedToggle();
        if(selectedButton != null) {
            selectedIndex = hbox2.getChildren().indexOf(selectedButton);
        }
        return selectedIndex;
    }


}