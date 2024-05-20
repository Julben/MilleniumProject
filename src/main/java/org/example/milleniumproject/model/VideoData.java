package org.example.milleniumproject.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.prefs.Preferences;

/**
 * La classe VideoData enregistre le choix de l'apparition des vidéos et des animations.
 */
public class VideoData {

    private static final Preferences preferences = Preferences.userNodeForPackage(VideoData.class);

    private static BooleanProperty videoChoose = new SimpleBooleanProperty(false);
    private static BooleanProperty animation = new SimpleBooleanProperty(false);

    /**
     * Vérifie si le joueur a choisi d'activer les vidéos de pré-lancement.
     *
     * @return true si les vidéos sont activées ou false.
     */
    public static boolean isVideoChoose() {

        return videoChoose.get();
    }

    /**
     * Définit si le joueur a choisi d'activer les vidéos de pré-lancement.
     *
     * @param choose true pour activer les vidéos ou false.
     */
    public static void setVideoChoose(boolean choose) {
        videoChoose.set(choose);
        preferences.putBoolean("videoChoose", choose);
    }


    public static BooleanProperty videoChooseProperty() {

        return videoChoose;
    }

    /**
     * Vérifie si le joueur a choisi d'activer les animations.
     *
     * @return true si les animations sont activées ou false.
     */
    public static boolean isAnimation() {

        return animation.get();
    }

    /**
     * Définit si le joueur a choisi d'activer les animations.
     *
     * @param animate true pour activer les animations ou false.
     */
    public static void setAnimation(boolean animate) {
        animation.set(animate);
        preferences.putBoolean("animation", animate);
    }


    public static BooleanProperty animationProperty() {

        return animation;
    }

    static {
        videoChoose.set(preferences.getBoolean("videoChoose", false));
        animation.set(preferences.getBoolean("animation", false));
    }
}