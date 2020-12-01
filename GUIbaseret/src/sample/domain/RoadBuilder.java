package sample.domain;

import sample.domain.PlasticElements.Plastic;
import sample.presentation.Controller;

import java.util.List;

public class RoadBuilder {
    private static int inventoryCount = 0;
    private static int damaged = 0;
    private static boolean haveSpoken = false;
    private String image = "src/sample/presentation/pictures/RoadBuilder.png";


    public static int inventory(List<Plastic> plastic) {
        inventoryCount += plastic.size();
        return inventoryCount;
    }

    public static void damagedMachine() {
        if (inventoryCount >= 19 && !haveSpoken) {
            damaged = 100;
            System.out.println("Machine: \"Oh no i have stopped working, talk to the mechanic in the town\"");
            haveSpoken = true;
        }
    }

    public static int getInventoryCount() {
        return inventoryCount;
    }

    public static int getDamaged() {
        return damaged;
    }

    public static void setDamaged(int newDamage) {
        damaged = newDamage;
    }

    public String getImage() {
        return image;
    }
}
