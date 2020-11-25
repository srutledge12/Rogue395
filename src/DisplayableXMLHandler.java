// package src;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// import apple.laf.JRSUIState.ScrollBarState;

import java.util.*;
// package src;

//import javax.lang.model.util.ElementScanner14;
// import javax.swing.Action;

public class DisplayableXMLHandler extends DefaultHandler {
    // the two lines that follow declare a DEBUG flag to control
// debug print statements and to allow the class to be easily
// printed out. These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";

    // data can be called anything, but it is the variables that
// contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
// Student objects to this array so that it has a list of
// all specified students. Had we covered containers at the
// time I put this file on the web page I would have made this
// an ArrayList of Students (ArrayList<Student>) and not needed
// to keep tract of the length and maxStudents. You should use
// an ArrayList in your project.
    private ArrayList<Displayable> dis_object = new ArrayList<Displayable>();
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Passage> passageList = new ArrayList<Passage>();
    private ArrayList<Creature> creatureList = new ArrayList<Creature>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
// private ArrayList<int> pLocation = new ArrayList<Item>();


    // The XML file contains a list of Students, and within each
// Student a list of activities (clubs and classes) that the
// student participates in. When the XML file initially
// defines a student, many of the fields of the object have
// not been filled in. Additional lines in the XML file
// give the values of the fields. Having access to the
// current Student and Activity allows setters on those
// objects to be called to initialize those fields.
    private Displayable displayableBeingParsed = null;
    private Dungeon dungeonBeingParsed = null;
    // private Room roomBeingParsed = null;
    private Passage passageBeingParsed = null;
    // private Item itemBeingParsed = null;
// private Monster monsterBeingParsed = null;
// private Player playerBeingParsed = null;
    private Action actionBeingParsed = null;



    // The bX fields here indicate that at corresponding field is
// having a value defined in the XML file. In particular, a
// line in the xml file might be:
// <instructor>Brook Parke</instructor>
// The startElement method (below) is called when <instructor>
// is seen, and there we would set bInstructor. The endElement
// method (below) is called when </instructor> is found, and
// in that code we check if bInstructor is set. If it is,
// we can extract a string representing the instructor name
// from the data variable above.
    private boolean bRoom = false;
    private boolean bPassage = false;
    private boolean bDungeon = false;
    private boolean bCreature = false;
    private boolean bMonster = false;
    private boolean bPlayer = false;
    private boolean bItem = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bwidth = false;
    private boolean bhp = false;
    private boolean bhpMoves = false;
    private boolean bheight = false;
    private boolean bvisible = false;
    private boolean bmaxhit = false;
    private boolean btype = false;
    private boolean bIntValue = false;
    private boolean bAction = false;
    private boolean bactionIntValue = false;
    private boolean bactionCharValue = false;
    private boolean bArmor = false;
    private boolean bScroll = false;
    private boolean bSword = false;



    // Used by code outside the class to get the list of Student objects
// that have been constructed.
    public ArrayList<Displayable> getDis_objects() {
        return dis_object;
    }
    public ArrayList<Room> getRooms() {
        return roomList;
    }
    public ArrayList<Passage> getPassages() {
        return passageList;
    }
    public ArrayList<Creature> getCreatures() {
        return creatureList;
    }
    public ArrayList<Item> getItems() {
        return itemList;
    }


    // A constructor for this class. It makes an implicit call to the
// DefaultHandler zero arg constructor, which does the real work
// DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
// imported above, and we don't need to write it. We get its
// functionality by deriving from it!
    public DisplayableXMLHandler() {
    }

    // startElement is called when a <some element> is called as part of
// <some element> ... </some element> start and end tags.
// Rather than explain everything, look at the xml file in one screen
// and the code below in another, and see how the different xml elements
// are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }
        if (qName.equalsIgnoreCase("Dungeon"))
        {

            String name = attributes.getValue("name");
// String type = attributes.getValue("type");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameheight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeonBeingParsed = new Dungeon(name,width,gameheight, topHeight, bottomHeight);
            dis_object.add(displayableBeingParsed);
        }
        else if (qName.equalsIgnoreCase("Room"))
        {
            String name = attributes.getValue("room");
// String type = attributes.getValue("type");
            Room room1 = new Room(name);
            room1.setId(Integer.parseInt(name.toString()));
            roomList.add(room1);
            dungeonBeingParsed.addRoom(room1);
            displayableBeingParsed = (Room) room1;
            dis_object.add(displayableBeingParsed);
// roomList.add(displayableBeingParsed);
            bRoom = true;
        }
        else if (qName.equalsIgnoreCase("Passage"))
        {
            String room1 = attributes.getValue("room1");
            String room2 = attributes.getValue("room2");
            Passage passage1 = new Passage();
            passage1.setId(Integer.parseInt(room1.toString()), Integer.parseInt(room2.toString()));
            passageList.add(passage1);
            dungeonBeingParsed.addPassage(passage1);
            displayableBeingParsed = (Passage) passage1;
            dis_object.add(displayableBeingParsed);
            bPassage = true;
        }
        else if (qName.equalsIgnoreCase("ItemAction"))
        {
            String name = attributes.getValue("name");
            actionBeingParsed = new ItemAction( (Item) displayableBeingParsed);
            dis_object.add(displayableBeingParsed);
            ((Item) displayableBeingParsed).setAction((ItemAction) actionBeingParsed);
            bItem = true;
        }

        else if (qName.equalsIgnoreCase("Armor"))
        {
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            Armor armor1 = new Armor(name);
// Item item = new Armor();
            armor1.setName(name);
            armor1.setID(Integer.parseInt(room.toString()), Integer.parseInt(serial.toString()));
            displayableBeingParsed = armor1;
            dis_object.add(displayableBeingParsed);
            bArmor = true;
// bItem = true;
        }
        else if (qName.equalsIgnoreCase("CreatureAction"))
        {
            String name = attributes.getValue("name");
            if(displayableBeingParsed != null)
            {
                actionBeingParsed = new CreatureAction( (Creature) displayableBeingParsed);
                dis_object.add(displayableBeingParsed);
                ((Creature) displayableBeingParsed).setAction((CreatureAction) actionBeingParsed);
            }
            bCreature = true;
        }
        else if (qName.equalsIgnoreCase("Sword"))
        {

            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            Sword sword1 = new Sword(name);
// Item item = new Armor();
// armor1.setName(name);
            sword1.setID(Integer.parseInt(room.toString()), Integer.parseInt(serial.toString()));
            displayableBeingParsed = (Sword) sword1;
            dis_object.add(displayableBeingParsed);
            bSword = true;

        }
        else if (qName.equalsIgnoreCase("Scroll"))
        {

            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            Scroll scroll1 = new Scroll(name);
// Item item = new Armor();
// armor1.setName(name);
            scroll1.setID(Integer.parseInt(room.toString()), Integer.parseInt(serial.toString()));
            displayableBeingParsed = (Scroll) scroll1;
            dis_object.add(displayableBeingParsed);
            bArmor = true;

        }
// else if (qName.equalsIgnoreCase("Scroll"))
// {
// String type = attributes.getValue("name");
// Item item = new Scroll();
// displayableBeingParsed = item;
// dis_object.add(displayableBeingParsed);
// bItem = true;
// }
// else if (qName.equalsIgnoreCase("ActionMessage"))
// {
// Action action = new Action();
// dis_object.add(action);
// bAction = true;
// }
        else if (qName.equalsIgnoreCase("Monster"))
        {
// String type = attributes.getValue("type");
// String hp = attributes.getValue("hp");
// String maxhit = attributes.getValue("maxhit");
// displayableBeingParsed = new Monster();
// dis_object.add(displayableBeingParsed);

            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            Monster monster1 = new Monster();
// Item item = new Armor();
            monster1.setName(name);
            monster1.setID(Integer.parseInt(room.toString()), Integer.parseInt(serial.toString()));
            displayableBeingParsed = (Monster) monster1;
            dis_object.add(displayableBeingParsed);
            creatureList.add(monster1);
            bCreature = true;
            bMonster = true;
// bArmor = true;
        }
        else if (qName.equalsIgnoreCase("Player"))
        {
// displayableBeingParsed = new Player();
// // String type = attributes.getValue("type");
// dis_object.add(displayableBeingParsed);
// bCreature = true;
// bPlayer = true;

            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            Player player1 = new Player();
// Item item = new Armor();
            player1.setName(name);
            player1.setID(Integer.parseInt(room.toString()), Integer.parseInt(serial.toString()));
            displayableBeingParsed = (Player) player1;
            dis_object.add(displayableBeingParsed);
            creatureList.add(player1);
            bCreature = true;
            bPlayer = true;
        }
        else if (qName.equalsIgnoreCase("visible")) {
            bvisible = true;
        }
        else if (qName.equalsIgnoreCase("PosX")) {
            bPosX = true;
        }
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            bactionIntValue = true;
        }
        else if (qName.equalsIgnoreCase("actionCharValue")) {
            bactionCharValue = true;
        }
        else if (qName.equalsIgnoreCase("PosY")) {
            bPosY = true;
// System.out.println("--------------In Pos Y");
        }
        else if (qName.equalsIgnoreCase("hp")) {
// System.out.print("--------------In HP\n");
            bhp = true;
        }
        else if (qName.equalsIgnoreCase("maxhit")) {
// System.out.print("--------------In MaxHit\n");
            bmaxhit = true;
        }
        else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bIntValue= true;
        }
        else if (qName.equalsIgnoreCase("hpmoves")) {
            bhpMoves = true;
        }
        else if (qName.equalsIgnoreCase("width")) {
            bwidth = true;
        }
        else if (qName.equalsIgnoreCase("height")) {
            bheight = true;
        }
        else if (qName.equalsIgnoreCase("type")) {
            btype = true;
        }
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            bIntValue= true;
        }
        else if (qName.equalsIgnoreCase("actionMessage")) {
            bAction= true;
        }
        else
        {
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Displayable display = (Displayable) displayableBeingParsed;
        Action action;
// display = (Displayable) displayableBeingParsed;
        if(bvisible){displayableBeingParsed.setVisible(); bvisible = false;}
        else if(bPosX)
        {
            displayableBeingParsed.setPosX(Integer.parseInt(data.toString()));
            bPosX = false;
            if(bPassage)
            {
                ((Passage)displayableBeingParsed).set_coordinatesx(Integer.parseInt(data.toString()));
            }
        }
        else if(bPosY)
        {
            displayableBeingParsed.setPosY(Integer.parseInt(data.toString()));
            bPosY = false;
            if(bPassage)
            {
                ((Passage)displayableBeingParsed).set_coordinatesy(Integer.parseInt(data.toString()));
            }
        }
        else if(btype){displayableBeingParsed.setType(data.toString()); btype = false;}
        else if(bhp){displayableBeingParsed.setHp(Integer.parseInt(data.toString())); bhp = false;}
// else{display.setInvisible();}
        else if(bactionIntValue){actionBeingParsed.setIntValue(Integer.parseInt(data.toString())); bactionIntValue = false;}
        else if(bactionCharValue){actionBeingParsed.setCharValue((data.toString().charAt(0))); bactionCharValue = false;}
        else if(bmaxhit){displayableBeingParsed.setMaxHit(Integer.parseInt(data.toString())); bmaxhit = false;}
        else if(bhpMoves){displayableBeingParsed.setHpMove(Integer.parseInt(data.toString())); bhpMoves = false;}
        else if(bwidth){displayableBeingParsed.setWidth(Integer.parseInt(data.toString())); bwidth = false;}
        else if(bheight){displayableBeingParsed.setHeight(Integer.parseInt(data.toString())); bheight = false;}
// else if(bAction){
// action = (Action) displayableBeingParsed;
// action.setMessage(data.toString());
// bAction = false;
// }
// else if(bIntValue){displayableBeingParsed.setIntValue(Integer.parseInt(data.toString())); bIntValue = false;}
// else if(bheight){displayableBeingParsed.setHeight(Integer.parseInt(data.toString())); bheight = false;}
        else if (bPassage)
        {
            bPassage = false;
            displayableBeingParsed = null;
        }
// else if(bPosX)
// {
// display.setPosX(Integer.parseInt(data.toString()));
// bPosX = false;
// }
        else if(bMonster)
        {
// display = (Displayable) displayableBeingParsed;
// if(bPosX){display.setPosX(Integer.parseInt(data.toString())); bPosX = false;}
// if(bPosY){display.setPosY(Integer.parseInt(data.toString())); bPosY = false;}
// if(bvisible){display.setVisible(); bvisible = false;}
// // else{display.setInvisible();}
// if(bhp){display.setHp(Integer.parseInt(data.toString())); bhp = false;}
// if(bhpMoves){display.setHpMove(Integer.parseInt(data.toString())); bhpMoves = false;}
            bMonster = false;
            displayableBeingParsed = null;
        }

        else if (qName.equalsIgnoreCase("Armor"))
        {
            displayableBeingParsed = dis_object.get(dis_object.size() - 2);
        }

        else if(bPlayer)
        {
            bPlayer = false;
        }
        else if (bCreature)
        {
            if(data.toString() != null && displayableBeingParsed != null)
            {
                displayableBeingParsed.setType(data.toString());
            }
            bCreature = false;
        }
        else if (bItem)
        {
// System.out.print("ZZZ\n");
            displayableBeingParsed.setType(data.toString());

            bItem = false;
        }
        else if (bRoom)
        {
            bRoom = false;
            displayableBeingParsed = null;
        }
// else{
// Systen
// }
    }

    private void addDisplayable(Displayable _dis_object) {
        dis_object.add(_dis_object);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "DisplayableXMLHandler\n";
        for (int i = 0; i < dis_object.size(); i++) {
            str += dis_object.get(i).toString() + "\n";;
        }
        str += " bPosX: " + bPosX + "\n";
        str += " bPosY: " + bPosY + "\n";
        str += " bwidth: " + bwidth + "\n";
        str += " bheight: " + bheight + "\n";
        str += " bvisible: " + bvisible + "\n";
        str += " bhp: " + bhp + "\n";
        str += " bmaxhit: " + bmaxhit + "\n";
        str += " bhpMoves: " + bhpMoves + "\n";
        str += " dungeonBeingParsed: " + displayableBeingParsed.toString() + "\n";
        str += " roomBeingParsed: " + displayableBeingParsed.toString() + "\n";
        str += " passageBeingParsed: " + displayableBeingParsed.toString() + "\n";
        str += " itemBeingParsed: " + displayableBeingParsed.toString() + "\n";
        str += " monsterBeingParsed: " + displayableBeingParsed.toString() + "\n";
        str += " playerBeingParsed: " + displayableBeingParsed.toString() + "\n";
        return str;
    }
}