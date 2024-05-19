package org.example.milleniumproject.model;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonsStyleTest {

	@BeforeAll
	public static void initJFX() {
		// Initialiser JavaFX Toolkit
		if (System.getProperty("javafx.platform") == null) {
			System.setProperty("javafx.platform", "headless");
		}
		new JFXPanel();
	}

	@Test
	public void testAppliquerStyle() {
		// Création d'un bouton
		Button bouton = new Button("Test Button");

		// Application du style
		double width = 100;
		double height = 50;
		double size = 14;
		ButtonsStyle.appliquerStyle(bouton, width, height, size);

		// Vérification des propriétés du bouton après l'application du style
		assertEquals(width, bouton.getPrefWidth(), "La largeur du bouton doit être égale à " + width);
		assertEquals(height, bouton.getPrefHeight(), "La hauteur du bouton doit être égale à " + height);
		assertEquals(size, bouton.getFont().getSize(), "La taille de la police du texte doit être égale à " + size);
		assertEquals(Color.TRANSPARENT, ((BackgroundFill) bouton.getBackground().getFills().get(0)).getFill(), "La couleur de fond du bouton doit être blanche");
		assertEquals(Color.WHITE, bouton.getTextFill(), "La couleur du texte du bouton doit être blanche");

		// Vérification du texte du bouton
		assertEquals("Test Button", bouton.getText(), "Le texte du bouton doit être 'Test Button'");
	}
}

