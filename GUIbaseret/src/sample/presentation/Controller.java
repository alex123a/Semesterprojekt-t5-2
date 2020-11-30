package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import sample.domain.*;
import sample.domain.Rooms.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    public static String background;
    public static Road road = new Road();
    public static Player playerObject = new Player();
    public static RoadBuilder roadBuilder = new RoadBuilder();
    private boolean north, south, east, west;
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private int[] numbersPlayer;
    private long animationWalk = 0;

    @FXML
    private ImageView backgroundRoom;

    @FXML
    public ImageView roadView = new ImageView("file:" + road.getImage());

    @FXML
    public ImageView roadBuilderView = new ImageView("file:" + roadBuilder.getImage());

    @FXML
    public ImageView player = new ImageView("file:" + playerObject.getImage());


    public void initialize() {
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
        showRoadBuilderRoad();
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
            case UP:
            case W:
                timer.start();
                north = true;
                west = false;
                east = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case DOWN:
            case S:
                timer.start();
                south = true;
                west = false;
                east = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case LEFT:
            case A:
                timer.start();
                east = true;
                north = false;
                south = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case RIGHT:
            case D:
                timer.start();
                west = true;
                north = false;
                south = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
        }
        NewRoom();
    }

    private void NewRoom() {
        //North
        if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateY() < -202 && player.getTranslateX() > -142.5 && player.getTranslateX() < -82.5) {
            changeNorth();
        } else if (Main.game.getCurrentRoom() instanceof Sdu && player.getTranslateY() < -158 && player.getTranslateX() > -80 && player.getTranslateX() < 14) {
            changeNorth();
        //South
        } else if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateY() > 208 && player.getTranslateX() > -80 && player.getTranslateX() < 14) {
            changeSouth();
        } else if (Main.game.getCurrentRoom() instanceof Park && player.getTranslateY() > 208 && player.getTranslateX() > -142.5 && player.getTranslateX() < -82.5) {
            changeSouth();
        } else if (Main.game.getCurrentRoom() instanceof Park && player.getTranslateY() > 208 && player.getTranslateX() > 68 && player.getTranslateX() < 126) {
            changeSouth();
        //West
        } else if (player.getTranslateX() < -328 && player.getTranslateY() > -116.5 && player.getTranslateY() < -61.5) {
            changeWest();
        } else if (Main.game.getCurrentRoom() instanceof Town && player.getTranslateX() < -328 && player.getTranslateY() > -64 && player.getTranslateY() < -8) {
            changeWest();
        //East
        } else if (player.getTranslateX() > 328 && player.getTranslateY() > -116.5 && player.getTranslateY() < -61.5) {
            changeEast();
        } else if (Main.game.getCurrentRoom() instanceof Park && player.getTranslateX() > 328 && player.getTranslateY() > -64 && player.getTranslateY() < -8) {
            changeEast();
        }
    }

    public void stopPlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
            case W:
                timer.stop();
                animationWalk = 0;
                north = false;
                break;
            case DOWN:
            case S:
                timer.stop();
                animationWalk = 0;
                south = false;
                break;
            case LEFT:
            case A:
                timer.stop();
                animationWalk = 0;
                east = false;
                break;
            case RIGHT:
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
        showRoadBuilderRoad();
    }

    public void changeSouth() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Farm || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Sdu)) {
            player.setTranslateY(-200);
        }
        if (Main.game.getCurrentRoom() instanceof Park){
            player.setTranslateX(-117.5);
        }
        if (Main.game.getCurrentRoom() instanceof RoadBuild){
            player.setTranslateY(-150);
        }
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
    }

    public void changeWest() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateX(327);
        }
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
    }

    public void changeEast() {
        if (!(Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm)) {
            player.setTranslateX(-327);
        }
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
    }

    public void showRoadBuilderRoad() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadView.setViewport(new Rectangle2D(-681 + (RoadBuilder.getInventoryCount() * 22.7), 0, 681, 69));
        } else {
            roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
        }
        showRoadBuilder();
    }

    public void showRoadBuilder() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
            roadBuilderView.setTranslateX(300 - (RoadBuilder.getInventoryCount() * 20));
        } else {
            roadBuilderView.setViewport(new Rectangle2D(-484, 0, 484, 323));
        }
    }
}