package org.example.milleniumproject.model;

import javafx.stage.Screen;

/**
 * Classe contenant les constantes utilisées dans le projet.
 */
public class Constant {

    /** Hauteur des boutons en mode vertical. */
    public static final double button_heightV = 50;

    /** Hauteur des boutons en mode horizontal. */
    public static final double button_heightH = 360;

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
