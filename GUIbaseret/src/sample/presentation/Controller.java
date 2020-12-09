package sample.presentation;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.domain.*;
import sample.domain.PlasticElements.*;
import sample.domain.Rooms.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<String> roomExit = new ArrayList<>();
    private ObservableList<ImageView> inventoryObservable = FXCollections.observableList(new ArrayList<ImageView>());
    public static String background;
    public static DialogNPC dialog = new DialogNPC();
    public static Timer highScoreTimer = new Timer();
    private boolean north, south, east, west;
    private boolean generalFarmerTalk = true;
    private boolean roadbuilderTalk = false;
    private boolean farmerTalk = false;
    private boolean professorTalk = false;
    private boolean mechanicTalk = false;
    private boolean talking = false;
    private boolean gameNotStarted = true;
    private boolean talkingRoadbuilder = false;
    private boolean doneRepairing = true;
    private boolean isInventoryFull = false;
    private boolean gameOver = false;
    private final String[] direction = {"North", "South", "West", "East"};
    private final SpriteAnimation playerAnimation = new SpriteAnimation(direction[0]);
    private final FireAnimation fireAnimation = new FireAnimation();
    private final BrokeMachineAnimation brokeMachineAnimation = new BrokeMachineAnimation();
    private final OldLadyAnimation oldLadyAnimation = new OldLadyAnimation();
    private final PigeonAnimation pigeonAnimation = new PigeonAnimation();
    private final NoAccess noAccess = new NoAccess();
    private double[] numbersPlayer;
    private double[] numbersFire;
    private double[] numbersBrokenFire;
    private double[] numbersOldLady;
    private double[] numbersPig;
    private long animationWalk = 0L;
    private long oldLadyWalk = 0L;
    private long animationFireSmoke = 0L;
    private long animationFireSmokeBrokenMachine = 0L;
    private long animationDriving = 0L;
    private long dialogueAnimation = 0L;
    private int numberOfMovement = 0;
    private int spaceCount = 0;
    private int counterRepair = 0;
    private int animationBird = 0;
    private final AudioMusicPlayer backgroundMusic = new AudioMusicPlayer("src/sample/presentation/audio/BackgroundMusic.wav");
    private final AudioMusicPlayer roadbuilderCrashSound = new AudioMusicPlayer("src/sample/presentation/audio/RoadbuildCrash.wav");
    private final AudioMusicPlayer roadbuilderMovingSound = new AudioMusicPlayer("src/sample/presentation/audio/RoadbuilderMovingSound.wav");
    private final AudioMusicPlayer npcTalk = new AudioMusicPlayer("src/sample/presentation/audio/npcTalking.wav");
    private final AudioMusicPlayer repairSound = new AudioMusicPlayer("src/sample/presentation/audio/repairSound.wav");
    private final AudioMusicPlayer pickUpSound = new AudioMusicPlayer("src/sample/presentation/audio/pickUpSound.wav");

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
    public ImageView pigeon;


    public void initialize() {
        //Sets the font for the text.
        playerText.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine1.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        NPCTextLine2.setFont(Font.font("Dialog", FontWeight.BOLD, 11));
        //Sets the start image and map image.
        map.setImage(new Image("file:src/sample/presentation/pictures/keyItems/Map.png"));
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
                //Makes the player stand firm while the start screen is presented.
                north = false;
                south = false;
                east = false;
                west = false;
            }
            //Because it is in 'else if' statements the player can't move obliquely, and one direction at a time.
            //Therefore we only have four directions, instead of eight. (ex. south-east)
            if (north && player.getTranslateY() > -220) {
                animationHandle(0,0,-2);
            } else if (south && player.getTranslateY() < 220) {
                animationHandle(1,0,2);
            } else if (west && player.getTranslateX() > -340) {
                animationHandle(2,-2,0);
            } else if (east && player.getTranslateX() < 340) {
                animationHandle(3,2,0);
            }
        }
    };

    public void animationHandle(int dir, int x, int y) {
        playerAnimation.setDirection(direction[dir]);
        if (!noAccess.moveBlock(player.getTranslateX(), player.getTranslateY(), x, y)) {
            if (animationWalk % 13 == 0) {
                numbersPlayer = playerAnimation.changePic();
                player.setViewport(new Rectangle2D(numbersPlayer[0], numbersPlayer[1], numbersPlayer[2], numbersPlayer[3]));
            }
            switch (direction[dir]) {
                case "North":
                    player.setTranslateY(player.getTranslateY() - 2.5);
                    break;
                case "South":
                    player.setTranslateY(player.getTranslateY() + 2.5);
                    break;
                case "East":
                    player.setTranslateX(player.getTranslateX() + 2.5);
                    break;
                case "West":
                    player.setTranslateX(player.getTranslateX() - 2.5);
                    break;
            }
        }
        animationWalk++;
    }

    // Need this method
    public void updateInventory() {
        ImageView[] inventoryItems = {item1, item2, item3, item4, item5, item6, item7, item8, item9, item10};
        inventoryObservable.removeAll();
        ArrayList<Plastic> playersInv = new ArrayList<>(Main.game.getPlayerObject().getPlasticInv());
        for (int i = 0; i < inventoryItems.length; i++) {
            if (i < Main.game.getPlayerObject().getPlasticInv().size()) {
                inventoryItems[i].setImage(new Image("file:" + playersInv.get(i).getImage()));
                //Plastic imageviews have a standard layout in the FXML document, which makes us use LayoutX here instead of TranslateX
                inventoryItems[i].setLayoutX((147 + i * 45) + Main.game.getPlayerObject().getPlasticInv().get(i).getAdjustXForInventory());
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
        if (Main.game.getPlayerObject().getPlasticInv().size() < 10 && roadbuilderTalk) {
            for (int i = 0; i < plast.length; i++) {
                if (plast[i].getTranslateX() - 15 <= player.getTranslateX() && plast[i].getTranslateX() + 15 >= player.getTranslateX()) {
                    if (plast[i].getTranslateY() - 15 <= player.getTranslateY() && plast[i].getTranslateY() + 15 >= player.getTranslateY()) {
                        //Sound when picking up plastic
                        pickUpSound.AudioPlayer();
                        Main.game.getPlayerObject().plasticCollect(plasticList.get(i), Main.game.getCurrentRoom());
                        plast[i].setTranslateX(3000);
                        updateInventory();
                    }
                }
            }
        } else if (Main.game.getPlayerObject().getPlasticInv().size() >= 10) {
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

    public void movePlayer(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case P:
                if (gameOver) {
                    ControllerAsk.startAsk();
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
                break;
            case DOWN:
            case S:
                timer.start();
                south = true;
                break;
            case LEFT:
            case A:
                timer.start();
                west = true;
                break;
            case RIGHT:
            case D:
                timer.start();
                east = true;
                break;
            case SPACE:
                if (numberOfMovement == 0) {
                    if (gameNotStarted) {
                        namePlayer();
                    } else if (!isInventoryFull) {
                        collectPlastic(Main.game.placePlastic());
                    }
                    if (Main.game.getCurrentRoom() instanceof RoadBuild && talkingRoadbuilder && spaceCount != 0 && roadbuilderTalk) {
                        if (spaceCount == 1) {
                            talkNPC(playerText, "Road builder", 3);
                            spaceCount++;
                        } else if (spaceCount == 2) {
                            hideDialogBox();
                            spaceCount = 0;
                        }
                    } else if (Main.game.getCurrentRoom() instanceof RoadBuild && player.getTranslateX() > roadBuilderView.getTranslateX() - 50 && player.getTranslateX() < roadBuilderView.getTranslateX() + 50 && player.getTranslateY() > roadBuilderView.getTranslateY() - 50 && player.getTranslateY() < roadBuilderView.getTranslateY() + 50) {
                        damagedDialog();
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

    private void namePlayer() {
        String name = nameField.getText();
        if (name.matches(".*[0-9].*") || name.matches(".*[A-Z]*.")) {
            Main.game.getPlayerObject().setNames(name);
            nameField.setOpacity(0);
            startGame();
            gameNotStarted = false;
        }
    }

    public void repairMachine() {
        Timeline timeline = new Timeline();
        int FPS = 60;
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (counterRepair % 60 == 0 && Main.game.getRoadBuilder().getDamaged() > 0) {
                Main.game.getPlayerObject().getToolset().repairMachine();
                dialogBox.setTranslateY(-170);
                NPCTextLine1.setText(100 - Main.game.getRoadBuilder().getDamaged() + "% repaired");
            } else if (doneRepairing && Main.game.getRoadBuilder().getDamaged() == 0 && !gameOver) {
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
        if (Main.game.getRoadBuilder().getInventoryCount() >= Main.game.getRoadDone()) {
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
            Main.game.getRoadBuilder().setInventoryCount(0);
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
        //Starts the background music
        backgroundMusic.AudioStop();
        gameOver = false;
        Main.game.createRooms();
        //Create the images
        backgroundRoom.setImage(new Image("file:src/sample/presentation/pictures/Backgrounds/Sdu.png"));
        player.setImage(new Image("file:" + Main.game.getPlayerObject().getImage()));
        player.setViewport(new Rectangle2D(0, 0, 32, 48));
        roadView.setImage(new Image("file:" + Main.game.getRoad().getImage()));
        roadBuilderView.setImage(new Image("file:" + Main.game.getRoadBuilder().getImage()));
        professorNpc.setImage(new Image("file:" + Main.game.getProfessorObject().getImage()));
        mechanicNpc.setImage(new Image("file:" + Main.game.getMechanicObject().getImage()));
        farmerNpc.setImage(new Image("file:" + Main.game.getFarmerObject().getImage()));
        pigeon.setImage(new Image("file:src/sample/presentation/pictures/Animations/birds.png"));
        fishermanNpc.setImage(new Image("file:" + Main.game.getFishermanObject().getImage()));
        oldLadyNPC.setImage(new Image("file:" + Main.game.getOldLadyObject().getImage()));
        dialogBox.setImage(new Image("file:" + dialog.getImage()));
        smoke.setImage(new Image("file:src/sample/presentation/pictures/Animations/buildSmoke.png"));
        smokeBrokenMachine.setImage(new Image("file:src/sample/presentation/pictures/Animations/fireSmoke-1.png"));
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
        //sets the players start postion
        player.setTranslateX(-40);
        player.setTranslateY(0);
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
                west = false;
                break;
            case RIGHT:
            case D:
                timer.stop();
                animationWalk = 0;
                east = false;
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
        showProfessor();
    }

    public void showNPCs(String dir) {
        Game.changedRoom = dir;
        Main.game.goRoom();
        backgroundRoom.setImage(new Image("file:" + background));
        hideDialogBox();
        generatePlasticInRoom(Main.game.placePlastic());
        //Execute every time the player changes room. Move the image on TranslateX from 3000 to their position in the screen.
        showRoad();
        showFarmer();
        showProfessor();
        showMechanic();
        showFisherman();
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
            if (!(Main.game.getCurrentRoom() instanceof RoadBuild) || Main.game.getCurrentRoom() instanceof RoadBuild ^ !roadbuilderTalk) {
                if (Main.game.getCurrentRoom() instanceof RoadBuild || Main.game.getCurrentRoom() instanceof Sdu) {
                    player.setTranslateY(204);
                }

                showNPCs("north");
                if (!roadbuilderTalk) {
                    dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/keyItems/RoadBuilder.png"));
                    dialogNPC.setScaleX(3.5);
                    dialogNPC.setScaleY(3.5);
                    NPCTextLine.setTranslateY(-210);
                    talkNPC(NPCTextLine, "Road builder", 4);
                }
            }
        }
    }

    public void changeSouth() {
        if (roadbuilderTalk) {
            if (Main.game.getCurrentRoom() instanceof Park || Main.game.getCurrentRoom() instanceof RoadBuild) {
                player.setTranslateY(-200);
            }
            if (Main.game.getCurrentRoom() instanceof Park) {
                player.setTranslateX(-117.5);
            }
            if (Main.game.getCurrentRoom() instanceof RoadBuild) {
                player.setTranslateY(-150);
            }
            showNPCs("south");
        }
    }

    public void changeWest() {
        if (roadbuilderTalk) {
            if (Main.game.getCurrentRoom() instanceof RoadBuild || Main.game.getCurrentRoom() instanceof Town || Main.game.getCurrentRoom() instanceof Farm) {
                player.setTranslateX(327);
            }
            if (Main.game.getCurrentRoom() instanceof Town) {
                player.setTranslateY(-77);
            }
            if (Main.game.getCurrentRoom() instanceof Farm) {
                player.setTranslateY(-33.5);
            }
            showNPCs("west");
        }
    }

    public void changeEast() {
        if (roadbuilderTalk) {
            if (Main.game.getCurrentRoom() instanceof Beach || Main.game.getCurrentRoom() instanceof Park || Main.game.getCurrentRoom() instanceof RoadBuild) {
                player.setTranslateX(-327);
            }
            if (Main.game.getCurrentRoom() instanceof RoadBuild) {
                player.setTranslateY(-30);
            }
            if (Main.game.getCurrentRoom() instanceof Park) {
                player.setTranslateY(-76);
            }
            showNPCs("east");
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
            if (Main.game.getCurrentRoom() instanceof RoadBuild && Main.game.getRoadBuilder().getDamaged() > 0) {
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
                    roadView.setViewport(new Rectangle2D(-681 + (Main.game.getRoadBuilder().getInventoryCount() - numberOfMovement / 4) * 18.9166 + 113.5, 0, 681, 69));
                    roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
                    roadBuilderView.setTranslateX((300 - ((Main.game.getRoadBuilder().getInventoryCount() - numberOfMovement / 4) * 18.9166 + 113.5) + 90));
                    --numberOfMovement;
                }
                animationDriving++;
            } else {
                showRoad();
                roadbuilderMovingSound.AudioStop();
            }

        });
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    public void showRoad() {
        if (Main.game.getRoadBuilder().getInventoryCount() >= 30) {
            endGame();
        } else {
            if (Main.game.getCurrentRoom() instanceof RoadBuild) {
                roadView.setViewport(new Rectangle2D(-681 + (Main.game.getRoadBuilder().getInventoryCount() * 18.9166 + 113.5), 0, 681, 69));
            } else {
                roadView.setViewport(new Rectangle2D(-681, 0, 681, 69));
            }
            showRoadBuilder();
        }

    }

    public void showRoadBuilder() {
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            roadBuilderView.setViewport(new Rectangle2D(0, 0, 484, 323));
            roadBuilderView.setTranslateX(300 - ((Main.game.getRoadBuilder().getInventoryCount() * 18.9166 + 113.5) - 90));
        } else {
            roadBuilderView.setViewport(new Rectangle2D(-484, 0, 484, 323));
        }
    }

    public void showProfessor() {
        if (Main.game.getCurrentRoom() instanceof Sdu) {
            professorNpc.setTranslateX(30);
        } else {
            professorNpc.setTranslateX(3000);
        }
    }

    public void showMechanic() {
        if (Main.game.getCurrentRoom() instanceof Town) {
            mechanicNpc.setTranslateX(178);
            mechanicNpc.setTranslateY(50);
        } else {
            mechanicNpc.setTranslateX(3000);
        }
    }

    public void showFarmer() {
        if (Main.game.getCurrentRoom() instanceof Farm) {
            farmerNpc.setTranslateX(190);
            farmerNpc.setTranslateY(2);
        } else {
            farmerNpc.setTranslateX(3000);
        }
    }

    public void showFisherman() {
        if (Main.game.getCurrentRoom() instanceof Beach) {
            fishermanNpc.setTranslateX(-126);
            fishermanNpc.setTranslateY(-134);
        } else {
            fishermanNpc.setTranslateX(3000);
        }
    }

    public void showBirdAnimation() {
        int FPS = 60;
        Timeline pigeonTimeline = new Timeline();
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

    public void showOldLady() {
        oldLadyWalk = 0;
        int FPS = 60;
        Timeline oldLadyTimeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / FPS), event -> {
            if (Main.game.getCurrentRoom() instanceof Park) {
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
            }
        });
        oldLadyTimeline.setCycleCount(oldLadyTimeline.INDEFINITE);
        oldLadyTimeline.getKeyFrames().add(frame);
        oldLadyTimeline.play();

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
            dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/keyItems/RoadBuilder.png"));
            if (spaceCount == 0 && !roadbuilderTalk) {
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
                dialogNPC.setScaleX(1);
                dialogNPC.setScaleY(1);
                hideDialogBox();
                roadbuilderTalk = true;
            }
        }
        //Farmer
        if (Main.game.getCurrentRoom() instanceof Farm) {
            if (generalFarmerTalk) {
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
                    farmerTalk = Main.game.getPlayerObject().addPlasticInv();
                    if (!farmerTalk) {
                        talkNPC(NPCTextLine, "farmer", 7);
                        playerText.setText("");
                    }
                    updateInventory();
                    spaceCount++;
                } else if (spaceCount == 5) {
                    if (!farmerTalk) {
                        hideDialogBox();
                        generalFarmerTalk = false;
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
                    farmerTalk = Main.game.getPlayerObject().addPlasticInv();
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
            if (Main.game.getRoadBuilder().getDamaged() > 0) {
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
                    Main.game.getPlayerObject().setToolset(Main.game.getMechanicObject().giveToolset());
                    lockToolSlot.setOpacity(0);
                    toolsetImg.setImage(new Image("file:" + Main.game.getPlayerObject().getToolset().getImage()));
                    toolsetImg.setTranslateX(650);
                    toolsetImg.setTranslateY(458);
                    toolsetImg.setFitHeight(60);
                    toolsetImg.setFitWidth(60);
                } else if (spaceCount == 3) {
                    hideDialogBox();
                    mechanicTalk = true;
                }
            } else if (Main.game.getRoadBuilder().getDamaged() == 0) {
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
                dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/npc/OldLadyScale.png"));
                npcTalk.musicPlayerInfinity();
                talking = true;
                talkNPC(NPCTextLine, "oldLady", 0);
                talkNPC(NPCTextLine1, "oldLady", 1);
                talkNPC(NPCTextLine2, "oldLady", 2);
                spaceCount++;
            } else if (spaceCount == 1) {
                int road = (int) (((double) Main.game.getRoadBuilder().getInventoryCount() / (double) Main.game.getRoadDone()) * 100);
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

    public void damagedDialog() {
        if (!roadbuilderTalk) {
            showDialogBox();
        }
        if (Main.game.getRoadBuilder().getInventoryCount() >= 19 && Main.game.getRoadBuilder().isNotDamagedBefore() && roadbuilderTalk) {
            dialogNPC.setImage(new Image("file:src/sample/presentation/pictures/keyItems/RoadBuilder.png"));
            dialogNPC.setScaleX(3.5);
            dialogNPC.setScaleY(3.5);
            Main.game.getRoadBuilder().damagedMachine();
            Main.game.getRoadBuilder().setNotDamagedBefore(false);
            roadbuilderCrashSound.AudioPlayer();
        } else if (Main.game.getPlayerObject().getHaveToolset() && Main.game.getRoadBuilder().getDamaged() > 0) {
            repairSound.musicPlayerInfinity();
            repairMachine();
        }

        if (Main.game.getRoadBuilder().getDamaged() > 0 && !Main.game.getPlayerObject().getHaveToolset()) {
            NPCTextLine.setTranslateY(-210);
            NPCTextLine1.setTranslateY(-190);
            NPCTextLine2.setTranslateY(-170);
            playerText.setTranslateY(-130);
            if (spaceCount == 0 && Main.game.getRoadBuilder().getDamaged() > 0) {
                dialogNPC.setScaleX(1);
                dialogNPC.setScaleY(1);
                talkNPC(NPCTextLine, "Road builder", 0);
                talkNPC(NPCTextLine1, "Road builder", 1);
                talkNPC(NPCTextLine2, "Road builder", 2);
                spaceCount++;
                talkingRoadbuilder = true;
            }

        } else if (Main.game.getRoadBuilder().getDamaged() == 0) {
            numberOfMovement = Main.game.getPlayerObject().getPlasticInv().size() * 4;
            if (Main.game.getPlayerObject().getPlasticInv().size() > 0) {
                Main.game.givePlastic();
                roadbuilderMovingSound.AudioPlayer();
            }
            updateInventory();
            isInventoryFull = false;
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
            namePlayer();
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

