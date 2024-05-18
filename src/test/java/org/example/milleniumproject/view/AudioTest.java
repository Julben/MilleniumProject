package org.example.milleniumproject.view;

import javafx.stage.Stage;
import org.example.milleniumproject.view.Audio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudioTest {

    private Stage primaryStage;
    private Audio audio;

    @BeforeEach
    public void setUp() {
        primaryStage = new Stage(); // Création d'une nouvelle instance de Stage pour chaque test
        audio = new Audio(primaryStage);
    }

    @Test
    public void testSliderInitialization() {
        assertNotNull(audio.sliderWithControls2); // Vérifie si le premier slider est initialisé
        assertNotNull(audio.sliderWithControls3); // Vérifie si le deuxième slider est initialisé
    }

    @Test
    public void testButtonInitialization() {
        assertNotNull(audio.sliderWithControls2.getBtnLeft()); // Vérifie si le bouton gauche du premier slider est initialisé
        assertNotNull(audio.sliderWithControls2.getBtnRight()); // Vérifie si le bouton droit du premier slider est initialisé
        assertNotNull(audio.sliderWithControls3.getBtnLeft()); // Vérifie si le bouton gauche du deuxième slider est initialisé
        assertNotNull(audio.sliderWithControls3.getBtnRight()); // Vérifie si le bouton droit du deuxième slider est initialisé
    }

    @Test
    public void testSliderRange() {
        assertTrue(audio.sliderWithControls2.getSlider().getMin() == 0 && audio.sliderWithControls2.getSlider().getMax() == 100); // Vérifie la plage du premier slider
        assertTrue(audio.sliderWithControls3.getSlider().getMin() == 0 && audio.sliderWithControls3.getSlider().getMax() == 100); // Vérifie la plage du deuxième slider
    }

    @Test
    public void testButtonActions() {
        // Simule l'action des boutons pour vérifier si la valeur des sliders est modifiée
        audio.sliderWithControls2.getBtnLeft().fire();
        assertTrue(audio.sliderWithControls2.getSlider().getValue() == 49); // Vérifie si la valeur du premier slider est réduite de 1
        audio.sliderWithControls2.getBtnRight().fire();
        assertTrue(audio.sliderWithControls2.getSlider().getValue() == 50); // Vérifie si la valeur du premier slider est augmentée de 1
        audio.sliderWithControls3.getBtnLeft().fire();
        assertTrue(audio.sliderWithControls3.getSlider().getValue() == 49); // Vérifie si la valeur du deuxième slider est réduite de 1
        audio.sliderWithControls3.getBtnRight().fire();
        assertTrue(audio.sliderWithControls3.getSlider().getValue() == 50); // Vérifie si la valeur du deuxième slider est augmentée de 1
    }
}
