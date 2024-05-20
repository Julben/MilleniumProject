package org.example.milleniumproject.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.prefs.Preferences;

/**
 * La classe VideoData stocke les préférences des vidéos et des animations.
 * Elle utilise les préférences utilisateur pour sauvegarder et récupérer les valeurs des choix.
 */
public class VideoData {

    private static final Preferences preferences = Preferences.userNodeForPackage(VideoData.class);

    private static BooleanProperty videoChoose = new SimpleBooleanProperty(false);
    private static BooleanProperty animation = new SimpleBooleanProperty(false);

    /**
     * Vérifie si l'utilisateur a choisi d'activer les vidéos de pré-lancement.
     *
     * @return true si les vidéos de pré-lancement sont activées, sinon false.
     */
    public static boolean isVideoChoose() {
        return videoChoose.get();
    }

    /**
     * Définit si l'utilisateur a choisi d'activer les vidéos de pré-lancement.
     *
     * @param choose true pour activer les vidéos de pré-lancement, sinon false.
     */
    public static void setVideoChoose(boolean choose) {
        videoChoose.set(choose);
        preferences.putBoolean("videoChoose", choose);
    }

    /**
     * Récupère la propriété associée au choix des vidéos de pré-lancement.
     *
     * @return la propriété associée au choix des vidéos de pré-lancement.
     */
    public static BooleanProperty videoChooseProperty() {
        return videoChoose;
    }

    /**
     * Vérifie si l'utilisateur a choisi d'activer les animations.
     *
     * @return true si les animations sont activées, sinon false.
     */
    public static boolean isAnimation() {
        return animation.get();
    }

    /**
     * Définit si l'utilisateur a choisi d'activer les animations.
     *
     * @param animate true pour activer les animations, sinon false.
     */
    public static void setAnimation(boolean animate) {
        animation.set(animate);
        preferences.putBoolean("animation", animate);
    }

    /**
     * Récupère la propriété associée au choix des animations.
     *
     * @return la propriété associée au choix des animations.
     */
    public static BooleanProperty animationProperty() {
        return animation;
    }

    static {
        videoChoose.set(preferences.getBoolean("videoChoose", false));
        animation.set(preferences.getBoolean("animation", false));
    }
}