import java.util.*;

public class Region {
    private int units;
    private Player owner;
    private List<Region> neighbors;
    private String name;

    public Region(String name, Player owner, int units){
        this.name = name;
        this.owner = owner;
        this.units = units;
        neighbors = new LinkedList<Region>();
    }

    public void setUnits(int numUnits){
        units = numUnits;
    }

    /*
    param additionalUnits: posative number to add units negative number to remove units
     */
    public int changeUnits(int additionalUnits){
        if (units + additionalUnits < 1){
            return 0;
        }
        else {
            units += additionalUnits;
            return units;
        }
    }

    public int getUnits() {
        return units;
    }

    public void setOwner(Player newOwner){
        owner = newOwner;
    }

    public Player getOwner() {
        return owner;
    }

    public void addNeighbors(Region neighbor){
        neighbors.add(neighbor);

    }

    public List<Region> getNeighbors() {
        return neighbors;
    }

    public String getName() {
        return name;
    }
}
