package game;

import tools.Armor;
import tools.Weapon;

public class Inventory {
    private boolean water;
    private boolean food;
    private boolean firewood;
    private Weapon weapon;
    private Armor armor;

    public Inventory(){
        this.weapon = new Weapon("Punch", 0, 0, 0);
        this.armor = new Armor("T-Shirt", 0, 0, 0);
    }

    public void treasureInfo(){
        System.out.println("WATER: "+ ((isWater()) ? "Found" : "Not Found"));
        System.out.println("FOOD: "+ ((isFood()) ? "Found" : "Not Found"));
        System.out.println("FIREWOOD: "+ ((isFirewood()) ? "Found" : "Not Found"));
    }
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }


}
