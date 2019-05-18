import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random r = new Random();
        int seed = r.nextInt(1000000) + 1;
        SeedGenerator s = new SeedGenerator(20,15, seed);
        int[][] map = s.generateEmptyMap();
        s.mapAlgorithm(map);
        s.printMap(map);
    }
}
