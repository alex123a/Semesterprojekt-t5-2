package worldofzuul;

import worldofzuul.PlasticElements.Plastic;

import java.util.ArrayList;

public class RoadBuilder {
    private static ArrayList<Plastic> inventory = new ArrayList<>();
    private static int damaged = 0;

    public static ArrayList<Plastic> inventory(ArrayList<Plastic> plastic) {
        inventory.addAll(plastic);
        if (inventory.size() < Game.getRoadDone()) {
            int inv = ((inventory.size()*100)/Game.getRoadDone());
            System.out.println("The road is " + inv + "% Complete");
            System.out.println("You need to collect " + (Game.getRoadDone()-inventory.size()) + " to finish the road ");
        }
        return inventory;
    }

    public static ArrayList<Plastic> getInventory() {
        return inventory;
    }

    public static int getDamaged() {
        return damaged;
    }

    public static void setDamaged(int newDamage) {
        damaged = newDamage;
    }


}
