package org.example.milleniumproject.model.Enum;

public enum TitreEnum {
    un(1,"Padawan"),
    deux(2,"Apprenti Jedi"),
    trois(3,"Jeune Jedi"),
    quatre(4,"Allie fidèle de l'ordre"),
    cinq(5,"Jedi"),
    six(6, "Maitre jedi"),
    sept(7,"Seigneur Sith"),
    huit(8,"Wookie"),
    neuf(9,"Mandalorian"),
    dix(10,"Jsp quoi");
    private final int RankValue;
    private final String Text;
    TitreEnum(int RankValue, String Text) {
        this.RankValue=RankValue;
        this.Text=Text;
    }
    public String getText(){
        return Text;
    }
    public int getRankValue(){
        return RankValue;
    }
}
