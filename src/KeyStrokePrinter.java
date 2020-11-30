import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.ArrayList;
import asciiPanel.AsciiPanel;
import java.util.Random;
public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    public int newX;
    public int newY;
    public ArrayList<Displayable> items = new ArrayList<Displayable>();
    public ArrayList<Displayable> inventory = new ArrayList<Displayable>();
    public ArrayList<Displayable> onPlayer = new ArrayList<Displayable>();
    private static AsciiPanel terminal;
    private int player_id = -1; /////////////////////////////////////////////////////////////////////////////////////
    private int player_x = -1; //////////////////////////////////////////////////////////////////////////////////////
    private int player_y = -1;//
    public Random ran = new Random();
    

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
        int scroll = 0;
        for(int i = 0; i < length - 1; i++)
        {
            // System.out.println(displayGrid.dis_object.get(i));
            if(scroll == 1 && displayGrid.dis_object.get(i) instanceof Scroll){
                displayGrid.dis_object.remove(displayGrid.dis_object.get(i));
                // items.remove(displayGrid.dis_object.get(i));
            }
            if(displayGrid.dis_object.get(i) instanceof Scroll)
            {
                scroll = 1;
            }

            if(displayGrid.dis_object.get(i) instanceof Player)
            {

                player_id = i;///////////////////////////////////////////////////////////////////////////////////////
                player_x = displayGrid.dis_object.get(i).PosX;/////////////////////////////////////////////////////////////
                player_y = displayGrid.dis_object.get(i).PosY;//

                for(int k = 0; k < displayGrid.usedX.size()-1; k++)
                {
                    if(displayGrid.dis_object.get(i).PosX + dx + displayGrid.startx == displayGrid.usedX.get(k) && displayGrid.dis_object.get(i).PosY + dy + displayGrid.starty == displayGrid.usedY.get(k)){
                        newX = displayGrid.dis_object.get(i).PosX + dx;
                        displayGrid.dis_object.get(i).setPosX(newX);
                        newY = displayGrid.dis_object.get(i).PosY + dy;
                        displayGrid.dis_object.get(i).setPosY(newY);
                        compare(newX, newY);
                        break;
                    }
                    
                }
                // compare(newX, newY);
                break;
            }
            //If instance of Room
        }
        // if(onPlayer.size() == 0)
        // {
        //     displayGrid.noArmor();
        // }
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
                // System.out.println("Scroll");
                System.out.println(displayGrid.dis_object.get(i));
                items.add(displayGrid.dis_object.get(i));
            }
        }
        // int scroll = 0;
        // for(int i = 0; i < items.size(); i++)
        // {
        //     if(scroll == 1 && displayGrid.dis_object.get(i) instanceof Scroll){
        //         displayGrid.dis_object.remove(displayGrid.dis_object.get(i));
        //         items.remove(displayGrid.dis_object.get(i));
        //     }
        //     if(displayGrid.dis_object.get(i) instanceof Scroll)
        //     {
        //         scroll = 1;
        //     }
        // }

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

    public void wear(Armor armor)
    {
        int length = (displayGrid.dis_object).size();
        
        for(int i = 0; i < length; i++)
        {
            if(displayGrid.dis_object.get(i) instanceof Player)
            {
                // Player a1 = (Player)displayGrid.dis_object.get(i);
                Player.setArmor(armor);
            }
            //If instance of Room
        }
        // terminal.clear();
        
        
        // return(PosX);
    }

    public void weild(Sword sword)
    {
        int length = (displayGrid.dis_object).size();
        
        for(int i = 0; i < length; i++)
        {
            if(displayGrid.dis_object.get(i) instanceof Player)
            {
                // Player a1 = (Player)displayGrid.dis_object.get(i);
                Player.setSword(sword);
            }
            //If instance of Room
        }
        // terminal.clear();
        
        
        // return(PosX);
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
                else if(ch == 'w')
                {
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
                        if(inventory.get(ind-1) instanceof Armor)
                        {
                            wear((Armor)inventory.get(ind-1));
                            displayGrid.worn((Armor)inventory.get(ind-1));
                            onPlayer.add(inventory.get(ind-1));
                            inventory.remove(inventory.get(ind-1));
                        }
                        else{
                            displayGrid.noArmor();
                        }
                    }
                }

                else if(ch == 'c')
                {
                    displayGrid.armorOff();
                    if(onPlayer.size() > 0)
                    {
                        inventory.add(onPlayer.get(0));
                        onPlayer.remove(onPlayer.get(0));
                    }
                }

                else if(ch == 'T')
                {
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
                        if(inventory.get(ind-1) instanceof Sword)
                        {
                            weild((Sword)inventory.get(ind-1));
                            displayGrid.weilded((Sword)inventory.get(ind-1));
                            // onPlayer.add(inventory.get(ind-1));
                            // inventory.remove(inventory.get(ind-1));
                        }
                        else{
                            displayGrid.notSword();
                        }
                    }
                }

                else if(ch == 'x')
                {
                    displayGrid.hallucinate();
                    // Char ch2 = new '@';
                    Char ch2 = new Char('@');
                    displayGrid.addPlayer(ch2, newX, newY);
                } 
                else if(ch == 'a')
                {
                    int length = (displayGrid.dis_object).size();
                    
                    for(int i = 0; i < length - 1; i++)
                    {
                        if(displayGrid.dis_object.get(i) instanceof Monster)
                        {
                            displayGrid.teleport(displayGrid.dis_object.get(i));
                            break;
                        }
                    }
        
                }

                else if(ch == 'r')
                {
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
                        if(inventory.get(ind-1) instanceof Scroll)
                        {
                            displayGrid.read((Scroll)inventory.get(ind-1));
                            // displayGrid.weilded((Sword)inventory.get(ind-1));
                            // onPlayer.add(inventory.get(ind-1));
                            // inventory.remove(inventory.get(ind-1));
                        }
                        else{
                            displayGrid.notScroll();
                        }
                    }
                }
                    
                else {
                    // System.out.println("character " + ch + " entered on the keyboard");
                }

                
            }
            
        }
        return true;
    }
    
    public void hit(Monster monster, Player player) ///////////////////////////////////////////////////////////////////
    {

        int hit_p = 0;
        int hit_m = 0;
        if(Player.sword == null)
        {
            hit_p = ran.nextInt(player.maxHit+1);
        }
        else
        {
            hit_p = ran.nextInt(player.maxHit + Player.sword.intValue + 1);
        }
        hit_m = ran.nextInt(monster.maxHit+1);
        if(Player.armor == null)
        {
            player.Hp -= hit_m;
        }
        else
        {
            player.Hp = player.Hp + Player.armor.intValue - hit_m;
        }
        monster.Hp -= hit_p;
        if(player.Hp <= 0)
        {
            displayGrid.endGame();
            System.out.println("Game Over");
            terminal.write("info:  ", 19, 35);
        }
        if(monster.Hp <= 0)
        {
            int length = (displayGrid.dis_object).size();
        
            for(int i = 0; i < length - 1; i++)
            {
                if(displayGrid.dis_object.get(i) == monster)
                {
                    System.out.println("D Monster");
                    displayGrid.dis_object.remove(i);
                    monster.PosX = 99;
                    monster.PosY = 99;
                    if(inventory.size() > 0)
                    {
                        drop(1);
                    }
                    
                    break;
                }
            }
            //System.out.println(monster.DA_list.get(monster.win).msg);
        }
        //System.out.println("Player: "+hit_m+" Monster: "+hit_p);
        //System.out.println(player.Hp);

        return;
    }

    public void compare(int newx, int newy)///////////////////////////////////////////////////////////////////////////
    {
        for(int x = 0; x < displayGrid.m_list.size(); x++)
        {
            if(((Monster) displayGrid.m_list.get(x)).s_x == newx && ((Monster) displayGrid.m_list.get(x)).s_y == newy)
            {
                hit(((Monster) displayGrid.m_list.get(x)), (Player) displayGrid.dis_object.get(player_id));
                displayGrid.dis_object.get(player_id).PosX = player_x;
                displayGrid.dis_object.get(player_id).PosY = player_y;
                break;

            }
        }
        return;
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
