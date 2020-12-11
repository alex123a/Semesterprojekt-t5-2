package sample.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.domain.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    public static Game game;
    private String path = "src/sample/presentation/pictures/video/Introduction.mp4";

    //start() is the entry point into JavaFX. where JavaFX is executed
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Sets the title of the stage
        primaryStage.setTitle("Plastic Road");
        //Sets the icon of the stage
        primaryStage.getIcons().add(new Image("file:src/sample/presentation/pictures/keyItems/TrafficCone.png"));
        //Sets the scene to the root of the sample.fxml file, which is loaded in the FXMLLoader
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main.main() is the entry point into the application. The execution of the program.
    public static void main(String[] args) {
        //Starts the game loop in the game Class.
        game = new Game();
        game.goRoom();
        //Sets up the start room
        Controller.roomExit = new ArrayList<>(Arrays.asList(Game.startDirections));
        //Launches JavaFX
        launch(args);
    }

}
