import java.util.*;

public class Territory {
    private int numUnits;
    private Player owner;
    private final List<Territory> neighborTerritories;
    private final String name;

    public Territory(String name, Player owner, int units){
        this.name = name;
        this.owner = owner;
        this.numUnits = units;
        this.neighborTerritories = new LinkedList<>();
        owner.addTerritory(this);
    }

    public void setUnits(int numUnits){
        this.numUnits = numUnits;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public void updateNumUnits(int numUnits) {
        this.numUnits += numUnits;
    }

    public void setOwner(Player newOwner){
        owner = newOwner;
    }

    public Player getOwner() {
        return owner;
    }

    public void addNeighbors(Territory neighbor){
        neighborTerritories.add(neighbor);

    }

    public List<Territory> getNeighbors() {
        return neighborTerritories;
    }

    public String getName() {
        return name;
    }


    public String toString(){
        String returnString = "";

        returnString += name + " Is owned by: " + owner.getName() + "Has " + numUnits + " Units" + "\n Has Neighbors :";
        for (Territory n: neighborTerritories) {
            returnString += "  " + n.getName();
        }
        return returnString;
    }
}
