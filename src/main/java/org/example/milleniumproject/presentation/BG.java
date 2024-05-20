package org.example.milleniumproject.presentation;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Classe pour ajouter un fond d'écran.
 */
public class BG extends StackPane {

    private Background background;

    /**
     * Crée un fond d'écran à partir d'une image dans les ressources'.
     *
     * @param imagePath L'image à utiliser comme fond d'écran.
     * @throws RuntimeException Exception si l'image n'est pas trouvé.
     */
    public BG (String imagePath) {

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

    public BG() {

        List<String> stringList = Arrays.asList("src/main/resources/Backgrounds/BG1.png", "src/main/resources/Backgrounds/BG2.png", "src/main/resources/Backgrounds/BG3.png", "src/main/resources/Backgrounds/BG4.png", "src/main/resources/Backgrounds/BG5.png",
                "src/main/resources/Backgrounds/BG6.png", "src/main/resources/Backgrounds/BG7.png", "src/main/resources/Backgrounds/BG8.png", "src/main/resources/Backgrounds/BG9.png", "src/main/resources/Backgrounds/BG10.png",
                "src/main/resources/Backgrounds/BG11.png", "src/main/resources/Backgrounds/BG12.png", "src/main/resources/Backgrounds/BG13.png", "src/main/resources/Backgrounds/BG14.png", "src/main/resources/Backgrounds/BG15.png",
                "src/main/resources/Backgrounds/BG16.png", "src/main/resources/Backgrounds/BG17.png", "src/main/resources/Backgrounds/BG18.png", "src/main/resources/Backgrounds/BG19.png", "src/main/resources/Backgrounds/BG20.png",
                "src/main/resources/Backgrounds/BG21.png", "src/main/resources/Backgrounds/BG22.png", "src/main/resources/Backgrounds/BG23.png", "src/main/resources/Backgrounds/BG24.png");

        Random rand = new Random();
        int index = rand.nextInt(stringList.size());
        String randomString = stringList.get(index);

        try {
            Image image = new Image(new FileInputStream(randomString));
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, backgroundSize);
            this.background = new Background(backgroundImage);
            setBackground(this.background);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtient le fond d'écran.
     *
     * @return Le fond d'écran.
     */
    public Background getCustomBackground() {
        return background;
    }
}