package worldofzuul;

import worldofzuul.Rooms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private String name;
    final private File welcomeMessage = Paths.get(new File("worldofzuul\\textfiles\\gameDescription.txt").getAbsolutePath()).toFile();
    final private File roomDescription = Paths.get(new File("worldofzuul\\textfiles\\roomDescription.txt").getAbsolutePath()).toFile();
    final private File help = Paths.get(new File("worldofzuul\\textfiles\\help.txt").getAbsolutePath()).toFile();


    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {
        Room RoadBuild, Town, Beach, Farm, Park, Sdu;

        Scanner reader;
        try {
            reader = new Scanner(roomDescription);
            RoadBuild = new RoadBuild(reader.nextLine());
            Town = new Town(reader.nextLine());
            Beach = new Beach(reader.nextLine());
            Farm = new Farm(reader.nextLine());
            Park = new Park(reader.nextLine());
            Sdu = new Sdu(reader.nextLine());
            reader.close();

            RoadBuild.setExit("east", Farm);
            RoadBuild.setExit("south", Sdu);
            RoadBuild.setExit("west", Beach);
            RoadBuild.setExit("north", Park);

            Sdu.setExit("north", RoadBuild);

            Farm.setExit("west", RoadBuild);

            Beach.setExit("east", RoadBuild);

            Park.setExit("east", Town);
            Park.setExit("south", RoadBuild);

            Town.setExit("west", Park);

            currentRoom = RoadBuild;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("You have completed 100% of the road in plastic.");
    }

    private void printWelcome() {

        Scanner reader;
        try {
            reader = new Scanner(welcomeMessage);
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
        System.out.print("What is your name?\n> ");
        boolean nameChosen = false;
        while (!nameChosen) { // In this while loop we check for a name that is valid (No only space names) etc...
            Scanner playerName = new Scanner(System.in);
            name = playerName.nextLine();
            if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
                nameChosen = true;
            } else {
                System.out.print("Name not vaild enter new name\n> ");
            }

        }
        System.out.println("You have chosen " + name + " as your player name");
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == commandWord.COLLECT) {
            Player.plasticCollect(currentRoom.getPlastic(),currentRoom);
        }
        return wantToQuit;
    }

    private void printHelp() {

        Scanner reader;
        try {
            reader = new Scanner(help);
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            System.out.println(reader.nextLine());
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("That is not possible!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.getPlasticTypes();
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
