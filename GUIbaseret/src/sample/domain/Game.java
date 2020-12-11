package sample.domain;

import sample.domain.NPCs.*;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;
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

    //Creates the rooms and their associations to the other rooms.
    public void createRooms() {
        //The rooms is created
        RoadBuild = new RoadBuild();
        Town = new Town();
        Beach = new Beach();
        Farm = new Farm();
        Park = new Park();
        Sdu = new Sdu();

        //The exits for each room is created.
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

        //currentRoom sets the start room.
        currentRoom = Sdu;
    }

    //Gets the plastic for the current room/new room, because each room have different amount of plastic.
    public List<Plastic> placePlastic() {
        return changedRoom != null ? currentRoom.getPlasticInRoom() : Sdu.getPlasticInRoom();
    }

    //Handles how the player changes room.
    public void goRoom() {
        Room nextRoom = changedRoom != null ? currentRoom.getExit(changedRoom) : Sdu;

        //sets the current room to the room the player are going
        currentRoom = nextRoom;

        //Removes the exits for the past room and sets up some new for the current room.
        Controller.roomExit.removeAll(currentDirections);
        for (String directions : currentRoom.getExits().keySet()) {
            currentDirections.add(directions);
        }
        Controller.roomExit.addAll(currentDirections);
        currentDirections.removeAll(currentDirections);

        currentRoom.getPlasticTypes();

        //Sets the new background for the room
        Controller.background = currentRoom.getPictureRoom();
    }

    //Gets the players inventory and adds it to the roadbuilder, so it can build road
    //Resets the player inventory so the player can collect more plastic.
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
