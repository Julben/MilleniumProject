package org.example.milleniumproject.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

import static org.example.milleniumproject.model.ButtonUtils.getNodeByRowColumnIndex;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.TRANSPARENT;
import static org.example.milleniumproject.model.Methodeia.disableMouseInteractions;

import javafx.application.Platform;
import javafx.scene.control.Button;
import java.util.Random;

public class PartyIA extends StackPane {
    private int currentPlayer = 1;
    private VBox leftVBox;
    private VBox rightVBox;
    private int turns = 0;
    private boolean isNoChrono = false;
    private ToggleGroup toggleGroup3;
    private HBox hbox3;
    private ToggleGroup toggleGroup2;
    private HBox hbox2;
    private int remainingSeconds = 30;
    private Timeline timeline;
    private Button selectedButton = null;
    private List<Button> buttonsJ1 = new ArrayList<>();
    private List<Button> buttonsJ2 = new ArrayList<>();
    private GridPane gridPane;
    private boolean placementisfinished = false;
    private boolean isRemovePieceMode = false;
    boolean boutonlibre = false;
    private VBox pauseMenu; // Conteneur pour le menu pause
    private VBox quitterMenu;
    private boolean change = false;
    Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
    Button randomButtonJ1 = getRandomButtonJ1();
    private static final List<String[]> neighbourList = Arrays.asList(
            new String[]{"A", "B"}, new String[]{"A", "J"}, new String[]{"B", "C"}, new String[]{"B", "E"}, new String[]{"C", "O"}, new String[]{"D", "E"}, new String[]{"D", "K"}, new String[]{"E", "F"},
            new String[]{"E", "H"}, new String[]{"F", "N"}, new String[]{"G", "H"}, new String[]{"G", "L"}, new String[]{"H", "I"}, new String[]{"I", "M"}, new String[]{"J", "K"}, new String[]{"J", "V"},
            new String[]{"K", "L"}, new String[]{"K", "S"}, new String[]{"L", "P"}, new String[]{"M", "R"}, new String[]{"M", "N"}, new String[]{"N", "O"}, new String[]{"N", "U"}, new String[]{"O", "X"},
            new String[]{"P", "Q"}, new String[]{"Q", "R"}, new String[]{"Q", "T"}, new String[]{"S", "T"}, new String[]{"T", "U"}, new String[]{"T", "W"}, new String[]{"V", "W"}, new String[]{"W", "X"}
    );
    private static final List<String[]> alignments = Arrays.asList(
            new String[]{"A", "B", "C"}, new String[]{"D", "E", "F"}, new String[]{"G", "H", "I"}, new String[]{"J", "K", "L"}, new String[]{"M", "N", "O"}, new String[]{"P", "Q", "R"},
            new String[]{"S", "T", "U"}, new String[]{"V", "W", "X"}, new String[]{"A", "J", "V"}, new String[]{"D", "K", "S"}, new String[]{"G", "L", "P"}, new String[]{"B", "E", "H"},
            new String[]{"Q", "T", "W"}, new String[]{"I", "M", "R"}, new String[]{"F", "N", "U"}, new String[]{"C", "O", "X"}
    );
    RectangleConstructor abc = new RectangleConstructor(0.38125*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, -0.2986*screenHeight); Rectangle ABC = abc.getRectangle();
    RectangleConstructor vwx = new RectangleConstructor(0.38125*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, 0.29861*screenHeight); Rectangle VWX = vwx.getRectangle();
    RectangleConstructor ajv = new RectangleConstructor(0.04297*screenWidth, 0.67778*screenHeight, -0.1672*screenWidth, 0.00000*screenHeight); Rectangle AJV = ajv.getRectangle();
    RectangleConstructor cox = new RectangleConstructor(0.04297*screenWidth, 0.67778*screenHeight, 0.16875*screenWidth, 0.00000*screenHeight); Rectangle COX = cox.getRectangle();
    RectangleConstructor def = new RectangleConstructor(0.26875*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, -0.1986*screenHeight); Rectangle DEF = def.getRectangle();
    RectangleConstructor stu = new RectangleConstructor(0.26875*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, 0.19861*screenHeight); Rectangle STU = stu.getRectangle();
    RectangleConstructor dks = new RectangleConstructor(0.04297*screenWidth, 0.47778*screenHeight, -0.1109*screenWidth, 0.00000*screenHeight); Rectangle DKS = dks.getRectangle();
    RectangleConstructor fnu = new RectangleConstructor(0.04297*screenWidth, 0.47778*screenHeight, 0.11250*screenWidth, 0.00000*screenHeight); Rectangle FNU = fnu.getRectangle();
    RectangleConstructor ghi = new RectangleConstructor(0.15859*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, -0.1000*screenHeight); Rectangle GHI = ghi.getRectangle();
    RectangleConstructor pqr = new RectangleConstructor(0.15859*screenWidth, 0.07639*screenHeight, 0.00078*screenWidth, 0.10000*screenHeight); Rectangle PQR = pqr.getRectangle();
    RectangleConstructor glp = new RectangleConstructor(0.04297*screenWidth, 0.28194*screenHeight, -0.0555*screenWidth, 0.00000*screenHeight); Rectangle GLP = glp.getRectangle();
    RectangleConstructor imr = new RectangleConstructor(0.04297*screenWidth, 0.28194*screenHeight, 0.05703*screenWidth, 0.00000*screenHeight); Rectangle IMR = imr.getRectangle();
    RectangleConstructor beh = new RectangleConstructor(0.04297*screenWidth, 0.27917*screenHeight, 0.00078*screenWidth, -0.1986*screenHeight); Rectangle BEH = beh.getRectangle();
    RectangleConstructor jkl = new RectangleConstructor(0.15703*screenWidth, 0.07639*screenHeight, -0.1109*screenWidth, 0.00000*screenHeight); Rectangle JKL = jkl.getRectangle();
    RectangleConstructor mno = new RectangleConstructor(0.15703*screenWidth, 0.07639*screenHeight, 0.11250*screenWidth, 0.00000*screenHeight); Rectangle MNO = mno.getRectangle();
    RectangleConstructor qtw = new RectangleConstructor(0.04297*screenWidth, 0.27917*screenHeight, 0.00078*screenWidth, 0.19861*screenHeight); Rectangle QTW = qtw.getRectangle();

    // Initialisez votre carte pour mapper les noms des rectangles à leurs instances
    Map<String, RectangleConstructor> rectangleMap = new HashMap<>();

    public PartyIA(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2) {
        this.toggleGroup3 = toggleGroup3;
        this.hbox3 = hbox3;

        rectangleMap.put("ABC", abc); rectangleMap.put("DEF", def); rectangleMap.put("GHI", ghi); rectangleMap.put("JKL", jkl);
        rectangleMap.put("MNO", mno); rectangleMap.put("PQR", pqr); rectangleMap.put("STU", stu); rectangleMap.put("VWX", vwx);
        rectangleMap.put("AJV", ajv); rectangleMap.put("DKS", dks); rectangleMap.put("GLP", glp); rectangleMap.put("BEH", beh);
        rectangleMap.put("QTW", qtw); rectangleMap.put("IMR", imr); rectangleMap.put("FNU", fnu); rectangleMap.put("COX", cox);

        int selectedIndex = PrePartyIA.getSelectedIndex(toggleGroup3, hbox3);

        String backgroundImage = "";
        if (selectedIndex == 0) {
            backgroundImage = "src/main/resources/FENABOO.png";
        } else if (selectedIndex == 1) {
            backgroundImage = "src/main/resources/FECORUSCANT.png";
        } else if (selectedIndex == 2) {
            backgroundImage = "src/main/resources/FEMUSTAPHAR.png";
        }
        BG ground = new BG(backgroundImage);
        setBackground(ground.getCustomBackground());

        this.toggleGroup2 = toggleGroup2;
        this.hbox2 = hbox2;

        int selectedIndexchrono = PreParty.getSelectedIndexchrono(toggleGroup2, hbox2);

        String chrono;

        if (selectedIndexchrono == 0){
            chrono = "30";
        } else if (selectedIndexchrono == 1){
            chrono = "60";
        } else {
            chrono = "0";
            isNoChrono = true;
        }

        int[] remainingSeconds1 = {Integer.parseInt(chrono)};
        int[] remainingSeconds2 = {Integer.parseInt(chrono)};
        Label timerLabel1 = new Label(chrono);
        Label timerLabel2 = new Label(chrono);
        timerLabel1.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        timerLabel2.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        Timeline timeline1 = Chrono(timerLabel1, remainingSeconds1);
        Timeline timeline2 = Chrono(timerLabel2, remainingSeconds2);

        gridPane = new GridPane();
        gridPane.setHgap(0.0171875*screenWidth); // Espacement horizontal entre les boutons
        gridPane.setVgap(0.0305556*screenHeight); // Espacement vertical entre les boutons
        gridPane.setAlignment(Pos.CENTER); // Positionnement au centre de la StackPane

        String[] buttonLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"};
        int[] rowIndices = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6};
        int[] colIndices = {0, 3, 6, 1, 3, 5, 2, 3, 4, 0, 1, 2, 4, 5, 6, 2, 3, 4, 1, 3, 5, 0, 3, 6, 6};

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = createStyledButton(buttonLabels[i]);
            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        String str = ProfileData.getShip(1);
        int lastIndex = str.lastIndexOf('/');
        String vaisseau1 = str.substring(lastIndex + 1);

        String str2 = ProfileData.getShip(2);
        int lastIndex2 = str2.lastIndexOf('/');
        String vaisseau2 = str2.substring(lastIndex2 + 1);

        leftVBox = ProfilParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfilParty.createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * Constant.screenWidth); // Espacement horizontal entre les Vbox
        hBox.getChildren().addAll(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(leftVBox, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightVBox, Pos.CENTER_RIGHT);

        String avatar1 = ProfileData.getAvatar(1);
        String playerName1 = ProfileData.getPlayerName(1);
        String rank1 = ProfileData.getRank(1);

        String avatar2 = ProfileData.getAvatar(2);
        String playerName2 = ProfileData.getPlayerName(2);
        String rank2 = ProfileData.getRank(2);

        String avatarFileName1 = avatar1.substring(avatar1.lastIndexOf('/') + 1);
        String avatarFileName2 = avatar2.substring(avatar2.lastIndexOf('/') + 1);

        VBox profileBox1 = ProfilParty.createProfileBox(avatarFileName1, playerName1, rank1, timerLabel1, true, false);
        VBox profileBox2 = ProfilParty.createProfileBox(avatarFileName2, playerName2, rank2, timerLabel2, false, true);
        setMargin(profileBox1, new Insets(0, 0, 0.020833*screenHeight, 0.015625*screenWidth));
        setMargin(profileBox2, new Insets(0, 0.015625*screenWidth, 0.020833*screenHeight, 0));

        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

        // Création du bouton pause avec une image
        Image pauseImage = new Image("pause.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image
        ImageView imageView = new ImageView(pauseImage);
        imageView.setFitWidth(0.025*screenWidth); // Ajustez la largeur de l'image selon vos besoins
        imageView.setFitHeight(0.04444*screenHeight); // Ajustez la hauteur de l'image selon vos besoins

        Button pauseButton = new Button();
        pauseButton.setGraphic(imageView); // Définit l'image comme graphique du bouton

        // Rendre l'arrière-plan du bouton invisible
        pauseButton.setStyle("-fx-background-color: transparent");

        // Ajout d'une action pour afficher le menu pause lors du clic sur le bouton pause
        pauseButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            timeline1.pause();
            timeline2.pause();
            pauseMenu.setVisible(true);
        });

        // Positionnement du bouton pause en haut à droite
        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        setMargin(pauseButton, new Insets(0.0138889*screenHeight, 0.0078*screenWidth, 0, 0));

        pauseMenu = createPauseMenu(primaryStage, timeline1, timeline2);
        pauseMenu.setVisible(false);
        quitterMenu.setVisible(false);

        getChildren().addAll(hBox, profileBox1, profileBox2, ABC, VWX, AJV, COX, DEF, STU, DKS, FNU, GHI, PQR, GLP, IMR, BEH, JKL, MNO, QTW, gridPane, pauseMenu, quitterMenu, pauseButton);

        timeline1.play();

        // Gestionnaire d'événements pour les boutons du GridPane
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> {
                    SoundPlayer.soundPlay();
                    handleButtonClick(button, gridPane, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono);
                });
            }
        }
    }




    // Méthode pour retirer un pion adverse
    private void removePiece(Button button) {
        // Vérifier si le bouton cliqué contient une image
        if (button.getGraphic() instanceof ImageView) {
            if(currentPlayer==1) {
                for(Button b : buttonsJ2){
                    if (!isNotlibre(b)){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ2.contains(button) && !isNotlibre(button)){
                    button.setGraphic(null);
                    buttonsJ2.remove(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
                else if(!boutonlibre && buttonsJ2.contains(button)){
                    button.setGraphic(null);
                    buttonsJ2.remove(button);
                    resetButtonColorsForMovedButton(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
            }
            else if(currentPlayer==2) {
                for(Button b : buttonsJ1){
                    if (!isNotlibre(b)){
                        boutonlibre = true;
                    }
                }
                if(boutonlibre && buttonsJ1.contains(button) && !isNotlibre(button)){
                    button.setGraphic(null);
                    buttonsJ1.remove(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
                else if(!boutonlibre && buttonsJ1.contains(button)){
                    button.setGraphic(null);
                    buttonsJ1.remove(button);
                    resetButtonColorsForMovedButton(button);
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    isRemovePieceMode = false;
                    boutonlibre = false;
                }
            }
        }
    }


    // Méthode pour retourner un bouton du joueur 1 de manière aléatoire
    public Button getRandomButtonJ1() {
        // Vérifier si la liste des boutons du joueur 1 est vide
        if (buttonsJ1.isEmpty()) {
            return null; // Retourner null s'il n'y a aucun bouton
        }

        // Générer un index aléatoire dans la plage des boutons du joueur 1
        Random random = new Random();
        int randomIndex = random.nextInt(buttonsJ1.size());

        // Récupérer le bouton à partir de l'index généré aléatoirement
        return buttonsJ1.get(randomIndex);
    }

    public Button getRandomFreeButtonJ1() {
        // Créer une liste pour stocker les boutons libres du joueur 1
        List<Button> freeButtonsJ1 = new ArrayList<>();

        // Parcourir tous les boutons du joueur 1 et ajouter les boutons libres à la liste
        for (Button button : buttonsJ1) {
            if (!isNotlibre(button)) {
                freeButtonsJ1.add(button);
            }
        }

        // Vérifier s'il y a des boutons libres du joueur 1
        if (freeButtonsJ1.isEmpty()) {
            return null; // Retourner null s'il n'y a aucun bouton libre
        }

        // Générer un index aléatoire dans la plage des boutons libres du joueur 1
        Random random = new Random();
        int randomIndex = random.nextInt(freeButtonsJ1.size());

        // Récupérer le bouton libre à partir de l'index généré aléatoirement
        return freeButtonsJ1.get(randomIndex);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////















    // Méthode pour gérer le clic sur le bouton
    private void handleButtonClick(Button button, GridPane gridpane, Timeline timeline1, Timeline timeline2, Label timerLabel1, Label timerLabel2, int[] remainingSeconds1, int[] remainingSeconds2, String chrono) {

        if (isRemovePieceMode) {
            // Si le mode de suppression de pion est activé
            removePiece(button);

            if (currentPlayer==1){
                ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);


            }
            else {
                ResetChrono(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);
                // Désactiver la souris pendant une seconde
                disableMouseInteractions(gridpane, true);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    makeRandomMove();
                    disableMouseInteractions(gridpane, false);
                });
                pause.play();
            }

            // Vérifier si la partie est terminée
            if(placementisfinished){
                if (isGameFinished()) {
                    // La partie est terminée, afficher un message ou prendre toute autre action nécessaire
                    System.out.println("La partie est terminée.");
                    // Vous pouvez ajouter du code pour afficher un message ou terminer la partie ici
                }
            }
        }
        else {
            // Vérifier si le bouton n'a pas déjà d'image et si tous les tours n'ont pas été joués
            if (button.getGraphic() == null && turns < 18) {
                // Placer l'image du joueur sur le bouton en fonction du joueur 1
                if (currentPlayer == 1) {
                    placePlayerImage(button, leftVBox);
                    buttonsJ1.add(button);
                    // Vérifier les combinaisons après chaque placement de pion
                    checkButtonCombinations();
                    if(isRemovePieceMode){
                        currentPlayer = 1;

                    } else {
                        currentPlayer = 2;

                    }
                    turns++;
                    // Désactiver la souris pendant une seconde
                    disableMouseInteractions(gridpane, true);

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        makeRandomMove();
                        // Réactiver la souris
                        disableMouseInteractions(gridpane, false);
                    });
                    pause.play();
                }

            }
            else if ( turns >= 18) {
                placementisfinished = true;
                // Vérifier si le bouton cliqué appartient à la liste des boutons autorisés à être sélectionnés par le joueur actuel
                if (currentPlayer == 1 && (buttonsJ1.contains(button) || button.getGraphic() == null)) {
                    handleSelection(buttonsJ1, button, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono);
                } else if (currentPlayer == 2 && (buttonsJ2.contains(button) || button.getGraphic() == null)) {
                    handleSelection(buttonsJ2, button, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono);
                }
                // Vérifier si la partie est terminée
                if (isGameFinished()) {
                    // La partie est terminée, afficher un message ou prendre toute autre action nécessaire
                    System.out.println("La partie est terminée.");
                    // Vous pouvez ajouter du code pour afficher un message ou terminer la partie ici
                }
            }
        }
    }

    private void makeRandomMove() {
        // Vérifier si c'est le tour de l'IA
        if (currentPlayer == 2 && !isRemovePieceMode && turns < 18) {
            // Sélectionner un bouton aléatoire parmi les boutons disponibles
            Random random = new Random();
            Button randomButton = null;
            do {
                int index = random.nextInt(gridPane.getChildren().size());
                Node node = gridPane.getChildren().get(index);
                if (node instanceof Button) {
                    randomButton = (Button) node;
                }
            } while (randomButton == null || randomButton.getGraphic() != null);

            // Placer l'image de l'IA sur le bouton sélectionné
            placePlayerImage(randomButton, rightVBox);
            buttonsJ2.add(randomButton);

            // Vérifier les combinaisons après chaque placement de pion
            checkButtonCombinations();
            if(isRemovePieceMode){
                currentPlayer = 2;
            } else {
                currentPlayer = 1;

            }
            turns++;
        }
    }










    // Méthode pour placer l'image du joueur sur un bouton
    private void placePlayerImage(Button button, VBox playerVBox) {
        // Obtenir le GridPane enfant de la VBox
        GridPane gridPane = (GridPane) playerVBox.getChildren().get(0);

        // Vérifier si le GridPane contient des images
        if (!gridPane.getChildren().isEmpty()) {
            // Récupérer l'image correspondant à l'index du joueur actuel
            ImageView imageView = (ImageView) gridPane.getChildren().get(0);
            button.setGraphic(imageView);
            gridPane.getChildren().remove(imageView);
        }
    }





    // Méthode pour gérer la sélection du bouton
    private void handleSelection(List<Button> buttons, Button clickedButton, Timeline timeline1, Timeline timeline2, Label timerLabel1, Label timerLabel2, int[] remainingSeconds1, int[] remainingSeconds2, String chrono) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton) && placementisfinished) {
                selectedButton = clickedButton;// Sélectionner le bouton actuel
                selectButton(selectedButton);
            }
        }
        else {
            // Vérifier si le bouton actuel est voisin du bouton sélectionné
            if (isNeighbourButton(selectedButton, clickedButton) || (buttons.size() == 3 && placementisfinished)) {
                // Échanger les images des boutons
                if (clickedButton.getGraphic() == null) {
                    ImageView imageView = (ImageView) selectedButton.getGraphic();
                    clickedButton.setGraphic(imageView);
                    selectedButton.setGraphic(null);
                    buttons.remove(selectedButton);
                    buttons.add(clickedButton);
                    deselectButton(clickedButton);
                    change = true;

                    // Après le déplacement, réinitialiser la couleur des boutons formant une ligne complète
                    resetButtonColorsForMovedButton(selectedButton);

                    // Vérifier si une nouvelle ligne de trois pions a été formée après le déplacement
                    checkButtonCombinations();

                    if(!isRemovePieceMode) {
                        currentPlayer = currentPlayer == 1 ? 2 : 1;
                        if (currentPlayer==1){
                            ResetChrono(timeline2, timerLabel2, chrono, remainingSeconds2, timeline1);
                        } else {
                            ResetChrono(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);
                        }
                    }
                }
            }
            // Désélectionner le bouton sélectionné
            deselectButton(selectedButton);
            selectedButton = null;
            if (!change && buttons.contains(clickedButton)) {
                selectButton(clickedButton);
                selectedButton = clickedButton;
            }
            change = false;
        }
    }

    // Méthode pour réinitialiser la couleur des boutons de la ligne/colonne où le dernier bouton a été déplacé si les trois boutons de l'alignement sont verts
    private void resetButtonColorsForMovedButton(Button movedButton) {
        String nomButton = movedButton.getText();
        for (Map.Entry<String, RectangleConstructor> entry : rectangleMap.entrySet()) {
            String rectangleName = entry.getKey();
            RectangleConstructor rectangleConstructor = entry.getValue();

            // Itérer sur chaque caractère du nom du rectangle
            for (int i = 0; i < rectangleName.length(); i++) {
                // Vérifier si le caractère du nom du rectangle est égal au nom du bouton et si le rectangle est vert
                if (rectangleName.charAt(i) == nomButton.charAt(0) && rectangleConstructor.getStrokeColor() == GREEN) {
                    rectangleConstructor.setStrokeColor(TRANSPARENT);
                }
            }
        }
    }



    // Méthode pour vérifier si deux boutons sont voisins
    public static boolean isNeighbourButton(Button button1, Button button2) {
        String id1 = button1.getId();
        String id2 = button2.getId();

        // Vérifier si les boutons sont dans la liste des voisins
        for (String[] neighbours : neighbourList) {
            if ((neighbours[0].equals(id1) && neighbours[1].equals(id2)) ||
                    (neighbours[0].equals(id2) && neighbours[1].equals(id1))) {
                return true;
            }
        }
        return false;
    }


    // Méthode pour changer le style d'un bouton sélectionné
    public static void selectButton(Button button) {
        button.setStyle("-fx-background-color: yellow; -fx-background-radius: 50%");
        ImageView originalImageView = (ImageView) button.getGraphic();
        originalImageView.setScaleX(1.5);
        originalImageView.setScaleY(1.5);

    }

    // Méthode pour changer le style d'un bouton désélectionné
    public static void deselectButton(Button button) {
        button.setStyle("-fx-background-color: transparent"); // Bordure transparente
        if(button.getGraphic() != null){
            ImageView originalImageView = (ImageView) button.getGraphic();
            originalImageView.setScaleX(1.0);
            originalImageView.setScaleY(1.0);
        }
    }

    // Méthode pour vérifier les combinaisons de boutons et changer leur couleur si une combinaison est trouvée
    private boolean checkAndChangeButtonColor(String buttonId1, String buttonId2, String buttonId3) {
        Button button1 = getButtonById(buttonId1);
        Button button2 = getButtonById(buttonId2);
        Button button3 = getButtonById(buttonId3);

        String nomRectangle = buttonId1 + buttonId2 + buttonId3;
        RectangleConstructor rectangle = rectangleMap.get(nomRectangle);

        if (button1.getGraphic() != null && button2.getGraphic() != null && button3.getGraphic() != null) {
            if (((ImageView) button1.getGraphic()).getImage().getUrl().equals(((ImageView) button2.getGraphic()).getImage().getUrl()) &&
                    ((ImageView) button2.getGraphic()).getImage().getUrl().equals(((ImageView) button3.getGraphic()).getImage().getUrl())) {
                if (rectangle.getStrokeColor() == GREEN){
                    return false;
                }
                else {
                    rectangle.setStrokeColor(GREEN);
                    return true;
                }
            }
        }
        return false;
    }

    // Méthode pour récupérer un bouton à partir de son identifiant
    private Button getButtonById(String buttonId) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (button.getId().equals(buttonId)) {
                    return button;
                }
            }
        }
        return null;
    }

    // Méthode pour vérifier les combinaisons de boutons et changer leur couleur si une combinaison est trouvée
    private void checkButtonCombinations() {
        for (String[] combination : alignments) {
            if (checkAndChangeButtonColor(combination[0], combination[1], combination[2])) {
                isRemovePieceMode = true;
            }
        }
    }

    private boolean isNotlibre(Button b) {
        String nomButton = b.getText();
        int compteur = 0;
        for (Map.Entry<String, RectangleConstructor> entry : rectangleMap.entrySet()) {
            String rectangleName = entry.getKey();
            RectangleConstructor rectangleConstructor = entry.getValue();

            // Itérer sur chaque caractère du nom du rectangle
            for (int i = 0; i < rectangleName.length(); i++) {
                // Vérifier si le caractère du nom du rectangle est égal au nom du bouton et si le rectangle est vert
                if (rectangleName.charAt(i) == nomButton.charAt(0) && rectangleConstructor.getStrokeColor() == GREEN) {
                    compteur++;
                }
            }
        }
        // Vérifier si le compteur atteint 2 (ce qui signifie que les deux bords verts sont trouvés)
        return compteur > 0;
    }



    // Méthode pour vérifier si tous les pions d'un joueur ont au moins un voisin libre
    private boolean hasPlayerFreeNeighbours(List<Button> playerButtons) {
        for (Button button : playerButtons) {
            if (hasFreeNeighbour(button)) {
                return true;
            }
        }
        return false;
    }

    // Méthode pour vérifier si un bouton a au moins un voisin libre
    private boolean hasFreeNeighbour(Button button) {
        String id = button.getId();
        for (String[] neighbours : neighbourList) {
            if (neighbours[0].equals(id) || neighbours[1].equals(id)) {
                Button neighbourButton1 = getButtonById(neighbours[0]);
                Button neighbourButton2 = getButtonById(neighbours[1]);
                if (neighbourButton1.getGraphic() == null || neighbourButton2.getGraphic() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    // Méthode pour vérifier si la partie est terminée
    private boolean isGameFinished() {
        // Vérifier si un joueur a moins de 3 pions restants
        if (currentPlayer == 1) {
            if (buttonsJ1.size() < 3) {
                return true; // La partie est terminée si le joueur 1 a moins de 3 pions
            }
        } else {
            if (buttonsJ2.size() < 3) {
                return true; // La partie est terminée si le joueur 2 a moins de 3 pions
            }
        }

        // Vérifier si un joueur n'a plus de voisins libres
        if (currentPlayer == 1) {
            return !hasPlayerFreeNeighbours(buttonsJ1);
        } else {
            return !hasPlayerFreeNeighbours(buttonsJ2);
        }
    }

    private Timeline Chrono(Label timerLabel, int[] remainingSeconds){
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(isNoChrono){
                            remainingSeconds[0]++;
                        } else {
                            remainingSeconds[0]--;
                        }
                        timerLabel.setText(Integer.toString(remainingSeconds[0]));
                        if (remainingSeconds[0] <= 0) {
                            timeline.stop();
                            // Mettez ici votre code à exécuter une fois le minuteur terminé
                            System.out.println("Le temps est écoulé !");
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

    public void ResetChrono(Timeline timeline1, Label timerLabel, String chrono, int[] remainingSeconds, Timeline timeline2){
        int reset = Integer.parseInt(chrono);
        timeline1.stop();
        timerLabel.setText(chrono);
        remainingSeconds[0] = reset;
        timeline2.play();
    }

    private VBox createPauseMenu(Stage primaryStage, Timeline timeline1, Timeline timeline2) {
        VBox menu = new VBox(0.020833*screenHeight); // Conteneur pour les boutons du menu pause

        // Ajout des boutons nécessaires (Reprendre, Options, Quitter, etc.)
        Button resumeButton = new Button("Reprendre");
        Button regles = new Button("Règles");
        Button parametres = new Button("Paramètres");
        Button quitter = new Button("Quitter la Partie");

        resumeButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        regles.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        parametres.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        quitter.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));

        // Stylisation des boutons du menu pause
        resumeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        regles.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        parametres.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        quitter.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

        // Ajout d'une action pour le bouton "Reprendre" pour masquer le menu pause
        resumeButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            if (currentPlayer==1){
                timeline1.play();
            }
            else {
                timeline2.play();
            }
            menu.setVisible(false);
        });

        // Action du bouton "Règles" pour afficher les règles
        regles.setOnAction(e -> {
            SoundPlayer.soundPlay();
            ButtonPause.afficherRegles(this); // Passer la racine de la scène pour ajouter la StackPane
        });

        quitter.setOnAction(e -> {
            SoundPlayer.soundPlay();
            quitterMenu.setVisible(true);
        });

        quitterMenu = ButtonPause.boutonquitter(primaryStage);

        // Ajout des boutons au menu
        menu.getChildren().addAll(resumeButton, regles, parametres, quitter);

        // Stylisation du menu pause avec un arrière-plan semi-transparent
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7)");

        // Positionnement du menu pause au centre de l'écran
        menu.setAlignment(Pos.CENTER);

        return menu;
    }

    // Méthode pour créer et styliser les boutons
    private Button createStyledButton(String label) {
        Button button = new Button(label);
        button.setId(label);
        button.setPrefSize(0.03906*screenWidth, 0.069444*screenHeight); // Taille préférée des boutons
        button.setMinSize(0.03906*screenWidth, 0.069444*screenHeight);
        button.setMaxSize(0.03906*screenWidth, 0.069444*screenHeight);
        button.setStyle("-fx-background-color: transparent"); // Fond transparent
        button.setTextFill(Color.TRANSPARENT);
        return button;
    }
}