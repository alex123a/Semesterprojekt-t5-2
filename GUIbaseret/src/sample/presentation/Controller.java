+package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.domain.*;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;
import javafx.geometry.Orientation;

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
    private long animationWalk = 0;
    private boolean gameNotStarted = true;
    private ObservableList<ImageView> inventoryObservable = FXCollections.observableList(new ArrayList<ImageView>());

    @FXML
    private ImageView backgroundRoom = new ImageView("file:");
    @FXML
    public ImageView roadView = new ImageView("file:" + road.getImage());
    @FXML
    public ImageView roadBuilderView = new ImageView("file:" + roadBuilder.getImage());
    @FXML
    public ImageView player = new ImageView("file:" + playerObject.getImage());
    @FXML
    public ListView inventory = new ListView();


    public void initialize() {
        if (gameNotStarted) {
            inventory.setOpacity(0);
        }
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/StartScreen.png"));
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

    // Need this method
    public void updateInventory() {
        inventory.getItems().clear();
        inventoryObservable.removeAll();
        ArrayList<Plastic> playersInv = new ArrayList<>(playerObject.getPlasticInv());
        for (int i = 0; i < playerObject.getPlasticInv().size(); i++) {
            ImageView imageView = new ImageView("file:" + playersInv.get(i).getImage());
            imageView.setFitHeight(35);
            imageView.setFitWidth(15);
            inventoryObservable.add(imageView);
        }
        inventory.setItems(inventoryObservable);
        inventory.setOrientation(Orientation.HORIZONTAL);
        // inventory.setOpacity(100);
    }

    public void collectPlastic(List<Plastic> plasticList) {
        if (playerObject.getPlasticInv().size() < 10) {
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
                if (gameNotStarted) {
                    north = false;
                    break;
                }
                timer.start();
                north = true;
                System.out.println("x: " + player.getTranslateX());
                System.out.println("y: " + player.getTranslateY());
                break;
            case S:
                if (gameNotStarted) {
                    south = false;
                    break;
                }
                timer.start();
                south = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
            case A:
                if (gameNotStarted) {
                    east = false;
                    break;
                }
                timer.start();
                east = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
            case D:
                if (gameNotStarted) {
                    west = false;
                    break;
                }
                timer.start();
                west = true;
                System.out.println("y: " + player.getTranslateX());
                System.out.println("x: " + player.getTranslateY());
                break;
            case SPACE:
                if (gameNotStarted) {
                    StartGame();
                    inventory.setOpacity(0.4);
                    gameNotStarted = false;
                }
                collectPlastic(Main.game.placePlastic());
        }
        NewRoom();

    }

    private void StartGame() {
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/RoadBuild.png"));
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
        //plas1.setImage(new Image("file:" + "src/sample/presentation/pictures/plastic/cleaningPlastic.png"));
        generatePlasticInRoom(Main.game.placePlastic());
        Timer.setStartTime(); // tid starter til highscorelisten
    }

    private void NewRoom() {
        //North
        if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateY() < -202 && player.getTranslateX() > -142.5 && player.getTranslateX() < -82.5) {
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


    public void showRoadBuilder() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
            if (RoadBuilder.getInventoryCount() < 5) {
                roadBuilderView.setTranslateX(300);
            } else {
                roadBuilderView.setTranslateX(300 - ((RoadBuilder.getInventoryCount() * 22.7)-90));
            }
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
