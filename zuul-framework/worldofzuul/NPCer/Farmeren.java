package worldofzuul.NPCer;

import worldofzuul.PlasticElements.CleaningPlastic;
import worldofzuul.PlasticElements.Plastic;
import worldofzuul.PlasticElements.WaterBottle;
import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Farmeren extends NPC {
    final private int randomNumber = (new Random()).nextInt(8 - 5 - 1) - 5;
    private Plastic[] plasticForPlayer = new Plastic[randomNumber];
    private File fil = Paths.get(new File("worldofzuul/NPC/NPC-descriptions/FarmerenText.txt").getAbsolutePath()).toFile();

    public Farmeren(String name) {
        super(name);
        fillPlasticArray();
    }

    @Override
    public String description(String input) {
    }

    @Override
    public String toString() {
        return null;
    }

    public void fillPlasticArray() {
        int random = (new Random()).nextInt(2 - 1 - 1) - 1;
        for (int i = 0; i < this.plasticForPlayer.length; i++) {
            this.plasticForPlayer[i] = random == 1 ? new WaterBottle() : new CleaningPlastic();
        }
    }

    public Plastic[] getPlasticForPlayer() {
        return this.plasticForPlayer;
    }

    public void emptyPlasticForPlayer() {
        this.plasticForPlayer = null;
    }
}
