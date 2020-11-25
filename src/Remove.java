// package src;

public class Remove extends CreatureAction
{
    private String name;
    private Creature owner;
    public Remove(String _name, Creature _owner){
        super(_owner);
        this.owner = _owner;
        this.name = _name;
    }
}
