package worldofzuul.NPCer;

import worldofzuul.PlasticElements.CleaningPlastic;
import worldofzuul.PlasticElements.Plastic;
import worldofzuul.PlasticElements.WaterBottle;
import worldofzuul.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Farmer extends NPC {
    final private int randomNumber = (new Random()).nextInt(8 - 5 + 1) + 5;
    private Plastic[] plasticForPlayer = new Plastic[randomNumber];
    // private File file = Paths.get(new File("worldofzuul/NPC/NPC-descriptions/FarmerenText.txt").getAbsolutePath()).toFile();
    private String file = Paths.get(new File("worldofzuul/NPCer/NPC-descriptions/FarmerenText.txt").getAbsolutePath()).toString();

    public Farmer(String name) {
        super(name);
        fillPlasticArray();
    }

    @Override
    public void description(String command) {
        if (!super.getTalking()) {
            try {
                setTalking(true);
                String line = Files.readAllLines(Paths.get(this.file)).get(0);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (command.equals("information") && super.getTalking()) {
            try {
                String line = Files.readAllLines(Paths.get(this.file)).get(1);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (command.equals("take") && super.getTalking()) {
            if (plasticForPlayer != null) {
                emptyPlasticForPlayer();
                try {
                    String line = Files.readAllLines(Paths.get(this.file)).get(2);
                    System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String line = Files.readAllLines(Paths.get(this.file)).get(3);
                    System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (command.equals("bye") && super.getTalking()) {
            try {
                String line = Files.readAllLines(Paths.get(this.file)).get(4);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unknown conversation");
        }
    }

    @Override
    public String toString() {
        return super.getName();
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
        ArrayList<Plastic> newInv = Player.getPlasticInv();;
        for (Plastic plastic: plasticForPlayer) {
            newInv.add(plastic);
        }
        Player.setPlasticInv(newInv);
        this.plasticForPlayer = null;
    }

}
