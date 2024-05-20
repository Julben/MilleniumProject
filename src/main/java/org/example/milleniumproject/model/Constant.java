package org.example.milleniumproject.model;

import javafx.stage.Screen;

/**
 * Classe contenant les constantes utilisées dans le projet.
 */
public class Constant {

    /** Largeur de l'écran. */
    public static double screenWidth;

    /** Hauteur de l'écran. */
    public static double screenHeight;

    /** Objet représentant l'écran principal. */
    private static final Screen screen = Screen.getPrimary();

    // Initialisation des constantes de largeur et de hauteur de l'écran
    static {
        screenWidth = screen.getBounds().getWidth();
        screenHeight = screen.getBounds().getHeight();
    }
}