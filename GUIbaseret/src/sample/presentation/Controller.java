package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.domain.Game;
import sample.domain.Player;
import sample.domain.Room;
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
                player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                player.setTranslateY(player.getTranslateY() - 2);
            }
            if (south && player.getTranslateY() < 220) {
                playerAnimation.setDirection(direction[1]);
                numbersPlayer = playerAnimation.changePic();
                player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                player.setTranslateY(player.getTranslateY() + 2);
            }
            if (east && player.getTranslateX() > -340) {
                playerAnimation.setDirection(direction[2]);
                numbersPlayer = playerAnimation.changePic();
                player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                player.setTranslateX(player.getTranslateX() - 2);
            }
            if (west && player.getTranslateX() < 340) {
                playerAnimation.setDirection(direction[3]);
                numbersPlayer = playerAnimation.changePic();
                player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                player.setTranslateX(player.getTranslateX() + 2);
            }

        }
    };

    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                timer.start();
                north = true;
                System.out.println(player.getTranslateY());
                break;
            case S:
                timer.start();
                south = true;
                System.out.println(player.getTranslateY());
                break;
            case A:
                timer.start();
                east = true;
                System.out.println(player.getTranslateX());
                break;
            case D:
                timer.start();
                west = true;
                System.out.println(player.getTranslateX());
                break;
            case M:
                if (player.getTranslateY() < -212) {
                    Main.game.goRoom();
                    changeNorth();
                } else if (player.getTranslateY() > 212) {
                    Main.game.goRoom();
                    changeSouth();
                } else if (player.getTranslateX() < -332) {
                    Main.game.goRoom();
                    changeWest();
                } else if (player.getTranslateX() > 332) {
                    Main.game.goRoom();
                    changeEast();
                }
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
        Game.changedRoom = "north";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeSouth() {
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeWest() {
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }

    public void changeEast() {
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
    }


}
