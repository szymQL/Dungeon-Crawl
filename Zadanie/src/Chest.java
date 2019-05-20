import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chest{
    List<Item> Chest;
    Item sword;
    Item longSword;
    Item barbarianSword;
    Item battleSword;

    Item chainMail;
    Item ironPlateArmor;
    Item blackSteelArmor;
    Item magicPlateArmor;
    private boolean opened;

    public Chest(){
        this.Chest=new ArrayList<Item>();
        this.sword=new Item(3,"Sword", 1,3,0);
        this.longSword=new Item(5,"Long Sword",2,3,0);
        this.barbarianSword=new Item(7,"Barbarian Sword",1,5,0);
        this.battleSword=new Item(9,"Battle Sword",3,6,0);
        this.chainMail=new Item(4,"Chain Mail",0,0,3);
        this.ironPlateArmor=new Item(6,"Iron Plate Armor",0,0,5);
        this.blackSteelArmor=new Item(8,"Black Steel Armor",0,0,8);
        this.magicPlateArmor=new Item(10,"Magic Plate Armor",0,0,10);
        opened=false;

        Chest.add(sword);
        Chest.add(longSword);
        Chest.add(barbarianSword);
        Chest.add(battleSword);
        Chest.add(chainMail);
        Chest.add(ironPlateArmor);
        Chest.add(blackSteelArmor);
        Chest.add(magicPlateArmor);
    }

    public Item drop() {
        Random r = new Random();
        opened=true;
        int x = r.nextInt(8);
        return Chest.get(x);
    }

    public Item cheat() {
        Random r = new Random();
        int x = r.nextInt(8);
        return Chest.get(x);
    }

    public boolean getOpened(){
        return opened;
    }
    public void setOpened(){
        this.opened=true;
    }
    public void setClosed(){this.opened = false;}

}
