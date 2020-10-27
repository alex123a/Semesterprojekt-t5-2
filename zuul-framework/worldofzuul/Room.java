package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class Room 
{
    private String description;
    private ArrayList<Plastic> plasticInRoom;
    private HashMap<String, Room> exits;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.plasticInRoom = new ArrayList<>();
    }

    public Room(String description, Plastic[] randomPlastic)
    {
        this.description = description;
        this.plasticInRoom = new ArrayList<>(Arrays.asList(randomPlastic));
        exits = new HashMap<String, Room>();
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
}

