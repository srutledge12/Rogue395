// package src;

class Displayable {

    public int PosX;
    public int PosY;
    public int maxHit;
    public int hpMoves;
    public int hp;
    public String type;
    public int intValue;
    public int width;
    public int height;


    

    public Displayable(){
        System.out.println("Displayable");
    }

    void setInvisible(){
        System.out.println("setInvisible");
    }
    
    public void setVisible(){
        System.out.println("setVisible");
    }

    public void setMaxHit(int maxHit){
        this.maxHit = maxHit;
        System.out.printf("setMaxHit: %d\n", maxHit);
    }

    public void setHpMove(int hpMoves){
        this.hpMoves = hpMoves;
        System.out.printf("setHPMoves: %d\n", hpMoves);
    }

    public void setHp(int hp){
        System.out.println("In Dis HP ---------------");
        this.hp = hp;
        System.out.printf("setHp: %d\n", hp);
    }

    public void setType(String t){
        this.type = t;
        System.out.printf("setType: %s\n", t);
    }

    public void setIntValue(int v){
        this.intValue = v;
        System.out.printf("setIntValue: %d\n", v);
    }

    public void setPosX(int x){
        this.PosX = x;
        System.out.printf("setPosX: %d\n", x);
    }

    public void setPosY(int y){
        this.PosY = y;
        System.out.printf("setPosY: %d\n", y);
    }

    public void setWidth(int x){
        this.width = x;
        System.out.printf("setWidth: %d\n", x);
    }

    public void setHeight(int y){
        this.height = y;
        System.out.printf("setHeight: %d\n", y);
    }
}
