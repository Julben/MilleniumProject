package org.example.milleniumproject.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SauvegardePartie {
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


    public SauvegardePartie(GridPane gridPane,String avatar1,String avatar2,String rank1,String rank2, String ship1, String ship2, String name1, String name2,int currentPlayer,int turn,int chronoselect,int BGselect) {
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
    }

    public void sauvegarderDansFichier(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            // Parcourir les éléments de la grille
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

            // Écrire d'autres données de la partie
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}