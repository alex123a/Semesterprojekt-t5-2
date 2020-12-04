package sample.domain;

import sample.domain.PlasticElements.Plastic;
import sample.presentation.Controller;

import java.util.List;

public class RoadBuilder {
    private int inventoryCount = 0;
    private int damaged = 0;
    private boolean haveSpoken = false;
    private boolean notDamagedBefore = true;
    private String image = "src/sample/presentation/pictures/RoadBuilder.png";


    public int inventory(List<Plastic> plastic) {
        inventoryCount += plastic.size();
        return inventoryCount;
    }

    public void damagedMachine() {
        if (inventoryCount >= 19 && !haveSpoken) {
            damaged = 100;
            System.out.println("Machine: \"Oh no i have stopped working, talk to the mechanic in the town\"");
            haveSpoken = true;
        }
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int newDamage) {
        damaged = newDamage;
    }

    public String getImage() {
        return image;
    }

    public boolean isNotDamagedBefore() {
        return notDamagedBefore;
    }

    public void setNotDamagedBefore(boolean notDamagedBefore) {
        this.notDamagedBefore = notDamagedBefore;
    }

    public boolean isHaveSpoken() {
        return haveSpoken;
    }

    public void setHaveSpoken(boolean haveSpoken) {
        this.haveSpoken = haveSpoken;
    }
}
