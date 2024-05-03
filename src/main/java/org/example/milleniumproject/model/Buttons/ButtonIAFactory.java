package org.example.milleniumproject.model.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class ButtonIAFactory {

    // Créer des boutons avec des images à partir de chemins d'images
    public List<Button> createImageButtons(String[] imagePaths) {
        List<Button> imageButtons = new ArrayList<>();

        for (String path : imagePaths) {
            Image image = new Image(path);
            Extensionimagebuttonia button = new Extensionimagebuttonia(image);
            imageButtons.add(button);
        }

        return imageButtons;
    }
}

