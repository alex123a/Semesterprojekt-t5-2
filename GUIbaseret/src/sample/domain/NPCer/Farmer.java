package sample.domain.NPCer;

import sample.domain.PlasticElements.*;
import sample.domain.Player;
import sample.presentation.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Farmer extends NPC {
    final private int randomNumber = (new Random()).nextInt(8 - 5 + 1) + 5;
    private Plastic[] plasticForPlayer = new Plastic[randomNumber];
    // private File file = Paths.get(new File("worldofzuul/NPC/NPC-descriptions/FarmText.txt").getAbsolutePath()).toFile();
    private String file = "src/sample/data/textfiles/npcDescriptions/FarmText.txt";

    public Farmer(String name) {
        super(name,"src/sample/presentation/pictures/npc/farmer.png");
        fillPlasticArray();
    }

    public void fillPlasticArray() {
        int random = (new Random()).nextInt(2 - 1 + 1) + 1;
        for (int i = 0; i < this.plasticForPlayer.length; i++) {
            this.plasticForPlayer[i] = random == 1 ? new WaterBottle() : new CleaningPlastic();
        }
    }

    public Plastic[] getPlasticForPlayer() {
        return this.plasticForPlayer;
    }

    public void emptyPlasticForPlayer() {
        List<Plastic> newInv = Controller.playerObject.getPlasticInv();;
        for (Plastic plastic: plasticForPlayer) {
            newInv.add(plastic);
        }
        Controller.playerObject.setPlasticInv(newInv);
        this.plasticForPlayer = null;
    }

}
