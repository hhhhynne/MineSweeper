package projekt;
import java.util.ArrayList;

public class safeSpot extends Spot {
    private ArrayList<Spot> neighbours = new ArrayList<>();


    public safeSpot(int x, int y) {
        super(x, y);
        super.open = false;
        super.string = " ";
    }

    public void addNeighbours(Spot spot) {
        neighbours.add(spot);
        

    }

    public ArrayList<Spot> getNeighbours() {
        return  neighbours;
    }


    
}
