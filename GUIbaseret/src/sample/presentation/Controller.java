package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.domain.*;
import sample.domain.NPCer.Farmer;
import sample.domain.NPCer.Fisherman;
import sample.domain.NPCer.Mechanic;
import sample.domain.NPCer.OldLady;
import sample.domain.NPCer.Professor;
import sample.domain.PlasticElements.*;
import sample.domain.Rooms.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    public static Fisherman fishermanObject = new Fisherman("Fisherman");
    public static OldLady oldLadyObject = new OldLady("OldLady");
    public static DialogNPC dialog = new DialogNPC();
    public static Timer highScoreTimer = new Timer();
    private boolean north, south, east, west;
    private int spaceCount = 0;
    private boolean farmerTalked = true;
    private boolean roadbuilderTalked = false;
    private boolean farmerTalk = false;
    private boolean professorTalk = false;
    private boolean mechanicTalk = false;
    private boolean talking = false;
    private boolean firstTimeEntering = true;
    private String[] direction = {"North", "South", "West", "East"};
    private SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private FireAnimation fireAnimation = new FireAnimation();
    private BrokeMachineAnimation brokeMachineAnimation = new BrokeMachineAnimation();
    private OldLadyAnimation oldLadyAnimation = new OldLadyAnimation();
    private PigeonAnimation pigeonAnimation = new PigeonAnimation();
    private NoAccess noAccess = new NoAccess();
    private double[] numbersPlayer;
    private double[] numbersFire;
    private double[] numbersBrokenFire;
    private double[] numbersOldLady;
    private double[] numbersPig;
    private long animationWalk = 0L;
    private long oldLadyWalk = 0L;
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
    private AudioMusicPlayer npcTalk = new AudioMusicPlayer("src/sample/presentation/audio/npcTalking.wav");
    private AudioMusicPlayer repairSound = new AudioMusicPlayer("src/sample/presentation/audio/repairSound.wav");
    private AudioMusicPlayer pickUpSound = new AudioMusicPlayer("src/sample/presentation/audio/pickUpSound.wav");
    private long dialogueAnimation = 0L;
    private boolean isInventoryFull = false;
    private boolean gameOver = false;
    private int animationBird = 0;
    private Timeline oldLadyTimeline = new Timeline();
    private Timeline pigeonTimeline = new Timeline();

    @FXML
    private ImageView backgroundRoom;
    @FXML
    public ImageView roadView;
    @FXML
    public ImageView roadBuilderView;
    @FXML
    public ImageView player;
    @FXML
    public ImageView smoke;
    @FXML
    public ImageView smokeBrokenMachine;
    @FXML
    public Rectangle inventory;
    @FXML
    public ImageView professorNpc;
    @FXML
    public ImageView mechanicNpc;
    @FXML
    public ImageView farmerNpc;
    @FXML
    public ImageView fishermanNpc;
    @FXML
    public ImageView oldLadyNPC;
    @FXML
    public ImageView dialogBox;
    @FXML
    private ImageView toolsetImg;
    @FXML
    private Text NPCTextLine;
    @FXML
    private Text NPCTextLine1;
    @FXML
    private Text NPCTextLine2;
    @FXML
    private Text playerText;
    @FXML
    private Rectangle toolRect;
    @FXML
    private TextField nameField;
    @FXML
    private ImageView lockToolSlot;
    @FXML
    private ImageView map;
    @FXML
    private Rectangle mapBackground;
    @FXML
    private Rectangle startMouseClick;
    @FXML
    private Text mapText;
    @FXML
    private ImageView dialogNPC;
    @FXML
    public ImageView pigeon = new ImageView("file:src/sample/presentation/pictures/birds.png");



    public void initialize() {
        playerText.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine1.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine2.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        map.setImage(new Image("file:src/sample/presentation/pictures/Map.png"));
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/StartScreen.png"));
    }

    public void generatePlasticInRoom(List<Plastic> plasticList) {
        clearPlasticInRoom();
        ImageView[] plast = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                plast16, plast17, plast18, plast19, plast20};

        for (int i = 0; i < plasticList.size(); i++) {
            if (plasticList.get(i) != null) {
                plast[i].setImage(new Image("file:" + plasticList.get(i).getImage()));
                plast[i].setTranslateX(plasticList.get(i).getPosition()[0]);
                plast[i].setTranslateY(plasticList.get(i).getPosition()[1]);
                plast[i].setFitHeight(30);
                plast[i].setFitWidth(30);
                // Kode til at give plastik ny position hvis de falder inden for no go zonerne.
                if (noAccess.moveBlock(plast[i].getTranslateX(), plast[i].getTranslateY(), 0, -2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plast[i].getTranslateX(), plast[i].getTranslateY(), 0, 2)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plast[i].getTranslateX(), plast[i].getTranslateY(), -2, 0)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                } else if (noAccess.moveBlock(plast[i].getTranslateX(), plast[i].getTranslateY(), 2, 0)) {
                    plasticList.get(i).newPosition();
                    generatePlasticInRoom(plasticList);
                    break;
                }
            }
        }
    }

    public void clearPlasticInRoom() {
        ImageView[] plast = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                plast16, plast17, plast18, plast19, plast20};

        for (ImageView image : plast) {
            image.setTranslateX(3000);
            image.setTranslateY(3000);
        }
    }


    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (gameNotStarted) {
                north = false;
                south = false;
                east = false;
                west = false;
            }
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
            } else if (south && player.getTranslateY() < 220) {
                playerAnimation.setDirection(direction[1]);
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), 0, 2)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateY(player.getTranslateY() + 2.5);

                }
                animationWalk++;
            } else if (east && player.getTranslateX() > -340) {
                playerAnimation.setDirection(direction[2]);
                if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), -2, 0)) {
                    if (animationWalk % 13 == 0) {
                        numbersPlayer = playerAnimation.changePic();
                        player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
                    }
                    player.setTranslateX(player.getTranslateX() - 2.5);
                }
                animationWalk++;
            } else if (west && player.getTranslateX() < 340) {
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
        ImageView[] inventoryItems = {item1, item2, item3, item4, item5, item6, item7, item8, item9, item10};
        inventoryObservable.removeAll();
        ArrayList<Plastic> playersInv = new ArrayList<>(playerObject.getPlasticInv());
        for (int i = 0; i < inventoryItems.length; i++) {
            if (i < playerObject.getPlasticInv().size()) {
                inventoryItems[i].setImage(new Image("file:" + playersInv.get(i).getImage()));
                // Da plastik imageviews har en standard layout i FXML dokumentet, så skal der bruges LayoutX i stedet for TranslateX
                inventoryItems[i].setLayoutX((147 + i * 45) + playerObject.getPlasticInv().get(i).getAdjustXForInventory());
            } else {
                inventoryItems[i].setImage(null);
            }
        }
    }

    public void collectPlastic(List<Plastic> plasticList) {
        Timeline timeline = fullInventory();
        timeline.stop();
        ImageView[] plast = {plast1, plast2, plast3, plast4, plast5, plast6, plast7, plast8, plast9, plast10, plast11, plast12, plast13, plast14, plast15,
                plast16, plast17, plast18, plast19, plast20};
        if (playerObject.getPlasticInv().size() < 10 && roadbuilderTalked) {
            for (int i = 0; i < plast.length; i++) {
                if (plast[i].getTranslateX() - 15 <= player.getTranslateX() && plast[i].getTranslateX() + 15 >= player.getTranslateX()) {
                    if (plast[i].getTranslateY() - 15 <= player.getTranslateY() && plast[i].getTranslateY() + 15 >= player.getTranslateY()) {
                        pickUpSound.AudioPlayer();
                        playerObject.plasticCollect(plasticList.get(i), Main.game.getCurrentRoom());
                        plast[i].setTranslateX(3000);
                        updateInventory();
                    }
                }
            }
        } else if (playerObject.getPlasticInv().size() >= 10) {
            for (int i = 0; i < plast.length; i++) {
                if (plast[i].getTranslateX() - 15 <= player.getTranslateX() && plast[i].getTranslateX() + 15 >= player.getTranslateX()) {
                    if (plast[i].getTranslateY() - 15 <= player.getTranslateY() && plast[i].getTranslateY() + 15 >= player.getTranslateY()) {
                        isInventoryFull = true;
                    }
                }
            }
            if (isInventoryFull) {
                dialogueAnimation = 0;
                timeline.play();
            }
        }
    }

    public Timeline fullInventory() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            dialogueAnimation++;
            if (dialogueAnimation == 1 && isInventoryFull) {
                playerText.setTranslateY(-130);
                talkNPC(playerText, "Player", 0);
                dialogueAnimation++;
            } else if (dialogueAnimation == 120) {
                hideDialogBox();
                isInventoryFull = false;
                dialogueAnimation++;
            }
        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        return timeline;
    }



    /*
    public int adjustInventoryItem(Plastic plastic) {
        if (plastic instanceof CleaningPlastic) {
            return -3;
        } else if (plastic instanceof MilkBottle) {
            return -7;
        } else if (plastic instanceof SodaBottle) {
            return
        }
        return 0;
    }

     */

    public void movePlayer(KeyEvent keyEvent) throws InterruptedException {
        switch (keyEvent.getCode()) {
            case P:
                if (gameOver) {
                    Main main = new Main();
                    Stage primaryStage = new Stage();
                    ControllerAsk.theStage = primaryStage;
                    try {
                        main.start(primaryStage);
                        Parent askRoot = FXMLLoader.load(getClass().getResource("askSample.fxml"));
                        Scene scene2 = new Scene(askRoot);
                        primaryStage.setScene(scene2);
                        primaryStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case M:
                if (!gameNotStarted) {
                    map.setTranslateX(0);
                    mapBackground.setOpacity(0.7);
                }
                break;
            case H:
                try {
                    Desktop.getDesktop().open(new File("src/sample/presentation/pictures/video/Introduction.mp4"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case I:
                if (gameOver) {
                    try {
                        Desktop.getDesktop().open(new File("src/sample/presentation/pictures/video/KWS Plasticroad.mp4"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case UP:
            case W:
                timer.start();
                north = true;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case DOWN:
            case S:
                timer.start();
                south = true;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case LEFT:
            case A:
                timer.start();
                east = true;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case RIGHT:
            case D:
                timer.start();
                west = true;
                System.out.println("y =  " + player.getTranslateY() + " x = " + player.getTranslateX());
                break;
            case SPACE:
                if (numberOfMovement == 0) {
                    if (gameNotStarted) {
                        String name = nameField.getText();
                        if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
                            playerObject.setNames(name);
                            nameField.setOpacity(0);
                            startGame();
                            gameNotStarted = false;
                        }
                    } else if (!isInventoryFull) {
                        collectPlastic(Main.game.placePlastic());
                    }

                    if (Main.game.getCurrentRoom() instanceof RoadBuild && talkingRoadbuilder && spaceCount != 0 && roadbuilderTalked) {
                        if (spaceCount == 1) {
                            talkNPC(playerText, "Road builder", 3);
                            spaceCount++;
                        } else if (spaceCount == 2) {
                            hideDialogBox();
                            spaceCount = 0;
                        }
                    } else if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateX() > roadBuilderView.getTranslateX() - 50 && player.getTranslateX() < roadBuilderView.getTranslateX() + 50 && player.getTranslateY() > roadBuilderView.getTranslateY() - 50 && player.getTranslateY() < roadBuilderView.getTranslateY() + 50) {
                        if (!roadbuilderTalked) {
                            showDialogBox();
                        }
                        if (roadBuilder.getInventoryCount() >= 19 && roadBuilder.isNotDamagedBefore() && roadbuilderTalked) {
                            roadBuilder.damagedMachine();
                            roadBuilder.setNotDamagedBefore(false);
                            roadbuilderCrashSound.AudioPlayer();
                        } else if (playerObject.getHaveToolset() && roadBuilder.getDamaged() > 0) {
                            repairSound.musicPlayerInfinity();
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
                            isInventoryFull = false;
                        }

                    } else if (Main.game.getCurrentRoom() instanceof Farm && player.getTranslateX() > farmerNpc.getTranslateX() - 30 && player.getTranslateX() < farmerNpc.getTranslateX() + 30 && player.getTranslateY() > farmerNpc.getTranslateY() - 30 && player.getTranslateY() < farmerNpc.getTranslateY() + 30) {
                        showDialogBox();
                    } else if (Main.game.getCurrentRoom() instanceof Sdu && player.getTranslateX() > professorNpc.getTranslateX() - 30 && player.getTranslateX() < professorNpc.getTranslateX() + 30 && player.getTranslateY() > professorNpc.getTranslateY() - 30 && player.getTranslateY() < professorNpc.getTranslateY() + 30) {
                        showDialogBox();
                    } else if (Main.game.getCurrentRoom() instanceof Town && player.getTranslateX() > mechanicNpc.getTranslateX() - 30 && player.getTranslateX() < mechanicNpc.getTranslateX() + 30 && player.getTranslateY() > mechanicNpc.getTranslateY() - 30 && player.getTranslateY() < mechanicNpc.getTranslateY() + 30) {
                        showDialogBox();
                    } else if (Main.game.getCurrentRoom() instanceof Park && player.getTranslateX() > oldLadyNPC.getTranslateX() - 30 && player.getTranslateX() < oldLadyNPC.getTranslateX() + 30 && player.getTranslateY() > oldLadyNPC.getTranslateY() - 30 && player.getTranslateY() < oldLadyNPC.getTranslateY() + 30) {
                        showDialogBox();
                    } else if (Main.game.getCurrentRoom() instanceof Beach && player.getTranslateX() > fishermanNpc.getTranslateX() - 30 && player.getTranslateX() < fishermanNpc.getTranslateX() + 30 && player.getTranslateY() > fishermanNpc.getTranslateY() - 30 && player.getTranslateY() < fishermanNpc.getTranslateY() + 30) {
                        showDialogBox();
                    }

                }
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
            } else if (doneRepairing && roadBuilder.getDamaged() == 0 && !gameOver) {
                hideDialogBox();
                toolsetImg.setTranslateX(3000);
                lockToolSlot.setOpacity(0.7);
                doneRepairing = false;
                repairSound.AudioStop();
            }
            counterRepair++;
        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    private void endGame() {
        if (roadBuilder.getInventoryCount() >= Main.game.getRoadDone()) {
            gameOver = true;
            //Sets the highscorebackground
            backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/EndScreen.png"));
            //Presents the score
            highScoreTimer.setEndTime();
            playerText.setText("Your " + highScoreTimer.timeScore());
            playerText.setTranslateY(130);
            String[] scoreList = highScoreTimer.setHighScore();
            NPCTextLine.setTranslateY(-80);
            NPCTextLine1.setTranslateY(0);
            NPCTextLine2.setTranslateY(85);
            NPCTextLine.setText(scoreList[0]);
            NPCTextLine1.setText(scoreList[1]);
            NPCTextLine2.setText(scoreList[2]);
            //resets the road and game
            roadBuilder.setInventoryCount(0);
            gameNotStarted = true;
            //Hide images
            hideSlotLines();
            mapText.setOpacity(0);
            lockToolSlot.setOpacity(0);
            roadBuilderView.setOpacity(0);
            roadView.setOpacity(0);
            player.setOpacity(0);
            smoke.setOpacity(0);
            inventory.setOpacity(0);
            toolRect.setOpacity(0);
            //Lay out new plastic
            clearPlasticInRoom();
            //Resets NPCs
            farmerTalk = false;
            professorTalk = false;
            mechanicTalk = false;
            talkingRoadbuilder = false;
            doneRepairing = true;
        }
    }

    private void startGame() {
        backgroundMusic.AudioStop();
        gameOver = false;
        Main.game.createRooms();
        //Create the images
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/Sdu.png"));
        player.setImage(new Image("file:" + playerObject.getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + road.getImage()));
        roadBuilderView.setImage(new Image("file:" + roadBuilder.getImage()));
        professorNpc.setImage(new Image("file:" + professorObject.getImage()));
        mechanicNpc.setImage(new Image("file:" + mechanicObject.getImage()));
        farmerNpc.setImage(new Image("file:" + farmerObject.getImage()));
        pigeon.setImage(new Image("file:src/sample/presentation/pictures/birds.png"));
        fishermanNpc.setImage(new Image("file:" + fishermanObject.getImage()));
        oldLadyNPC.setImage(new Image("file:" + oldLadyObject.getImage()));
        dialogBox.setImage(new Image("file:" + dialog.getImage()));
        smoke.setImage(new Image("file:src/sample/presentation/pictures/buildSmoke.png"));
        smokeBrokenMachine.setImage(new Image("file:src/sample/presentation/pictures/fireSmoke-1.png"));
        //Show images (& hides highscore)
        showSlotLines();
        mapText.setOpacity(1);
        lockToolSlot.setOpacity(0.7);
        inventory.setOpacity(0.5);
        toolRect.setOpacity(0.5);
        roadBuilderView.setOpacity(1);
        roadView.setOpacity(1);
        player.setOpacity(1);
        smoke.setOpacity(1);
        hideDialogBox();
        //Generates plastic and runs the animations
        generatePlasticInRoom(Main.game.placePlastic());
        movementMachine();
        smokeMachine();
        smokeBrokenMachine();
        checkForNpcs();
        //Starts the time for highscorelist
        highScoreTimer.setStartTime();
        backgroundMusic.musicPlayerInfinity();
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
        } else if (Main.game.getCurrentRoom() instanceof Beach && player.getTranslateX() > 328 && player.getTranslateY() > -100 && player.getTranslateY() < -61.5) {
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
            case M:
                map.setTranslateX(3000);
                mapBackground.setOpacity(0);
                break;
        }
    }

    public void checkForNpcs() {
        showBirdAnimation();
        showOldLady();
    }

    public void showNpcs() {
        if (Main.game.getCurrentRoom() instanceof Park) {
            pigeon.setOpacity(1);
            oldLadyNPC.setOpacity(1);

        } else {
            pigeon.setOpacity(0);
            oldLadyNPC.setOpacity(0);
        }
    }

    public void changeNorth() {
        if (professorTalk) {
            if (!(Main.game.getCurrentRoom() instanceof RoadBuild) || Main.game.getCurrentRoom() instanceof RoadBuild ^ !roadbuilderTalked) {
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
                showNpcs();
                if (!roadbuilderTalked) {
                    NPCTextLine.setTranslateY(-210);
                    talkNPC(NPCTextLine, "Road builder", 4);
                }
            }
        }
    }

    public void changeSouth() {
        if (roadbuilderTalked) {
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
            showNpcs();
        }
    }

    public void changeWest() {
        if (roadbuilderTalked) {
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
            showNpcs();
        }
    }

    public void changeEast() {
        if (roadbuilderTalked) {
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
            showNpcs();
        }
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
            if (numberOfMovement != 0 && Main.game.getCurrentRoom() instanceof RoadBuild && roadBuilderView.getTranslateX() > -290) {
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
        if (roadBuilder.getInventoryCount() >= 30) {
            endGame();
        } else {
            showFarmer();
            showProfessor();
            showMechanic();
            showFisherman();
            if (Main.game.getCurrentRoom() instanceof RoadBuild) {
                roadView.setViewport(new Rectangle2D(-681 + (roadBuilder.getInventoryCount() * 18.9166 + 113.5), 0, 681, 69));
            } else {
                roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
            }
            showRoadBuilder();
        }

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
            mechanicNpc.setTranslateX(178);
            mechanicNpc.setTranslateY(50);
        }
    }


    public void showFarmer() {
        farmerNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Farm) {
            farmerNpc.setTranslateX(190);
            farmerNpc.setTranslateY(2);
        }
    }

    public void showBirdAnimation() {
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (Main.game.getCurrentRoom() instanceof Park) {
                if (animationBird % 20 == 0) {
                    numbersPig = pigeonAnimation.changePic();
                    pigeon.setViewport(new Rectangle2D(numbersPig[0], numbersPig[1], numbersPig[2], numbersPig[3]));
                    double pigeonHeight = pigeon.getTranslateY();
                    double pigeonWidth = pigeon.getTranslateX();
                    if (numbersPig[4] % 48 > 0 && numbersPig[4] % 48 <= 12) {
                        pigeonHeight += 10;
                    } else if (numbersPig[4] % 48 > 12 && numbersPig[4] % 48 <= 24) {
                        pigeonWidth -= 10;
                    } else if (numbersPig[4] % 48 > 24 && numbersPig[4] % 48 <= 36) {
                        pigeonHeight -= 10;
                    } else if (numbersPig[4] % 48 > 36 && numbersPig[4] % 48 < 48 || numbersPig[4] % 48 == 0) {
                        pigeonWidth += 10;
                    }
                    pigeon.setTranslateY(pigeonHeight);
                    pigeon.setTranslateX(pigeonWidth);
                }
                animationBird++;
            }
        });
        pigeonTimeline.setCycleCount(pigeonTimeline.INDEFINITE);
        pigeonTimeline.getKeyFrames().add(frame);
        pigeonTimeline.play();
    }

    public void showFisherman() {
        fishermanNpc.setTranslateX(3000);
        if (Main.game.getCurrentRoom() instanceof Beach) {
            fishermanNpc.setTranslateX(-126);
            fishermanNpc.setTranslateY(-134);
        }
    }

    public void showOldLady() {
        oldLadyWalk = 0;
        if (Main.game.getCurrentRoom() instanceof Park) {
            int FPS = 60;
            KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
                if (talking) {
                    oldLadyNPC.setViewport(new Rectangle2D(0, 0, numbersOldLady[2], numbersOldLady[3]));
                } else if (oldLadyWalk % 20 == 0) {
                    if (oldLadyWalk < 500) {
                        numbersOldLady = oldLadyAnimation.changePic();
                        oldLadyNPC.setViewport(new Rectangle2D(numbersOldLady[0], numbersOldLady[1], numbersOldLady[2], numbersOldLady[3]));
                        oldLadyNPC.setTranslateY(oldLadyNPC.getTranslateY() + 10);
                    } else if (oldLadyWalk < 1000) {
                        numbersOldLady = oldLadyAnimation.changePic();
                        oldLadyNPC.setViewport(new Rectangle2D(numbersOldLady[0], 144, numbersOldLady[2], numbersOldLady[3]));
                        oldLadyNPC.setTranslateY(oldLadyNPC.getTranslateY() - 10);
                    } else {
                        oldLadyWalk = 0;
                    }
                }
                oldLadyWalk++;
            });
            oldLadyTimeline.setCycleCount(oldLadyTimeline.INDEFINITE);
            oldLadyTimeline.getKeyFrames().add(frame);
            oldLadyTimeline.play();
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
        npcTalk.AudioStop();
        dialogNPC.setImage(null);
    }

    public void showDialogBox() {
        NPCTextLine.setTranslateY(-210);
        NPCTextLine1.setTranslateY(-190);
        NPCTextLine2.setTranslateY(-170);
        playerText.setTranslateY(-130);


        //Roadbuilder
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            if (spaceCount == 0 && !roadbuilderTalked) {
                npcTalk.musicPlayerInfinity();
                talking = true;
                talkNPC(NPCTextLine, "Road builder", 5);
                talkNPC(NPCTextLine1, "Road builder", 6);
                spaceCount++;
            } else if (spaceCount == 1) {
                talkNPC(NPCTextLine, "Road builder", 7);
                talkNPC(NPCTextLine1, "Road builder", 8);
                talkNPC(NPCTextLine2, "Road builder", 9);
                spaceCount++;
            } else if (spaceCount == 2) {
                talkNPC(NPCTextLine, "Road builder", 10);
                NPCTextLine1.setText("");
                NPCTextLine2.setText("");
                spaceCount++;
            } else if (spaceCount == 3) {
                NPCTextLine1.setText("");
                talkNPC(playerText, "Road builder", 11);
                spaceCount++;
            } else if (spaceCount == 4) {
                hideDialogBox();
                roadbuilderTalked = true;
            }
        }
        //Farmer
        if (Main.game.getCurrentRoom() instanceof Farm) {
            if (farmerTalked) {
                if (spaceCount == 0 && !farmerTalk) {
                    dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/farmer.png"));
                    npcTalk.musicPlayerInfinity();
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
                    playerText.setText("");
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
                        farmerTalked = false;
                    } else {
                        hideDialogBox();
                    }
                }
            } else {
                if (spaceCount == 0 && !farmerTalk) {
                    dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/farmer.png"));
                    npcTalk.musicPlayerInfinity();
                    talking = true;
                    talkNPC(NPCTextLine, "farmer", 8);
                    farmerTalk = playerObject.addPlasticInv();
                    updateInventory();
                    spaceCount++;
                    if (!farmerTalk) {
                        talkNPC(NPCTextLine, "farmer", 7);
                        playerText.setText("");
                    }
                } else if (spaceCount == 1) {
                    if (!farmerTalk) {
                        hideDialogBox();
                        spaceCount = 0;
                    } else {
                        hideDialogBox();
                        farmerTalk = true;
                    }
                }
            }
            //Professor
        } else if (Main.game.getCurrentRoom() instanceof Sdu) {
            if (spaceCount == 0 && !professorTalk) {
                dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/Professor.png"));
                npcTalk.musicPlayerInfinity();
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
                playerText.setText("");
                spaceCount++;
            } else if (spaceCount == 3) {
                talkNPC(NPCTextLine, "professor", 5);
                spaceCount++;
            } else if (spaceCount == 4) {
                talkNPC(playerText, "professor", 6);
                spaceCount++;
            } else if (spaceCount == 5) {
                hideDialogBox();
                professorTalk = true;
            }
            //Mechanic
        } else if (Main.game.getCurrentRoom() instanceof Town) {
            if (roadBuilder.getDamaged() > 0) {
                if (spaceCount == 0 && !mechanicTalk) {
                    dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/Mechanic.png"));
                    npcTalk.musicPlayerInfinity();
                    talkNPC(NPCTextLine, "mechanic", 0);
                    talkNPC(NPCTextLine1, "mechanic", 1);
                    spaceCount++;
                } else if (spaceCount == 1) {
                    talkNPC(playerText, "mechanic", 2);
                    spaceCount++;
                } else if (spaceCount == 2) {
                    talkNPC(NPCTextLine, "mechanic", 3);
                    NPCTextLine1.setText("");
                    playerText.setText("");
                    spaceCount++;
                    playerObject.setToolset(mechanicObject.giveToolset());
                    lockToolSlot.setOpacity(0);
                    toolsetImg.setImage(new Image("file:" + playerObject.getToolset().getImage()));
                    toolsetImg.setTranslateX(650);
                    toolsetImg.setTranslateY(458);
                    toolsetImg.setFitHeight(60);
                    toolsetImg.setFitWidth(60);
                } else if (spaceCount == 3) {
                    hideDialogBox();
                    mechanicTalk = true;
                }
            } else if (roadBuilder.getDamaged() == 0) {
                if (spaceCount == 0 && !mechanicTalk) {
                    dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/Mechanic.png"));
                    talking = true;
                    npcTalk.musicPlayerInfinity();
                    talkNPC(NPCTextLine, "mechanic", 4);
                    spaceCount++;
                } else if (spaceCount == 1) {
                    hideDialogBox();
                    talking = false;
                    spaceCount = 0;
                }
            }
        } else if (Main.game.getCurrentRoom() instanceof Park) {
            if (spaceCount == 0) {
                dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/OldLady.png"));
                dialogNPC.setViewport(new Rectangle2D(0,0,32,48));
                npcTalk.musicPlayerInfinity();
                talking = true;
                talkNPC(NPCTextLine, "oldLady", 0);
                talkNPC(NPCTextLine1, "oldLady", 1);
                talkNPC(NPCTextLine2, "oldLady", 2);
                spaceCount++;
            } else if (spaceCount == 1) {
                int road = (int) (((double) roadBuilder.getInventoryCount() / (double) Main.game.getRoadDone()) * 100);
                playerText.setText("I have built " + road + "% of the road.");
                spaceCount++;
            } else if (spaceCount == 2) {
                talkNPC(NPCTextLine, "oldLady", 3);
                NPCTextLine1.setText("");
                NPCTextLine2.setText("");
                playerText.setText("");
                spaceCount++;
            } else if (spaceCount == 3) {
                hideDialogBox();
            }
        } else if (Main.game.getCurrentRoom() instanceof Beach) {
            if (spaceCount == 0) {
                dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/fisherMan.png"));
                npcTalk.musicPlayerInfinity();
                talking = true;
                talkNPC(NPCTextLine, "Fisherman", 0);
                spaceCount++;
            } else if (spaceCount == 1) {
                NPCTextLine.setText("");
                talkNPC(NPCTextLine, "Fisherman", 1);
                spaceCount++;
            } else if (spaceCount == 2) {
                NPCTextLine.setText("");
                talkNPC(NPCTextLine, "Fisherman", 2);
                spaceCount++;
            } else if (spaceCount == 3) {
                hideDialogBox();
            }
        }

    }

    private void talkNPC(Text npcText, String npcType, int index) {
        dialogBox.setTranslateY(-170);
        npcText.setText(dialog.getNPCText(npcType, index));
    }

    public void hideSlotLines() {
        Line[] lines = {slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9};

        for (Line line : lines) {
            line.setOpacity(0);
        }
    }

    public void showSlotLines() {
        Line[] lines = {slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9};

        for (Line line : lines) {
            line.setOpacity(0.5);
        }
    }

    public void startGameRect(MouseEvent mouseEvent) {
        if (gameNotStarted) {
            String name = nameField.getText();
            if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
                playerObject.setNames(name);
                nameField.setOpacity(0);
                startGame();
                gameNotStarted = false;
            }
        }
    }

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

    @FXML
    private ImageView item1;

    @FXML
    private ImageView item2;

    @FXML
    private ImageView item3;

    @FXML
    private ImageView item4;

    @FXML
    private ImageView item5;

    @FXML
    private ImageView item6;

    @FXML
    private ImageView item7;

    @FXML
    private ImageView item8;

    @FXML
    private ImageView item9;

    @FXML
    private ImageView item10;

    // De 9 slot linjer under inkaplser de 10 inventory pladser

    @FXML
    private Line slot1;

    @FXML
    private Line slot2;

    @FXML
    private Line slot3;

    @FXML
    private Line slot4;

    @FXML
    private Line slot5;

    @FXML
    private Line slot6;

    @FXML
    private Line slot7;

    @FXML
    private Line slot8;

    @FXML
    private Line slot9;
}

