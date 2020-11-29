import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.ArrayList;
import asciiPanel.AsciiPanel;
public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    public int newX;
    public int newY;
    public ArrayList<Displayable> items = new ArrayList<Displayable>();
    public ArrayList<Displayable> inventory = new ArrayList<Displayable>();
    private static AsciiPanel terminal;
    

    public KeyStrokePrinter(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        pickUp();
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            // System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void move(int dx, int dy)
    {
        int length = (displayGrid.dis_object).size();
        
        for(int i = 0; i < length; i++)
        {
            if(displayGrid.dis_object.get(i) instanceof Player)
            {
                for(int k = 0; k < displayGrid.usedX.size()-1; k++)
                {
                    if(displayGrid.dis_object.get(i).PosX + dx + displayGrid.startx == displayGrid.usedX.get(k) && displayGrid.dis_object.get(i).PosY + dy + displayGrid.starty == displayGrid.usedY.get(k)){
                        newX = displayGrid.dis_object.get(i).PosX + dx;
                        displayGrid.dis_object.get(i).setPosX(newX);
                        newY = displayGrid.dis_object.get(i).PosY + dy;
                        displayGrid.dis_object.get(i).setPosY(newY);
                        break;
                    }
                    
                }
                // compare(newX, newY);
                break;
            }
            //If instance of Room
        }
        // terminal.clear();
        displayGrid.initializeDisplay();
        
        // return(PosX);
    }

    public void pickUp()
    {
        items.clear();
        int length = (displayGrid.dis_object).size();
        
        for(int i = 0; i < length; i++)
        {
            if(displayGrid.dis_object.get(i) instanceof Scroll || displayGrid.dis_object.get(i) instanceof Armor || displayGrid.dis_object.get(i) instanceof Sword)
            {
                System.out.println(displayGrid.dis_object.get(i));
                items.add(displayGrid.dis_object.get(i));
            }
        }

        int l1 = (items).size();
        
        for(int i = 0; i < l1; i++)
        {
            if(items.get(i).PosX == newX && items.get(i).PosY == newY)
            {
                inventory.add(items.get(i));
                displayGrid.dis_object.remove(items.get(i));
                break;
            }
        }
        displayGrid.printInventory(inventory);

    }

    public void inventory()
    {
        displayGrid.printInventory(inventory);
        
    }

    public void drop(int ind)
    {
        if(inventory.size() >= ind)
        {
            inventory.get(ind - 1).PosX = newX;
            inventory.get(ind - 1).PosY = newY;
            displayGrid.dis_object.add(inventory.get(ind - 1));
            inventory.remove(ind - 1);
            
        }
        displayGrid.printInventory(inventory);
        
    }

    // public void compare(int pX, int)

    private boolean processInput() {

        char ch;
        char prev;
        int ind;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                if (ch == 'X') {
                    System.out.println("got an X, ending input checking");
                    return false;
                } 
                else if(ch == 'h'){
                    move(-1,0);
                    
                }
                else if(ch == 'l'){
                    move(1,0);
                }
                else if(ch == 'k'){
                    move(0,-1);
                }
                else if(ch == 'j'){
                    move(0,1);
                }
                else if(ch == 'p'){
                    pickUp();
                }
                else if(ch == 'd'){
                    ind = 11;
                    while(inputQueue.peek() == null){}
                    
                    ch = inputQueue.poll();
                    if(Character.isDigit(ch))
                    {
                        ind = Integer.parseInt(String.valueOf(ch));
                    }
                    
                    // System.out.println(ind);
                    if(ind <= inventory.size())
                    {
                        System.out.println(ind);
                        drop(ind);
                    }
                    
                }
                else if(ch == 'i'){
                    inventory();
                }
                else if(ch == '?')
                {
                    displayGrid.help();
                }
                else if((ch == 'y' || ch == 'Y'))
                {
                    // displayGrid.endGame();
                    // System.out.println("Game Over");
                    // return(false);
                }
                else if(ch == 'E')
                {
                    // if(inputQueue.peek() == 'y' || inputQueue.peek() == 'Y')
                    // {
                    displayGrid.checkEnd();
                    while(inputQueue.peek() == null){}
                    ch = inputQueue.peek();
                    if(ch == 'y' || ch == 'Y')
                    {
                        displayGrid.endGame();
                        System.out.println("Game Over");
                        return(false);
                    }
                    // System.out.println(prev);
                    // }
                }
                    
                    
                else {
                    // System.out.println("character " + ch + " entered on the keyboard");
                }

                
            }
            
        }
        return true;
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }
}
