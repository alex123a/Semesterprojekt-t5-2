package sample.domain;

import sample.domain.PlasticElements.Plastic;
import sample.domain.PlasticElements.WaterBottle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.domain.Game;
import sample.presentation.Main;

public class Player {
    private String name;
    private List<Plastic> plasticInv = new ArrayList();
    private boolean haveToolset = false;
    private String image = "src/sample/presentation/pictures/Playergame.png";
    private WaterBottle plastic = new WaterBottle();
    // private List<Tool> toolInv = new ArrayList();
    // Her er der taget udgangspunkt i et "Tool" objekt og et "Plastic" objekt.

    /*
    public static void setName() {
        System.out.print("What is your name?\n> ");
        boolean nameChosen = false;
        while (!nameChosen) { // In this while loop we check for a name that is valid (No only space names) etc...
            Scanner playerName = new Scanner(System.in);
            name = playerName.nextLine();
            if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
                nameChosen = true;
            } else {
                System.out.print("Name not vaild enter new name\n> ");
            }
        }
        System.out.println("You have chosen " + name + " as your player name");
    }
    */

    public String getName() {
        return name;
    }

    // plasticCollect skal fjerne en den plastik fra rummet som den står oven på.
    public void plasticCollect(Plastic piece, Room room) {
        if (room.getPlasticInRoom().size() > 0) {
            plasticInv.add(piece);
            room.removePlastic(piece);
            System.out.println(plasticInv.size());
        } else {
            System.out.println("This room is empty");
        }
    }

    public void setPlasticInv(List<Plastic> newInv) {
        plasticInv = newInv;
    }

    public List<Plastic> getPlasticInv() {
        return plasticInv;
    }

    public boolean addPlasticInv() {
        if (plasticInv.size() <= 10 - Main.game.getFarmer().getPlasticForPlayer().length) {
            plasticInv.addAll(Arrays.asList(Main.game.getFarmer().getPlasticForPlayer()));
            return true;
        }
        return false;
    }

    public void setHaveToolset(boolean haveToolsetPara) {
        haveToolset = haveToolsetPara;
    }

    public boolean getHaveToolset() {
        return haveToolset;
    }

    public void resetPlasticInv() {
        plasticInv = new ArrayList<>();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

