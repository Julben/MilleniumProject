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
 * Cette classe contient des méthodes utilitaires pour la création de profils de joueur et de boîtes avec des images répétées.
 */
public class ProfilParty extends StackPane {

    /**
     * Crée une VBox contenant des images répétées.
     *
     * @param imageLink Le lien vers l'image à répéter.
     * @param count Le nombre d'images à afficher.
     * @return Une VBox contenant les images répétées.
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