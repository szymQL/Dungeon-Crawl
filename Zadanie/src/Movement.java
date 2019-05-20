import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Movement implements Observable{
    private int asset;
    private int value;                                  //opisuje na jakim polu jestesmy
    private int end;                                    // sluzy do sprawdzenia czy to juz koniec planszy
    private int[] userPosition = new int[2];
    private int[][] map;
    private int userKierunek = 0;
    private int mapSize = 25;
    private Random r = new Random();
    private int randomSeed = r.nextInt(1000000) + 1;
    private SeedGenerator seedGenerator;
    private ArrayList<ImageIcon> assets = new ArrayList<>(50);
    private ImageIcon image = new ImageIcon();
    private Observer observer;
    private int cheat = 0;

    public Movement(){
        userPosition[0] = mapSize/2;
        userPosition[1] = mapSize/2;
        seedGenerator = new SeedGenerator(mapSize,15, randomSeed);
        map = new int[mapSize][mapSize];
        map = seedGenerator.generateEmptyMap();
        seedGenerator.mapAlgorithm(map);
        map[userPosition[0]][userPosition[1]] = 9; // pozycja gracza
        seedGenerator.printMap(map);
        assetInitialize();
    }
    public Movement(String seed, int userMapSize){
        this.mapSize = userMapSize;
        userPosition[0] = userMapSize/2;
        userPosition[1] = userMapSize/2;
        seedGenerator = new SeedGenerator(userMapSize,15, seed);
        map = new int[mapSize][mapSize];
        map = seedGenerator.generateEmptyMap();
        seedGenerator.mapAlgorithm(map);
        map[userPosition[0]][userPosition[1]] = 9; // pozycja gracza
        seedGenerator.printMap(map);
        assetInitialize();
    }
    public Movement(int userMapSize){
        userPosition[0] = userMapSize/2;
        userPosition[1] = userMapSize/2;
        seedGenerator = new SeedGenerator(userMapSize,15, randomSeed);
        map = new int[mapSize][mapSize];
        map = seedGenerator.generateEmptyMap();
        seedGenerator.mapAlgorithm(map);
        map[userPosition[0]][userPosition[1]] = 9; // pozycja gracza
        seedGenerator.printMap(map);
        assetInitialize();
    }
    public void assetInitialize(){
        assets.add(new ImageIcon(getClass().getResource("image/black.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset1.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset2.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset3.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset4.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset5.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset6.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset7.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset8.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset9.png")));
        assets.add(new ImageIcon(getClass().getResource("image/asset10.png")));
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(13, new ImageIcon(getClass().getResource("image/asset13.png")));
        assets.add(14, new ImageIcon(getClass().getResource("image/asset14.png")));
        assets.add(15, new ImageIcon(getClass().getResource("image/asset15.png")));
        assets.add(16, new ImageIcon(getClass().getResource("image/asset16.png")));
        assets.add(17, new ImageIcon(getClass().getResource("image/asset17.png")));
        assets.add(18, new ImageIcon(getClass().getResource("image/asset18.png")));
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(26, new ImageIcon(getClass().getResource("image/asset26.png")));
        assets.add(27, new ImageIcon(getClass().getResource("image/asset27.png")));
        assets.add(28, new ImageIcon(getClass().getResource("image/asset28.png")));
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(36, new ImageIcon(getClass().getResource("image/asset36.png")));
        assets.add(37, new ImageIcon(getClass().getResource("image/asset37.png")));
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(new ImageIcon());
        assets.add(46, new ImageIcon(getClass().getResource("image/asset46.png")));
        assets.add(47, new ImageIcon(getClass().getResource("image/asset47.png")));
        assets.add(48, new ImageIcon(getClass().getResource("image/canwegodeeper.png")));
    }                   //metoda inicjalizujaca nasze assety

    public void Left(){
        userKierunek--;
        if(userKierunek <0 )
            userKierunek = 3;
        if(cheat == 0 || cheat == 4)                     //fragmenty dotyczace kodu
            cheat++;
        else
            cheat = 0;
    }
    public void Right(){
        userKierunek++;
        if (userKierunek >3)
            userKierunek = 0;
        if(cheat == 1 || cheat == 3)
            cheat++;
        else
            cheat = 0;
    }

    public ImageIcon SetImage() {
        asset = generateAsset();
        image = assets.get(asset);
        return image;
    }
    public void Forward()throws Ending{
        end = 0;

        if(cheat == 2)
            cheat++;
        else
            cheat = 0;

        int[] temp = new int[3];
        temp[0] = userPosition[0];
        temp[1] = userPosition[1];
        switch (userKierunek){
            case 0:                 //przod
                if(map[userPosition[0] - 1][userPosition[1]] != 0) {
                    if(map[userPosition[0] - 1][userPosition[1]] == 3){
                        end = map[userPosition[0] - 1][userPosition[1]];
                        throw new Ending();}
                    map[userPosition[0]][userPosition[1]] = 1;
                    userPosition[0]--;
                    value = map[userPosition[0]][userPosition[1]];
                    map[userPosition[0]][userPosition[1]] = 9;
                }
                break;
            case 1:                 //prawo
                if(map[userPosition[0]][userPosition[1]+1] != 0) {
                    if(map[userPosition[0]][userPosition[1]+1] == 3){
                        end = map[userPosition[0]][userPosition[1]+1];
                        throw new Ending();}
                    map[userPosition[0]][userPosition[1]] = 1;
                    userPosition[1]++;
                    value = map[userPosition[0]][userPosition[1]];
                    map[userPosition[0]][userPosition[1]] = 9;
                }
                break;
            case 2:                 //tyl
                if(map[userPosition[0] + 1][userPosition[1]] != 0) {
                    if(map[userPosition[0] + 1][userPosition[1]] == 3){
                        end = map[userPosition[0] + 1][userPosition[1]];
                        throw new Ending();}
                    map[userPosition[0]][userPosition[1]] = 1;
                    userPosition[0]++;
                    value = map[userPosition[0]][userPosition[1]];
                    map[userPosition[0]][userPosition[1]] = 9;
                }
                break;
            case 3:                  //lewo
                if(map[userPosition[0]][userPosition[1]-1] != 0) {
                    if(map[userPosition[0]][userPosition[1]-1] == 3) {
                        end = map[userPosition[0]][userPosition[1]-1];
                        throw new Ending();
                    }
                    map[userPosition[0]][userPosition[1]] = 1;
                    userPosition[1]--;
                    value = map[userPosition[0]][userPosition[1]];
                    map[userPosition[0]][userPosition[1]] = 9;
                }
                break;
        }
        seedGenerator.printMap(map);
        observer.positionChange(userPosition, temp, userKierunek, value, end);
    }
    public int generateAsset() {                         //funkcja sprawdza okolice pokoju do ktorego chcemy sie udac
        int temp[] = new int[2];                           // na tej podstawie zwraca nr assetu ktory zostanie wyswietlony
        temp[0] = userPosition[0];
        temp[1] = userPosition[1];                          //chodzi o wyswietlanie nabiezaco elementow lochu przed graczem
        int left = 0;
        int right = 0;
        int left_close = 0;
        int right_close = 0;
        int forw2 = 0;
        int forw = 0;
        switch (userKierunek) {
            case 0:
                temp[0]--;
                if (map[temp[0] - 1][temp[1]] != 0) {     //sprawdza polnoc od pokoju
                    forw2++;

                }
                if(map[temp[0]][temp[1]] != 0) {
                    forw++;
                }
                if (map[temp[0]][temp[1] - 1] != 0) {          //sprawdza lewo
                    left++;
                }
                if (map[temp[0]+1][temp[1] - 1] != 0) {          //sprawdza lewo blisko
                    left_close++;
                }
                if (map[temp[0]][temp[1] + 1] != 0) {          //sprawdza prawo
                    right++;
                }
                if (map[temp[0]+1][temp[1] + 1] != 0) {          //sprawdza prawo
                    right_close++;
                }
                break;
            case 1:
                temp[1]++;
                if (map[temp[0] - 1][temp[1]] != 0) {     //sprawdza polnoc od pokoju
                    left++;
                }
                if (map[temp[0] - 1][temp[1]-1] != 0) {     //sprawdza polnoc od pokoju
                    left_close++;
                }
                if (map[temp[0] + 1][temp[1]] != 0) {          //sprawdza dol
                    right++;
                }
                if (map[temp[0] + 1][temp[1]-1] != 0) {          //sprawdza dol
                    right_close++;
                }
                if (map[temp[0]][temp[1] + 1] != 0) {          //sprawdza prawo
                    forw2++;
                }
                if(map[temp[0]][temp[1]] != 0) {
                    forw++;
                }
                break;
            case 2:
                temp[0]++;
                if (map[temp[0]][temp[1] - 1] != 0) {     //sprawdza lewo od pokoju
                    right++;
                }
                if (map[temp[0]-1][temp[1] - 1] != 0) {     //sprawdza lewo od pokoju
                    right_close++;
                }
                if (map[temp[0] + 1][temp[1]] != 0) {          //sprawdza dol
                    forw2++;
                }
                if (map[temp[0]][temp[1] + 1] != 0) {          //sprawdza prawo
                    left++;
                }
                if (map[temp[0]-1][temp[1] + 1] != 0) {          //sprawdza prawo
                    left_close++;
                }
                if(map[temp[0]][temp[1]] != 0) {
                    forw++;
                }
                break;
            case 3:
                temp[1]--;
                if (map[temp[0] - 1][temp[1]] != 0) {     //sprawdza polnoc od pokoju
                    right++;
                }
                if (map[temp[0] - 1][temp[1]+1] != 0) {     //sprawdza polnoc od pokoju
                    right_close++;
                }
                if (map[temp[0] + 1][temp[1]] != 0) {          //sprawdza dol
                    left++;
                }
                if (map[temp[0] + 1][temp[1]+1] != 0) {          //sprawdza dol
                    left_close++;
                }
                if (map[temp[0]][temp[1] - 1] != 0) {          //sprawdza lewo
                    forw2++;
                }
                if(map[temp[0]][temp[1]] != 0) {
                    forw++;
                }
                break;
        }
        if (left_close == 1 && forw == 0 && right_close == 0)
            asset = 13;
        if (left_close == 0 && forw == 0 && right_close == 1)
            asset = 14;
        if (left_close == 1 && forw == 0 && right_close == 1)
            asset = 15;
        if (left_close == 0 && forw == 1 && right_close == 1 && left == 0 && right == 0 && forw2 == 1)
            asset = 16;
        if (left_close == 1 && forw == 1 && right_close == 0 && left == 0 && right == 0 && forw2 == 1)
            asset = 17;
        if (left_close == 1 && forw == 1 && right_close == 1 && left == 0 && right == 0 && forw2 == 1)
            asset = 18;
        if (left_close == 0 && forw == 1 && right_close == 1 && left == 0 && right == 0 && forw2 == 0)
            asset = 26;
        if (left_close == 1 && forw == 1 && right_close == 0 && left == 0 && right == 0 && forw2 == 0)
            asset = 27;
        if (left_close == 1 && forw == 1 && right_close == 1 && left == 0 && right == 0 && forw2 == 0)
            asset = 28;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 0 && right == 0 && forw2 == 1)
            asset = 1;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 0 && right == 0 && forw2 == 0)
            asset = 2;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 1 && right == 0 && forw2 == 0)
            asset = 3;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 0 && right == 1 && forw2 == 0)
            asset = 4;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 1 && right == 1 && forw2 == 0)
            asset = 5;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 0 && right == 1 && forw2 == 1)
            asset = 6;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 1 && right == 0 && forw2 == 1)
            asset = 7;
        if (left_close == 0 && forw == 1 && right_close == 0 && left == 1 && right == 1 && forw2 == 1)
            asset = 8;
        if (left_close == 0 && forw == 0 && right_close == 0)
            asset = 9;
        if (left_close == 0 && forw == 1 && right_close == 1 && left == 1 && right == 0 && forw2 == 1)
            asset = 36;
        if (left_close == 1 && forw == 1 && right_close == 0 && left == 0&& right == 1 && forw2 == 1)
            asset = 37;
        if (left_close == 0 && forw == 1 && right_close == 1 && left == 1 && right == 0 && forw2 == 0)
            asset = 46;
        if (left_close == 1 && forw == 1 && right_close == 0 && left == 0&& right == 1 && forw2 == 0)
            asset = 47;
        if(value == 3)
            asset = 48;

        observer.positionChange(userPosition, userPosition, userKierunek, value, end);
        return asset;
    }

    public int[][] getMap(){return map;}

    public int getValue(){return value;}

    @Override
    public void subscribe(Observer o) {
        this.observer = o;
    }

    public int getMapSize() {
        return mapSize;
    }

    @Override
    public void unsubscribe(Observer o) {
        if(this.observer != null)
            this.observer = null;
    }
    public int getCheat(){return cheat;}

}
