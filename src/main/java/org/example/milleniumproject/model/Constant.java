package org.example.milleniumproject.model;

import javafx.stage.Screen;

//Créer une classe oû mettre les valeures constante
public class Constant {
    public static final double button_heightV = 50;
    public static final double button_heightH = 360;
    static Screen screen = Screen.getPrimary();
    public static double screenWidth = screen.getBounds().getWidth();
    public static double screenHeight = screen.getBounds().getHeight();
}