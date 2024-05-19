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
import static org.example.milleniumproject.model.EndParty.isGameFinished;
import static org.example.milleniumproject.model.PartyIA.*;

public class RemovePiece {

    private static List<Button> FreeButtonsJ1 = new ArrayList<>();

    static void RemovePiece(StackPane root, Button button, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage) {

        if (button.getGraphic() instanceof ImageView) {
            if(currentPlayer==1) {
                for(Button b : buttonsJ2){
                    if (!isNotlibre(b)){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ2.contains(button) && !isNotlibre(button)){
                    updateButtonState(button,buttonsJ2);
                    currentPlayer = 2;
                }
                else if(!boutonlibre && buttonsJ2.contains(button)){
                    updateButtonState2(button,buttonsJ2);
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
                    Button finalButton1 = button;
                    pause.setOnFinished(event -> {
                        updateButtonState(finalButton1, buttonsJ1);
                        ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        if (isGameFinished()) {
                            FinPartie(root, timeline1, timeline2, primaryStage);
                        }
                    });
                    pause.play();
                    currentPlayer = 1;
                }
                else if(!boutonlibre && buttonsJ1.contains(button)) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    Button finalButton = button;
                    pause.setOnFinished(event -> {
                        updateButtonState2(finalButton, buttonsJ1);
                        ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        if (isGameFinished()) {
                            FinPartie(root, timeline1, timeline2, primaryStage);
                        }
                    });
                    pause.play();
                    currentPlayer = 1;
                }
            }
        }
        FreeButtonsJ1.clear();
    }

    public static void updateButtonState(Button button, List<Button> buttonsList) {
        button.setGraphic(null);
        SoundPlayer.soundPlay();
        buttonsList.remove(button);
        isRemovePieceMode = false;
        boutonlibre = false;
    }

    public static void updateButtonState2(Button button, List<Button> buttonsList) {
        updateButtonState(button,buttonsList);
        resetButtonColorsForMovedButton(button);
    }

    static void CheckRemovedifficulty(StackPane root, Button button, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, int difficulty) {

        checkPerfectAlignments();
        checkAlignments();

        if(!finalValidAlignments.isEmpty() && difficulty == 1 && currentPlayer == 2){
            Random random = new Random();
            String[] randomAlignment = finalValidAlignments.get(random.nextInt(finalValidAlignments.size()));

            // Filtrer les boutons appartenant à buttonsJ1 dans l'alignement sélectionné
            List<Button> j1ButtonsInAlignment = new ArrayList<>();
            for (String buttonId : randomAlignment) {
                Button button1 = getButtonById(buttonId);
                if (buttonsJ1.contains(button1)) {
                    j1ButtonsInAlignment.add(button1);
                }
            }

            Button randomJ1Button = j1ButtonsInAlignment.get(random.nextInt(j1ButtonsInAlignment.size()));
            RemovePiece(root, randomJ1Button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

            finalValidAlignments.clear();
        }
        else if(!finalAlignments.isEmpty() && difficulty == 1 && currentPlayer == 2){
            Random random = new Random();
            String[] randomAlignment = finalAlignments.get(random.nextInt(finalAlignments.size()));

            // Filtrer les boutons appartenant à buttonsJ1 dans l'alignement sélectionné
            List<Button> j1ButtonsInAlignment = new ArrayList<>();
            for (String buttonId : randomAlignment) {
                Button button1 = getButtonById(buttonId);
                if (buttonsJ1.contains(button1)) {
                    j1ButtonsInAlignment.add(button1);
                }
            }

            Button randomJ1Button = j1ButtonsInAlignment.get(random.nextInt(j1ButtonsInAlignment.size()));
            RemovePiece(root, randomJ1Button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

            finalAlignments.clear();
        }
        else {
            if(currentPlayer==2) {
                button = RemovePiece.RandomFreeJ1();
            }
            RemovePiece.RemovePiece(root, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);
        }
    }

    public static void checkPerfectAlignments() {
        // Boucle sur chaque élément de alignments
        for (String[] alignment : alignments) {
            int count = 0;
            Button buttonWithoutImage = null;
            List<Button> buttonsInAlignment = new ArrayList<>();

            // Identifier le bouton sans image et collecter les boutons présents dans l'alignement
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

            // Si on a exactement deux boutons dans buttonsJ2 et un bouton sans image
            if (count == 2 && buttonWithoutImage != null) {
                String buttonIdWithoutImage = buttonWithoutImage.getId();
                boolean hasValidNeighbour = false;

                // Vérifier les voisins du bouton sans image dans neighbourList
                for (String[] neighbourPair : neighbourList) {
                    if (neighbourPair[0].equals(buttonIdWithoutImage) || neighbourPair[1].equals(buttonIdWithoutImage)) {
                        String neighbourId = neighbourPair[0].equals(buttonIdWithoutImage) ? neighbourPair[1] : neighbourPair[0];
                        Button neighbourButton = getButtonById(neighbourId);

                        // Vérifier si le voisin appartient à buttonsJ2 et n'est pas déjà dans l'alignement
                        if (neighbourButton != null && buttonsJ1.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
                            hasValidNeighbour = true;
                            break;
                        }
                    }
                }

                // Si un voisin valide est trouvé, ajouter l'alignement à la liste finale
                if (hasValidNeighbour) {
                    finalValidAlignments.add(alignment);
                }
            }
        }
    }

    public static void checkAlignments(){
        // Boucle sur chaque élément de alignments
        for (String[] alignment : alignments) {
            int count = 0;
            Button buttonWithoutImage = null;

            // Identifier le bouton sans image et compter les boutons présents dans buttonsJ1
            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button != null && button.getGraphic() == null) {
                    buttonWithoutImage = button;
                }
                if (button != null && buttonsJ1.contains(button)) {
                    count++;
                }
            }

            // Si on a exactement deux boutons dans buttonsJ1 et un bouton sans image
            if (count == 2 && buttonWithoutImage != null) {
                finalAlignments.add(alignment);
            }
        }
    }

    public static Button RandomFreeJ1(){

        for(Button i : buttonsJ1){
            if(!isNotlibre(i)){
                FreeButtonsJ1.add(i);
            }
        }

        Button randomFreeButton;

        if(FreeButtonsJ1.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(buttonsJ1.size());
            randomFreeButton = buttonsJ1.get(index);
        }
        else{
            Random random = new Random();
            int index = random.nextInt(FreeButtonsJ1.size());
            randomFreeButton = FreeButtonsJ1.get(index);
        }

        return randomFreeButton;
    }
}