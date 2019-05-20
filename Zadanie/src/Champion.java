import java.util.Random;

public class Champion {
    private int maxHealth;
    private int health;
    private Item weapon,armor;
    private int champAtk;
    private int potions;
    private String text = "";

    public Champion(){
        this.maxHealth = 100;
        this.health=maxHealth;
        this.champAtk=2;
        this.weapon=new Item(1,"Stick",0,1,0);
        this.armor=new Item(2,"Cloth Armor",0,0,1);
        this.potions = 3;
    }

    public int Attack(){
        int maxDmg=champAtk+weapon.getMaxAtk();
        int minDmg=champAtk+weapon.getMinAtk();
        Random r=new Random();
        int dmg=r.nextInt((maxDmg-minDmg)+1)+minDmg;
        System.out.println("Damage dealt: "+dmg);
        return dmg;
    }

    public void ItHurts(int damage){
        if(damage>=armor.getDef()) {
            health = health - (damage-armor.getDef());
        }else health = health-0;
        if(health <= 1){
            System.out.println("You died");
        }
    }

    public void Heal(){
        if(potions > 0 && health < 100) {
            potions--;
            health =  health + 25;
        }
        else
            text = "You cant heal right now";

    }

    public int getHealth(){return health;}
    public void setHealth(int hp){health = hp;}

    public void setArmor(Item armor){
        this.armor=armor;
    }
    public void setWeapon(Item weapon){
        this.weapon=weapon;
    }
    public String getText(){return text;};
}
