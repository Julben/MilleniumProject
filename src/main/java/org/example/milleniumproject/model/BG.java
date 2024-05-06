package org.example.milleniumproject.model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Classe pour ajouter un fond d'écran à une StackPane.
 */
public class BG extends StackPane {

    private Background background;

    /**
     * Crée un fond d'écran à partir du chemin de l'image spécifié.
     *
     * @param imagePath Le chemin de l'image à utiliser comme fond d'écran.
     * @throws RuntimeException Si le fichier image n'est pas trouvé.
     */
    public BG(String imagePath) {

        try {
            Image image = new Image(new FileInputStream(imagePath));
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
            this.background = new Background(backgroundImage);
            setBackground(this.background);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtient le fond d'écran personnalisé associé à cette StackPane.
     *
     * @return Le fond d'écran personnalisé.
     */
    public Background getCustomBackground() {
        return background;
    }
}
