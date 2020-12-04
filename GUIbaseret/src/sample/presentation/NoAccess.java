package sample.presentation;

import javafx.scene.shape.Rectangle;
import sample.domain.Rooms.*;

public class NoAccess {
    public boolean moveBlock(double objx, double objy, int x, int y) {
        boolean cantMove = false;
        //RoadBuild
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
        //Beach
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
        //Farm
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
            Rectangle farmer = new Rectangle(170, -6, 43, 50);
            if (farmer.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        //SDU
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
            Rectangle closetBottomOne = new Rectangle(-300, 75, 80, 110);
            if (closetBottomOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle closetBottomTwo = new Rectangle(-210, 75, 75, 110);
            if (closetBottomTwo.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle professor = new Rectangle(2.5, -27, 50, 67);
            if (professor.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        //Park
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

            Rectangle leftBottomRightGarden = new Rectangle(130, 9, 210, 250);
            if (leftBottomRightGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topMiddelGarden = new Rectangle(-43, -221, 80, 110);
            if (topMiddelGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle bottomMiddelGarden = new Rectangle(-40, 59, 80, 150);
            if (bottomMiddelGarden.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle bottomMiddelTrees = new Rectangle(-70, -6, 122, 80);
            if (bottomMiddelTrees.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle topMiddelTrees = new Rectangle(-75, -126, 122, 70);
            if (topMiddelTrees.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        //Town
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
            Rectangle bottomOneHouse = new Rectangle(-272, 56.5, 90, 100);
            if (bottomOneHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle bottomTwoHouse = new Rectangle(-44, 54, 90, 100);
            if (bottomTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle threeTwoHouse = new Rectangle(83, 54, 85, 100);
            if (threeTwoHouse.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle mechanic = new Rectangle(158, 45, 40, 47.5);
            if (mechanic.contains(objx + x, objy + y)) {
                cantMove = true;
            }
        }
        return cantMove;
    }
}
