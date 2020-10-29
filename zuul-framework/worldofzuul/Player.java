package worldofzuul;

import worldofzuul.PlasticElements.Plastic;

import java.util.ArrayList;

public class Player {
    private String name;
    private static ArrayList<Plastic> plasticInv = new ArrayList();
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
}

