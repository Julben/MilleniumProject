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
import javafx.animation.Timeline;
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
    static VBox createVBoxWithImages(String imageLink, int count) {

        VBox vBox = new VBox(0.01389 * screenHeight); // Espacement vertical entre les images
        vBox.setAlignment(Pos.CENTER); // Centrer les images dans la VBox

        List<ImageView> imageViews = new ArrayList<>();

        Image image = new Image(imageLink);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(0.04 * Constant.screenWidth); // Largeur de l'image réduite
            imageView.setFitHeight(0.04 * Constant.screenWidth); // Hauteur de l'image réduite
            imageViews.add(imageView);
        }

        // Création d'une grille de 3x3 images
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

    // Méthode pour créer une VBox affichant le profil d'un joueur avec l'avatar à côté des labels
    static VBox createProfileBox(String avatarFileName, String playerName, String rank, Label timerlabel, boolean isPlayer1, boolean isIA) {

        VBox profileBox = new VBox(0); // Espacement vertical entre les éléments du profil
        profileBox.setAlignment(Pos.BOTTOM_CENTER); // Alignement au centre et en bas

        // Création d'une HBox pour contenir l'avatar, le nom et le rang
        HBox hbox = new HBox(0.01389*screenWidth); // Espacement horizontal entre les éléments
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        if (isPlayer1) {
            hbox.setAlignment(Pos.BOTTOM_LEFT);
        }

        // Ajout de l'avatar à la HBox
        ImageView avatarImageView = new ImageView(new Image(avatarFileName));

        if (isPlayer1) {
            // Pour le joueur 1, placer l'avatar à gauche et aligner les labels à droite
            hbox.getChildren().add(avatarImageView);
        }

        // Création d'une VBox pour contenir le nom et le rang
        VBox labelsVBox = new VBox(0); // Espacement vertical entre les labels

        if (isPlayer1) {
            labelsVBox.setAlignment(Pos.CENTER_LEFT); // Alignement à droite des labels pour le joueur 1
        } else {
            labelsVBox.setAlignment(Pos.CENTER_RIGHT); // Alignement à gauche des labels pour le joueur 2
            if(isIA){
                playerName = "Robot";
                rank = "IA";
            }
        }

        if(!isIA){
            labelsVBox.getChildren().add(timerlabel);
            labelsVBox.setPadding(new Insets(0, 0, 0.055556*screenHeight, 0));
        }

        // Ajout du nom du joueur
        Label nameLabel = new Label(playerName);
        nameLabel.setFont(Font.font("Cardo", 0.044444*screenHeight)); // Définition de la police et de la taille
        nameLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(nameLabel);

        // Ajout du rang du joueur
        Label rankLabel = new Label(rank);
        rankLabel.setFont(Font.font("Cardo", 0.0278*screenHeight)); // Définition de la police et de la taille
        rankLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(rankLabel);

        hbox.getChildren().add(labelsVBox); // Ajout de la VBox des labels à la HBox pour le joueur 2

        // Ajout de l'avatar à droite pour le joueur 2
        if (!isPlayer1 && !isIA) {
            hbox.getChildren().add(avatarImageView); // Ajout de l'avatar à droite pour le joueur 2
        } else if (isIA) {
            avatarImageView = new ImageView(new Image("Robot.png"));
            hbox.getChildren().add(avatarImageView);
        }

        avatarImageView.setFitWidth(0.1172*screenWidth); // Taille de l'avatar
        avatarImageView.setFitHeight(0.2083*screenHeight);

        // Ajout de la HBox au VBox principal
        profileBox.getChildren().add(hbox);

        return profileBox;
    }
}