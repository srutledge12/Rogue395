// package src;

public class Item extends Displayable
{
    private Creature owner;
    private String hpMoves;
    private ItemAction deathAction;
    private String hitAction;
    /*public Creature(String _name, int _numPlayers, int _numMonsters) {
        name = _name;
        numActivities = _numActivities;
        activities = new Activity[numActivities];
    }*/

    public void setOwner(Creature _owner) {
        this.owner = _owner;
    }

    public void setHpMoves(String _HpMoves) {
        this.hpMoves = _HpMoves;
        // HpMoves = _HpMoves;
    }

    public void setAction(ItemAction _DeathAction) {
        this.deathAction = _DeathAction;
        // DeathAction = _DeathAction;
    }

    // public void setHitAction(String _HitAction) {
    //     this.hitAction = _HitAction;
    //     // HitAction = _HitAction;
    // }
}