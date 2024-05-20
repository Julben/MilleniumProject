package org.example.milleniumproject.view;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import org.example.milleniumproject.model.Constant;
import org.example.milleniumproject.model.PartyIA;

public class Campagne extends Pane {
    private int currentRound = 0;
    private final int totalRounds = 3;
    private Stage primaryStage;

    public Campagne(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void startCampagne() {
        System.out.println("Starting campaign...");
        playVideo("/VideoCamp/VideoCampagneNaboo.mp4", this::startRound);
    }

    private void playVideo(String videoPath, Runnable onEnd) {
        try {
            String fullPath = getClass().getResource(videoPath).toExternalForm();
            System.out.println("Playing video: " + fullPath);

            VideoPlayer videoPlayer = new VideoPlayer(fullPath);
            getChildren().setAll(videoPlayer);

            videoPlayer.playVideo(onEnd);
        } catch (NullPointerException e) {
            System.err.println("Video file not found: " + videoPath);
            onEnd.run();
        }
    }

    private void startRound() {
        System.out.println("Starting round: " + currentRound);

        if (currentRound < totalRounds) {
            playPart(() -> {
                currentRound++;
                String videoPath = getVideoPathForRound(currentRound);
                if (videoPath != null) {
                    System.out.println("Ending round, playing video: " + videoPath);
                    playVideo(videoPath, this::startRound);
                }
            }, currentRound);

        } else {
            System.out.println("Campaign finished, playing end video.");
            playVideo("src/main/resources/VideoFinCampagne.mp4", () -> {
                primaryStage.close();
            });
        }
    }

    private String getVideoPathForRound(int round) {
        switch (round) {
            case 1:
                return "/VideoCamp/VideoCampagneCorusant.mp4";
            case 2:
                return "/VideoCamp/VideoCampagneMustafar.mp4";
            default:
                return null;
        }
    }

    private void playPart(Runnable onEnd, int currentRound) {
        System.out.println("Playing part for round: " + currentRound);

        PartyIA partyIA = new PartyIA(primaryStage, currentRound);

        partyIA.setPrefWidth(Constant.screenWidth);
        partyIA.setPrefHeight(Constant.screenHeight);

        partyIA.setLayoutX(0);
        partyIA.setLayoutY(0);

        getChildren().setAll(partyIA);

        PauseTransition pause = new PauseTransition(Duration.seconds(50));
        pause.setOnFinished(event -> {
            System.out.println("Part finished.");
            onEnd.run();
        });
        pause.play();

        System.out.println("Pause transition started");
    }
}