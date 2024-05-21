package org.example.milleniumproject.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * La classe SauvegardePartie permet de sauvegarder l'état d'une partie de jeu dans un fichier.
 */
public class SaveParty {
    private GridPane gridPane;
    private int currentPlayer;
    private int turn;
    private String avatar1;
    private String avatar2;
    private String rank1;
    private String rank2;
    private String ship1;
    private String ship2;
    private String name1;
    private String name2;
    private int chronoselect;
    private int BGselect;
    private boolean ia;
    private int difficulty;

    /**
     * Constructeur de la classe SauvegardePartie.
     *
     * @param gridPane      Le GridPane contenant les boutons du jeu.
     * @param avatar1       L'avatar du joueur 1.
     * @param avatar2       L'avatar du joueur 2.
     * @param rank1         Le rang du joueur 1.
     * @param rank2         Le rang du joueur 2.
     * @param ship1         Le vaisseau du joueur 1.
     * @param ship2         Le vaisseau du joueur 2.
     * @param name1         Le nom du joueur 1.
     * @param name2         Le nom du joueur 2.
     * @param currentPlayer Le joueur actuel (1 ou 2).
     * @param turn          Le numéro du tour actuel.
     * @param chronoselect  La sélection du chronomètre.
     * @param BGselect      La sélection de l'arrière-plan.
     */
    
    public SaveParty(GridPane gridPane, String avatar1, String avatar2, String rank1, String rank2, String ship1, String ship2, String name1, String name2, int currentPlayer, int turn, int chronoselect, int BGselect, boolean ia, int difficulty) {
        this.gridPane = gridPane;
        this.currentPlayer = currentPlayer;
        this.turn = turn;
        this.avatar1 = avatar1;
        this.avatar2 = avatar2;
        this.rank1 = rank1;
        this.rank2 = rank2;
        this.ship1 = ship1;
        this.ship2 = ship2;
        this.name1 = name1;
        this.name2 = name2;
        this.chronoselect=chronoselect;
        this.BGselect=BGselect;
        this.ia=ia;
        this.difficulty=difficulty;
    }
    /**
     * Sauvegarde l'état actuel de la partie dans un fichier.
     *
     * @param nomFichier Le nom du fichier dans lequel sauvegarder la partie.
     */
    public void saveInFile(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (Node node : gridPane.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    String ligne = "setgraphique=" + (button.getGraphic() != null) + ", ";
                    if (button.getGraphic() != null && button.getGraphic() instanceof ImageView) {
                        ImageView imageView = (ImageView) button.getGraphic();
                        ligne += imageView.getImage().getUrl();
                    }
                    writer.write(ligne);
                    writer.newLine();
                }
            }

            writer.write("currentPlayer=" + currentPlayer);
            writer.newLine();
            writer.write("Tour=" + turn);
            writer.newLine();
            writer.write("Avatar1="+avatar1);
            writer.newLine();
            writer.write("Avatar2="+avatar2);
            writer.newLine();
            writer.write("Rank1="+rank1);
            writer.newLine();
            writer.write("Rank2="+rank2);
            writer.newLine();
            writer.write("Ship1="+ship1);
            writer.newLine();
            writer.write("Ship2="+ship2);
            writer.newLine();
            writer.write("Name1="+name1);
            writer.newLine();
            writer.write("Name2="+name2);
            writer.newLine();
            writer.write("ChronoSelect="+chronoselect);
            writer.newLine();
            writer.write("BGSelect="+BGselect);
            writer.newLine();
            writer.write("Ia="+ia);
            writer.newLine();
            writer.write("Difficulté="+difficulty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}