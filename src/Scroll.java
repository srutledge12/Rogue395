// package src;

public class Scroll extends Item
{
    public String name;
    public int room;
    public int serial;

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