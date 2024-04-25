package battleLoc;
import java.util.Random;
import game.*;
import tools.*;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = randomObstacleNumber();
        System.out.println("*                  " + this.getName() + "                  *");
        System.out.println("Be careful! There can be " + obsNumber + " " + this.getObstacle().getName() + " living here!");
        System.out.print("<F>ight or <E>scape : ");

        String selectCase = scanner.nextLine();
        selectCase = selectCase.toUpperCase();

        if (selectCase.equals("F") && combat(obsNumber)) {
            // Savaş komutları
            System.out.println("All enemies are killed!");
            String award = this.getAward().toLowerCase();
            if(!award.equals("nothing") ){
                System.out.println("You've earned the " + this.getAward() + " treasure!");
                switch (award) {
                    case "water" -> this.getPlayer().getInventory().setWater(true);
                    case "food" -> this.getPlayer().getInventory().setFood(true);
                    case "firewood" -> this.getPlayer().getInventory().setFirewood(true);
                }

            }else{
                System.out.println("We are experienced :D");
            }


            System.out.println();
            return true;

        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("you died :/");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());

            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");
            playerStats();
            System.out.println("================================================");
            obstacleStats(i);
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");

            // 0 gelirse bu koşul çalışmıyor ve oyuncu ilk vuruyor
            // 1 gelirse bu koşula girerek ilk canavarın vurması sağlanıyor
            // Her iki koşulda da süreç aynı ilerlediği için böyle yaptım
            if (Game.coinToss() == 1) {
                System.out.println("The monster hits you!");
                int obsDamage = this.obstacle.getDamage() - this.getPlayer().getArmor().getDefence();
                if (obsDamage < 0)
                    obsDamage = 0;

                this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
                afterHit();
            }

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {

                System.out.print("<H>it or <E>scape : ");
                String selectCombat = scanner.nextLine().toUpperCase();

                if (selectCombat.equals("H")) {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("You hit it!");
                    obstacle.setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());

                    afterHit();

                    if (this.getObstacle().getHealth() > 0) {
                        Game.battle();
                        System.out.println("The monster has hit you!");
                        int obsDamage = this.obstacle.getDamage() - this.getPlayer().getArmor().getDefence();
                        if (obsDamage < 0)
                            obsDamage = 0;

                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);

                        afterHit();
                    }
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                } else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println(this.getObstacle().getName() + " killed!");

                if (this.getObstacle().getAward() > 0) {
                    System.out.println("You earned " + this.obstacle.getAward() + " coins");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.obstacle.getAward());
                } else {
                    this.awardChance(random.nextInt(100) + 1);
                }
            }else {
                return false;
            }
        }

        return true;
    }

    public void afterHit() {
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.obstacle.getName() + "-> Health : " + this.obstacle.getHealth());
    }

    public void awardChance(int chance) {
        if (chance > 0 && chance <= 45) {
            System.out.println("You didn't win anything! Next time...");

        } else if (chance > 45 && chance <= 70) {
            System.out.println("You earned " + moneyAward(random.nextInt(100) + 1) + " coins");

        } else if (chance > 70 && chance <= 85) {
            armorAward(random.nextInt(100) + 1);

        } else if (chance > 85 && chance <= 100) {
            weaponAward(random.nextInt(100) + 1);

        }
    }

    public int moneyAward(int chance) {
        if (chance > 0 && chance <= 20) {
            return 10;
        } else if (chance > 20 && chance <= 50) {
            return 5;
        }

        return 1;
    }

    public void weaponAward(int chance) {
        if(chance > 0 && chance <= 20){
            System.out.println("You won a "+ Weapon.getWeaponObjByID(3).getName());
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));

        }else if(chance > 20 && chance <= 50){
            System.out.println("You won a "+ Weapon.getWeaponObjByID(2).getName());
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));

        }else{
            System.out.println("You won a "+ Weapon.getWeaponObjByID(1).getName());
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
        }
    }

    public void armorAward(int chance) {
        if(chance > 0 && chance <= 20){
            System.out.println("You have gained "+ Armor.getArmorObjById(3).getType()+"armor");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));

        }else if(chance > 20 && chance <= 50){
            System.out.println("You have gained "+ Armor.getArmorObjById(2).getType()+"armor");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));

        }else{
            System.out.println("You have gained "+ Armor.getArmorObjById(1).getType()+"armor");
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
        }
    }

    public void playerStats() {
        System.out.println("Hero Information");
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("Health: | " + this.getPlayer().getHealth());
        System.out.println("Damage: | " + this.getPlayer().getTotalDamage());
        System.out.println("Money:  | " + this.getPlayer().getMoney());
        System.out.println("Tools");
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("Gun:     | " + this.getPlayer().getWeapon().getName());
        System.out.println("Damage:  | " + this.getPlayer().getWeapon().getDamage());
        System.out.println("Armor:   | " + this.getPlayer().getArmor().getType());
        System.out.println("Defence: | " + this.getPlayer().getArmor().getDefence());

    }

    public void obstacleStats(int i) {
        System.out.println(i + ". Monster Information");
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("Type:   | " + this.getObstacle().getName());
        System.out.println("Health: | " + this.getObstacle().getHealth());
        System.out.println("Damage: | " + this.getObstacle().getDamage());
        if(this.getObstacle().getAward() > 0)
            System.out.println("Award:  | " + this.getObstacle().getAward());
        else
            System.out.println("Award:  | Anything" );
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }


    // Getter Setter Methods
    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
