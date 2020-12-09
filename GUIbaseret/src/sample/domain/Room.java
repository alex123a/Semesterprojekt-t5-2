package sample.domain;

import sample.domain.PlasticElements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

public abstract class Room {
    private ArrayList<Plastic> plasticInRoom;
    private HashMap<String, Room> exits;
    private int plasticCount;
    private String pictureRoom;

    public Room(String pictureRoom) {
        exits = new HashMap<>();
        this.plasticInRoom = new ArrayList<>();
        this.plasticCount = this.plasticInRoom.size();
        this.pictureRoom = pictureRoom;
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

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void getPlasticTypes() {
        int[] numberOfPlastic = new int[4];
        for (Plastic plastic: plasticInRoom) {
            if (plastic instanceof CleaningPlastic) {
                numberOfPlastic[0]++;
            } else if (plastic instanceof SodaBottle) {
                numberOfPlastic[1]++;
            } else if (plastic instanceof MilkBottle) {
                numberOfPlastic[2]++;
            } else if (plastic instanceof WaterBottle) {
                numberOfPlastic[3]++;
            }
        }
    }

    public List<Plastic> getPlasticInRoom() {
            return plasticInRoom;
    }

    public void removePlastic(Plastic plastic) {
        plasticInRoom.set(plasticInRoom.indexOf(plastic), null);
    }

    public abstract Plastic[] generatePlasticArray();

    public String getPictureRoom() {
        return this.pictureRoom;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public void emptyPlasticArray() {
        plasticInRoom.removeAll(plasticInRoom);
    }
}

