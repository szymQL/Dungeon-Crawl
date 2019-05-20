public class Core {
    private boolean combat;
    Champion champion = new Champion();
    Chest chest=new Chest();
   // private Spider spider = new Spider("Spider", 30, 1, 3);
    private Movement movement;
    private Observer observer;
    private Enemy spider = null;
    String text = "";

    public Core(){
        this.movement = new Movement();
        this.combat = false;
    }
    public Core(String seed, String mapsize){
        if(seed.isEmpty())
            this.movement = new Movement(Integer.parseInt(mapsize));
        else
            this.movement = new Movement(seed, Integer.parseInt(mapsize));
        this.combat = false;
    }

    public void Canwefight() {
        if(movement.getValue() == 7){
            spider = new Spider("Spider", 30, 1, 3);
            setCombat(true);
            spider.setMeinLeben(1);

        }
    }

    public int Mortal(){
        int x = 0;
        if(combat) {
            spider.ItLives(champion.Attack());
            if (spider.getHealth() > 0) {
                x = spider.Attack();
                champion.ItHurts(x);
            }
        }
        if(spider.getHealth() <= 0)
            spider.setMeinLeben(0);
        return x;
    }

    public void Endoffight()throws Dead{
        if(combat){
            if (spider.getMeinLeben() == 0) {
                setCombat(false);
            }
            else if(champion.getHealth()<=1){
                throw new Dead();
            }else text = "Kill him!";
        }else text = "Nothing to attack";

    }
    public void canITakeIt(){
            if (movement.getValue() == 5) {
                if(!chest.getOpened()) {
                    Item dropped = chest.drop();
                    text = dropped.getName();
                    System.out.println(text);
                    if (dropped.getID() % 2 == 0) {
                        champion.setArmor(dropped);

                    } else {
                        champion.setWeapon(dropped);

                    }
                }else text = "Chest is opened";
            } else text = "Nothing to take";
    }

    public void moveRight(){
        if(!combat) {
            movement.Right();
        }
        else text = "You can not move now!";
    }

    public void moveLeft(){
        if(!combat) {
            movement.Left();
            if (movement.getCheat() == 5) {
                Item dropped = chest.drop();
                text = dropped.getName();
                System.out.println(text);
                if (dropped.getID() % 2 == 0) {
                    champion.setArmor(dropped);

                } else {
                    champion.setWeapon(dropped);

                }
            }
        }
        else text = "You can not move now!";
    }

    public void moveForward()throws Ending{
        if(!combat) {
            try {
                movement.Forward();
                chest.setClosed();
            }catch(Ending ending){
                throw new Ending();
            }
        }
        else text = "You can not move now!";
    }

    public void Heal(){
        champion.Heal();
        text = champion.getText();
    }


    public void setCombat(boolean canwe){this.combat = canwe;}
    public boolean getCombat(){return combat;}


    public Movement getMovement() {
        return movement;
    }
    public Champion getChampion(){
        return champion;
    }

    public Chest getChest(){
        return chest;
    }
    public String getText() {return text;}
}
