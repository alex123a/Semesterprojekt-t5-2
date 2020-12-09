package sample.domain;

import sample.domain.PlasticElements.Plastic;
import sample.presentation.Controller;

import java.util.List;

public class RoadBuilder {
    private int inventoryCount = 0;
    private int damaged = 0;
    private boolean haveSpoken = false;
    private boolean notDamagedBefore = true;
    private String image = "src/sample/presentation/pictures/keyItems/RoadBuilder.png";

    // This is used to update the inventory of the road/roadbuilder. Takes a list of plastic objects(player inv)
    // Returns how much plastic the player has given to the road through the game
    public int inventory(List<Plastic> plastic) {
        // Takes the amount of plastic from the player inv and puts it in the roadbuilers inv.
        // Not the objects just the amount, because the objects are not importent to the roadbuilder.
        inventoryCount += plastic.size();
        // Returns the number of plastic objets the player has given to the roadbuilder
        return inventoryCount;
    }
    // This breaks the machine
    public void damagedMachine() {
        // This if-statement says the player has to have given the roadbuilder at least 19 pc. of plastic and have to speak
        // if this is forfilled the machine breakes and sets the damaged count to 100
        // The have spoken atribute is used to that the machine only can break once
        if (inventoryCount >= 19 && !haveSpoken) {
            damaged = 100;
            // Have spoken sets to true, so that i cant break twice.
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

    public void setHaveSpoken(boolean haveSpoken) {
        this.haveSpoken = haveSpoken;
    }
}
