package projekt;
public class Spot {
    protected boolean open;
    protected int xValue;
    protected int yValue;
    protected String string;
    protected int bombsNearby;

    public Spot(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.open = false;
        this.string = "x";
    }


    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getStringSpot() {
        return string; 
    }

    public void setStringSpot(String string) {
        this.string = string;
    }

    public int getXval() {
        return xValue;
    }

    public int getYval() {
        return yValue;
    }

    public int getBombsNearby() {
        return bombsNearby;
    }

    public void addBomb() {
        bombsNearby ++;
    }

    
}