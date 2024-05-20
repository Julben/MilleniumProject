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

/**
 * La classe RemovePiece gère la suppression des pions.
 */
public class RemovePiece {
    /**
     * Supprime un pion alétoire du joueur 1.
     *
     * @param root            Conteneur principal de la scène.
     * @param button          Le bouton contenant le pion à supprimer.
     * @param timeline2       La timeline pour le second joueur ou l'IA.
     * @param timerLabel2     Le label du timer pour le second joueur ou l'IA.
     * @param chrono          Le chronomètre.
     * @param remainingSeconds2 Le temps restant pour le second joueur.
     * @param timeline1       La timeline pour le premier joueur.
     * @param primaryStage    La scène en premier plan.
     */
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

    /**
     * Met à jour les boutons contenant des pions.
     *
     * @param button         Le bouton à mettre à jour.
     * @param buttonsList    La liste de bouton du joueur 1 ou 2.
     * @param root           Conteneur principal de la scène.
     * @param timeline1      La timeline pour le premier joueur.
     * @param timeline2      La timeline pour le second joueur.
     * @param primaryStage   La scène en premier plan.
     */
    static void updateButtonState(Button button, List<Button> buttonsList, StackPane root, Timeline timeline1, Timeline timeline2, Stage primaryStage) {
        button.setGraphic(null);
        SoundPlayer.soundPlay();
        buttonsList.remove(button);
        isRemovePieceMode = false;
        boutonlibre = false;
    }
    /**
     Même utilité que updateButtonState mais retire le rectangle si un pion d'une ligne de 3 est supprimé.
     Prends les mêmes paramètres que updateButtonState.
     */
    static void updateButtonState2(Button button,List<Button> buttonsList, StackPane root, Timeline timeline1, Timeline timeline2, Stage primaryStage) {
        updateButtonState(button, buttonsList, root, timeline1, timeline2, primaryStage);
        resetButtonColorsForMovedButton(button);
    }
    /**
     * Supprime un pion du joueur 1 qui est sur une même ligne qu'un autre pion.
     *
     * @param root            Conteneur principal de la scène.
     * @param button          Le bouton contenant le pion à supprimer.
     * @param timeline2       La timeline pour le second joueur ou l'IA.
     * @param timerLabel2     Le label du timer pour le second joueur ou l'IA.
     * @param timerLabel1     Le label du timer pour le premier joueur.
     * @param chrono          Le chronomètre.
     * @param remainingSeconds2 Le temps restant pour le second joueur.
     * @param timeline1       La timeline pour le premier joueur.
     * @param primaryStage    La scène en premier plan.
     */
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
    /**
     * Vérifie les alignements des pions.
     */
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
    /**
     * Vérifie les alignements des pions.
     */
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