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
import org.example.milleniumproject.presentation.ButtonTransitionHandler;
import java.util.*;
import static org.example.milleniumproject.model.ButtonSelector.deselectButton;
import static org.example.milleniumproject.model.ButtonSelector.selectButton;
import static org.example.milleniumproject.model.EndParty.endGame;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.RemovePawn.*;
/**
 * Cette classe gère la logique du mouvement des pions.
 */
public class Movement {

    private static List<Button> FreeButtonsJ1 = new ArrayList<>();
    /**
     * Gère le mouvement pour l'IA en difficulté facile.
     *
     * @param root Conteneur principal de la scène.
     * @param button Le bouton qui a été cliqué.
     * @param timeline2 La timeline pour le chrono du deuxième joueur.
     * @param timerLabel2 Le label pour le chrono du deuxième joueur.
     * @param timerLabel1 Le label pour le chrono du premier joueur.
     * @param chrono Le chronomètre.
     * @param remainingSeconds2 Les secondes restantes pour le chrono du deuxième joueur.
     * @param timeline1 La chrono du premier joueur.
     * @param primaryStage La scène en premier plan.
     * @param difficulty Le niveau de difficulté de l'IA.
     */
    static void easyMovement(StackPane root, Button button, Timeline timeline2, Label timerLabel2, Label timerLabel1, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, int difficulty) {
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

                    if(VideoData.isAnimation()){
                        Button finalButtonJ2avecviosinlibre = buttonJ2avecvoisinlibre;
                        ButtonTransitionHandler.performTransition(buttonJ2avecvoisinlibre, voisinChoisi, buttonsJ2, () -> {

                            deselectButton(voisinChoisi);
                            deselectButton(buttonJ2avecvoisinlibre);

                            buttonsvoisinlibres.clear();

                            resetButtonColorsForMovedButton(finalButtonJ2avecviosinlibre);
                            checkButtonCombinations();

                            if (!isRemovePieceMode) {
                                currentPlayer = 1;
                                resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            } else {
                                Button randomFreeButton = getRandomFreeButtonJ1();
                                if (difficulty == 0) {
                                    easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
                                } else if (difficulty == 1) {
                                    hardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);
                                }
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            }
                        });
                    }
                    else {
                        ImageView imageView1 = (ImageView) buttonJ2avecvoisinlibre.getGraphic();
                        voisinChoisi.setGraphic(imageView1);
                        SoundPlayer.soundPlay();
                        buttonJ2avecvoisinlibre.setGraphic(null);
                        buttonsJ2.remove(buttonJ2avecvoisinlibre);
                        buttonsJ2.add(voisinChoisi);
                        deselectButton(buttonJ2avecvoisinlibre);
                        deselectButton(voisinChoisi);
                        deselectButton(buttonJ2avecvoisinlibre);

                        buttonsvoisinlibres.clear();

                        resetButtonColorsForMovedButton(buttonJ2avecvoisinlibre);
                        checkButtonCombinations();

                        if (!isRemovePieceMode) {
                            currentPlayer = 1;
                            resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                            if (isGameFinished()) {
                                endGame(root, timeline1, timeline2, primaryStage);
                            }
                        } else {
                            Button randomFreeButton = getRandomFreeButtonJ1();
                            if (difficulty == 0) {
                                easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);
                            } else if (difficulty == 1) {
                                hardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);
                            }
                            if (isGameFinished()) {
                                endGame(root, timeline1, timeline2, primaryStage);
                            }
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

                        if(VideoData.isAnimation()){
                            Button finalpionrestantJ2 = pionRestantJ2;
                            ButtonTransitionHandler.performTransition(pionRestantJ2, boutonVide, buttonsJ2, () -> {
                                deselectButton(boutonVide);
                                deselectButton(pionRestantJ2);

                                resetButtonColorsForMovedButton(finalpionrestantJ2);
                                checkButtonCombinations();

                                if (!isRemovePieceMode) {
                                    currentPlayer = 1;
                                    resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                } else {
                                    currentPlayer = 2;

                                    Button randomFreeButton = randomFreeJ1();

                                    if (difficulty == 0) {
                                        easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);

                                    } else if (difficulty == 1) {
                                        hardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);

                                    }
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                }
                            });
                        }
                        else {
                            ImageView imageView1 = (ImageView) pionRestantJ2.getGraphic();
                            boutonVide.setGraphic(imageView1);
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
                                resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            } else {
                                currentPlayer = 2;

                                Button randomFreeButton = randomFreeJ1();

                                if (difficulty == 0) {
                                    easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage);

                                } else if (difficulty == 1) {
                                    hardRemove(root, randomFreeButton, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage);

                                }
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            }
                        }
                    });
                    pause.play();
                }
            });
            pause1.play();
        }
    }
    /**
     * Gère le mouvement pour l'IA en difficulté difficile.
     *
     * @param root  Conteneur principal de la scène.
     * @param button Le bouton qui a été cliqué.
     * @param timeline2 La timeline pour le chrono du deuxième joueur.
     * @param timerLabel2 Le label pour le chrono du deuxième joueur.
     * @param timerLabel1 Le label pour le chrono du premier joueur.
     * @param chrono Le chronomètre.
     * @param remainingSeconds2 Les secondes restantes pour le minuteur du deuxième joueur.
     * @param timeline1 La timeline pour le chrono du premier joueur.
     * @param primaryStage  La scène en premier plan.
     * @param gridpane Le gridpane(plateau).
     * @param difficulty Le niveau de difficulté de l'IA.
     */
    static void hardMovement(PartyIA root, Button button, Timeline timeline2, Label timerLabel2, Label timerLabel1, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, GridPane gridpane, int difficulty){
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

                        if(VideoData.isAnimation()){
                            Button finalfinalselectedButton = finalSelectedButton;
                            ButtonTransitionHandler.performTransition(finalSelectedButton, finalClickedButton, buttonsJ2, () -> {
                                deselectButton(finalClickedButton);
                                deselectButton(finalSelectedButton);

                                finalValidAlignments.clear();
                                finalValidAlignmentsJ1.clear();

                                resetButtonColorsForMovedButton(finalfinalselectedButton);
                                checkButtonCombinations();

                                if(!isRemovePieceMode){
                                    currentPlayer = 1;
                                    PartyIA.resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                }
                                else {
                                    currentPlayer = 2;

                                    Button randomFreeButton = randomFreeJ1();

                                    if(difficulty == 0){
                                        easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                                    } else if (difficulty == 1) {
                                        hardRemove( root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);

                                    }
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                }
                            });
                        }
                        else {
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
                                PartyIA.resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            }
                            else {
                                currentPlayer = 2;

                                Button randomFreeButton = randomFreeJ1();

                                if(difficulty == 0){
                                    easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                                } else if (difficulty == 1) {
                                    hardRemove( root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);

                                }
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
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

                        if(VideoData.isAnimation()){
                            Button finalfinalselectedButton = finalSelectedButton;
                            ButtonTransitionHandler.performTransition(finalSelectedButton, finalClickedButton, buttonsJ2, () -> {
                                deselectButton(finalClickedButton);
                                deselectButton(finalSelectedButton);

                                finalValidAlignmentsJ1.clear();
                                finalValidAlignments.clear();

                                resetButtonColorsForMovedButton(finalfinalselectedButton);
                                checkButtonCombinations();

                                if(!isRemovePieceMode){
                                    currentPlayer = 1;
                                    resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                }
                                else {
                                    currentPlayer = 2;
                                    Button randomFreeButton = randomFreeJ1();
                                    if (difficulty == 0){
                                        easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                                    } else if (difficulty == 1) {
                                        hardRemove(root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);
                                    }
                                    if (isGameFinished()) {
                                        endGame(root, timeline1, timeline2, primaryStage);
                                    }
                                }
                            });
                        }
                        else {
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
                                resetTimer(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
                            }
                            else {
                                currentPlayer = 2;
                                Button randomFreeButton = randomFreeJ1();
                                if (difficulty == 0){
                                    easyRemove(root, randomFreeButton, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);

                                } else if (difficulty == 1) {
                                    hardRemove(root,  button,  timeline2,  timerLabel2,  timerLabel1,  chrono, remainingSeconds2,  timeline1,  primaryStage);
                                }
                                if (isGameFinished()) {
                                    endGame(root, timeline1, timeline2, primaryStage);
                                }
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
                easyMovement(root, randomFreeButton, timeline2, timerLabel2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
            }
        }
        else if (buttonsJ2.size() == 3 && placementisfinished) {
            Button randomFreeButton = getRandomFreeButtonJ1();
            easyMovement(root, randomFreeButton, timeline2,  timerLabel2,  timerLabel1,  chrono,  remainingSeconds2,  timeline1,  primaryStage, difficulty);
        }
    }
    /**
     * Vérifie les alignements valides pour l'IA.
     */
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
    /**
     * Vérifie les alignements valides pour le joueur.
     */
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
    /**
     * Permet d'avoir un bouton.
     *
     * @return Un bouton libre aléatoire du joueur 1.
     */
    public static Button randomFreeJ1() {

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