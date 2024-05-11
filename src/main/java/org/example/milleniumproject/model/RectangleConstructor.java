package org.example.milleniumproject.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleConstructor {
    private final Rectangle rectangle;

    public RectangleConstructor(int width, int height, int translateX, int translateY) {
        rectangle = new Rectangle(width, height);
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(4);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setTranslateX(translateX);
        rectangle.setTranslateY(translateY);
    }

    public void setStrokeColor(Color color){
        rectangle.setStroke(color);
    }

    public Paint getStrokeColor(){
        return rectangle.getStroke();
    }

    // Getter pour le rectangle
    public Rectangle getRectangle() {
        return rectangle;
    }
}