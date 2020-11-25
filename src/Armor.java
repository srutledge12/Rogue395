// package src;

public class Armor extends Item {
    private String name;
    private int room;
    private int serial;

    public Armor(){
        // name = _name;
    }

    public Armor(String _name){
        this.name = _name;

    }
    public void setName(String _name) {
        this.name = _name;
    }
    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }
}