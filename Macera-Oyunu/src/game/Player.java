package game;

import heroes.Archer;
import heroes.GameChar;
import heroes.Knight;
import heroes.Samurai;
import tools.Armor;
import tools.Weapon;

import java.util.Scanner;

public class Player {
    private Inventory inventory;

    private String name;

    private int damage;
    private int health;
    private int defaultHealth;
    private int money;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        System.out.println();
        System.out.println("1 - Samurai:  <Damage: 5, Health: 21, Money: 15>");
        System.out.println("2 - Archer:\t  <Damage: 7, Health: 18, Money: 20>");
        System.out.println("3 - Knight:   <Damage: 8, Health: 24, Money: 5>");
        System.out.println("------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        // Kullanıcıdan karakter seçimini isteme
        int secim;
        do {
            System.out.print("Choose a hero (1-3): ");
            secim = scanner.nextInt();

            switch (secim){
                case 1:
                    initPlayer(new Samurai("Samurai", 1, 5, 21, 15));
                    break;
                case 2:
                    initPlayer(new Archer("Archer", 2, 7, 18, 20));
                    break;
                case 3:
                    initPlayer(new Knight("Knight", 3, 8, 24, 5));
            }

        } while (secim < 1 || secim > 3);
    }

    public void initPlayer(GameChar gameChar){
        this.setName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());

        this.defaultHealth = gameChar.getHealth();
    }

    public void playerInfo(){
        System.out.println("|||||+PLAYER INFO+|||||");
        System.out.println("Gun: " + this.getInventory().getWeapon().getName()
                + ", Armor: " + this.getInventory().getArmor().getType()
                + ", Defence: " + this.getInventory().getArmor().getDefence()
                + ", Damage: " + this.getTotalDamage()
                + ", Health: " + this.getHealth()
                + ", Money: " + this.getMoney());

        return;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        if(health < 0)
            health = 0;
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }
    public Armor getArmor(){
        return this.getInventory().getArmor();
    }

}