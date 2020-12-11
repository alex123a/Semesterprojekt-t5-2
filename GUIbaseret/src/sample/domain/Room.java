package sample.domain;

import sample.domain.PlasticElements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

public abstract class Room {
    /**
     * @param pictureRoom a path to the picture
     */
    private ArrayList<Plastic> plasticInRoom;
    private HashMap<String, Room> exits;
    private int plasticCount;
    private String pictureRoom;

    /*
    Setup for each room. Sets the exits in a hashmap.
    Puts the plastic in the room into an new arraylist.
    */
    public Room(String pictureRoom) {
        exits = new HashMap<>();
        this.plasticInRoom = new ArrayList<>();
        this.plasticCount = this.plasticInRoom.size();
        this.pictureRoom = pictureRoom;
    }

    //Sets the plastic in the room
    public void setPlasticArray(Plastic[] randomPlastic) {
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        this.plasticCount = this.plasticInRoom.size();
    }

    //Sets the exits for the direction of each room.
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    //Plus all the plastics in the room into one array with the types.
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

