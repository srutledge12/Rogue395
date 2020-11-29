// package src;

public class Player extends Creature{
    private static Item sword;
    private static Item armor;
    private String DeathAction;
    private String HitAction;
    private String name;
    public int room;
    private int serial;

    public static void setSword(Item _sword) {
        sword = _sword;
    }
    public static void setArmor(Item _armor) {
        armor = _armor;
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

    // public static void update(int dx, int dy)
    // {
    //     move(dx, dy);
    // }
    
}