// package src;

public class Monster extends Creature{
    private String name;
    public int room;
    public int serial;
    public int s_x;
    public int s_y;
    public int index;

    public Monster(){
        System.out.println("Monster");
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
