package org.example.milleniumproject.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

/**
 * Cette classe permet la création du profil et des pions des joueurs durant la partie.
 */
public class ProfileParty extends StackPane {

    /**
     * Crée une VBox contenant les pions choisi par le joueur.
     *
     * @param imageLink L'image du pion du joueur.
     * @param count Le nombre de pions à afficher.
     * @return Une VBox contenant les pions du joueur.
     */
    public static VBox createVBoxWithImages(String imageLink, int count) {

        VBox vBox = new VBox(0.01389 * screenHeight);
        vBox.setAlignment(Pos.CENTER);

        List<ImageView> imageViews = new ArrayList<>();

        Image image = new Image(imageLink);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(0.04 * Constant.screenWidth);
            imageView.setFitHeight(0.04 * Constant.screenWidth);
            imageViews.add(imageView);
        }

        GridPane gridPane = new GridPane();
        gridPane.setHgap(0.01389 * screenHeight);
        gridPane.setVgap(0.0078 * screenWidth);
        int rowIndex = 0;
        int colIndex = 0;
        for (ImageView imageView : imageViews) {
            gridPane.add(imageView, colIndex, rowIndex);
            colIndex++;
            if (colIndex > 2) {
                colIndex = 0;
                rowIndex++;
            }
        }

        vBox.getChildren().add(gridPane);

        return vBox;
    }
    /**
     * Crée une VBox représentant le profil d'un joueur, contenant son avatar, son nom, son rang et un chronomètre si applicable.
     *
     * @param avatarFileName L'image de l'avatar du joueur.
     * @param playerName     Le nom du joueur.
     * @param rank           Le rang du joueur.
     * @param timerlabel     Le chronomètre pour le joueur.
     * @param isPlayer1      Indique si il faut utiliser la ProfilBox du profil .
     * @param isIA           Indique si il faut utiliser la ProfilBox de l'IA.
     * @return Une VBox contenant le profil du joueur ou l'IA.
     */
    public static VBox createProfileBox(String avatarFileName, String playerName, String rank, Label timerlabel, boolean isPlayer1, boolean isIA) {

        VBox profileBox = new VBox(0);
        profileBox.setAlignment(Pos.BOTTOM_CENTER);

        HBox hbox = new HBox(0.01389*screenWidth);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        if (isPlayer1) {
            hbox.setAlignment(Pos.BOTTOM_LEFT);
        }

        ImageView avatarImageView = new ImageView(new Image(avatarFileName));

        if (isPlayer1) {
            hbox.getChildren().add(avatarImageView);
        }

        VBox labelsVBox = new VBox(0);

        if (isPlayer1) {
            labelsVBox.setAlignment(Pos.CENTER_LEFT);
        } else {
            labelsVBox.setAlignment(Pos.CENTER_RIGHT);
            if(isIA){
                playerName = "Robot";
                rank = "IA";
            }
        }

        if(!isIA){
            labelsVBox.getChildren().add(timerlabel);
            labelsVBox.setPadding(new Insets(0, 0, 0.055556*screenHeight, 0));
        }

        Label nameLabel = new Label(playerName);
        nameLabel.setFont(Font.font("Cardo", 0.044444*screenHeight));
        nameLabel.setTextFill(Color.WHITE);
        labelsVBox.getChildren().add(nameLabel);

        Label rankLabel = new Label(rank);
        rankLabel.setFont(Font.font("Cardo", 0.0278*screenHeight));
        rankLabel.setTextFill(Color.WHITE);
        labelsVBox.getChildren().add(rankLabel);

        hbox.getChildren().add(labelsVBox);

        if (!isPlayer1 && !isIA) {
            hbox.getChildren().add(avatarImageView);
        } else if (isIA) {
            avatarImageView = new ImageView(new Image("Robot.png"));
            hbox.getChildren().add(avatarImageView);
        }

        avatarImageView.setFitWidth(0.1172*screenWidth);
        avatarImageView.setFitHeight(0.2083*screenHeight);

        profileBox.getChildren().add(hbox);

        return profileBox;
    }
}