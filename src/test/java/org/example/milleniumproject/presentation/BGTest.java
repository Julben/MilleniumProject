package org.example.milleniumproject.presentation;

//import presentation.BG;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Background;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BGTest {

	@BeforeAll
	public static void init() {
		// Initialiser JavaFX Toolkit pour les tests
		JFXPanel jfxPanel = new JFXPanel();
	}

	@Test
	public void testCustomBackgroundNotNull() {
		BackGround bg = new BackGround("src/main/resources/Backgrounds/BG1.png");
		assertNotNull(bg.getCustomBackground(), "Le fond d'écran personnalisé ne doit pas être null");
	}

	@Test
	public void testRandomBackgroundNotNull() {
		BackGround bg = new BackGround();
		assertNotNull(bg.getCustomBackground(), "Le fond d'écran personnalisé ne doit pas être null");
	}

	@Test
	public void testGetCustomBackground() {
		BackGround bg = new BackGround("src/main/resources/Backgrounds/BG1.png");
		Background expectedBackground = bg.getCustomBackground();
		assertNotNull(expectedBackground, "Le fond d'écran personnalisé ne doit pas être null");

		// Vérifier que le fond d'écran renvoyé est celui qui a été défini
		assertEquals(expectedBackground, bg.getCustomBackground(), "Les fonds d'écran doivent être égaux");
	}
}
