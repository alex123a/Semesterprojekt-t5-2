package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sample.domain.Game;
import sample.domain.Player;
import sample.domain.Room;
import sample.domain.Rooms.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    public static String background;
    private boolean north, south, east, west;
    public static Player playerObject = new Player();
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private int[] numbersPlayer;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    public ImageView player = new ImageView("file:" + playerObject.getImage());

    public void initialize() {
        player.setImage(new Image("file:" + playerObject.getImage()));
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (north && player.getTranslateY() > -220) {
                playerAnimation.setDirection(direction[0]);
                numbersPlayer = playerAnimation.changePic();
                if (!moveBlock(0, -2)) {
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    player.setTranslateY(player.getTranslateY() - 2);
                }
            }
            if (south && player.getTranslateY() < 220) {
                playerAnimation.setDirection(direction[1]);
                numbersPlayer = playerAnimation.changePic();
                if (!moveBlock(0, 2)) {
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    player.setTranslateY(player.getTranslateY() + 2);
                }
            }
            if (east && player.getTranslateX() > -340) {
                playerAnimation.setDirection(direction[2]);
                numbersPlayer = playerAnimation.changePic();
                if (!moveBlock(-2, 0)) {
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    player.setTranslateX(player.getTranslateX() - 2);
                }
            }
            if (west && player.getTranslateX() < 340) {
                playerAnimation.setDirection(direction[3]);
                numbersPlayer = playerAnimation.changePic();
                if (!moveBlock(2, 0)) {
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    player.setTranslateX(player.getTranslateX() + 2);
                }
            }

        }
    };

    public boolean moveBlock(int x, int y) {
        boolean cantMove = false;
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            // træ i højre hjørne
            if (player.getTranslateX() + x < -150 && player.getTranslateY() + y < -90) {
                cantMove = true;
                //væg 1
            } else if (player.getTranslateX() + x > 10 && player.getTranslateY() + y > 205) {
                cantMove = true;
                // væg 2
            } else if (player.getTranslateX() + x < -25 && player.getTranslateY() + y > 205) {
                cantMove = true;
            }
        }
        if (Main.game.getCurrentRoom() instanceof Beach)
            // Havet
            if (player.getTranslateX() + x < -95 && player.getTranslateY() + y > -340) {
                cantMove = true;
            }

        if (Main.game.getCurrentRoom() instanceof Farm) {
            // Farmené
            Rectangle field = new Rectangle(-176,-64,250,160);
          //  field.setTranslateX(-160);
          //  field.setTranslateY(-62);
            if(field.contains(player.getTranslateX() + x,player.getTranslateY() + y)){
                System.out.println("Hej");
                   cantMove = true;
            }


        }


        if(Main.game.getCurrentRoom() instanceof Farm){
            if(player.getTranslateX() + x > 115 && player.getTranslateY() + y < -36){
                cantMove = true;
            }
        }
        //Sdu's vægge
        if(Main.game.getCurrentRoom() instanceof Sdu){
            if(player.getTranslateX() + x < -50 && player.getTranslateY() + y <-190){
                cantMove = true;
            }
            if(player.getTranslateX() + x > 20 && player.getTranslateY() + y <-190){
                cantMove = true;
            }
        }

        if(Main.game.getCurrentRoom() instanceof Park){
            if(player.getTranslateX() + x < -150 && player.getTranslateY() + y < -85){
                cantMove = true;
            }
            if(player.getTranslateX() + x < -150 && player.getTranslateY() + y > 0){
                cantMove = true;
            }

            if(player.getTranslateX() + x > 135 && player.getTranslateY() + y < -85){
                cantMove = true;
            }
            if(player.getTranslateX() + x > 135 && player.getTranslateY() + y > 0){
                cantMove = true;
            }

        }

        return cantMove;
    }


    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                timer.start();
                north = true;
                System.out.println("x: " + player.getTranslateX());
                System.out.println("y: " + player.getTranslateY());
                break;
            case S:
                timer.start();
                south = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
            case A:
                timer.start();
                east = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
            case D:
                timer.start();
                west = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
        }
        if (player.getTranslateY() < -208 && player.getTranslateX() > -108 && player.getTranslateX() < -30) {
            changeNorth();
        } else if (player.getTranslateY() > 208 && player.getTranslateX() > -140 && player.getTranslateX() < 20) {
            changeSouth();
        } else if (player.getTranslateX() < -328 && player.getTranslateY() > -60 && player.getTranslateY() < 0) {
            changeWest();
        } else if (player.getTranslateX() > 328 && player.getTranslateY() > -60 && player.getTranslateY() < 0) {
            changeEast();
        }
    }

    public void stopPlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                timer.stop();
                north = false;
                break;
            case S:
                timer.stop();
                south = false;
                break;
            case A:
                timer.stop();
                east = false;
                break;
            case D:
                timer.stop();
                west = false;
                break;
        }
    }

    public void changeNorth() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Farm || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateY(204);
        }
        Game.changedRoom = "north";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeSouth() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Farm || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Sdu)) {
            player.setTranslateY(-204);
        }
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeWest() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateX(330);
        }
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeEast() {
        if (!(Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm)) {
            player.setTranslateX(-330);
        }
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }


}
