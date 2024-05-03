package org.example.milleniumproject.model.Enum;

public enum ImageEnum {
    premiere(1, "src/main/resources/Avatar/1.png"),
    deuxieme(2, "src/main/resources/Avatar/2.png"),
    troisieme(3, "src/main/resources/Avatar/3.png"),
    quatre(4, "src/main/resources/Avatar/4.png"),
    cinq(5, "src/main/resources/Avatar/5.png"),
    six(6, "src/main/resources/Avatar/6.png"),
    sept(7, "src/main/resources/Avatar/7.png"),
    huit(8, "src/main/resources/Avatar/8.png"),
    neuf(9, "src/main/resources/Avatar/9.png"),
    dix(10, "src/main/resources/Avatar/10.png"),
    onze(11, "src/main/resources/Avatar/11.png"),
    douze(12, "src/main/resources/Avatar/12.png");

    private final int rankValue;
    private final String imageFile;
    ImageEnum(int rankValue, String imageFile) {
        this.rankValue = rankValue;
        this.imageFile= imageFile;
    }

    public String getImageFile() {
        return imageFile;
    }

    public int getRankValue() {
        return rankValue;
    }
}
