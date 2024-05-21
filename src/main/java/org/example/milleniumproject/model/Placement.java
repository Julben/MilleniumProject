package org.example.milleniumproject.model;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import static org.example.milleniumproject.model.EndParty.endGame;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.RemovePawn.*;
/**
 * Cette classe gère le placement des pions lors de la partie.
 */
public class Placement {
    /**
     * Réalise un placement facile pour l'IA.
     *
     * @param root               Conteneur principal de la scène.
     * @param button             Le bouton du plateau(gridPane).
     * @param gridPane           La grille de jeu(plateau).
     * @param timeline1          La timeline pour le joueur 1.
     * @param timeline2          La timeline pour le joueur 2.
     * @param remainingSeconds2 Le temps restant pour le joueur 2.
     * @param timerLabel2        Le chrone pour le joueur 2.
     * @param timerLabel1        Le chrone pour le joueur 1.
     * @param chrono             Le chronomètre.
     * @param primaryStage       La scène en premier plan.
     * @param difficulty         Le niveau de difficulté de l'IA.
     */
    static void makeEasyPlacement(StackPane root, Button button, GridPane gridPane, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, Label timerLabel1, String chrono, Stage primaryStage, int difficulty) {

        Button randomButton = getEmptyButton(gridPane);

        placePlayerImage(randomButton, rightVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
        buttonsJ2.add(randomButton);

        checkButtonCombinations();
        if (isRemovePieceMode) {
            Button randomFreeButton = getRandomFreeButtonJ1();
            if(difficulty == 0){
                easyRemove(root,randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
            } else if (difficulty == 1) {
                hardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);
            }
        } else {
            if (isGameFinished() && placementisfinished) {
                endGame(root,timeline1, timeline2, primaryStage);
            }
            currentPlayer = 1;
            resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
        }
        turns++;


    }
    /**
     * Réalise un placement facile pour l'IA.
     *
     * @param root               Conteneur principal de la scène.
     * @param button             Le bouton du plateau(gridPane).
     * @param gridPane           La grille de jeu(plateau).
     * @param timeline1          La timeline pour le joueur 1.
     * @param timeline2          La timeline pour le joueur 2.
     * @param remainingSeconds2 Le temps restant pour le joueur 2.
     * @param timerLabel2        Le chrone pour le joueur 2.
     * @param timerLabel1        Le chrone pour le joueur 1.
     * @param chrono             Le chronomètre.
     * @param primaryStage       La scène en premier plan.
     * @param difficulty         Le niveau de difficulté de l'IA.
     */
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
                easyRemove(root,randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
            } else if (difficulty == 1) {
                hardRemove( root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);
            }
        } else {
            if (isGameFinished() && placementisfinished) {
                endGame(root,timeline1, timeline2, primaryStage);
            }
            currentPlayer = 1;
            resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
        }
        turns++;
    }
    /**
     * Vérifie si il y a un alignement à effectuer.
     *
     * @param playerButtons La liste des boutons du joueur.
     * @return Le bouton à placer s'il y a une possibilité d'alignement ou null.
     */
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
    /**
     * Obtient le bouton à placer en priorité.
     *
     * @return Le bouton à placer en priorité.
     */
    static Button getPriorityButtonToPlace() {
        List<Button> priorityButtons = Arrays.asList(getButtonById("K"), getButtonById("E"), getButtonById("N"), getButtonById("T"));

        for (Button button : priorityButtons) {
            if (button != null && !isButtonOccupied(button)) {
                return button;
            }
        }

        return null;
    }
    /**
     * Vérifie si un bouton est occupé par un joueur.
     *
     * @param button        Le bouton à vérifier.
     * @param playerButtons La liste des boutons du joueur.
     * @return true si le bouton est occupé par le joueur ou false.
     */
    static boolean isButtonOccupiedByPlayer(Button button, List<Button> playerButtons) {
        return playerButtons.contains(button);
    }
    /**
     * Vérifie si un bouton est occupé.
     *
     * @param button Le bouton à vérifier.
     * @return true si le bouton est occupé par le joueur ou false.
     */
    static boolean isButtonOccupied(Button button) {

        return button.getGraphic() != null;
    }
}