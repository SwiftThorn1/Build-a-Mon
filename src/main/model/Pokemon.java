package model;

//Represents a Pokemon with a name, one or two types, and stats (HP, attack, defense, special attack, special defense,
// and speed)
//All stats should be > 0
public class Pokemon {
    private String name;
    private String type1;
    private String type2;
    private int hp;
    private int atk;
    private int def;
    private int spa;
    private int spd;
    private int spe;

    //EFFECTS: initializes a Pokemon with the given attributes
    public Pokemon(String name, String type1, String type2, int hp, int atk, int def, int spa, int spd, int spe) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spa = spa;
        this.spd = spd;
        this.spe = spe;
    }

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpa() {
        return spa;
    }

    public int getSpd() {
        return spd;
    }

    public int getSpe() {
        return spe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }

    //EFFECTS: returns name and stats
    public String toString() {
        if (type2.equals("")) {
            return name + " | Type: " + type1 + " | HP: " + hp + " | Attack: " + atk + " | Defense: " + def
                    + " | Sp. Attack: " + spa + " | Sp. Def: " + spd + " | Speed: " + spe;
        }
        return name + " | Type: " + type1 + "/" + type2 + " | HP: " + hp + " | Attack: " + atk + " | Defense: " + def
                + " | Sp. Attack: " + spa + " | Sp. Def: " + spd + " | Speed: " + spe;
    }
}
