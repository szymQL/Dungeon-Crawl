import java.util.Random;

public abstract class Enemy {
    private String name;
    private int health;
    private int maxHP;
    private int minDamage,maxDamage;
    private int meinLeben;

    public Enemy(String name, int hp, int minDmg, int maxDmg){
        this.name = name;
        this.maxHP = hp;
        this.minDamage = minDmg;
        this.maxDamage = maxDmg;
    }

    public int Attack(){
        Random r=new Random();
        int dmg=r.nextInt((getMaxDamage()-getMinDamage())+1)+getMinDamage();
        System.out.println("Damage taken: "+dmg);
        return dmg;
    }
    public void ItLives(int damage) {
        int hp = getHealth();
        hp = hp - damage;
        setHealth(hp);
        if (hp <= 0){
            System.out.println("Monster is dead");
        }
    }

    public void setMeinLeben(int x){
        meinLeben = x;
        if(x == 1)
            setHealth(maxHP);

    }
    public int getMeinLeben(){return meinLeben;}
    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getMinDamage(){return minDamage;}
    public int getMaxDamage(){return maxDamage;}
    public void setHealth(int hp){health = hp;}
}
