package org.example.milleniumproject.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.milleniumproject.model.ProfileData;
import org.example.milleniumproject.view.Profil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilTest {
    private Profil profil;

    @BeforeEach
    public void setUp() {
        // Initialiser JavaFX pour les tests
        JFXPanel jfxPanel = new JFXPanel();
        // Créer une instance de la classe Profil
        profil = new Profil(new Stage());
    }

    @Test
    public void testCreateTextField() {
        TextField textField = profil.createTextField("PlayerName");
        assertEquals("PlayerName", textField.getText());
    }

    @Test
    public void testCreatePlayerBox() {
        String[] avatar = {"src/main/resources/Avatar/1.png", "src/main/resources/Avatar/2.png"};
        String[] rang = {"Padawan", "Apprenti Jedi"};
        String[] vaisseau = {"src/main/resources/PionDestroyer.png", "src/main/resources/PionFaucon.png"};

        VBox vBox = profil.createPlayerBox("Joueur 1", avatar, rang, vaisseau, "PlayerName", 0, 0, 0);

        // Vérifier que la VBox contient bien les éléments attendus
        assertEquals(5, vBox.getChildren().size());
        assertTrue(vBox.getChildren().get(2) instanceof TextField);
    }

    @Test
    public void testSaveProfileData() {
        profil.saveProfileData(1, "PlayerName", 0, 0, 0, new String[]{"Avatar1", "Avatar2"}, new String[]{"Rank1", "Rank2"}, new String[]{"Ship1", "Ship2"});
        assertEquals("PlayerName", ProfileData.getPlayerName(1));
        assertEquals("Avatar1", ProfileData.getAvatar(1));
        assertEquals("Rank1", ProfileData.getRank(1));
        assertEquals("Ship1", ProfileData.getShip(1));
    }
}
