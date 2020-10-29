package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class Room 
{
    private String description;
    private ArrayList<worldofzuul.Plastic> plasticInRoom;
    private HashMap<String, Room> exits;
    private int plasticCount;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.plasticInRoom = new ArrayList<>();
        this.plasticCount = this.plasticInRoom.size();
    }

    public Room(String description, worldofzuul.Plastic[] randomPlastic) {
        this.description = description;
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        exits = new HashMap<String, Room>();
        this.plasticCount = this.plasticInRoom.size();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are at " + description + "\n" + getExitString();
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
        for (worldofzuul.Plastic plastic: plasticInRoom) {
            if (plastic instanceof worldofzuul.CleaningPlastic) {
                numberOfPlastic[0]++;
            } else if (plastic instanceof worldofzuul.JuiceBottle) {
                numberOfPlastic[1]++;
            } else if (plastic instanceof worldofzuul.MilkBottle) {
                numberOfPlastic[2]++;
            } else if (plastic instanceof worldofzuul.WaterBottle) {
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

    public ArrayList<worldofzuul.Plastic> getPlasticInRoom() {
            return plasticInRoom;
    }

    public worldofzuul.Plastic getPlastic() {
        worldofzuul.Plastic plastic = new worldofzuul.Plastic();
        if (plasticInRoom.size() > 0) {
            plasticInRoom.remove(plasticCount-1);
            plasticCount--;
            System.out.println("You have collected 1 piece of plastic, there is " + plasticCount + " pieces of plastic left in the room");
        }
        return plastic;
    }
}

