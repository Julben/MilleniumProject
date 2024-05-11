package org.example.milleniumproject.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.prefs.Preferences;

public class VideoData {

    private static final Preferences preferences = Preferences.userNodeForPackage(VideoData.class);

    private static BooleanProperty videoChoose = new SimpleBooleanProperty(false);
    private static BooleanProperty animation = new SimpleBooleanProperty(false);

    public static boolean isVideoChoose() {
        return videoChoose.get();
    }

    public static void setVideoChoose(boolean choose) {
        videoChoose.set(choose);
        preferences.putBoolean("videoChoose", choose); // Enregistre la valeur dans les préférences
    }

    public static BooleanProperty videoChooseProperty() {
        return videoChoose;
    }

    public static boolean isAnimation() {
        return animation.get();
    }

    public static void setAnimation(boolean animate) {
        animation.set(animate);
        preferences.putBoolean("animation", animate); // Enregistre la valeur dans les préférences
    }

    public static BooleanProperty animationProperty() {
        return animation;
    }

    // Initialisation des données à partir des préférences
    static {
        videoChoose.set(preferences.getBoolean("videoChoose", false));
        animation.set(preferences.getBoolean("animation", false));
    }
}
