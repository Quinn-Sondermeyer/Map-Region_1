import java.util.*;

public class Map {
    private final List<Territory> territories;
    private static final String[] InputTerritories = new String [] {
            "Alaska","Alberta","Central America","Eastern United States","Greenland","Northwest Territory","Ontario","Quebec","Western United States",
            "Argentina", "Brazil","Peru","Venezuela",
            "Great Britain","Iceland","Northern Europe","Scandinavia","Southern Europe","Ukraine","Western Europe",
            "Congo","East Africa","Egypt","Madagascar","North Africa","South Africa",
            "Afghanistan","China","India","Irkutsk","Japan","Kamchatka","Middle East","Mongolia","Siam","Siberia","Ural","Yakutsk",
            "Eastern Australia","Indonesia","New Guinea","Western Australia"};

    private static final int[][] listOfTerritoryNeighbors = new int [][] {
            {1,5,31},  {0,5,6,8},  {8,3,12},  {2,8,6,7},  {5,6,8,14},  {0,1,4,6},  {1,3,4,5,7,8},  {3,4,6},  {1,2,3,6},
            {10,11},  {9,11,12,24},  {9,10,12},  {10,11,2},
            {14,15,16,19},  {4,13,16},  {13,16,17,18,19},  {13,14,15,18},  {15,18,19,24,22,32},  {15,16,17,26,31,36},  {13,15,17,24},
            {21,24,26},  {20,22,23,25,26,32},  {21,24,17,32},  {21,25},  {20,21,22,19,17,10},  {20,21,23},
            {27,28,32,36,18},  {26,28,33,34,35,36},  {26,27,32,34},  {31,33,35,37},  {31,33},  {29,30,33,36},  {26,28,18,17,22,21},  {27,29,30,31,35},  {27,28,39},  {27,29,33,36,37}, {26,27,35,18}, {29,31,35},
            {40,41},  {40,41,34},  {38,39,41},  {38,39,40}
    };

    public Map(List<Player> Players, int noOfUnitsPerPlayer){
        this.territories = new LinkedList<>();
        generateMap(Players, noOfUnitsPerPlayer);
    }

    public List<Territory> getTerritories() {
        return this.territories;
    }

    public void addTerritory(Territory newTerritory){
        this.territories.add(newTerritory);
    }

    private void generateMap(List<Player> players, int noOfUnitsPerPlayer){
        Random rand = new Random();

        for (String TerritoryName : InputTerritories){
            Player randomPlayer = players.get(rand.nextInt(players.size()));
            if(randomPlayer.getTerritories().size() <= (InputTerritories.length / players.size() + 1)) {
                this.territories.add(new Territory(TerritoryName, randomPlayer, 1));
            }
        }

        for (int i = 0; i < listOfTerritoryNeighbors.length; i++) {
            for (int j = 0; j < (listOfTerritoryNeighbors[i].length); j++) {
                this.territories.get(i).addNeighbors(this.territories.get(listOfTerritoryNeighbors[i][j]));
            }
        }

        for (Player player: players) {
            int noOfUnits = noOfUnitsPerPlayer - 1;
            List<Territory> playerTerritories = player.getTerritories();
            while (noOfUnits > 0) {
                noOfUnits = rand.nextInt(3) + 1;
                Territory randomPlayerTerritory = playerTerritories.get(rand.nextInt(playerTerritories.size()));
                if (noOfUnits + player.getTotalNumUnits() <= noOfUnitsPerPlayer) {
                    randomPlayerTerritory.updateNumUnits(noOfUnits);
                    noOfUnits--;
                }
            }
        }
    }
    public String toString() {
        String returnString = "World Layout: \n";
        for (Territory t :territories){
            returnString += t.getName() + "Owned by: " + t.getOwner().getName() + "Has " + t.getNumUnits() + " Units" + "\n Has Neighbors :";
            for (Territory n: t.getNeighbors()) {
                returnString += "  " + n.getName();
            }
            returnString += "\n";
        }

        return returnString;
    }
}
