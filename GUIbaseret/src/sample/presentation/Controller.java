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
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    public static DialogNPC dialog = new DialogNPC();
    private boolean north, south, east, west;
    private int spaceCount = 0;
    private boolean farmerTalk = false;
    private boolean professorTalk = false;
    private boolean mechanicTalk = false;
    private boolean talking = false;
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private FireAnimation fireAnimation = new FireAnimation();
    private BrokeMachineAnimation brokeMachineAnimation = new BrokeMachineAnimation();
    private NoAccess noAccess = new NoAccess();
    private double[] numbersPlayer;
    private double[] numbersFire;
    private double[] numbersBrokenFire;
    private long animationWalk = 0L;
    private boolean gameNotStarted = true;
    private ObservableList<ImageView> inventoryObservable = FXCollections.observableList(new ArrayList<ImageView>());
    private long animationFireSmoke = 0L;
    private long animationFireSmokeBrokenMachine = 0L;
    private long animationDriving = 0L;
    private int numberOfMovement = 0;
    private boolean talkingRoadbuilder = false;
    private int counterRepair = 0;
    private boolean doneRepairing = true;
    private AudioMusicPlayer backgroundMusic = new AudioMusicPlayer("src/sample/presentation/audio/BackgroundMusic.wav");
    private AudioMusicPlayer roadbuilderCrashSound = new AudioMusicPlayer("src/sample/presentation/audio/RoadbuildCrash.wav");
    private AudioMusicPlayer roadbuilderMovingSound = new AudioMusicPlayer("src/sample/presentation/audio/RoadbuilderMovingSound.wav");


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
    public ImageView mechanicNpc = new ImageView("file:" + mechanicObject.getImage());
    @FXML
    public ImageView farmerNpc = new ImageView("file:" + farmerObject.getImage());
    @FXML
    public ImageView dialogBox = new ImageView("file:" + dialog.getImage());
    @FXML
    private ImageView toolsetImg = new ImageView("file:" + "src/sample/presentation/pictures/npc/Tool.png");
    @FXML
    private Text NPCTextLine;
    @FXML
    private Text NPCTextLine1;
    @FXML
    private Text NPCTextLine2;
    @FXML
    private Text playerText;

    public void initialize() {
        if (gameNotStarted) {
            inventory.setOpacity(0);
        }
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/StartScreen.png"));
        professorNpc.setImage(new Image("file:" + professorObject.getImage()));
        mechanicNpc.setImage(new Image("file:" + mechanicObject.getImage()));
        farmerNpc.setImage(new Image("file:" + farmerObject.getImage()));
        dialogBox.setImage(new Image("file:" + dialog.getImage()));
        backgroundMusic.musicPlayerInfinity();
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
                if (noAccess.moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 0, -2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 0, 2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), -2, 0)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plas[i].getTranslateX(), plas[i].getTranslateY(), 2, 0)) {
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
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), 0, -2)) {
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
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), 0, 2)) {
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
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), -2, 0)) {
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
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), 2, 0)) {
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

    public void movePlayer(KeyEvent keyEvent) throws InterruptedException {
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

                if (Main.game.getCurrentRoom() instanceof RoadBuild && talkingRoadbuilder && spaceCount != 0) {
                    if (spaceCount == 1) {
                        talking = true;
                        talkNPC(playerText, "Road builder", 3);
                        spaceCount++;
                    } else if (spaceCount == 2) {
                        hideDialogBox();
                        spaceCount = 0;
                    }
                } else if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateX() > roadBuilderView.getTranslateX() - 50 && player.getTranslateX() < roadBuilderView.getTranslateX() + 50 && player.getTranslateY() > roadBuilderView.getTranslateY() - 50 && player.getTranslateY() < roadBuilderView.getTranslateY() + 50) {
                    if (roadBuilder.getInventoryCount() >= 19 && roadBuilder.isNotDamagedBefore()) {
                        roadBuilder.damagedMachine();
                        roadBuilder.setNotDamagedBefore(false);
                        roadbuilderCrashSound.AudioPlayer();
                    } else if (playerObject.getHaveToolset() && roadBuilder.getDamaged() > 0) {
                        repairTheMachine();
                    }

                    if (roadBuilder.getDamaged() > 0 && !playerObject.getHaveToolset()) {
                        NPCTextLine.setTranslateY(-210);
                        NPCTextLine.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
                        NPCTextLine1.setTranslateY(-190);
                        NPCTextLine1.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
                        NPCTextLine2.setTranslateY(-170);
                        NPCTextLine2.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
                        playerText.setTranslateY(-130);
                        playerText.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
                        if (spaceCount == 0 && roadBuilder.getDamaged() > 0) {
                            talking = true;
                            talkNPC(NPCTextLine, "Road builder", 0);
                            talkNPC(NPCTextLine1, "Road builder", 1);
                            talkNPC(NPCTextLine2, "Road builder", 2);
                            spaceCount++;
                            talkingRoadbuilder = true;
                        }

                    } else if (roadBuilder.getDamaged() == 0) {
                        numberOfMovement = playerObject.getPlasticInv().size() * 4;
                        if (playerObject.getPlasticInv().size() > 0) {
                            Main.game.givePlastic();
                            roadbuilderMovingSound.AudioPlayer();
                        }
                        updateInventory();
                        EndGame();
                    }
                } else if (Main.game.getCurrentRoom() instanceof Farm && player.getTranslateX() > farmerNpc.getTranslateX() - 30 && player.getTranslateX() < farmerNpc.getTranslateX() + 30 && player.getTranslateY() > farmerNpc.getTranslateY() - 30 && player.getTranslateY() < farmerNpc.getTranslateY() + 30) {
                    showDialogBox();
                } else if (Main.game.getCurrentRoom() instanceof Sdu && player.getTranslateX() > professorNpc.getTranslateX() - 30 && player.getTranslateX() < professorNpc.getTranslateX() + 30 && player.getTranslateY() > professorNpc.getTranslateY() - 30 && player.getTranslateY() < professorNpc.getTranslateY() + 30) {
                    showDialogBox();
                } else if (Main.game.getCurrentRoom() instanceof Town && player.getTranslateX() > mechanicNpc.getTranslateX() - 30 && player.getTranslateX() < mechanicNpc.getTranslateX() + 30 && player.getTranslateY() > mechanicNpc.getTranslateY() - 30 && player.getTranslateY() < mechanicNpc.getTranslateY() + 30) {
                    showDialogBox();
                }
                collectPlastic(Main.game.placePlastic());
        }
        NewRoom();
    }

    public void repairTheMachine() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (counterRepair % 60 == 0 && roadBuilder.getDamaged() > 0) {
                playerObject.getToolset().repairMachine();
                dialogBox.setTranslateY(-170);
                NPCTextLine1.setText(100 - roadBuilder.getDamaged() + "% repaired");
            } else if (doneRepairing && roadBuilder.getDamaged() == 0) {
                hideDialogBox();
                toolsetImg.setTranslateX(3000);
                doneRepairing = false;
            }
            counterRepair++;
        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    private void EndGame() {
        if (roadBuilder.getInventoryCount() == Game.getRoadDone()) {
            System.out.println("You are finished");
        }
    }

    private void StartGame() {
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/RoadBuild.png"));
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
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
        hideDialogBox();
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
        hideDialogBox();
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeWest() {
        if (!(Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Park)) {
            player.setTranslateX(327);
        }
        if (Main.game.getCurrentRoom() instanceof Town) {
            player.setTranslateY(-77);
        }
        if (Main.game.getCurrentRoom() instanceof Farm) {
            player.setTranslateY(-33.5);
        }
        Game.changedRoom = "west";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideDialogBox();
        showRoadBuilderRoad();
        generatePlasticInRoom(Main.game.placePlastic());
    }

    public void changeEast() {
        if (!(Main.game.getCurrentRoom() instanceof Sdu || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm)) {
            player.setTranslateX(-327);
        }
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            player.setTranslateY(-30);
        }
        if (Main.game.getCurrentRoom() instanceof Park) {
            player.setTranslateY(-76);
        }
        Game.changedRoom = "east";
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideDialogBox();
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
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public void smokeBrokenMachine() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (Main.game.getCurrentRoom() instanceof RoadBuild && roadBuilder.getDamaged() > 0) {
                if (animationFireSmokeBrokenMachine % 20 == 0) {
                    numbersBrokenFire = brokeMachineAnimation.changePic();
                    smokeBrokenMachine.setViewport(new Rectangle2D(numbersBrokenFire[0], numbersBrokenFire[1], numbersBrokenFire[2], numbersBrokenFire[3]));
                    double smokeHeight = roadBuilderView.getTranslateY() - 11;
                    double smokeWidth = roadBuilderView.getTranslateX() - 36;
                    smokeBrokenMachine.setTranslateY(smokeHeight);
                    smokeBrokenMachine.setTranslateX(smokeWidth);
                }
                animationFireSmokeBrokenMachine++;
            } else {
                smokeBrokenMachine.setTranslateX(3000);
            }
        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
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
                roadbuilderMovingSound.AudioStop();
            }

        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public void showRoadBuilderRoad() {
        showFarmer();
        showProfessor();
        showMechanic();
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

    public void showProfessor() {
        professorNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Sdu) {
            professorNpc.setTranslateX(30);
        }
    }

    public void showMechanic() {
        mechanicNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Town) {
            mechanicNpc.setTranslateX(169);
            mechanicNpc.setTranslateY(20);
        }
    }

    public void showFarmer() {
        farmerNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Farm) {
            farmerNpc.setTranslateX(190);
            farmerNpc.setTranslateY(2);
        }
    }

    public void hideDialogBox() {
        spaceCount = 0;
        NPCTextLine.setText("");
        NPCTextLine1.setText("");
        NPCTextLine2.setText("");
        playerText.setText("");
        dialogBox.setTranslateY(3000);
        talking = false;
    }

    public void showDialogBox() {
        NPCTextLine.setTranslateY(-210);
        NPCTextLine.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine1.setTranslateY(-190);
        NPCTextLine1.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine2.setTranslateY(-170);
        NPCTextLine2.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        playerText.setTranslateY(-130);
        playerText.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        if (Main.game.getCurrentRoom() instanceof Farm) {
            if (spaceCount == 0 && !farmerTalk) {
                talking = true;
                talkNPC(NPCTextLine, "farmer", 0);
                talkNPC(NPCTextLine1, "farmer", 1);
                talkNPC(NPCTextLine2, "farmer", 2);
                spaceCount++;
            } else if (spaceCount == 1) {
                talkNPC(playerText, "farmer", 3);
                spaceCount++;
            } else if (spaceCount == 2) {
                talkNPC(NPCTextLine, "farmer", 4);
                NPCTextLine1.setText("");
                NPCTextLine2.setText("");
                spaceCount++;
            } else if (spaceCount == 3) {
                talkNPC(playerText, "farmer", 5);
                spaceCount++;
            } else if (spaceCount == 4) {
                talkNPC(NPCTextLine, "farmer", 6);
                farmerTalk = playerObject.addPlasticInv();
                if (!farmerTalk) {
                    talkNPC(NPCTextLine, "farmer", 7);
                    playerText.setText("");
                }
                updateInventory();
                spaceCount++;
            } else if (spaceCount == 5) {
                if (!farmerTalk) {
                    hideDialogBox();
                    spaceCount = 0;
                } else if (farmerTalk) {
                    hideDialogBox();
                    farmerTalk = true;
                }
            }
        } else if (Main.game.getCurrentRoom() instanceof Sdu) {
            if (spaceCount == 0 && !professorTalk) {
                talking = true;
                talkNPC(NPCTextLine, "professor", 0);
                talkNPC(NPCTextLine1, "professor", 1);
                talkNPC(NPCTextLine2, "professor", 2);
                spaceCount++;
            } else if (spaceCount == 1) {
                talkNPC(playerText, "professor", 3);
                spaceCount++;
            } else if (spaceCount == 2) {
                talkNPC(NPCTextLine, "professor", 4);
                NPCTextLine1.setText("");
                NPCTextLine2.setText("");
                spaceCount++;
            } else if (spaceCount == 3) {
                hideDialogBox();
                professorTalk = true;
            }
        } else if (Main.game.getCurrentRoom() instanceof Town) {
            if (roadBuilder.getDamaged() > 0) {
                if (spaceCount == 0 && !mechanicTalk) {
                    talkNPC(NPCTextLine, "mechanic", 0);
                    talkNPC(NPCTextLine1, "mechanic", 1);
                    spaceCount++;
                } else if (spaceCount == 1) {
                    talkNPC(playerText, "mechanic", 2);
                    spaceCount++;
                } else if (spaceCount == 2) {
                    talkNPC(NPCTextLine, "mechanic", 3);
                    NPCTextLine1.setText("");
                    spaceCount++;
                    playerObject.setToolset(mechanicObject.giveToolset());
                    toolsetImg.setImage(new Image("file:" + playerObject.getToolset().getImage()));
                    toolsetImg.setTranslateX(100);
                    toolsetImg.setTranslateY(100);
                } else if (spaceCount == 3) {
                    hideDialogBox();
                    mechanicTalk = true;
                }
            }
        }
    }

    private void talkNPC(Text npcText, String npcType, int index) {
        dialogBox.setTranslateY(-170);
        npcText.setText(dialog.getNPCText(npcType, index));
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