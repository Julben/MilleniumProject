package org.example.milleniumproject.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import static org.example.milleniumproject.model.Constant.screenHeight;
import static org.example.milleniumproject.model.Constant.screenWidth;

public class ProfilParty {

    // Méthode pour créer une VBox avec des images répétées
    static VBox createVBoxWithImages(String imageLink, int count) {

        VBox vBox = new VBox(0.01389*screenHeight); // Espacement vertical entre les images
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
        gridPane.setHgap(0.01389*screenHeight);
        gridPane.setVgap(0.0078*screenWidth);
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
    static VBox createProfileBox(String avatarFileName, String playerName, String rank, boolean isPlayer1, boolean suishumain) {
        VBox profileBox = new VBox(0); // Espacement vertical entre les éléments du profil
        profileBox.setAlignment(Pos.BOTTOM_CENTER); // Alignement au centre et en bas

        // Création d'une HBox pour contenir l'avatar, le nom et le rang
        HBox hbox = new HBox(0.0078 * screenWidth); // Espacement horizontal entre les éléments
        hbox.setAlignment(Pos.CENTER); // Centrage horizontal des éléments

        // Création d'une ImageView pour l'avatar
        ImageView avatarImageView;
        if (isPlayer1 || suishumain) {
            // Si c'est le joueur 1 ou si on utilise le profil précédent, charger l'image depuis le fichier
            avatarImageView = new ImageView(new Image(avatarFileName));
            hbox.getChildren().add(avatarImageView);
        } else {
            // Sinon, utiliser l'image constante
            avatarImageView = new ImageView(new Image("Robot.png"));
        }
        avatarImageView.setFitWidth(150); // Taille de l'avatar
        avatarImageView.setFitHeight(150);

        // Création d'une VBox pour contenir le nom et le rang
        VBox labelsVBox = new VBox(0); // Espacement vertical entre les labels
        if (isPlayer1 || suishumain) {
            labelsVBox.setAlignment(Pos.CENTER_LEFT); // Alignement à droite des labels pour le joueur 1
        } else {
            labelsVBox.setAlignment(Pos.CENTER_RIGHT); // Alignement à gauche des labels pour le joueur 2
            // Définir le nom et le rang par défaut pour le joueur 2
            playerName = "Robot";
            rank = "IA";
        }

        // Ajout du nom du joueur
        Label nameLabel = new Label(playerName);
        nameLabel.setFont(Font.font("Cardo", 35)); // Définition de la police et de la taille
        nameLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(nameLabel);

        // Ajout du rang du joueur
        Label rankLabel = new Label(rank);
        rankLabel.setFont(Font.font("Cardo", 20)); // Définition de la police et de la taille
        rankLabel.setTextFill(Color.WHITE); // Définition de la couleur du text
        labelsVBox.getChildren().add(rankLabel);

        hbox.getChildren().add(labelsVBox); // Ajout de la VBox des labels à la HBox

        // Ajout de l'avatar à droite pour le joueur 2
        if (!isPlayer1 && !suishumain) {
            hbox.getChildren().add(avatarImageView); // Ajout de l'avatar à droite pour le joueur 2
        }

        // Ajout de la HBox au VBox principal
        profileBox.getChildren().add(hbox);

        return profileBox;
    }

}
