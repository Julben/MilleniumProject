package org.example.milleniumproject.model;

import java.util.prefs.Preferences;

public class ProfileData {
    private static final Preferences preferences = Preferences.userNodeForPackage(ProfileData.class);

    public static void saveProfile(int playerNumber, String playerName, String avatar, String rank, String ship) {
        preferences.put("playerName" + playerNumber, playerName);
        preferences.put("avatar" + playerNumber, avatar);
        preferences.put("rank" + playerNumber, rank);
        preferences.put("ship" + playerNumber, ship);
    }

    public static String getPlayerName(int playerNumber) {
        return preferences.get("playerName" + playerNumber, "");
    }

    public static String getAvatar(int playerNumber) {
        return preferences.get("avatar" + playerNumber, "");
    }

    public static String getRank(int playerNumber) {
        return preferences.get("rank" + playerNumber, "");
    }

    public static String getShip(int playerNumber) {
        return preferences.get("ship" + playerNumber, "");
    }

    // Méthode pour supprimer les données du profil
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