// package src;

public class Scroll extends Item
{
    private String name;
    private int room;
    private int serial;

    public Scroll() {
        // name = _name;
    }

    public Scroll(String _name) {
        this.name = _name;
    }
    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }
}