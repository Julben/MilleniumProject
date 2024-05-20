package org.example.milleniumproject.model;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import static org.example.milleniumproject.model.ButtonSelector.deselectButton;
import static org.example.milleniumproject.model.ButtonSelector.selectButton;
import static org.example.milleniumproject.model.EndParty.FinPartie;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.RemovePiece.*;

public class Movement {

    private static List<Button> FreeButtonsJ1 = new ArrayList<>();

    static void EasyMovement(StackPane root, Button button, Timeline timeline2, Label timerLabel2, Label timerLabel1, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, int difficulty) {
        if (buttonsJ2.size() > 3 && placementisfinished) {

            PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
            pause1.setOnFinished(e -> {
                List<Button> buttonsvoisinlibres = getFreeNeighbourButtons(buttonsJ2);

                Random random1 = new Random();
                int randomIndex1 = random1.nextInt(buttonsvoisinlibres.size());
                Button buttonJ2avecvoisinlibre = buttonsvoisinlibres.get(randomIndex1);

                selectButton(buttonJ2avecvoisinlibre);
                SoundPlayer.soundPlay();

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    Button voisinChoisi = getSelectedNeighbourButton(buttonJ2avecvoisinlibre);

                    ImageView imageView1 = (ImageView) buttonJ2avecvoisinlibre.getGraphic();
                    voisinChoisi.setGraphic(imageView1);
                    SoundPlayer.soundPlay();
                    buttonJ2avecvoisinlibre.setGraphic(null);
                    buttonsJ2.remove(buttonJ2avecvoisinlibre);
                    buttonsJ2.add(voisinChoisi);
                    deselectButton(buttonJ2avecvoisinlibre);
                    deselectButton(voisinChoisi);

                    buttonsvoisinlibres.clear();

                    resetButtonColorsForMovedButton(buttonJ2avecvoisinlibre);
                    checkButtonCombinations();

                    if (!isRemovePieceMode) {
                        currentPlayer = 1;
                        ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        if (isGameFinished()) {
                            System.out.println("3");
                            FinPartie(root, timeline1, timeline2, primaryStage);
                        }
                    } else {
                        Button randomFreeButton = getRandomFreeButtonJ1();
                        if (difficulty == 0) {
                            removePiece(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
                        } else if (difficulty == 1) {
                            HardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);
                        }
                        if (isGameFinished()) {
                            System.out.println("4");
                            FinPartie(root, timeline1, timeline2, primaryStage);
                        }
                    }
                });
                pause.play();
            });
            pause1.play();
        } else if (buttonsJ2.size() == 3 && placementisfinished) {
            PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
            pause1.setOnFinished(e -> {

                Random randomPion = new Random();
                int indexPion = randomPion.nextInt(buttonsJ2.size());
                Button pionRestantJ2 = buttonsJ2.get(indexPion);

                selectButton(pionRestantJ2);
                SoundPlayer.soundPlay();
                Button boutonVide = getEmptyButton(gridPane);

                if (boutonVide != null && pionRestantJ2 != null) {

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {

                        ImageView imageViewPion = (ImageView) pionRestantJ2.getGraphic();
                        boutonVide.setGraphic(imageViewPion);
                        SoundPlayer.soundPlay();
                        pionRestantJ2.setGraphic(null);
                        buttonsJ2.remove(pionRestantJ2);
                        buttonsJ2.add(boutonVide);
                        deselectButton(pionRestantJ2);
                        deselectButton(boutonVide);

                        resetButtonColorsForMovedButton(pionRestantJ2);
                        checkButtonCombinations();

                        if (!isRemovePieceMode) {
                            currentPlayer = 1;
                            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                            if (isGameFinished()) {
                                System.out.println("5");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        } else {
                            currentPlayer = 2;

                            Button randomFreeButton = RandomFreeJ1();

                            if (difficulty == 0) {
                                removePiece(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);

                            } else if (difficulty == 1) {
                                HardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);

                            }
                            if (isGameFinished()) {
                                System.out.println("6");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        }

                    });
                    pause.play();
                }
            });
            pause1.play();
        }
    }

    static void HardMovement(PartyIA root, Button button,Timeline timeline2, Label timerLabel2, Label timerLabel1,String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, GridPane gridpane, int difficulty){
        if (buttonsJ2.size() > 3 && placementisfinished){

            checkAlignments();
            checkAlignmentsJ1();

            if(!finalValidAlignments.isEmpty()){

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {

                    Random random = new Random();
                    int randomIndex = random.nextInt(finalValidAlignments.size());
                    String[] randomAlignment = finalValidAlignments.get(randomIndex);

                    Button clickedButton = null;
                    for(String id : randomAlignment){
                        if(getButtonById(id).getGraphic() == null){
                            clickedButton = getButtonById(id);
                        }
                    }

                    Button selectedButton = null;
                    for (String[] neighbourPair : neighbourList) {
                        if(neighbourPair[0].equals(clickedButton.getId()) || neighbourPair[1].equals(clickedButton.getId())){
                            String neighbourId = neighbourPair[0].equals(clickedButton.getId()) ? neighbourPair[1] : neighbourPair[0];
                            int compteur = 0;
                            for(String i : randomAlignment){
                                if (Objects.equals(i, neighbourId)){
                                    compteur ++;
                                }
                            }
                            if(compteur == 0 && buttonsJ2.contains(getButtonById(neighbourId))){
                                selectedButton = getButtonById(neighbourId);
                            }
                        }
                    }

                    selectButton(selectedButton);
                    SoundPlayer.soundPlay();

                    PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                    Button finalSelectedButton = selectedButton;
                    Button finalClickedButton = clickedButton;
                    pause1.setOnFinished(event -> {

                        ImageView imageView1 = (ImageView) finalSelectedButton.getGraphic();
                        finalClickedButton.setGraphic(imageView1);
                        SoundPlayer.soundPlay();
                        finalSelectedButton.setGraphic(null);
                        buttonsJ2.remove(finalSelectedButton);
                        buttonsJ2.add(finalClickedButton);
                        deselectButton(finalClickedButton);
                        deselectButton(finalSelectedButton);

                        finalValidAlignments.clear();
                        finalValidAlignmentsJ1.clear();

                        resetButtonColorsForMovedButton(finalSelectedButton);
                        checkButtonCombinations();

                        if(!isRemovePieceMode){
                            currentPlayer = 1;
                            PartyIA.ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                            if (isGameFinished()) {
                                System.out.println("7");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        }
                        else {
                            currentPlayer = 2;

                            Button randomFreeButton = RandomFreeJ1();

                            if(difficulty == 0){
                                removePiece(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                            } else if (difficulty == 1) {
                                HardRemove( root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);

                            }
                            if (isGameFinished()) {
                                System.out.println("8");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        }
                    });
                    pause1.play();
                });
                pause.play();
            }
            else if(!finalValidAlignmentsJ1.isEmpty()){

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {

                    Random random = new Random();
                    int randomIndex = random.nextInt(finalValidAlignmentsJ1.size());
                    String[] randomAlignment = finalValidAlignmentsJ1.get(randomIndex);

                    Button clickedButton = null;
                    for(String id : randomAlignment){
                        if(getButtonById(id).getGraphic() == null){
                            clickedButton = getButtonById(id);
                        }
                    }

                    Button selectedButton = null;
                    for (String[] neighbourPair : neighbourList) {
                        if(neighbourPair[0].equals(clickedButton.getId()) || neighbourPair[1].equals(clickedButton.getId())){
                            String neighbourId = neighbourPair[0].equals(clickedButton.getId()) ? neighbourPair[1] : neighbourPair[0];
                            int compteur = 0;
                            for(String i : randomAlignment){
                                if (Objects.equals(i, neighbourId)){
                                    compteur ++;
                                }
                            }
                            if(compteur == 0 && buttonsJ2.contains(getButtonById(neighbourId))){
                                selectedButton = getButtonById(neighbourId);
                            }
                        }
                    }

                    selectButton(selectedButton);
                    SoundPlayer.soundPlay();

                    PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                    Button finalSelectedButton = selectedButton;
                    Button finalClickedButton = clickedButton;
                    pause1.setOnFinished(event -> {

                        ImageView imageView1 = (ImageView) finalSelectedButton.getGraphic();
                        finalClickedButton.setGraphic(imageView1);
                        SoundPlayer.soundPlay();
                        finalSelectedButton.setGraphic(null);
                        buttonsJ2.remove(finalSelectedButton);
                        buttonsJ2.add(finalClickedButton);
                        deselectButton(finalClickedButton);
                        deselectButton(finalSelectedButton);

                        finalValidAlignmentsJ1.clear();
                        finalValidAlignments.clear();

                        resetButtonColorsForMovedButton(finalSelectedButton);
                        checkButtonCombinations();

                        if(!isRemovePieceMode){
                            currentPlayer = 1;
                            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                            if (isGameFinished()) {
                                System.out.println("9");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        }
                        else {
                            currentPlayer = 2;
                            Button randomFreeButton = RandomFreeJ1();
                            if (difficulty == 0){
                                removePiece(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                            } else if (difficulty == 1) {
                                HardRemove(root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);
                            }
                            if (isGameFinished()) {
                                System.out.println("10");
                                FinPartie(root, timeline1, timeline2, primaryStage);
                            }
                        }

                    });
                    pause1.play();
                });
                pause.play();
            }
            else {
                finalValidAlignments.clear();
                finalValidAlignmentsJ1.clear();
                Button randomFreeButton = getRandomFreeButtonJ1();
                EasyMovement(root, randomFreeButton, timeline2, timerLabel2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
            }
        }
        else if (buttonsJ2.size() == 3 && placementisfinished) {
            Button randomFreeButton = getRandomFreeButtonJ1();
            EasyMovement(root, randomFreeButton, timeline2,  timerLabel2,  timerLabel1,  chrono,  remainingSeconds2,  timeline1,  primaryStage, difficulty);
        }
    }

    public static void checkAlignments() {

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
                    if (buttonsJ2.contains(button)) {
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

                        if (neighbourButton != null && buttonsJ2.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
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

    public static void checkAlignmentsJ1() {
        for (String[] alignment : alignments) {
            int count = 0;
            Button buttonWithoutImage = null;

            List<Button> buttonsInAlignment = new ArrayList<>();

            for (String buttonId : alignment) {
                Button button = getButtonById(buttonId);
                if (button.getGraphic() == null) {
                    buttonWithoutImage = button;
                }
                buttonsInAlignment.add(button);
                if (buttonsJ1.contains(button)) {
                    count++;
                }

            }
            if (count == 2 && buttonWithoutImage != null) {
                String buttonIdWithoutImage = buttonWithoutImage.getId();
                boolean hasValidNeighbour = false;

                for (String[] neighbourPair : neighbourList) {
                    if (neighbourPair[0].equals(buttonIdWithoutImage) || neighbourPair[1].equals(buttonIdWithoutImage)) {
                        String neighbourId = neighbourPair[0].equals(buttonIdWithoutImage) ? neighbourPair[1] : neighbourPair[0];
                        Button neighbourButton = getButtonById(neighbourId);

                        if (neighbourButton != null && buttonsJ2.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
                            hasValidNeighbour = true;
                            break;
                        }
                    }
                }

                if (hasValidNeighbour) {
                    finalValidAlignmentsJ1.add(alignment);
                }
            }
        }
    }

    public static Button RandomFreeJ1() {

        FreeButtonsJ1.clear();

        for (Button i : buttonsJ1) {
            if (!isNotlibre(i)) {
                FreeButtonsJ1.add(i);
            }
        }
        if (FreeButtonsJ1.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(buttonsJ1.size());
            return buttonsJ1.get(index);
        } else {
            Random random = new Random();
            int index = random.nextInt(FreeButtonsJ1.size());
            return FreeButtonsJ1.get(index);
        }
    }
}