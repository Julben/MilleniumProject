package org.example.milleniumproject.model;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import static org.example.milleniumproject.model.EndParty.FinPartie;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.RemovePiece.*;

public class Placement {

    static void makeEasyPlacement(StackPane root, Button button, GridPane gridPane, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, Label timerLabel1, String chrono, Stage primaryStage, int difficulty) {

        Button randomButton = getEmptyButton(gridPane);

        placePlayerImage(randomButton, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
        buttonsJ2.add(randomButton);

        checkButtonCombinations();
        if (isRemovePieceMode) {
            Button randomFreeButton = getRandomFreeButtonJ1();
            if(difficulty == 0){
                removePiece(root,randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
            } else if (difficulty == 1) {
                HardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);
            }
        } else {
            if (isGameFinished() && placementisfinished) {
                FinPartie(root,timeline1, timeline2, primaryStage);
            }
            currentPlayer = 1;
            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
        }
        turns++;


    }

    static void makeHardPlacement(StackPane root, Button button,GridPane gridPane, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, Label timerLabel1, String chrono, Stage primaryStage, int difficulty) {

        String buttonToFill =  checkAlignment(buttonsJ2);
        String buttonToBlock = checkAlignment(buttonsJ1);

        if (buttonToFill != null) {
            Button buttonToPlace = getButtonById(buttonToFill);
            placePlayerImage(buttonToPlace, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
            buttonsJ2.add(buttonToPlace);
        } else if (buttonToBlock != null) {

            Button buttonToPlace = getButtonById(buttonToBlock);
            placePlayerImage(buttonToPlace, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
            buttonsJ2.add(buttonToPlace);
        }
        else {
            Button buttonToPlace = getPriorityButtonToPlace();

            if (buttonToPlace == null) {
                buttonToPlace = getEmptyButton(gridPane);
            }

            placePlayerImage(buttonToPlace, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
            buttonsJ2.add(buttonToPlace);
        }
        checkButtonCombinations();

        if (isRemovePieceMode) {
            Button randomFreeButton = getRandomFreeButtonJ1();
            if(difficulty == 0){
                removePiece(root,randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
            } else if (difficulty == 1) {
                HardRemove( root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);
            }
        } else {
            if (isGameFinished() && placementisfinished) {
                FinPartie(root,timeline1, timeline2, primaryStage);
            }
            currentPlayer = 1;
            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
        }
        turns++;
    }

    static String checkAlignment(List<Button> playerButtons) {
        for (String[] alignment : alignments) {
            int playerCount = 0;
            int emptyCount = 0;
            String emptyButton = null;

            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button != null) {
                    if (isButtonOccupiedByPlayer(button, playerButtons)) {
                        playerCount++;
                    } else if (!isButtonOccupied(button)) {
                        emptyCount++;
                        emptyButton = buttonId;
                    }
                }
            }
            if (playerCount == 2 && emptyCount == 1) {
                return emptyButton;
            }
        }
        return null;
    }


    static boolean isButtonOccupiedByPlayer(Button button, List<Button> playerButtons) {
        return playerButtons.contains(button);
    }



    static Button getPriorityButtonToPlace() {
        List<Button> priorityButtons = Arrays.asList(getButtonById("K"), getButtonById("E"), getButtonById("N"), getButtonById("T"));

        for (Button button : priorityButtons) {
            if (button != null && !isButtonOccupied(button)) {
                return button;
            }
        }

        return null;
    }

    static boolean isButtonOccupied(Button button) {
        return button.getGraphic() != null;
    }
}