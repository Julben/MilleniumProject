package org.example.milleniumproject.model;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import static org.example.milleniumproject.model.ButtonSelector.deselectButton;
import static org.example.milleniumproject.model.ButtonSelector.selectButton;
import static org.example.milleniumproject.model.EndParty.FinPartie;
import static org.example.milleniumproject.model.EndParty.isGameFinished;
import static org.example.milleniumproject.model.PartyIA.*;

public class Movement {

    public static void EasyMovement(PartyIA root, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, GridPane gridPane, int difficulty){
        if (buttonsJ2.size()>3 && placementisfinished){

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
                    afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
                });
                pause.play();
            });
            pause1.play();
        }
        else if (buttonsJ2.size()==3 && placementisfinished) {

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                // Sélectionner aléatoirement l'un des trois pions restants du joueur 2
                Random randomPion = new Random();
                int indexPion = randomPion.nextInt(buttonsJ2.size());
                Button pionRestantJ2 = buttonsJ2.get(indexPion);
                selectButton(pionRestantJ2);
                SoundPlayer.soundPlay();

                // Sélectionner un bouton vide
                Button boutonVide = PartyIA.getEmptyButton(gridPane);

                // Si on trouve un bouton vide et un pion restant
                if (boutonVide != null && pionRestantJ2 != null) {

                    PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                    pause1.setOnFinished(e -> {
                        // Placer l'image du pion restant sur le bouton vide
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
                        afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
                    });
                    pause1.play();
                }
            });
            pause.play();
        }
    }

    public static void CheckMovementDifficulty(PartyIA root, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, GridPane gridpane, int difficulty){
        if (buttonsJ2.size() > 3 && placementisfinished){

            Movement.checkAlignments(buttonsJ2);
            Movement.checkAlignmentsJ1(buttonsJ1, buttonsJ2);

            if(!finalValidAlignments.isEmpty() && difficulty == 1){

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
                    System.out.println("1 : "+selectedButton);

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

                        resetButtonColorsForMovedButton(finalSelectedButton);
                        checkButtonCombinations();
                        afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);

                    });
                    pause1.play();
                });
                pause.play();
            }
            else if(!finalValidAlignmentsJ1.isEmpty() && difficulty == 1){

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
                    System.out.println("2 : "+selectedButton);

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

                        resetButtonColorsForMovedButton(finalSelectedButton);
                        checkButtonCombinations();
                        afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);

                    });
                    pause1.play();
                });
                pause.play();
            }
            else {
                EasyMovement(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, gridpane, difficulty);
            }
        }
        else if (buttonsJ2.size() == 3 && placementisfinished) {
            RemovePiece.checkAlignments();
            if(finalAlignments.isEmpty() && difficulty == 1){
                checkPerfectAlignmentsAndSwap();
                checkButtonCombinations();
                afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
            }
            else if(!finalAlignments.isEmpty() && difficulty == 1){

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    Random random = new Random();
                    int randomIndex = random.nextInt(finalAlignments.size());
                    String[] randomAlignment = finalAlignments.get(randomIndex);

                    Button clickedButton = null;
                    for(String id : randomAlignment){
                        if(getButtonById(id).getGraphic() == null){
                            clickedButton = getButtonById(id);
                        }
                    }

                    Button selectedButton = null;
                    for(Button i : buttonsJ2){
                        Random random1 = new Random();
                        int index = random1.nextInt(buttonsJ2.size());
                        selectedButton = buttonsJ2.get(index);
                        selectButton(clickedButton);
                        SoundPlayer.soundPlay();
                    }

                    PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
                    Button finalSelectedButton = selectedButton;
                    Button finalClickedButton = clickedButton;
                    pause1.setOnFinished(e -> {
                        ImageView imageView1 = (ImageView) finalSelectedButton.getGraphic();
                        finalClickedButton.setGraphic(imageView1);
                        SoundPlayer.soundPlay();
                        finalSelectedButton.setGraphic(null);
                        buttonsJ2.remove(finalSelectedButton);
                        buttonsJ2.add(finalClickedButton);
                        deselectButton(finalClickedButton);
                        deselectButton(finalSelectedButton);

                        finalAlignments.clear();

                        resetButtonColorsForMovedButton(finalSelectedButton);
                        checkButtonCombinations();
                        afterGame(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
                    });
                    pause1.play();
                });
                pause.play();
            }
            else {
                EasyMovement(root, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, gridpane, difficulty);
            }
        }
    }

    public static void checkAlignments(List<Button> buttonsJ2) {
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
                    if (buttonsJ2.contains(button)) {
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
                        if (neighbourButton != null && buttonsJ2.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
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

    static Button getSelectedNeighbourButton(Button button) {
        List<Button> neighbours = new ArrayList<>();

        String id = button.getId();

        for (String[] neighbour : neighbourList) {
            if (neighbour[0].equals(id)) {
                Button neighbourButton = getButtonById(neighbour[1]);
                if (neighbourButton.getGraphic() == null) {
                    neighbours.add(neighbourButton);
                }
            } else if (neighbour[1].equals(id)) {
                Button neighbourButton = getButtonById(neighbour[0]);
                if (neighbourButton.getGraphic() == null) {
                    neighbours.add(neighbourButton);
                }
            }
        }

        if (!neighbours.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(neighbours.size());
            return neighbours.get(randomIndex);
        }

        return null;
    }

    static List<Button> getFreeNeighbourButtons(List<Button> buttonsJ2) {

        List<Button> buttonsvoisinlibres = new ArrayList<>();

        for (Button b : buttonsJ2) {
            String id = b.getId();

            for (String[] neighbours : neighbourList) {
                if (neighbours[0].equals(id)) {
                    Button button = getButtonById(neighbours[1]);
                    if (button.getGraphic() == null) {
                        buttonsvoisinlibres.add(b);
                    }
                } else if (neighbours[1].equals(id)) {
                    Button button = getButtonById(neighbours[0]);
                    if (button.getGraphic() == null) {
                        buttonsvoisinlibres.add(b);
                    }
                }
            }
        }

        return buttonsvoisinlibres;
    }

    public static void checkAlignmentsJ1(List<Button> buttonsJ1, List<Button> buttonsJ2) {
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
                        if (neighbourButton != null && buttonsJ2.contains(neighbourButton) && !buttonsInAlignment.contains(neighbourButton)) {
                            hasValidNeighbour = true;
                            break;
                        }
                    }
                }

                // Si un voisin valide est trouvé, ajouter l'alignement à la liste finale
                if (hasValidNeighbour) {
                    finalValidAlignmentsJ1.add(alignment);
                }
            }
        }
    }

    public static void checkPerfectAlignmentsAndSwap() {
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
                    if (buttonsJ2.contains(button)) {
                        count++;
                    }
                }
            }

            // Si on a exactement deux boutons dans buttonsJ2 et un bouton sans image
            if (count == 2 && buttonWithoutImage != null) {
                // Trouver un bouton de buttonsJ2 qui n'est pas dans l'alignement
                for (Button button : buttonsJ2) {
                    if (!buttonsInAlignment.contains(button)) {
                        selectButton(button);

                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        Button finalButtonWithoutImage = buttonWithoutImage;
                        pause.setOnFinished(event -> {
                            ImageView imageView1 = (ImageView) button.getGraphic();
                            finalButtonWithoutImage.setGraphic(imageView1);
                            SoundPlayer.soundPlay();
                            button.setGraphic(null);
                            buttonsJ2.remove(button);
                            buttonsJ2.add(finalButtonWithoutImage);
                            deselectButton(button);
                            deselectButton(finalButtonWithoutImage);
                            buttonsInAlignment.clear();
                            resetButtonColorsForMovedButton(button);
                        });
                        pause.play();
                    }
                }
            }
        }
    }

    public static void afterGame(PartyIA root, Timeline timeline2, Label timerLabel2, String chrono, int[] remainingSeconds2, Timeline timeline1, Stage primaryStage, int difficulty){
        if(!isRemovePieceMode){
            currentPlayer = 1;
            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
            if (isGameFinished()) {
                FinPartie(root, timeline1, timeline2, primaryStage);
            }
        }
        else {
            currentPlayer = 2;
            Button button = new Button();
            RemovePiece.CheckRemovedifficulty(root, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
            if (isGameFinished()) {
                FinPartie(root, timeline1, timeline2, primaryStage);
            }
        }
    }
}