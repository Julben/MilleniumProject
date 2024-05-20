package org.example.milleniumproject.model;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.milleniumproject.view.Menu;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;
import static org.example.milleniumproject.model.PartyIA.*;
import static org.example.milleniumproject.model.PartyIA.currentPlayer;

public class EndParty extends StackPane {

    static void afficherFinPartie(StackPane root, Stage primaryStage){
        StackPane reglesPane = new StackPane();
        reglesPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");

        List<String> stringList;
        if (currentPlayer == 2) {
            stringList = Arrays.asList("FinParty1.png", "FinParty2.png", "FinParty3.png", "FinParty4.png", "FinParty5.png", "FinParty6.png");
        } else {
            stringList = Arrays.asList("FinParty7.png", "FinParty8.png", "FinParty9.png", "FinParty10.png", "FinParty11.png", "FinParty12.png");
        }
        Random rand = new Random();
        int index = rand.nextInt(stringList.size());
        String randomString = stringList.get(index);

        Image image = new Image(randomString);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(screenWidth);
        imageView.setFitHeight(screenHeight);

        Image quitterImage = new Image("BoutonQuitter.png");
        ImageView imageView1 = new ImageView(quitterImage);
        imageView1.setFitWidth(0.03125*screenWidth);
        imageView1.setFitHeight(0.05556*screenHeight);

        Button quitterButton = new Button();
        quitterButton.setGraphic(imageView1);

        quitterButton.setStyle("-fx-background-color: transparent");

        quitterButton.setOnAction(e -> {
            turns = 0;
            isNoChrono = false;
            currentPlayer = 1;
            placementisfinished = false;
            isRemovePieceMode = false;
            buttonsJ1.clear();
            buttonsJ2.clear();
            SoundPlayer.soundPlay();
            MusicPlayer.musicPlay("src/main/resources/MusicMenu.mp3");
            Menu menu = new Menu();
            menu.afficherMenu(primaryStage);
        });

        StackPane.setAlignment(quitterButton, Pos.TOP_RIGHT);
        setMargin(quitterButton, new Insets(0.011719*screenHeight, 5, 0, 0));

        MusicPlayer.musicPlay("src/main/resources/MusicEndParty.mp3");

        reglesPane.getChildren().addAll(imageView, quitterButton);

        StackPane.setAlignment(reglesPane, Pos.CENTER);

        root.getChildren().add(reglesPane);
    }

    static void FinPartie(StackPane root, Timeline timeline1, Timeline timeline2, Stage primaryStage){
        timeline1.stop();
        timeline2.stop();
        afficherFinPartie(root, primaryStage);
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
}