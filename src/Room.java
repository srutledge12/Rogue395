// package src;

public class Room extends Structure{
    String x;
    int room;
    Creature monster;

    public Room(String x){
        this.x = x;
        System.out.println("Room");
    }

    public void setId(int room){
        this.room = room;
        System.out.println("setId");
    }

    public void setCreature(Creature Monster){
        this.monster = Monster;
        System.out.println("setCreature");
    }
}
