// package src;

public class EndGame extends CreatureAction
{
    private String name;
    private Creature owner;
    public EndGame(String _name, Creature _owner){
        super(_owner);
        this.owner = _owner;
        this.name = _name;
    }
}
