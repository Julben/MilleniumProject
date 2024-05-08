/*
package org.example.milleniumproject.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.prefs.Preferences;

public class AudioData {

    private static final Preferences preferences = Preferences.userNodeForPackage(AudioData.class);

    private static DoubleProperty soundVolume = new SimpleDoubleProperty(0.5);
    private static DoubleProperty musicVolume = new SimpleDoubleProperty(0.5);
    private static DoubleProperty sliderPosition = new SimpleDoubleProperty(50);

    public static double getSoundVolume() {
        return soundVolume.get();
    }

    public static void setSoundVolume(double volume) {
        soundVolume.set(volume);
        preferences.putDouble("soundVolume", volume); // Enregistre le volume dans les préférences
    }

    public static DoubleProperty soundVolumeProperty() {
        return soundVolume;
    }

    public static double getMusicVolume() {
        return musicVolume.get();
    }

    public static void setMusicVolume(double volume) {
        musicVolume.set(volume);
        preferences.putDouble("musicVolume", volume); // Enregistre le volume dans les préférences
    }

    public static DoubleProperty musicVolumeProperty() {
        return musicVolume;
    }

    public static double getSliderPosition() {
        return sliderPosition.get();
    }

    public static void setSliderPosition(double position) {
        sliderPosition.set(position);
        preferences.putDouble("sliderPosition", position); // Enregistre la position dans les préférences
    }

    public static DoubleProperty sliderPositionProperty() {
        return sliderPosition;
    }

    // Initialisation des données à partir des préférences
    static {
        soundVolume.set(preferences.getDouble("soundVolume", 0.5));
        musicVolume.set(preferences.getDouble("musicVolume", 0.5));
        sliderPosition.set(preferences.getDouble("sliderPosition", 50));
    }
}
*/