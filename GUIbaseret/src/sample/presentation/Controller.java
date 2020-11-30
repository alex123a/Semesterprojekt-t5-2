package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import sample.domain.Game;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Player;
import sample.domain.Road;
import sample.domain.RoadBuilder;
import sample.domain.Rooms.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ObservableList<ImageView> inventoryObservable = FXCollections.observableList(new ArrayList<ImageView>());

    @FXML
    private ImageView backgroundRoom;
    @FXML
    public ImageView roadView = new ImageView("file:" + road.getImage());
    @FXML
    public ImageView roadBuilderView = new ImageView("file:" + roadBuilder.getImage());
    @FXML
    public ImageView player = new ImageView("file:" + playerObject.getImage());
    @FXML
    public ListView inventory = new ListView();

    public void initialize() {
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
        //plas1.setImage(new Image("file:" + "src/sample/presentation/pictures/plastic/cleaningPlastic.png"));
        generatePlasticInRoom(Main.game.placePlastic());
        showRoadBuilderRoad();
    }

    public void generatePlasticInRoom(List<Plastic> plasticList) {
        clearPlasticInRoom();
        ImageView[] plas = {plas1, plas2, plas3, plas4, plas5, plas6, plas7, plas8, plas9, plas10, plas11, plas12, plas13, plas14, plas15,
                plas16, plas17, plas18, plas19, plas20};

        for (int i = 0; i < plasticList.size(); i++) {
            if (plasticList.get(i) != null) {
                plas[i].setImage(new Image("file:" + plasticList.get(i).getImage()));
                plas[i].setTranslateX(plasticList.get(i).getPosition()[0]);
                plas[i].setTranslateY(plasticList.get(i).getPosition()[1]);
                plas[i].setFitHeight(30);
                plas[i].setFitWidth(30);
            }
        }
    }

    public void clearPlasticInRoom() {
        ImageView[] plas = {plas1, plas2, plas3, plas4, plas5, plas6, plas7, plas8, plas9, plas10, plas11, plas12, plas13, plas14, plas15,
                plas16, plas17, plas18, plas19, plas20};

        for (ImageView image: plas) {
            image.setTranslateX(3000);
            image.setTranslateY(3000);
        }
    }

    // Lad vær med at slette det under for nu
    /*
    public void generatePlasticInRoom(List<Plastic> plasticList) {
        plasticRoom = new ArrayList<>();

        for (int i = 0; i < plasticList.size(); i++) {
            plasticRoom.add(new ImageView("file:" + plasticList.get(i).getImage()));
            plasticRoom.get(i).setX(plasticList.get(i).getPosition()[0]);
            plasticRoom.get(i).setY(plasticList.get(i).getPosition()[1]);
        }
    }
    */

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

    // Need this method
    public void updateInventory() {
        inventoryObservable.removeAll();
        ArrayList<Plastic> playersInv = new ArrayList<>(playerObject.getPlasticInv());
        for (int i = 0; i < playerObject.getPlasticInv().size(); i++) {
            inventoryObservable.add((new ImageView("file:" + playersInv.get(i).getImage())));
        }
        inventory.setItems(inventoryObservable);
        // inventory.setOpacity(100);
    }

    public void collectPlastic(List<Plastic> plasticList) {
        if (playerObject.getPlasticInv().size() <= 10) {
            ImageView[] plas = {plas1, plas2, plas3, plas4, plas5, plas6, plas7, plas8, plas9, plas10, plas11, plas12, plas13, plas14, plas15,
                    plas16, plas17, plas18, plas19, plas20};
            for (int i = 0; i < plas.length; i++) {
                if (plas[i].getTranslateX() - 15 <= player.getTranslateX() && plas[i].getTranslateX() + 15 >= player.getTranslateX()) {
                    if (plas[i].getTranslateY() - 15 <= player.getTranslateY() && plas[i].getTranslateY() + 15 >= player.getTranslateY()) {
                        playerObject.plasticCollect(plasticList.get(i), Main.game.getCurrentRoom());
                        plas[i].setTranslateX(3000);
                        updateInventory();
                    }
                }
            }
        } else {
            System.out.println("I can't lift more!!!!");
        }
    }

    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
            case W:
                timer.start();
                north = true;
                west = false;
                east = false;
                System.out.println(player.getTranslateY());
                break;
            case DOWN:
            case S:
                timer.start();
                south = true;
                west = false;
                east = false;
                System.out.println(player.getTranslateY());
                break;
            case LEFT:
            case A:
                timer.start();
                east = true;
                north = false;
                south = false;
                System.out.println(player.getTranslateX());
                break;
            case RIGHT:
            case D:
                timer.start();
                west = true;
                north = false;
                south = false;
                System.out.println(player.getTranslateX());
                break;
            case SPACE:
                collectPlastic(Main.game.placePlastic());
        }
        NewRoom();
    }


    private void NewRoom() {
        if (player.getTranslateY() < -208 && player.getTranslateX() > -150 && player.getTranslateX() < -10) {
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
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeSouth() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Farm || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Sdu)) {
            player.setTranslateY(-204);
        }
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeWest() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateX(330);
        }
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeEast() {
        if (!(Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm)) {
            player.setTranslateX(-330);
        }
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void showRoadBuilderRoad() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadView.setViewport(new Rectangle2D(-681 + (RoadBuilder.getInventoryCount()*22.7), 0, 681, 69));
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

    // Det under er alle plastik imageviews. Det er placeret her, da der er alt for mange, og der skal undersøges om det ikke kan gøres på en smart måde, så vi
    // ikke skal have 20 imageview

    @FXML
    private ImageView plas1 = new ImageView();
    @FXML
    private ImageView plas2 = new ImageView();
    @FXML
    private ImageView plas3 = new ImageView();
    @FXML
    private ImageView plas4 = new ImageView();
    @FXML
    private ImageView plas5 = new ImageView();
    @FXML
    private ImageView plas6 = new ImageView();
    @FXML
    private ImageView plas7 = new ImageView();
    @FXML
    private ImageView plas8 = new ImageView();
    @FXML
    private ImageView plas9 = new ImageView();
    @FXML
    private ImageView plas10 = new ImageView();
    @FXML
    private ImageView plas11 = new ImageView();
    @FXML
    private ImageView plas12 = new ImageView();
    @FXML
    private ImageView plas13 = new ImageView();
    @FXML
    private ImageView plas14 = new ImageView();
    @FXML
    private ImageView plas15 = new ImageView();
    @FXML
    private ImageView plas16 = new ImageView();
    @FXML
    private ImageView plas17 = new ImageView();
    @FXML
    private ImageView plas18 = new ImageView();
    @FXML
    private ImageView plas19 = new ImageView();
    @FXML
    private ImageView plas20 = new ImageView();

}