// package src;
public class Creature extends Displayable{
    private int Hp;
    private int HpMoves;
    public CreatureAction DeathAction;
    public String HitAction;
    public Player[ ] players;
    public Monster[ ] monsters;

    /*public Creature(String _name, int _numPlayers, int _numMonsters) {
        name = _name;
        numActivities = _numActivities;
        activities = new Activity[numActivities];
    }*/

    public void setHp(int _Hp) {
        // Hp = _Hp;
    }

    public void setHpMoves(int _HpMoves) {
        // HpMoves = _HpMoves;
    }

    public void setAction(CreatureAction _DeathAction) {
        DeathAction = _DeathAction;
    }

}