package org.example.milleniumproject.model.Enum;

public enum PionEnum {
    premiere(1,"Ressource/PionDestroyer.png"),
    deuxieme(2,"Ressource/PionTfighter.png"),
    troisieme(3,"Ressource/PionFaucon.png"),
    quatre(4,"Ressource/PionXwing.png");
    private final int rankvalue;
    private final String imagefile;
    PionEnum(int rankValue, String imageFile) {
        this.rankvalue = rankValue;
        this.imagefile= imageFile;
    }

    public String getImagePionFile() {
        return imagefile;
    }

    public int getRankvalue() {
        return rankvalue;
    }
}
