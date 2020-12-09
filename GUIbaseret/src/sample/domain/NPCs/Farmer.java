package sample.domain.NPCs;

import sample.domain.PlasticElements.*;
import sample.presentation.Main;

import java.util.List;
import java.util.Random;

//This class inherits from the NPC class
public class Farmer extends NPC {
    // Decides random size of plastic for plasticPlayer
    final private int randomNumber = (new Random()).nextInt(8 - 5 + 1) + 5;
    private Plastic[] plasticForPlayer = new Plastic[randomNumber];

    public Farmer(String name) {
        super(name, "src/sample/presentation/pictures/npc/farmer.png");
        fillPlasticArray();
    }

    // Fills plasticForPlayer array.
    public void fillPlasticArray() {
        /*
        Loops through the plasticForPlayer array and returns the type of plastic, the player will get.
        The type of plastic is decided here. Type of plastic is either WaterBottle or CleaningPlast
        */
        for (int i = 0; i < this.plasticForPlayer.length; i++) {
            int random = (new Random()).nextInt(2 - 1 + 1) + 1;
            this.plasticForPlayer[i] = random == 1 ? new WaterBottle() : new CleaningPlastic();
        }
    }

    public Plastic[] getPlasticForPlayer() {
        return this.plasticForPlayer;
    }

    // Is this method used??
    public void emptyPlasticForPlayer() {
        List<Plastic> newInv = Main.game.getPlayerObject().getPlasticInv();
        for (Plastic plastic : plasticForPlayer) {
            newInv.add(plastic);
        }
        Main.game.getPlayerObject().setPlasticInv(newInv);
        this.plasticForPlayer = null;
    }

}
