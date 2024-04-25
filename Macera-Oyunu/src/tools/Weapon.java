package tools;

public class Weapon {
    private String name;
    private int id;
    private int damage;
    private int price;

    public Weapon(String name, int id, int damage, int price) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.price = price;
    }

    public static Weapon[] weapons(){
        Weapon[] weaponList = {
                new Weapon("Pistol", 1, 3, 35),
                new Weapon("Sword", 2, 2, 25),
                new Weapon("Rifle", 3, 7, 2)
        };

        return weaponList;
    }

    public static Weapon getWeaponObjByID(int id){
        for(Weapon w : Weapon.weapons()){
            if(w.getId() == id){
                return w;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
