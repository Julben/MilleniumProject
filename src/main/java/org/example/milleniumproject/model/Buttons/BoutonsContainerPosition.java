package org.example.milleniumproject.model.Buttons;

import javafx.scene.layout.HBox;

public class BoutonsContainerPosition extends HBox {

    public BoutonsContainerPosition(int Space, int X, int Y) {
        // Création d'un conteneur pour les boutons
        setSpacing(Space); // Espacement entre les boutons
        setLayoutX(X);
        setLayoutY(Y);
    }
}

