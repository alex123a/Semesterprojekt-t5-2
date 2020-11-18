package sample.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.domain.Game;
import sample.domain.Room;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    public static String background;


    @FXML
    private Button westBut;
    @FXML
    private Button eastBut;
    @FXML
    private Button northBut;
    @FXML
    private Button southBut;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    private ImageView player;

    public void movePlayer(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            player.setTranslateY(player.getTranslateY() - 4);
            System.out.println(player.getTranslateY());
        } else if (keyEvent.getCode() == KeyCode.S) {
            player.setTranslateY(player.getTranslateY() + 4);
            System.out.println(player.getTranslateY());
        } else if (keyEvent.getCode() == KeyCode.A) {
            player.setTranslateX(player.getTranslateX() - 4);
            System.out.println(player.getTranslateX());
        } else if (keyEvent.getCode() == KeyCode.D) {
            player.setTranslateX(player.getTranslateX() + 4);
            System.out.println(player.getTranslateX());
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            
        }

    public void hideButtons() {
        westBut.setVisible(true);
        eastBut.setVisible(true);
        southBut.setVisible(true);
        northBut.setVisible(true);
        if(!roomExit.contains("west")){
            westBut.setVisible(false);
        }
        if(!roomExit.contains("east")){
            eastBut.setVisible(false);
        }
        if(!roomExit.contains("south")){
            southBut.setVisible(false);
        }
        if(!roomExit.contains("north")){
            northBut.setVisible(false);
        }
    }

    public void changeNorth() {
        System.out.println("Test");
        Game.changedRoom = "north";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideButtons();
    }
    public void changeSouth() {
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideButtons();
    }
    public void changeWest() {
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideButtons();
    }
    public void changeEast() {
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideButtons();
    }
}
