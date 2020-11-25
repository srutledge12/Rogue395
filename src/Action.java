// package src;

public class Action{
    private char c;
    private String msg;
    private int v;
    public CreatureAction[ ] creatureactions;
    public ItemAction[ ] itemactions;

    /*public Creature(String _name, int _numPlayers, int _numMonsters) {
        name = _name;
        numActivities = _numActivities;
        activities = new Activity[numActivities];
    }*/

    public void setMessage(String _msg) {
        this.msg = _msg;
        System.out.println(this.msg);
    }

    public void setIntValue(int _v) {
        this.v = _v;
        System.out.println(this.v);
    }

    public void setCharValue(char _c) {
        this.c = _c;
        System.out.println(this.c);
    }
}