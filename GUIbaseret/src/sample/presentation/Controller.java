package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.domain.Game;
import sample.domain.Room;

import java.util.ArrayList;
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

    public void initialize(){
        westBut.setVisible(false);
        eastBut.setVisible(false);
        southBut.setVisible(false);
        northBut.setVisible(false);
        if(!roomExit.contains("west")){
            westBut.setVisible(true);
        }
        if(!roomExit.contains("east")){
            eastBut.setVisible(true);
        }
        if(!roomExit.contains("south")){
            southBut.setVisible(true);
        }
        if(!roomExit.contains("north")){
            northBut.setVisible(true);
        }

    }

    public void changeNorth() {
        Game.changedRoom = "north";
        backgroundRoom.setImage(new Image("file:" + background));
    }
    public void changeSouth(){
        Game.changedRoom = "south";
        backgroundRoom.setImage(new Image("file:" + background));
    }
    public void changeWest(){
        Game.changedRoom = "west";
        backgroundRoom.setImage(new Image("file:" + background));
    }
    public void changeEast(){
        Game.changedRoom = "east";
        backgroundRoom.setImage(new Image("file:" + background));
    }



}
