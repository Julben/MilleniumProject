package org.example.milleniumproject.model;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauvegardePartieTest {

    private SauvegardePartie sauvegardePartie;
    private GridPane gridPane;
    private final String nomFichier = "test_sauvegarde.txt";

    @BeforeEach
    public void setUp() {
        // Initialiser le toolkit JavaFX
        new JFXPanel();

        gridPane = new GridPane();
        Button button1 = new Button();
        button1.setGraphic(new ImageView(new Image("file:src/test/resources/avatar1.png")));
        gridPane.add(button1, 0, 0);

        Button button2 = new Button();
        gridPane.add(button2, 1, 0);

        sauvegardePartie = new SauvegardePartie(gridPane, "avatar1.png", "avatar2.png", "rank1", "rank2", "ship1", "ship2", "name1", "name2", 1, 10, 5, 2);
    }

    @AfterEach
    public void tearDown() {
        // Supprimer le fichier de test après chaque test
        java.nio.file.Path path = java.nio.file.Paths.get(nomFichier);
        try {
            java.nio.file.Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSauvegarderDansFichier() throws IOException {
        // Exécuter la méthode de sauvegarde
        sauvegardePartie.sauvegarderDansFichier(nomFichier);

        // Lire le fichier de sauvegarde
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            assertEquals("setgraphique=true, file:src/test/resources/avatar1.png", reader.readLine());
            assertEquals("setgraphique=false, ", reader.readLine());
            assertEquals("currentPlayer=1", reader.readLine());
            assertEquals("Tour=10", reader.readLine());
            assertEquals("Avatar1=avatar1.png", reader.readLine());
            assertEquals("Avatar2=avatar2.png", reader.readLine());
            assertEquals("Rank1=rank1", reader.readLine());
            assertEquals("Rank2=rank2", reader.readLine());
            assertEquals("Ship1=ship1", reader.readLine());
            assertEquals("Ship2=ship2", reader.readLine());
            assertEquals("Name1=name1", reader.readLine());
            assertEquals("Name2=name2", reader.readLine());
            assertEquals("ChronoSelect=5", reader.readLine());
            assertEquals("BGSelect=2", reader.readLine());
        }
    }
}
