public class Item {
    private int ID,minAtk,maxAtk,def;
    private String name;

    public Item(int ID, String name, int minAtk, int maxAtk,int def){
        this.ID=ID;
        this.name=name;
        this.minAtk=minAtk;
        this.maxAtk=maxAtk;
        this.def=def;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getMinAtk() {
        return minAtk;
    }

    public void setMinAtk(int minAtk) {
        this.minAtk = minAtk;
    }

    public int getMaxAtk() {
        return maxAtk;
    }

    public void setMaxAtk(int maxAtk) {
        this.maxAtk = maxAtk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
