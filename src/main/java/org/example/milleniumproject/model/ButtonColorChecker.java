package org.example.milleniumproject.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ButtonColorChecker {

    public static void checkAndChangeButtonColor(String buttonLabel1, String buttonLabel2, String buttonLabel3, GridPane gridPane) {
        // Variables pour stocker les URLs des boutons
        String url1 = null;
        String url2 = null;
        String url3 = null;

        // Obtenez tous les nœuds enfants du GridPane
        ObservableList<Node> children = gridPane.getChildren();

        // Parcourez chaque nœud pour obtenir les informations de chaque bouton
        for (Node node : children) {
            if (node instanceof Button) {
                Button currentButton = (Button) node;
                String buttonText = getButtonText(currentButton);
                String imageUrl = getImageUrlFromButton(currentButton);

                // Stockez les URLs des boutons
                if (buttonText.equals(buttonLabel1)) {
                    url1 = imageUrl;
                } else if (buttonText.equals(buttonLabel2)) {
                    url2 = imageUrl;
                } else if (buttonText.equals(buttonLabel3)) {
                    url3 = imageUrl;
                }
            }
        }

        // Vérifiez si les URLs des boutons sont identiques
        if (url1 != null && url2 != null && url3 != null && url1.equals(url2) && url1.equals(url3)) {
            // Parcourez à nouveau chaque nœud pour changer la couleur des boutons
            for (Node node : children) {
                if (node instanceof Button) {
                    Button currentButton = (Button) node;
                    String buttonText = getButtonText(currentButton);
                    // Si le bouton est l'un des boutons spécifiés, changez sa couleur en rouge
                    if (buttonText.equals(buttonLabel1) || buttonText.equals(buttonLabel2) || buttonText.equals(buttonLabel3)) {
                        currentButton.setStyle("-fx-background-color: yellow;"+
                                "-fx-background-radius: 50%; " + // Rendre les coins ronds
                                "-fx-min-width: 65px; " + // Définir la largeur
                                "-fx-min-height: 65px; " + // Définir la hauteur
                                "-fx-max-width: 65px; " + // Limiter la largeur
                                "-fx-max-height: 65px;");
                    }
                }
            }
        }
    }

    private static String getImageUrlFromButton(Button button) {
        // Vérifier si le bouton contient une image
        if (button.getGraphic() instanceof ImageView) {
            ImageView imageView = (ImageView) button.getGraphic();
            Image image = imageView.getImage();

            // Récupérer l'URL de l'image
            String imageUrl = image.getUrl();
            return imageUrl;
        } else {
            // Le bouton ne contient pas d'image
            return null;
        }
    }

    private static String getButtonText(Button button) {
        // Récupérer le texte du bouton
        String buttonText = button.getText();
        return buttonText;
    }
}

