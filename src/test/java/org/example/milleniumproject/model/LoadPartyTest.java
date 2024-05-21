package org.example.milleniumproject.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadPartyTest {

	@Test
	void LoadPartyFromFile() {
		LoadParty chargerPartie = new LoadParty();
		List<Object> result = chargerPartie.loadPartyFromFile("test"); // Assurez-vous que "Save/test.txt" existe

		assertNotNull(result);
		assertEquals(15, result.size()); // Vérifie que la taille de la liste retournée est correcte

		// Vérifie les valeurs spécifiques retournées
		assertEquals("src/main/resources/Avatar/2.png", result.get(0));
		assertEquals("src/main/resources/Avatar/10.png", result.get(1));
		assertEquals("Wookie", result.get(2));
		assertEquals("Seigneur Sith", result.get(3));
		assertEquals("PionDestroyer.png", result.get(4)); // "resources/" est retiré dans la méthode
		assertEquals("PionXwing.png", result.get(5)); // "resources/" est retiré dans la méthode
		assertEquals("Marcel-Bubule", result.get(6));
		assertEquals("Pignouf", result.get(7));
		assertEquals(2, result.get(8)); // currentPlayer
		assertEquals(18, result.get(9)); // turn
		assertEquals(1, result.get(10)); // chronoselect
		assertEquals(1, result.get(11)); // BGselect
		assertTrue(result.get(12) instanceof List); // Vérifie que le dernier élément est une liste
		List<String> pictureButton = (List<String>) result.get(12);
		assertEquals(24, pictureButton.size()); // Vérifie la taille de la liste des images graphiques
		assertEquals("PionDestroyer.png", pictureButton.get(0)); // Vérifie le premier élément graphique
		assertEquals("PionXwing.png", pictureButton.get(1)); // Vérifie le deuxième élément graphique
	}
}
