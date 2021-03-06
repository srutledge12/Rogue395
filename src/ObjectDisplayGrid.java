import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject
{

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";
    private static AsciiPanel terminal;
    private List<InputObserver> inputObservers = null;
    private static int height;
    private static int width;
    public ArrayList<Displayable> dis_object = new ArrayList<Displayable>();
    public ArrayList<Room> room_list = new ArrayList<Room>();
    public Char[][] objectGrid = null;
    public static int playerX;
    public static int playerY;
    public List<Integer> usedX = new ArrayList<Integer>();
    public List<Integer> usedY = new ArrayList<Integer>();
    public static int startx = -1;
    public static int starty = -1;
    //private Stack<Displayable>[][] objectGrid = null; // note change in this line


    public ObjectDisplayGrid(int _width, int _height, ArrayList<Displayable> _dis_object, ArrayList<Room> _room_list)
    {
        width = _width;
        height = _height;
        dis_object = _dis_object;
        room_list = _room_list;

        terminal = new AsciiPanel(width, height);
        objectGrid = new Char[width][height];
        //objectGrid = (Stack<Displayable>[][]) new Stack[width][height];
        //initializeDisplay();

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0)
        {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }
    /*public void keyTyped(KeyEvent e)
    {

        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        String locationString = "key location: ";
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            locationString += "standard";
        } else if (location == KeyEvent.VK_LEFT) {
            locationString += "left";
        } else if (location == KeyEvent.VK_RIGHT) {
            locationString += "right";
        } else if (location == KeyEvent.VK_UP) {
            locationString += "up";
        } else if (location == KeyEvent.VK_DOWN) {
            locationString += "down";
        }
        else if (location == KeyEvent.VK_I) {
            locationString += "inventory";
        }
        else if (location == KeyEvent.VK_C) {
            locationString += "change";
        }
        else if (location == KeyEvent.VK_D) {
            locationString += "drop";
        }
        else if (location == KeyEvent.VK_R) {
            locationString += "read";
        }
        else if (location == KeyEvent.VK_P) {
            locationString += "pickup";
        }
        else if (location == KeyEvent.VK_W) {
            locationString += "wear";
        }


    }*/
    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }


    /*Food[] myFoods; //your food array
if (Burger.isInstance(myFoods[0])) { //check that the Food is a Burger
        ((Room)dis_object.get(i)).PosX; //cast the object to a Burger and access its property
    }*/



    public final void initializeDisplay()
    {
        
        Char ch = new Char('.');
        for (int i = 0; i < dis_object.size(); i++)
        {
            if(dis_object.get(i) instanceof Room)
            {
                for (int x = (dis_object.get(i)).PosX; x < (dis_object.get(i)).width + (dis_object.get(i)).PosX; x++)
                {
                    for (int y = (dis_object.get(i)).PosY; y < (dis_object.get(i)).height + (dis_object.get(i)).PosY; y++)
                    {
                        if(x == (dis_object.get(i)).PosX || y == (dis_object.get(i)).PosY || x == (dis_object.get(i)).width + (dis_object.get(i)).PosX - 1 || y == (dis_object.get(i)).height + (dis_object.get(i)).PosY - 1)
                        {
                            ch = new Char('X');
                            addObjectToDisplay(ch, x, y);
                            ch = new Char('.');
                        }
                        else{addObjectToDisplay(ch, x, y);
                        usedX.add(x);
                        usedY.add(y);
                        }

                    }
                }
            }
            else if(dis_object.get(i) instanceof Passage)
            {
                ch = new Char('+');
                int finish = -1;
                int x_0 = -1;
                int x_f = -1;
                int y_0 = -1;
                int y_f = -1;
                for (int number = 0; number <  ((Passage)dis_object.get(i)).x_coordinates.size() - 1 ; number++)
                {
                    x_0 = ((Passage)dis_object.get(i)).x_coordinates.get(number);
                    x_f = ((Passage)dis_object.get(i)).x_coordinates.get(number+1);
                    y_0 = ((Passage)dis_object.get(i)).y_coordinates.get(number);
                    y_f = ((Passage)dis_object.get(i)).y_coordinates.get(number+1);
                    if(x_0 < x_f  || y_0 < y_f)
                    {
                        for (int x = ((Passage)dis_object.get(i)).x_coordinates.get(number); x <= ((Passage)dis_object.get(i)).x_coordinates.get(number+1); x++)
                        {
                            for (int y = ((Passage)dis_object.get(i)).y_coordinates.get(number); y <= ((Passage)dis_object.get(i)).y_coordinates.get(number+1); y++)
                            {
                                if(x == (dis_object.get(i).PosX) && y == (dis_object.get(i).PosY))
                                {
                                    ch = new Char('+');
                                    addObjectToDisplay(ch, x, y);
                                    usedX.add(x);
                                    usedY.add(y);
                                    ch = new Char('#');
                                }

                                else
                                {
                                    addObjectToDisplay(ch, x, y);
                                    usedX.add(x);
                                    usedY.add(y);
                                    ch = new Char('#');
                                }

                            }
                        }
                    }
                    else
                        {
                            for (int x = ((Passage)dis_object.get(i)).x_coordinates.get(number); x >= ((Passage)dis_object.get(i)).x_coordinates.get(number+1); x--)
                            {
                                for (int y = ((Passage)dis_object.get(i)).y_coordinates.get(number); y >= ((Passage)dis_object.get(i)).y_coordinates.get(number+1); y--)
                                {
                                    if(x == (dis_object.get(i).PosX) && y == (dis_object.get(i).PosY))
                                    {
                                        ch = new Char('+');
                                        addObjectToDisplay(ch, x, y);
                                        usedX.add(x);
                                        usedY.add(y);
                                        ch = new Char('#');
                                    }

                                    else
                                    {
                                        addObjectToDisplay(ch, x, y);
                                        usedX.add(x);
                                        usedY.add(y);
                                        ch = new Char('#');
                                    }

                                }
                            }
                        }



                }

                //System.out.printf("Passage PosX : %d",dis_object.get(i).PosX);
                //System.out.printf("Passage PosY : %d\n",dis_object.get(i).PosY);


            }
            else if(dis_object.get(i) instanceof Monster)
            {
                for (int z = 0; z < room_list.size(); z++)
                {
                    if(room_list.get(z) instanceof Room)
                    {
                        if(((Monster)dis_object.get(i)).room == room_list.get(z).room)
                        {
                            startx = room_list.get(z).PosX;
                            starty = room_list.get(z).PosY;
                            break;
                        }
                    }
                }
                ch = new Char(dis_object.get(i).type.charAt(0));
                addObjectToDisplay(ch, dis_object.get(i).PosX + startx, dis_object.get(i).PosY + starty);
            }
            else if(dis_object.get(i) instanceof Player)
            {
                for (int z = 0; z < room_list.size(); z++)
                {
                    if(room_list.get(z) instanceof Room)
                    {
                        if(((Player)dis_object.get(i)).room == room_list.get(z).room)
                        {
                            startx = room_list.get(z).PosX;
                            // playerX = startx;
                            starty = room_list.get(z).PosY;
                            // playerY = starty;
                            break;
                        }
                    }
                }
                ch = new Char('@');
                // addObjectToDisplay(ch, dis_object.get(i).PosX + startx, dis_object.get(i).PosY + starty);
                // for(int k = 0; k < usedX.size()-1; k++)
                // {
                //     if(dis_object.get(i).PosX + startx == usedX.get(k) && dis_object.get(i).PosY + starty == usedY.get(k)){
                        playerX = dis_object.get(i).PosX + startx;
                        playerY = dis_object.get(i).PosY + starty;
                //         break;
                //     }
                    
                // }
                
            }
            else if(dis_object.get(i) instanceof Sword)
            {
                ch = new Char(')');
                /*for (int z = 0; z < room_list.size(); z++)
                {
                    if(room_list.get(z) instanceof Room)
                    {
                        if(((Sword)dis_object.get(i)).room == room_list.get(z).room)
                        {
                            startx = room_list.get(z).PosX;
                            starty = room_list.get(z).PosY;
                            break;
                        }
                    }
                }*/
                addObjectToDisplay(ch, dis_object.get(i).PosX + startx, dis_object.get(i).PosY + starty);
            }
            else if(dis_object.get(i) instanceof Scroll)
            {
                ch = new Char('?');
                addObjectToDisplay(ch, dis_object.get(i).PosX, dis_object.get(i).PosY);
            }
            else if(dis_object.get(i) instanceof Armor)
            {
                ch = new Char(']');
                addObjectToDisplay(ch, dis_object.get(i).PosX, dis_object.get(i).PosY);
            }
            
        }
        // for(int i = 0; i < usedX.size()-1; i++)
        // {
        //     if(playerX == usedX.get(i) && playerY == usedY.get(i)){
                ch = new Char('@');
                addPlayer(ch, playerX, playerY);
                
            //     break;
            // }
            
            
        // }
        terminal.repaint();
            

    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y)
    {
        if ((0 <= x) && (x < objectGrid.length))
        {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    public void addPlayer(Char ch, int x, int y)
    {
        if ((0 <= x) && (x < objectGrid.length))
        {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].getChar();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
/*
    public void update_stats(Monster monster, Player player, CreatureAction action)
    {
        if ((0 <= x) && (x < objectGrid.length))
        {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    public void fight(Monster monster, Player player, CreatureAction action)
    {
        if ((0 <= x) && (x < objectGrid.length))
        {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    public void compare_distance(Monster monster, Player player,)
    {
        if ((0 <= x) && (x < objectGrid.length))
        {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }*/

}


