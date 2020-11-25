// package src;

import java.util.ArrayList;

public class Passage extends Structure
{
    String name;
    int room1;
    int room2;
    ArrayList<Integer> x_coordinates = new ArrayList<Integer>();
    ArrayList<Integer> y_coordinates = new ArrayList<Integer>();


    public Passage(){
        System.out.println("Passage");
    }

    public void setName(String x){
        this.name = x;
        System.out.printf("setName: %s\n", name);
    }
    public void set_coordinatesx(int x)
    {
        this.x_coordinates.add(x);
    }
    public void set_coordinatesy(int x)
    {
        this.y_coordinates.add(x);
    }

    public void setId(int room1, int room2)
    {
        this.room1 = room1;
        this.room2 = room2;
        System.out.printf("room1: %d, room2: %d\n", room1, room2);
    }

    
}
