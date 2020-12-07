package sample.domain;

import sample.domain.NPCer.*;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sample.presentation.Controller;


public class Game {
    public static String[] startDirections = {"north", "south", "west", "east"};

    private Room currentRoom;
    final private File welcomeMessage = new File("src/sample/data/textfiles/gameDescriptions/gameDescription.txt");
    final private File help = new File("src/sample/data/textfiles/gameDescriptions/help.txt");
    private static final int roadDone = 30;
    private Room RoadBuild, Town, Beach, Farm, Park, Sdu;
    private Farmer farmer = new Farmer("Farmer");
    private Mechanic mechanic = new Mechanic("Mechanic");
    private Professor professor = new Professor("Professor");
    private Toolset toolset = new Toolset();
    // private List<String> currentDirections = new ArrayList<>(Arrays.asList(startDirections));
    private List<String> currentDirections = new ArrayList<>();
    //sæt privat
    public static String changedRoom;

    public Game() {
        createRooms();
    }


    public void createRooms() {
        RoadBuild = new RoadBuild();
        Town = new Town();
        Beach = new Beach();
        Farm = new Farm();
        Park = new Park();
        Sdu = new Sdu();

        RoadBuild.setExit("east", Town);
        RoadBuild.setExit("south", Sdu);
        RoadBuild.setExit("west", Beach);
        RoadBuild.setExit("north", Park);

        Sdu.setExit("north", RoadBuild);

        Farm.setExit("west", Park);

        Beach.setExit("east", RoadBuild);

        Park.setExit("east", Farm);
        Park.setExit("south", RoadBuild);

        Town.setExit("west", RoadBuild);

        currentRoom = Sdu;
    }

    public List<Plastic> placePlastic() {
        return changedRoom != null ? currentRoom.getPlasticInRoom() : Sdu.getPlasticInRoom();
    }

    public void goRoom() {
        Room nextRoom = changedRoom != null ? currentRoom.getExit(changedRoom) : Sdu;

        currentRoom = nextRoom;

        Controller.roomExit.removeAll(currentDirections);
        for (String directions : currentRoom.getExits().keySet()) {
            currentDirections.add(directions);
        }

        Controller.roomExit.addAll(currentDirections);
        currentDirections.removeAll(currentDirections);
        currentRoom.getPlasticTypes();
        Controller.background = currentRoom.getPictureRoom();
    }

    public void givePlastic() {
        List<Plastic> plasticInv = Controller.playerObject.getPlasticInv();
        Controller.roadBuilder.inventory(plasticInv);
        Controller.playerObject.resetPlasticInv();
    }

    public int getRoadDone() {
        return roadDone;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Farmer getFarmer() {
        return farmer;
    }
}
