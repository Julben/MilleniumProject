package org.example.milleniumproject.model;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.example.milleniumproject.model.EndParty.FinPartie;
import static org.example.milleniumproject.model.PartyIA.*;

public class RemovePiece {

    static void removePiece(StackPane root, Button button, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage) {
        if (button.getGraphic() instanceof ImageView) {
            if(currentPlayer==1) {
                for(Button b : buttonsJ2){
                    if (!isNotlibre(b)){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ2.contains(button) && !isNotlibre(button)){
                    updateButtonState(button, buttonsJ2, root, timeline1, timeline2, primaryStage);
                    currentPlayer = 2;
                }
                else if(!boutonlibre && buttonsJ2.contains(button)){
                    updateButtonState2(button, buttonsJ2, root, timeline1, timeline2, primaryStage);
                    currentPlayer = 2;
                }
            }
            else if(currentPlayer==2) {
                for(Button b : buttonsJ1){
                    if (!isNotlibre(b)){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ1.contains(button) && !isNotlibre(button)){
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        updateButtonState(button, buttonsJ1, root, timeline1, timeline2, primaryStage);
                        ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        if (isGameFinished() && placementisfinished) {
                            FinPartie(root,timeline1, timeline2, primaryStage);
                        }
                    });
                    pause.play();
                    currentPlayer = 1;
                }
                else if(!boutonlibre && buttonsJ1.contains(button)) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        updateButtonState2(button, buttonsJ1, root, timeline1, timeline2, primaryStage);
                        ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        if (isGameFinished() && placementisfinished) {
                            FinPartie(root,timeline1, timeline2, primaryStage);
                        }
                    });
                    pause.play();
                    currentPlayer = 1;
                }
            }
        }
    }


    static void updateButtonState(Button button, List<Button> buttonsList, StackPane root, Timeline timeline1, Timeline timeline2, Stage primaryStage) {
        button.setGraphic(null);
        SoundPlayer.soundPlay();
        buttonsList.remove(button);
        isRemovePieceMode = false;
        boutonlibre = false;
    }

    static void updateButtonState2(Button button,List<Button> buttonsList, StackPane root, Timeline timeline1, Timeline timeline2, Stage primaryStage) {
        updateButtonState(button, buttonsList, root, timeline1, timeline2, primaryStage);
        resetButtonColorsForMovedButton(button);
    }

    static void HardRemove(StackPane root, Button button, Timeline timeline2, Label timerLabel2, Label timerLabel1, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage) {

        checkPerfectAlignments();
        checkAlignments();

        if(!finalValidAlignments.isEmpty() && currentPlayer == 2){
            Random random = new Random();
            String[] randomAlignment = finalValidAlignments.get(random.nextInt(finalValidAlignments.size()));

            List<Button> j1ButtonsInAlignment = new ArrayList<>();
            for (String buttonId : randomAlignment) {
                Button button1 = getButtonById(buttonId);
                if (buttonsJ1.contains(button1)) {
                    j1ButtonsInAlignment.add(button1);
                }
            }

            Button randomJ1Button = j1ButtonsInAlignment.get(random.nextInt(j1ButtonsInAlignment.size()));
            removePiece(root, randomJ1Button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

            j1ButtonsInAlignment.clear();
            finalValidAlignments.clear();
        }
        else if(!finalAlignments.isEmpty() && currentPlayer == 2){
            Random random = new Random();
            String[] randomAlignment = finalAlignments.get(random.nextInt(finalAlignments.size()));

            List<Button> j1ButtonsInAlignment = new ArrayList<>();
            for (String buttonId : randomAlignment) {
                Button button1 = getButtonById(buttonId);
                if (buttonsJ1.contains(button1)) {
                    j1ButtonsInAlignment.add(button1);
                }
            }

            Button randomJ1Button = j1ButtonsInAlignment.get(random.nextInt(j1ButtonsInAlignment.size()));
            removePiece(root, randomJ1Button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

            j1ButtonsInAlignment.clear();
            finalAlignments.clear();
        }
        else {
            removePiece(root, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);
        }
    }

    public static void checkPerfectAlignments() {
        for (String[] alignment : alignments) {
            int count = 0;
            Button buttonWithoutImage = null;
            List<Button> buttonsInAlignment = new ArrayList<>();

            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button != null && button.getGraphic() == null) {
                    buttonWithoutImage = button;
                }
                if (button != null) {
                    buttonsInAlignment.add(button);
                    if (buttonsJ1.contains(button)) {
                        count++;
                    }
                }
            }

            if (count == 2 && buttonWithoutImage != null) {
                String buttonIdWithoutImage = buttonWithoutImage.getId();
                boolean hasValidNeighbour = false;

                for (String[] neighbourPair : neighbourList) {
                    if (neighbourPair[0].equals(buttonIdWithoutImage) || neighbourPair[1].equals(buttonIdWithoutImage)) {
                        String neighbourId = neighbourPair[0].equals(buttonIdWithoutImage) ? neighbourPair[1] : neighbourPair[0];
                        Button neighbourButton = getButtonById(neighbourId);

                        if (neighbourButton != null && buttonsJ1.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
                            hasValidNeighbour = true;
                            break;
                        }
                    }
                }
                if (hasValidNeighbour) {
                    finalValidAlignments.add(alignment);
                }
            }
        }
    }

    public static void checkAlignments(){
        for (String[] alignment : alignments) {
            int count = 0;
            Button buttonWithoutImage = null;

            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button != null && button.getGraphic() == null) {
                    buttonWithoutImage = button;
                }
                if (button != null && buttonsJ1.contains(button)) {
                    count++;
                }
            }
            if (count == 2 && buttonWithoutImage != null) {
                finalAlignments.add(alignment);
            }
        }
    }
}