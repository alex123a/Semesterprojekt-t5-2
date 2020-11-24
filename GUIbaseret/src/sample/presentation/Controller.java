package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import sample.domain.Game;
import sample.domain.Player;
import sample.domain.Road;
import sample.domain.Rooms.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    public static String background;
    public static Road road = new Road();
    private boolean north, south, east, west;
    public static Player playerObject = new Player();
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private int[] numbersPlayer;
    private long animationWalk = 0;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    public ImageView roadView = new ImageView("file:" + road.getImage());

    @FXML
    public ImageView player = new ImageView("file:" + playerObject.getImage());

    public void initialize() {
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (north && player.getTranslateY() > -220) {
                playerAnimation.setDirection(direction[0]);
                if (animationWalk % 13 == 0) {
                    numbersPlayer = playerAnimation.changePic();
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                }
                player.setTranslateY(player.getTranslateY() - 2.5);
                animationWalk++;
            }
            if (south && player.getTranslateY() < 220) {
                playerAnimation.setDirection(direction[1]);
                if (animationWalk % 13 == 0) {
                    numbersPlayer = playerAnimation.changePic();
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                }
                player.setTranslateY(player.getTranslateY() + 2.5);
                animationWalk++;
            }
            if (east && player.getTranslateX() > -340) {
                playerAnimation.setDirection(direction[2]);
                if (animationWalk % 13 == 0) {
                    numbersPlayer = playerAnimation.changePic();
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                }
                player.setTranslateX(player.getTranslateX() - 2.5);
                animationWalk++;
            }
            if (west && player.getTranslateX() < 340) {
                playerAnimation.setDirection(direction[3]);
                if (animationWalk % 13 == 0) {
                    numbersPlayer = playerAnimation.changePic();
                    player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                }
                player.setTranslateX(player.getTranslateX() + 2.5);
                animationWalk++;
            }

        }
    };

    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                timer.start();
                north = true;
                west = false;
                east = false;
                System.out.println(player.getTranslateY());
                break;
            case S:
                timer.start();
                south = true;
                west = false;
                east = false;
                System.out.println(player.getTranslateY());
                break;
            case A:
                timer.start();
                east = true;
                north = false;
                south = false;
                System.out.println(player.getTranslateX());
                break;
            case D:
                timer.start();
                west = true;
                north = false;
                south = false;
                System.out.println(player.getTranslateX());
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
                animationWalk = 0;
                north = false;
                break;
            case S:
                timer.stop();
                animationWalk = 0;
                south = false;
                break;
            case A:
                timer.stop();
                animationWalk = 0;
                east = false;
                break;
            case D:
                timer.stop();
                animationWalk = 0;
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

    public void showRoad() {
    }


}
