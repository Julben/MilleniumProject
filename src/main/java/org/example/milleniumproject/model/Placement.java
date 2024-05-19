package org.example.milleniumproject.model;

import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;
import static org.example.milleniumproject.model.PartyIA.*;
import javafx.scene.control.Button;

public class Placement {

    static void makeEasyPlacement(StackPane root,GridPane gridPane, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, String chrono, Stage primaryStage, int difficulty) {

        Button randomButton = getEmptyButton(gridPane);

        // Placer l'image de l'IA sur le bouton sélectionné
        placePlayerImage(randomButton, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
        buttonsJ2.add(randomButton);

        // Vérifier les combinaisons après chaque placement de pion
        checkButtonCombinations();

        if (isRemovePieceMode) {
            Button button = new Button();
            RemovePiece.CheckRemovedifficulty(root, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage, difficulty);
        } else {
            currentPlayer = 1;

            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
        }
        turns++;
    }

    static void makeHardPlacement(PartyIA root,GridPane gridPane, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, String chrono, Stage primaryStage, int difficulty) {
        // Vérifier si le joueur 1 est sur le point de faire un alignement
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
            // Si aucun alignement à bloquer, utiliser la méthode précédente pour placer le pion
            Button buttonToPlace = getPriorityButtonToPlace();

            // Si aucun bouton prioritaire n'est disponible, placer un pion aléatoire comme avant
            if (buttonToPlace == null) {
                buttonToPlace = getEmptyButton(gridPane);
            }

            // Placer l'image de l'IA sur le bouton sélectionné
            placePlayerImage(buttonToPlace, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
            buttonsJ2.add(buttonToPlace);
        }
        // Vérifier les combinaisons après chaque placement de pion
        checkButtonCombinations();

        if (isRemovePieceMode) {
            Button button = new Button();
            RemovePiece.CheckRemovedifficulty(root, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);

        } else {
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

            // Parcourir les boutons de l'alignement
            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button != null) {
                    if (isButtonOccupied(button, playerButtons)) { // Modifier cet appel
                        // Si le joueur a déjà placé un pion dans cette case, incrémenter le compteur
                        playerCount++;
                    } else if (!isButtonOccupied(button, null)) { // Modifier cet appel
                        // Sinon, enregistrer le bouton vide
                        emptyCount++;
                        emptyButton = buttonId;
                    }
                }
            }

            // Si le joueur a deux pions alignés et une case vide dans cet alignement, retourner la case vide pour bloquer l'alignement
            if (playerCount == 2 && emptyCount == 1) {
                return emptyButton;
            }
        }
        return null; // Aucun alignement à bloquer
    }

    static Button getPriorityButtonToPlace() {
        // Liste des boutons prioritaires à placer
        List<Button> priorityButtons = Arrays.asList(getButtonById("K"), getButtonById("E"), getButtonById("N"), getButtonById("T"));

        // Parcourir la liste des boutons prioritaires
        for (Button button : priorityButtons) {
            if (button != null && !isButtonOccupied(button, null)) { // Modifier cet appel
                return button; // Retourner le premier bouton disponible trouvé
            }
        }

        return null; // Aucun bouton prioritaire disponible
    }

    static boolean isButtonOccupied(Button button, List<Button> playerButtons) {
        // Vérifier si le bouton est occupé par un joueur
        if (playerButtons != null) {
            return playerButtons.contains(button);
        } else {
            return button.getGraphic() != null;
        }
    }
}