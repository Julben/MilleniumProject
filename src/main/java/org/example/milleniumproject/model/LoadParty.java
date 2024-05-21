package org.example.milleniumproject.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadParty {

    public List<Object> loadPartyFromFile(String nomfichier) {
        String avatar1 = "";
        String avatar2 = "";
        String rank1 = "";
        String rank2 = "";
        String ship1 = "";
        String ship2 = "";
        String name1 = "";
        String name2 = "";
        List<String> pictureButton = new ArrayList<>();
        int currentPlayer = 1;
        int turn = 0;
        int chronoselect = 0;
        int BGselect = 0;
        boolean Ia =false;
        int difficulty=0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Save/"+nomfichier+".txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.startsWith("setgraphique=true")) {
                    pictureButton.add(ligne.split("main/")[1].trim());
                } else if (ligne.startsWith("setgraphique=false")) {
                    pictureButton.add("null");
                } else if (ligne.startsWith("currentPlayer=")) {
                    currentPlayer = Integer.parseInt(ligne.split("=")[1].trim());
                } else if (ligne.startsWith("Tour=")) {
                    turn = Integer.parseInt(ligne.split("=")[1].trim());
                } else if (ligne.startsWith("Avatar1=")) {
                    avatar1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Avatar2=")) {
                    avatar2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Rank1=")) {
                    rank1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Rank2=")) {
                    rank2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Ship1=")) {
                    ship1 = ligne.split("resources/")[1].trim();
                } else if (ligne.startsWith("Ship2=")) {
                    ship2 = ligne.split("resources/")[1].trim();
                } else if (ligne.startsWith("Name1=")) {
                    name1 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("Name2=")) {
                    name2 = ligne.split("=")[1].trim();
                } else if (ligne.startsWith("ChronoSelect=")) {
                    chronoselect = Integer.parseInt(ligne.split("=")[1].trim());
                } else if (ligne.startsWith("BGSelect=")) {
                    BGselect = Integer.parseInt(ligne.split("=")[1].trim());
                }else if(ligne.startsWith("Ia=")){
                    Ia= Boolean.parseBoolean(ligne.split("=")[1].trim());
                }else if(ligne.startsWith("Difficulté=")){
                    difficulty= Integer.parseInt(ligne.split("=")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(avatar1, avatar2, rank1, rank2, ship1, ship2, name1, name2, currentPlayer, turn, chronoselect, BGselect, pictureButton,Ia,difficulty);
    }
}