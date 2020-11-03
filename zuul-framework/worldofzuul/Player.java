package worldofzuul;

import worldofzuul.PlasticElements.Plastic;

import java.util.ArrayList;

public class Player {
    private String name;
    private static ArrayList<Plastic> plasticInv = new ArrayList();
    private static boolean haveToolset = false;
    // private ArrayList<Tool> toolInv = new ArrayList();
    // Her er der taget udgangspunkt i et "Tool" objekt og et "Plastic" objekt.
    
    public Player(String name) {
        this.name = name;
    }

    public static void plasticCollect(Plastic piece, Room room) {
        if (room.getPlasticInRoom().size() > 0) {
            plasticInv.add(piece);
        } else {
            System.out.println("The room is empty");
        }
    }

    public static void setPlasticInv(ArrayList<Plastic> newInv) {
        plasticInv = newInv;
    }

    public static ArrayList<Plastic> getPlasticInv() {
        return plasticInv;
    }

    public static void setHaveToolset(boolean haveToolsetPara) {
        haveToolset = haveToolsetPara;
    }

    public static boolean getHaveToolset() {
        return haveToolset;
    }

}

