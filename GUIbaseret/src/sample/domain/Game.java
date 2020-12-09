package sample.domain;

import sample.domain.NPCs.*;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sample.presentation.Controller;


public class Game {

    public static String[] startDirections = {"north", "south", "west", "east"};
    private Room currentRoom;
    private static final int roadDone = 30;
    private Room RoadBuild, Town, Beach, Farm, Park, Sdu;
    private Player playerObject = new Player();
    private RoadBuilder roadBuilder = new RoadBuilder();
    private Professor professorObject = new Professor("Professor");
    private Mechanic mechanicObject = new Mechanic("Mechanic");
    private Farmer farmerObject = new Farmer("Farmer");
    private Fisherman fishermanObject = new Fisherman("Fisherman");
    private OldLady oldLadyObject = new OldLady("OldLady");
    public Road road = new Road();
    private List<String> currentDirections = new ArrayList<>();
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
        List<Plastic> plasticInv = playerObject.getPlasticInv();
        roadBuilder.inventory(plasticInv);
        playerObject.resetPlasticInv();
    }

    public int getRoadDone() {
        return roadDone;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayerObject() {
        return playerObject;
    }

    public RoadBuilder getRoadBuilder() {
        return roadBuilder;
    }

    public Professor getProfessorObject() {
        return professorObject;
    }

    public Mechanic getMechanicObject() {
        return mechanicObject;
    }

    public Farmer getFarmerObject() {
        return farmerObject;
    }

    public Fisherman getFishermanObject() {
        return fishermanObject;
    }

    public OldLady getOldLadyObject() {
        return oldLadyObject;
    }

    public Road getRoad() {
        return road;
    }
}
