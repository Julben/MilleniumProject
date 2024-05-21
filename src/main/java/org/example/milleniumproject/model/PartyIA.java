package org.example.milleniumproject.model;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import static org.example.milleniumproject.model.ButtonSelector.*;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.TRANSPARENT;
import static org.example.milleniumproject.model.EndParty.endGame;
import static org.example.milleniumproject.model.EndParty.displayEndGame;
import static org.example.milleniumproject.model.AImethod.disableMouseInteractions;
import static org.example.milleniumproject.model.Movement.*;
import static org.example.milleniumproject.model.Placement.*;
import static org.example.milleniumproject.model.RemovePawn.*;
import javafx.scene.control.Button;
import org.example.milleniumproject.presentation.BackGround;
import org.example.milleniumproject.presentation.ButtonTransitionHandler;
import org.example.milleniumproject.presentation.RectangleConstructor;
import org.example.milleniumproject.view.Campaign;
import org.example.milleniumproject.view.PreParty;
import org.example.milleniumproject.view.PrePartyIA;

import java.util.Random;

public class PartyIA extends StackPane {
    private LoadParty chargerPartie = new LoadParty();
    static int currentPlayer = 1;
    private Campaign campagne;
    private VBox leftVBox;
    static VBox rightVBox;
    static int turns = 0;
    static boolean isNoChrono = false;
    private ToggleGroup toggleGroup3;
    private HBox hbox3;
    private ToggleGroup toggleGroup2;
    private HBox hbox2;
    private int remainingSeconds = 30;
    static Timeline timeline;
    private int difficulty;
    private int selectedIndexchrono;
    private int selectedIndex;
    private Button selectedButton = null;
    static List<Button> buttonsJ1 = new ArrayList<>();
    static List<Button> buttonsJ2 = new ArrayList<>();
    static List<String[]> finalValidAlignments = new ArrayList<>();
    static List<String[]> finalAlignments = new ArrayList<>();
    static List<String[]> finalValidAlignmentsJ1 = new ArrayList<>();
    static GridPane gridPane;
    static boolean placementisfinished = false;
    static boolean isRemovePieceMode = false;
    static boolean boutonlibre = false;
    private VBox pauseMenu;
    private VBox quitterMenu;
    private boolean selected = false;
    private boolean change = false;
    private static boolean FromSave=false;
    String[] buttonLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"};

    static final List<String[]> neighbourList = Arrays.asList(
            new String[]{"A", "B"}, new String[]{"A", "J"}, new String[]{"B", "C"}, new String[]{"B", "E"}, new String[]{"C", "O"}, new String[]{"D", "E"}, new String[]{"D", "K"}, new String[]{"E", "F"},
            new String[]{"E", "H"}, new String[]{"F", "N"}, new String[]{"G", "H"}, new String[]{"G", "L"}, new String[]{"H", "I"}, new String[]{"I", "M"}, new String[]{"J", "K"}, new String[]{"J", "V"},
            new String[]{"K", "L"}, new String[]{"K", "S"}, new String[]{"L", "P"}, new String[]{"M", "R"}, new String[]{"M", "N"}, new String[]{"N", "O"}, new String[]{"N", "U"}, new String[]{"O", "X"},
            new String[]{"P", "Q"}, new String[]{"Q", "R"}, new String[]{"Q", "T"}, new String[]{"S", "T"}, new String[]{"T", "U"}, new String[]{"T", "W"}, new String[]{"V", "W"}, new String[]{"W", "X"}
    );
    static final List<String[]> alignments = Arrays.asList(
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

    static Map<String, RectangleConstructor> rectangleMap = new HashMap<>();

    public PartyIA(){

    }
    public PartyIA (Stage primaryStage,int selectedIndex,int selectedIndexchrono, int difficulty,String avatar1,String rank1,String playerName1,int turns,int currentPlayer,List<Button> buttonSave, List<Button> buttonsJ1, List<Button> buttonsJ2){
        rectangleMap.put("ABC", abc); rectangleMap.put("DEF", def); rectangleMap.put("GHI", ghi); rectangleMap.put("JKL", jkl);
        rectangleMap.put("MNO", mno); rectangleMap.put("PQR", pqr); rectangleMap.put("STU", stu); rectangleMap.put("VWX", vwx);
        rectangleMap.put("AJV", ajv); rectangleMap.put("DKS", dks); rectangleMap.put("GLP", glp); rectangleMap.put("BEH", beh);
        rectangleMap.put("QTW", qtw); rectangleMap.put("IMR", imr); rectangleMap.put("FNU", fnu); rectangleMap.put("COX", cox);

        this.selectedIndex = selectedIndex;
        this.selectedIndexchrono = selectedIndexchrono;
        this.difficulty = difficulty;
        this.turns = turns;
        this.currentPlayer = currentPlayer;
        this.buttonsJ1 = buttonsJ1;
        this.buttonsJ2 = buttonsJ2;
        //isNoChrono=true;
       // this.isNoChrono = isNoChrono;

        String backgroundImage = "";
        if (selectedIndex == 0) {
            backgroundImage = "src/main/resources/FENABOO.png";
        } else if (selectedIndex == 1) {
            backgroundImage = "src/main/resources/FECORUSCANT.png";
        } else if (selectedIndex == 2) {
            backgroundImage = "src/main/resources/FEMUSTAPHAR.png";
        }
        BackGround ground = new BackGround(backgroundImage);
        setBackground(ground.getCustomBackground());

        String chrono;

        if (selectedIndexchrono == 0){
            chrono = "30";
        } else if (selectedIndexchrono == 1){
            chrono = "60";
        } else{
            chrono="0";
            isNoChrono=true;
        }

        FromSave=true;

        int[] remainingSeconds1 = {Integer.parseInt(chrono)};
        int[] remainingSeconds2 = {Integer.parseInt(chrono)};
        Label timerLabel1 = new Label(chrono);
        Label timerLabel2 = new Label(chrono);
        timerLabel1.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        timerLabel2.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        Timeline timeline1 = timer(timerLabel1, remainingSeconds1, primaryStage,this);
        Timeline timeline2 = timer(timerLabel2, remainingSeconds2, primaryStage,this);

        gridPane = new GridPane();
        gridPane.setHgap(0.0171875*screenWidth);
        gridPane.setVgap(0.0305556*screenHeight);
        gridPane.setAlignment(Pos.CENTER);

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

        leftVBox = ProfileParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfileParty.createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * Constant.screenWidth);
        hBox.getChildren().addAll(leftVBox, rightVBox);
        hBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(leftVBox, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightVBox, Pos.CENTER_RIGHT);

        String avatarFileName1 = avatar1.substring(avatar1.lastIndexOf('/') + 1);
        String playerName2 = "Robot";
        String rank2 = "IA";
        String avatarFileName2 ="Robot.png";


        VBox profileBox1 = ProfileParty.createProfileBox(avatarFileName1, playerName1, rank1, timerLabel1, true, false);
        VBox profileBox2 = ProfileParty.createProfileBox(avatarFileName2, playerName2, rank2, timerLabel2, false, true);
        setMargin(profileBox1, new Insets(0, 0, 0.020833*screenHeight, 0.015625*screenWidth));
        setMargin(profileBox2, new Insets(0, 0.015625*screenWidth, 0.020833*screenHeight, 0));

        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

        Image pauseImage = new Image("pause.png");
        ImageView imageView = new ImageView(pauseImage);
        imageView.setFitWidth(0.025*screenWidth);
        imageView.setFitHeight(0.04444*screenHeight);

        Button pauseButton = new Button();
        pauseButton.setGraphic(imageView);

        pauseButton.setStyle("-fx-background-color: transparent");

        pauseButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            timeline1.pause();
            timeline2.pause();
            pauseMenu.setVisible(true);
        });

        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        setMargin(pauseButton, new Insets(0.0138889*screenHeight, 0.0078*screenWidth, 0, 0));

        pauseMenu = createPauseMenu(primaryStage, timeline1, timeline2);
        pauseMenu.setVisible(false);
        quitterMenu.setVisible(false);

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = buttonSave.get(i);
            button.setPrefSize(0.03906 * Constant.screenWidth, 0.069444 * Constant.screenHeight);
            button.setMinSize(0.03906 * Constant.screenWidth, 0.069444 * Constant.screenHeight);
            button.setMaxSize(0.03906 * Constant.screenWidth, 0.069444 * Constant.screenHeight);
            button.setStyle("-fx-background-color: transparent");
            button.setTextFill(Color.TRANSPARENT);

            gridPane.add(button, colIndices[i], rowIndices[i]);
        }

        getChildren().addAll(hBox, profileBox1, profileBox2, ABC, VWX, AJV, COX, DEF, STU, DKS, FNU, GHI, PQR, GLP, IMR, BEH, JKL, MNO, QTW, gridPane, pauseMenu, quitterMenu, pauseButton);



        timeline1.play();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> {
                    SoundPlayer.soundPlay();
                    handleButtonClick(button, gridPane, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono,primaryStage, difficulty,buttonsJ1,buttonsJ2);
                });
            }
        }


    }
    public PartyIA(Stage primaryStage,int currentRound) {

        rectangleMap.put("ABC", abc); rectangleMap.put("DEF", def); rectangleMap.put("GHI", ghi); rectangleMap.put("JKL", jkl);
        rectangleMap.put("MNO", mno); rectangleMap.put("PQR", pqr); rectangleMap.put("STU", stu); rectangleMap.put("VWX", vwx);
        rectangleMap.put("AJV", ajv); rectangleMap.put("DKS", dks); rectangleMap.put("GLP", glp); rectangleMap.put("BEH", beh);
        rectangleMap.put("QTW", qtw); rectangleMap.put("IMR", imr); rectangleMap.put("FNU", fnu); rectangleMap.put("COX", cox);

        if (currentRound == 0){
            selectedIndex = 0;
        }else if (currentRound == 1){
            selectedIndex = 1;
        }else {
            selectedIndex = 2;
        }

        String backgroundImage = "";
        if (selectedIndex == 0) {
            backgroundImage = "src/main/resources/FENABOO.png";
        } else if (selectedIndex == 1) {
            backgroundImage = "src/main/resources/FECORUSCANT.png";
        } else if (selectedIndex == 2) {
            backgroundImage = "src/main/resources/FEMUSTAPHAR.png";
        }
        BackGround ground = new BackGround(backgroundImage);
        setBackground(ground.getCustomBackground());

        String chrono;

        if(currentRound == 0){
            selectedIndexchrono = 1;
        }else if (currentRound == 1){
            selectedIndexchrono = 1;
        }else{
            selectedIndexchrono = 0;
        }

        if (selectedIndexchrono == 0){
            chrono = "30";
        } else{
            chrono = "60";
        }

        if (currentRound == 0){
            difficulty=0;
        }else{
            difficulty=1;
        }

        int[] remainingSeconds1 = {Integer.parseInt(chrono)};
        int[] remainingSeconds2 = {Integer.parseInt(chrono)};
        Label timerLabel1 = new Label(chrono);
        Label timerLabel2 = new Label(chrono);
        timerLabel1.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        timerLabel2.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        Timeline timeline1 = timer(timerLabel1, remainingSeconds1, primaryStage,this);
        Timeline timeline2 = timer(timerLabel2, remainingSeconds2, primaryStage,this);

        gridPane = new GridPane();
        gridPane.setHgap(0.0171875*screenWidth);
        gridPane.setVgap(0.0305556*screenHeight);
        gridPane.setAlignment(Pos.CENTER);

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

        leftVBox = ProfileParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfileParty.createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * Constant.screenWidth);
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

        VBox profileBox1 = ProfileParty.createProfileBox(avatarFileName1, playerName1, rank1, timerLabel1, true, false);
        VBox profileBox2 = ProfileParty.createProfileBox(avatarFileName2, playerName2, rank2, timerLabel2, false, true);
        setMargin(profileBox1, new Insets(0, 0, 0.020833*screenHeight, 0.015625*screenWidth));
        setMargin(profileBox2, new Insets(0, 0.015625*screenWidth, 0.020833*screenHeight, 0));

        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

        Image pauseImage = new Image("pause.png");
        ImageView imageView = new ImageView(pauseImage);
        imageView.setFitWidth(0.025*screenWidth);
        imageView.setFitHeight(0.04444*screenHeight);

        Button pauseButton = new Button();
        pauseButton.setGraphic(imageView);

        pauseButton.setStyle("-fx-background-color: transparent");

        pauseButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            timeline1.pause();
            timeline2.pause();
            pauseMenu.setVisible(true);
        });

        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        setMargin(pauseButton, new Insets(0.0138889*screenHeight, 0.0078*screenWidth, 0, 0));

        pauseMenu = createPauseMenu(primaryStage, timeline1, timeline2);
        pauseMenu.setVisible(false);
        quitterMenu.setVisible(false);

        getChildren().addAll(hBox, profileBox1, profileBox2, ABC, VWX, AJV, COX, DEF, STU, DKS, FNU, GHI, PQR, GLP, IMR, BEH, JKL, MNO, QTW, gridPane, pauseMenu, quitterMenu, pauseButton);

        timeline1.play();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> {
                    SoundPlayer.soundPlay();
                    handleButtonClick(button, gridPane, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono,primaryStage, 1,buttonsJ1,buttonsJ2);
                });
            }
        }
    }

    public PartyIA(Stage primaryStage, ToggleGroup toggleGroup3, HBox hbox3, ToggleGroup toggleGroup2, HBox hbox2, ToggleGroup toggleGroup1, HBox hbox1) {
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
        BackGround ground = new BackGround(backgroundImage);
        setBackground(ground.getCustomBackground());

        this.toggleGroup2 = toggleGroup2;
        this.hbox2 = hbox2;

        int selectedIndexchrono = PreParty.getSelectedIndexchrono(toggleGroup2, hbox2);

        difficulty = PrePartyIA.getSelectedIndexDifficulty(toggleGroup1, hbox1);

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
        Timeline timeline1 = timer(timerLabel1, remainingSeconds1, primaryStage, this);
        Timeline timeline2 = timer(timerLabel2, remainingSeconds2, primaryStage, this);

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

        leftVBox = ProfileParty.createVBoxWithImages(vaisseau1, 9);
        rightVBox = ProfileParty.createVBoxWithImages(vaisseau2, 9);

        HBox hBox = new HBox(0.6 * Constant.screenWidth);
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

        VBox profileBox1 = ProfileParty.createProfileBox(avatarFileName1, playerName1, rank1, timerLabel1, true, false);
        VBox profileBox2 = ProfileParty.createProfileBox(avatarFileName2, playerName2, rank2, timerLabel2, false, true);
        setMargin(profileBox1, new Insets(0, 0, 0.020833*screenHeight, 0.015625*screenWidth));
        setMargin(profileBox2, new Insets(0, 0.015625*screenWidth, 0.020833*screenHeight, 0));

        setAlignment(profileBox1, Pos.BOTTOM_LEFT);
        setAlignment(profileBox2, Pos.BOTTOM_RIGHT);

        Image pauseImage = new Image("pause.png");
        ImageView imageView = new ImageView(pauseImage);
        imageView.setFitWidth(0.025*screenWidth);
        imageView.setFitHeight(0.04444*screenHeight);

        Button pauseButton = new Button();
        pauseButton.setGraphic(imageView);

        pauseButton.setStyle("-fx-background-color: transparent");

        pauseButton.setOnAction(e -> {
            SoundPlayer.soundPlay();
            timeline1.pause();
            timeline2.pause();
            pauseMenu.setVisible(true);
        });

        StackPane.setAlignment(pauseButton, Pos.TOP_RIGHT);
        setMargin(pauseButton, new Insets(0.0138889*screenHeight, 0.0078*screenWidth, 0, 0));

        pauseMenu = createPauseMenu(primaryStage, timeline1, timeline2);
        pauseMenu.setVisible(false);
        quitterMenu.setVisible(false);

        getChildren().addAll(hBox, profileBox1, profileBox2, ABC, VWX, AJV, COX, DEF, STU, DKS, FNU, GHI, PQR, GLP, IMR, BEH, JKL, MNO, QTW, gridPane, pauseMenu, quitterMenu, pauseButton);

        timeline1.play();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(e -> {
                    SoundPlayer.soundPlay();
                    handleButtonClick(button, gridPane, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono, primaryStage, difficulty,buttonsJ1,buttonsJ2);
                });
            }
        }
    }

    private void handleButtonClick(Button button, GridPane gridpane, Timeline timeline1, Timeline timeline2, Label timerLabel1, Label timerLabel2, int[] remainingSeconds1, int[] remainingSeconds2, String chrono, Stage primaryStage, int diffliculty,List<Button> buttonsJ1, List<Button> buttonsJ2) {
        if (FromSave==true){
            turns= 18;
        }
        disableMouseInteractions(gridpane, false);

        if (isRemovePieceMode) {

            if(buttonsJ2.contains(button) && !isNotlibre(button)){
                easyRemove(this, button, timeline2, timerLabel2, chrono, remainingSeconds2, timeline1,primaryStage);
                resetTimer(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);

                if(turns < 18){

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        if(diffliculty == 0){
                            Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                            makeEasyPlacement(this, randomFreeButtonJ1, gridpane, timeline1, timeline2, remainingSeconds2, timerLabel1, timerLabel2, chrono, primaryStage, diffliculty);
                        } else if(diffliculty == 1){
                            Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                            makeHardPlacement(this, randomFreeButtonJ1, gridpane, timeline1, timeline2, remainingSeconds2, timerLabel1, timerLabel2, chrono, primaryStage, diffliculty);
                        }
                    });
                    pause.play();
                }
                else{
                    if(diffliculty == 0){
                        Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                        easyMovement(this, randomFreeButtonJ1, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, diffliculty);
                    } else if(diffliculty == 1){
                        Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                        hardMovement( this, randomFreeButtonJ1, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, gridpane, diffliculty);
                    }
                }

                if(placementisfinished){
                    if (isGameFinished()) {
                        endGame(this,timeline1, timeline2, primaryStage);
                    }
                }
            }
        }
        else {
            if (button.getGraphic() == null && turns < 18) {
                if (currentPlayer == 1) {
                    placePlayerImage(button, leftVBox, timeline1, timeline2, remainingSeconds2, timerLabel2, chrono);
                    buttonsJ1.add(button);
                    checkButtonCombinations();
                    if(isRemovePieceMode){
                        currentPlayer = 1;
                    }
                    else {
                        currentPlayer = 2;
                        disableMouseInteractions(gridpane, true);
                        resetTimer(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);
                        timerLabel1.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");

                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(event -> {
                            if(diffliculty == 0){
                                Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                                makeEasyPlacement(this, randomFreeButtonJ1, gridpane, timeline1, timeline2, remainingSeconds2, timerLabel1, timerLabel2, chrono, primaryStage, diffliculty);
                            } else if (diffliculty == 1) {
                                Button randomFreeButtonJ1 = getRandomFreeButtonJ1();
                                makeHardPlacement(this, randomFreeButtonJ1, gridpane, timeline1, timeline2, remainingSeconds2, timerLabel1, timerLabel2, chrono, primaryStage, diffliculty);

                            }
                            disableMouseInteractions(gridpane, false);
                        });
                        pause.play();
                    }
                    turns++;
                }
            }
            else if ( turns >= 18) {
                placementisfinished = true;
                if (currentPlayer == 1 && (buttonsJ1.contains(button) || button.getGraphic() == null)) {
                    handleSelection(this, button, buttonsJ1, button, timeline1, timeline2, timerLabel1, timerLabel2, remainingSeconds1, remainingSeconds2, chrono, primaryStage,gridpane, diffliculty);
                }
                if (isGameFinished()) {
                    endGame(this,timeline1, timeline2, primaryStage);
                }
            }
        }
    }

    static void placePlayerImage(Button button, VBox playerVBox, Timeline timeline1, Timeline timeline2, int[] remainingSeconds2, Label timerLabel2, String chrono) {
        GridPane gridPane = (GridPane) playerVBox.getChildren().get(0);

        if (!gridPane.getChildren().isEmpty()) {
            ImageView imageView = (ImageView) gridPane.getChildren().get(0);
            button.setGraphic(imageView);
            SoundPlayer.soundPlay();
            gridPane.getChildren().remove(imageView);
        }
    }

    static Button getRandomFreeButtonJ1() {
        List<Button> freeButtonsJ1 = new ArrayList<>();
        for (Button button : buttonsJ1) {
            if (!isNotlibre(button)) {
                freeButtonsJ1.add(button);
            }
        }

        if (freeButtonsJ1.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(buttonsJ1.size());
            return buttonsJ1.get(index);
        } else {
            Random random = new Random();
            int index = random.nextInt(freeButtonsJ1.size());
            return freeButtonsJ1.get(index);
        }
    }


    static Button getEmptyButton(GridPane gridPane) {
        Random random = new Random();
        Button emptyButton = null;
        do {
            int index = random.nextInt(gridPane.getChildren().size());
            Node node = gridPane.getChildren().get(index);
            if (node instanceof Button) {
                emptyButton = (Button) node;
                if (emptyButton.getGraphic() != null) {
                    emptyButton = null;
                }
            }
        } while (emptyButton == null);
        return emptyButton;
    }

    static Button getButtonById(String buttonId) {
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

    private void handleSelection(StackPane root, Button button, List<Button> buttons, Button clickedButton, Timeline timeline1, Timeline timeline2, Label timerLabel1, Label timerLabel2, int[] remainingSeconds1, int[] remainingSeconds2, String chrono, Stage primaryStage,GridPane gridpane, int difficulty) {
        if (selectedButton == null) {
            if (buttons.contains(clickedButton) && placementisfinished) {
                selectedButton = clickedButton;// Sélectionner le bouton actuel
                selectButton(selectedButton);
                selected = true;
            }
        }
        else {
            if (isNeighbourButton(selectedButton, clickedButton) || (buttons.size() == 3 && placementisfinished)) {
                if (clickedButton.getGraphic() == null) {

                    if(VideoData.isAnimation()){
                        Button finalSelectedButton = selectedButton;
                        ButtonTransitionHandler.performTransition(selectedButton, clickedButton, buttonsJ1, () -> {
                            deselectButton(clickedButton);

                            resetButtonColorsForMovedButton(finalSelectedButton);
                            checkButtonCombinations();

                            if(!isRemovePieceMode) {
                                currentPlayer = 2;
                                resetTimer(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);
                                if(difficulty == 0){
                                    easyMovement(this, button, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
                                } else if (difficulty == 1) {
                                    hardMovement( this, button, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, gridpane, difficulty);
                                }
                            }
                            change = true;
                        });
                    }
                    else {
                        ImageView imageView = (ImageView) selectedButton.getGraphic();
                        clickedButton.setGraphic(imageView);
                        selectedButton.setGraphic(null);
                        buttons.remove(selectedButton);
                        buttons.add(clickedButton);
                        deselectButton(clickedButton);

                        resetButtonColorsForMovedButton(selectedButton);
                        checkButtonCombinations();

                        if(!isRemovePieceMode) {
                            currentPlayer = 2;
                            resetTimer(timeline1, timerLabel1, chrono, remainingSeconds1, timeline2);
                            if(difficulty == 0){
                                easyMovement(this, button, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, difficulty);
                            } else if (difficulty == 1) {
                                hardMovement( this, button, timeline2, timerLabel2, timerLabel1, chrono, remainingSeconds2, timeline1, primaryStage, gridpane, difficulty);
                            }
                        }
                        change = true;
                    }
                }
            }
            deselectButton(selectedButton);
            selectedButton = null;
            if (!change && buttons.contains(clickedButton)) {
                selectButton(clickedButton);
                selected = true;
                selectedButton = clickedButton;
            }
            change = false;
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



    static void resetButtonColorsForMovedButton(Button movedButton) {
        String nomButton = movedButton.getText();
        for (Map.Entry<String, RectangleConstructor> entry : rectangleMap.entrySet()) {
            String rectangleName = entry.getKey();
            RectangleConstructor rectangleConstructor = entry.getValue();

            for (int i = 0; i < rectangleName.length(); i++) {
                if (rectangleName.charAt(i) == nomButton.charAt(0) && rectangleConstructor.getStrokeColor() == GREEN) {
                    rectangleConstructor.setStrokeColor(TRANSPARENT);
                }
            }
        }
    }

    static boolean checkAndChangeButtonColor(String buttonId1, String buttonId2, String buttonId3) {
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

    static void checkButtonCombinations() {
        int compteur = 0;
        for (String[] combination : alignments) {
            if (checkAndChangeButtonColor(combination[0], combination[1], combination[2])) {
                compteur++;
            }
        }
        if (compteur >= 1) {
            isRemovePieceMode = true;
        }
    }

    static boolean isNotlibre(Button b) {
        String nomButton = b.getText();
        int compteur = 0;
        for (Map.Entry<String, RectangleConstructor> entry : rectangleMap.entrySet()) {
            String rectangleName = entry.getKey();
            RectangleConstructor rectangleConstructor = entry.getValue();

            for (int i = 0; i < rectangleName.length(); i++) {
                if (rectangleName.charAt(i) == nomButton.charAt(0) && rectangleConstructor.getStrokeColor() == GREEN) {
                    compteur++;
                }
            }
        }
        return compteur > 0;
    }

    static boolean isNeighbourButton(Button button1, Button button2) {
        String id1 = button1.getId();
        String id2 = button2.getId();

        for (String[] neighbours : neighbourList) {
            if ((neighbours[0].equals(id1) && neighbours[1].equals(id2)) ||
                    (neighbours[0].equals(id2) && neighbours[1].equals(id1))) {
                return true;
            }
        }
        return false;
    }

    static boolean hasPlayerFreeNeighbours(List<Button> playerButtons) {
        for (Button button : playerButtons) {
            if (hasFreeNeighbour(button)) {
                return true;
            }
        }
        return false;
    }

    static boolean hasFreeNeighbour(Button button) {
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

    static boolean isGameFinished() {
        if(buttonsJ1.size()<3 || buttonsJ2.size()<3) {
            return true;
        }

        if (currentPlayer == 1 && (buttonsJ1.size() != 3 || buttonsJ2.size() != 3)) {
            return !hasPlayerFreeNeighbours(buttonsJ1);
        } else if (currentPlayer == 2 && (buttonsJ1.size() != 3 || buttonsJ2.size() != 3)) {
            return !hasPlayerFreeNeighbours(buttonsJ2);
        }
        return false;
    }

    static Timeline timer(Label timerLabel, int[] remainingSeconds, Stage primaryStage, StackPane root ) {
        final Timeline[] timeline = new Timeline[1];

        timeline[0] = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (FromSave ==true){
                            isNoChrono=true;
                        }
                        if(isNoChrono){
                            remainingSeconds[0]++;
                        } else {
                            remainingSeconds[0]--;
                            if (remainingSeconds[0] <= 5) {
                                SoundPlayer.soundPlay();
                                timerLabel.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: red;");
                            }
                        }
                        timerLabel.setText(Integer.toString(remainingSeconds[0]));
                        if (remainingSeconds[0] <= 0) {
                            timeline[0].stop();
                            displayEndGame(root,primaryStage);
                        }
                    }
                })
        );
        timeline[0].setCycleCount(Timeline.INDEFINITE);
        return timeline[0];
    }

    static void resetTimer(Timeline timeline1, Label timerLabel, String chrono, int[] remainingSeconds, Timeline timeline2) {
        int reset = Integer.parseInt(chrono);
        timeline1.stop();
        timerLabel.setText(chrono);
        timerLabel.setStyle("-fx-font-family: 'Cardo'; -fx-font-size: 48; -fx-text-fill: white;");
        remainingSeconds[0] = reset;
        timeline2.play();
    }

    private VBox createPauseMenu(Stage primaryStage, Timeline timeline1, Timeline timeline2) {
        VBox menu = new VBox(0.020833*screenHeight);

        Button resumeButton = new Button("Reprendre");
        Button regles = new Button("Règles");
        Button parametres = new Button("Paramètres");
        Button quitter = new Button("Quitter la Partie");

        resumeButton.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        regles.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        parametres.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));
        quitter.setFont(Font.font("Cardo", FontWeight.BOLD, 0.027778*screenHeight));

        resumeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        regles.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        parametres.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        quitter.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");

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

        parametres.setOnAction(e -> {
            SoundPlayer.soundPlay();
            ButtonPause.settings(this);
        });

        regles.setOnAction(e -> {
            SoundPlayer.soundPlay();
            ButtonPause.displayRules(this);
        });

        quitter.setOnAction(e -> {
            SoundPlayer.soundPlay();
            quitterMenu = quitterMenuChoose(primaryStage,gridPane,currentPlayer,turns,selectedIndexchrono,selectedIndex,difficulty);
            quitterMenu.setVisible(true);
        });

        quitterMenu = quitterMenuChoose(primaryStage,gridPane,currentPlayer,turns,selectedIndexchrono,selectedIndex,difficulty);

        menu.getChildren().addAll(resumeButton, regles, parametres, quitter,quitterMenu);

        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7)");

        menu.setAlignment(Pos.CENTER);

        return menu;
    }

    public VBox quitterMenuChoose(Stage primaryStage,GridPane gridPane, int currentPlayer , int turns, int chrono , int bg, int difficulty) {
        VBox newQuitterMenu;

        if (turns <= 17) {
            newQuitterMenu = ButtonPause.quitButton(primaryStage);
        } else {
            newQuitterMenu = ButtonPause.quitButtonSave(primaryStage,gridPane,chrono,bg,true,difficulty);
        }

        if (quitterMenu != null) {
            getChildren().remove(quitterMenu);
        }
        quitterMenu = newQuitterMenu;
        getChildren().add(quitterMenu);

        return quitterMenu;
    }

    public void LoadParty(Stage primaryStage, String nameFile){
        List<Object> allInfo = chargerPartie.loadPartyFromFile(nameFile);
        String avatar1 = (String) allInfo.get(0);
        String rank1 = (String) allInfo.get(2);
        String ship1 = (String) allInfo.get(4);
        String name1 = (String) allInfo.get(6);
        int currentPlayer = (int) allInfo.get(8);
        int turns =18;
        int selectedIndexchrono =2;
        int selectedIndex = (int) allInfo.get(11);
        List<String> pictureButton = (List<String>) allInfo.get(12);
        int difficulty = (int) allInfo.get(14);
        List<Button> buttonSave = new ArrayList<>();
        isNoChrono = true;

        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = createStyledButton(buttonLabels[i]);
            if (pictureButton.get(i)!="null") {
                Image image = new Image(pictureButton.get(i));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(0.04*screenWidth);
                imageView.setFitHeight(0.07104*screenHeight);
                button.setGraphic(imageView);
                buttonSave.add(button);
                if(ship1.equals(pictureButton.get(i))){
                    buttonsJ1.add(button);
                }else{
                    buttonsJ2.add(button);
                }
            } else {
                button.setGraphic(null);
                buttonSave.add(button);
            }
        }

        PartyIA partyIA = new PartyIA(primaryStage,selectedIndex,selectedIndexchrono, difficulty,avatar1,rank1,name1,turns,currentPlayer,buttonSave,buttonsJ1,buttonsJ2);
        primaryStage.getScene().setRoot(partyIA);
    }
}