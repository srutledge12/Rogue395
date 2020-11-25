import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Test implements Runnable
{
    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    public Test(int width, int height, ArrayList<Displayable> dis_object, ArrayList<Room> room_list)
    {
        displayGrid = new ObjectDisplayGrid(width,height,dis_object,room_list);
    }

    @Override
    public void run()
    {
        displayGrid.fireUp();
        for (int step = 1; step < WIDTH / 2; step *= 2)
        {
            for (int i = 0; i < WIDTH; i += step) {
                for (int j = 0; j < HEIGHT; j += step) {
                    //displayGrid.addObjectToDisplay(new Char('X'), i, j);
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            displayGrid.initializeDisplay();
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = null;
        switch (args.length) {
            case 1:
                // note that the relative file path may depend on what IDE you are
                // using.  This worked for NetBeans.
                fileName = "xmlFiles/" + args[0];
                break;
            default:
                System.out.println("java Test <xmlfilename>");
                return;
        }
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        ArrayList<Displayable> dis_object = null;
        ArrayList<Room> room_list = null;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DisplayableXMLHandler handler = new DisplayableXMLHandler();
            saxParser.parse(new File(fileName), handler);
            dis_object = handler.getDis_objects();
            room_list = handler.getRooms();
            /*for (int i = 0; i < dis_object.size(); i++) {
                System.out.println(dis_object.get(i));
            }*/
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
        Test test = new Test(WIDTH, HEIGHT, dis_object, room_list);
        Thread testThread = new Thread(test);
        testThread.start();

        test.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
        test.keyStrokePrinter.start();

        testThread.join();
        test.keyStrokePrinter.join();
    }
}