package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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

import java.io.File;

import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class PreParty extends StackPane {
    private Button launchButton;
    //String[] vaisseau = {"src/main/resources/Pion/PionDestroyer.png", "src/main/resources/Pion/PionFaucon.png", "src/main/resources/Pion/PionTfighter.png", "src/main/resources/Pion/PionXwing.png"};
    String video;
    String shipIndex1 = ProfileData.getShip(1);
    String shipIndex2 = ProfileData.getShip(2);

    public PreParty(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG("src/main/resources/BGAUDIO.png");
        setBackground(ground.getCustomBackground());

        VBox vbox = new VBox(30); // Création de la VBox
        vbox.setAlignment(Pos.CENTER); // Centrer les HBox verticalement
        vbox.setPadding(new Insets(100, 0, 0, 0)); // Ajouter une marge en haut

        String[] niveau = {"Facile", "Moyen", "Difficile"};

        ToggleGroup toggleGroup1 = new ToggleGroup();
        HBox hbox1 = createToggleHBox(niveau, toggleGroup1); // Appeler la méthode pour créer la HBox appropriée
        ToggleGroup toggleGroup2 = new ToggleGroup();
        String[] imageUrls2 = {"Chrono30Test.png", "Chrono1Test.png", "ChronoXTest.png"}; // Remplacez les liens par vos propres URLs
        HBox hbox2 = createImageToggleHBox(toggleGroup2, imageUrls2); // Appeler la méthode pour créer la HBox appropriée
        ToggleGroup toggleGroup3 = new ToggleGroup();
        String[] imageUrls3 = {"Naboo.png", "Coruscant.png", "Mustafar.png"}; // Remplacez les liens par vos propres URLs
        HBox hbox3 = createImageToggleHBox(toggleGroup3, imageUrls3); // Appeler la méthode pour créer la HBox appropriée
        HBox hbox4 = new HBox(10); // Création de la VBox pour le bouton "Lancer Partie"

        hbox4.setAlignment(Pos.CENTER);

        launchButton = new Button("Lancer Partie");
        launchButton.setPrefSize(200, 50);
        launchButton.setStyle("-fx-background-color: #9ed586  ; -fx-text-fill: black;");
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
            VideoLoad(primaryStage, toggleGroup3, hbox3);
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

    public void VideoLoad(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3) {
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
                Party party = new Party(primaryStage, toggleGroup3, hbox3); // Supposons que primaryStage soit accessible ici
                primaryStage.getScene().setRoot(party);
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            Party party = new Party(primaryStage, toggleGroup3, hbox3); // Supposons que primaryStage soit accessible ici
            primaryStage.getScene().setRoot(party);
        });
        mediaPlayer.play();

    }

    private HBox createToggleHBox(String[] niveau, ToggleGroup toggleGroup) {
        HBox hbox = new HBox(30); // Création de la HBox
        hbox.setAlignment(Pos.CENTER); // Centrer les boutons horizontalement

        for (String niv: niveau) {
            ToggleButton button = new ToggleButton(niv);
            button.setToggleGroup(toggleGroup); // Associez le bouton au ToggleGroup

            button.setPrefSize(150, 40); // Taille préférée des boutons (largeur x hauteur)
            button.setFont(Font.font("Cardo", FontWeight.BOLD, 20)); // Police et taille du texte
            button.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(50), Insets.EMPTY)));
            button.setTextFill(Color.BLACK);

            // Ajouter un style différent pour les boutons sélectionnés
            button.setStyle("-fx-background-color: white; -fx-text-fill: black;");

            // Ajouter un style lorsque le bouton est sélectionné
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    button.setStyle("-fx-background-color: #9ed586; -fx-text-fill: black;");
                } else {
                    button.setStyle("-fx-background-color: white; -fx-text-fill: black;");
                }
            });

            hbox.getChildren().add(button); // Ajouter le bouton à la HBox
        }

        return hbox;
    }

    private HBox createImageToggleHBox(ToggleGroup toggleGroup, String[] imageUrls) {
        HBox hbox = new HBox(30); // Création de la HBox
        hbox.setAlignment(Pos.CENTER); // Centrer les boutons horizontalement

        for (int j = 0; j < 3; j++) {
            ToggleButton button = new ToggleButton();
            button.setToggleGroup(toggleGroup);
            button.setUserData(j);

            // Charger une image différente pour chaque bouton
            Image image = new Image(imageUrls[j]);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(75);
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent;");

            // Ajouter un style lorsque le bouton est sélectionné
            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    button.setStyle("-fx-border-color: #9ed586; -fx-border-width: 2px; -fx-background-color: transparent;");
                } else {
                    button.setStyle("-fx-background-color: transparent;");
                }
            });

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


}