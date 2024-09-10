package projekt;
public class neighbour extends Spot{
    

    public neighbour(int x, int y, String string) {
        super(x, y);
        super.open = false;
        super.string = string;
        super.bombsNearby = 0;
    }

    

    
}
