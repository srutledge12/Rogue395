// package src;

public class DropPack extends CreatureAction
{
    private String name;
    private Creature owner;
    public DropPack(String _name, Creature _owner){
        super(_owner);
        this.owner = _owner;
        this.name = _name;
    }
}
