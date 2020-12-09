package sample.presentation;

import javafx.scene.shape.Rectangle;
import sample.domain.Rooms.*;

import java.awt.geom.Area;


public class NoAccess {
    // The arrays in the as atributs are cordinates for the objects on the map.
    private int[] xCordinatesTree = new int[]{27, 38, 132, 145, 177, 144, 245, 265, -132, -125, -272, -262, 20, 30, 92, 102};
    private int[] yCordinatesTree = new int[]{36, 86, 44, 86, 106, 192, 114, 154, 129, 171, 134, 174, -245, -206, -221, -188};
    private int[] xCordinateBush = new int[]{-210,-295,-340,-182,77,62,305,152,275,315,-77};
    private int[] yCordinateBush = new int[]{64,46,159,181,129,169,99,-216,-208,-191,128};
    private int[] xCordinatesTreeFarm = new int[]{-324,-312,-182,-182,-319,-307,248,255,298,315};
    private int[] yCordinatesTreeFarm = new int[]{91,116,-221,-221,-186,-153,36,71,104,144};
    private int[] xCordinatesPalm = new int[]{92,117,252,274,14,34,157,179,14,37,267,287,-25,0};
    private int[] yCordinatesPalm = new int[]{-220,-170,-221,-193,-78,-26,-21,26,101,144,81,126,-221,-193};

    // This method is returning a boolean that determines if the player should walk or not
    // The parameters objx and objy, are the cordinates of the player objects on the map
    // x and y are buffer values, so that the player only can move close to the rectangles, but not on the
    // if the players touches the a rectangle he wont be albe to move agian.
    public boolean moveBlock(double objx, double objy, int x, int y) {
        // the boolean has to be false in the begning, so the player can move
        boolean cantMove = false;
        //RoadBuild room restrictions
        if (Main.game.getCurrentRoom() instanceof RoadBuild) {
            // Here a rectangle is defined, and then if the player object is +x and +y close to, he wont be able to move
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
            // A methode has been made, for trees and bushes, with the same parameters as moveblock.
            // It is onlue been made for bushees, tree and palm because they have the same size.
            if (treeIdentifyer(xCordinatesTree, yCordinatesTree, objx, objy, x, y)) {
                cantMove = true;
            }
            if (bushIdentifyer(xCordinateBush, yCordinateBush, objx, objy, x, y)) {
                cantMove = true;
            }
        }
        //Beach
        if (Main.game.getCurrentRoom() instanceof Beach) {
            Rectangle ocean = new Rectangle(-340.5, -221, 240, 500);
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
            if(palmIdentifyer(xCordinatesPalm,yCordinatesPalm,objx,objy,x,y)){
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
            if (treeIdentifyer(xCordinatesTreeFarm, yCordinatesTreeFarm, objx, objy, x, y)) {
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
            Rectangle labyrinthOuterButtom = new Rectangle(187.5, -111, 200, 30);
            if (labyrinthOuterButtom.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthFirstWall = new Rectangle(160, -150, 70, 30);
            if (labyrinthFirstWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthSecondWall = new Rectangle(255, -150, 30, 55);
            if (labyrinthSecondWall.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthVertiWallOne = new Rectangle(180, -221, 30, 60);
            if (labyrinthVertiWallOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthVertiWallTwo = new Rectangle(233, -223, 30, 66);
            if (labyrinthVertiWallTwo.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthHoriWallOne = new Rectangle(265, -213, 50, 30);
            if (labyrinthHoriWallOne.contains(objx + x, objy + y)) {
                cantMove = true;
            }
            Rectangle labyrinthVertiWallThree = new Rectangle(298, -212, 30, 70);
            if (labyrinthVertiWallThree.contains(objx + x, objy + y)) {
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
    // Returns a boolean that says if the player are x and y cloes to the area.
    // Has the same atributes as the MoveBlock but also cordinates from the class atributes
    public boolean treeIdentifyer(int[] xCordinates, int[] yCordinates, double objx, double objy, int x, int y) {
        // Area of the tree is made.
        Area tree = new Area();
        // Defines boolean
        boolean inrange = false;
        // This for loop runs through the cordinates in the class atribute arrays, and makes new area.
        for (int i = 0; i < xCordinates.length; i++) {
            // The tree crown area is made and put into posotion
            Area treeCrown = new Area(new java.awt.Rectangle(xCordinates[i], yCordinates[i], 55, 50));
            // The arrays are setup so that index 0 is the three crown and index 1 is the tree stem
            // Therefor count it one up
            i++;
            // Creates the area for the tree stem
            Area treeStem = new Area(new java.awt.Rectangle(xCordinates[i], yCordinates[i], 15, 34));
            // Adds the to areas into one area
            tree.add(treeCrown);
            tree.add(treeStem);
            // If statement that checks if the player is to close to the tree
            if (tree.contains(objx + x, objy + y)) {
                // returns inrange true, is the player is to close
                inrange = true;
            }
        }
        return inrange;
    }
    // Same logic as the method above, just with a diffrent sized area
    public boolean bushIdentifyer(int[] xCordinates, int[] yCordinates, double objx, double objy, int x, int y) {
        boolean inrange = false;
        for (int i = 0; i < xCordinates.length; i++) {
            Area treeCrown = new Area(new java.awt.Rectangle(xCordinates[i], yCordinates[i], 33, 33));
            if (treeCrown.contains(objx + x, objy + y)) {
                inrange = true;
            }
        }
        return inrange;

    }
    // Same logic as the methods above, just with a diffrent sized areas
    public boolean palmIdentifyer(int[] xCordinates, int[] yCordinates, double objx, double objy, int x, int y) {
        Area tree = new Area();
        boolean inrange = false;
        for (int i = 0; i < xCordinates.length; i++) {
            Area treeCrown = new Area(new java.awt.Rectangle(xCordinates[i], yCordinates[i], 80, 55));
            i++;
            Area treeStem = new Area(new java.awt.Rectangle(xCordinates[i], yCordinates[i], 25, 60));
            tree.add(treeCrown);
            tree.add(treeStem);
            if (tree.contains(objx + x, objy + y)) {
                inrange = true;
            }
        }
        return inrange;
    }
}
