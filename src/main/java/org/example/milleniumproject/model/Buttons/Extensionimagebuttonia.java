package org.example.milleniumproject.model.Buttons;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Extensionimagebuttonia extends Button {
    private boolean isGlowEffectApplied = false;

    public Extensionimagebuttonia(Image a) {
        ImageView i = new ImageView(a);
        i.setFitWidth(250); // Ajustez la largeur de l'image
        i.setFitHeight(150);// Ajustez la hauteur de l'image
        i.setPreserveRatio(false); // Permet à l'image de remplir complètement le bouton
        setGraphic(i); // Utilisez l'image comme élément graphique du bouton
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));// Définir la couleur de fond du bouton sur transparent
    }

    public void toggleGlowEffect() {
        if (isGlowEffectApplied) {
            setEffect(null); // Supprimer l'effet de lueur
        } else {
            addGlowEffect(); // Ajouter l'effet de lueur
        }
        isGlowEffectApplied = !isGlowEffectApplied; // Inverser l'état de l'effet de lueur
    }

    public void addGlowEffect() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW); // Couleur de la lueur
        glow.setRadius(10); // Rayon de la lueur
        glow.setSpread(0.8); // Étalement de la lueur
        setEffect(glow);
    }
}
