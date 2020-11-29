// package src;

public class CreatureAction extends Action{
    private Creature owner;
    public String name;
    public CreatureAction(Creature _owner) {
        this.owner = _owner;
    }
    public void name(String _name){this.name = _name;}
}