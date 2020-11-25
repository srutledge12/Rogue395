// package src;

public class Dungeon extends Displayable{
    private String name;
    private int width;
    private int gameHeight;
    private int topHeight;
    private int bottomHeight;

    Room room;
    Creature creature;
    Passage passage;
    Item item;

    public Dungeon(String name, int width, int gameHeight, int topHeight, int bottomHeight){
        this.name = name;
        this.width = width;
        this.gameHeight = gameHeight;
        this.topHeight = topHeight;
        this.bottomHeight = bottomHeight;
    }

    public void addRoom(Room room){
        this.room = room;
        System.out.println("addRoom");
    }

    public void addCreature(Creature creature){
        this.creature = creature;
        System.out.println("addCreature");
    }

    public void addPassage(Passage passage){
        this.passage = passage;
        System.out.println("addPassage");
    }

    public void addItem(Item item){
        this.item = item;
        System.out.println("addItem");
    }

    
}