package org.example.milleniumproject.presentation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * La classe RectangleConstructor permet de créer et de gérer des objets Rectangle avec des caractéristiques spécifiques.
 */
public class RectangleConstructor {
    private final Rectangle rectangle;

    /**
     * Constructeur de la classe RectangleConstructor.
     *
     * @param width      La largeur du rectangle.
     * @param height     La hauteur du rectangle.
     * @param translateX La translation en X du rectangle.
     * @param translateY La translation en Y du rectangle.
     */
    public RectangleConstructor(double width, double height, double translateX, double translateY) {
        rectangle = new Rectangle(width, height);
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(4);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setTranslateX(translateX);
        rectangle.setTranslateY(translateY);
    }

    /**
     * Définit la couleur du contour (stroke) du rectangle.
     *
     * @param color La couleur à utiliser pour le contour du rectangle.
     */
    public void setStrokeColor(Color color){
        rectangle.setStroke(color);
    }

    /**
     * Obtient la couleur du contour (stroke) du rectangle.
     *
     * @return La couleur du contour du rectangle.
     */
    public Paint getStrokeColor(){
        return rectangle.getStroke();
    }

    /**
     * Obtient l'objet Rectangle encapsulé.
     *
     * @return L'objet Rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
}
