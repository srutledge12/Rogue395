// package src;

public class Player extends Creature{
    private Item sword;
    private Item armor;
    private String DeathAction;
    private String HitAction;
    private String name;
    public int room;
    private int serial;

    public void setSword(Item _sword) {
        this.sword = _sword;
    }
    public void setArmor(Item _armor) {
        this.armor = _armor;
    }
    public void setName(String string){
        this.name = string;
        System.out.println("setName");
    }

    public void setID(int room, int serial){
        this.room = room;
        this.serial = serial;
        System.out.println("setID");
    }
}