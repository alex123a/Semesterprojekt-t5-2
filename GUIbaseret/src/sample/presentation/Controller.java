package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.domain.*;
import sample.domain.NPCer.Farmer;
import sample.domain.NPCer.Mechanic;
import sample.domain.NPCer.Professor;
import sample.domain.PlasticElements.Plastic;
import sample.domain.Rooms.*;
import javafx.geometry.Orientation;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    public static String background;
    public static Road road = new Road();
    public static Player playerObject = new Player();
    public static RoadBuilder roadBuilder = new RoadBuilder();
    public static Professor professorObject = new Professor("Professor");
    public static Mechanic mechanicObject = new Mechanic("Mechanic");
    public static Farmer farmerObject = new Farmer("Farmer");
    private boolean north, south, east, west;
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private FireAnimation fireAnimation = new FireAnimation();
    private double[] numbersPlayer;
    private double[] numbersFire;
    private long animationWalk = 0;
    private boolean gameNotStarted = true;
    private ObservableList<ImageView> inventoryObservable = FXCollections.observableList(new ArrayList<ImageView>());
    private long animationFireSmoke = 0;
    private long animationDriving = 0;
    private int numberOfMovement = 0;

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
    @FXML
    public ImageView smoke = new ImageView("file:src/sample/presentation/pictures/buildSmoke.png");
    @FXML
    public ImageView smokeBrokenMachine = new ImageView("file:src/sample/presentation/pictures/fireSmoke-1.png");

    @FXML
    public ImageView professorNpc = new ImageView("file:" + professorObject.getImage());

    @FXML
    public ImageView mechanicNpc = new ImageView("file:"+ mechanicObject.getImage());

    @FXML
    public ImageView farmerNpc = new ImageView("file:" + farmerObject.getImage());


    public void initialize() {
        if (gameNotStarted) {
            inventory.setOpacity(0);
        }
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/StartScreen.png"));
        showRoadBuilderRoad();
    }

    public void generatePlasticInRoom(List<Plastic> plasticList) {
        clearPlasticInRoom();
        ImageView[] plas = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                plast16, plast17, plast18, plast19, plast20};

        for (int i = 0; i < plasticList.size(); i++) {
            if (plasticList.get(i) != null) {
                plas[i].setImage(new Image("file:" + plasticList.get(i).getImage()));
                plas[i].setTranslateX(plasticList.get(i).getPosition()[0]);
                plas[i].setTranslateY(plasticList.get(i).getPosition()[1]);
                plas[i].setFitHeight(30);
                plas[i].setFitWidth(30);
                // Kode til at give plastik ny position hvis de falder inden for no go zonerne.
                if (moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 0, -2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 0, 2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), -2, 0)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 2, 0)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                }
            }
        }
    }

    public void clearPlasticInRoom() {
        ImageView[] plas = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                plast16, plast17, plast18, plast19, plast20};

        for (ImageView image : plas) {
            image.setTranslateX(3000);
            image.setTranslateY(3000);
        }
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (north && player.getTranslateY() > -220) {
                playerAnimation.setDirection(direction[0]);
                if (!moveBlock(player.getTranslateX(), player.getTranslateY(), 0, -2)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateY(player.getTranslateY() - 2.5);
                }
                animationWalk++;
            }
            if (south && player.getTranslateY() < 220) {
                playerAnimation.setDirection(direction[1]);
                if (!moveBlock(player.getTranslateX(), player.getTranslateY(), 0, 2)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateY(player.getTranslateY() + 2.5);

                }
                animationWalk++;
            }
            if (east && player.getTranslateX() > -340) {
                playerAnimation.setDirection(direction[2]);
                if (!moveBlock(player.getTranslateX(), player.getTranslateY(), -2, 0)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateX(player.getTranslateX() - 2.5);
                }
                animationWalk++;
            }
            if (west && player.getTranslateX() < 340) {
                playerAnimation.setDirection(direction[3]);
                if (!moveBlock(player.getTranslateX(), player.getTranslateY(), 2, 0)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateX(player.getTranslateX() + 2.5);

                }
                animationWalk++;
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
            ImageView[] plas = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                    plast16, plast17, plast18, plast19, plast20};
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

    public boolean moveBlock(double objx, double objy, int x, int y) {
        boolean cantMove = false;
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            // tree in top left conor.
            Rectangle topLeftTrees = new Rectangle(-340, -220, 180, 120);
            if (topLeftTrees.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle leftButtomWall = new Rectangle(-340, 205, 300, 30);
            if (leftButtomWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle rightButtomWall = new Rectangle(20, 205, 350, 30);
            if (rightButtomWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        if (Main.game.getCurrentRoom() instanceof Beach) {
            Rectangle ocean = new Rectangle(-340.5, -220, 240, 500);
            if (ocean.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle ladyNoOne = new Rectangle(32, 40, 80, 50);
            if (ladyNoOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle ladyNoTwo = new Rectangle(155, 170, 80, 40);
            if (ladyNoTwo.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }

        if (Main.game.getCurrentRoom() instanceof Farm) {
            // Field
            Rectangle field = new Rectangle(-176, -64, 250, 160);
            if (field.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle barn = new Rectangle(115.5, -220, 300, 185);
            if (barn.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle tractor = new Rectangle(55.5, -220, 60, 50);
            if (tractor.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }

        if (Main.game.getCurrentRoom() instanceof Sdu) {
            Rectangle topLeftWall = new Rectangle(-340, -220, 300, 30);
            if (topLeftWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topRightWall = new Rectangle(27.5, -220, 400, 30);
            if (topRightWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle closetTopOne = new Rectangle(-300, -75, 80, 110);
            if (closetTopOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle closetTopTwo = new Rectangle(-210, -75, 75, 110);
            if (closetTopTwo.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle closetButtomOne = new Rectangle(-300, 75, 80, 110);
            if (closetButtomOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle closetButtomTwo = new Rectangle(-210, 75, 75, 110);
            if (closetButtomTwo.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }

        if (Main.game.getCurrentRoom() instanceof Park) {
            Rectangle buttomLeftGarden = new Rectangle(-340, -1, 190, 210);
            if (buttomLeftGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topLeftGarden = new Rectangle(-340, -221, 185, 145);
            if (topLeftGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            // Labyrinten
            Rectangle labyrinthOuterTop = new Rectangle(130, -221, 30, 145);
            if (labyrinthOuterTop.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthOuterButtom = new Rectangle(187.5,-111,200,30);
            if(labyrinthOuterButtom.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthFirstWall = new Rectangle(160,-150,70,30);
            if(labyrinthFirstWall.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthSecondWall = new Rectangle(255,-150,30,55);
            if(labyrinthSecondWall.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthVertiWallOne = new Rectangle(180,-221,30,60);
            if(labyrinthVertiWallOne.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthVertiWallTwo = new Rectangle(233,-223,30,66);
            if(labyrinthVertiWallTwo.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthHoriWallOne = new Rectangle(265,-213,50,30);
            if(labyrinthHoriWallOne.contains(objx + x, objy + y)){
                cantMove = true;
            }
            Rectangle labyrinthVertiWallThree = new Rectangle(298,-212,30,70);
            if(labyrinthVertiWallThree.contains(objx + x, objy + y)){
                cantMove = true;
            }

            Rectangle leftButtomRightGarden = new Rectangle(130, 9, 210, 250);
            if (leftButtomRightGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topMiddelGarden = new Rectangle(-43, -221, 80, 110);
            if (topMiddelGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle buttomMiddelGarden = new Rectangle(-40, 59, 80, 150);
            if (buttomMiddelGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle buttomMiddelTrees = new Rectangle(-70, -6, 122, 80);
            if (buttomMiddelTrees.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topMiddelTrees = new Rectangle(-75, -126, 122, 70);
            if (topMiddelTrees.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        if (Main.game.getCurrentRoom() instanceof Town) {
            Rectangle topOneHouse = new Rectangle(-277, -221, 100, 75);
            if (topOneHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topTwoHouse = new Rectangle(-62, -221, 100, 75);
            if (topTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topThreeHouse = new Rectangle(58, -221, 100, 75);
            if (topThreeHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topFourHouse = new Rectangle(178, -221, 100, 75);
            if (topFourHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle middelOneHouse = new Rectangle(-110, -101, 100, 100);
            if (middelOneHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle middelTwoHouse = new Rectangle(60, -111, 100, 110);
            if (middelTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle buttomOneHouse = new Rectangle(-272, 56.5, 90, 100);
            if (buttomOneHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle buttomTwoHouse = new Rectangle(-44, 54, 90, 100);
            if (buttomTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle threeTwoHouse = new Rectangle(83, 54, 85, 100);
            if (threeTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }


        return cantMove;
    }

    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
            case W:
                if (gameNotStarted) {
                    north = false;
                    break;
                }
                timer.start();
                north = true;
                west = false;
                east = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case DOWN:
            case S:
                if (gameNotStarted) {
                    south = false;
                    break;
                }
                timer.start();
                south = true;
                west = false;
                east = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case LEFT:
            case A:
                if (gameNotStarted) {
                    east = false;
                    break;
                }
                timer.start();
                east = true;
                north = false;
                south = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case RIGHT:
            case D:
                if (gameNotStarted) {
                    west = false;
                    break;
                }
                timer.start();
                west = true;
                north = false;
                south = false;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case SPACE:
                if (gameNotStarted) {
                    StartGame();
                    inventory.setOpacity(0.4);
                    gameNotStarted = false;
                }
                if (player.getTranslateX() > roadBuilderView.getTranslateX()-50 && player.getTranslateX() < roadBuilderView.getTranslateX()+50 && player.getTranslateY() > roadBuilderView.getTranslateY()-50 && player.getTranslateY() < roadBuilderView.getTranslateY()+50) {
                    if (roadBuilder.getInventoryCount() >= 19) {
                        roadBuilder.damagedMachine();
                    }

                    if (roadBuilder.getDamaged() > 0) {
                        System.out.println("Repair me :)");
                    } else {
                        numberOfMovement = playerObject.getPlasticInv().size() * 4;
                        Main.game.givePlastic();
                        updateInventory();
                        EndGame();
                    }
                }
                collectPlastic(Main.game.placePlastic());

        }
        NewRoom();
    }

    private void EndGame() {
        if (roadBuilder.getInventoryCount() == Game.getRoadDone()) {
            System.out.println("Du er færdig");
        }
    }

    private void StartGame() {
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/RoadBuild.png"));
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
        professorNpc.setImage(new Image("file:" + professorObject.getImage()));
        professorNpc.setTranslateX(3000);
        mechanicNpc.setImage(new Image("file:" + mechanicObject.getImage()));
        mechanicNpc.setTranslateX(3000);
        farmerNpc.setImage(new Image("file:" + farmerObject.getImage()));
        farmerNpc.setTranslateX(3000);
        //plas1.setImage(new Image("file:" + "src/sample/presentation/pictures/plastic/cleaningPlastic.png"));
        generatePlasticInRoom(Main.game.placePlastic());
        smoke.setImage(new Image("file:src/sample/presentation/pictures/buildSmoke.png"));
        smokeBrokenMachine.setImage(new Image("file:src/sample/presentation/pictures/fireSmoke-1.png"));
        movementMachine();
        smokeMachine();
        smokeBrokenMachine();
        Timer.setStartTime(); // tid starter til highscorelisten
    }

    private void NewRoom() {
        //North
        if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateY() < -202 && player.getTranslateX() > -142.5 && player.getTranslateX() < -82.5) {
            changeNorth();
        } else if (Main.game.getCurrentRoom() instanceof Sdu && player.getTranslateY() < -158 && player.getTranslateX() > -45.5 && player.getTranslateX() < 14) {
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
        } else if (Main.game.getCurrentRoom() instanceof Farm && player.getTranslateX() < -328 && player.getTranslateY() > -96 && player.getTranslateY() < -66) {
            changeWest();
        } else if (Main.game.getCurrentRoom() instanceof Town && player.getTranslateX() < -328 && player.getTranslateY() > -53 && player.getTranslateY() < -15) {
            changeWest();
            //East
        } else if (Main.game.getCurrentRoom() instanceof Beach && player.getTranslateX() > 328 && player.getTranslateY() > -116.5 && player.getTranslateY() < -61.5) {
            changeEast();
        } else if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateX() > 328 && player.getTranslateY() > -116.5 && player.getTranslateY() < -61.5) {
            changeEast();
        } else if (Main.game.getCurrentRoom() instanceof Park && player.getTranslateX() > 330 && player.getTranslateY() > -56 && player.getTranslateY() < -15) {
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
        showFarmer();
        showProfessor();
        showMechanic();
    }

    public void changeSouth() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Farm || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Sdu)) {
            player.setTranslateY(-200);
        }
        if (Main.game.getCurrentRoom() instanceof Park) {
            player.setTranslateX(-117.5);
        }
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            player.setTranslateY(-150);
        }
        Game.changedRoom = "south";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeWest() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateX(327);
        }
        if (Main.game.getCurrentRoom() instanceof Town){
            player.setTranslateY(-77);
        }
        if (Main.game.getCurrentRoom() instanceof Farm){
            player.setTranslateY(-33.5);
        }
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeEast() {
        if (!(Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm)) {
            player.setTranslateX(-327);
        }
        if (Main.game.getCurrentRoom() instanceof RoadBuild){
            player.setTranslateY(-30);
        }
        if (Main.game.getCurrentRoom() instanceof Park){
            player.setTranslateY(-76);
        }
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void smokeMachine() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (Main.game.getCurrentRoom() instanceof RoadBuild) {
                if (animationFireSmoke % 20 == 0) {
                    numbersFire = fireAnimation.changePic();
                    smoke.setViewport(new Rectangle2D(numbersFire[0], numbersFire[1], numbersFire[2], numbersFire[3]));
                    double smokeHeight = roadBuilderView.getTranslateY() - numbersFire[0] / 22 - 40;
                    double smokeWidth = roadBuilderView.getTranslateX() + numbersFire[0] / 22 + 43;
                    smoke.setTranslateY(smokeHeight);
                    smoke.setTranslateX(smokeWidth);
                }
                animationFireSmoke++;
            } else {
                smoke.setTranslateX(3000);
            }
        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame); //This was the offending line.
        timeline.play();
    }

    public void smokeBrokenMachine() {

    }

    public void movementMachine() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (numberOfMovement != 0 && Main.game.getCurrentRoom() instanceof RoadBuild) {
                if (animationDriving % 5 == 0) {
                    roadView.setViewport(new Rectangle2D(-681 + (roadBuilder.getInventoryCount() - numberOfMovement / 4) * 18.9166 + 113.5, 0, 681, 69));
                    roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
                    roadBuilderView.setTranslateX((300 - ((roadBuilder.getInventoryCount() - numberOfMovement / 4) * 18.9166 + 113.5) + 90));
                    --numberOfMovement;
                }
                animationDriving++;
            } else {
                showRoadBuilderRoad();
            }

        });

        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame); //This was the offending line.
        timeline.play();
    }

    public void showRoadBuilderRoad() {
        showFarmer();
        showProfessor();
        showMechanic();
        //roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
        //roadBuilderView.setViewport(new Rectangle2D(0,0,484,323));
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadView.setViewport(new Rectangle2D(-681 + (roadBuilder.getInventoryCount() * 18.9166 + 113.5), 0, 681, 69));
        } else {
            roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
        }
        showRoadBuilder();
    }

    public void showRoadBuilder() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
            roadBuilderView.setTranslateX(300 - ((roadBuilder.getInventoryCount() * 18.9166 + 113.5) - 90));
        } else {
            roadBuilderView.setViewport(new Rectangle2D(-484, 0, 484, 323));
        }
    }

    public void showProfessor(){
        professorNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Sdu){
            professorNpc.setTranslateX(30);
        }
    }

    public void showMechanic() {
        mechanicNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Town){
            mechanicNpc.setTranslateX(169);
            mechanicNpc.setTranslateY(20);
        }
    }

    public void showFarmer(){
        farmerNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Farm){
            farmerNpc.setTranslateX(190);
            farmerNpc.setTranslateY(2);
        }
    }
    // Det under er alle plastik imageviews. Det er placeret her, da der er alt for mange, og der skal undersøges om det ikke kan gøres på en smart måde, så vi
    // ikke skal have 20 imageview

    @FXML
    private ImageView plast1 = new ImageView();
    @FXML
    private ImageView plast2 = new ImageView();
    @FXML
    private ImageView plast3 = new ImageView();
    @FXML
    private ImageView plast4 = new ImageView();
    @FXML
    private ImageView plast5 = new ImageView();
    @FXML
    private ImageView plast6 = new ImageView();
    @FXML
    private ImageView plast7 = new ImageView();
    @FXML
    private ImageView plast8 = new ImageView();
    @FXML
    private ImageView plast9 = new ImageView();
    @FXML
    private ImageView plast10 = new ImageView();
    @FXML
    private ImageView plast11 = new ImageView();
    @FXML
    private ImageView plast12 = new ImageView();
    @FXML
    private ImageView plast13 = new ImageView();
    @FXML
    private ImageView plast14 = new ImageView();
    @FXML
    private ImageView plast15 = new ImageView();
    @FXML
    private ImageView plast16 = new ImageView();
    @FXML
    private ImageView plast17 = new ImageView();
    @FXML
    private ImageView plast18 = new ImageView();
    @FXML
    private ImageView plast19 = new ImageView();
    @FXML
    private ImageView plast20 = new ImageView();

}