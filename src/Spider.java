import javax.swing.*;
import java.util.Random;

public class Spider extends Enemy{

    private int meinLeben;                                  //opisuje czy pajak zyje
    private int maxHP;

    public Spider(String name, int hp, int minDmg, int maxDmg){
        super(name,hp,minDmg,maxDmg);
        meinLeben = 1;
        maxHP = hp;
    }


}
