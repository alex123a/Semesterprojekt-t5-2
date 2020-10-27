package worldofzuul;

import worldofzuul.Rooms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    final private String file = new File("worldofzuul\\textfiles\\roomDescription").getAbsolutePath();
    final private String file2 = new File("worldofzuul\\textfiles\\gameDescription").getAbsolutePath();
    final private String file3 = new File("worldofzuul\\textfiles\\help").getAbsolutePath();
    final private File welcomeMessage = new File(file2.replace("\\","\\\\") + ".txt");
    final private File roomDescription = new File(file.replace("\\","\\\\") + ".txt");
    final private File help = new File(file3.replace("\\","\\\\") + ".txt");


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
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
