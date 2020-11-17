package sample.domain;

import sample.domain.PlasticElements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

public abstract class Room {
    private List<Plastic> plasticInRoom;
    private HashMap<String, Room> exits;
    private int plasticCount;

    public Room() {
        exits = new HashMap<>();
        this.plasticInRoom = new ArrayList<>();
        this.plasticCount = this.plasticInRoom.size();
    }

    public void setPlasticArray(Plastic[] randomPlastic) {
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        this.plasticCount = this.plasticInRoom.size();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void getPlasticTypes() {
        int[] numberOfPlastic = new int[4];
        for (Plastic plastic: plasticInRoom) {
            if (plastic instanceof CleaningPlastic) {
                numberOfPlastic[0]++;
            } else if (plastic instanceof JuiceBottle) {
                numberOfPlastic[1]++;
            } else if (plastic instanceof MilkBottle) {
                numberOfPlastic[2]++;
            } else if (plastic instanceof WaterBottle) {
                numberOfPlastic[3]++;
            }
        }

        String[] typer = {"Cleaning products", "Juice bottles", "Milk bottles", "Water bottles"};
        System.out.println("Total of " + plasticInRoom.size() + " plastic pieces");
        for (int i = 0; i < numberOfPlastic.length; i++) {
            if (numberOfPlastic[i] != 0) {
                System.out.print("There is " + numberOfPlastic[i] + " of the type " + typer[i] + " ");
            }
        }
        System.out.println();

    }

    public List<Plastic> getPlasticInRoom() {
            return plasticInRoom;
    }

    public Plastic getPlastic() {
        Plastic plastic = new Plastic();
        if (plasticInRoom.size() > 0) {
            plasticInRoom.remove(plasticCount-1);
            plasticCount--;
            System.out.println("You have collected 1 piece of plastic, there is " + plasticCount + " pieces of plastic left in the room");
        }
        return plastic;
    }

    public abstract Plastic[] generatePlasticArray();
}

