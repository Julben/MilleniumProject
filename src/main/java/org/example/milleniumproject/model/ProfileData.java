package org.example.milleniumproject.model;

import java.util.prefs.Preferences;

/**
 * Classe permettant de gérer les données des profils des joueurs.
 */
public class ProfileData {
    // Préférences pour stocker les données des profils
    private static final Preferences preferences = Preferences.userNodeForPackage(ProfileData.class);

    /**
     * Enregistre les données du profil d'un joueur.
     *
     * @param playerNumber Le numéro du joueur
     * @param playerName   Le nom du joueur
     * @param avatar       L'avatar du joueur
     * @param rank         Le rang du joueur
     * @param ship         Le vaisseau du joueur
     */
    public static void saveProfile(int playerNumber, String playerName, String avatar, String rank, String ship) {
        preferences.put("playerName" + playerNumber, playerName);
        preferences.put("avatar" + playerNumber, avatar);
        preferences.put("rank" + playerNumber, rank);
        preferences.put("ship" + playerNumber, ship);
    }

    /**
     * Récupère le nom du joueur spécifié.
     *
     * @param playerNumber Le numéro du joueur
     * @return Le nom du joueur
     */
    public static String getPlayerName(int playerNumber) {
        return preferences.get("playerName" + playerNumber, "");
    }

    /**
     * Récupère l'avatar du joueur spécifié.
     *
     * @param playerNumber Le numéro du joueur
     * @return L'avatar du joueur
     */
    public static String getAvatar(int playerNumber) {
        return preferences.get("avatar" + playerNumber, "");
    }

    /**
     * Récupère le rang du joueur spécifié.
     *
     * @param playerNumber Le numéro du joueur
     * @return Le rang du joueur
     */
    public static String getRank(int playerNumber) {
        return preferences.get("rank" + playerNumber, "");
    }

    /**
     * Récupère le vaisseau du joueur spécifié.
     *
     * @param playerNumber Le numéro du joueur
     * @return Le vaisseau du joueur
     */
    public static String getShip(int playerNumber) {
        return preferences.get("ship" + playerNumber, "");
    }

    /**
     * Réinitialise les données des profils des joueurs.
     */
    public static void resetProfiles() {
        preferences.remove("playerName1");
        preferences.remove("avatar1");
        preferences.remove("rank1");
        preferences.remove("ship1");

        preferences.remove("playerName2");
        preferences.remove("avatar2");
        preferences.remove("rank2");
        preferences.remove("ship2");
    }
}
