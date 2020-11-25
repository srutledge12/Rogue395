// package src;

public class BlessCurseOwner extends ItemAction
{
    private Creature owner;
    public BlessCurseOwner(Creature _owner) {
        super(_owner);
        this.owner = _owner;
    }
}