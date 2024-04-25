package normalLoc;

import game.Game;
import game.Player;
import tools.Armor;
import tools.Weapon;

public class ToolStore extends NormalLoc {
    public ToolStore(Player p) {
        super(p, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("*                  TOOL STORE                  *");
        System.out.println("1 - Guns");
        System.out.println("2 - Armors");
        System.out.println("0 - Exit");

        int secim;
        do {
            System.out.print("Select the action you want to do: ");
            secim = scanner.nextInt();

            switch (secim) {
                case 0:
                    System.out.println("We are waiting for you again!");
                    break;
                case 1:
                    menu("guns");
                    buy("gun");
                    break;
                case 2:
                    menu("armors");
                    buy("armor");
                    break;
            }

        } while (secim < 0 || secim > 2);

        return true;
    }

    public void menu(String category) {
        Game.loading();

        switch (category){
            case "guns" -> {
                System.out.println("### Guns ### (Money: " + this.getPlayer().getMoney() + ")");
                for (Weapon w : Weapon.weapons()) {
                    System.out.println(w.getId() + " - " + w.getName() + " <Damage: " + w.getDamage() + ", Price: " + w.getPrice() + ">");
                }
            }

            case "armors" -> {
                System.out.println("### Armors ### (Money: "+this.getPlayer().getMoney()+")");
                for (Armor a : Armor.armors()) {
                    System.out.println(a.getId() + " - " + a.getType() + " <Defence: " + a.getDefence() + ", Price: " + a.getPrice() + ">");
                }
            }
        }
    }
    public void buy(String category) {
        int secim;

        switch (category) {
            case "gun" -> {
                do {
                    System.out.print("Enter the weapon number (<0> for Exit): ");
                    secim = scanner.nextInt();

                    if (secim >= 0 && secim < Weapon.weapons().length + 1) {
                        Weapon selectedWeapon = Weapon.getWeaponObjByID(secim);
                        if (selectedWeapon != null) {

                            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                                System.out.println("Insufficient balance!");
                            } else {
                                System.out.println("You bought the " + selectedWeapon.getName() + " gun!");

                                int newBalance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                                this.getPlayer().setMoney(newBalance); // Bakiye güncelleniyor

                                System.out.println("Previous weapon: " + this.getPlayer().getInventory().getWeapon().getName()
                                        + " -> New Weapon: " + selectedWeapon.getName());

                                this.getPlayer().getInventory().setWeapon(selectedWeapon);

                                System.out.println("Money: " + this.getPlayer().getMoney());
                            }

                        } else {
                            System.out.println("Weapon stock is depleted.");
                        }

                    } else {
                        System.out.print("Invalid selection! Please select again:");
                    }
                } while (secim < 0 || secim > Weapon.weapons().length);
            }

            case "armor" -> {
                do {
                    System.out.print("Enter the armor number (0 for Exit):");
                    secim = scanner.nextInt();

                    if (secim >= 0 && secim < Armor.armors().length) {
                        Armor selectedArmor = Armor.getArmorObjById(secim);
                        if (selectedArmor != null) {

                            if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                                System.out.println("Insufficient balance!");
                            } else {
                                System.out.println("You bought the " + selectedArmor.getType() + " armor!");

                                int newBalance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                                this.getPlayer().setMoney(newBalance); // Bakiye güncelleniyor

                                System.out.println("Previous armor: " + this.getPlayer().getInventory().getArmor().getType()
                                        + " -> New armor: " + selectedArmor.getType());

                                this.getPlayer().getInventory().setArmor(selectedArmor);

                                System.out.println("Money: " + this.getPlayer().getMoney());
                            }

                        } else {
                            System.out.println("Armor stock is depleted.");
                            onLocation();
                        }

                    } else {
                        System.out.print("Invalid selection! Please select again:");
                    }

                    System.out.println("------------------------------------------------");
                } while (secim < 0 || secim > Armor.armors().length);
            }
        }
    }
}