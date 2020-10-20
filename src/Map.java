import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Map {
    private List<Region> regions;
    private List<Player> players;
    private static final String[] InputRegions = new String [] {
                                            "Alaska","Alberta","Central America","Eastern United States","Greenland","Northwest Territory","Ontario","Quebec","Western United States",
                                            "Argentina", "Brazil","Peru","Venezuela",
                                            "Great Britain","Iceland","Northern Europe","Scandinavia","Southern Europe","Ukraine","Western Europe",
                                            "Congo","East Africa","Egypt","Madagascar","North Africa","South Africa",
                                            "Afghanistan","China","India","Irkutsk","Japan","Kamchatka","Middle East","Mongolia","Siam","Siberia","Ural","Yakutsk",
                                            "Eastern Australia","Indonesia","New Guinea","Western Australia"};

    private static final int[][] listOfRegionNeighbors = new int [][] {
            {1,5,31},  {0,5,6,8},  {8,3,12},  {2,8,6,7},  {5,6,8,14},  {0,1,4,6},  {1,3,4,5,7,8},  {3,4,6},  {1,2,3,6},
            {10,11},  {9,11,12,24},  {9,10,12},  {10,11,2},
            {14,15,16,19},  {4,13,16},  {13,16,17,18,19},  {13,14,15,18},  {15,18,19,24,22,32},  {15,16,17,26,31,36},  {13,15,17,24},
            {21,24,26},  {20,22,23,25,26,32},  {21,24,17,32},  {21,25},  {20,21,22,19,17,10},  {20,21,23},
            {27,28,32,36,18},  {26,28,33,34,35,36},  {26,27,32,34},  {31,33,35,37},  {31,33},  {29,30,33,36},  {26,28,18,17,22,21},  {27,29,30,31,35},  {27,28,39},  {27,29,33,36,37}, {26,27,35,18}, {29,31,35},
            {40,41},  {40,41,34},  {38,39,41},  {38,39,40}
            };
    public Map(List<Player> Players, int noOfUnitsPerPlayer){
        this.players = Players;
        regions = new LinkedList<>();
        generateMap(this.players, noOfUnitsPerPlayer);
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void addRegion(Region newRegion){
        regions.add(newRegion);
    }

    private void generateMap(List<Player> players, int noOfUnitsPerPlayer){
        Random rand = new Random();
        int playerChosen;

        int numOfTroops;
        int location;
        int totalTroops = noOfUnitsPerPlayer * players.size();


        for (String i : InputRegions){
                playerChosen = rand.nextInt(players.size());
                regions.add(new Region(i,players.get(playerChosen),1));
                players.get(playerChosen).changeUnits(-1);
            }


        for (int i = 0; i < listOfRegionNeighbors.length; i++) {
            for (int j = 0; j < (listOfRegionNeighbors[i].length); j++) {
                    regions.get(i).addNeighbors(regions.get(listOfRegionNeighbors[i][j]));

            }
        }

        for (Player p:players){
            p.setUnits(noOfUnitsPerPlayer);
        }
        while (totalTroops>0) {
            numOfTroops = rand.nextInt(3) +1;
            location = rand.nextInt(regions.size());
            if (regions.get(location).getOwner().getUnits() > 0) {
                regions.get(location).getOwner().changeUnits(-1*numOfTroops);
                regions.get(location).changeUnits(numOfTroops);
                totalTroops--;

            }

        }
    }
}


