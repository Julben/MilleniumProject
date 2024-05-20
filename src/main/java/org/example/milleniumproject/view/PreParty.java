package org.example.milleniumproject.view;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.example.milleniumproject.model.*;
import org.example.milleniumproject.presentation.BG;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class PreParty extends StackPane {
    private Button launchButton;
    String video;
    String shipIndex1 = ProfileData.getShip(1);
    String shipIndex2 = ProfileData.getShip(2);

    public PreParty(Stage primaryStage) {

        Button retourButton = BackButtons.createBackButton(primaryStage);

        BG ground = new BG();
        setBackground(ground.getCustomBackground());

        VBox vbox = new VBox(0.0625*screenHeight);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0.13889*screenHeight, 0, 0, 0));

        ToggleGroup toggleGroup2 = new ToggleGroup();
        String[] imageUrls2 = {"Chrono30sec.png", "Chrono1min.png", "ChronoNoTime.png"};
        HBox hbox2 = createImageToggleHBox(toggleGroup2, imageUrls2);
        ToggleGroup toggleGroup3 = new ToggleGroup();
        String[] imageUrls3 = {"NabooNoBG.png", "CoruscantNoBG.png", "MustafarNoBG.png"};
        HBox hbox3 = createImageToggleHBox(toggleGroup3, imageUrls3);
        HBox hbox4 = new HBox(0.0078*screenWidth);
        hbox4.setAlignment(Pos.CENTER);
        setMargin(hbox4, new Insets(0.111*screenHeight, 0, 0, 0));

        launchButton = new Button("Lancer Partie");
        launchButton.setPrefSize(0.15625*screenWidth, 0.06944*screenHeight);
        launchButton.setBackground(new Background(new BackgroundFill(Color.rgb(85, 174, 47), new CornerRadii(50), javafx.geometry.Insets.EMPTY)));
        launchButton.setTextFill(Color.WHITE);
        launchButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.0333*screenHeight));
        launchButton.setDisable(true);

        hbox4.getChildren().add(launchButton);

        vbox.getChildren().addAll(hbox2, hbox3, hbox4);

        getChildren().addAll(vbox);

        toggleGroup2.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkLaunchButtonState(toggleGroup2, toggleGroup3));
        toggleGroup3.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkLaunchButtonState(toggleGroup2, toggleGroup3));

        launchButton.setOnAction(event -> {
            MusicPlayer.stopPlaying();
            VideoLoad(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2);
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
        Preferences preferences = Preferences.userNodeForPackage(Video.class);
        return preferences.getBoolean("video", false);
    }

    public void VideoLoad(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2) {
        if(VideoData.isVideoChoose()) {

            List<String> stringList = Arrays.asList("src/main/resources/MusicParty1.mp3", "src/main/resources/MusicParty2.mp3", "src/main/resources/MusicParty3.mp3",
                    "src/main/resources/MusicParty4.mp3", "src/main/resources/MusicParty5.mp3", "src/main/resources/MusicParty6.mp3");
            Random rand = new Random();
            int index = rand.nextInt(stringList.size());
            String randomString = stringList.get(index);

            File file = new File(ChooseVideo(shipIndex1, shipIndex2, toggleGroup3, hbox3));
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setFitWidth(screenWidth);
            mediaView.setFitHeight(screenHeight);

            StackPane root = new StackPane();
            root.getChildren().add(mediaView);

            Scene scene = new Scene(root, screenWidth, screenHeight);

            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();

            primaryStage.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE && !mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                    mediaPlayer.stop();
                    MusicPlayer.musicPlay(randomString);
                    Party party = new Party(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2);
                    primaryStage.getScene().setRoot(party);
                }
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                MusicPlayer.musicPlay(randomString);
                Party party = new Party(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2);
                primaryStage.getScene().setRoot(party);
            });
            mediaPlayer.play();
        }else{
            Party party = new Party(primaryStage, toggleGroup3, hbox3, toggleGroup2, hbox2);
            primaryStage.getScene().setRoot(party);
        }
    }

    private HBox createImageToggleHBox(ToggleGroup toggleGroup, String[] imageUrls) {
        HBox hbox = new HBox(0.04167*screenWidth);
        hbox.setAlignment(Pos.CENTER);

        for (int j = 0; j < 3; j++) {
            ToggleButton button = new ToggleButton();
            button.setToggleGroup(toggleGroup);
            button.setUserData(j);

            Image image = new Image(imageUrls[j]);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(0.1156*screenWidth);
            imageView.setFitHeight(0.11527*screenHeight);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(3);
            dropShadow.setOffsetX(2);
            dropShadow.setOffsetY(2);
            imageView.setEffect(dropShadow);

            button.selectedProperty().addListener((observable, oldValue, newValue) -> {
                SoundPlayer.soundPlay();
                if (newValue) {
                    Color intenseBlue = Color.rgb(0, 255, 0);
                    imageView.setEffect(new javafx.scene.effect.DropShadow(40, intenseBlue));
                } else {
                    imageView.setEffect(dropShadow);
                }
            });

            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent;");

            hbox.getChildren().add(button);
        }
        return hbox;
    }

    private void checkLaunchButtonState(ToggleGroup toggleGroup2, ToggleGroup toggleGroup3) {
        if (toggleGroup2.getSelectedToggle() != null && toggleGroup3.getSelectedToggle() != null) {
            launchButton.setDisable(false);
        } else {
            launchButton.setDisable(true);
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