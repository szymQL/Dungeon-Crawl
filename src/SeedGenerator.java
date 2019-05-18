import java.util.Random;
// 1-prawo, 2-lewo 3, prosto
public class SeedGenerator {                                            //klasa odpowiedzialna z tworzenie tablicy ktora program interpretuje jako mape, 0 to puste pola, zas kazda inna liczba ma swoja funkcje
    private Random generator;                                           //9 aktualna pozycja gracza
    private int mapSize;                                                //7 pole z przeciwnikiem
    private int minPath;                                                //5 pole z przedmiotem
    private int actualPath = 0;                                         //3 pole koniec poziomu
    private boolean chest = false;

    public SeedGenerator(){
        this.mapSize = 21;
        this.minPath = 10;
        this.generator = new Random(0);
    }

    public SeedGenerator(int mapSize, int minPath, String seed){
        this.mapSize = mapSize;
        this.minPath = mapSize;
        this.generator = new Random(Long.parseLong(seed,36));

    }
    public SeedGenerator(int mapSize, int minPath, int seed){
        this.mapSize = mapSize;
        this.minPath = mapSize;
        this.generator = new Random(Integer.toUnsignedLong(seed));
    }
    public void printMap(int[][] map){                              //wyswietla mape, pozycja gracza okreslana jako 9 jest pokazana za pomoca X
        for (int i = 0; i< mapSize; i++){
            for(int j = 0; j< mapSize; j++){
                if(map[i][j] == 0)
                    System.out.print(".");
                else if(map[i][j] == 9)
                    System.out.print("X");
                else {
                    System.out.print(map[i][j]);
                }

            }
            System.out.println();
        }
    }
    public int[][] generateEmptyMap(){                                             // tworzy tablice wypelniaona zerami

        int[][] map = new int[mapSize][mapSize];
        for (int i = 0; i< mapSize; i++){
            for(int j = 0; j< mapSize; j++){
                map[i][j] = 0;
            }
        }
        return map;
    }
    public int[][] mapAlgorithm(int[][] map){                                       //glowna metoda tej klasy, tworzy "labirynt" i korzysta z klasy cordControll
        map[mapSize /2][mapSize /2] = 1;
        while(actualPath < minPath) {
            //  0 0
            //   .
            //  0 X
            for (int i = mapSize / 2; i < mapSize - 2; i++) {
                for (int j = mapSize / 2; j < mapSize - 2; j++) {
                    int[] thisCord = {i, j};
                    cordControll(map, thisCord);
                }
            }
            //  0 X
            //   .
            //  0 0
            for (int i = mapSize / 2; i > 2; i--) {
                for (int j = mapSize / 2; j < mapSize - 2; j++) {
                    int[] thisCord = {i, j};
                    cordControll(map, thisCord);
                }
            }
            //  X 0
            //   .
            //  0 0
            for (int i = mapSize / 2; i > 2; i--) {
                for (int j = mapSize / 2; j > 2; j--) {
                    int[] thisCord = {i, j};
                    cordControll(map, thisCord);
                }
            }
            //  0 0
            //   .
            //  X 0
            for (int i = mapSize / 2; i < mapSize - 2; i++) {
                for (int j = mapSize / 2; j > 2; j--) {
                    int[] thisCord = {i, j};
                    cordControll(map, thisCord);
                }
            }
        }
        return accidentGenerator(map);
    }
    public void cordControll(int[][] map, int[] cord){                              //metoda sprawdzajaca czy czy dane pole sasiaduje z tylko innym
        int temp = 0;                                                               //aby stworzyc linowy korytarz bez petli lub "duzych pol"
        int tempCord = generator.nextInt(2);
        if(tempCord == 1) {
            if (map[cord[0]][cord[1] + 1] == 1)
                temp++;
            if (map[cord[0]][cord[1] - 1] == 1)
                temp++;
            if (map[cord[0] + 1][cord[1]] == 1)
                temp++;
            if (map[cord[0] - 1][cord[1]] == 1)
                temp++;
            if (temp == 1) {
                map[cord[0]][cord[1]] = 1;
                if(actualPath == minPath)
                    map[cord[0]][cord[1]] = 3;
                actualPath++;                                                       //"w losowym miejscu" tworzy koniec gry, implemenacja nastepuje w tym miejscu, aby zmniejszyc szanse, ze pole koncowe
            }                                                                       //bedzie tuz obok nas, choc nie dziala to idealnie, warto pomyslec nad nowa metoda lub wykorzystac accidentGenerator
        }
    }
    public int[][] accidentGenerator(int[][] map) {                                 //metoda odpowiedzialna za tworzenie zdarzen na w losowych miejscach na mapie
        for (int i = 0; i < mapSize; i++) {                                         //liczba 7 odpowiada przeciwnikowi, zas 7 okresla pole z przedmiotem do podniesiania
            for (int j = 0; j < mapSize; j++) {                                     //3 koniec gry
                if (map[i][j] == 1) {
                    int randomacc = generator.nextInt(10);
                    if (randomacc == 5)
                        map[i][j] = 5;

                    if(randomacc == 7)
                        map[i][j] = 7;

                }
            }
        }
        return map;
    }

}
