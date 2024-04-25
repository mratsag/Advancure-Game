package tools;
public class Armor {
    private String type;
    private int id;
    private int defence;
    private int price;

    public Armor(String type, int id, int defence, int price) {
        this.type = type;
        this.id = id;
        this.defence = defence;
        this.price = price;
    }

    public static Armor[] armors(){
        Armor[] armorList = {
                new Armor("Light", 1, 1, 15),
                new Armor("Middle", 2, 3, 25),
                new Armor("Heavy", 3, 5, 40)
        };

        return armorList;
    }

    public static Armor getArmorObjById(int id){
        for(Armor a : Armor.armors()){
            if(a.getId() == id){
                return a;
            }
        }

        return null;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
