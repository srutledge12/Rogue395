import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;

    public KeyStrokePrinter(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
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
        int newX;
        int newY;
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
                // newX = displayGrid.dis_object.get(i).PosX + dx;
                // displayGrid.dis_object.get(i).setPosX(newX);
                // newY = displayGrid.dis_object.get(i).PosY + dy;
                // displayGrid.dis_object.get(i).setPosY(newY);
                break;
            }
        }
        
        displayGrid.initializeDisplay();
        
        // return(PosX);
    }

    private boolean processInput() {

        char ch;

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
                    move(0,1);
                }
                else if(ch == 'j'){
                    move(0,-1);
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
