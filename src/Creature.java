import java.util.ArrayList;

// package src;
public class Creature extends Displayable{
    public int Hp;
    public int HpMoves;
    public CreatureAction DeathAction;
    public ArrayList<CreatureAction> HA_list = new ArrayList<>();
    public ArrayList<CreatureAction> DA_list = new ArrayList<>();
    public Player[ ] players;
    public Monster[ ] monsters;
    public int win = 0;
    public int check = 0;


    public void setHP(int _Hp) {
         Hp = _Hp;
    }

    public void setHpMoves(int _HpMoves) {
        // HpMoves = _HpMoves;
    }

    public void setDeathAction(CreatureAction _DeathAction)
    {
        check++;
        if(_DeathAction.name == "YouWin")
        {
          win = check;
        }

        DA_list.add(_DeathAction);
    }
    public void setHitAction(CreatureAction _HitAction) {
        HA_list.add(_HitAction);
    }

}