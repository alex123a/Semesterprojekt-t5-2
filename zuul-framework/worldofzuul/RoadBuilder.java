package worldofzuul;

import worldofzuul.PlasticElements.Plastic;

import java.util.ArrayList;

public class RoadBuilder {
    private static ArrayList<Plastic> inventory = new ArrayList<>();
    private static int damaged = 0;
    private static boolean haveSpoken = false;


    public static ArrayList<Plastic> inventory(ArrayList<Plastic> plastic) {
        inventory.addAll(plastic);
        if (inventory.size() < Game.getRoadDone()) {
            int inv = ((inventory.size()*100)/Game.getRoadDone());
            System.out.println("The road is " + inv + "% Complete");
            System.out.println("You need to collect " + (Game.getRoadDone()-inventory.size()));
        }
        return inventory;
    }
    public static void damagedMachine() {
        if (inventory.size() >= 19 && !haveSpoken) {
            damaged = 100;
            System.out.println("Machine: \"Oh no i have stopped working, talk to the mechanic in the village\"");
            haveSpoken = true;
        }
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
