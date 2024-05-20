package org.example.milleniumproject.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.prefs.Preferences;

/**
 * La classe AudioData stocke les préférences audio de l'application.
 * Elle utilise les préférences utilisateur pour sauvegarder et récupérer les valeurs de volume et de position du curseur.
 */
public class AudioData {

    private static final Preferences preferences = Preferences.userNodeForPackage(AudioData.class);

    private static DoubleProperty soundVolume = new SimpleDoubleProperty(0.5);
    private static DoubleProperty musicVolume = new SimpleDoubleProperty(0.5);
    private static DoubleProperty sliderPosition = new SimpleDoubleProperty(50);

    /**
     * Récupère le volume du son.
     *
     * @return Le volume du son.
     */
    public static double getSoundVolume() {
        return soundVolume.get();
    }

    /**
     * Définit le volume du son.
     *
     * @param volume Le volume du son.
     */
    public static void setSoundVolume(double volume) {
        soundVolume.set(volume);
        preferences.putDouble("soundVolume", volume);
    }

    /**
     * Récupère la propriété associée au volume du son.
     *
     * @return La propriété associée au volume du son.
     */
    public static DoubleProperty soundVolumeProperty() {
        return soundVolume;
    }

    /**
     * Récupère le volume de la musique.
     *
     * @return Le volume de la musique.
     */
    public static double getMusicVolume() {
        return musicVolume.get();
    }

    /**
     * Définit le volume de la musique.
     *
     * @param volume Le volume de la musique.
     */
    public static void setMusicVolume(double volume) {
        musicVolume.set(volume);
        preferences.putDouble("musicVolume", volume);
    }

    /**
     * Récupère la propriété associée au volume de la musique.
     *
     * @return La propriété associée au volume de la musique.
     */
    public static DoubleProperty musicVolumeProperty() {
        return musicVolume;
    }

    /**
     * Récupère la position du curseur de volume.
     *
     * @return La position du curseur de volume.
     */
    public static double getSliderPosition() {
        return sliderPosition.get();
    }

    /**
     * Définit la position du curseur de volume.
     *
     * @param position La position du curseur de volume.
     */
    public static void setSliderPosition(double position) {
        sliderPosition.set(position);
        preferences.putDouble("sliderPosition", position);
    }

    /**
     * Récupère la propriété associée à la position du curseur de volume.
     *
     * @return La propriété associée à la position du curseur de volume.
     */
    public static DoubleProperty sliderPositionProperty() {
        return sliderPosition;
    }

    // Initialise les données à partir des préférences
    static {
        soundVolume.set(preferences.getDouble("soundVolume", 0.5));
        musicVolume.set(preferences.getDouble("musicVolume", 0.5));
        sliderPosition.set(preferences.getDouble("sliderPosition", 50));
    }
}